package net.chaimae.mouhssinechaimaeexamjee.Config;


import lombok.RequiredArgsConstructor;
import net.chaimae.mouhssinechaimaeexamjee.Security.JwtAuthenticationFilter;
import net.chaimae.mouhssinechaimaeexamjee.Security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html").permitAll()
                        // Client endpoints
                        .requestMatchers(HttpMethod.GET, "/api/clients/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/clients/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/clients/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/clients/**").hasRole("ADMIN")
                        // Contrat endpoints
                        .requestMatchers(HttpMethod.GET, "/api/contrats/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/contrats/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contrats/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contrats/**").hasRole("ADMIN")
                        .requestMatchers("/api/contrats/*/valider", "/api/contrats/*/resilier").hasAnyRole("EMPLOYE", "ADMIN")
                        // Paiement endpoints
                        .requestMatchers(HttpMethod.GET, "/api/paiements/**").hasAnyRole("CLIENT", "EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/paiements/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/paiements/**").hasRole("ADMIN")
                        // Stats - admin only
                        .requestMatchers("/api/stats/**").hasAnyRole("EMPLOYE", "ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigins));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

