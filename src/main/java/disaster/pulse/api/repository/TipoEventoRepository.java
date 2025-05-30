package disaster.pulse.api.repository;

import disaster.pulse.api.model.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEventoRepository extends JpaRepository<TipoEvento, Long> {
}
