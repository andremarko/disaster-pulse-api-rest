package disaster.pulse.api.dto.response;

public record LocalizacaoResponseDTO(
        Long idLocalizacao,
        String latitude,
        String longitude
) {}
