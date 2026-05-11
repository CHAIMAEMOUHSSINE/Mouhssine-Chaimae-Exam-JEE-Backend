package net.chaimae.mouhssinechaimaeexamjee.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}