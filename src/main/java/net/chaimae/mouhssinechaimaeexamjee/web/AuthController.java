package net.chaimae.mouhssinechaimaeexamjee.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.chaimae.mouhssinechaimaeexamjee.dtos.LoginRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.LoginResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.RegisterRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.entities.AppRole;
import net.chaimae.mouhssinechaimaeexamjee.entities.AppUser;
import net.chaimae.mouhssinechaimaeexamjee.repositories.AppRoleRepository;
import net.chaimae.mouhssinechaimaeexamjee.repositories.AppUserRepository;
import net.chaimae.mouhssinechaimaeexamjee.Security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints d'authentification")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    @Operation(summary = "Connexion utilisateur")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.generateAccessToken(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(request.getUsername());

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .username(userDetails.getUsername())
                .roles(roles)
                .build());
    }

    @PostMapping("/register")
    @Operation(summary = "Enregistrement d'un nouvel utilisateur (ROLE_CLIENT par défaut)")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Erreur: Username déjà utilisé!");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Erreur: Email déjà utilisé!");
        }

        AppRole clientRole = roleRepository.findByRoleName("ROLE_CLIENT")
                .orElseThrow(() -> new RuntimeException("Role ROLE_CLIENT non trouvé"));

        Set<AppRole> roles = new HashSet<>();
        roles.add(clientRole);

        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .active(true)
                .roles(roles)
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("Utilisateur enregistré avec succès!");
    }

    @PostMapping("/refresh")
    @Operation(summary = "Rafraîchir le token JWT")
    public ResponseEntity<LoginResponseDTO> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken)) {
            return ResponseEntity.badRequest().build();
        }
        String username = jwtUtils.getUsernameFromToken(refreshToken);
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> roles = user.getRoles().stream()
                .map(AppRole::getRoleName)
                .toList();

        // Build a minimal authentication for token generation
        var authorities = roles.stream()
                .map(r -> (GrantedAuthority) () -> r)
                .toList();

        var auth = new UsernamePasswordAuthenticationToken(
                new org.springframework.security.core.userdetails.User(username, "", authorities),
                null, authorities);

        String newAccessToken = jwtUtils.generateAccessToken(auth);
        String newRefreshToken = jwtUtils.generateRefreshToken(username);

        return ResponseEntity.ok(LoginResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .username(username)
                .roles(roles)
                .build());
    }
}
