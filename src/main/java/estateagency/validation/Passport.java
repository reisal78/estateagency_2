package estateagency.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PassportValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Passport {
    String message() default "{message.errors.passport_format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
