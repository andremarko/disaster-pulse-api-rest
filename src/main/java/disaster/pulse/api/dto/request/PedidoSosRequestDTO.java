package disaster.pulse.api.dto.request;

import disaster.pulse.api.model.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PedidoSosRequestDTO(
        @Schema(description = "Id do evento. Atente-se se já existe um evento cadastrado", example = "1")
        @NotNull
        Long idEvento,
        @Size(min=10, max=256)
        @Schema(description = "Comentário. Mínimo de 10 caracteres e máximo de 256.", example = "Estou ilhado na região do Butantã, muitos postes caídos")
        String comentario,
        @NotNull
        @Schema(description = "Status do pedido, se está em aberto ou se foi resolvido", example = "ABERTO")
        Status status
) {
}
