package disaster.pulse.api.service;

import disaster.pulse.api.dto.request.EventoRequestDTO;
import disaster.pulse.api.dto.response.EventoResponseDTO;
import disaster.pulse.api.mapper.EventoMapper;
import disaster.pulse.api.model.Entidade;
import disaster.pulse.api.model.Evento;
import disaster.pulse.api.model.TipoEvento;
import disaster.pulse.api.repository.EntidadeRepository;
import disaster.pulse.api.repository.EventoRepository;
import disaster.pulse.api.repository.TipoEventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    private final TipoEventoRepository tipoEventoRepository;
    private final EntidadeRepository entidadeRepository;
    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;
    private final JwtService jwtService;

    public EventoService(TipoEventoRepository tipoEventoRepository,
                         EntidadeRepository entidadeRepository,
                         EventoRepository eventoRepository,
                         EventoMapper eventoMapper,
                         JwtService jwtService) {
        this.tipoEventoRepository = tipoEventoRepository;
        this.entidadeRepository = entidadeRepository;
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.jwtService = jwtService;
    }

    public EventoResponseDTO save(EventoRequestDTO dto) {
        TipoEvento tipoEvento = tipoEventoRepository.findById(dto.idTipoEvento())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de evento não encontrado"));

        Long idEntidade = jwtService.getAuthenticatedUserId();
        Entidade entidade = entidadeRepository.findById(idEntidade)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        Evento evento = eventoMapper.toEntity(dto);
        evento.setTipoEvento(tipoEvento);
        evento.setEntidade(entidade);
        Evento savedEvento = eventoRepository.save(evento);
        return eventoMapper.toResponseDTO(savedEvento);
    }

    public EventoResponseDTO update(Long id, EventoRequestDTO dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

        Long idEntidadeAutenticada = jwtService.getAuthenticatedUserId();

        if (!evento.getEntidade().getId().equals(idEntidadeAutenticada)) {
            throw new SecurityException("Usuário não autorizado para editar este evento");
        }

        eventoMapper.updateEntityFromDto(dto, evento);

        if (!evento.getTipoEvento().getIdTipoEvento().equals(dto.idTipoEvento())) {
            TipoEvento novoTipoEvento = tipoEventoRepository.findById(dto.idTipoEvento())
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de evento não encontrado"));
            evento.setTipoEvento(novoTipoEvento);
        }


        Evento updatedEvento = eventoRepository.save(evento);
        return eventoMapper.toResponseDTO(updatedEvento);
    }

    public Page<EventoResponseDTO> getAll(Long entidadeId, Pageable pageable) {
        Page<Evento> eventos = eventoRepository.findByEntidadeId(entidadeId, pageable);
        return eventos.map(eventoMapper::toResponseDTO);
    }

    public EventoResponseDTO getById(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
        return eventoMapper.toResponseDTO(evento);
    }

    public void delete(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado");
        }
        eventoRepository.deleteById(id);
    }
}
