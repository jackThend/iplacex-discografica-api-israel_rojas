package org.iplacex.proyectos.discografia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository disco_repository;

    @Autowired
    private IArtistaRepository artista_repository; 

    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> handle_post_disco_request(@RequestBody Disco nuevo_disco) { 
        if (!artista_repository.existsById(nuevo_disco.getIdArtista())) { 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Disco disco_guardado = disco_repository.save(nuevo_disco); 
        return new ResponseEntity<>(disco_guardado, HttpStatus.CREATED);
    }

    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> handle_get_discos_request() { 
        List<Disco> discos = disco_repository.findAll(); 
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> handle_get_disco_request(@PathVariable("id") String id_disco) { 
        Optional<Disco> disco_optional = disco_repository.findById(id_disco); 
        if (disco_optional.isPresent()) {
            return new ResponseEntity<>(disco_optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/artista/{id_artista}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> handle_get_discos_by_artista_request(@PathVariable("id_artista") String id_artista) { 
        // verifica si el artista existe antes de buscar sus discos
        if (!artista_repository.existsById(id_artista)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Disco> discos = disco_repository.findDiscosByIdArtista(id_artista); 
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @PutMapping(value = "/disco/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> handle_update_disco_request(@PathVariable("id") String id_disco, @RequestBody Disco disco_actualizado) { 
        if (!disco_repository.existsById(id_disco)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!artista_repository.existsById(disco_actualizado.getIdArtista())) { 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
        disco_actualizado.setId(id_disco);
        Disco disco_guardado = disco_repository.save(disco_actualizado); 
        return new ResponseEntity<>(disco_guardado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> handle_delete_disco_request(@PathVariable("id") String id_disco) {
        if (!disco_repository.existsById(id_disco)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        disco_repository.deleteById(id_disco);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}