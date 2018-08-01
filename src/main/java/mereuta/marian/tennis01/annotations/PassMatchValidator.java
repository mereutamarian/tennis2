package mereuta.marian.tennis01.annotations;

import org.mvel2.MVEL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PassMatchValidator implements ConstraintValidator<PassMatch, Object> {

    private String field;
    private String verifyField;


    public void initialize(PassMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.verifyField = constraintAnnotation.verifyField();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldObj = MVEL.getProperty(field, value);
        Object verifyFieldObj = MVEL.getProperty(verifyField, value);

        boolean neitherSet = (fieldObj == null) && (verifyFieldObj == null);

        if (neitherSet) {
            return true;
        }

        boolean matches = (fieldObj != null) && fieldObj.equals(verifyFieldObj);

        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("le mot de pass est different du mot de pass de confirmation")
                    .addNode(verifyField)
                    .addConstraintViolation();
        }

        return matches;
    }

}


