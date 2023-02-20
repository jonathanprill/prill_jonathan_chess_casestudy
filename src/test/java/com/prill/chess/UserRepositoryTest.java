package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.prill.chess.model.User;
import com.prill.chess.repository.TeamRepository;
import com.prill.chess.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TeamRepository teamRepo;

//	Tests that user repository has users inside
	@Test
	public void shouldFindUsersInRepo() {
		assertThat(userRepo.findAll()).isNotEmpty();
	}
	
//	Using the Find user by email to check their username against what's in the DB
	@Test
	public void findUserByEmailPositiveTest() throws Exception {
		assertThat(userRepo.findUserByEmail("test2@gmail.com")).isNotNull();
		User userInDB = userRepo.findUserByEmail("test2@gmail.com");
		assertEquals(userInDB.getUsername(), "test2");
	}
	
//	No user of that email in database so should be NULL
	@Test
	public void findUserByEmailNegativeTest() throws Exception {
		assertThat(userRepo.findUserByEmail("xxxxxxx.com")).isNull();
	}
	
	@Test
	public void findByIDTest() throws Exception {
		User userInDB = userRepo.findUserByEmail("jprill@gmail.com");
		Optional<User> tempUser = userRepo.findById(1);
		User sameUserInDB = tempUser.get();
		
		assertEquals(userInDB, sameUserInDB);

	}

}