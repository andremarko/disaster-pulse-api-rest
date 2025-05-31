package disaster.pulse.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "LoginDTO", description = "Dados para autenticação")
public record LoginDto(
        @Schema(
                description = "Identificação do usuário para login. Deve ser CPF para usuários do tipo CIVIL ou CNPJ para usuários do tipo ENTIDADE.",
                example = "12345678901 ou 12345678000199 (cpf para civil, cnpj para entidade)"
        )
        @NotBlank(message = "Login é obrigatório!")
        String login,
        @Schema(description = "Senha do usuário (mínimo de 6 e máximo de 12 caracteres)", example = "senha123")
        @NotBlank(message = "Senha é obrigatória!")
        String senha) {
}
