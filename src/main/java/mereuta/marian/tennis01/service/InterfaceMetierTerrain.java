package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Terrain;

import java.util.List;

public interface InterfaceMetierTerrain {

    public List<Terrain> showTerrain();

    public List<Terrain> attribuerTerrain(List<Integer> listeChoix);
}
