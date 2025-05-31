package disaster.pulse.api.repository;

import disaster.pulse.api.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    Page<Evento> findByEntidadeId(Long entidadeId, Pageable pageable);
}
