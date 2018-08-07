package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.MesureInterval;
import mereuta.marian.tennis01.repository.MesureIntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetierMesure implements MetierMesureInterface {

    @Autowired
    MesureIntervalRepository mesureIntervalRepository;

    @Override
    public List<MesureInterval> showIntervales() {
        return mesureIntervalRepository.findAll();
    }

    @Override
    public MesureInterval getMesure(Integer id) {

        if (id==null){
            id=1;
        }

        return mesureIntervalRepository.getOne(id);
    }
}
