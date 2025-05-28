package disaster.pulse.api.model;

import disaster.pulse.api.model.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="DP_PEDIDO_SOS")
public class PedidoSOS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sos")
    private Long idSos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_evento", nullable = false)
    private Evento evento;

    @CreationTimestamp
    @Column(name="data_hora", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

    @Column(nullable=false)
    private String comentario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Status status;
}
