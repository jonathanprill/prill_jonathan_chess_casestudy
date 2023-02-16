package com.prill.chess.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.prill.chess.model.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//	Find User By Email
	User findUserByEmail(String email) throws Exception;

	
//	Lets a user join a team
	@Modifying
	@Transactional 
	@Query(value = "INSERT INTO users_teams VALUES (:id, :teamid)", nativeQuery = true)
	void joinTeam(@Param("id") Integer id, @Param("teamid") Integer teamid);
	
	
	
//	When a user finishes a puzzle
	@Modifying
	@Transactional 
	@Query(value = "INSERT INTO users_puzzles VALUES (:id, :puzzleid)", nativeQuery = true)
	void puzzleComplete(@Param("id") Integer id, @Param("puzzleid") Integer puzzleid);
	
	
	
	
	
}
