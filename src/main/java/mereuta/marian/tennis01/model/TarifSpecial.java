package mereuta.marian.tennis01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalDate;

@Entity(name = "tarifspeciaux")
public class TarifSpecial {

    @Id @GeneratedValue
    @Column(name ="id_tarif_speciaux" )
    private Integer idTarifSpecial;
    @Column(name="date_debut")
    private LocalDate dateDebut;
    @Column(name="date_fin")
    private LocalDate dateFin;
    @Column(name="heure_debut")
    private Time heureDebut;
    @Column(name="heure_fin")
    private Time heureFin;
    @Column(name="actif")
    private boolean actif;


}
