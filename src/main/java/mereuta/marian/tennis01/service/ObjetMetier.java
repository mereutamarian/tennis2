package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Objet;
import mereuta.marian.tennis01.repository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetMetier implements ObjetMetierInterface {

    @Autowired
    ObjetRepository objetRepository;


    @Override
    public List<Objet> getListeObjets() {
        return objetRepository.findAll();
    }
}
