package disaster.pulse.api.controller;

import disaster.pulse.api.dto.request.CivilRequestDTO;
import disaster.pulse.api.dto.response.CivilResponseDTO;
import disaster.pulse.api.service.CivilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// cadastro
@RestController
@RequestMapping("/cadastro/civil")
public class CivilController {

    @Autowired
    private CivilService civilService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CivilResponseDTO create(@Valid @RequestBody CivilRequestDTO dto) {
        return civilService.save(dto);
    }

    @PutMapping("/{id}")
    public CivilResponseDTO update(@PathVariable Long id, @Valid @RequestBody CivilRequestDTO dto) {
        return civilService.update(id, dto);
    }

    @GetMapping("/{id}")
    public CivilResponseDTO getById(@PathVariable Long id) {
        return civilService.read(id);
    }
}
