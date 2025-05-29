package disaster.pulse.api.controller;


import disaster.pulse.api.dto.request.EntidadeRequestDTO;
import disaster.pulse.api.dto.response.EntidadeResponseDTO;
import disaster.pulse.api.service.EntidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro/entidade")
public class EntidadeController {
    @Autowired
    private EntidadeService entidadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntidadeResponseDTO create(@Valid @RequestBody EntidadeRequestDTO dto) {
        return entidadeService.save(dto);
    }

    @PutMapping("/{id}")
    public EntidadeResponseDTO update(@PathVariable Long id, @Valid @RequestBody EntidadeRequestDTO dto) {
        return entidadeService.update(id, dto);
    }

    @GetMapping("/{id}")
    public EntidadeResponseDTO getById(@PathVariable Long id) {
        return entidadeService.read(id);
    }
}
