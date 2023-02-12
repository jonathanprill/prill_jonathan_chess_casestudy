package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prill.chess.model.Puzzle;

public interface PuzzleRepository extends JpaRepository<Puzzle, Integer> {
//	List<Puzzle> findAllPuzzlesByUserId(Integer id); //or userId
	
//	  @Query(value = "SELECT puzzle.id, puzzle_icon, puzzle_name, puzzle_status FROM puzzle JOIN users_puzzles ON users_puzzles.puzzleid=puzzle.id WHERE users_puzzles.id = :id", nativeQuery = true)
//	  List<Puzzle> findAllPuzzlesByUserId(@Param("id") Integer id);
	
}
