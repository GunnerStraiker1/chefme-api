package mx.uady.jpademo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uady.jpademo.model.Usuario;

/**
 * UsuarioRepository
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    Optional<Usuario> findByUser(String user);

    Boolean existsByUser(String user);
}