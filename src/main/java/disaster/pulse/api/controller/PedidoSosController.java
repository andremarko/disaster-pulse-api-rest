package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.PedidoSosRequestDTO;
import disaster.pulse.api.dto.response.PedidoSosResponseDTO;
import disaster.pulse.api.service.PedidoSosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sos")
@Tag(name = "Pedidos SOS", description = "Operações relacionadas a pedidos de socorro (comentários) em um evento. Somente usuários do tipo Civil conseguem cadastrar, alterar ou deletar um pedido sos.")
public class PedidoSosController {

    private final PedidoSosService pedidoSosService;

    public PedidoSosController(PedidoSosService pedidoSosService) {
        this.pedidoSosService = pedidoSosService;
    }

    @PreAuthorize("hasRole('CIVIL')")
    @Operation(summary = "Cria um pedido SOS. Certifique-se se há algum evento cadastrado.")
    @PostMapping
    public ResponseEntity<PedidoSosResponseDTO> create(@Valid @RequestBody PedidoSosRequestDTO dto) {
        PedidoSosResponseDTO created = pedidoSosService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PreAuthorize("hasRole('CIVIL')")
    @Operation(summary = "Atualiza um pedido SOS")
    @PutMapping("/{id}")
    public ResponseEntity<PedidoSosResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PedidoSosRequestDTO dto) {
        PedidoSosResponseDTO updated = pedidoSosService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Lista todos os pedidos SOS")
    @GetMapping
    public ResponseEntity<List<PedidoSosResponseDTO>> getAll() {
        return ResponseEntity.ok(pedidoSosService.getAll());
    }

    @Operation(summary = "Busca um pedido SOS pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoSosResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoSosService.getById(id));
    }

    @PreAuthorize("hasRole('CIVIL')")
    @Operation(summary = "Deleta um pedido SOS")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoSosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

