package mereuta.marian.tennis01.model;


import mereuta.marian.tennis01.annotations.CheckDateHoraireSpecial;
import mereuta.marian.tennis01.annotations.ValidAfterDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;



@ValidAfterDate
@Component
@Entity(name="horaire")
public class Horaire {


    @Id @GeneratedValue
    @Column(name = "id_horaire")
    private Integer id;
    @NotNull
    @Column(name = "heure_debut")
    private LocalTime heureDebut;


    @NotNull
    @Column(name = "heure_fin")
    private LocalTime heureFin;


    @NotNull
    @Size(min = 3, max = 30)
    @Column(name = "nom_horaire")
    private String nom;

    @Column(name = "jour_horaire_fix")
    private String horaireFix;

    @Future(message = "la date doit être supérieure à la date d'aujourd'hui")
    @CheckDateHoraireSpecial(message = "il existe deja un horaire avec cette date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_horaire_special")
    private LocalDate dateHoraireSpecial;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interval_mesure")
    private MesureInterval mesureInterval;

    @ManyToOne
    @JoinColumn(name = "id_Utilisateur")
    private Utilisateur utilisateur;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "terrainshoraire", joinColumns = @JoinColumn(name = "id_horaire"), inverseJoinColumns = @JoinColumn(name = "id_terrain"))

    private List<Terrain> terrains;


    public Horaire() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateHoraireSpecial() {
        return dateHoraireSpecial;
    }

    public void setDateHoraireSpecial(LocalDate dateHoraireSpecial) {
        this.dateHoraireSpecial = dateHoraireSpecial;
    }

    public MesureInterval getMesureInterval() {
        return mesureInterval;
    }

    public void setMesureInterval(MesureInterval mesureInterval) {
        this.mesureInterval = mesureInterval;
    }

    public String getHoraireFix() {
        return horaireFix;
    }

    public void setHoraireFix(String horaireFix) {
        this.horaireFix = horaireFix;
    }

    public List<Terrain> getTerrains() {
        return terrains;
    }

    public void setTerrains(List<Terrain> terrains) {
        this.terrains = terrains;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "id=" + id +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", nom='" + nom + '\'' +
                ", horaireFix='" + horaireFix + '\'' +
                ", dateHoraireSpecial=" + dateHoraireSpecial +
                ", mesureInterval=" + mesureInterval +
                ", utilisateur=" + utilisateur +
                ", terrains=" + terrains +
                '}';
    }
}
