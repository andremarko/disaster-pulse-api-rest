package disaster.pulse.api.dto.response;

import disaster.pulse.api.model.enums.Status;

import java.time.LocalDateTime;

public record PedidoSosResponseDTO(
        Long idPedidoSos,
        EventoResponseDTO evento,
        CivilResumoResponseDTO civil,
        LocalDateTime data_hora,
        String comentario,
        Status status
) {
}
