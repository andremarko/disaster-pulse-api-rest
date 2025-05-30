package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.request.CivilRequestDTO;
import disaster.pulse.api.dto.response.CivilResponseDTO;
import disaster.pulse.api.model.Civil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CivilMapper {

    CivilResponseDTO toResponseDTO(Civil civil);

    @Mapping(target = "login", source = "cpf")
    Civil toEntity(CivilRequestDTO dto);

    void updateEntityFromDto(CivilRequestDTO dto, @MappingTarget Civil civil);
}