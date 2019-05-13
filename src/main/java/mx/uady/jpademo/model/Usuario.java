package mx.uady.jpademo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idusuario;
    @Column
    private String user;
    @Column
    private String password;
    @Column
    private String type;
    @Column
    private String token;

    @OneToMany(mappedBy = "recetary")
    @JsonManagedReference
    private Set<Receta> recetas;

    public Usuario() {
    }

    public Usuario(String user, String password, String type) {
        this.user = user;
        this.password = password;
        this.type = type;
    }

    /**
     * @return the idusuario
     */
    public Integer getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the recetas
     */
    public Set<Receta> getRecetas() {
        return recetas;
    }
    /**
     * @param recetas the recetas to set
     */
    public void setRecetas(Set<Receta> recetas) {
        this.recetas = recetas;
    }

    @Override
    public String toString() {
        return ("\n User:" + getUser() + " Token: " + getToken() + " Type: " + getType());
    }
}