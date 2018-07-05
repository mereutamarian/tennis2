package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.model.Tarif;
import mereuta.marian.tennis01.model.Terrain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReservationMetierInterface {

        public Horaire checkHoraire(LocalDate date);
        public LocalTime getSecondHeure(Integer indexDate2, List<LocalTime> listeHeureues);

        public LocalDateTime constructionDateTime(LocalDate date, LocalTime heure);

       public Tarif recupereTarif(LocalDate date, LocalTime heure1);
}
