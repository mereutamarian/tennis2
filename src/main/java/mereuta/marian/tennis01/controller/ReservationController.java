package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.service.MetierHoraire;
import mereuta.marian.tennis01.service.MetierReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private MetierReservation metierReservation;
    @Autowired
    private MetierHoraire metierHoraire;


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

        System.out.println("---------------------------");
        System.out.println(heure1);
        System.out.println("terrain" + idTerrain);
        System.out.println(date);
        System.out.println("index" + indexDate2);
        System.out.println(listeHeureues);
        System.out.println("-------------------------------------------");


        return "redirect:/reservation/tableau";
    }


}

