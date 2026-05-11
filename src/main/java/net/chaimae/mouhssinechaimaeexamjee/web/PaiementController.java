package net.chaimae.mouhssinechaimaeexamjee.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Gestion des paiements")
@SecurityRequirement(name = "bearerAuth")
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping
    @Operation(summary = "Lister tous les paiements")
    public ResponseEntity<List<PaiementResponseDTO>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.getAllPaiements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un paiement par ID")
    public ResponseEntity<PaiementResponseDTO> getPaiementById(@PathVariable Long id) {
        return ResponseEntity.ok(paiementService.getPaiementById(id));
    }

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Obtenir les paiements d'un contrat")
    public ResponseEntity<List<PaiementResponseDTO>> getPaiementsByContrat(@PathVariable Long contratId) {
        return ResponseEntity.ok(paiementService.getPaiementsByContrat(contratId));
    }

    @GetMapping("/contrat/{contratId}/total")
    @Operation(summary = "Obtenir le total des paiements d'un contrat")
    public ResponseEntity<Map<String, Double>> getTotalPaiements(@PathVariable Long contratId) {
        return ResponseEntity.ok(Map.of("total", paiementService.getTotalPaiementsForContrat(contratId)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Enregistrer un paiement")
    public ResponseEntity<PaiementResponseDTO> createPaiement(@Valid @RequestBody PaiementRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.createPaiement(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
