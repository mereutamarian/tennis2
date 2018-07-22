package mereuta.marian.tennis01.service;

import mereuta.marian.tennis01.model.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ReservationMetierInterface {

    public Horaire checkHoraire(LocalDate date);

    public LocalTime getSecondHeure(LocalTime heure1, List<LocalTime> listeHeureues);

    public Tarif recupereTarif(LocalDate date, LocalTime heure1);

    public Reservation getReservation(Integer idReservation);

    public void annulerReservation(Reservation reservation);

    public boolean checkIfDateEtHeureInferieureMaintenant(LocalDate date, LocalTime heure1);

    public boolean checkIfCancelBefore24Hours(LocalDate dateReservation, LocalTime heureDebut);

    public boolean checkIfCreditOk(Utilisateur utilisateur, Tarif tarif,LocalTime heure1,LocalTime heure2);

    public void heuresAnnulerReservation(int nombreHeures);

    public void joursReservation(int nombreJours);

    public int checkJourSpecial(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

    public List<LocalTime> transformHeures(Horaire h);

    public List<Integer> nombreJours();

    public LocalDate dateDuTableauReservation(Integer compteur);

    public void addReservation(Reservation reservation);

    public List<Reservation> getReservationList();
}
