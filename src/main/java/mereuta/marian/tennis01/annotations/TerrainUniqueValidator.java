package mereuta.marian.tennis01.annotations;

import mereuta.marian.tennis01.repository.TerrainRepository;
import mereuta.marian.tennis01.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TerrainUniqueValidator implements ConstraintValidator<TerrainUnique,String> {

    @Autowired
    TerrainRepository terrainRepository;

    @Override
    public void initialize(TerrainUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String nomTerrain, ConstraintValidatorContext constraintValidatorContext) {
        if (terrainRepository==null){
            return true;
        }
        return terrainRepository.findByNomTerrain(nomTerrain)==null;
    }
}
