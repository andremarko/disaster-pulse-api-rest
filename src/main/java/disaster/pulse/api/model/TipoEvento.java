package disaster.pulse.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="DP_TIPO_EVENTO")
public class TipoEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_evento")
    private Long idTipoEvento;

    @Column(unique = true, nullable = false, length = 30)
    private String tipo;

    public TipoEvento(String tipo) {
        this.tipo = tipo;
    }
}
