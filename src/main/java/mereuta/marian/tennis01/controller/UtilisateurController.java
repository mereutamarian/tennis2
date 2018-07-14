package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Utilisateur;
import mereuta.marian.tennis01.service.UtilisateurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurMetier utilisateurMetier;


@GetMapping("/inscription")
    public String registerForm(Model model){

        model.addAttribute("utilisateur", new Utilisateur());

        return "utilisateur/formRegister";
    }


    @PostMapping("/addUtilisateur")
    public String addUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult,Model model){


    if(bindingResult.hasErrors()){
        return "utilisateur/formRegister";
    }


    utilisateurMetier.creerUtilisateur(utilisateur);

    return "utilisateur/utilisateurAjoute";



    }
}
