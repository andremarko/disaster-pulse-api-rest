package disaster.pulse.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CivilRequestDTO(

        @Schema(description = "Nome completo do cidadão", example = "José da Silva")
        @NotBlank
        String nomeCompleto,

        @Schema(description = "E-mail do cidadão", example = "jose.silva@example.com")
        @NotBlank
        @Email
        String email,

        @Schema(description = "CPF do cidadão (apenas números)", example = "12345678901")
        @NotBlank
        @Size(min = 11, max = 11, message = "CPF deve conter exatamente 11 dígitos")
        String cpf,

        @Schema(description = "Telefone para contato (opcional)", example = "")
        String telefone,

        @Schema(description = "Senha de acesso (6 a 12 caracteres)", example = "senha123")
        @NotBlank
        @Size(min = 6, max = 12, message = "Senha deve ter entre 6 e 12 caracteres")
        String senha
) {}
