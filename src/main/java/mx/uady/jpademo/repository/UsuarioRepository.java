package mx.uady.jpademo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Usuario;

/**
 * UsuarioRepository
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    Usuario findByUser(String user);

    Boolean existsByUser(String user);

    Usuario findByToken(String token);
}