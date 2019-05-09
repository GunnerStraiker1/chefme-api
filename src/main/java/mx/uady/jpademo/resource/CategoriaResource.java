package mx.uady.jpademo.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mx.uady.jpademo.model.Categoria;
import mx.uady.jpademo.model.enums.CategoriaEnum;
import mx.uady.jpademo.request.CategoriaRequest;
import mx.uady.jpademo.service.CategoriaService;

@RestController
public class CategoriaResource {
    final Logger LOG = LoggerFactory.getLogger(CategoriaResource.class);

    @Autowired
    private CategoriaService categoriaService;

    /**
     * @return the categoriaService
     */
    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    @GetMapping("/categorias")
    public List<Categoria> getCategorias() {
        LOG.info("Llamada a listar categorias - Resource");
        List<Categoria> categorias = new LinkedList<>();
        categorias = getCategoriaService().getCategorias();
        return categorias;
    }

    @GetMapping("/categorias/{nombre}")
    public ResponseEntity<Categoria> getCategoria(@PathVariable CategoriaEnum nombre) {
        Categoria categoria = getCategoriaService().getCategoriaName(nombre);
        return ResponseEntity.ok().body(categoria);
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> saveCategoria(@RequestBody @Valid CategoriaRequest request)
            throws URISyntaxException {
        LOG.info("Llamada a agregar Categoria");
        Categoria categoria = getCategoriaService().saveCategoria(request);
        URI location = new URI("/categorias/" + categoria.getName());
        ResponseEntity<Categoria> response = ResponseEntity.created(location).body(categoria);
        return response;
    }

    @PutMapping("/categorias")
    public ResponseEntity<Categoria> editCategoria(@RequestBody @Valid CategoriaRequest request) {
        LOG.info("Llamada a editar Categoria, request: {}", request);
        Categoria categoria = getCategoriaService().editCategoria(request);
        return ResponseEntity.ok().body(categoria);
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        LOG.info("Llamada a eliminar Categoria {}", id);
        Categoria categoria = getCategoriaService().getCategoriaId(id);
        getCategoriaService().deleteCategoria(categoria);

        return ResponseEntity.ok().build();
    }
}