package net.chaimae.mouhssinechaimaeexamjee.dtos;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {
    private Long id;
    private String nom;
    private String email;
    private int nombreContrats;
}