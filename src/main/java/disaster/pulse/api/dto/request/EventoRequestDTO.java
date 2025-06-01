package disaster.pulse.api.dto.request;

import disaster.pulse.api.model.enums.Risco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventoRequestDTO(
        @Schema(description = "ID do tipo do evento. Confira o endpoint de TipoEvento", example = "1")
        @NotNull
        Long idTipoEvento,

        @Schema(description = "Título do evento", example = "Temporal e risco de enchentes no Butantã")
        @NotBlank
        @Size(min = 10, max = 50, message = "Máximo de 50 caracteres")
        String titulo,

        @Schema(description = "Descrição detalhada do evento (opcional)", example = "Previsão de fortes chuvas acompanhadas de risco elevado de enchentes na região do Butantã. Moradores devem permanecer atentos a possíveis alagamentos, quedas de árvores e " +
                "interrupções no trânsito.")
        @Size(min = 10, max = 256, message = "Máximo de 256 caracteres")
        String descricao,

        @Schema(description = "Data e hora de início do evento (formato ISO 8601)", example = "2025-05-30T14:30:00")
        @NotNull
        LocalDateTime dataInicio,

        @Schema(description = "Latitude da localização do evento", example = "-23.55052")
        @NotNull
        Double latitude,

        @Schema(description = "Longitude da localização do evento", example = "-46.633308")
        @NotNull
        Double longitude,

        @Schema(description = "Nível de risco do evento", example = "ALTO")
        @NotNull
        Risco risco
) {
}
