package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.EventoRequestDTO;
import disaster.pulse.api.dto.response.EventoResponseDTO;
import disaster.pulse.api.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;  // Import para PreAuthorize
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PreAuthorize("hasRole('ENTIDADE')")
    @PostMapping
    public ResponseEntity<EventoResponseDTO> create(@Valid @RequestBody EventoRequestDTO dto) {
        EventoResponseDTO created = eventoService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ENTIDADE')")
    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EventoRequestDTO dto) {
        EventoResponseDTO updated = eventoService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> getAll() {
        List<EventoResponseDTO> list = eventoService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> getById(@PathVariable Long id) {
        EventoResponseDTO dto = eventoService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ENTIDADE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
