package disaster.pulse.api.controller;

import disaster.pulse.api.dto.JwtDto;
import disaster.pulse.api.dto.LoginDto;
import disaster.pulse.api.model.Usuario;
import disaster.pulse.api.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login", description = "Endpoint de login. Retorna token JWT de validação. Necessário login antes de realizar operações POST (PedidoSOS para Civil e Evento para Entidade)")
public class AuthController {
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager manager, JwtService jwtService) {
        this.manager = manager;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Realiza autenticação e gera token JWT")
    @PostMapping
    public ResponseEntity<JwtDto> efetuarLogin(@RequestBody @Valid LoginDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);
        var jwt = jwtService.gerarJwt((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtDto(jwt));
    }

}
