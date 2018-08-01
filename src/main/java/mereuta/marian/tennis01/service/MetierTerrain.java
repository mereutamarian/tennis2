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
        if(listechoix== null){
            listechoix=terrainRepository.findAll();
        }

        System.out.println(listechoix +" je suis la liste de choix");

        return listechoix;
    }

    @Override
    public Terrain getTerrain(Integer id) {
        return terrainRepository.getOne(id);
    }

    @Override
    public void deleteTerrain(Terrain terrain){
        terrainRepository.delete(terrain);

    }

    @Override
    public void addTerrain(Terrain terrain){

        terrainRepository.save(terrain);
    }

    @Override
    public void setActivTerrain(Terrain t){

        t.setActif(true);

    }

    @Override
    public List<Terrain> effacerTerrainCourrant(List<Terrain> terrains, Integer idResa) {

        int index=0;

        for (Terrain t : terrains){
            if(t.getId().equals(idResa)){
                System.out.println("voici le terrain"+t);
                index=terrains.indexOf(t);
            }
        }

        terrains.remove(terrains.get(index));

        System.out.println("voiala la liste des terrains"+terrains);

        return terrains;
    }

    //permet de recuperer les id du string terrain liste
    @Override
    public List<Integer> getIdsFromString(String terrainsListe) {

        List<Integer> ids=new ArrayList<>();
        String[]tableau= terrainsListe.split("i");
        Integer idTerrain;

        for (String id:tableau){
            if(id.startsWith("d=")){
                idTerrain=Integer.parseInt(id.substring(2, id.indexOf(",")));
                ids.add(idTerrain);
            }
        }


        return ids ;
    }

    @Override
    public List<Terrain> getTerrainsFromIds(List<Integer> idsTerrains) {

        List<Terrain> terrains= new ArrayList<>();
        Terrain terrain;

        for (Integer i: idsTerrains){
            terrain=getTerrain(i);
            terrains.add(terrain);
        }

        return terrains;
    }


}
