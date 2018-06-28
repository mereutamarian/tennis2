package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Tarif;

import java.util.List;

public interface TarifMetierInterface {

    public List<Tarif> listeTarifs();
    public void addTarif(Tarif tarif);
    public Tarif getTarif(Integer id);
    public void TarifActif(Tarif tarif);
    public void deleteTarif(Tarif tarif);
    public int intersectionDates(Tarif tarif);

}
