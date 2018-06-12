package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.service.MetierHoraire;
import mereuta.marian.tennis01.service.MetierReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

   @Autowired
   private  MetierReservation metierReservation;
   @Autowired
   private MetierHoraire metierHoraireInterface;

   private Horaire horaire;

        @GetMapping("/tableau")
    public String tableau(){

    horaire=metierReservation.checkHoraire(LocalDate.now());

    List<LocalTime> heures;

    heures=metierReservation.transformHeures(horaire);


            return "horaires";
        }



    @GetMapping("/check")
    public String check(){



        int i=metierReservation.checkJourSpecial(LocalDate.now() );

        System.out.println(i);

        return "horaires";
    }

}

