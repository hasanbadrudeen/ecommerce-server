package com.project.service;

import com.project.exception.UserException;
import com.project.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	
}
