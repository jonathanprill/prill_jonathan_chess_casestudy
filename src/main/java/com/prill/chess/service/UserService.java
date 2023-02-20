package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.prill.chess.model.User;
import com.prill.chess.repository.PuzzleRepository;
import com.prill.chess.repository.TeamRepository;
import com.prill.chess.repository.UserRepository;


/**
 * This Service Class gives logic to the User Controller utilizing the UserRepository, 
 * @author Jonathan Prill
 *
 */
@Service
public class UserService {
	@Autowired
	PuzzleRepository puzzleRepository;
	
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	UserRepository userRepository;


	/**
	 * This method contains logic for creating a list of users, pulling them from the database
	 * @return
	 */
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();

		
		return userList;
	}


	/**
	 * This method contains logic for getting a user by ID
	 * @param id
	 * @return
	 */
	public Optional<User> getUserById(Integer id) {
		Optional<User> returnUser = userRepository.findById(id);
		// maybe add commentList and Team List here!
		return returnUser;
	}

	/**
	 * This method contains the logic for creating a user and adding it to the database
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		// Encrypt password
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		userRepository.save(user);
		return user;
	}


	/**
	 * This method contains the logic for updating a user based on Id and updating the database 
	 * @param id
	 * @param user
	 * @return
	 */
	public User updateUser(Integer id, User user) {
		Optional<User> tempUser = userRepository.findById(id);
		User userObj = tempUser.get();
		if (!tempUser.equals(null)) {
			user.setId(userObj.getId());
			userRepository.save(user);
		}
		return user;
	}
	

	/**
	 * This method contains the logic for deleting a user based on Id and updating the database 
	 * @param id
	 */
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}
