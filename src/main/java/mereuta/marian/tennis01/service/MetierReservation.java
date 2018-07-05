package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Ecran;
import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.Reservation;
import mereuta.marian.tennis01.model.Tarif;
import mereuta.marian.tennis01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    TarifRepository tarifRepository;

    @Autowired
    ReservationRepository reservationRepository;

    private Horaire horaire;
    private List<Horaire> horaires;
    private Ecran ecran;
    private List<Tarif> tarifs;




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

                } else if (jour=="TUESDAY") {

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

        for(int i=0; i<heures+2; i++){

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

@Override
    public LocalTime getSecondHeure(Integer indexDate2, List<LocalTime> listeHeureues) {

       return listeHeureues.get(indexDate2);
    }

    @Override
    public LocalDateTime constructionDateTime(LocalDate date, LocalTime heure) {


        return LocalDateTime.of(date, heure);

    }

    @Override
    public Tarif recupereTarif(LocalDate date, LocalTime heure1) {

        tarifs=tarifRepository.findAll();

        DayOfWeek dayOfWeek= date.getDayOfWeek();
        String jour=dayOfWeek.name();

        Tarif tarifFinal= new Tarif();


        for (Tarif tarif: tarifs){
            if(     date.isAfter(tarif.getDateDebut()) &&
                    date.isBefore(tarif.getDateFin())  &&
                    heure1.isAfter(tarif.getHeureDebut()) &&
                    heure1.isBefore(tarif.getHeureFin()) &&
                    tarif.isTarifSpecial() && tarif.isActif() && !tarif.isTarifParDefaut()
                    ){
                tarifFinal= tarif;
            }else if(
                    jour=="SATURDAY" || jour=="SUNDAY" &&
                    date.isAfter(tarif.getDateDebut()) &&
                    date.isBefore(tarif.getDateFin())  &&
                    heure1.isAfter(tarif.getHeureDebut()) &&
                    heure1.isBefore(tarif.getHeureFin()) &&
                    !tarif.isTarifSpecial() && tarif.isActif() && !tarif.isTarifParDefaut()&& tarif.isWeekend()
                    ){

                    tarifFinal= tarif;
            }else if (

                   jour !="SATURDAY" || jour !="SUNDAY" &&
                            date.isAfter(tarif.getDateDebut()) &&
                            date.isBefore(tarif.getDateFin())  &&
                            heure1.isAfter(tarif.getHeureDebut()) &&
                            heure1.isBefore(tarif.getHeureFin()) &&
                            !tarif.isTarifSpecial() && tarif.isActif() && !tarif.isTarifParDefaut()&& !tarif.isWeekend()

                    ){
                tarifFinal=tarif;

            }else {
                tarifFinal= tarifRepository.getOne(83);
            }

        }

        return tarifFinal;
    }

    public void addReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }


}
