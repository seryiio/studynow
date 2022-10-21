package com.pe.studynow.business.crud;


import java.util.Optional;

import com.pe.studynow.model.entity.User;


public interface UserService extends CrudService<User, Integer>{

	boolean existsByUsername(String username) throws Exception;
	Optional<User> register(User User) throws Exception;	

}
