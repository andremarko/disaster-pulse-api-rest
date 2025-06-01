package disaster.pulse.api.service;

import disaster.pulse.api.dto.request.PedidoSosRequestDTO;
import disaster.pulse.api.dto.response.PedidoSosResponseDTO;
import disaster.pulse.api.mapper.PedidoSosMapper;
import disaster.pulse.api.model.Civil;
import disaster.pulse.api.model.Evento;
import disaster.pulse.api.model.PedidoSOS;
import disaster.pulse.api.repository.CivilRepository;
import disaster.pulse.api.repository.EventoRepository;
import disaster.pulse.api.repository.PedidoSosRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoSosService {

    private final PedidoSosRepository pedidoSosRepository;
    private final EventoRepository eventoRepository;
    private final CivilRepository civilRepository;
    private final PedidoSosMapper pedidoSosMapper;
    private final JwtService jwtService;
    private final EntityManager entityManager;

    public PedidoSosService(PedidoSosRepository pedidoSosRepository,
                            EventoRepository eventoRepository,
                            CivilRepository civilRepository,
                            PedidoSosMapper pedidoSosMapper,
                            JwtService jwtService,
                            EntityManager entityManager) {
        this.pedidoSosRepository = pedidoSosRepository;
        this.eventoRepository = eventoRepository;
        this.civilRepository = civilRepository;
        this.pedidoSosMapper = pedidoSosMapper;
        this.jwtService = jwtService;
        this.entityManager = entityManager;
    }

    @Transactional
    public PedidoSosResponseDTO save(PedidoSosRequestDTO dto) {
        Evento evento = eventoRepository.findById(dto.idEvento())
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

        Long idCivilAutenticado = jwtService.getAuthenticatedUserId();
        Civil civil = civilRepository.findById(idCivilAutenticado)
                .orElseThrow(() -> new EntityNotFoundException("Civil não encontrado"));

        PedidoSOS pedidoSos = pedidoSosMapper.toEntity(dto);
        pedidoSos.setEvento(evento);
        pedidoSos.setCivil(civil);
        PedidoSOS saved = pedidoSosRepository.save(pedidoSos);
        entityManager.refresh(saved);
        return pedidoSosMapper.toResponseDTO(saved);
    }

    @CacheEvict(value = "pedidoSosCache", allEntries = true)
    public PedidoSosResponseDTO update(Long id, PedidoSosRequestDTO dto) {
        PedidoSOS pedidoSos = pedidoSosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido SOS não encontrado"));

        Long idCivilAutenticado = jwtService.getAuthenticatedUserId();

        if (!pedidoSos.getCivil().getId().equals(idCivilAutenticado)) {
            throw new SecurityException("Usuário não autorizado para editar este pedido SOS");
        }

        pedidoSosMapper.updateEntityFromDto(dto, pedidoSos);

        if (!pedidoSos.getEvento().getIdEvento().equals(dto.idEvento())) {
            Evento novoEvento = eventoRepository.findById(dto.idEvento())
                    .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
            pedidoSos.setEvento(novoEvento);
        }

        PedidoSOS updated = pedidoSosRepository.save(pedidoSos);
        return pedidoSosMapper.toResponseDTO(updated);
    }

    @Cacheable("pedidoSosCache")
    public List<PedidoSosResponseDTO> getAll() {
        return pedidoSosRepository.findAll()
                .stream()
                .map(pedidoSosMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PedidoSosResponseDTO getById(Long id) {
        PedidoSOS pedidoSos = pedidoSosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido SOS não encontrado"));
        return pedidoSosMapper.toResponseDTO(pedidoSos);
    }

    @CacheEvict(value = "pedidoSosCache", allEntries = true)
    public void delete(Long id) {
        PedidoSOS pedidoSos = pedidoSosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido SOS não encontrado"));

        Long idCivilAutenticado = jwtService.getAuthenticatedUserId();

        if (!pedidoSos.getCivil().getId().equals(idCivilAutenticado)) {
            throw new SecurityException("Usuário não autorizado para deletar este pedido SOS");
        }

        pedidoSosRepository.deleteById(id);
    }
}
