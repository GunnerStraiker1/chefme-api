package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    public Categoria findByName(String name);
}