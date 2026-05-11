package net.chaimae.mouhssinechaimaeexamjee.repositories;

import net.chaimae.mouhssinechaimaeexamjee.entities.ContratSante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratSanteRepository extends JpaRepository<ContratSante, Long> {

}
