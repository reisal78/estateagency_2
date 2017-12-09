package estateagency.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PassportValidator implements ConstraintValidator<Passport, String > {
    @Override
    public void initialize(Passport constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.matches("^[a-zA-Z0-9 ]+$")) {
        /* Доступные форматы
            RR 456789
         */
            return true;
        }

        return false;
    }
}
