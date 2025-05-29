package disaster.pulse.api.service;

import org.springframework.stereotype.Service;
import disaster.pulse.api.dto.request.CivilRequestDTO;
import disaster.pulse.api.dto.response.CivilResponseDTO;
import disaster.pulse.api.model.Civil;
import disaster.pulse.api.repository.CivilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CivilService {
    @Autowired
    private CivilRepository civilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CivilResponseDTO save(CivilRequestDTO dto) {
        var civil = new Civil(
                dto.nomeCompleto(),
                dto.email(),
                dto.cpf(),
                dto.telefone(),
                passwordEncoder.encode(dto.senha())
        );
        var saved = civilRepository.save(civil);
        return new CivilResponseDTO(
                saved.getId(),
                saved.getNomeCompleto(),
                saved.getEmail(),
                saved.getCpf(),
                saved.getTelefone()
        );
    }

    public CivilResponseDTO update(Long id, CivilRequestDTO dto) {
        var civil = civilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Civil não encontrado"));

        civil.setNomeCompleto(dto.nomeCompleto());
        civil.setEmail(dto.email());
        civil.setCpf(dto.cpf());
        civil.setTelefone(dto.telefone());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            civil.setSenha(passwordEncoder.encode(dto.senha()));
        }

        var updated = civilRepository.save(civil);
        return new CivilResponseDTO(
                updated.getId(),
                updated.getNomeCompleto(),
                updated.getEmail(),
                updated.getCpf(),
                updated.getTelefone()
        );
    }

    public CivilResponseDTO read(Long id) {
        var civil = civilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Civil não encontrado"));

        return new CivilResponseDTO(
                civil.getId(),
                civil.getNomeCompleto(),
                civil.getEmail(),
                civil.getCpf(),
                civil.getTelefone()
        );
    }
}
