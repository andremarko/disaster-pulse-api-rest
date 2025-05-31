package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.request.EventoRequestDTO;
import disaster.pulse.api.dto.response.EventoResponseDTO;
import disaster.pulse.api.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventoMapper {
    EventoResponseDTO toResponseDTO(Evento evento);
    Evento toEntity(EventoRequestDTO dto);
    void updateEntityFromDto(EventoRequestDTO dto, @MappingTarget Evento evento);
}
