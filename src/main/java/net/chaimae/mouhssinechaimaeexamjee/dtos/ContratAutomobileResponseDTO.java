package net.chaimae.mouhssinechaimaeexamjee.dtos;


import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContratAutomobileResponseDTO extends ContratResponseDTO {
    private String numeroImmatriculation;
    private String marqueVehicule;
    private String modeleVehicule;
}

