package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.Terrain;

import java.time.LocalDate;
import java.util.List;

public interface ReservationMetierInterface {

        public Horaire checkHoraire(LocalDate date);


}
