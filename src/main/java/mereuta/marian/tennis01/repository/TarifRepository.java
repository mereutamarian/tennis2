package mereuta.marian.tennis01.repository;

import mereuta.marian.tennis01.model.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarifRepository extends JpaRepository<Tarif,Integer> {

    @Query(value = "select * from tarifs where actif =1 and tarif_special=0 and default_tarif=0", nativeQuery = true)
    List<Tarif> listeTarifsNormaux();


}
