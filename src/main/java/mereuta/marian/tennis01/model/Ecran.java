package mereuta.marian.tennis01.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "ecran")
public class Ecran {

    @Id @GeneratedValue
    @Column(name="id_ecran")
    private  Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tableau")
    private Tableau tableau;



    public Ecran() {
    }

    public Integer getId() {
        return id;
    }



    public Tableau getTableau() {
        return tableau;
    }

    public void setTableau(Tableau tableau) {
        this.tableau = tableau;
    }

    @Override
    public String toString() {
        return "Ecran{" +
                "id=" + id +
                ", tableau=" + tableau +
                '}';
    }
}
