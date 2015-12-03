package be.kroma.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=NewPasswordEqualsConfirmPasswordValidator.class)
public @interface NewPasswordEqualsConfirmPassword {
	String message() default "{be.kroma.constraints.NewPasswordEqualsConfirmPassword}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
