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

import mx.uady.jpademo.model.Ingrediente;
import mx.uady.jpademo.request.IngredienteRequest;
import mx.uady.jpademo.service.IngredienteService;

@RestController
public class IngredienteResource {
    final Logger LOG = LoggerFactory.getLogger(IngredienteResource.class);

    @Autowired
    private IngredienteService ingredienteService;

    /**
     * @return the ingredienteService
     */
    public IngredienteService getIngredienteService() {
        return ingredienteService;
    }

    @GetMapping("/ingredientes")
    public List<Ingrediente> getIngredientes() {
        LOG.info("Llamada a listar ingredientes- Resource");
        List<Ingrediente> ingredientes = new LinkedList<>();
        ingredientes = getIngredienteService().getIngredientes();
        return ingredientes;
    }

    @GetMapping("/ingredientes/{nombre}")
    public ResponseEntity<Ingrediente> getIngrediente(@PathVariable String nombre) {
        Ingrediente ingrediente = getIngredienteService().getIngredienteNombre(nombre);
        return ResponseEntity.ok().body(ingrediente);
    }

    @PostMapping("/ingredientes")
    public ResponseEntity<Ingrediente> saveIngrediente(@RequestBody @Valid IngredienteRequest request)
            throws URISyntaxException {
        LOG.info("Llamada a agregar Ingrediente");
        Ingrediente ingrediente = getIngredienteService().saveIngrediente(request);
        URI location = new URI("/ingredientes/" + ingrediente.getName());
        ResponseEntity<Ingrediente> response = ResponseEntity.created(location).body(ingrediente);
        return response;
    }

    @PutMapping("/ingredientes")
    public ResponseEntity<Ingrediente> editIngrediente(@RequestBody @Valid IngredienteRequest request) {
        LOG.info("Llamada a editar Ingrediente, request: {}", request);
        Ingrediente ingrediente = getIngredienteService().editIngrediente(request);
        return ResponseEntity.ok().body(ingrediente);
    }

    @DeleteMapping("/ingredientes/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Integer id) {
        LOG.info("Llamada a eliminar Ingrediente {}", id);
        Ingrediente ingrediente = getIngredienteService().getIngredienteId(id);
        getIngredienteService().deleteIngrediente(ingrediente);

        return ResponseEntity.ok().build();
    }
}