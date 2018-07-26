package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.MesureInterval;
import mereuta.marian.tennis01.model.Terrain;
import mereuta.marian.tennis01.repository.HoraireRepository;
import mereuta.marian.tennis01.repository.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class MetierHoraire implements HoraireMetierInterface {

    @Autowired
    HoraireRepository horaireRepository;
    @Autowired
    TerrainRepository terrainRepository;


    private List<Terrain> terrains;
    private Horaire horaire;


    @Override
    public List<Horaire> afficheHoraires() {


        return horaireRepository.findAll();
    }

    @Override
    public void addHoraire(Horaire horaire) {


        horaireRepository.save(horaire);

    }


    @Override
    public Horaire getHoraire(Integer id) {
        horaire = horaireRepository.getOne(id);

        return horaire;
    }

    @Override
    public void deleteHoraire(Horaire horaire) {

        horaireRepository.delete(horaire);

    }

    @Override
    public void update(Horaire horaire) {
        horaireRepository.save(horaire);
    }

    @Override
    public Horaire attribuerTerrainHoraire(Horaire horaire, List<Terrain> listeTerrains) {



        horaire.setTerrains(listeTerrains);

        return horaire;
    }

    @Override
    public Horaire attribuerIntervalMesure(Horaire horaire, MesureInterval mesureInterval) {

        horaire.setMesureInterval(mesureInterval);

        return horaire;
    }

        public int getIdHoraire(LocalDate localDate){

        Horaire horaire=horaireRepository.findByDateHoraireSpecial(localDate);

        return horaire.getId();

        }


}

