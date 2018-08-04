package mereuta.marian.tennis01.controller;


import mereuta.marian.tennis01.model.Objet;
import mereuta.marian.tennis01.repository.ObjetRepository;
import mereuta.marian.tennis01.service.ObjetMetier;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@org.springframework.stereotype.Controller
@RequestMapping("/objet")
public class ObjetsController {

    @Autowired
    ObjetMetier objetMetier;

    @GetMapping("/liste")
    public  String listeObjets(Model model){

        List<Objet> listeObjets=objetMetier.getListeObjets();

        model.addAttribute("objets", listeObjets);


        return "objet/objetsListe";
    }

    @GetMapping("/formObjet")
    public String formHoraire(Model model) {

        model.addAttribute("objet", new Objet());

        return "objet/objetFormulaire";
    }

}
