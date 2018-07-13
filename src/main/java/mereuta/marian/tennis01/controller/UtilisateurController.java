package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {


@GetMapping("/inscription")
    public String registerForm(Model model){

        model.addAttribute("utilisateur", new Utilisateur());

        return "utilisateur/formRegister";
    }


    @PostMapping("/addUtilisateur")
    public String addUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult){


    if(bindingResult.hasErrors()){
        return "utilisateur/formRegister";
    }else {
        System.out.println(utilisateur);
        return null;
    }

    }
}
