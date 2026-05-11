package net.chaimae.mouhssinechaimaeexamjee.repositories;

import ma.insurance.backend.entities.ContratAutomobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContratAutomobileRepository extends JpaRepository<ContratAutomobile, Long> {
    Optional<ContratAutomobile> findByNumeroImmatriculation(String numeroImmatriculation);
    List<ContratAutomobile> findByMarqueVehiculeIgnoreCase(String marque);
}
