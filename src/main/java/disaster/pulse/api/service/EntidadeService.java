package disaster.pulse.api.service;

import disaster.pulse.api.dto.request.EntidadeRequestDTO;
import disaster.pulse.api.dto.response.EntidadeResponseDTO;
import disaster.pulse.api.mapper.EntidadeMapper;
import disaster.pulse.api.repository.EntidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EntidadeService {

    private final EntidadeRepository entidadeRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntidadeMapper entidadeMapper;

    public EntidadeService(EntidadeRepository entidadeRepository,
                           PasswordEncoder passwordEncoder,
                           EntidadeMapper entidadeMapper) {
        this.entidadeRepository = entidadeRepository;
        this.passwordEncoder = passwordEncoder;
        this.entidadeMapper = entidadeMapper;
    }

    public EntidadeResponseDTO save(EntidadeRequestDTO dto) {
        var entidade = entidadeMapper.toEntity(dto);
        entidade.setSenha(passwordEncoder.encode(dto.senha()));
        var saved = entidadeRepository.save(entidade);
        return entidadeMapper.toResponseDTO(saved);
    }

    public EntidadeResponseDTO update(Long id, EntidadeRequestDTO dto) {
        var entidade = entidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        entidadeMapper.updateEntityFromDto(dto, entidade);

        if (dto.senha() != null && !dto.senha().isBlank()) {
            entidade.setSenha(passwordEncoder.encode(dto.senha()));
        }

        var updated = entidadeRepository.save(entidade);
        return entidadeMapper.toResponseDTO(updated);
    }

    public EntidadeResponseDTO read(Long id) {
        var entidade = entidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        return entidadeMapper.toResponseDTO(entidade);
    }
}
