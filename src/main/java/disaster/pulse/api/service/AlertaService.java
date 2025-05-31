package disaster.pulse.api.service;

import disaster.pulse.api.dto.request.AlertaRequestDTO;
import disaster.pulse.api.dto.response.AlertaResponseDTO;
import disaster.pulse.api.mapper.AlertaMapper;
import disaster.pulse.api.model.Alerta;
import disaster.pulse.api.model.Evento;
import disaster.pulse.api.repository.AlertaRepository;
import disaster.pulse.api.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final EventoRepository eventoRepository;
    private final AlertaMapper alertaMapper;

    public AlertaService(AlertaRepository alertaRepository,
                         EventoRepository eventoRepository,
                         AlertaMapper alertaMapper) {
        this.alertaRepository = alertaRepository;
        this.eventoRepository = eventoRepository;
        this.alertaMapper = alertaMapper;
    }

    public AlertaResponseDTO save(AlertaRequestDTO dto) {
        Evento evento = eventoRepository.findById(dto.idEvento())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

        Alerta alerta = alertaMapper.toEntity(dto);
        alerta.setEvento(evento);

        Alerta saved = alertaRepository.save(alerta);
        return alertaMapper.toResponseDTO(saved);
    }

    public AlertaResponseDTO update(Long id, AlertaRequestDTO dto) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));

        alertaMapper.updateEntityFromDto(dto, alerta);

        if (!alerta.getEvento().getIdEvento().equals(dto.idEvento())) {
            Evento novoEvento = eventoRepository.findById(dto.idEvento())
                    .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
            alerta.setEvento(novoEvento);
        }

        Alerta updated = alertaRepository.save(alerta);
        return alertaMapper.toResponseDTO(updated);
    }

    public List<AlertaResponseDTO> getAll() {
        return alertaRepository.findAll()
                .stream()
                .map(alertaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AlertaResponseDTO getById(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));
        return alertaMapper.toResponseDTO(alerta);
    }

    public void delete(Long id) {
        if (!alertaRepository.existsById(id)) {
            throw new EntityNotFoundException("Alerta não encontrado");
        }
        alertaRepository.deleteById(id);
    }
}


