package estateagency.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Voskubenko on 18.02.2017.
 */
public class NameValidator implements ConstraintValidator<Name, String> {
    @Override
    public void initialize(Name name) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        if (value.matches("^[a-zA-Zа-яА-Яа-щА-ЩЬьЮюЯяЇїІіЄєҐґ`' ]+$")) {
            return true;
        }
        return false;
    }
}
