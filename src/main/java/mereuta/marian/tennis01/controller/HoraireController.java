package mereuta.marian.tennis01.controller;


import mereuta.marian.tennis01.model.Horaire;

import mereuta.marian.tennis01.model.MesureInterval;
import mereuta.marian.tennis01.model.Terrain;
import mereuta.marian.tennis01.service.MetierHoraire;
import mereuta.marian.tennis01.service.MetierMesure;
import mereuta.marian.tennis01.service.MetierTerrain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/horaire")
public class HoraireController {


    @Autowired
    private MetierHoraire metierHoraire;

    @Autowired
    private MetierTerrain metierTerrain;

    @Autowired
    MetierMesure metierMesure;

    private List<Horaire> horaires;
    private Horaire horaire;
    private List<Terrain> terrains;
    private List<MesureInterval> intervals;
    private MesureInterval mesureInterval;

    @GetMapping("/liste")
    public String afficheHoraire(Model model) {
        horaires = metierHoraire.afficheHoraires();
        System.out.println(horaires);

        model.addAttribute("liste", horaires);

        return "horaires";
    }

    @GetMapping("/form")
    public String formHoraire(Model model) {

        model.addAttribute("horaire", new Horaire());
        return "addHoraire";
    }

    @GetMapping("/horaireSpecial")
    public String formHoraireSpecial(Model model) {

        terrains = metierTerrain.showTerrain();
        intervals=metierMesure.showIntervales();

        model.addAttribute("terrains", terrains);
        model.addAttribute("intervals",intervals);

        model.addAttribute("horaire", new Horaire());
        return "addHoraireSpecial";
    }


    @GetMapping("/add")
    public String addHoraire(@Valid @ModelAttribute("horaire") Horaire horaire, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addHoraire";
        } else {

            metierHoraire.addHoraire(horaire);
            return "redirect:horaires";
        }

    }

    @GetMapping("/addHoraireSpecial")
    public String addHoraireSpecial(@Valid @ModelAttribute("horaire") Horaire horaire, BindingResult bindingResult, @RequestParam(name = "idTerrain", required = false) List<Integer> idT,

                                    @RequestParam(name = "idInterval", required = false) Integer id) {


        if (bindingResult.hasErrors()) {
            return "addHoraireSpecial";
        } else {

            terrains = metierTerrain.attribuerTerrain(idT);
            mesureInterval=metierMesure.getMesure(id);

            horaire = metierHoraire.attribuerTerrainHoraire(horaire, terrains);
            horaire=metierHoraire.attribuerIntervalMesure(horaire, mesureInterval);


            metierHoraire.addHoraire(horaire);

            return "redirect:/horaire/liste";
        }

    }

    @GetMapping("/edit")
    public String getHoraire(@RequestParam(name = "id") Integer id, Model model) {

        horaire = metierHoraire.getHoraire(id);
        terrains= metierTerrain.showTerrain();


        model.addAttribute("horaire", horaire);
        model.addAttribute("terrains", terrains);



        return "editHoraire";
    }

    @GetMapping("/editTerrain")
    public String AttribuerTerrain(@RequestParam(name = "id") Integer id, Model model) {

        horaire = metierHoraire.getHoraire(id);
        terrains = metierTerrain.showTerrain();

        model.addAttribute("horaire", horaire);
        model.addAttribute("terrains", terrains);

        return "editTerrainHoraire";
    }


    @GetMapping("/update")
    public String updateHoraire(@Valid @ModelAttribute("horaire") Horaire horaire, BindingResult bindingResult) {

        System.out.println(horaire);
        if (bindingResult.hasErrors()) {
            return "editHoraire";
        } else {


            metierHoraire.addHoraire(horaire);
            return "horaires";
        }

    }


    @GetMapping("/updateTerrain")
    public String updateTerrain(@RequestParam(name = "id") Integer id, @RequestParam(name = "idTerrain", required = false) List<Integer> idT) throws Exception {

        List<Terrain> listeChoix = new ArrayList<>();


        horaire = metierHoraire.getHoraire(id);

        try {
            listeChoix = metierTerrain.attribuerTerrain(idT);
        } catch (Exception e) {

        }

        // s'il n'y a pas  de terrain selectioné on garde les anciens terrains et on ne fais pas le changement
        if (idT != null) {
            horaire.setTerrains(listeChoix);

            metierHoraire.update(horaire);
        }

        return "horaires";

    }


    @GetMapping("/delete")
    public String deleteHoraire(@RequestParam(name = "id") Integer id) {

        horaire = metierHoraire.getHoraire(id);


        metierHoraire.deleteHoraire(horaire);


        return "redirect:/horaire/liste";
    }

    @GetMapping("/editMesure")
    public String AttribuerInterval(@RequestParam(name = "id") Integer id, Model model) {

        horaire = metierHoraire.getHoraire(id);
        intervals = metierMesure.showIntervales();

        model.addAttribute("horaire", horaire);
        model.addAttribute("intervals", intervals);

        return "editIntervalHoraire";
    }

    @GetMapping("/updateInterval")
    public String updateInterval(@RequestParam(name = "id") Integer id, @RequestParam(name = "idInterval", required = false) Integer idInterval) {


        horaire = metierHoraire.getHoraire(id);
        mesureInterval = metierMesure.getMesure(idInterval);


        // s'il n'y a pas  de terrain selectioné on garde les anciens terrains et on ne fais pas le changement
        if (idInterval != null) {
            horaire = metierHoraire.attribuerIntervalMesure(horaire, mesureInterval);

            metierHoraire.update(horaire);
        }

        return "redirect:/horaire/liste";

    }


}
