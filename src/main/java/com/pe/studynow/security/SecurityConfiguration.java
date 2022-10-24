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
			http.authorizeRequests()
					// Aqui realiza la configuración de los permisos

					.antMatchers("/*.js", "/*.css").permitAll()

					.antMatchers("/students").hasRole("ADMINISTRATOR").antMatchers("/teachers").hasRole("ADMINISTRATOR")
					.antMatchers("/careers").hasRole("ADMINISTRATOR").antMatchers("/sections").hasRole("ADMINISTRATOR")
					.antMatchers("/courses").hasRole("ADMINISTRATOR").antMatchers("/users").hasRole("ADMINISTRATOR")

					.antMatchers("/students/new").hasRole("ADMINISTRATOR").antMatchers("/teachers/new")
					.hasRole("ADMINISTRATOR").antMatchers("/careers/new").hasRole("ADMINISTRATOR")
					.antMatchers("/sections/new").hasRole("ADMINISTRATOR").antMatchers("/courses/new")
					.hasRole("ADMINISTRATOR").antMatchers("/users/new").hasRole("ADMINISTRATOR")

					.antMatchers("/users/view-profile").hasAnyRole("ADMINISTRATOR", "TEACHER", "STUDENT")

					.antMatchers("/students/**/edit").hasRole("ADMINISTRATOR").antMatchers("/teachers/**/edit")
					.hasRole("ADMINISTRATOR").antMatchers("/careers/**/edit").hasRole("ADMINISTRATOR")
					.antMatchers("/sections/**/edit").hasRole("ADMINISTRATOR").antMatchers("/courses/**/edit")
					.hasRole("ADMINISTRATOR").antMatchers("/users/**/edit").hasRole("ADMINISTRATOR")

					.antMatchers("/students/**/edit").hasAnyAuthority("ACCESS_EDIT_MATRI", "ACCESS_ALL")
					.antMatchers("/courses/list").hasRole("TEACHER").antMatchers("/enrollments/**").hasRole("STUDENT")

					.antMatchers("/enrollments").hasRole("STUDENT")

					.and().formLogin().successHandler(sucessHandler).loginPage("/login").loginProcessingUrl("/login")
					// Si el login es exitoso, retorna a /home
					.defaultSuccessUrl("/main").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
					// Si el usuario va a una ruta sin acceso, devuelve a /error(Configurado en MvcConfig)
					.and().exceptionHandling().accessDeniedPage("/error");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e.getMessage());
		}

	}

}
