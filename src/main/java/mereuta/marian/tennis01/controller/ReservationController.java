package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.*;
import mereuta.marian.tennis01.repository.UtilisateurRepository;
import mereuta.marian.tennis01.service.MetierHoraire;
import mereuta.marian.tennis01.service.MetierReservation;
import mereuta.marian.tennis01.service.MetierTerrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    //provisoire
    @Autowired
    UtilisateurRepository utilisateurRepository;


    private Horaire horaire;
    private LocalDate date = LocalDate.now();

    @GetMapping("/tableau")
    public String tableau(Model model,
                          @RequestParam(name = "page", defaultValue = "0") Integer p) {


        List<Integer> compteurJours;
        List<LocalTime> heures;

        //l'horaire qui va être affiché
        horaire = metierReservation.checkHoraire(date);

        System.out.println(horaire);


        //liste des heures qui vont etre disponibles pour la reservation
        heures = metierReservation.transformHeures(horaire);

        System.out.println("heures " + heures);


        //nombre des jours qui vont pouvoir être faites les reservations
        compteurJours = metierReservation.nombreJours();

        System.out.println(compteurJours);

        //chaque numero du tableau de reservation correspond à l'index du tableau des dates
        LocalDate dateResa = metierReservation.dateDuTableauReservation(p);

        System.out.println(dateResa);

        model.addAttribute("date", dateResa);
        model.addAttribute(horaire);
        model.addAttribute("compteur", compteurJours);
        model.addAttribute("heures", heures);


        return "reservation/tableauReservations";
    }

    @GetMapping("/addResa")
    public String insererReservation(@RequestParam(value = "heure") LocalTime heure1,
                                     @RequestParam(value = "terrain") Integer idTerrain,
                                     @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                     @RequestParam(value = "date2") Integer indexDate2,
                                     @RequestParam(value = "listeHeures") List<LocalTime> listeHeureues) {



        //recuperation de la deuxieme heure
        LocalTime heure2=metierReservation.getSecondHeure(indexDate2, listeHeureues);

        //creation date1 et date2 pour la db
        LocalDateTime date1=metierReservation.constructionDateTime(date, heure1);
        LocalDateTime date2=metierReservation.constructionDateTime(date, heure2);

       //get terrain by id
        Terrain terrain=metierTerrain.getTerrain(idTerrain);

        //recuperer Tarif
        System.out.println("je suis la date"+date);


        //on recupere le tarif qui correspond a la date de la reservation
        Tarif tarif=metierReservation.recupereTarif(date,heure1);

        System.out.println("je suis le tarif "+tarif);

        float prix=tarif.getPrix();


//provisoire
      //  Utilisateur utilisateur= utilisateurRepository.getOne(1);

        Integer idUtilisateur=1;

        //Reservation reservation=







        return "redirect:/reservation/tableau";
    }


}

