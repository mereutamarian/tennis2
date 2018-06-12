package mereuta.marian.tennis01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDate;

@Entity(name = "tarifs")
public class Tarif {
    @Id
    @GeneratedValue
    @Column(name = "id_tarif")
    private Integer id;
    @Column(name = "nom_tarif")
    private String nomTarif;
    @Column(name = "prix")
    private float prix;
    @Column(name = "actif")
    private boolean actif;
    @Column(name = "date_debut")
    private LocalDate dateDebut;
    @Column(name = "date_fin")
    private LocalDate dateFin;
    @Column(name = "heure_debut")
    private Time heureDebut;
    @Column(name = "heure_fin")
    private Time heureFin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomTarif() {
        return nomTarif;
    }

    public void setNomTarif(String nomTarif) {
        this.nomTarif = nomTarif;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public Tarif() {
    }

    public Tarif(String nomTarif, float prix, boolean actif, LocalDate dateDebut, LocalDate dateFin, Time heureDebut, Time heureFin) {
        this.nomTarif = nomTarif;
        this.prix = prix;
        this.actif = actif;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    @Override
    public String toString() {
        return "Tarif{" +
                "id=" + id +
                ", nomTarif='" + nomTarif + '\'' +
                ", prix=" + prix +
                ", actif=" + actif +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                '}';
    }
}