package co.simplon.dream_team.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueProjectInternalIdValidator.class)
public @interface UniqueProjectInternalId {
    String message() default "##########UniqueProjectInternalIdValidator##########";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
