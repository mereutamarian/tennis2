package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.MesureInterval;
import mereuta.marian.tennis01.model.Terrain;

import java.util.List;

public interface HoraireMetierInterface {

    public List<Horaire> afficheHoraires();
    public void addHoraire(Horaire horaire);
    public Horaire getHoraire(Integer id);
    public void deleteHoraire(Horaire horaire);
    public void update(Horaire horaire);
    public Horaire attribuerTerrainHoraire(Horaire horaire, List<Terrain> listeTerrains);
    public Horaire attribuerIntervalMesure(Horaire horaire, MesureInterval mesureInterval);



}
