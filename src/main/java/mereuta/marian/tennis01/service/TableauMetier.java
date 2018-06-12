package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Tableau;
import mereuta.marian.tennis01.repository.TableauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




@Service
public class TableauMetier implements TableauMetierInterface {

    @Autowired
    TableauRepository tableauRepository;



    @Override
    public List<Tableau> showTableaux() {

        return tableauRepository.findAll();
    }

    @Override
    public void addTableau(Tableau tableau) {

        tableauRepository.save(tableau);
    }

    @Override
    public Tableau chargeTableau(Integer id) {
        return tableauRepository.getOne(id);
    }

    @Override
    public void deleteTableau(Tableau tableau) {

        tableauRepository.delete(tableau);
    }


}
