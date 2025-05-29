package disaster.pulse.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoEventoRequestDTO(
        @Size(max=30, message = "Máximo de 30 caracteres")
        @NotBlank
        String tipo
) {
}
