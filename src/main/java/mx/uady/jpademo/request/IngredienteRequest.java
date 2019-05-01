package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class IngredienteRequest {
    @NotBlank
    @Size(min = 0, max = 255)
    private String nombre;

    public IngredienteRequest() {
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}