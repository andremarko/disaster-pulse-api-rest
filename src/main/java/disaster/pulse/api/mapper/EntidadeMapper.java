package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.request.EntidadeRequestDTO;
import disaster.pulse.api.dto.response.EntidadeResponseDTO;
import disaster.pulse.api.model.Entidade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntidadeMapper {
    EntidadeResponseDTO toResponseDTO(Entidade entidade);
    @Mapping(target = "login", source = "cnpj")
    Entidade toEntity(EntidadeRequestDTO dto);
    void updateEntityFromDto(EntidadeRequestDTO dto,@MappingTarget Entidade entidade);
}
