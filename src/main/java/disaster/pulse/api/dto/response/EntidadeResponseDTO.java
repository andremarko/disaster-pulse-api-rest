package disaster.pulse.api.dto.response;

import java.util.List;

public record EntidadeResponseDTO(
        Long id,
        String nomeFantasia,
        String email,
        String telefone,
        String cnpj,
        List<EventoResumoResponseDTO> eventos
) {
}
