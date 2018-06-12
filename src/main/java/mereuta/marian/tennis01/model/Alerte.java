package mereuta.marian.tennis01.model;

import javax.persistence.*;

@Entity(name = "alertes")
public class Alerte {

    @Id @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    public Alerte() {


    }

    public Alerte(Utilisateur utilisateur, Reservation reservation) {
        this.utilisateur = utilisateur;
        this.reservation = reservation;
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Alerte{" +

                ", utilisateur=" + utilisateur +
                ", reservation=" + reservation +
                '}';
    }
}
