package disaster.pulse.api.dto.response;

import disaster.pulse.api.model.enums.Status;

public record PedidoSosResponseDTO(
        Long idSos,
        CivilResumoResponseDTO civil,
        String dataHora,
        String comentario,
        Status status
) {
}
