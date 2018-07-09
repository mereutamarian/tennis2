package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.*;
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
import java.time.temporal.ChronoUnit;

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
    @Autowired
    UtilisateurRepository utilisateurRepository;

    private Horaire horaire;
    private List<Horaire> horaires;
    private Ecran ecran;
    private List<Tarif> tarifs;
    private int nombreHeuresAvantAnnulation=24;




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

    @Override
    public Reservation getReservation(Integer idReservation) {
        return reservationRepository.getOne(idReservation);
    }

    @Override
    public void annulerReservation(Reservation reservation) {

        reservation.setActif(false);
        reservationRepository.save(reservation);
    }

    @Override
    public boolean checkIfDateEtHeureInferieureMaintenant(LocalDate date, LocalTime heure1) {

        LocalDateTime maintenant=LocalDateTime.now();
        LocalDateTime dateHeureResa=LocalDateTime.of(date, heure1);

        if(maintenant.isAfter(dateHeureResa)){
            return true;
        }else {
            return false;
        }


    }

    @Override
    public boolean checkIfCancelBefore24Hours(LocalDate dateReservation, LocalTime heureDebut) {

        LocalDateTime resaHeure=LocalDateTime.of(dateReservation,heureDebut);

        // check if 24 heures de difference entre la date de la reservation et la date d'aujourd'hui
        long hours=ChronoUnit.HOURS.between(resaHeure,LocalDateTime.now());

        System.out.println("je suis les heures"+hours);



        if (hours<= -nombreHeuresAvantAnnulation){
            return true;
        }else {
            return  false;
        }

    }

    @Override
    public boolean checkIfCreditOk(Utilisateur utilisateur, Tarif tarif) {

        System.out.println("je suis le prix de la resa"+tarif.getPrix());
        System.out.println("je suis le credit de l'utilisateur"+utilisateur.getCredit());



        if(utilisateur.getCredit()>=tarif.getPrix()){

            utilisateur.setCredit(utilisateur.getCredit()-tarif.getPrix());
            utilisateurRepository.save(utilisateur);

            return true;


        }else {
            return false;
        }

    }

    @Override
    public void heuresAnnulerReservation(int nombreHeures) {
        nombreHeuresAvantAnnulation=nombreHeures;
    }

    public void addReservation(Reservation reservation){
        reservationRepository.save(reservation);
    }

    LocalDate today;


    public List<Reservation> getReservationList(){

        // on met la date d'ouajourd'hui a minuit
        today=LocalDate.now().minusDays(1);


        System.out.println("-----------------------");
        System.out.println("la date est " +today);
        System.out.println("---------------------------");


        return reservationRepository.findByDateReservationIsGreaterThanAndActifTrue(today);
    }




}
