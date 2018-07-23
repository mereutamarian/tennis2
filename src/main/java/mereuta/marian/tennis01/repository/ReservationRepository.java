package mereuta.marian.tennis01.repository;


import mereuta.marian.tennis01.model.Reservation;
import mereuta.marian.tennis01.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByUtilisateurAndDateReservationGreaterThanEqualAndActif(Utilisateur utilisateur, Date currentDate, boolean actif);

    List<Reservation>findByUtilisateurAndDateReservationIsLessThanAndActif(Utilisateur utilisateur, Date currentDate, boolean actif);

    List<Reservation>findByDateReservationIsGreaterThanAndActifTrue(LocalDate today);
    List<Reservation>findByOptionalTrue();




}


