package net.chaimae.mouhssinechaimaeexamjee.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrats_automobile")
@DiscriminatorValue("AUTOMOBILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContratAutomobile extends Contrat {

    @Column(nullable = false)
    private String numeroImmatriculation;

    @Column(nullable = false)
    private String marqueVehicule;

    @Column(nullable = false)
    private String modeleVehicule;
}
