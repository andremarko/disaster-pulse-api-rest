package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.PedidoSosRequestDTO;
import disaster.pulse.api.dto.response.PedidoSosResponseDTO;
import disaster.pulse.api.service.PedidoSosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sos")
@Tag(name = "Pedidos SOS", description = "Operações relacionadas a pedidos de socorro")
public class PedidoSosController {

    private final PedidoSosService pedidoSosService;

    public PedidoSosController(PedidoSosService pedidoSosService) {
        this.pedidoSosService = pedidoSosService;
    }

    @PreAuthorize("hasRole('CIVIL')")
    @Operation(
            summary = "Cria um pedido SOS",
            description = "Para cadastrar um pedido SOS em um evento, é necessário estar autenticado como Usuário com papel 'CIVIL'. Certifique-se de que o evento esteja previamente cadastrado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Pedido criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PedidoSosResponseDTO.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de cadastro",
                                    summary = "JSON para criação de pedido SOS",
                                    value = """
                        {
                          "idEvento": 1,
                          "dataHora": "2025-05-30T07:45:00",
                          "comentario": "Inundações severas após chuvas intensas. Famílias ilhadas no bairro Jardim das Águas.",
                          "status": "AGUARDANDO"
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso proibido")
    })
    @PostMapping
    public ResponseEntity<PedidoSosResponseDTO> create(
            @Parameter(description = "Dados do pedido SOS", required = true)
            @Valid @RequestBody PedidoSosRequestDTO dto) {
        PedidoSosResponseDTO created = pedidoSosService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CIVIL')")
    @Operation(summary = "Atualiza um pedido SOS", description = "Atualiza dados do pedido SOS existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "403", description = "Desconhecido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PedidoSosResponseDTO> update(
            @Parameter(description = "ID do pedido SOS", required = true) @PathVariable Long id,
            @Parameter(description = "Dados atualizados do pedido SOS", required = true) @Valid @RequestBody PedidoSosRequestDTO dto) {
        PedidoSosResponseDTO updated = pedidoSosService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Lista todos os pedidos SOS", description = "Retorna uma lista com todos os pedidos de socorro cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<PedidoSosResponseDTO>> getAll() {
        List<PedidoSosResponseDTO> list = pedidoSosService.getAll();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Busca um pedido SOS pelo ID", description = "Retorna os dados de um pedido de socorro específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "403", description = "Desconhecido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoSosResponseDTO> getById(
            @Parameter(description = "ID do pedido SOS", required = true) @PathVariable Long id) {
        PedidoSosResponseDTO dto = pedidoSosService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('CIVIL')")
    @Operation(summary = "Deleta um pedido SOS", description = "Remove um pedido de socorro pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado"),
            @ApiResponse(responseCode = "403", description = "Desconhecido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do pedido SOS", required = true) @PathVariable Long id) {
        pedidoSosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
