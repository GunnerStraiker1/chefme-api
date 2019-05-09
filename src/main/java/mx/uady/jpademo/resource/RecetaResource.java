package mx.uady.jpademo.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.jpademo.model.Receta;
import mx.uady.jpademo.request.RecetaRequest;
import mx.uady.jpademo.service.RecetaService;

@RestController
public class RecetaResource {
    final Logger LOG = LoggerFactory.getLogger(RecetaResource.class);

    @Autowired
    private RecetaService recetaService;

    /**
     * @return the recetaService
     */
    public RecetaService getRecetaService() {
        return recetaService;
    }

    @GetMapping("/recetas")
    public List<Receta> getRecetas() {
        LOG.info("Llamada a listar recetas- Resource");
        List<Receta> recetas = new LinkedList<>();
        recetas = getRecetaService().getRecetas();
        return recetas;
    }

    @GetMapping("/recetas/{ingrediente}")
    public List<Receta> getRecetasByIngrediente(@PathVariable String ingrediente) {
        LOG.info("Llamada a listar recetas por ingrediente: {} - Resource", ingrediente);
        List<Receta> recetas = new LinkedList<>();
        recetas = getRecetaService().getRecetasByIngrediente(ingrediente);
        return recetas;
    }

    @GetMapping("/recetas/receta/{id}")
    public Receta getRecetaById(@PathVariable Integer id) {
        LOG.info("Llamada a listar recetas por id: {} - Resource", id);

        Receta recetas = getRecetaService().getRecetaById(id);
        return recetas;
    }

    @PostMapping("/recetas")
    public ResponseEntity<Receta> saveReceta(@RequestBody @Valid RecetaRequest request) throws URISyntaxException {
        LOG.info("Llamada a agregar Receta");
        Receta receta = getRecetaService().saveReceta(request);
        URI location = new URI("/recetas/" + receta.getTitle());
        ResponseEntity<Receta> response = ResponseEntity.created(location).body(receta);
        return response;
    }

    @PutMapping("/recetas")
    public ResponseEntity<Receta> editReceta(@RequestBody @Valid RecetaRequest request) {
        LOG.info("Llamada a editar Receta, request: {}", request);
        Receta receta = getRecetaService().editReceta(request);
        return ResponseEntity.ok().body(receta);
    }

    @DeleteMapping("/recetas/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        LOG.info("Llamada a eliminar Receta {}", id);
        Receta receta = getRecetaService().getRecetaId(id);
        getRecetaService().deleteReceta(receta);

        return ResponseEntity.ok().build();
    }
}