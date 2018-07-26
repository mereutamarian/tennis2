
package mereuta.marian.tennis01.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {UniqueDateHoraireValidator.class}
)
public @interface CheckDateHoraireSpecial {

    String message() default "{javax.validation.constraints.Size.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
