package disaster.pulse.api.dto.request;

import disaster.pulse.api.model.enums.Risco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventoRequestDTO(
        @NotNull
        Long idTipoEvento,
        @NotBlank
        @Size(min = 10, max = 50, message = "Máximo de 50 caracteres")
        String titulo,
        @Size(min = 10, max = 256, message = "Máximo de 256 caracteres")
        String descricao,
        @NotNull
        LocalDateTime dataInicio,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude,
        @NotNull
        Risco risco
)   {
}
