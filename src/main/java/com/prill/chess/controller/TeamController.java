package com.prill.chess.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prill.chess.model.Team;
import com.prill.chess.service.TeamService;

/**
 * This Class is used as a Rest Controller that contains all routes involved in Team CRUD manipulation
 * @author Jonathan Prill
 *
 */
@RestController
public class TeamController {

	@Autowired
	TeamService teamService;


	/**
	 * This method Gets All Teams
	 * @return
	 */
	@GetMapping("/teams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}


	/**
	 * This method will Get a Team Based on an Id parameter
	 * @param id
	 * @return
	 */
	@GetMapping("/teams/{id}")
	public Optional<Team> getTeamById(@PathVariable("id") Integer id) {
		return teamService.getTeamById(id);
	}

	
	/**
	 * This method is used to create a Team
	 * @param team
	 */
	@PostMapping("/teams")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTeam(@RequestBody Team team) {
		teamService.addTeam(team);
	}


	/**
	 * This method is used to update a Team based on Id parameter
	 * @param id
	 * @param team
	 * @return
	 */
	@PutMapping("/team/{id}")
	public Team updateTeam(@PathVariable Integer id, @RequestBody Team team) {
		return teamService.updateTeam(id, team);
	}


	/**
	 * This method is used to delete a Team based on Id parameter
	 * @param id
	 */
	@DeleteMapping("/team/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTeamById(@PathVariable("id") Integer id) {
		teamService.deleteTeam(id);
	}

}
