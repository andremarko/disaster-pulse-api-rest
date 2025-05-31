package disaster.pulse.api.dto.response;

import disaster.pulse.api.model.enums.Risco;

import java.time.LocalDateTime;

public record EventoResumoResponseDTO(
        Long id,
        String titulo,
        Risco risco,
        LocalDateTime dataInicio
) {}
