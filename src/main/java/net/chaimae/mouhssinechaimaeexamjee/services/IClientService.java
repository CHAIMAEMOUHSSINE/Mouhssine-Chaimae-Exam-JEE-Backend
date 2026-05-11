package net.chaimae.mouhssinechaimaeexamjee.services;

import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ContratResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {
	ClientResponseDTO createClient(ClientRequestDTO dto);
	ClientResponseDTO updateClient(Long id, ClientRequestDTO dto);
	ClientResponseDTO getClientById(Long id);
	List<ClientResponseDTO> getAllClients();
	void deleteClient(Long id);
	List<ClientResponseDTO> searchClients(String nom);
	List<ContratResponseDTO> getClientContrats(Long clientId);
	Page<ContratResponseDTO> getClientContratsPage(Long clientId, Pageable pageable);
}
