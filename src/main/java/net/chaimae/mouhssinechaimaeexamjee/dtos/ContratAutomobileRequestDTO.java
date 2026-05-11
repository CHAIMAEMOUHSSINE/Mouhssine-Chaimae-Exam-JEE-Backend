package net.chaimae.mouhssinechaimaeexamjee.dtos;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratAutomobileRequestDTO {
    @NotNull(message = "La date de souscription est obligatoire")
    private LocalDate dateSouscription;

    @NotNull @Positive
    private Double montantCotisation;

    @NotNull @Positive
    private Integer dureeContrat;

    @NotNull @DecimalMin("0") @DecimalMax("100")
    private Double tauxCouverture;

    @NotNull
    private Long clientId;

    @NotBlank(message = "Le numéro d'immatriculation est obligatoire")
    private String numeroImmatriculation;

    @NotBlank(message = "La marque du véhicule est obligatoire")
    private String marqueVehicule;

    @NotBlank(message = "Le modèle du véhicule est obligatoire")
    private String modeleVehicule;
}
