package disaster.pulse.api.model;


import disaster.pulse.api.model.enums.Risco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="DP_EVENTO", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"titulo", "data_inicio", "id_localizacao"})
})
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_evento")
    private Long idEvento;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name="id_localizacao", nullable = false)
    private Localizacao localizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipo_evento", nullable = false)
    private TipoEvento tipoEvento;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable=true)
    private String descricao;

    @Column(name="data_inicio", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private Risco risco;
}
