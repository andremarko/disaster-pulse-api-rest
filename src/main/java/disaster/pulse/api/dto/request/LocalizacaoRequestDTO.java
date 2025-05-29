package disaster.pulse.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LocalizacaoRequestDTO(
        @NotBlank
        String latitude,
        @NotBlank
        String longitude
) {}
