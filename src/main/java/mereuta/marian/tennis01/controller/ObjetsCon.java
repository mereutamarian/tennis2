package mereuta.marian.tennis01.controller;


import mereuta.marian.tennis01.model.Objets;
import mereuta.marian.tennis01.repository.ObjetRepository;
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
@RequestMapping("/objets")
public class ObjetsCon {

    @Autowired
    private ObjetRepository objetRepository;
    @Value("${dir.images}")
    private String imagesDir;

    @RequestMapping("/test")
    public String afficheAll(Model model,
                             @RequestParam(name = "page", defaultValue = "0")int p,
                             @RequestParam(name = "motCle ", defaultValue = "")String motCle  ){

        Page<Objets> objetsList=objetRepository.findAll(new PageRequest(p,3));

        // avoir le nombre des pages
        int nPages=objetsList.getTotalPages();

        int[]pages=new int[nPages];
        for(int i=0; i<nPages; i++){
            pages[i]=i;
        }
        model.addAttribute("pages",pages);
        model.addAttribute("listeObjets", objetsList);
        model.addAttribute("pageCourante",p);
        model.addAttribute("motC", motCle);
        return "Test";
    }

    @RequestMapping("mot")
    public String afficheMotCle(Model model, @RequestParam(value = "motCle", defaultValue = "")String motC){

        List<Objets> objetsList= objetRepository.rechercherObjetMotCle("%"+motC+"%");

        model.addAttribute("listeObjets",objetsList);
        model.addAttribute("motCle" , motC);
        return "Test";
    }

    @GetMapping("/form")
    public String formObjet(Model model){

        model.addAttribute("objet", new Objets());

        return "form";
    }

    @RequestMapping(value = "saveObjet", method = RequestMethod.POST)
    //binding result sert a
    public String save(@Valid @ModelAttribute("objet") Objets objet, BindingResult bindingResult, @RequestParam(name = "photoPath") MultipartFile photo) throws IOException {



        objet.setActive(true);
        objet.setPhotoPath(photo.getOriginalFilename());

        objetRepository.save(objet);

        if(!(photo.isEmpty())){
            objet.setPhotoPath(imagesDir+photo.getOriginalFilename());
            photo.transferTo(new File(imagesDir+objet.getIdObjests()));
        }




        //on se redirectionne verre la methode affiche all

        // si on voulais aller dans une methode d'un autre controlleeur la il fallait indiquer tout le chemin avec les "/"
        return "redirect:test";
    }



    @RequestMapping(value = "/getPhoto", produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    // quand on envoie des données on utilise ResponseBody
    @ResponseBody
    public byte[] getPhoto(Integer id) throws Exception{

        // on recupere le fichier et on le stocke dans le fichier f et ensuite on le retourne

        File f= new File(imagesDir+id);

        //choisir Ioutils de apache commons
         return IOUtils.toByteArray(new FileInputStream(f));
    }

    @GetMapping("/supprimer")
    public String supprimer(Integer id){

        objetRepository.deleteById(id);

        return "redirect:test";
    }


}
