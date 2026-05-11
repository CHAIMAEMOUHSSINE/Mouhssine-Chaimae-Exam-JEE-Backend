package net.chaimae.mouhssinechaimaeexamjee.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.Paiement.TypePaiement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementRequestDTO {
    @NotNull(message = "La date de paiement est obligatoire")
    private LocalDate datePaiement;

    @NotNull(message = "Le montant est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double montant;

    @NotNull(message = "Le type de paiement est obligatoire")
    private TypePaiement typePaiement;

    @NotNull(message = "L'ID du contrat est obligatoire")
    private Long contratId;
}
