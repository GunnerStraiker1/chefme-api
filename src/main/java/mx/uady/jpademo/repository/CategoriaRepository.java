package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Categoria;
import mx.uady.jpademo.model.enums.CategoriaEnum;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
    public Categoria findByName(CategoriaEnum name);
}