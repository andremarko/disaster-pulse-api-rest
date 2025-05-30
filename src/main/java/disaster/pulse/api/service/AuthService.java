package disaster.pulse.api.service;

import disaster.pulse.api.repository.CivilRepository;
import disaster.pulse.api.repository.EntidadeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final CivilRepository civilRepository;
    private final EntidadeRepository entidadeRepository;

    public AuthService(CivilRepository civilRepository, EntidadeRepository entidadeRepository) {
        this.civilRepository = civilRepository;
        this.entidadeRepository = entidadeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails usuario;
        if (username.length() == 11) {
            usuario = civilRepository.findByLogin(username);
        } else {
            usuario = entidadeRepository.findByLogin(username);
        }
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com login: " + username);
        }
        return usuario;
    }

}
