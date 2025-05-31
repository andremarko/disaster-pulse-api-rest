package disaster.pulse.api.controller;

import disaster.pulse.api.dto.response.TipoEventoResponseDTO;
import disaster.pulse.api.mapper.TipoEventoMapper;
import disaster.pulse.api.repository.TipoEventoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-evento")
@Tag(name = "Tipo Evento", description = "Endpoint dos tipos de evento (Climático, Geográfico, etc.)")
public class TipoEventoController {

    private final TipoEventoRepository tipoEventoRepository;
    private final TipoEventoMapper tipoEventoMapper;

    public TipoEventoController(TipoEventoRepository tipoEventoRepository, TipoEventoMapper tipoEventoMapper) {
        this.tipoEventoRepository = tipoEventoRepository;
        this.tipoEventoMapper = tipoEventoMapper;
    }
    @Operation(summary = "Lista todos os tipos de evento")
    @GetMapping
    public List<TipoEventoResponseDTO> listarTodos() {
        return tipoEventoRepository.findAll()
                .stream()
                .map(tipoEventoMapper::toResponseDTO)
                .toList();
    }
}
