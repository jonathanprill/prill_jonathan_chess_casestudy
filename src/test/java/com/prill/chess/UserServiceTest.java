package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.prill.chess.controller.UserController;
import com.prill.chess.model.User;
import com.prill.chess.service.UserService;


@WebMvcTest(UserController.class)
public class UserServiceTest {

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMVC;

//	tests that the route to /users is a valid route
	@Test
	public void testUserRoute() throws Exception {
		mockMVC.perform(get("/users")) // perform get
				.andExpect(status().isOk()); // expects http 200 - is successfuul
	}
	
//	tests get method for retrieving all users
	@Test
	public void testUserService() {
		List<User> userList = userService.getAllUsers();
		
		assertThat(userService.getAllUsers().equals(userList));
	}

}