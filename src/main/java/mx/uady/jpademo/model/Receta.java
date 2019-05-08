package mx.uady.jpademo.model;

// import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "receta")
public class Receta {
    @Id
    private Integer id_receta;
    @Column
    private String title;
    @Column
    private String preparation;
    @Column
    private String image;
    @Column
    private Integer verified;

    public Receta() {
    }

    /**
     * @return the receta_id
     */
    public Integer getReceta_id() {
        return id_receta;
    }

    /**
     * @param receta_id the receta_id to set
     */
    public void setReceta_id(Integer id_receta) {
        this.id_receta = id_receta;
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
    public Integer getVerified() {
        return verified;
    }

    /**
     * @param isVerified the isVerified to set
     */
    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getReceta_id() + "'" + ", titulo='" + getTitle() + "'" + ", prepración='"
                + getPreparation() + "'" + ", verificado='" + getVerified() + "'" + "}";
    }
}