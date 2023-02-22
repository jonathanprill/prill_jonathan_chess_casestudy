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

import com.prill.chess.controller.TeamController;
import com.prill.chess.model.Team;
import com.prill.chess.service.TeamService;

/**
 * This Class is used for testing the Team Controller
 * @author Jonathan Prill
 *
 */
@WebMvcTest(TeamController.class)
public class TeamServiceTest {

	@MockBean
	TeamService teamService;

	@Autowired
	MockMvc mockMVC;

//	tests that the route to /teams is a valid route
	@Test
	public void testTeamRoute() throws Exception {
		mockMVC.perform(get("/teams")) // perform get
				.andExpect(status().isOk()); // expects http 200 - is successfuul
	}
	
//	tests get method for retrieving all teams
	@Test
	public void testTeamService() {
		List<Team> teamList = teamService.getAllTeams();
		
		assertThat(teamService.getAllTeams().equals(teamList));
	}

}