package disaster.pulse.api.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank(message = "Login é obrigatório!") String login, @NotBlank(message = "Senha é obrigatória!") String senha) {}
