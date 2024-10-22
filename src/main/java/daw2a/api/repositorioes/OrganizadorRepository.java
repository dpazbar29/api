package daw2a.api.repositorioes;

import daw2a.api.entities.Organizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
    List<Organizador> findByNombre(String nombre);
}
