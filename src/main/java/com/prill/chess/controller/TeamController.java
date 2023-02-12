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

@RestController
public class TeamController {

	@Autowired
	TeamService teamService;

//	Get all teams
	@GetMapping("/teams")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

//	Get team by ID
	@GetMapping("/teams/{id}")
	public Optional<Team> getTeamById(@PathVariable("id") Integer id) {
		return teamService.getTeamById(id);
	}

//	Add Team
	@PostMapping("/teams")
	@ResponseStatus(HttpStatus.CREATED)
	public void createTeam(@RequestBody Team team) {
		teamService.addTeam(team);
	}

//	update Team
	@PutMapping("/team/{id}")
	public Team updateTeam(@PathVariable Integer id, @RequestBody Team team) {
		return teamService.updateTeam(id, team);
	}

//	Delete Team
	@DeleteMapping("/team/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTeamById(@PathVariable("id") Integer id) {
		teamService.deleteTeam(id);
	}

}
