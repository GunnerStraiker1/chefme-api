package mx.uady.jpademo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Receta;

@Repository
public interface RecetaRepository extends CrudRepository<Receta, Integer> {
    public Receta findByTitle(String title);

    @Query("FROM Receta Where title Like %?1%")
    public List<Receta> getRecetasConIngrediente(String ingrediente);
}