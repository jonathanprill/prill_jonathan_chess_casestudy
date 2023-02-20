package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Team;
import com.prill.chess.repository.TeamRepository;


/**
 * This Service Class gives logic to the Team Controller utilizing the TeamRepository
 * @author Jonathan Prill
 *
 */
@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

	/**
	 * This method contains logic for creating a list of teams, pulling them from the database
	 * @return
	 */
	public List<Team> getAllTeams() {
		List<Team> teamList = teamRepository.findAll();
		return teamList;
	}

	/**
	 * This method contains logic for getting a team by ID
	 * @param id
	 * @return
	 */
	public Optional<Team> getTeamById(Integer id) {
		Optional<Team> returnTeam = teamRepository.findById(id);
		// Maybe add commentList and User List here!
		return returnTeam;
	}

	/**
	 * This method contains the logic for creating a team and adding it to the database
	 * @param team
	 * @return
	 */
	public Team addTeam(Team team) {
		return teamRepository.save(team);
	}

	/**
	 * This method contains the logic for updating a team based on Id and updating the database 
	 * @param id
	 * @param team
	 * @return
	 */
	public Team updateTeam(Integer id, Team team) {
		Optional<Team> tempTeam = teamRepository.findById(id);
		Team teamObj = tempTeam.get();
		if (!tempTeam.equals(null)) {
			team.setId(teamObj.getId());
			teamRepository.save(team);
		}
		return team;
	}

	/**
	 * This method contains the logic for deleting a team based on Id and updating the database 
	 * @param id
	 */
	public void deleteTeam(Integer id) {
		teamRepository.deleteById(id);
	}
}
