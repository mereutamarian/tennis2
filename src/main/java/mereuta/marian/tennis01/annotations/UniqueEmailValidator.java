package mereuta.marian.tennis01.annotations;

import mereuta.marian.tennis01.model.Utilisateur;
import mereuta.marian.tennis01.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<EmailUnique,String> {

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (utilisateurRepository==null){
            return true;
        }
        return utilisateurRepository.findByEmail(email)==null;
    }
}
