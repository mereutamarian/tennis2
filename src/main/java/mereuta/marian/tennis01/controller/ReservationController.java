package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.*;
import mereuta.marian.tennis01.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private MetierReservation metierReservation;
    @Autowired
    private MetierHoraire metierHoraire;
    @Autowired
    private MetierTerrain metierTerrain;
    @Autowired
    UtilisateurMetier utilisateurMetier;


    private Horaire horaire;
    private LocalDate date = LocalDate.now();
    private List<Reservation> reservations;
    private Reservation reservation;

    @GetMapping("/tableau")
    public String tableau(Model model,
                          @RequestParam(name = "page", defaultValue = "0") Integer p) {


        List<Integer> compteurJours;
        List<LocalTime> heures;



        LocalDate dateResa = metierReservation.dateDuTableauReservation(p);

        //l'horaire qui va être affiché
        horaire = metierReservation.checkHoraire(dateResa);



        //liste des heures qui vont etre disponibles pour la reservation
        heures = metierReservation.transformHeures(horaire);

        System.out.println("heures " + heures);


        //nombre des jours qui vont pouvoir être faites les reservations
        compteurJours = metierReservation.nombreJours();

        System.out.println(compteurJours);

        //chaque numero du tableau de reservation correspond à l'index du tableau des dates





        reservations = metierReservation.getReservationList();




        System.out.println("la taille de la lisste des heures est :"+heures.size());


        int iterator = 0;
        int iteratorOption=0;


        model.addAttribute("iterator", iterator);
        model.addAttribute("date", dateResa);
        model.addAttribute(horaire);
        model.addAttribute("compteur", compteurJours);
        model.addAttribute("heures", heures);
        model.addAttribute("reservations", reservations);
        model.addAttribute("iteratorOption",iteratorOption);



        return "reservation/tableauReservations";
    }

    @GetMapping("/addResa")
    public String insererReservation(@RequestParam(value = "heure") LocalTime heure1,
                                     @RequestParam(value = "terrain") Integer idTerrain,
                                     @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                     @RequestParam(value = "listeHeures") List<LocalTime> listeHeureues,
                                     @RequestParam(value = "idUtilisateur")Integer idUtilisateur,
                                     @RequestParam(value = "optionalOrReservation")Integer optionalOrReservation) {


        //recuperation de la deuxieme heure
        LocalTime heure2 = metierReservation.getSecondHeure(heure1, listeHeureues);


        //get terrain by id
        Terrain terrain = metierTerrain.getTerrain(idTerrain);

        //recuperer Tarif

        //on recupere le tarif qui correspond a la date de la reservation
        Tarif tarif = metierReservation.recupereTarif(date, heure1);


        Utilisateur utilisateur = utilisateurMetier.getUtilisateur(idUtilisateur);



         if (optionalOrReservation==1){
             reservation = new Reservation(LocalDateTime.now(), date, heure1, heure2, true,false, utilisateur, terrain, tarif);
         }else {
             reservation = new Reservation(LocalDateTime.now(), date, heure1, heure2, false,true, utilisateur, terrain, tarif);



                 //je dois faire une methode qui va checker dans la db si cette option n'est pas déja mise
         }



        //check si l'heure de la résa est après l'heure currente

        boolean checkHeureresa = metierReservation.checkIfDateEtHeureInferieureMaintenant(date, heure1);
        boolean checkCredit = metierReservation.checkIfCreditOk(utilisateur, tarif, heure1, heure2);

        System.out.println(" je suis la fonction de check" + checkHeureresa);


        if (checkHeureresa == false && checkCredit == true) {
            metierReservation.addReservation(reservation);
            return "redirect:/reservation/tableau";
        } else if (checkHeureresa == true) {
            return "reservation/checkAddResa";
        } else {
            return "reservation/creditInsuffisant";
        }
    }



    @GetMapping("/cancelResa")
    public String annulerReservation(@RequestParam(value = "idResa") Integer idReservation) {


        reservation = metierReservation.getReservation(idReservation);

        boolean checkCancelOk = metierReservation.checkIfCancelBefore24Hours(reservation.getDateReservation(), reservation.getHeureDebut());
        if (checkCancelOk == true) {

            metierReservation.annulerReservation(reservation);
            return "redirect:/reservation/tableau";
        } else {
            return "reservation/checkIfCancelationOk";
        }


    }

    @GetMapping("/heuresAnnulationForm")
    public String formHeuresAnnulation() {

        return "reservation/formHeuresAnnulation";
    }


    @PostMapping("/heuresAnnulation")
    public String heuresAnulationReservation(@RequestParam(name = "nombreHeures") int nombreHeures) {

        metierReservation.heuresAnnulerReservation(nombreHeures);



        return "redirect:/reservation/tableau";
    }






    @GetMapping("/joursReservationForm")
    public String joursReservationForm() {

        return "reservation/formJoursReservation";
    }


    @PostMapping("/joursReservation")
    public String joursReservation(@RequestParam(name = "nombreJours") int nombreJours) {

        metierReservation.joursReservation(nombreJours);



        return "redirect:/reservation/tableau";
    }




}

