package mereuta.marian.tennis01.repository;


import mereuta.marian.tennis01.model.Terrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public interface TerrainRepository extends JpaRepository<Terrain, Integer> {

    Terrain findByNomTerrain(String nomTerrain);


}
