package disaster.pulse.api.dto.response;

import disaster.pulse.api.model.enums.Risco;

import java.time.LocalDateTime;

public record EventoResponseDTO(
        Long idEvento,
        String titulo,
        String descricao,
        LocalDateTime dataInicio,
        Risco risco,
        LocalizacaoResponseDTO localizacaoResponseDTO,
        TipoEventoResponseDTO tipoEventoResponseDTO,
        EntidadeResumoResponseDTO entidade
) {}
