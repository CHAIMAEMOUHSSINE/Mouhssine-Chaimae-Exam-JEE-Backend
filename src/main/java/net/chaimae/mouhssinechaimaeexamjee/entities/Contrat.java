package net.chaimae.mouhssinechaimaeexamjee.entities;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contrats")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_contrat", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"client", "paiements"})
@EqualsAndHashCode(exclude = {"client", "paiements"})
public abstract class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate datesouscription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutContrat statut = StatutContrat.EN_COURS;

    private LocalDate dateValidation;

    @Column(nullable = false)
    private Double montantCotisation;

    @Column(nullable = false)
    private Integer dureeContrat; // in months

    @Column(nullable = false)
    private Double tauxCouverture; // percentage

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paiement> paiements = new ArrayList<>();

    public enum StatutContrat {
        EN_COURS, VALIDE, RESILIE
    }
}
