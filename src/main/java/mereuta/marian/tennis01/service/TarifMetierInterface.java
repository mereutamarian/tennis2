package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Tarif;

import java.util.List;

public interface TarifMetierInterface {

    public List<Tarif> listeTarifs();
    public void addTarif(Tarif tarif);
    public Tarif getTarif(Integer id);
    public void TarifActif(Tarif tarif);
    public void deleteTarif(Tarif tarif);
    public int intersectionDatesOuDatesEgales(Tarif tarif, List<Tarif> tarifs);
    public boolean weekendEgal(Tarif tarif ,Tarif tarif1);
    public int intersectionDatesOuDatesEgalesEtWeekEndDifferent(Tarif tarif);
    public boolean heureEgaleOuIntersection(Tarif tarif1, Tarif tarif2);
    public int dateEgaleWeekEndEgalEtHeureDifferente(Tarif tarif);
    public List<Tarif> listeTarifsNormaux();

}
