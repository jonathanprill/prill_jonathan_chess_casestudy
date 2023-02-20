package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.prill.chess.model.Team;
import com.prill.chess.repository.TeamRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	TeamRepository teamRepository;

//	Tests that team repository has teams inside
	@Test
	public void shouldFindPuzzlesInRepo() {
		assertThat(teamRepository.findAll()).isNotEmpty();
	}

////	Tests FindByID Method and FindAll Methods
	@Test
	public void findByIDTest() throws Exception {
		Optional<Team> tempTeam = teamRepository.findById(1);
		Team teamInDB = tempTeam.get();

		List<Team> teamList = teamRepository.findAll();
		assertThat(teamList.contains(teamInDB));
	}
	
//	test findAllTeamsByUserId custom query and findById
	@Test
	public void findAllTeamsByUserIdTest() {
		List<Team> teamList = teamRepository.findAllTeamsByUserId(1);
		Optional<Team> tempTeam = teamRepository.findById(1);
		Team teamInDb = tempTeam.get();
		assertThat(teamList.contains(teamInDb));
	}
}
