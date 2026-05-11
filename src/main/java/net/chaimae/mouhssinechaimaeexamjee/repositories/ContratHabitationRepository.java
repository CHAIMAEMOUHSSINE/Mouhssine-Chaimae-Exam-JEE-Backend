package net.chaimae.mouhssinechaimaeexamjee.repositories;

import net.chaimae.mouhssinechaimaeexamjee.entities.ContratHabitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContratHabitationRepository extends JpaRepository<ContratHabitation, Long> {
	Optional<ContratHabitation> findByAdresseLogement(String adresse);
}
