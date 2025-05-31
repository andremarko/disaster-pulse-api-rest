package disaster.pulse.api.repository;

import disaster.pulse.api.model.Civil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CivilRepository extends JpaRepository<Civil, Long> {
    Civil findByLogin(String login);
}
