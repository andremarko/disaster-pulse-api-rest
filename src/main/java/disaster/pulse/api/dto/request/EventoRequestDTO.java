package disaster.pulse.api.dto.request;

import disaster.pulse.api.model.enums.Risco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventoRequestDTO(
        @NotNull
        Long idLocalizacao,
        @NotNull
        Long idTipoEvento,
        @NotNull
        Long idEntidade,
        @NotBlank
        @Size(min = 10, max = 50, message = "Máximo de 50 caracteres")
        String titulo,
        @Size(min = 10, max = 256, message = "Máximo de 256 caracteres")
        String descricao,
        @NotNull
        LocalDateTime dataInicio,
        @NotNull
        Risco risco
)   {
}
