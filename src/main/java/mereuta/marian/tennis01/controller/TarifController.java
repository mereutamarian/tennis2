package mereuta.marian.tennis01.controller;


import mereuta.marian.tennis01.model.Tarif;
import mereuta.marian.tennis01.service.TarifMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tarif")
public class TarifController {

    @Autowired
    TarifMetier tarifMetier;

    List<Tarif> tarifs;
    Tarif tarif;

@GetMapping("/liste")
public String listeTarifs(Model model){

    tarifs=tarifMetier.listeTarifs();

    model.addAttribute("tarifs",tarifs);

    return "tarifs/listeTarifs";

}


@GetMapping("/form")
    public String  ajouterTarif(Model model){

    model.addAttribute("tarif",new Tarif());

    return "tarifs/addTarif";
}

    @GetMapping("/addTarif")
    public String addHoraire(@Valid  @ModelAttribute("tarif") Tarif tarif, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "tarifs/addTarif";
        } else if(tarifMetier.intersectionDates(tarif)==0) {

            tarifMetier.TarifActif(tarif);
           tarifMetier.addTarif(tarif);
            return "redirect: /tarif/liste";

        }else{
            return "tarifs/problemeTarif";
        }

    }


    @GetMapping("/delete")
    public String supprimerTarif(@RequestParam(value = "id")Integer id){

        tarif=tarifMetier.getTarif(id);

        tarifMetier.deleteTarif(tarif);

        return "redirect:/tarif/liste";
    }

}
