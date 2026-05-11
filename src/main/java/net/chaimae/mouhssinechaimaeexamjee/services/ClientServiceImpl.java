package net.chaimae.mouhssinechaimaeexamjee.services;


import lombok.RequiredArgsConstructor;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ContratResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.entities.Client;
import net.chaimae.mouhssinechaimaeexamjee.mappers.ClientMapper;
import net.chaimae.mouhssinechaimaeexamjee.mappers.ContratMapper;
import net.chaimae.mouhssinechaimaeexamjee.repositories.ClientRepository;
import net.chaimae.mouhssinechaimaeexamjee.repositories.ContratRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ContratRepository contratRepository;
    private final ClientMapper clientMapper;
    private final ContratMapper contratMapper;

    @Override
    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un client avec cet email existe déjà: " + dto.getEmail());
        }
        Client client = clientMapper.toEntity(dto);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    public ClientResponseDTO updateClient(Long id, ClientRequestDTO dto) {
        Client client = findClientById(id);
        clientMapper.updateFromDTO(dto, client);
        return clientMapper.toDTO(clientRepository.save(client));
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponseDTO getClientById(Long id) {
        return clientMapper.toDTO(findClientById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteClient(Long id) {
        Client client = findClientById(id);
        clientRepository.delete(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponseDTO> searchClients(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom).stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponseDTO> getClientContrats(Long clientId) {
        findClientById(clientId); // validate exists
        return contratRepository.findByClientId(clientId).stream()
                .map(contratMapper::toDTOGeneric)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContratResponseDTO> getClientContratsPage(Long clientId, Pageable pageable) {
        findClientById(clientId);
        return contratRepository.findByClientId(clientId, pageable)
                .map(contratMapper::toDTOGeneric);
    }

    private Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec l'id: " + id));
    }
}
