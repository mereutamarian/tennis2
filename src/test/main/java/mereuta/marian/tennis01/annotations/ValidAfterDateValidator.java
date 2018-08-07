
package mereuta.marian.tennis01.annotations;

import mereuta.marian.tennis01.annotations.ValidAfterDate;
import mereuta.marian.tennis01.model.Horaire;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ValidAfterDateValidator implements ConstraintValidator<ValidAfterDate, Horaire> {

    @Override
    public void initialize(ValidAfterDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Horaire horaire, ConstraintValidatorContext context) {
        if (horaire == null) {
            return true;
        }

        return horaire.getHeureDebut().isBefore(horaire.getHeureFin());
    }
}