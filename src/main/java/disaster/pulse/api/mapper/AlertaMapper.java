package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.request.AlertaRequestDTO;
import disaster.pulse.api.dto.response.AlertaResponseDTO;
import disaster.pulse.api.model.Alerta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlertaMapper {
    AlertaResponseDTO toResponseDTO(Alerta alerta);
    Alerta toEntity(AlertaRequestDTO dto);
    void updateEntityFromDto(AlertaRequestDTO dto, @MappingTarget Alerta alerta);
}
