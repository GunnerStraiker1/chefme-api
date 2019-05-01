package mx.uady.jpademo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {
    @Id
    private Integer ingrediente_id;
    @Column
    private String name;
    @Column
    private Integer quantity;
    @Column
    private String measure;

    @ManyToMany(mappedBy = "indispensables")
    Set<Receta> elementosIndispensables;

    public Ingrediente() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the ingrediente_id
     */
    public Integer getIngrediente_id() {
        return ingrediente_id;
    }

    /**
     * @param ingrediente_id the ingrediente_id to set
     */
    public void setIngrediente_id(Integer ingrediente_id) {
        this.ingrediente_id = ingrediente_id;
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

    /**
     * @return the measure
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * @param measure the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}