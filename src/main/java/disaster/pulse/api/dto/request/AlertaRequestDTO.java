package disaster.pulse.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record AlertaRequestDTO(
        @NotNull
        Long idEvento,
        @NotBlank
        @Size(min = 1, max = 255)
        String mensagem,
        @NotNull
        LocalDateTime dataHora
) {
}
