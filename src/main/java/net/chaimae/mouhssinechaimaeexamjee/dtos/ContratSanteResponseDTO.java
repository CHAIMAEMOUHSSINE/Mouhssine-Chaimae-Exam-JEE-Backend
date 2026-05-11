package net.chaimae.mouhssinechaimaeexamjee.dtos;
import lombok.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.ContratSante.NiveauCouverture;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratSanteResponseDTO extends ContratResponseDTO {
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}
