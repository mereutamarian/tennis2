package mereuta.marian.tennis01.repository;


import mereuta.marian.tennis01.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {

    Utilisateur findByEmail(String email);
    //nombre total utilisateurs qui sert pour les statistiques
    @Query(value = "select count(sexe) from utilisateurs WHERE  sexe='m'" , nativeQuery = true)
    int numbreHommesCount();

    @Query(value = "select count(sexe) from utilisateurs  WHERE sexe='f'" , nativeQuery = true)
    int numbreFemmesCount();

    @Query(value = "select  extract(year from current_date()) - extract(year from utilisateurs.date_naissance)\n" +
            "    -   case when utilisateurs.date_naissance + interval (extract(year from current_date()) - extract(year from utilisateurs.date_naissance))" +
            " year > current_date then 1 else 0 end as AGE\n" +
            "from    utilisateurs", nativeQuery = true)
    List<BigInteger> agesToutesPersonnes();

    @Query(value = "select * from utilisateurs where nom like ?1", nativeQuery = true)
    List<Utilisateur> trouverUtilisateurParMotCleViaSonNom(String motCle );

    @Query(value = "select id_utilisateur from utilisateurs where email=?1", nativeQuery = true)
    Integer findIdUtilisateurAvecEmail(String Email);

}
