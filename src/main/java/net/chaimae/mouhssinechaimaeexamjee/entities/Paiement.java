package net.chaimae.mouhssinechaimaeexamjee.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "paiements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "contrat")
@EqualsAndHashCode(exclude = "contrat")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate datePaiement;

    @Column(nullable = false)
    private Double montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePaiement typePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrat_id", nullable = false)
    private Contrat contrat;

    public enum TypePaiement {
        MENSUALITE, PAIEMENT_ANNUEL, PAIEMENT_EXCEPTIONNEL
    }
}
