package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Objet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ObjetMetierInterface {


   public List<Objet> getListeObjets();

   public void ajouterObjet(Objet objet);

   public void ajouterPhotoObjet(Objet objet, MultipartFile file) throws IOException;

   public File chargerPhoto(Integer id);

   public void supprimerObjet(Integer id);

   public Objet getObjetById(Integer idObjet);

   public List<Objet> rechercheObjetParMotCle(String s);
}
