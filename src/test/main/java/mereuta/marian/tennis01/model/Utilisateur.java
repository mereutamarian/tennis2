package mereuta.marian.tennis01.model;

import mereuta.marian.tennis01.annotations.CheckAge;
import mereuta.marian.tennis01.annotations.EmailUnique;
import mereuta.marian.tennis01.annotations.PassMatch;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@PassMatch(field = "password", verifyField = "confirmPassword")
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
    @Transient
    private String confirmPassword;
    @Column(name="credit")
    private float credit;
    @Column(name = "actif")
    private boolean actif;
    @CheckAge(message = "vous avez moins de 16 ans")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_naissance")
    private LocalDate dateNaissance;
    @Size(min = 1 , max = 1)
    @Column(name="sexe")
    private String sexe;

    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_utilisateur")
    private List<Reservation> reservations= new ArrayList<>();

    public Utilisateur() {
    }

    public Utilisateur(@Pattern(regexp = "[\\p{L} '-]+", message = "ce champ ne doit pas contenir des chiffres") @NotNull String nom, @Pattern(regexp = "[\\p{L} '-]+", message = "ce champ ne doit pas contenir des chiffres") @NotNull String prenom, String adrese, @Size(min = 4, max = 4) @Pattern(regexp = "[0-9]{4}", message = "le code postal doit contenir 4 chiffres !!!") String codePostal, String ville, @Pattern(regexp = "([(+]*[0-9]+[()+. -]*)", message = "le format du numéro du téléphone que vous avez inseré est invalide") String telephone, @Pattern(regexp = "^[\\w\\-.+_%]+@[\\w\\.\\-]+\\.[A-Za-z0-9]{2,}$", message = "le format de l'email est invalide") String email, LocalDate dateNaissance, @Size(min = 1, max = 1) String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.adrese = adrese;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
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
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", credit=" + credit +
                ", actif=" + actif +
                ", dateNaissance=" + dateNaissance +
                ", sexe='" + sexe + '\'' +
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}