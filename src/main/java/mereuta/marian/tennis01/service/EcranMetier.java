package mereuta.marian.tennis01.service;


import mereuta.marian.tennis01.model.Ecran;
import mereuta.marian.tennis01.repository.EcranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EcranMetier implements EcranMetierInterface {

    @Autowired
    EcranRepository ecranRepository;

    @Override
    public Ecran showEcran() {
        return ecranRepository.getOne(1);
    }

    @Override
    public void addEcran(Ecran ecran) {
       ecranRepository.save(ecran);
    }


}
