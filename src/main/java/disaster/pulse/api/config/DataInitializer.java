package disaster.pulse.api.config;
import disaster.pulse.api.model.TipoEvento;
import disaster.pulse.api.repository.TipoEventoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(TipoEventoRepository tipoEventoRepository) {
        return args -> {
            if (tipoEventoRepository.count() == 0) {
                tipoEventoRepository.save(new TipoEvento("Climático"));
                tipoEventoRepository.save(new TipoEvento("Geográfico"));
                tipoEventoRepository.save(new TipoEvento("Biológico"));
                tipoEventoRepository.save(new TipoEvento("Ambiental"));
                tipoEventoRepository.save(new TipoEvento("Epidêmico"));
            }
        };
    }
}