package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Tarif;
import mereuta.marian.tennis01.repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifMetier implements TarifMetierInterface {

    @Autowired
    TarifRepository tarifRepository;

    List<Tarif> tarifs;


    @Override
    public List<Tarif> listeTarifs(){

        return tarifRepository.findAll();
    }

    @Override
    public void addTarif(Tarif tarif) {

        tarifRepository.save(tarif);

    }

    @Override
    public Tarif getTarif(Integer id) {
        return tarifRepository.getOne(id);
    }

    @Override
    public void TarifActif(Tarif tarif) {
        tarif.setActif(true);
    }

    @Override
    public void deleteTarif(Tarif tarif) {
        tarif.setActif(false);
        tarifRepository.save(tarif);
    }

    @Override
    public int intersectionDates(Tarif tarif){

        int check=0;
        tarifs=tarifRepository.listeTarifsNormaux();

        for (Tarif t: tarifs){
            if(tarif.getDateDebut().isAfter(t.getDateDebut()) && tarif.getDateDebut().isBefore(t.getDateFin())||
               t.getDateDebut().isAfter(tarif.getDateDebut()) && t.getDateDebut().isBefore(tarif.getDateFin()) ){

                check++;
                System.out.println( "je suis "+check);
            }

        }
        System.out.println(check);


        return check;
    }


}
