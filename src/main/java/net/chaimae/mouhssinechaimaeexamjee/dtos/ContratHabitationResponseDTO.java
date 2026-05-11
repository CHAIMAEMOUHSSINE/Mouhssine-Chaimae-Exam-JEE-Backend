package net.chaimae.mouhssinechaimaeexamjee.dtos;

import lombok.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.ContratHabitation.TypeLogement;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratHabitationResponseDTO extends ContratResponseDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private Double superficie;
}