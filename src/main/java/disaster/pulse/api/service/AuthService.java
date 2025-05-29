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
        if (username.length() == 11) {
            return civilRepository.findByLogin(username);
        }
        return entidadeRepository.findByLogin(username);
    }

}
