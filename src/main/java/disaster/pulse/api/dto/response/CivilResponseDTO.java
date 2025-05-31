package disaster.pulse.api.dto.response;

import java.util.List;

public record CivilResponseDTO(Long id,
                               String nomeCompleto,
                               String email,
                               String cpf,
                               String telefone,
                               List<PedidoSosResumoResponseDTO> sos
) {
}
