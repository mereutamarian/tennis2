package mereuta.marian.tennis01.repository;

import mereuta.marian.tennis01.model.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalTime;
import java.util.List;

public interface TarifRepository extends JpaRepository<Tarif,Integer> {


    @Query(value = "select * from tarifs where actif =1  and tarif_special=0 and default_tarif=0", nativeQuery = true)
    List<Tarif> listeTarifsNormaux();

    @Query(value = "select * from tarifs where actif =1 and weekend=0 and tarif_special=0 and default_tarif=0", nativeQuery = true)
    List<Tarif> listeTarifsNormauxSemaine();

    @Query(value = "select * from tarifs where actif =1 and weekend =1 and tarif_special=0 and default_tarif=0", nativeQuery = true)
    List<Tarif> listeTarifsNormauxWeekEnd();



    @Query(value = "select * from tarifs where actif =1 and tarif_special=1 and default_tarif=0", nativeQuery = true)
    List<Tarif> listeTarifsSpeciaux();

    Tarif findByTarifParDefautTrue();

//    @Query(value = "select * from tarifs where actif =1 and id=?", nativeQuery =true )
//    Tarif trouverTarif(Integer id);

    Tarif findTarifByIdAndActifTrue(Integer id);








}
