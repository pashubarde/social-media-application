package com.socialmediaapp.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialmediaapp.bean.User;
import com.socialmediaapp.dao.UserDao;
import com.socialmediaapp.exception.UserNotFoundException;

import jakarta.validation.Valid;

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
		User user = dao.findUsers(id);
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		return user;
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUsers(@PathVariable int id) {
		User user = dao.findUsers(id);
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		dao.deleteById(id);
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = dao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
