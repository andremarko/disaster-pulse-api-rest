package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.CivilRequestDTO;
import disaster.pulse.api.dto.response.CivilResponseDTO;
import disaster.pulse.api.service.CivilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// cadastro
@RestController
@RequestMapping("/api/cadastro/civil")
@Tag(name="Civil", description="API para cadastro de usuário tipo civil")
public class CivilController {

    @Autowired
    private CivilService civilService;

    @Operation(summary="Cadastrar usuário civil")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CivilResponseDTO create(@Valid @RequestBody CivilRequestDTO dto) {
        return civilService.save(dto);
    }

    @Operation(summary = "Atualizar cadastro civil por ID")
    @PutMapping("/{id}")
    public CivilResponseDTO update(@PathVariable Long id, @Valid @RequestBody CivilRequestDTO dto) {
        return civilService.update(id, dto);
    }

    @Operation(summary = "Buscar cadastro civil por ID")
    @GetMapping("/{id}")
    public CivilResponseDTO getById(@PathVariable Long id) {
        return civilService.read(id);
    }
}
