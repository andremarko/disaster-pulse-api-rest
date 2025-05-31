package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.AlertaRequestDTO;
import disaster.pulse.api.dto.response.AlertaResponseDTO;
import disaster.pulse.api.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@Tag(name = "Alertas", description = "API para cadastro de Alertas de um Evento.")
public class AlertaController {

    private final AlertaService alertaService;

    public AlertaController(AlertaService alertaService) {
        this.alertaService = alertaService;
    }

    @Operation(summary = "Cria um novo alerta")
    @PostMapping
    public ResponseEntity<AlertaResponseDTO> create(@Valid @RequestBody AlertaRequestDTO dto) {
        AlertaResponseDTO created = alertaService.save(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um alerta existente pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AlertaRequestDTO dto) {
        AlertaResponseDTO updated = alertaService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Lista todos os alertas")
    @GetMapping
    public ResponseEntity<List<AlertaResponseDTO>> getAll() {
        List<AlertaResponseDTO> list = alertaService.getAll();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Busca alerta por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponseDTO> getById(@PathVariable Long id) {
        AlertaResponseDTO dto = alertaService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Exclui um alerta pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alertaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
