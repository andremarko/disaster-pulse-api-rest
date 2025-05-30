package disaster.pulse.api.service;

import disaster.pulse.api.dto.request.CivilRequestDTO;
import disaster.pulse.api.dto.response.CivilResponseDTO;
import disaster.pulse.api.mapper.CivilMapper;
import disaster.pulse.api.repository.CivilRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CivilService {

    private final CivilRepository civilRepository;
    private final PasswordEncoder passwordEncoder;
    private final CivilMapper civilMapper;

    public CivilService(CivilRepository civilRepository,
                        PasswordEncoder passwordEncoder,
                        CivilMapper civilMapper) {
        this.civilRepository = civilRepository;
        this.passwordEncoder = passwordEncoder;
        this.civilMapper = civilMapper;
    }

    public CivilResponseDTO save(CivilRequestDTO dto) {
        var civil = civilMapper.toEntity(dto);
        civil.setSenha(passwordEncoder.encode(dto.senha()));
        var saved = civilRepository.save(civil);
        return civilMapper.toResponseDTO(saved);
    }

    public CivilResponseDTO update(Long id, CivilRequestDTO dto) {
        var civil = civilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Civil não encontrado"));

        civilMapper.updateEntityFromDto(dto, civil);

        if (dto.senha() != null && !dto.senha().isBlank()) {
            civil.setSenha(passwordEncoder.encode(dto.senha()));
        }

        var updated = civilRepository.save(civil);
        return civilMapper.toResponseDTO(updated);
    }

    public CivilResponseDTO read(Long id) {
        var civil = civilRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Civil não encontrado"));

        return civilMapper.toResponseDTO(civil);
    }
}
