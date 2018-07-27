package mereuta.marian.tennis01.annotations;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidAfterDateValidator.class })
@Documented
public @interface ValidAfterDate {


    String message() default "The End date should be after the starting date." ;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}