package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.EntidadeRequestDTO;
import disaster.pulse.api.dto.response.EntidadeResponseDTO;
import disaster.pulse.api.service.EntidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastro/entidade")
@Tag(name="Entidade", description="API para cadastro de usuário tipo entidade (ONGs, Orgãos Públicos, etc.)")
public class EntidadeController {
    @Autowired
    private EntidadeService entidadeService;

    @Operation(summary="Cadastrar usuário entidade")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntidadeResponseDTO create(@Valid @RequestBody EntidadeRequestDTO dto) {
        return entidadeService.save(dto);
    }

    @Operation(summary = "Atualizar cadastro entidade por ID")
    @PutMapping("/{id}")
    public EntidadeResponseDTO update(@PathVariable Long id, @Valid @RequestBody EntidadeRequestDTO dto) {
        return entidadeService.update(id, dto);
    }

    @Operation(summary = "Buscar cadastro entidade por ID")
    @GetMapping("/{id}")
    public EntidadeResponseDTO getById(@PathVariable Long id) {
        return entidadeService.read(id);
    }
}
