package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.*;
import mereuta.marian.tennis01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class MetierReservation implements ReservationMetierInterface {

    @Autowired
    private HoraireRepository horaireRepository;


    @Autowired
    TarifRepository tarifRepository;

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    private Horaire horaire;
    private List<Horaire> horaires;

    private Utilisateur utilisateur;

    private List<Tarif> tarifs;
    private int nombreHeuresAvantAnnulation = 24;
    private int nombreJoursAvantResa = 7;


    @Override
    public int checkJourSpecial(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {


        //prendd tous les horaires speciaux
        horaires = horaireRepository.findAllByDateHoraireSpecialNotNull();

        int check = 0;

        for (Horaire h : horaires) {
            if (h.getDateHoraireSpecial().equals(date)) {
                check++;
            }
        }

        return check;

    }

    @Override
    public Horaire checkHoraire(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        System.out.println("je suis la date " + date);

        DayOfWeek day = date.getDayOfWeek();

        String jour = day.name();

        Horaire horaire = new Horaire();

        // System.out.println("je suis le jour "+jour);


        if (checkJourSpecial(date) == 1) {

            horaire = horaireRepository.findByDateHoraireSpecial(date);

        } else {

            if (jour == "MONDAY") {
                horaire = horaireRepository.getOne(1);

            } else if (jour == "TUESDAY") {

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

    @Override
    public List<LocalTime> transformHeures(Horaire h) {

        System.out.println("je suis l'horaire  " + h);

        System.out.println("je suis l'interval----" + h.getMesureInterval().getMesure_interval());

        List<LocalTime> listeHeures = new ArrayList<>();

        int heures = (int) HOURS.between(h.getHeureDebut(), h.getHeureFin());
        System.out.println("heures premier" + heures);

        if (h.getMesureInterval().getMesure_interval() == 30) {
            heures = heures + heures;
        } else {
            heures = heures + 0;
        }


        System.out.println("heures 2eme" + heures);
        System.out.println(heures + "je suis les heures");

        for (int i = 0; i < heures + 2; i++) {

            LocalTime heureDebut = h.getHeureDebut();


            if (h.getMesureInterval().getMesure_interval() == 30) {
                heureDebut = heureDebut.plusMinutes(30 * i);
            } else {
                heureDebut = heureDebut.plusHours(i);
            }

            listeHeures.add(heureDebut);

        }

        return listeHeures;
    }

    @Override
    public List<Integer> nombreJours() {

        List<Integer> compteurJours = new ArrayList<>();


        for (int i = 0; i < nombreJoursAvantResa; i++) {
            compteurJours.add(i);
        }

        return compteurJours;
    }

    @Override
    public LocalDate dateDuTableauReservation(Integer compteur) {

        LocalDate dateTableau;

        dateTableau = LocalDate.now();

        dateTableau = dateTableau.plusDays(compteur);

        return dateTableau;


    }

    @Override
    public LocalTime getSecondHeure(LocalTime heure1, List<LocalTime> listeHeureues) {

        int indexheure2 = listeHeureues.indexOf(heure1);
        indexheure2 = indexheure2 + 1;

        return listeHeureues.get(indexheure2);
    }

    public Tarif getTarifSpecial(LocalDate date , LocalTime heure1){

        Tarif tarif=null;

        tarifs=tarifRepository.listeTarifsSpeciaux();

        for(Tarif t: tarifs){

            if(date.isAfter(t.getDateDebut()) &&date.isBefore( t.getDateFin()) && heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    || date.isAfter(t.getDateDebut()) && date.isEqual(t.getDateFin()) && heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    || date.isEqual(t.getDateDebut()) && date.isBefore(t.getDateFin())&& heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    ){
                tarif=t;
            }

        }

        return tarif;
    }

    public Tarif getTarifNormalSemaine(LocalDate date, LocalTime heure1){

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String jour = dayOfWeek.name();



        tarifs=tarifRepository.listeTarifsNormauxSemaine();

        System.out.println(" la liste des tarifs semaine "+tarifs);

        Tarif tarif=null;


        for (Tarif t: tarifs){
            if(jour !="SATURDAY" && jour != "SUNDAY" &&
                    date.isAfter(t.getDateDebut()) &&date.isBefore( t.getDateFin()) && heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    || date.isAfter(t.getDateDebut()) && date.isEqual(t.getDateFin()) && heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    || date.isEqual(t.getDateDebut()) && date.isBefore(t.getDateFin())&& heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    ){


                    tarif=t;


            }else {
                tarif=null;
            }
        }

        return tarif;
    }

    public Tarif getTarifNormalWeekEnd(LocalDate date, LocalTime heure1){

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String jour = dayOfWeek.name();

        tarifs=tarifRepository.listeTarifsNormauxWeekEnd();

        Tarif tarif=null;


        for (Tarif t: tarifs){
            if(jour=="SATURDAY" || jour== "SUNDAY" &&
                    date.isAfter(t.getDateDebut()) &&date.isBefore( t.getDateFin()) && heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    || date.isAfter(t.getDateDebut()) && date.isEqual(t.getDateFin()) && heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    || date.isEqual(t.getDateDebut()) && date.isBefore(t.getDateFin())&& heure1.isAfter(t.getHeureDebut()) && heure1.isBefore(t.getHeureFin()) || heure1.isAfter(t.getHeureDebut()) && heure1.equals(t.getHeureFin()) || heure1.equals(t.getHeureDebut())&& heure1.isBefore(t.getHeureFin())
                    ){

                            tarif=t;

            }
        }

        return tarif;
    }

    @Override
    public Tarif recupereTarif(LocalDate date, LocalTime heure1) {

        Tarif tarif;


       Tarif  tarifDefaut=tarifRepository.findByTarifParDefautTrue();



        if(getTarifSpecial(date,heure1)!=null){
                tarif=getTarifSpecial(date,heure1);

            }else if(getTarifNormalWeekEnd(date, heure1)!=null){
                        tarif=getTarifNormalWeekEnd(date, heure1);

            }else if(getTarifNormalSemaine(date, heure1)!=null){
                tarif=getTarifNormalSemaine(date,heure1);
        }   else {
            tarif=tarifDefaut;
        }







       return tarif;

    }




    @Override
    public Reservation getReservation(Integer idReservation) {
        return reservationRepository.getOne(idReservation);
    }

    @Override
    public void annulerReservation(Reservation reservation) {

        reservation.setActif(false);
        reservationRepository.save(reservation);


        //on recupere l'utilisatuer de la resa et on lui redonne le credit

        System.out.println("je suis l'id" + reservation.getUtilisateur().getId());
        utilisateur = utilisateurRepository.getOne(reservation.getUtilisateur().getId());

        utilisateur.setCredit(utilisateur.getCredit() + reservation.getTarif().getPrix());

        utilisateurRepository.save(utilisateur);


    }

    @Override
    public boolean checkIfDateEtHeureInferieureMaintenant(LocalDate date, LocalTime heure1) {

        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime dateHeureResa = LocalDateTime.of(date, heure1);

        if (maintenant.isAfter(dateHeureResa)) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean checkIfCancelBefore24Hours(LocalDate dateReservation, LocalTime heureDebut) {

        LocalDateTime resaHeure = LocalDateTime.of(dateReservation, heureDebut);

        // check if 24 heures de difference entre la date de la reservation et la date d'aujourd'hui
        long hours = ChronoUnit.HOURS.between(resaHeure, LocalDateTime.now());

        System.out.println("je suis les heures" + hours);


        if (hours <= -nombreHeuresAvantAnnulation) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean checkIfCreditOk(Utilisateur utilisateur, Tarif tarif, LocalTime heure1, LocalTime heure2) {

        //on regarde si la reservation a été effectuée pour une heure ou pour une demiheure
        Duration duration = Duration.between(heure1, heure2);

        float prixTarif = tarif.getPrix();

        //1800 = une demiheure en secondes
        //si resa d'une demi heure on doit diviser le tarif par 2 car le tarif est fait pour une heure
        if (duration.getSeconds() == 1800) {
            prixTarif = prixTarif / 2;
        }

        System.out.println("je suis le prix de la resa" + tarif.getPrix());
        System.out.println("je suis le credit de l'utilisateur" + utilisateur.getCredit());


        if (utilisateur.getCredit() >= prixTarif) {


            utilisateur.setCredit(utilisateur.getCredit() - prixTarif);


            System.out.println("je suis le tarif  de " + duration.getSeconds() + " secondes");

            utilisateurRepository.save(utilisateur);

            return true;


        } else {
            return false;
        }

    }

    @Override
    public void heuresAnnulerReservation(int nombreHeures) {
        nombreHeuresAvantAnnulation = nombreHeures;
    }

    @Override
    public void joursReservation(int nombreJours) {
        nombreJoursAvantResa = nombreJours;
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    LocalDate today;


    @Override
    public List<Reservation> getReservationList() {

        // on met la date d'ouajourd'hui a minuit
        today = LocalDate.now().minusDays(1);


        System.out.println("-----------------------");
        System.out.println("la date est " + today);
        System.out.println("---------------------------");


        return reservationRepository.findByDateReservationIsGreaterThanAndActifTrue(today);
    }

    @Override
    public List<Reservation> listeReservationOptional() {
        return reservationRepository.findByOptionalTrue();
    }

    @Override
    public void modifierTerrain(Reservation reservation, Terrain terrain) {
        reservation.setTerrain(terrain);
        reservationRepository.save(reservation);
    }




}
