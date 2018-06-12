package mereuta.marian.tennis01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "roles")
public class Role {


    @Id @GeneratedValue
    @Column(name = "id_role")
    private Integer id;
    @Column(name = "nom_role")
    private String nomRole;
    @Column(name = "actif")
    private boolean actif;

    public Role() {
    }

    public Role( String nomRole ) {

        this.nomRole = nomRole;
        this.actif=true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nomRole='" + nomRole + '\'' +
                ", actif=" + actif +
                '}';
    }
}
