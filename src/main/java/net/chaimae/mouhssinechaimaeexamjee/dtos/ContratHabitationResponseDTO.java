package net.chaimae.mouhssinechaimaeexamjee.dtos;

import lombok.*;
import ma.insurance.backend.entities.ContratHabitation.TypeLogement;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratHabitationResponseDTO extends ContratResponseDTO {
    private TypeLogement typeLogement;
    private String adresseLogement;
    private Double superficie;
}