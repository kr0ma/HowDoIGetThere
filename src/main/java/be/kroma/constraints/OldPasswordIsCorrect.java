package be.kroma.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OldPasswordIsCorrectValidator.class)
public @interface OldPasswordIsCorrect {
	String message() default "{be.kroma.constraints.OldPasswordIsCorrect}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
