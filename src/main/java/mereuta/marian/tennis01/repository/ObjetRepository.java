package mereuta.marian.tennis01.repository;

import mereuta.marian.tennis01.model.Objets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ObjetRepository extends JpaRepository<Objets, Integer>, CrudRepository<Objets, Integer>, org.springframework.data.repository.Repository<Objets,Integer>{

    @Query(value = "select * from  objets WHERE nom_objet=?1 AND actif=FALSE " , nativeQuery = true)
    public List<Objets> rechercheParNom(String nom);

    public Optional<List<Objets>> findAllByNomObjet(String nom);

    @Query(value = "select * from objets where nom_objet like ?1", nativeQuery = true)
    public List<Objets> rechercherObjetMotCle(String mc);



}
