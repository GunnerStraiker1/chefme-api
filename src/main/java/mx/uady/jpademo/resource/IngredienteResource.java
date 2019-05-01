package mx.uady.jpademo.resource;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.jpademo.model.Ingrediente;
import mx.uady.jpademo.service.IngredienteService;

@RestController
public class IngredienteResource{
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
    public List<Ingrediente> getIngredientes(){
        LOG.info("Llamada a listar ingredientes- Resource");
        List<Ingrediente> ingredientes = new LinkedList<>();
        ingredientes = getIngredienteService().getIngredientes();
        return ingredientes;
    }
}