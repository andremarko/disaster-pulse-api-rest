package disaster.pulse.api.dto.request;

import disaster.pulse.api.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PedidoSosRequestDTO(
        @NotNull
        Long idEvento,
        @NotNull
        LocalDateTime dataHora,
        @Size(min=10, max=256)
        String comentario,
        @NotBlank
        Status status
) {
}
