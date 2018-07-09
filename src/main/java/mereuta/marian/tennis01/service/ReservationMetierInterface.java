package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReservationMetierInterface {

    public Horaire checkHoraire(LocalDate date);

    public LocalTime getSecondHeure(Integer indexDate2, List<LocalTime> listeHeureues);


    public Tarif recupereTarif(LocalDate date, LocalTime heure1);

    public Reservation getReservation(Integer idReservation);

    public void annulerReservation(Reservation reservation);

    public boolean checkIfDateEtHeureInferieureMaintenant(LocalDate date, LocalTime heure1);

    public boolean checkIfCancelBefore24Hours(LocalDate dateReservation, LocalTime heureDebut);


    public boolean checkIfCreditOk(Utilisateur utilisateur, Tarif tarif);

   public void heuresAnnulerReservation(int nombreHeures);
}
