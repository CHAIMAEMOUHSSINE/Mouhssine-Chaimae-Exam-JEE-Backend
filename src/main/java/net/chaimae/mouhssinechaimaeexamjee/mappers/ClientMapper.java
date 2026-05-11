package net.chaimae.mouhssinechaimaeexamjee.mappers;

import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientRequestDTO;
import net.chaimae.mouhssinechaimaeexamjee.dtos.ClientResponseDTO;
import net.chaimae.mouhssinechaimaeexamjee.entities.Client;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "nombreContrats", expression = "java(client.getContrats() != null ? client.getContrats().size() : 0)")
    ClientResponseDTO toDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrats", ignore = true)
    Client toEntity(ClientRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contrats", ignore = true)
    void updateFromDTO(ClientRequestDTO dto, @MappingTarget Client client);
}

