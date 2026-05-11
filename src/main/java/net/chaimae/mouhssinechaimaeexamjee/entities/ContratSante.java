package net.chaimae.mouhssinechaimaeexamjee.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrats_sante")
@DiscriminatorValue("SANTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContratSante extends Contrat {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NiveauCouverture niveauCouverture;

    @Column(nullable = false)
    private Integer nombrePersonnesCouvertes;

    public enum NiveauCouverture {
        BASIQUE, INTERMEDIAIRE, PREMIUM
    }
}
