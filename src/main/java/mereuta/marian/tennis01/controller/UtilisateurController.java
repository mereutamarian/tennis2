package mereuta.marian.tennis01.controller;

import mereuta.marian.tennis01.model.Utilisateur;
import mereuta.marian.tennis01.service.MailMetier;
import mereuta.marian.tennis01.service.UtilisateurMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    @Autowired
    UtilisateurMetier utilisateurMetier;
    @Autowired
    MailMetier mailMetier;



    @GetMapping("/inscription")
    public String registerForm(Model model) {

        model.addAttribute("utilisateur", new Utilisateur());

        return "utilisateur/formRegister";
    }


    @PostMapping("/addUtilisateur")
    public String addUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "utilisateur/formRegister";
        }


        utilisateurMetier.creerUtilisateur(utilisateur);

        return "utilisateur/utilisateurAjoute";


    }

    @GetMapping("/loginForm")
    public String showLogin(Model model, @RequestParam(name = "email", required = false) String email, @RequestParam(name = "password", required = false) String password) {

        return "utilisateur/login";


    }

    @GetMapping("/home")
    public String home() {



        return "utilisateur/home";
    }

    @PostMapping("/login")
    public String login(Model model, @RequestParam(name = "email") String email, @RequestParam(name = "password") String password, HttpServletRequest request) {




        int compt = 0;
        Utilisateur utilisateur = utilisateurMetier.findByEmail(email);
        HttpSession session = request.getSession();


        if (utilisateurMetier.checkEmailLogin(email) == false) {


            if (utilisateurMetier.comparePassword(password, utilisateur) == true) {

                session.setAttribute("session", utilisateur);


                return "utilisateur/home";
            } else {
                compt = 1;
                model.addAttribute("compt", compt);

                return "utilisateur/loginFailed";
            }


        } else {

            compt = 2;
            model.addAttribute("compt", compt);

            return "utilisateur/loginFailed";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession();
        session.invalidate();

        return "utilisateur/home";
    }

    @GetMapping("/sendMailForm")
    public String sendMailForm(){


        return "utilisateur/mailForm";
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestParam(name ="email") String email, @RequestParam(name = "sujet")String sujet, @RequestParam(name = "message")String message, RedirectAttributes model){




      //  mailService.sendSimpleMail(email,sujet,message);


        if (mailMetier.sendSimpleMail(email,sujet,message)) {
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", "Your message has been sent");
        } else {
            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "An unexpected error occurred thank you to repeat your request later");
        }


        return "redirect:/utilisateur/sendMailForm";
    }

    @GetMapping("/modifierMailAdresse")
    public String formHeuresAnnulation() {

        return "utilisateur/mailAdresseModification";
    }


    @PostMapping("/modificationAdresseMail")
    public String heuresAnulationReservation(@RequestParam(name = "email") String email) {

        mailMetier.modifierAdresseMail(email);



        return "redirect:/reservation/tableau";
    }

    public String femmesHommes(Model model){

        List<List<Map<Object,Object>>> list =utilisateurMetier.getListFemmesHommes();

        model.addAttribute("listeFemmesHommes",list);

        return "utilisateur/graphHommesFemmes";
    }

}
