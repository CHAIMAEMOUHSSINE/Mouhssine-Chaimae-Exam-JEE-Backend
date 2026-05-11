package net.chaimae.mouhssinechaimaeexamjee.repositories;

import net.chaimae.mouhssinechaimaeexamjee.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
	List<Paiement> findByContratId(Long contratId);

	@Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.id = :contratId")
	Double sumMontantByContratId(@Param("contratId") Long contratId);
}
