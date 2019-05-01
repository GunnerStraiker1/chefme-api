package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uady.jpademo.model.Ingrediente;
import mx.uady.jpademo.repository.IngredienteRepository;

@Service
public class IngredienteService{
    private final Logger LOG = LoggerFactory.getLogger(IngredienteService.class);

    @Autowired
    private IngredienteRepository ingredienteRepo;

    /**
     * @return the ingredienteRepo
     */
    public IngredienteRepository getIngredienteRepo() {
        return ingredienteRepo;
    }

    public List<Ingrediente> getIngredientes(){
        LOG.info("Llamada a listar Ingredientes - Service");
        List<Ingrediente> ingredientes = new LinkedList<>();
        getIngredienteRepo().findAll().iterator().forEachRemaining(ingredientes::add);
        return ingredientes;
    }
}