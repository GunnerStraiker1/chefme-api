package mx.uady.jpademo.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import mx.uady.jpademo.model.Usuario;
import mx.uady.jpademo.repository.UsuarioRepository;
import mx.uady.jpademo.request.SignupRequest;

@Service
public class UsuarioService {
    private final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    /**
     * @return the usuarioRepository
     */
    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public Usuario saveUser(@Valid @RequestBody SignupRequest request) {
        LOG.info("Guardando informacion de usuario");
        if (usuarioRepository.existsByUser(request.getUser())) {
            Usuario notVerified = new Usuario();
            LOG.info("Usuario duplicado");
            notVerified.setUser("Null");
            return notVerified;
        }
        Usuario user = new Usuario(request.getUser(), encoder.encode(request.getPassword()), request.getType());
        user = usuarioRepository.save(user);
        LOG.info("Usuario guardado");

        return user;

    }

}