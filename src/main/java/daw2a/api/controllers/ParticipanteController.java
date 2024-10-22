package daw2a.api.controllers;

import daw2a.api.entities.Participante;
import daw2a.api.repositorioes.OrganizadorRepository;
import daw2a.api.repositorioes.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @GetMapping
    public List<Participante> getAllParticipantes() {
        return participanteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> getParticipanteById(@PathVariable Long id) {
        return participanteRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Participante createParticipante(@RequestBody Participante participante) {
        return participanteRepository.save(participante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> updateParticipante(@PathVariable Long id, @RequestBody Participante participanteDetails) {
        return participanteRepository.findById(id).map(participante -> {
            participante.setNombre(participanteDetails.getNombre());
            participante.setEmail(participanteDetails.getEmail());
            participante.setEvento(participanteDetails.getEvento());

            Participante updatedParticipante = participanteRepository.save(participante);
            return ResponseEntity.ok(updatedParticipante);
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        if (participanteRepository.existsById(id)) {
            participanteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
