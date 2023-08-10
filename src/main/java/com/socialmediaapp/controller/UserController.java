package com.socialmediaapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public List<User> retrieveAllUsers() {
		return dao.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public User retrieveUsers(@PathVariable int id) {
		return dao.findUsers(id);
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = dao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
