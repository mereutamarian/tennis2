package mereuta.marian.tennis01.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name ="objets" )

public class Objet {
    @Id @GeneratedValue
    @Column(name = "id_objet")
    private Integer idObjests;

    @Column(name = "nom_objet")

    private String nomObjet;

    @NotNull
    @Size(min = 15, max = 1000)
    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photoPath;

    @Column(name = "actif")
    private boolean actif;


    public Objet() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return "Objet{" +
                "idObjests=" + idObjests +
                ", nomObjet='" + nomObjet + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", actif=" + actif +
                '}';
    }
}
