package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RecetaRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String titulo;

    private String preparacion;
    private Integer categoria_id;

    public RecetaRequest() {
    }

    /**
     * @return the nombre
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the nombre to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the preparacion
     */
    public String getPreparacion() {
        return preparacion;
    }

    /**
     * @param preparacion the preparacion to set
     */
    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    /**
     * @return the categoria_id
     */
    public Integer getCategoria_id() {
        return categoria_id;
    }
    /**
     * @param categoria_id the categoria_id to set
     */
    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }
}