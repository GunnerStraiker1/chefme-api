package mx.uady.jpademo.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoriaRequest {

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    private Integer quantity;

    public CategoriaRequest() {}

    /**
     * @return the nombre
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the nombre to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}