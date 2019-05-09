package mx.uady.jpademo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.uady.jpademo.model.Categoria;
import mx.uady.jpademo.model.enums.CategoriaEnum;
import mx.uady.jpademo.repository.CategoriaRepository;
import mx.uady.jpademo.request.CategoriaRequest;

@Service
public class CategoriaService {
    private final Logger LOG = LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * @return the ingredienteRepo
     */
    public CategoriaRepository getCategoriaRepo() {
        return categoriaRepository;
    }

    public List<Categoria> getCategorias() {
        LOG.info("Llamada a listar Categorias - Service");
        List<Categoria> categorias = new LinkedList<>();
        getCategoriaRepo().findAll().iterator().forEachRemaining(categorias::add);
        return categorias;
    }

    public Categoria saveCategoria(CategoriaRequest request) { // Verificar que no se agregue uno repetido
        LOG.info("Llamada a agregar Categoria - Service");

        Integer registrados = (int) (getCategoriaRepo().count());

        if (registrados == 0) {
            LOG.error("Sin registros en la tabla de Categoria");
        }

        Categoria categoria = new Categoria();
        categoria.setName(request.getName());

        categoria = getCategoriaRepo().save(categoria);

        return categoria;
    }

    public Categoria getCategoriaName(CategoriaEnum nombre) {
        LOG.info("Obtener categoria por nombre: {}", nombre);
        Categoria categoria = getCategoriaRepo().findByName(nombre);

        if (categoria == null) {
            LOG.error("La categoría con nombre {} no existe", nombre);
            // throw new RecursoNoEncontradoException("equipo"); -- Crear la clase para la
            // excepcion
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return categoria;
    }

    public Categoria editCategoria(CategoriaRequest request) {
        LOG.info("Llamada a editar Categoria - Service");
        Categoria categoria = getCategoriaName(request.getName());

        getCategoriaRepo().delete(categoria);

        Categoria newCategoria = new Categoria();
        newCategoria.setCategoria_id(categoria.getCategoria_id());
        newCategoria.setName(categoria.getName());

        return getCategoriaRepo().save(newCategoria);
    }

    public void deleteCategoria(Categoria categoria) {
        LOG.info("Llamada a eliminar Categoria - Service");
        getCategoriaRepo().delete(categoria);
    }

    public Categoria getCategoriaId(Integer id) {
        LOG.info("Obtener Categoria por id: {}", id);
        Optional<Categoria> optional = getCategoriaRepo().findById(id);

        if (!optional.isPresent()) {
            LOG.error("La categoría con id {} no existe", id);
            // throw new RecursoNoEncontradoException("equipo"); -- Agregar la excepción
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return optional.get();
    }
}