package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prill.chess.model.Puzzle;

public interface PuzzleRepository extends JpaRepository<Puzzle, Integer> {
	List<Puzzle> findAllPuzzlesByUserId(Integer id); //or userId
}
