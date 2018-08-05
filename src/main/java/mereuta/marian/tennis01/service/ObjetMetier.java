package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Objet;
import mereuta.marian.tennis01.repository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ObjetMetier implements ObjetMetierInterface {

    @Value("${dir.images}")
    private String dossierImages;

    @Autowired
    ObjetRepository objetRepository;


    @Override
    public List<Objet> getListeObjets() {
        return objetRepository.findAll();
    }

    @Override
    public void ajouterObjet(Objet objet) {
        objetRepository.save(objet);
    }

    @Override
    public Objet ajouterPhotoObjet(Objet objet, MultipartFile file) {

        if (!(file.isEmpty())){
            objet.setPhotoPath(file.getOriginalFilename());
            try {
                //le nom de la photo va comporter l'id de l'étudiant
                file.transferTo(new File(dossierImages+objet.getIdObjests()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return objet;
    }
}
