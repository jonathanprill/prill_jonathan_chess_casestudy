package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prill.chess.model.Puzzle;

/**
 * This Interface creates the Puzzle Repository and extends JPA Repository.
 * @author Jonathan Prill
 *
 */
public interface PuzzleRepository extends JpaRepository<Puzzle, Integer> {

	static final String FIND_ALL_PUZZLES_BY_ID_QUERY = "SELECT puzzle.id, puzzle_icon, puzzle_name, puzzle_status FROM puzzle JOIN users_puzzles ON users_puzzles.puzzleid=puzzle.id WHERE users_puzzles.id = :id";
	
	/**
	 * This method creates a custom SQL query to find all Puzzles by userId  by joining the puzzles_teams table
	 * @param id
	 * @return
	 */
	@Query(value = FIND_ALL_PUZZLES_BY_ID_QUERY, nativeQuery = true)
	List<Puzzle> findAllPuzzlesByUserId(@Param("id") Integer id);

}
