package disaster.pulse.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Data
@Table(name = "DP_ENTIDADE")
@NoArgsConstructor
public class Entidade extends Usuario {

    private static final String ROLE = "ROLE_ENTIDADE";

    @Column(unique = true, nullable = false)
    private String nomeFantasia;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = true)
    private String telefone;

    @Column(unique = true)
    private String cnpj;

    @OneToMany(mappedBy = "entidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos = new ArrayList<>();

    public Entidade(Long id) {
        this.idUsuario = id;
    }

    public Entidade(String nomeFantasia, String email, String telefone, String cnpj, String senha) {
        super(cnpj, senha);
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.cnpj = cnpj;
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
        if (!(o instanceof Entidade entidade)) return false;
        return Objects.equals(cnpj, entidade.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return "Entidade{" +
                "nomeFantasia='" + nomeFantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
