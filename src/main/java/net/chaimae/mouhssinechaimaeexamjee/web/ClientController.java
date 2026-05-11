package net.chaimae.mouhssinechaimaeexamjee.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ContratResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.services.IClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un client par ID")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des clients par nom")
    public ResponseEntity<List<ClientResponseDTO>> searchClients(@RequestParam String nom) {
        return ResponseEntity.ok(clientService.searchClients(nom));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Créer un nouveau client")
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    @Operation(summary = "Mettre à jour un client")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id,
                                                          @Valid @RequestBody ClientRequestDTO dto) {
        return ResponseEntity.ok(clientService.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/contrats")
    @Operation(summary = "Obtenir les contrats d'un client")
    public ResponseEntity<List<ContratResponseDTO>> getClientContrats(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientContrats(id));
    }

    @GetMapping("/{id}/contrats/page")
    @Operation(summary = "Obtenir les contrats d'un client (paginé)")
    public ResponseEntity<Page<ContratResponseDTO>> getClientContratsPage(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clientService.getClientContratsPage(id, pageable));
    }
}

