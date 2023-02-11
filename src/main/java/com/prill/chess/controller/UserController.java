package com.prill.chess.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prill.chess.model.User;
import com.prill.chess.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

//	Get all users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

//	Get
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}

//	Create User
	@PostMapping("/adduser")
	public void createUser(@RequestBody User user) {
		userService.addUser(user);
	}

//	update user
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}
	
//	Delete User
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
}
