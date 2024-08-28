package co.simplon.dream_team.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueProjectInternalIdValidator.class)
public @interface UniqueProjectInternalId {
    String message() default "[][][][][[][][](( UniqueProjectInternalIdValidator ))[][][][][[][][]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
