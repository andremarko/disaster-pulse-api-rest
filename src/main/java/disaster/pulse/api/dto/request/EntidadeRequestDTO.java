package disaster.pulse.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EntidadeRequestDTO(

        @Schema(description = "Nome fantasia da entidade", example = "Defesa Civil do Estado de São Paulo")
        @NotBlank(message = "Nome é obrigatório")
        String nomeFantasia,

        @Schema(description = "E-mail de contato da entidade", example = "defesacivilsp@entidade.org")
        @NotBlank
        @Email
        String email,

        @Schema(description = "Telefone de contato da entidade", example = "")
        String telefone,

        @Schema(description = "CNPJ da entidade (somente números, 14 dígitos)", example = "12345678000195")
        @NotBlank
        @Size(min = 14, max = 14, message = "CNPJ deve conter exatamente 14 dígitos")
        String cnpj,

        @Schema(description = "Senha para acesso (6 a 12 caracteres)", example = "senha123")
        @NotBlank
        @Size(min = 6, max = 12, message = "Senha deve ter entre 6 e 12 caracteres")
        String senha
) {}
