package disaster.pulse.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Data
@Table(name="DP_CIVIL")
@NoArgsConstructor
public class Civil extends Usuario {

    private static final String ROLE = "ROLE_CIVIL";

    @Column(name="nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(nullable = true)
    private String telefone;

    @OneToMany(mappedBy = "civil", cascade = CascadeType.ALL)
    private List<PedidoSOS> sos = new ArrayList<>();

    public Civil(Long id) {
        this.idUsuario = id;
    }

    public Civil(String nomeCompleto, String email, String cpf, String telefone, String senha) {
        super(cpf, senha);
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Civil civil)) return false;
        return Objects.equals(cpf, civil.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Civil{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
