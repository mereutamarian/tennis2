package mereuta.marian.tennis01.repository;

import mereuta.marian.tennis01.model.Objet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ObjetRepository extends JpaRepository<Objet, Integer>, CrudRepository<Objet, Integer>, org.springframework.data.repository.Repository<Objet,Integer>{


    List<Objet> findByNomObjet(String nom);
    Page<Objet> findByNomObjet(String nom , Pageable p);

    @Query(value = "select * from objets where nom_objet like ?1", nativeQuery = true)
    public List<Objet> rechercherObjetMotCle(String mc);



}
