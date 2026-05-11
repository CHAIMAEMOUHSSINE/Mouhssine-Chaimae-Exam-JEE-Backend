package net.chaimae.mouhssinechaimaeexamjee.services;
package ma.insurance.backend.services.impl;

import lombok.RequiredArgsConstructor;
import ma.insurance.backend.dtos.PaiementRequestDTO;
import ma.insurance.backend.dtos.PaiementResponseDTO;
import ma.insurance.backend.entities.Contrat;
import ma.insurance.backend.entities.Paiement;
import ma.insurance.backend.mappers.PaiementMapper;
import ma.insurance.backend.repositories.ContratRepository;
import ma.insurance.backend.repositories.PaiementRepository;
import ma.insurance.backend.services.IPaiementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementResponseDTO createPaiement(PaiementRequestDTO dto) {
        Contrat contrat = contratRepository.findById(dto.getContratId())
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé: " + dto.getContratId()));
        Paiement paiement = paiementMapper.toEntity(dto);
        paiement.setContrat(contrat);
        return paiementMapper.toDTO(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementResponseDTO getPaiementById(Long id) {
        return paiementMapper.toDTO(paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé: " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponseDTO> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponseDTO> getAllPaiements() {
        return paiementRepository.findAll().stream()
                .map(paiementMapper::toDTO)
                .toList();
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Double getTotalPaiementsForContrat(Long contratId) {
        Double total = paiementRepository.sumMontantByContratId(contratId);
        return total != null ? total : 0.0;
    }
}
