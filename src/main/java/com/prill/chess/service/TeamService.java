package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Team;
import com.prill.chess.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

//	Get All Teams
	public List<Team> getAllTeams() {
		List<Team> teamList = teamRepository.findAll();
		return teamList;
	}

//	Get Team By Id
	public Optional<Team> getTeamById(Integer id) {
		Optional<Team> returnTeam = teamRepository.findById(id);
//		add commentList and User List here!
		return returnTeam;
	}

//	Add Team
	public Team addTeam(Team team) {
		return teamRepository.save(team);
	}

//	Update Team
	public Team updateTeam(Integer id, Team team) {
		Optional<Team> tempTeam = teamRepository.findById(id);
		Team teamObj = tempTeam.get();
		if (!tempTeam.equals(null)) {
			team.setId(teamObj.getId());
			teamRepository.save(team);
		}
		return team;
	}

//	Delete Team
	public void deleteTeam(Integer id) {
		teamRepository.deleteById(id);
	}
}
