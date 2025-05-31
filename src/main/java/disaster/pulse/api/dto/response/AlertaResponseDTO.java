package disaster.pulse.api.dto.response;

import java.time.LocalDateTime;

public record AlertaResponseDTO(
        Long idAlerta,
        EventoResumoResponseDTO evento,
        String mensagem,
        LocalDateTime dataHora
) {
}
