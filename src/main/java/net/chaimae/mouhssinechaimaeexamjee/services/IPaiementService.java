package net.chaimae.mouhssinechaimaeexamjee.services;

import ma.insurance.backend.dtos.PaiementRequestDTO;
import ma.insurance.backend.dtos.PaiementResponseDTO;

import java.util.List;

public interface IPaiementService {
    PaiementResponseDTO createPaiement(PaiementRequestDTO dto);
    PaiementResponseDTO getPaiementById(Long id);
    List<PaiementResponseDTO> getPaiementsByContrat(Long contratId);
    List<PaiementResponseDTO> getAllPaiements();
    void deletePaiement(Long id);
    Double getTotalPaiementsForContrat(Long contratId);
}

