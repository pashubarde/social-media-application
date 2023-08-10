package com.socialmediaapp.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.socialmediaapp.bean.User;

@Component
public class UserDao {

	private static List<User> users = new ArrayList<User>();

	static {
		users.add(new User(1, "Prashant", LocalDate.now().minusYears(30)));
		users.add(new User(2, "Ashok", LocalDate.now().minusYears(60)));
		users.add(new User(3, "Pankaj", LocalDate.now().minusYears(32)));
		users.add(new User(4, "Amit", LocalDate.now().minusYears(25)));
	}

	public List<User> findAll() {
		return users;
	}

	public User findUsers(int id) {
		return users.stream().filter(i -> i.getId().equals(id)).findFirst().get();
	}
}
