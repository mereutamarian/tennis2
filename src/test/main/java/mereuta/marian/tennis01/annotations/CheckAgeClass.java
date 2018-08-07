package mereuta.marian.tennis01.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CheckAgeClass implements ConstraintValidator<CheckAge,LocalDate> {
    @Override
    public void initialize(CheckAge constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate dateNaissance, ConstraintValidatorContext constraintValidatorContext) {

        int age=(int)ChronoUnit.YEARS.between(dateNaissance,LocalDate.now());

        if (age>16){
            return true;
        }

        return age>16;
    }
}
