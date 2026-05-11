package net.chaimae.mouhssinechaimaeexamjee.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.chaimae.mouhssinechaimaeexamjee.dtos.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.Contrat.StatutContrat;
import net.chaimae.mouhssinechaimaeexamjee.services.IContratService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats d'assurance")
@SecurityRequirement(name = "bearerAuth")
public class ContratController {

    private final IContratService contratService;



    @GetMapping
    @Operation(summary = "Lister tous les contrats (paginé)")
    public ResponseEntity<Page<ContratResponseDTO>> getAllContrats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(contratService.getAllContratsPage(pageable));
    }

    @GetMapping("/all")
    @Operation(summary = "Lister tous les contrats sans pagination")
    public ResponseEntity<List<ContratResponseDTO>> getAllContratsNoPage() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un contrat par ID")
    public ResponseEntity<ContratResponseDTO> getContratById(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.getContratById(id));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Filtrer les contrats par statut")
    public ResponseEntity<List<ContratResponseDTO>> getContratsByStatut(@PathVariable StatutContrat statut) {
        return ResponseEntity.ok(contratService.getContratsByStatut(statut));
    }


    @PostMapping("/automobile")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Créer un contrat automobile")
    public ResponseEntity<ContratAutomobileResponseDTO> createAutomobile(
            @Valid @RequestBody ContratAutomobileRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.createContratAutomobile(dto));
    }

    @PostMapping("/habitation")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Créer un contrat habitation")
    public ResponseEntity<ContratHabitationResponseDTO> createHabitation(
            @Valid @RequestBody ContratHabitationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.createContratHabitation(dto));
    }

    @PostMapping("/sante")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Créer un contrat santé")
    public ResponseEntity<ContratSanteResponseDTO> createSante(
            @Valid @RequestBody ContratSanteRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.createContratSante(dto));
    }


    @PutMapping("/automobile/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Mettre à jour un contrat automobile")
    public ResponseEntity<ContratAutomobileResponseDTO> updateAutomobile(
            @PathVariable Long id,
            @Valid @RequestBody ContratAutomobileRequestDTO dto) {
        return ResponseEntity.ok(contratService.updateContratAutomobile(id, dto));
    }

    @PutMapping("/habitation/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Mettre à jour un contrat habitation")
    public ResponseEntity<ContratHabitationResponseDTO> updateHabitation(
            @PathVariable Long id,
            @Valid @RequestBody ContratHabitationRequestDTO dto) {
        return ResponseEntity.ok(contratService.updateContratHabitation(id, dto));
    }

    @PutMapping("/sante/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Mettre à jour un contrat santé")
    public ResponseEntity<ContratSanteResponseDTO> updateSante(
            @PathVariable Long id,
            @Valid @RequestBody ContratSanteRequestDTO dto) {
        return ResponseEntity.ok(contratService.updateContratSante(id, dto));
    }



    @PatchMapping("/{id}/valider")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Valider un contrat")
    public ResponseEntity<ContratResponseDTO> validerContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.validerContrat(id));
    }

    @PatchMapping("/{id}/resilier")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Résilier un contrat")
    public ResponseEntity<ContratResponseDTO> resilierContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.resilierContrat(id));
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        contratService.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }



    @GetMapping("/stats/dashboard")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Statistiques du tableau de bord")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(contratService.getDashboardStats());
    }
}
