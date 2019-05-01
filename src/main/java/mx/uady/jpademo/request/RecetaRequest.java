package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RecetaRequest{
    @NotBlank
    @Size(min=3,max=255)
    private String titulo;

    public RecetaRequest(){}

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
}