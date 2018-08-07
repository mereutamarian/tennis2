package mereuta.marian.tennis01.controller;


import mereuta.marian.tennis01.model.Objet;
import mereuta.marian.tennis01.repository.ObjetRepository;
import mereuta.marian.tennis01.service.ObjetMetier;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/objet")
public class ObjetsController {

    @Value("${dir.images}")
    private String dossierImages;

  @Autowired
   ObjetMetier objetMetier;

  private Objet objet;
  private List<Objet> objets;


    @GetMapping("/liste")
    public  String listeObjets(Model model){

        objets=objetMetier.getListeObjets();

        model.addAttribute("objets", objets);


        return "objet/objetsListe";
    }

    @GetMapping("/formObjet")
    public String formHoraire(Model model) {

        model.addAttribute("objet", new Objet());

        return "objet/objetFormulaire";
    }

    @PostMapping("/addObjet")
    public String ajouterObjet(@Valid @ModelAttribute(value = "objet")Objet objet, BindingResult bindingResult,@RequestParam(value = "photo") MultipartFile file) throws IOException {

        if(bindingResult.hasErrors()){
            return "objet/objetFormulaire";
        }

        objetMetier.ajouterObjet(objet);

       objetMetier.ajouterPhotoObjet(objet,file);

        return "redirect:/objet/liste";
    }

    @GetMapping(value = "getPhoto", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] charherPhoto(Integer id) throws Exception {


        File f=objetMetier.chargerPhoto(id);

        return IOUtils.toByteArray(new FileInputStream(f));

    }

    @GetMapping("supprimer")
    public String supprimerObjet(@RequestParam(value = "id")Integer idObjet){

        objetMetier.supprimerObjet(idObjet);

        return "redirect:/objet/liste";
    }

    @GetMapping("edit")
    public String modifierObjet(@RequestParam(value = "id")Integer idObjet, Model model){

        objet=objetMetier.getObjetById(idObjet);

        model.addAttribute("objet", objet);

        return "objet/modifierObjet";
    }

    @PostMapping("/modifierObjet")
    public String ModifierObjet(@Valid @ModelAttribute(value = "objet")Objet objet, BindingResult bindingResult,@RequestParam(value = "photo") MultipartFile file) throws IOException {


        System.out.println("je suis l'id de l'obj"+objet.getIdObjests());
        if(bindingResult.hasErrors()){
            return "objet/modifierObjet";
        }

        objetMetier.ajouterObjet(objet);

        objetMetier.ajouterPhotoObjet(objet,file);

        return "redirect:/objet/liste";
    }

    @GetMapping("/rechercheMotcle")
    public String rechercherParMotcle(@RequestParam(value = "motCle", required = false) String motCle, Model model) {

       objets = objetMetier.rechercheObjetParMotCle("%" + motCle + "%");

        model.addAttribute("objets", objets);


        return "objet/objetsMotCle";
    }

}
