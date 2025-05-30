package disaster.pulse.api.mapper;

import disaster.pulse.api.dto.request.PedidoSosRequestDTO;
import disaster.pulse.api.dto.response.PedidoSosResponseDTO;
import disaster.pulse.api.model.PedidoSOS;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PedidoSosMapper {
    PedidoSosResponseDTO toResponseDTO(PedidoSOS sos);
    PedidoSOS toEntity(PedidoSosRequestDTO dto);
    void updateEntityFromDto(PedidoSosRequestDTO dto, @MappingTarget PedidoSOS sos);
}
