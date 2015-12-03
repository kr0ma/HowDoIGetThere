package be.kroma.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import be.kroma.web.ChangePasswordForm;

public class OldPasswordIsCorrectValidator implements ConstraintValidator<OldPasswordIsCorrect, ChangePasswordForm> {

	@Autowired
	private @Qualifier("myAuthenticationManager") AuthenticationManager authManager;
	
	@Autowired
	private @Qualifier("myUserDetailsService") UserDetailsService userDetailsService;

	@Override
	public void initialize(OldPasswordIsCorrect constraintAnnotation) {
	}

	@Override
	public boolean isValid(ChangePasswordForm changePasswordForm, ConstraintValidatorContext context) {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, changePasswordForm.getOldPassword(), userDetails.getAuthorities());
			authManager.authenticate(auth);
			if (auth.isAuthenticated()){
				return true;
			}
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
			return false;
		} 
		return false;
	}

}
