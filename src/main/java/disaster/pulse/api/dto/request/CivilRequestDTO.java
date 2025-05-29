package disaster.pulse.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CivilRequestDTO(
        @NotBlank
        String nomeCompleto,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 11, max = 11, message = "CPF deve conter exatamente 11 dígitos")
        String cpf,

        String telefone,

        @NotBlank
        @Size(min = 6, max = 12, message = "Senha deve ter entre 6 e 12 caracteres")
        String senha
) {}

