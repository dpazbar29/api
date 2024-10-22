package daw2a.api.repositorioes;

import daw2a.api.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    List<Participante> findByEmail(String email);
}
