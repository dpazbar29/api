package daw2a.api.repositorioes;

import daw2a.api.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByTitulo(String titulo);
}
