package mereuta.marian.tennis01.controller;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
import java.util.Optional;

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
    int addOrUpdate=0;

    model.addAttribute("tarifs",tarifs);
    //permet de faire la difference entre un update et un ajout pour laissr la possibilite de modifier un tarif deja encodé
    model.addAttribute("addOrUpdate",addOrUpdate);

    return "tarifs/listeTarifs";

}


@GetMapping("/form")
    public String  ajouterTarif(Model model ,@RequestParam(value = "id")int id,@RequestParam(value = "addOrUpdate")int addOrUpdate){

    model.addAttribute("tarif",new Tarif());
    model.addAttribute("typeTarif", id);
    model.addAttribute("addOrUpdate",addOrUpdate);

    return "tarifs/addTarif";
}





    @GetMapping("/addTarif")
    public String addTarif(@Valid  @ModelAttribute("tarif") Tarif tarif, BindingResult bindingResult, @RequestParam(value = "typeDeTarif" ) int typeTarif,@RequestParam(value = "addOrUpdate")int addOrUpdate) {


        System.out.println(" je suis le numero de --------------------"+addOrUpdate);

        System.out.println("le type de tarif est "+typeTarif);

       if(typeTarif==1){
           tarifs=tarifMetier.listeTarifsNormaux();



       }else{
            tarifs=tarifMetier.listeTarifsSpeciaux();
           tarifMetier.TarifSpecial(tarif);
       }

        System.out.println(tarifs);



        if (bindingResult.hasErrors()) {
            return "tarifs/addTarif";
        } else if(tarifMetier.intersectionDatesOuDatesEgales(tarif,tarifs)==0 ||
                tarifMetier.intersectionDatesOuDatesEgalesEtWeekEndDifferent(tarif,tarifs)>0 ||
                tarifMetier.dateEgaleWeekEndEgalEtHeureDifferente(tarif,tarifs)==0 ){

            tarifMetier.TarifActif(tarif);



           tarifMetier.addTarif(tarif);

            return "redirect:/tarif/liste";

        }else{
            if(addOrUpdate==2 && tarifMetier.getTarif(tarif.getId())!=null){

                    tarifMetier.addTarif(tarif);
                    return "redirect:/tarif/liste";

            }else {
                return "tarifs/problemeTarif";
            }
        }

    }


    @GetMapping("/delete")
    public String supprimerTarif(@RequestParam(value = "id")Integer id){

        tarif=tarifMetier.getTarif(id);

        tarifMetier.deleteTarif(tarif);

        return "redirect:/tarif/liste";
    }



    @GetMapping("/edit")
    public String getTarif(@RequestParam(name = "id") Integer id, Model model, @RequestParam(name = "typeTarif")int typeTarif,@RequestParam(value = "addOrUpdate")int addOrUpdate) {

        tarif = tarifMetier.getTarif(id);


        model.addAttribute("addOrUpdate",addOrUpdate);
        model.addAttribute("tarif", tarif);
        System.out.println(tarif);
        System.out.println("je suis le tarif"+tarif);
        model.addAttribute("typeTarif", typeTarif);




        return "tarifs/editTarif";
    }



    @GetMapping("/update")
    public String updateTarif(@Valid @ModelAttribute("tarif") Tarif tarif, BindingResult bindingResult, @RequestParam(name = "tarifType",defaultValue = "1") int idTypeTarif ,@RequestParam(value = "addOrUpdate")int addOrUpdate){

        System.out.println("je suis le tari------------------------"+addOrUpdate);
        if (bindingResult.hasErrors()) {


            return "tarifs/editTarif";
        }
        else if(idTypeTarif==3)
            {


            tarifMetier.addTarif(tarif);

            return "redirect:/tarif/liste";
        }else{
            return addTarif(tarif,bindingResult,idTypeTarif,addOrUpdate);
        }

    }

}
