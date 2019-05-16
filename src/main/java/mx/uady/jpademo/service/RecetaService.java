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

import mx.uady.jpademo.model.Receta;
import mx.uady.jpademo.repository.RecetaRepository;
import mx.uady.jpademo.request.RecetaRequest;

@Service
public class RecetaService {
    private final Logger LOG = LoggerFactory.getLogger(RecetaService.class);

    @Autowired
    private RecetaRepository recetaRepo;

    /**
     * @return the recetaRepo
     */
    public RecetaRepository getRecetaRepository() {
        return recetaRepo;
    }

    public List<Receta> getRecetas() {
        LOG.info("Llamada a listar Recetas - Service");
        List<Receta> recetas = new LinkedList<>();
        getRecetaRepository().findAll().iterator().forEachRemaining(recetas::add);
        return recetas;
    }

    public List<Receta> getRecetasByIngrediente(String ingrediente) {
        LOG.info("Llamada a listar recetas por igrediente: {} - Service", ingrediente);
        List<Receta> recetas = new LinkedList<>();
        getRecetaRepository().getRecetasConIngrediente(ingrediente).iterator().forEachRemaining(recetas::add);
        return recetas;
    }

    public Receta getRecetaById(Integer id) {
        LOG.info("Llamada a la receta por id: {} - Service", id);
        Receta recetas = getRecetaRepository().getRecetasConId(id);
        return recetas;
    }

    public Receta saveReceta(RecetaRequest request) { // Verificar que no se agregue uno repetido
        LOG.info("Llamada a agregar Receta - Service");

        Integer registrados = (int) (getRecetaRepository().count());

        if (registrados == 0) {
            LOG.error("Sin registros en la tabla de Recetas");
        }

        Receta receta = new Receta();
        // ingrediente.setIngrediente_id(registrados + 1);
        receta.setTitle(request.getTitulo());
        receta.setPreparation(request.getPreparacion());
        receta.setImage(request.getImage());
        receta.setCalification(request.getCalification());
        receta.setVerified(0);
        receta.setCategoria_id(request.getCategoria_id());
        receta.setUsuario_id(request.getUsuario_id());

        receta = getRecetaRepository().save(receta);

        return receta;
    }

    public Receta getRecetaTitle(String title) {
        LOG.info("Obtener receta por titulo: {}", title);
        Receta receta = getRecetaRepository().findByTitle(title);

        if (receta == null) {
            LOG.error("La receta con titulo {} no existe", title);
            // throw new RecursoNoEncontradoException("equipo"); -- Crear la clase para la
            // excepcion
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return receta;
    }

    public Receta editReceta(RecetaRequest request) {
        LOG.info("Llamada a editar Receta - Service");
        Receta receta = getRecetaTitle(request.getTitulo());

        // getRecetaRepository().delete(receta);

        Receta newReceta = new Receta();
        newReceta.setReceta_id(request.getId_receta());
        newReceta.setTitle(receta.getTitle());
        newReceta.setPreparation(request.getPreparacion());
        newReceta.setImage(receta.getImage());
        newReceta.setVerified(0);
        newReceta.setCategoria_id(receta.getCategoria_id());
        newReceta.setCalification(receta.getCalification());
        newReceta.setUsuario_id(receta.getUsuario_id());
        newReceta.setReceta_id(receta.getReceta_id());

        return getRecetaRepository().save(newReceta);
    }

    public void deleteReceta(Receta receta) {
        LOG.info("Llamada a eliminar Receta - Service");
        getRecetaRepository().delete(receta);
    }

    public Receta getRecetaId(Integer id) {
        LOG.info("Obtener Receta por id: {}", id);
        Optional<Receta> optional = getRecetaRepository().findById(id);

        if (!optional.isPresent()) {
            LOG.error("La receta con id {} no existe", id);
            // throw new RecursoNoEncontradoException("equipo"); -- Agregar la excepción
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }

    public Receta calificateReceta(Integer id, Integer puntuacion){
        Receta receta = getRecetaRepository().getRecetasConId(id);
        receta.setCalification(receta.getCalification()+puntuacion);
        receta.setVotos(receta.getVotos()+1);
        return receta;
    }
}