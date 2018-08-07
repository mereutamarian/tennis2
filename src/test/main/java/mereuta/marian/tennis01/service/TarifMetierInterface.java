package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Tarif;

import java.util.List;

public interface TarifMetierInterface {

    public List<Tarif> listeTarifs();
    public void addTarif(Tarif tarif);
    public Tarif getTarif(Integer id);
    public void TarifActif(Tarif tarif);
    public void TarifSpecial(Tarif tarif);
    public void deleteTarif(Tarif tarif);
    public int intersectionDatesOuDatesEgales(Tarif tarif, List<Tarif> tarifs);
    public boolean weekendEgal(Tarif tarif, Tarif tarif1);
    public int intersectionDatesOuDatesEgalesEtWeekEndDifferent(Tarif tarif, List<Tarif> tarifs);
    public boolean heureEgaleOuIntersection(Tarif tarif1, Tarif tarif2);
    public int dateEgaleWeekEndEgalEtHeureDifferente(Tarif tarif, List<Tarif> tarifs);
    public List<Tarif> listeTarifsNormaux();
    public List<Tarif> listeTarifsSpeciaux();
    public  List<Tarif> effacerTarifListe(Tarif tarif, List<Tarif> tarifs);
    public List<Tarif> tarifsNormauxWeekend();
    public int getAnneCourante();
    public List<Tarif> tarifsNormauxSemaine();
    public int getAnneeprochaine(int annee);
    public Tarif tarifParDefaut();
    public boolean checkIfIntersectionOuDatesEgales(Tarif tarif, Tarif t);

}
