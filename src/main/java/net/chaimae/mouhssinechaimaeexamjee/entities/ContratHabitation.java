package net.chaimae.mouhssinechaimaeexamjee.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contrats_habitation")
@DiscriminatorValue("HABITATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ContratHabitation extends Contrat {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeLogement typeLogement;

    @Column(nullable = false)
    private String adresseLogement;

    @Column(nullable = false)
    private Double superficie;

    public enum TypeLogement {
        APPARTEMENT, MAISON, LOCAL_COMMERCIAL
    }
}
