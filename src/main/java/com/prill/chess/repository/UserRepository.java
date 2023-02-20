package com.prill.chess.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prill.chess.model.User;

import jakarta.transaction.Transactional;


/**
 * This Interface creates the User Repository and extends JPA Repository. 
 * @author Jonathan Prill
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//	Find User By Email
	User findUserByEmail(String email) throws Exception;

	

	/**
	 * This method creates a custom SQL INSERT statement, allowing a user to join a team
	 * @param id
	 * @param teamid
	 */
	@Modifying
	@Transactional 
	@Query(value = "INSERT INTO users_teams VALUES (:id, :teamid)", nativeQuery = true)
	void joinTeam(@Param("id") Integer id, @Param("teamid") Integer teamid);
	
	
	

	/**
	 * This method creates a custom SQL INSERT statement, adding a completed puzzle to the a user's list
	 * @param id
	 * @param puzzleid
	 */
	@Modifying
	@Transactional 
	@Query(value = "INSERT INTO users_puzzles VALUES (:id, :puzzleid)", nativeQuery = true)
	void puzzleComplete(@Param("id") Integer id, @Param("puzzleid") Integer puzzleid);
	
	
	
	
	
}
