package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Receta;
import mx.uady.jpademo.repository.RecetaRepository;

@Service
public class RecetaService{
    private final Logger LOG = LoggerFactory.getLogger(RecetaService.class);

    @Autowired
    private RecetaRepository recetaRepo;

    /**
     * @return the recetaRepo
     */
    public RecetaRepository getRecetaRepository() {
        return recetaRepo;
    }

    public List<Receta> getRecetas(){
        LOG.info("Llamada a listar Recetas - Service");
        List<Receta> recetas = new LinkedList<>();
        getRecetaRepository().findAll().iterator().forEachRemaining(recetas::add);
        return recetas;
    }

    public List<Receta> getRecetasByIngrediente(String ingrediente){
        LOG.info("Llamada a listar recetas por igrediente: {} - Service", ingrediente);
        List<Receta> recetas = new LinkedList<>();
        getRecetaRepository().getRecetasConIngrediente(ingrediente).iterator().forEachRemaining(recetas::add);
        return recetas;
    }
}