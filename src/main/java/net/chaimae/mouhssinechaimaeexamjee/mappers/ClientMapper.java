package net.chaimae.mouhssinechaimaeexamjee.mappers;
package ma.insurance.backend.mappers;

import ma.insurance.backend.dtos.ClientRequestDTO;
import ma.insurance.backend.dtos.ClientResponseDTO;
import ma.insurance.backend.entities.Client;
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

