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
        objet.setActif(true);
        objetRepository.save(objet);
    }

    @Override
    public void ajouterPhotoObjet(Objet objet, MultipartFile file) throws IOException {



        if(!(file.isEmpty())){
            objet.setPhotoPath(file.getOriginalFilename());
            objetRepository.save(objet);

            if (!(file.isEmpty())){
                objet.setPhotoPath(file.getOriginalFilename());
                file.transferTo(new File(dossierImages+objet.getIdObjests()));
            }
        }



    }

    @Override
    public File chargerPhoto(Integer id) {

        File file=new File(dossierImages+id);
        return file ;
    }

    @Override
    public void supprimerObjet(Integer id) {

        Objet objet= objetRepository.getOne(id);
        objet.setActif(false);
        objetRepository.save(objet);

    }

    @Override
    public Objet getObjetById(Integer idObjet) {
        return objetRepository.getOne(idObjet);
    }

    @Override
    public List<Objet> rechercheObjetParMotCle(String s) {
        return objetRepository.rechercherObjetMotCle(s);
    }
}
