package estateagency.validation;

import estateagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    @Autowired
    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername uniqueUsername) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        return !(userService.getCountUsersByLogin(value) > 0);
    }
}
