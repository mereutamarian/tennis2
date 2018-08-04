package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Objet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ObjetMetierInterface {


   public List<Objet> getListeObjets();
}
