package estateagency.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommissionValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Commission {
    String message() default "{message.errors.commission_input}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

