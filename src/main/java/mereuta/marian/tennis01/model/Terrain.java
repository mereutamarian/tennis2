package mereuta.marian.tennis01.model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "terrains")
public class Terrain {

    @Id @GeneratedValue
    @Column(name = "id_terrain")
    private Integer id;
    @NotNull

    @Size(min = 3, max = 25)
    @Column(name = "nom_terrain")
    private  String nomTerrain;

    @Column(name = "actif")
    private  boolean actif;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Terrain(@NotNull @Size(min = 3, max = 25) String nomTerrain, boolean actif) {
        this.nomTerrain = nomTerrain;
        this.actif = actif;
    }

    public Terrain(){

    }

    @Override
    public String toString() {
        return "Terrain{" +
                "id=" + id +
                ", nomTerrain='" + nomTerrain + '\'' +
                ", actif=" + actif +
                '}';
    }
}