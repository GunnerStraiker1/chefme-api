package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.uady.jpademo.model.Ingrediente;
import mx.uady.jpademo.repository.IngredienteRepository;
import mx.uady.jpademo.request.IngredienteRequest;

@Service
public class IngredienteService {
    private final Logger LOG = LoggerFactory.getLogger(IngredienteService.class);

    @Autowired
    private IngredienteRepository ingredienteRepo;

    /**
     * @return the ingredienteRepo
     */
    public IngredienteRepository getIngredienteRepo() {
        return ingredienteRepo;
    }

    public List<Ingrediente> getIngredientes() {
        LOG.info("Llamada a listar Ingredientes - Service");
        List<Ingrediente> ingredientes = new LinkedList<>();
        getIngredienteRepo().findAll().iterator().forEachRemaining(ingredientes::add);
        return ingredientes;
    }

    public Ingrediente saveIngrediente(IngredienteRequest request) { // Verificar que no se agregue uno repetido
        LOG.info("Llamada a agregar Ingrediente - Service");

        Integer registrados = (int) (getIngredienteRepo().count());

        if (registrados == 0) {
            LOG.error("Sin registros en la tabla de Ingredientes");
        }

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setIngrediente_id(registrados + 1);
        ingrediente.setName(request.getNombre());
        ingrediente.setDescription(request.getDescripcion());
        ingrediente.setMeasure(request.getMeasure());
        ingrediente.setQuantity(request.getQuantity());

        ingrediente = getIngredienteRepo().save(ingrediente);

        return ingrediente;
    }

    public Ingrediente getIngredienteNombre(String nombre) {
        LOG.info("Obtener ingrediente por nombre: {}", nombre);
        Ingrediente ingrediente = getIngredienteRepo().findByName(nombre);

        if (ingrediente == null) {
            LOG.error("El ingrediente con nombre {} no existe", nombre);
            // throw new RecursoNoEncontradoException("equipo"); -- Crear la clase para la
            // excepcion
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingrediente;
    }

    public Ingrediente editIngrediente(IngredienteRequest request) {
        LOG.info("Llamada a editar Ingrediente - Service");
        Ingrediente ingrediente = getIngredienteNombre(request.getNombre());

        getIngredienteRepo().delete(ingrediente);

        Ingrediente newIngrediente = new Ingrediente();
        newIngrediente.setIngrediente_id(ingrediente.getIngrediente_id());
        newIngrediente.setName(ingrediente.getName());
        newIngrediente.setDescription(request.getDescripcion());
        newIngrediente.setMeasure(request.getMeasure());
        newIngrediente.setQuantity(request.getQuantity());

        return getIngredienteRepo().save(newIngrediente);
    }

    public void deleteIngrediente(Ingrediente ingrediente) {
        LOG.info("Llamada a eliminar Ingrediente - Service");
        getIngredienteRepo().delete(ingrediente);
    }

    public Ingrediente getIngredienteId(Integer id) {
        LOG.info("Obtener Ingrediente por id: {}", id);
        Optional<Ingrediente> optional = getIngredienteRepo().findById(id);

        if (!optional.isPresent()) {
            LOG.error("El ingrediente con id {} no existe", id);
            // throw new RecursoNoEncontradoException("equipo"); -- Agregar la excepción
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }
}