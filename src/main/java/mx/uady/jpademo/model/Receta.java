package mx.uady.jpademo.model;

import java.util.Set;

// import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "receta")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_receta;
    @Column
    private String title;
    @Column
    private String preparation;
    @Column
    private String image;
    @Column
    private Integer verified;
    @Column
    private Integer calification;
    @Column(name = "categoria_id")
    private Integer categoria_id;
    @Column(name = "usuario_id")
    private Integer usuario_id;

    @ManyToOne
    @MapsId("categoria_id")
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    private Categoria category;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Receta recetary;

    @OneToMany(mappedBy = "instead")
    @JsonManagedReference
    private Set<Ingrediente> ingredientes;

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

    /**
     * @return the calification
     */
    public Integer getCalification() {
        return calification;
    }

    /**
     * @param calification the calification to set
     */
    public void setCalification(Integer calification) {
        this.calification = calification;
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

    /**
     * @return the usuario_id
     */
    public Integer getUsuario_id() {
        return usuario_id;
    }
    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    /**
     * @return the recetary
     */
    public Receta getRecetary() {
        return recetary;
    }

    /**
     * @param recetary the recetary to set
     */
    public void setRecetary(Receta recetary) {
        this.recetary = recetary;
    }

    /**
     * @return the ingredientes
     */
    public Set<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    /**
     * @param ingredientes the ingredientes to set
     */
    public void setIngredientes(Set<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getReceta_id() + "'" + ", titulo='" + getTitle() + "'" + ", prepración='"
                + getPreparation() + "'" + ", verificado='" + getVerified() + "'" + ", categoria_id='"
                + getCategoria_id() + " +'}";
    }
}