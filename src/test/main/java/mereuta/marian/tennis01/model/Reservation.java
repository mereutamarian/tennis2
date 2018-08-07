package mereuta.marian.tennis01.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "reservations")
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_creation_reservation")
    private LocalDateTime dateCreationReservation;
    @Column(name = "date_reservation")
    private LocalDate dateReservation;
    @Column(name = "heure_debut")
    private LocalTime heureDebut;
    @Column(name = "heure_fin")
    private LocalTime heureFin;
    @Column(name = "active")
    private boolean actif;
    @Column(name = "optional")
    private boolean optional;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private   Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_terrain")
   private Terrain terrain;
    @ManyToOne
    @JoinColumn(name = "id_tarif")
   private Tarif tarif;

    public Reservation() {
    }

    public Reservation(LocalDateTime dateCreationReservation, LocalDate dateReservation, LocalTime heureDebut, LocalTime heureFin, boolean actif,boolean optional, Utilisateur utilisateur, Terrain terrain, Tarif tarif) {
        this.dateCreationReservation = dateCreationReservation;
        this.dateReservation = dateReservation;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.actif = actif;
        this.optional=optional;
        this.utilisateur = utilisateur;
        this.terrain = terrain;
        this.tarif = tarif;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateCreationReservation() {
        return dateCreationReservation;
    }

    public void setDateCreationReservation(LocalDateTime dateCreationReservation) {
        this.dateCreationReservation = dateCreationReservation;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateCreationReservation=" + dateCreationReservation +
                ", dateReservation=" + dateReservation +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", actif=" + actif +
                ", optional=" + optional +
                ", utilisateur=" + utilisateur +
                ", terrain=" + terrain +
                ", tarif=" + tarif +
                '}';
    }


}
