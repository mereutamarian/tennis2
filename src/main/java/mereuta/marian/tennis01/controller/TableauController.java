package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Ecran;
import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.Tableau;
import mereuta.marian.tennis01.repository.HoraireRepository;
import mereuta.marian.tennis01.service.EcranMetier;
import mereuta.marian.tennis01.service.TableauMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tableau")
public class TableauController {

    @Autowired
    TableauMetier tableauMetier;

    private List<Tableau> tableaux;

    Tableau tableau;

    @Autowired
    EcranMetier ecranMetier;

    @GetMapping("/liste")
public String tableau(Model model){

    tableaux=tableauMetier.showTableaux();

    model.addAttribute("liste", tableaux);



    return "tableaux/listeTableaux";
}

@GetMapping("/form")
    public String addTableau(Model model){

    model.addAttribute("tableau", new Tableau());

    return "tableaux/addTableau";

}

    @PostMapping("/add")
    public String addTableau(@Valid @ModelAttribute("tableau") Tableau tableau, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "tableaux/addTableau";
        } else {

           tableauMetier.addTableau(tableau);
            return "redirect:/tableau/liste";
        }

    }

    @GetMapping("/edit")
    public String chargerTableau(@RequestParam (name = "id") Integer id, Model model) {

        tableau=tableauMetier.chargeTableau(id);


        model.addAttribute("tableau", tableau);


        return "tableaux/editTableau";
    }


    @GetMapping("/update")
    public String updateHoraire(@Valid @ModelAttribute("tableau") Tableau tableau, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "editTableau";
        } else {


            tableauMetier.addTableau(tableau);
            return "redirect:/tableau/liste";
        }

    }

    @GetMapping("/delete")
    public String deleteTableau( @RequestParam(name = "id") Integer id) {

       tableau=tableauMetier.chargeTableau(id);



       tableauMetier.deleteTableau(tableau);



        return "redirect:/tableau/liste";
    }




    @Autowired
    HoraireRepository horaireRepository;

    @GetMapping("/tester")
    public  String test(){

        Horaire horaire= horaireRepository.getOne(71);


        return "tableaux/listeTableaux";
    }


}
