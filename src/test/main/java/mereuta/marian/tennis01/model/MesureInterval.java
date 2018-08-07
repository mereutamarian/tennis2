package mereuta.marian.tennis01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="mesureinterval")
public class MesureInterval {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
    @Column(name = "nom_unite_mesure")
    private String nomInterval;
    @Column(name = "mesure")
    private int mesure_interval;

    public MesureInterval() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomInterval() {
        return nomInterval;
    }

    public void setNomInterval(String nomInterval) {
        this.nomInterval = nomInterval;
    }

    public int getMesure_interval() {
        return mesure_interval;
    }

    public void setMesure_interval(int mesure_interval) {
        this.mesure_interval = mesure_interval;
    }

    @Override
    public String toString() {
        return "MesureInterval{" +
                "id=" + id +
                ", nomInterval='" + nomInterval + '\'' +
                ", mesure_interval=" + mesure_interval +
                '}';
    }
}
