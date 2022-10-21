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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	//Restringir acceso a la pagina
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		// Aqui realiza la configuración de los permisos
				.antMatchers("/").permitAll()
				.antMatchers("/careers/**").hasRole("ADMINISTRATOR")
				.antMatchers("/students/**").hasRole("ADMINISTRATOR")
				.antMatchers("/students/**/edit").hasAnyAuthority("ACCESS_EDIT_MATRI", "ACCESS_ALL")
				.antMatchers("/courses/list").hasRole("TEACHER")
				.antMatchers("/enrollments/**").hasRole("STUDENT")

				.antMatchers("/main").permitAll()
					.and()
				.formLogin().loginPage("/login").permitAll().and().logout().permitAll();
				
	}
	
	
}
