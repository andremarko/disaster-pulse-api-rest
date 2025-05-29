package disaster.pulse.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="DP_LOCALIZACAO")
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_localizacao")
    private Long idLocalizacao;

    @Column(nullable = false, length = 50)
    private String latitude;

    @Column(nullable = false, length = 50)
    private String longitude;

    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();
}
