package mx.uady.jpademo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.uady.jpademo.model.enums.CategoriaEnum;

@Entity
@Table(name = "categoria")
public class Categoria{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoria_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private CategoriaEnum name;
    
    @Column
    private Integer quantity;

    public Categoria(){};

    /**
     * @return the receta_id
     */
    public Integer getCategoria_id() {
        return categoria_id;
    }
    /**
     * @param categoria_id the receta_id to set
     */
    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    /**
     * @return the name
     */
    public CategoriaEnum getName() {
        return name;
    }
    /**
     * @param name the name to set
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

    @Override
    public String toString() {
        return super.toString();
    }
}