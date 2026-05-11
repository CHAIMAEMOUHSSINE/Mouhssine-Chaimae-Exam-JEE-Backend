package net.chaimae.mouhssinechaimaeexamjee.dtos;


import jakarta.validation.constraints.*;
import lombok.*;
import ma.insurance.backend.entities.ContratSante.NiveauCouverture;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratSanteRequestDTO {
    @NotNull private LocalDate dateSouscription;
    @NotNull @Positive private Double montantCotisation;
    @NotNull @Positive private Integer dureeContrat;
    @NotNull @DecimalMin("0") @DecimalMax("100") private Double tauxCouverture;
    @NotNull private Long clientId;
    @NotNull private NiveauCouverture niveauCouverture;
    @NotNull @Positive private Integer nombrePersonnesCouvertes;
}

