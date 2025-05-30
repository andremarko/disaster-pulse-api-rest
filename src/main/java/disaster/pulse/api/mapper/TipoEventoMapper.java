package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.response.TipoEventoResponseDTO;
import disaster.pulse.api.model.TipoEvento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoEventoMapper {
    TipoEventoResponseDTO toResponseDTO(TipoEvento tipoEvento);
}
