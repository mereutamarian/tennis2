package mereuta.marian.tennis01.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "messages")
public class Message {

    

    @Id @GeneratedValue
    @Column(name = "id_message")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "date_envoi")
    private Date dateEnvoi;

    @ManyToOne
    @JoinColumn(name="emetteur")
    private Utilisateur emmeteur;
    @ManyToOne
    @JoinColumn(name= "destinataire")
    private Utilisateur destinataire;

    public Message() {
    }

    public Message( String description, Utilisateur emmeteur, Utilisateur destinataire) {

        this.description = description;
        this.dateEnvoi=new Date();
        this.emmeteur = emmeteur;
        this.destinataire = destinataire;
    }

    public Integer getId() {
        return id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getdateEnvoi() {
        return dateEnvoi;
    }

    public void setdateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Utilisateur getEmmeteur() {
        return emmeteur;
    }

    public void setEmmeteur(Utilisateur emmeteur) {
        this.emmeteur = emmeteur;
    }

    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateEnvoi=" + dateEnvoi +
                ", emmeteur=" + emmeteur +
                ", destinataire=" + destinataire +
                '}';
    }
}
