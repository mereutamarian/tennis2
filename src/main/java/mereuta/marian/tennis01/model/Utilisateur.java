package mereuta.marian.tennis01.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "utilisateurs")
public class Utilisateur {
    @Id @GeneratedValue
    @Column(name = "id_utilisateur")
    private Integer id;
    @Column(name = "nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name = "adresse")
    private String adrese;
    @Column(name = "code_postal")
    private int codePostal;
    @Column(name = "ville")
    private String ville;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "email")
    private String email;
    private String password;
    @Column(name="credit")
    private float credit;
    @Column(name = "actif")
    private boolean actif;

    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;

    @OneToMany
    @JoinColumn(name = "id_reservation")
    private List<Reservation> reservations;

    public Utilisateur() {
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Utilisateur(String nom, String prenom, String adrese, int codePostal, String ville, String telephone, String email, String password , float credit, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.adrese = adrese;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.credit = credit;
        this.password=password;
        this.actif = true;
        this.role=role;

    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adrese='" + adrese + '\'' +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                ", actif=" + actif +
                ", role=" + role +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdrese() {
        return adrese;
    }

    public void setAdrese(String adrese) {
        this.adrese = adrese;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
