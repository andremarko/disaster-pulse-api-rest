package disaster.pulse.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EntidadeRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nomeFantasia,

        @NotBlank
        @Email
        String email,

        String telefone,

        @NotBlank
        @Size(min = 14, max = 14, message = "CNPJ deve conter exatamente 14 dígitos")
        String cnpj,

        @NotBlank
        @Size(min = 6, max = 12, message = "Senha deve ter entre 6 e 12 caracteres")
        String senha
) {}

