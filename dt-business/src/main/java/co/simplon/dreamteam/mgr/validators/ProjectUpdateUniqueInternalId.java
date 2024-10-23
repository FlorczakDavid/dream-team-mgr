package co.simplon.dreamteam.mgr.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ProjectUpdateUniqueInternalIdValidator.class)
public @interface ProjectUpdateUniqueInternalId {
    String message() default "##########UniqueProjectInternalIdValidator##########";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
