package estateagency.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.matches("^[0-9 ()+-]+$")) {

            return true;
        }

        return false;
    }
}

