package mereuta.marian.tennis01.repository;


import mereuta.marian.tennis01.model.Reservation;
import mereuta.marian.tennis01.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByUtilisateurAndDateDebutGreaterThanEqualAndActif(Utilisateur utilisateur, Date currentDate, boolean actif);

    List<Reservation>findByUtilisateurAndDateDebutIsLessThanAndActif(Utilisateur utilisateur, Date currentDate, boolean actif);
}


