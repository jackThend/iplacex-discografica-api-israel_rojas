package org.iplacex.proyectos.discografia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// importamos las anotaciones y clases necesarias para controlador rest

import java.util.List;
import java.util.Optional;

@RestController 
@CrossOrigin 
@RequestMapping("/api") 
public class ArtistaController {

    @Autowired
    private IArtistaRepository artista_repository; 

    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> handle_insert_artista_request(@RequestBody Artista nuevo_artista) { 
        Artista artista_guardado = artista_repository.save(nuevo_artista); 
        return new ResponseEntity<>(artista_guardado, HttpStatus.CREATED);
    }

    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> handle_get_artistas_request() { 
        List<Artista> artistas = artista_repository.findAll(); 
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> handle_get_artista_request(@PathVariable("id") String id_artista) { 
        // busca un artista por su id
        Optional<Artista> artista_optional = artista_repository.findById(id_artista); 
        if (artista_optional.isPresent()) {
            return new ResponseEntity<>(artista_optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> handle_update_artista_request(@PathVariable("id") String id_artista, @RequestBody Artista artista_actualizado) {
        if (!artista_repository.existsById(id_artista)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        artista_actualizado.setId(id_artista);
        Artista artista_guardado = artista_repository.save(artista_actualizado); 
        return new ResponseEntity<>(artista_guardado, HttpStatus.OK);
    }

    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> handle_delete_artista_request(@PathVariable("id") String id_artista) {
        if (!artista_repository.existsById(id_artista)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        artista_repository.deleteById(id_artista);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}