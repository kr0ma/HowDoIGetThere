package be.kroma.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.kroma.web.ChangePasswordForm;

public class NewPasswordEqualsConfirmPasswordValidator implements ConstraintValidator<NewPasswordEqualsConfirmPassword, ChangePasswordForm> {

	@Override
	public void initialize(NewPasswordEqualsConfirmPassword constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(ChangePasswordForm changePasswordForm, ConstraintValidatorContext context) {
		System.out.println(changePasswordForm.getNewPassword());
		System.out.println(changePasswordForm.getConfirmNewPassword());
		return changePasswordForm.getNewPassword().equals(changePasswordForm.getConfirmNewPassword());
	}

}
