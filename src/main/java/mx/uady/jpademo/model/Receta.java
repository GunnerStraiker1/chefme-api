package mx.uady.jpademo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "receta")
public class Receta {
    @Id
    private Integer receta_id;
    @Column
    private String title;
    @Column
    private String preparation;
    @Column
    private String image;
    @Column
    private boolean isVerified;

    @ManyToMany
    @JoinTable(name = "indispensables", joinColumns = @JoinColumn(name = "receta_id"), inverseJoinColumns = @JoinColumn(name = "ingrediente_id"))
    Set<Ingrediente> indispensables;

    public Receta() {
    }

    /**
     * @return the receta_id
     */
    public Integer getReceta_id() {
        return receta_id;
    }

    /**
     * @param receta_id the receta_id to set
     */
    public void setReceta_id(Integer receta_id) {
        this.receta_id = receta_id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the preparation
     */
    public String getPreparation() {
        return preparation;
    }

    /**
     * @param preparation the preparation to set
     */
    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return true or false
     */
    public boolean getVerified() {
        return isVerified;
    }

    /**
     * @param isVerified the isVerified to set
     */
    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getReceta_id() + "'" + ", titulo='" + getTitle() + "'" + ", prepración='"
                + getPreparation() + "'" + ", verificado='" + getVerified() + "'" + "}";
    }
}