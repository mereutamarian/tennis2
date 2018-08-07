package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Tarif;
import mereuta.marian.tennis01.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarifMetier implements TarifMetierInterface {

    @Autowired
    TarifRepository tarifRepository;

    List<Tarif> tarifs;


    @Override
    public List<Tarif> listeTarifs() {

        return tarifRepository.findAll();
    }

    @Override
    public void addTarif(Tarif tarif) {

        tarifRepository.save(tarif);


    }

    @Override
    public Tarif getTarif(Integer id) {
        return tarifRepository.findTarifByIdAndActifTrue(id);
    }

    @Override
    public void TarifActif(Tarif tarif) {
        tarif.setActif(true);
    }

    @Override
    public void TarifSpecial(Tarif tarif) {
        tarif.setTarifSpecial(true);
    }

    @Override
    public void deleteTarif(Tarif tarif) {
        tarif.setActif(false);
        tarifRepository.save(tarif);
    }

    @Override
    public int intersectionDatesOuDatesEgales(Tarif tarif, List<Tarif> tarifs) {


        int check = 0;


        for (Tarif t : tarifs) {
            if (checkIfIntersectionOuDatesEgales(tarif, t) == true)

                check++;
            {


                System.out.println("je suis " + check);
            }

        }
        System.out.println(check);


        return check;
    }

    @Override
    public int intersectionDatesOuDatesEgalesEtWeekEndDifferent(Tarif tarif, List<Tarif> tarifs) {


        List<Tarif> weekendsEgals = new ArrayList<>();
        int checkWeeknd = 0;
        int check = 0;


        for (Tarif t : tarifs) {
            if (checkIfIntersectionOuDatesEgales(tarif, t)) {

                weekendsEgals.add(t);

            }

        }

        for (Tarif t : weekendsEgals) {
            if (weekendEgal(tarif, t) == false) {
                checkWeeknd++;
            }
        }

        if (weekendsEgals.size() == checkWeeknd) {
            check++;
        }
        System.out.println("je suis le weekend" + check);


        return check;
    }


    @Override
    public boolean weekendEgal(Tarif tarif, Tarif tarif1) {

        if (tarif.isWeekend() == tarif1.isWeekend()) {
            return true;

        } else {
            return false;
        }


    }


    @Override
    public boolean heureEgaleOuIntersection(Tarif tarif1, Tarif tarif2) {

        if (tarif1.getHeureDebut().minusHours(1).isAfter(tarif2.getHeureDebut().minusHours(1)) && tarif1.getHeureDebut().minusHours(1).isBefore(tarif2.getHeureFin().minusHours(1)) ||
                tarif2.getHeureDebut().minusHours(1).isAfter(tarif1.getHeureDebut().minusHours(1)) && tarif2.getHeureDebut().minusHours(1).isBefore(tarif1.getHeureFin().minusHours(1)) ||
                tarif1.getHeureDebut().minusHours(1).equals(tarif2.getHeureDebut().minusHours(1)) && tarif1.getHeureFin().minusHours(1).equals(tarif2.getHeureFin().minusHours(1))) {

            return true;
        } else {
            return false;
        }


    }


    @Override
    public int dateEgaleWeekEndEgalEtHeureDifferente(Tarif tarif, List<Tarif> tarifs) {

        List<Tarif> weekendsEgals = new ArrayList<>();
        List<Tarif> heuresCheck = new ArrayList<>();
        int check = 0;


        for (Tarif t : tarifs) {
            if (checkIfIntersectionOuDatesEgales(tarif, t)) {

                weekendsEgals.add(t);

            }

        }

        for (Tarif t : weekendsEgals) {
            if (weekendEgal(tarif, t) == true) {
                heuresCheck.add(t);
            }
        }

        for (Tarif t : heuresCheck) {
            if (heureEgaleOuIntersection(tarif, t)) {
                check++;


            }
        }
        System.out.println("je suis l'heure " + check);
        return check;
    }

    @Override
    public List<Tarif> listeTarifsNormaux() {
        return tarifRepository.listeTarifsNormaux();
    }

    @Override
    public List<Tarif> listeTarifsSpeciaux() {
        return tarifRepository.listeTarifsSpeciaux();
    }

    @Override
    public List<Tarif> effacerTarifListe(Tarif tarif, List<Tarif> tarifs) {

        for(Tarif t: tarifs){
            if(tarif.getId()==t.getId()){
                System.out.println("avant "+tarifs);
                tarifs.remove(t);
                System.out.println("apres "+tarifs);
            }
        }

        return tarifs;
    }

    @Override
    public int getAnneCourante() {

        return LocalDate.now().getYear();
    }

    @Override
    public int getAnneeprochaine(int annee) {
        return annee+1;
    }

    @Override
    public boolean checkIfIntersectionOuDatesEgales(Tarif tarif, Tarif t) {
        if (tarif.getDateDebut().isAfter(t.getDateDebut()) && tarif.getDateDebut().isBefore(t.getDateFin()) ||
                t.getDateDebut().isAfter(tarif.getDateDebut()) && t.getDateDebut().isBefore(tarif.getDateFin()) ||
                tarif.getDateDebut().equals(t.getDateDebut()) && tarif.getDateFin().equals(t.getDateFin())
                ) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<Tarif> tarifsNormauxSemaine() {

        return tarifRepository.listeTarifsNormauxSemaine();
    }
    @Override
    public List<Tarif> tarifsNormauxWeekend() {

        return tarifRepository.listeTarifsNormauxWeekEnd();
    }
    @Override
    public Tarif tarifParDefaut() {

        return tarifRepository.findByTarifParDefautTrue();
    }
}