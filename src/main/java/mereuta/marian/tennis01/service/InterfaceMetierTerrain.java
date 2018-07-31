package mereuta.marian.tennis01.service;


import mereuta.marian.tennis01.model.Terrain;

import java.util.List;

public interface InterfaceMetierTerrain {

    public List<Terrain> showTerrain();
    public List<Terrain> attribuerTerrain(List<Integer> listeChoix);
    public Terrain getTerrain(Integer id);
    public void deleteTerrain(Terrain terrain);
    public void addTerrain(Terrain terrain);
    public void setActivTerrain(Terrain t);


    public List<Terrain> effacerTerrainCourrant(List<Terrain> terrains, Integer idResa);
}
