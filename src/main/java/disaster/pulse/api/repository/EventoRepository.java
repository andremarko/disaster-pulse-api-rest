package disaster.pulse.api.repository;

import disaster.pulse.api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> { }
