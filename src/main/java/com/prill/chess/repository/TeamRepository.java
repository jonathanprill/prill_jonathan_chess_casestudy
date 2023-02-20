package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prill.chess.model.Team;

/**
 * This Interface creates the Team Repository and extends JPA Repository. 
 * @author Jonathan Prill
 *
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {

	static final String FIND_ALL_TEAMS_BY_ID_QUERY ="SELECT team.id, team_desc, team_location, team_name, team_color FROM team JOIN users_teams ON users_teams.teamid=team.id WHERE users_teams.id = :id";
	
	/**
	 * This method creates a custom SQL query to find all Teams by userId by joining the users_teams table
	 * @param id
	 * @return
	 */
	@Query(value = FIND_ALL_TEAMS_BY_ID_QUERY, nativeQuery = true)
	List<Team> findAllTeamsByUserId(@Param("id") Integer id);
}
