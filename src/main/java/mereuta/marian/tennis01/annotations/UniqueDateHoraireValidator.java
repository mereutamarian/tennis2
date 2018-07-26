package mereuta.marian.tennis01.annotations;


import mereuta.marian.tennis01.model.Horaire;
import mereuta.marian.tennis01.repository.HoraireRepository;

import mereuta.marian.tennis01.service.MetierHoraire;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class UniqueDateHoraireValidator implements ConstraintValidator<CheckDateHoraireSpecial,LocalDate> {

    @Autowired
    HoraireRepository horaireRepository;


    @Override
    public void initialize(CheckDateHoraireSpecial constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (horaireRepository==null ){
            return true;
        }
        return horaireRepository.findByDateHoraireSpecial(localDate) ==null;
    }
}
