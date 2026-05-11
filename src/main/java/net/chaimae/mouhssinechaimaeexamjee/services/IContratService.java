package net.chaimae.mouhssinechaimaeexamjee.services;

package ma.insurance.backend.services;

import ma.insurance.backend.dtos.*;
import ma.insurance.backend.entities.Contrat.StatutContrat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IContratService {
    // Automobile
    ContratAutomobileResponseDTO createContratAutomobile(ContratAutomobileRequestDTO dto);
    ContratAutomobileResponseDTO updateContratAutomobile(Long id, ContratAutomobileRequestDTO dto);

    // Habitation
    ContratHabitationResponseDTO createContratHabitation(ContratHabitationRequestDTO dto);
    ContratHabitationResponseDTO updateContratHabitation(Long id, ContratHabitationRequestDTO dto);

    // Sante
    ContratSanteResponseDTO createContratSante(ContratSanteRequestDTO dto);
    ContratSanteResponseDTO updateContratSante(Long id, ContratSanteRequestDTO dto);

    // Common
    ContratResponseDTO getContratById(Long id);
    List<ContratResponseDTO> getAllContrats();
    Page<ContratResponseDTO> getAllContratsPage(Pageable pageable);
    void deleteContrat(Long id);
    ContratResponseDTO validerContrat(Long id);
    ContratResponseDTO resilierContrat(Long id);
    List<ContratResponseDTO> getContratsByStatut(StatutContrat statut);
    DashboardStatsDTO getDashboardStats();
}
