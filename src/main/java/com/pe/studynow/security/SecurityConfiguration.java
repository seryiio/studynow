package com.pe.studynow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}

	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEnconder());

		return daoAuthenticationProvider;
	}

	@Autowired
	private LoginSucessHandler sucessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	// Restringir acceso a la pagina
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.csrf().disable().authorizeRequests()
					// Aqui realiza la configuración de los permisos

					.antMatchers("/login").permitAll()
					
					.antMatchers("/*.js", "/*.css").permitAll()

					.antMatchers("/students").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/teachers").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/careers").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/sections").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/courses").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/users").access("hasRole('ADMINISTRATOR')")

					.antMatchers("/students/new").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/teachers/new").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/careers/new").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/sections/new").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/courses/new").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/users/new").access("hasRole('ADMINISTRATOR')")

					.antMatchers("/users/view-profile").access("hasRole('ADMINISTRATOR') or hasRole('STUDENT') or hasRole('TEACHER')")
					.antMatchers("/main").access("hasRole('ADMINISTRATOR') or hasRole('STUDENT') or hasRole('TEACHER')")

					.antMatchers("/students/**/edit").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/teachers/**/edit").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/careers/**/edit").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/sections/**/edit").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/courses/**/edit").access("hasRole('ADMINISTRATOR')")
					.antMatchers("/users/**/edit").access("hasRole('ADMINISTRATOR')")

					.antMatchers("/students/**/edit").access("hasRole('ADMINISTRATOR') or hasRole('STUDENT')")
					.antMatchers("/courses/list").access("hasRole('ADMINISTRATOR')")

					.antMatchers("/enrollments").access("hasRole('STUDENT')")
					
					.and().formLogin().successHandler(sucessHandler).loginPage("/login")
					
					.defaultSuccessUrl("/main",true).permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
					.and().exceptionHandling().accessDeniedPage("/error");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}

	}

}
