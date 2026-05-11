package net.chaimae.mouhssinechaimaeexamjee.repositories;

import net.chaimae.mouhssinechaimaeexamjee.entities.Contrat;
import net.chaimae.mouhssinechaimaeexamjee.entities.Contrat.StatutContrat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByClientId(Long clientId);
    List<Contrat> findByStatut(StatutContrat statut);
    Page<Contrat> findByClientId(Long clientId, Pageable pageable);
    long countByStatut(StatutContrat statut);

    
}

