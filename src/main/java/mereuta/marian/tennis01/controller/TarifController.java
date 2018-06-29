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
    public String  ajouterTarif(Model model ,@RequestParam(value = "id")int id){

    model.addAttribute("tarif",new Tarif());
    model.addAttribute("typeTarif", id);

    return "tarifs/addTarif";
}





    @GetMapping("/addTarif")
    public String addHoraire(@Valid  @ModelAttribute("tarif") Tarif tarif, BindingResult bindingResult, @RequestParam(value = "typeDeTarif") int typeTarif) {


       if(typeTarif==1){
           tarifs=tarifMetier.listeTarifsNormaux();
       }else{
            tarifs=tarifMetier.listeTarifsSpeciaux();
           tarifMetier.TarifSpecial(tarif);
       }



        if (bindingResult.hasErrors()) {
            return "tarifs/addTarif";
        } else if(tarifMetier.intersectionDatesOuDatesEgales(tarif,tarifs)==0 ||
                tarifMetier.intersectionDatesOuDatesEgalesEtWeekEndDifferent(tarif,tarifs)>0 ||
                tarifMetier.dateEgaleWeekEndEgalEtHeureDifferente(tarif,tarifs)==0 ){

            tarifMetier.TarifActif(tarif);



           tarifMetier.addTarif(tarif);

            return "redirect:/tarif/liste";

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

    @GetMapping("/addTarifSpecial")
    public String addHoraireSpecial(@Valid  @ModelAttribute("tarif") Tarif tarif, BindingResult bindingResult) {

        tarifs=tarifMetier.listeTarifsNormaux();

        if (bindingResult.hasErrors()) {
            return "tarifs/addTarif";
        } else if(tarifMetier.intersectionDatesOuDatesEgales(tarif,tarifs)==0||
                tarifMetier.dateEgaleWeekEndEgalEtHeureDifferente(tarif,tarifs)==0 ){

            tarifMetier.TarifActif(tarif);
            tarifMetier.addTarif(tarif);
            return "redirect:/tarif/liste";

        }else{
            return "tarifs/problemeTarif";
        }

    }

    @GetMapping("/edit")
    public String getTarif(@RequestParam(name = "id") Integer id, Model model, @RequestParam(name = "typeTarif")int typeTarif) {

        tarif = tarifMetier.getTarif(id);



        model.addAttribute("tarif", tarif);
        System.out.println(tarif);
        model.addAttribute("typeTarif", typeTarif);




        return "tarifs/editTarif";
    }

    @GetMapping("/update")
    public String updateHoraire(@Valid @ModelAttribute("horaire") Tarif tarif, BindingResult bindingResult) {

        System.out.println(tarif);
//        if (bindingResult.hasErrors()) {
//            return "editHoraire";
//        }
 //       else
            {


            tarifMetier.addTarif(tarif);
            return "horaires";
        }

    }

}
