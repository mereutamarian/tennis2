package mereuta.marian.tennis01.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @Column(name = "date_fin")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFin;
    @Column(name = "heure_debut")
    private LocalTime heureDebut;
    @Column(name = "heure_fin")
    private LocalTime heureFin;
    @Column(name="weekend")
    private boolean weekend;
    @Column(name = "tarif_special")
    private boolean tarifSpecial;
    @Column(name = "default_tarif")
    private boolean tarifParDefaut;
    @ManyToOne
    @JoinColumn(name = "id_Utilisateur")
    private Utilisateur utilisateur;

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

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public boolean isTarifSpecial() {
        return tarifSpecial;
    }

    public void setTarifSpecial(boolean tarifSpecial) {
        this.tarifSpecial = tarifSpecial;
    }

    public boolean isTarifParDefaut() {
        return tarifParDefaut;
    }

    public void setTarifParDefaut(boolean tarifParDefaut) {
        this.tarifParDefaut = tarifParDefaut;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Tarif() {
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
                ", weekend=" + weekend +
                ", tarifSpecial=" + tarifSpecial +
                ", tarifParDefaut=" + tarifParDefaut +
                ", utilisateur=" + utilisateur +
                '}';
    }
}