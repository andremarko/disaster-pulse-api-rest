package disaster.pulse.api.repository;

import disaster.pulse.api.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    Entidade findByLogin(String login);
}
