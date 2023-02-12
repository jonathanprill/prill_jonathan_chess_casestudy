package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Puzzle;
import com.prill.chess.model.Team;
import com.prill.chess.model.User;
import com.prill.chess.repository.PuzzleRepository;
import com.prill.chess.repository.TeamRepository;
import com.prill.chess.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	PuzzleRepository puzzleRepository;
	
	@Autowired
	TeamRepository teamRepository;

	@Autowired
	UserRepository userRepository;

//	Get All Users
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();

		
		return userList;
	}

//	Get User By Id
	public Optional<User> getUserById(Integer id) {
		Optional<User> returnUser = userRepository.findById(id);
//		add commentList and Team List here!
		return returnUser;
	}

//	Add User
	public User addUser(User user) {
		// Encrypt password
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
		userRepository.save(user);
		return user;
	}

//	Update User
	public User updateUser(Integer id, User user) {
		Optional<User> tempUser = userRepository.findById(id);
		User userObj = tempUser.get();
		if (!tempUser.equals(null)) {
			user.setId(userObj.getId());
			userRepository.save(user);
		}
		return user;
	}
	
//	Delete User
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}
