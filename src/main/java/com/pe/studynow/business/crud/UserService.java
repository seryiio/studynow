package com.pe.studynow.business.crud;

import java.util.Optional;

import com.pe.studynow.model.entity.User;


public interface UserService{

	boolean existsByUsername(String username) throws Exception;
	Optional<User> register(User User) throws Exception;

}
