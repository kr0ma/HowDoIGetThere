package be.kroma.web;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.kroma.constraints.NewPasswordEqualsConfirmPassword;
import be.kroma.constraints.OldPasswordIsCorrect;

@NewPasswordEqualsConfirmPassword
@OldPasswordIsCorrect
public class ChangePasswordForm {
	
	@NotBlank
	@Length(min = 6, max = 255)
	@SafeHtml
	private String newPassword;
	
	@NotBlank
	@Length(min = 6, max = 255)
	@SafeHtml
	private String confirmNewPassword;
	
	@NotBlank
	@Length(min = 6, max = 255)
	@SafeHtml
	private String oldPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
