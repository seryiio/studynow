package com.pe.studynow.init;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import com.pe.studynow.model.entity.Segment;
//import com.pe.studynow.model.entity.User;
//import com.pe.studynow.model.repository.UserRepository;

@Service
public class InitUser implements CommandLineRunner {
	
	//@Autowired
	//private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		//BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// ROLE_XXXXX	-> Segmento Objetivo
		// ACCESS_YYYYY
		
		// SIEMPRE DEBE DE ESTAR COMENTADO
		// SOLO SE DEBE DESBLOQUEAR CUANDO SE CREAN USUARIO
		/*
		
		User xiomara = new User();
		xiomara.setUsername("xiomara");
		xiomara.setPassword(bcpe.encode("xiomara"));	
		xiomara.setSegment(Segment.ADMINISTRATOR);
		xiomara.setIdSegment("ADXIOPLT");
		xiomara.addAuthority("ROLE_ADMINISTRATOR");
		xiomara.addAuthority("ACCESS_ALL");
		userRepository.save(xiomara);
		User fabrizio = new User();
		fabrizio.setUsername("fabrizio");
		fabrizio.setPassword(bcpe.encode("fabrizio"));	
		fabrizio.setSegment(Segment.ADMINISTRATOR);
		fabrizio.setIdSegment("ADFABAZ");
		fabrizio.addAuthority("ROLE_ADMINISTRATOR");
		fabrizio.addAuthority("ACCESS_ALL");
		userRepository.save(fabrizio);
		
		User eduardo = new User();
		eduardo.setUsername("eduardo");
		eduardo.setPassword(bcpe.encode("eduardo"));	
		eduardo.setSegment(Segment.ADMINISTRATOR);
		eduardo.setIdSegment("ADEDUTR");
		eduardo.addAuthority("ROLE_ADMINISTRATOR");
		eduardo.addAuthority("ACCESS_ALL");
		userRepository.save(eduardo);
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
