package net.chaimae.mouhssinechaimaeexamjee.mappers;

import net.chaimae.mouhssinechaimaeexamjee.dtos.*;
import net.chaimae.mouhssinechaimaeexamjee.entities.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratMapper {
	ContratAutomobile toEntity(ContratAutomobileRequestDTO dto);
	ContratHabitation toEntity(ContratHabitationRequestDTO dto);
	ContratSante toEntity(ContratSanteRequestDTO dto);

	ContratAutomobileResponseDTO toDTO(ContratAutomobile contrat);
	ContratHabitationResponseDTO toDTO(ContratHabitation contrat);
	ContratSanteResponseDTO toDTO(ContratSante contrat);

	ContratResponseDTO toDTOGeneric(Contrat contrat);

	List<ContratResponseDTO> toDTOList(List<Contrat> contrats);
}
