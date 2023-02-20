package com.prill.chess.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prill.chess.model.User;
import com.prill.chess.service.UserService;

/**
 * This Class is used as a Rest Controller that contains all routes involved in User CRUD manipulation
 * @author Jonathan Prill
 *
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;


	/**
	 * This method Gets All Users
	 * @return
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}


	/**
	 * This method will Get a User Based on an Id parameter
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}


	/**
	 * This method is used to create a User
	 * @param user
	 */
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		userService.addUser(user);
	}


	/**
	 * This method is used to update a User based on Id parameter
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}


	/**
	 * This method is used to delete a User based on Id parameter
	 * @param id
	 */
	@DeleteMapping("/user/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
	}
	
	//Logging error to file
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/logerror")
	public String hello() {
		logger.warn("enter hello...");
		logger.info("===info===");
		logger.trace("===trace===");
		logger.debug("===debug===");
		logger.warn("===warn===");

		return "Error Logged to File";
	}
}
