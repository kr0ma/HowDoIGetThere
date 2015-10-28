package be.kroma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CreateSecurityFilter extends WebSecurityConfigurerAdapter {

	private static final String USERS_BY_USERNAME = "select username, password, active as enabled"
			+ " from users where username = ?";
	private static final String AUTHORITIES_BY_USERNAME = "select users.username as username, roles.name as authorities"
			+ " from users inner join userroles" + " on users.id = userroles.userid"
			+ " inner join roles" + " on roles.id = userroles.roleid" + " where users.username= ?";

	@Autowired
	private DataSource dataSource;

	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERS_BY_USERNAME)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME)
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**").antMatchers("/styles/**").antMatchers("/scripts/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginProcessingUrl("/login").loginPage("/").and().logout().logoutSuccessUrl("/")
		.and().authorizeRequests()
		.antMatchers("/route").authenticated()
		.and().exceptionHandling()
		.accessDeniedPage("/WEB-INF/JSP/forbidden.jsp");
	}

	@Override
	@Bean(name = "myAuthenticationManager")
	protected AuthenticationManager authenticationManager() throws Exception {	
		return super.authenticationManager();
	}

	@Override
	@Bean(name = "myUserDetailsService")
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
	
}
