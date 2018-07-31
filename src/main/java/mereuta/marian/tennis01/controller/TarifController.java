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
import java.time.LocalDate;
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
            //le numero deux c'est update et 1 c'est add
            //on check si l'id du tarif existe , s'il existe veut dire que l'id du tarif est deja encodé dont 'est un update
            if(addOrUpdate==2 && tarifMetier.getTarif(tarif.getId())!=null &&
                    tarifMetier.intersectionDatesOuDatesEgales(tarif,tarifs)>1 && tarifMetier.intersectionDatesOuDatesEgales(tarif,tarifs)<2 ||
                    tarifMetier.intersectionDatesOuDatesEgalesEtWeekEndDifferent(tarif,tarifs)==0 ||
                    tarifMetier.dateEgaleWeekEndEgalEtHeureDifferente(tarif,tarifs)>1 && tarifMetier.dateEgaleWeekEndEgalEtHeureDifferente(tarif,tarifs)<2){


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


        if (bindingResult.hasErrors()) {


            return "tarifs/editTarif";
        }

        else if(idTypeTarif==3 ){


            return "redirect:/tarif/liste";
        }else{
            return addTarif(tarif,bindingResult,idTypeTarif,addOrUpdate);
        }

    }

    @GetMapping("/grille")
    public String listTarifsClients(Model model){

        List<Tarif>tarifsNormauxSemaine=tarifMetier.tarifsNormauxSemaine();
        List<Tarif>tarifsNormauxWeekend=tarifMetier.tarifsNormauxWeekend();
        List<Tarif>tarifsSpeciaux=tarifMetier.listeTarifsSpeciaux();
        Tarif tarifParDefaut=tarifMetier.tarifParDefaut();
        int annee=tarifMetier.getAnneCourante();
        int anneeProchaine=tarifMetier.getAnneeprochaine(annee);


        model.addAttribute("annee", annee);
        model.addAttribute("tarifsNormaux", tarifsNormauxSemaine);
        model.addAttribute("anneeProchaine", anneeProchaine);
        model.addAttribute("tarifsNormauxWeekend", tarifsNormauxWeekend);
        model.addAttribute("tarifsSpeciaux", tarifsSpeciaux);
        model.addAttribute("tarifParDefaut",tarifParDefaut);

        return "tarifs/grilleTarifClients";
    }

}
