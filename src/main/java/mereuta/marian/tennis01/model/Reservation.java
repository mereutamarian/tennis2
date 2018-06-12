package mereuta.marian.tennis01.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "reservations")
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "id_reservation")
    private Integer id;
    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;
    @Column(name = "date_debut")
    private LocalDateTime dateDebut;
    @Column(name = "date_fin")
    private LocalDateTime dateFin;
    @Column(name = "active")
    private boolean actif;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
  private   Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_terrain")
   private Terrain terrain;
    @ManyToOne
    @JoinColumn(name = "id_tarif")
   private Tarif tarif;
    @ManyToOne
    @JoinColumn(name="id_tarif_speciaux")
    private TarifSpecial tarifSpecial;

    public Reservation() {
    }

    public Reservation(LocalDateTime dateReservation, LocalDateTime dateDebut, LocalDateTime dateFin, boolean actif, Utilisateur utilisateur, Terrain terrain, Tarif tarif, TarifSpecial tarifSpecial) {
        this.dateReservation = dateReservation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.actif = actif;
        this.utilisateur = utilisateur;
        this.terrain = terrain;
        this.tarif = tarif;
        this.tarifSpecial = tarifSpecial;
    }
}
