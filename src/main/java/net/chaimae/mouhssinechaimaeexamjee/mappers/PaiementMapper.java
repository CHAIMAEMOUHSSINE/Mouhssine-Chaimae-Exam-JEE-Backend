package net.chaimae.mouhssinechaimaeexamjee.mappers;

package ma.insurance.backend.mappers;

import ma.insurance.backend.dtos.PaiementRequestDTO;
import ma.insurance.backend.dtos.PaiementResponseDTO;
import ma.insurance.backend.entities.Paiement;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(source = "contrat.id", target = "contratId")
    PaiementResponseDTO toDTO(Paiement paiement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    Paiement toEntity(PaiementRequestDTO dto);
}
