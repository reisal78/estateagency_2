package estateagency.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommissionValidator implements ConstraintValidator<Commission, Double> {
    @Override
    public void initialize(Commission commission) {

    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        if (aDouble == null) {
            return false;
        }
        if (aDouble > 0){
            return true;
        }
        return false;
    }

}
