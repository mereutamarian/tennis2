package mereuta.marian.tennis01.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "reservations")
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;
    @Column(name = "date_debut")
    private LocalDateTime dateDebut;
    @Column(name = "date_fin")
    private LocalDateTime dateFin;
    @Column(name = "active")
    private boolean actif;
    @Column(name = "prix")
    private float prix;
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

    public Reservation(LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin, boolean actif, float prix, Utilisateur utilisateur, Terrain terrain, Tarif tarif) {
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.actif = actif;
        this.prix = prix;
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

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateReservation=" + dateReservation +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", actif=" + actif +
                ", prix=" + prix +
                ", utilisateur=" + utilisateur +
                ", terrain=" + terrain +
                ", tarif=" + tarif +
                '}';
    }
}
