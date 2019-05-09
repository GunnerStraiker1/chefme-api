package mx.uady.jpademo.resource;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.jpademo.model.Token;
import mx.uady.jpademo.model.Usuario;
import mx.uady.jpademo.request.SigninRequest;
import mx.uady.jpademo.request.SignupRequest;
import mx.uady.jpademo.service.UsuarioService;

@RestController
public class UsuarioResource {
    final Logger LOG = LoggerFactory.getLogger(UsuarioResource.class);

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) throws URISyntaxException {
        LOG.info("Llamada a registrarse");
        Usuario user = usuarioService.saveUser(request);
        if (user.getUser().equalsIgnoreCase("Null")) {
            return new ResponseEntity<String>("Fail -> Username is already taken!", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok().body("User registered successfully!" + user.toString());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Token> login(@Valid @RequestBody SigninRequest signinRequest) {
        LOG.info("Llamada a iniciar sesion");
        Token token = usuarioService.authentication(signinRequest);
        return ResponseEntity.ok(token);
    }

}