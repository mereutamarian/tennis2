package mereuta.marian.tennis01.model;

import mereuta.marian.tennis01.annotations.EmailUnique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "utilisateurs")
public class Utilisateur {
    @Id @GeneratedValue
    @Column(name = "id_utilisateur")
    private Integer id;
    @Pattern(regexp = "[\\p{L} '-]+",message = "ce champ ne doit pas contenir des chiffres")
    @NotNull
    @Column(name = "nom")
    private String nom;
    @Pattern(regexp = "[\\p{L} '-]+",message = "ce champ ne doit pas contenir des chiffres")
    @NotNull
    @Column(name="prenom")
    private String prenom;
    @Column(name = "adresse")
    private String adrese;
    @Size(min=4 , max = 4)
    @Pattern(regexp = "[0-9]{4}", message = "le code postal doit contenir 4 chiffres !!!")
    @Column(name = "code_postal")
    private String codePostal;
    @Column(name = "ville")
    private String ville;
    @Pattern(regexp ="([(+]*[0-9]+[()+. -]*)", message = "le format du numéro du téléphone que vous avez inseré est invalide")
    @Column(name = "telephone")
    private String telephone;
    @Pattern(regexp = "^[\\w\\-.+_%]+@[\\w\\.\\-]+\\.[A-Za-z0-9]{2,}$",message = "le format de l'email est invalide")
    @Column(name = "email")
    @EmailUnique(message = "mail deja existant")
    private String email;
    @Size(min = 4, max = 255)
    @Column(name = "password")
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

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
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
