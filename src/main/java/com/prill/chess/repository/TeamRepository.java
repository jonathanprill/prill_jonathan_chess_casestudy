package com.prill.chess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prill.chess.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
//	List<Team> findAllTeamsByUserId(Integer id); //or userId
}
