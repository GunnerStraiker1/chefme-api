package mx.uady.jpademo.resource;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.jpademo.model.Receta;
import mx.uady.jpademo.service.RecetaService;

@RestController
public class RecetaResource{
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
    public List<Receta> getRecetas(){
        LOG.info("Llamada a listar recetas- Resource");
        List<Receta> recetas = new LinkedList<>();
        recetas = getRecetaService().getRecetas();
        return recetas;
    }

    @GetMapping("/recetas/{ingrediente}")
    public List<Receta> getRecetasByIngrediente(@PathVariable String ingrediente){
        LOG.info("Llamada a listar recetas por ingrediente: {} - Resource", ingrediente);
        List<Receta> recetas = new LinkedList<>();
        recetas = getRecetaService().getRecetasByIngrediente(ingrediente);
        return recetas;
    }
}