package disaster.pulse.api.controller;

import disaster.pulse.api.dto.response.TipoEventoResponseDTO;
import disaster.pulse.api.mapper.TipoEventoMapper;
import disaster.pulse.api.repository.TipoEventoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-evento")
public class TipoEventoController {

    private final TipoEventoRepository tipoEventoRepository;
    private final TipoEventoMapper tipoEventoMapper;

    public TipoEventoController(TipoEventoRepository tipoEventoRepository, TipoEventoMapper tipoEventoMapper) {
        this.tipoEventoRepository = tipoEventoRepository;
        this.tipoEventoMapper = tipoEventoMapper;
    }

    @GetMapping
    public List<TipoEventoResponseDTO> listarTodos() {
        return tipoEventoRepository.findAll()
                .stream()
                .map(tipoEventoMapper::toResponseDTO)
                .toList();
    }
}
