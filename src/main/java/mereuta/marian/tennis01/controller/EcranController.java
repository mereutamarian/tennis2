package mereuta.marian.tennis01.controller;


import mereuta.marian.tennis01.model.Ecran;
import mereuta.marian.tennis01.model.Tableau;
import mereuta.marian.tennis01.service.EcranMetier;
import mereuta.marian.tennis01.service.TableauMetier;
import mereuta.marian.tennis01.service.TarifMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("ecran")
public class EcranController {

    @Autowired
    EcranMetier ecranMetier;
    @Autowired
    TableauMetier tableauMetier;

    private Ecran  ecran;
    private List<Tableau> tableaux;
    private Tableau tableau;


    @GetMapping("ecran")
    public String showEcran(Model model){

        ecran =ecranMetier.showEcran();

        model.addAttribute("ecran", ecran);


        return "ecran/ecran";

    }

    @GetMapping("/edit")
    public String chargerTableau(Model model) {


        tableaux=tableauMetier.showTableaux();

        model.addAttribute("tableau", new Tableau());
        model.addAttribute("listetableaux",tableaux);
        model.addAttribute("ecran", ecran);



        return "ecran/editEcran";
    }

    @GetMapping("/update")
    public String attribuerTableau(@RequestParam(value = "idTableau") Integer idTableau){


        tableau=tableauMetier.getTableau(idTableau);

        ecranMetier.setEcran(tableau);



        return "redirect:/ecran/ecran";

    }


}
