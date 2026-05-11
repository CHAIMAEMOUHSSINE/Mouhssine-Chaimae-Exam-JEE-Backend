package net.chaimae.mouhssinechaimaeexamjee.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import ma.insurance.backend.entities.Contrat.StatutContrat;

import java.time.LocalDate;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "typeContrat", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContratAutomobileResponseDTO.class, name = "AUTOMOBILE"),
        @JsonSubTypes.Type(value = ContratHabitationResponseDTO.class, name = "HABITATION"),
        @JsonSubTypes.Type(value = ContratSanteResponseDTO.class, name = "SANTE")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratResponseDTO {
    private Long id;
    private String typeContrat;
    private LocalDate dateSouscription;
    private StatutContrat statut;
    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer dureeContrat;
    private Double tauxCouverture;
    private Long clientId;
    private String clientNom;
    private List<PaiementResponseDTO> paiements;
}
