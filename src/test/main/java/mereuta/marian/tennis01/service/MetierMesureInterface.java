package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.MesureInterval;

import java.util.List;

public interface MetierMesureInterface {

    public List<MesureInterval> showIntervales();
    public MesureInterval getMesure(Integer id);


}
