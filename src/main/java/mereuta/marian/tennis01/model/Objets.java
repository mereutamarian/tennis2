package mereuta.marian.tennis01.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name ="objets" )

public class Objets {
    @Id @GeneratedValue
    @Column(name = "id")
    private Integer idObjests;

    @Column(name = "nom_objet")

    private String nomObjet;

    @NotEmpty
    @Size(min = 3)
    @Size(max = 25)
    @Column(name = "description")
    private String descrition;

    @Column(name = "photo")
    private String photoPath;

    @Column(name = "actif")
    private boolean active;

    public Objets() {
    }

    public Objets(String nomObjet, String descrition, String photoPath, boolean active) {
        this.nomObjet = nomObjet;
        this.descrition = descrition;
        this.photoPath = photoPath;
        this.active = active;
    }

    public Integer getIdObjests() {
        return idObjests;
    }

    public void setIdObjests(Integer idObjests) {
        this.idObjests = idObjests;
    }

    public String getNomObjet() {
        return nomObjet;
    }

    public void setNomObjet(String nomObjet) {
        this.nomObjet = nomObjet;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Objets{" +
                "idObjests=" + idObjests +
                ", nomObjet='" + nomObjet + '\'' +
                ", descrition='" + descrition + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", active=" + active +
                '}';
    }
}
