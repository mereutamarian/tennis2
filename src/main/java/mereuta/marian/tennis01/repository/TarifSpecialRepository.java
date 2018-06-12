package mereuta.marian.tennis01.repository;

import mereuta.marian.tennis01.model.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifSpecialRepository extends JpaRepository<Horaire,Integer> {
}
