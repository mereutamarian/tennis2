package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Terrain;
import mereuta.marian.tennis01.repository.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetierTerrain implements InterfaceMetierTerrain {

    @Autowired
    TerrainRepository terrainRepository;

    public List<Terrain> showTerrain(){

        return terrainRepository.findAll();
    }


    // permet de recuperer les terrains choisis par le l'user
    //la liste listeId contient la liste avec les id des terrains choisis
    @Override
    public List<Terrain> attribuerTerrain(List<Integer> listeId) {

        List<Terrain> listechoix= new ArrayList<>();

        for( Integer i: listeId){
            // on recupere les terrains et on les ajoute dans la liste des terrains
            Terrain t=   terrainRepository.getOne(i);
           listechoix.add(t);
        }

        return listechoix;
    }


}
