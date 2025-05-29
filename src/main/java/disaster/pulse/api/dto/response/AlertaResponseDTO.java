package disaster.pulse.api.dto.response;

import java.time.LocalDateTime;

public record AlertaResponseDTO(
        Long idAlerta,
        EventoResponseDTO eventoResponseDTO,
        String mensagem,
        LocalDateTime dataHora
) {
}
