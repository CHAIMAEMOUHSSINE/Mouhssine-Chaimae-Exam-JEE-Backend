package net.chaimae.mouhssinechaimaeexamjee.services;


import lombok.RequiredArgsConstructor;
import ma.insurance.backend.dtos.*;
import ma.insurance.backend.entities.*;
import ma.insurance.backend.entities.Contrat.StatutContrat;
import ma.insurance.backend.mappers.ContratMapper;
import ma.insurance.backend.repositories.*;
import ma.insurance.backend.services.IContratService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final ContratAutomobileRepository automobileRepository;
    private final ContratHabitationRepository habitationRepository;
    private final ContratSanteRepository santeRepository;
    private final ClientRepository clientRepository;
    private final PaiementRepository paiementRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratAutomobileResponseDTO createContratAutomobile(ContratAutomobileRequestDTO dto) {
        Client client = findClient(dto.getClientId());
        ContratAutomobile contrat = contratMapper.toEntity(dto);
        contrat.setClient(client);
        return contratMapper.toDTO(automobileRepository.save(contrat));
    }

    @Override
    public ContratAutomobileResponseDTO updateContratAutomobile(Long id, ContratAutomobileRequestDTO dto) {
        ContratAutomobile contrat = automobileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat automobile non trouvé: " + id));
        contrat.setNumeroImmatriculation(dto.getNumeroImmatriculation());
        contrat.setMarqueVehicule(dto.getMarqueVehicule());
        contrat.setModeleVehicule(dto.getModeleVehicule());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        return contratMapper.toDTO(automobileRepository.save(contrat));
    }

    @Override
    public ContratHabitationResponseDTO createContratHabitation(ContratHabitationRequestDTO dto) {
        Client client = findClient(dto.getClientId());
        ContratHabitation contrat = contratMapper.toEntity(dto);
        contrat.setClient(client);
        return contratMapper.toDTO(habitationRepository.save(contrat));
    }

    @Override
    public ContratHabitationResponseDTO updateContratHabitation(Long id, ContratHabitationRequestDTO dto) {
        ContratHabitation contrat = habitationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat habitation non trouvé: " + id));
        contrat.setTypeLogement(dto.getTypeLogement());
        contrat.setAdresseLogement(dto.getAdresseLogement());
        contrat.setSuperficie(dto.getSuperficie());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        return contratMapper.toDTO(habitationRepository.save(contrat));
    }

    @Override
    public ContratSanteResponseDTO createContratSante(ContratSanteRequestDTO dto) {
        Client client = findClient(dto.getClientId());
        ContratSante contrat = contratMapper.toEntity(dto);
        contrat.setClient(client);
        return contratMapper.toDTO(santeRepository.save(contrat));
    }

    @Override
    public ContratSanteResponseDTO updateContratSante(Long id, ContratSanteRequestDTO dto) {
        ContratSante contrat = santeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat santé non trouvé: " + id));
        contrat.setNiveauCouverture(dto.getNiveauCouverture());
        contrat.setNombrePersonnesCouvertes(dto.getNombrePersonnesCouvertes());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        return contratMapper.toDTO(santeRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public ContratResponseDTO getContratById(Long id) {
        return contratMapper.toDTOGeneric(findContrat(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponseDTO> getAllContrats() {
        return contratMapper.toDTOList(contratRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContratResponseDTO> getAllContratsPage(Pageable pageable) {
        return contratRepository.findAll(pageable).map(contratMapper::toDTOGeneric);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.delete(findContrat(id));
    }

    @Override
    public ContratResponseDTO validerContrat(Long id) {
        Contrat contrat = findContrat(id);
        contrat.setStatut(StatutContrat.VALIDE);
        contrat.setDateValidation(LocalDate.now());
        return contratMapper.toDTOGeneric(contratRepository.save(contrat));
    }

    @Override
    public ContratResponseDTO resilierContrat(Long id) {
        Contrat contrat = findContrat(id);
        contrat.setStatut(StatutContrat.RESILIE);
        return contratMapper.toDTOGeneric(contratRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponseDTO> getContratsByStatut(StatutContrat statut) {
        return contratMapper.toDTOList(contratRepository.findByStatut(statut));
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardStatsDTO getDashboardStats() {
        return DashboardStatsDTO.builder()
                .totalClients(clientRepository.count())
                .totalContrats(contratRepository.count())
                .contratsEnCours(contratRepository.countByStatut(StatutContrat.EN_COURS))
                .contratsValides(contratRepository.countByStatut(StatutContrat.VALIDE))
                .contratsResilies(contratRepository.countByStatut(StatutContrat.RESILIE))
                .contratsAutomobile(automobileRepository.count())
                .contratsHabitation(habitationRepository.count())
                .contratsSante(santeRepository.count())
                .totalCotisations(contratRepository.findAll().stream()
                        .mapToDouble(Contrat::getMontantCotisation).sum())
                .totalPaiements(paiementRepository.count())
                .build();
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé: " + id));
    }

    private Contrat findContrat(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé: " + id));
    }
}

