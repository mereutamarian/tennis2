package mereuta.marian.tennis01.repository;


import mereuta.marian.tennis01.model.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface HoraireRepository extends JpaRepository<Horaire,Integer> {



    Horaire findByDateHoraireSpecial( LocalDate date);

    List<Horaire> findAllByDateHoraireSpecialNotNull();

    Horaire findByHoraireFix(String horaireFix);

}
