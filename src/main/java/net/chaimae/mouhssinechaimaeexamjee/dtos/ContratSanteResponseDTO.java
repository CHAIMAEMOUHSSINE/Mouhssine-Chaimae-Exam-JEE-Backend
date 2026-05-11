package net.chaimae.mouhssinechaimaeexamjee.dtos;
import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratSanteResponseDTO extends ContratResponseDTO {
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}
