package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Ingrediente;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente,String>{
    
}