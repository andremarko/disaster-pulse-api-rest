package disaster.pulse.api.service;

import disaster.pulse.api.dto.request.EntidadeRequestDTO;
import disaster.pulse.api.dto.response.EntidadeResponseDTO;
import disaster.pulse.api.model.Entidade;
import disaster.pulse.api.repository.EntidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EntidadeService {

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EntidadeResponseDTO save(EntidadeRequestDTO dto) {
        var entidade = new Entidade(
                dto.nome(),
                dto.email(),
                dto.cnpj(),
                dto.telefone(),
                passwordEncoder.encode(dto.senha())
        );
        var saved = entidadeRepository.save(entidade);
        return new EntidadeResponseDTO(
                saved.getId(),
                saved.getNome(),
                saved.getEmail(),
                saved.getCnpj(),
                saved.getTelefone()
        );
    }

    public EntidadeResponseDTO update(Long id, EntidadeRequestDTO dto) {
        var entidade = entidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        entidade.setNome(dto.nome());
        entidade.setEmail(dto.email());
        entidade.setCnpj(dto.cnpj());
        entidade.setTelefone(dto.telefone());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            entidade.setSenha(passwordEncoder.encode(dto.senha()));
        }

        var updated = entidadeRepository.save(entidade);
        return new EntidadeResponseDTO(
                updated.getId(),
                updated.getNome(),
                updated.getEmail(),
                updated.getCnpj(),
                updated.getTelefone()
        );
    }

    public EntidadeResponseDTO read(Long id) {
        var entidade = entidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));

        return new EntidadeResponseDTO(
                entidade.getId(),
                entidade.getNome(),
                entidade.getEmail(),
                entidade.getCnpj(),
                entidade.getTelefone()
        );
    }
}
