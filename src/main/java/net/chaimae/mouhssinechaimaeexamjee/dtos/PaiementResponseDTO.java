package net.chaimae.mouhssinechaimaeexamjee.dtos;

import lombok.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.Paiement.TypePaiement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaiementResponseDTO {
    private Long id;
    private LocalDate datePaiement;
    private Double montant;
    private TypePaiement typePaiement;
    private Long contratId;
}

