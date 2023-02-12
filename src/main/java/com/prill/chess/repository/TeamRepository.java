package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prill.chess.model.Puzzle;
import com.prill.chess.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
//	List<Team> findAllTeamsByUserId(Integer id); //or userId
	
//	  @Query(value = "SELECT team.id, team_desc, team_location, team_name FROM team JOIN users_teams ON users_teams.teamid=team.id WHERE users_teams.id = :id", nativeQuery = true)
//	  List<Team> findAllTeamssByUserId(@Param("id") Integer id);
}
