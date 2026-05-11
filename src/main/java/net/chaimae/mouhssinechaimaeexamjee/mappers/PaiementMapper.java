package net.chaimae.mouhssinechaimaeexamjee.mappers;

import net.chaimae.mouhssinechaimaeexamjee.dtos.PaiementRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.PaiementResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.entities.Paiement;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PaiementMapper {

    @Mapping(source = "contrat.id", target = "contratId")
    PaiementResponseDTO toDTO(Paiement paiement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrat", ignore = true)
    Paiement toEntity(PaiementRequestDTO dto);
}
