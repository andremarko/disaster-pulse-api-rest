package disaster.pulse.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AlertaRequestDTO(

        @Schema(
                description = "ID do evento previamente criado ao qual este alerta está relacionado",
                example = "1"
        )
        @NotNull
        Long idEvento,

        @Schema(
                description = "Mensagem de alerta a ser enviada para a população sobre o evento",
                example = "Risco de alagamento na região do Butantã. Evacuar pontos de risco!"
        )
        @NotBlank
        @Size(min = 1, max = 255)
        String mensagem,

        @Schema(
                description = "Data e hora da notificação do Alerta (formato ISO 8601)",
                example = "2025-05-30T15:45:00"
        )
        @NotNull
        LocalDateTime dataHora
) {
}
