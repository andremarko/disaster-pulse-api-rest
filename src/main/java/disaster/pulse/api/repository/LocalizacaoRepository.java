package disaster.pulse.api.repository;

import disaster.pulse.api.model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Integer> {
}
