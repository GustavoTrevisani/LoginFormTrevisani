package com.loginform.dao;

import org.springframework.data.repository.CrudRepository;

import com.loginform.bean.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public User findOneUserByLoginAndPassword(String login, String password);
	public User findOneUserByLogin(String login);
	
	

}
