package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Receta;

@Repository
public interface RecetaRepository extends CrudRepository<Receta,String>{
    
}