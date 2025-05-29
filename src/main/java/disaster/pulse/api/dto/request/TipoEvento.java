package disaster.pulse.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TipoEvento(
        @Size(max=30, message = "Máximo de 30 caracteres")
        @NotBlank
        String tipo
) {
}
