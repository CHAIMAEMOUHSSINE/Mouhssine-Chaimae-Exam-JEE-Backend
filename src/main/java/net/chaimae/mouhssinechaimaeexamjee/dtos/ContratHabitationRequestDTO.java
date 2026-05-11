package net.chaimae.mouhssinechaimaeexamjee.dtos;

import jakarta.validation.constraints.*;
import lombok.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.ContratHabitation.TypeLogement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratHabitationRequestDTO {
    @NotNull private LocalDate dateSouscription;
    @NotNull @Positive private Double montantCotisation;
    @NotNull @Positive private Integer dureeContrat;
    @NotNull @DecimalMin("0") @DecimalMax("100") private Double tauxCouverture;
    @NotNull private Long clientId;
    @NotNull private TypeLogement typeLogement;
    @NotBlank private String adresseLogement;
    @NotNull @Positive private Double superficie;
}