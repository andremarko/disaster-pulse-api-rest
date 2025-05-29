package disaster.pulse.api.dto.response;

import java.util.List;

public record EntidadeResponseDTO(
        Long idEntidade,
        String nomeFantasia,
        String email,
        String telefone,
        String cnpj,
        List<EventoResponseDTO> eventos
) {
}
