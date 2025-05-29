package disaster.pulse.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name="DP_ALERTA")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_alerta")
    private Long idAlerta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_evento", nullable = false)
    private Evento evento;

    @Column(nullable = false)
    private String mensagem;

    @Column(name="data_hora", nullable=false)
    private LocalDateTime dataHora;
}
