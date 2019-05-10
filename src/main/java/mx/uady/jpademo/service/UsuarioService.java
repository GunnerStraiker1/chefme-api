package mx.uady.jpademo.service;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import mx.uady.jpademo.model.Token;
import mx.uady.jpademo.model.Usuario;
import mx.uady.jpademo.repository.UsuarioRepository;
import mx.uady.jpademo.request.SigninRequest;
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

    public Token authentication(@Valid @RequestBody SigninRequest request) {
        Usuario user = usuarioRepository.findByUser(request.getUser());
        LOG.info("Validadndo credenciales");

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        if (!(user.getUser().equals(request.getUser()))
                || !encoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LOG.info("Creando token");
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        usuarioRepository.save(user);

        Token tokenResponse = new Token();
        tokenResponse.setToken(token);
        LOG.info("Inicio de sesion exitoso");
        return tokenResponse;

    }

}