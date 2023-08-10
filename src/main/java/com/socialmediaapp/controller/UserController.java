package com.socialmediaapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaapp.bean.User;
import com.socialmediaapp.dao.UserDao;

@RestController
public class UserController {
	private UserDao dao;
	
	public UserController(UserDao dao) {
		super();
		this.dao = dao;
	}

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers(){
		return dao.findAll();
	}
	
	@GetMapping(path = "/users/{id}")
	public User retrieveUsers(@PathVariable int id){
		return dao.findUsers(id);
	}
}
