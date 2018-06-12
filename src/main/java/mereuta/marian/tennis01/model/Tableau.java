package mereuta.marian.tennis01.model;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Entity(name = "tableaux")

public class Tableau {

    @Id @GeneratedValue
    @Column(name="id_tableau")
    private Integer id;
    @NotNull
    @Column(name = "nombre_jours")
    private int nombreJours;
    @Column(name = "nom_tableau")
    private String nomTableau;

    public Tableau() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNombreJours() {
        return nombreJours;
    }

    public void setNombreJours(int nombreJours) {
        this.nombreJours = nombreJours;
    }

    public String getNomTableau() {
        return nomTableau;
    }

    public void setNomTableau(String nomTableau) {
        this.nomTableau = nomTableau;
    }

    @Override
    public String toString() {
        return "Tableau{" +
                "id=" + id +
                ", nombreJours=" + nombreJours +
                ", nomTableau='" + nomTableau + '\'' +
                '}';
    }
}
