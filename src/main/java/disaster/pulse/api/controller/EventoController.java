package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.EventoRequestDTO;
import disaster.pulse.api.dto.response.EventoResponseDTO;
import disaster.pulse.api.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Operações relacionadas a eventos. Somente usuários do tipo Entidade conseguem cadastrar, alterar ou deletar um evento.")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PreAuthorize("hasRole('ENTIDADE')")
    @Operation(summary = "Cria um novo evento")
    @PostMapping
    public ResponseEntity<EventoResponseDTO> create(@Valid @RequestBody EventoRequestDTO dto) {
        EventoResponseDTO created = eventoService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ENTIDADE')")
    @Operation(summary = "Atualiza um evento existente")
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO dto) {
        EventoResponseDTO updated = eventoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Lista paginada de eventos")
    @GetMapping
    public ResponseEntity<Page<EventoResponseDTO>> getAll(
            @RequestParam(required = false) Long entidadeId,
            @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(eventoService.getAll(entidadeId, pageable));
    }

    @Operation(summary = "Busca um evento pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> getById(@PathVariable Long id) {
        EventoResponseDTO dto = eventoService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ENTIDADE')")
    @Operation(summary = "Deleta um evento pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
