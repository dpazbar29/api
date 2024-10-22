package daw2a.api.controllers;

import daw2a.api.entities.Organizador;
import daw2a.api.repositorioes.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @GetMapping
    public List<Organizador> getAllOrganizadores() {
        return organizadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> getOrganizadorById(@PathVariable Long id) {
        return organizadorRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Organizador createOrganizador(@RequestBody Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizador> updateOrganizador(@PathVariable Long id, @RequestBody Organizador organizadorDetails) {
        return organizadorRepository.findById(id).map(organizador -> {
            organizador.setNombre(organizadorDetails.getNombre());
            organizador.setEmail(organizadorDetails.getEmail());
            organizador.setEventos(organizadorDetails.getEventos());
            return ResponseEntity.ok(organizadorRepository.save(organizador));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizador(@PathVariable Long id) {
        if (organizadorRepository.existsById(id)) {
            organizadorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
