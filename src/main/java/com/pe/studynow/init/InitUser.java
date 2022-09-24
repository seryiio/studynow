package com.pe.studynow.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitUser implements CommandLineRunner {
	
	@Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// ROLE_XXXXX	-> Segmento Objetivo
		// ACCESS_YYYYY
		
		// SIEMPRE DEBE DE ESTAR COMENTADO
		// SOLO SE DEBE DESBLOQUEAR CUANDO SE CREAN USUARIO
		/*User carlos = new User();
		carlos.setUsername("carlos");
		carlos.setPassword(bcpe.encode("carlos"));	
		carlos.setSegment(Segment.STUDENT);
		carlos.setIdSegment(1);
		carlos.addAuthority("ROLE_STUDENT");
		carlos.addAuthority("ACCESS_VIEW_MATRI");
		carlos.addAuthority("ACCESS_EDIT_MATRI");
		userRepository.save(carlos);
		
		User maria = new User("maria", bcpe.encode("maria"), Segment.STUDENT, 2);
		maria.addAuthority("ROLE_STUDENT");
		maria.addAuthority("ACCESS_VIEW_MATRI");
		userRepository.save(maria);
		
		User pedro = new User("pedro", bcpe.encode("pedro"), Segment.TEACHER, 1);
		pedro.addAuthority("ROLE_TEACHER");
		pedro.addAuthority("ACCESS_VIEW_MATRI");
		userRepository.save(pedro);
		
		User admin = new User("admin", bcpe.encode("admin"), Segment.TEACHER, 2);
		admin.addAuthority("ROLE_ADMINISTRATOR");
		admin.addAuthority("ACCESS_ALL");
		userRepository.save(admin);
		
		User carla = new User("carla", bcpe.encode("carla"), Segment.TEACHER, 2);
		carla.addAuthority("ROLE_TEACHER");
		carla.addAuthority("ACCESS_VIEW_MATRI");
		userRepository.save(carla);
		
		User daniel = new User("daniel", bcpe.encode("daniel"), Segment.TEACHER, 3);
		daniel.addAuthority("ROLE_TEACHER");
		daniel.addAuthority("ACCESS_VIEW_MATRI");
		userRepository.save(daniel);*/
				
	}

}
