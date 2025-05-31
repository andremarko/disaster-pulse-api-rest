package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.request.PedidoSosRequestDTO;
import disaster.pulse.api.dto.response.PedidoSosResponseDTO;
import disaster.pulse.api.dto.response.PedidoSosResumoResponseDTO;
import disaster.pulse.api.model.PedidoSOS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PedidoSosMapper {
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    PedidoSosResponseDTO toResponseDTO(PedidoSOS sos);
    PedidoSosResumoResponseDTO toResumoDTO(PedidoSOS sos);
    PedidoSOS toEntity(PedidoSosRequestDTO dto);
    void updateEntityFromDto(PedidoSosRequestDTO dto, @MappingTarget PedidoSOS sos);
    default String map(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(FORMATTER) : null;
    }
}
