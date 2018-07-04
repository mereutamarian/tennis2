package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Ecran;
import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.repository.EcranRepository;
import mereuta.marian.tennis01.repository.HoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class MetierReservation implements ReservationMetierInterface {

    @Autowired
    private HoraireRepository horaireRepository;

    @Autowired
    EcranMetier ecranMetier;

    private Horaire horaire;
    private List<Horaire> horaires;
    private Ecran ecran;




    public int checkJourSpecial(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){


        //prendd tous les horaires speciaux
        horaires=horaireRepository.findAllByDateHoraireSpecialNotNull();

       int check=0;

        for (Horaire h: horaires){
            if (h.getDateHoraireSpecial().equals(date)){
                check++;
            }
        }

            return check;

    }


    public Horaire checkHoraire(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {



        DayOfWeek day = date.getDayOfWeek();

        String jour = day.name();

        Horaire horaire=new Horaire();



           if(checkJourSpecial(date)==1){

                horaire=horaireRepository.findByDateHoraireSpecial(date);

            }else {

                if (jour == "MONDAY") {
                    horaire = horaireRepository.getOne(1);

                } else if (date.getDayOfWeek().equals("TUESDAY")) {

                    horaire = horaireRepository.getOne(2);

                } else if (jour == "WEDNESDAY") {

                    horaire = horaireRepository.getOne(3);

                } else if (jour == "THURSDAY") {

                    horaire = horaireRepository.getOne(4);

                } else if (jour == "FRIDAY") {

                    horaire = horaireRepository.getOne(5);

                } else if (jour == "SATURDAY") {

                    horaire = horaireRepository.getOne(6);

                } else if (jour == "SUNDAY") {

                    horaire = horaireRepository.getOne(7);

                }
            }

            return horaire;
        }


    public List<LocalTime> transformHeures(Horaire h){

        List<LocalTime> listeHeures=new ArrayList<>();

        int heures= (int) HOURS.between(h.getHeureDebut(), h.getHeureFin());

        System.out.println(heures);

        for(int i=0; i<heures+1; i++){

            LocalTime heureDebut=h.getHeureDebut();

            heureDebut=heureDebut.plusHours(i);

            listeHeures.add(heureDebut);

        }

        return listeHeures;
    }

    public  List<Integer> nombreJours(){

        List<Integer> compteurJours=new ArrayList<>();

        ecran=ecranMetier.showEcran();

        for(int i=0;i<ecran.getTableau().getNombreJours();i++){
            compteurJours.add(i);
        }

        return compteurJours;
    }

    public LocalDate dateDuTableauReservation(Integer compteur){

        LocalDate dateTableau;

        dateTableau=LocalDate.now();

       dateTableau= dateTableau.plusDays(compteur);

        return dateTableau;


    }



}
