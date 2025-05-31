package disaster.pulse.api.model;

import disaster.pulse.api.model.enums.Risco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="DP_EVENTO", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "data_inicio"})
})
public class Evento extends RepresentationModel<Evento> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_evento")
    private Long idEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidade", nullable = false)
    private Entidade entidade;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable=true)
    private String descricao;

    @Column(name="data_inicio", nullable=false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Risco risco;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<PedidoSOS> sos = new ArrayList<>();
}
