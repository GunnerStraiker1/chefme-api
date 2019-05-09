package mx.uady.jpademo.request;

import javax.validation.constraints.NotNull;

import mx.uady.jpademo.model.enums.CategoriaEnum;

public class CategoriaRequest {

    @NotNull
    private CategoriaEnum name;

    private Integer quantity;

    public CategoriaRequest() {}

    /**
     * @return the nombre
     */
    public CategoriaEnum getName() {
        return name;
    }

    /**
     * @param name the nombre to set
     */
    public void setName(CategoriaEnum name) {
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