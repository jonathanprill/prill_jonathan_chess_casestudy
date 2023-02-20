package com.prill.chess.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.prill.chess.model.Puzzle;
import com.prill.chess.service.PuzzleService;

/**
 * This Class is used as a Rest Controller that contains all routes involved in Puzzle CRUD manipulation
 * @author Jonathan Prill
 *
 */
@RestController
public class PuzzleController {

	@Autowired
	PuzzleService puzzleService;


	/**
	 * This method Gets All Puzzles
	 * @return
	 */
	@GetMapping("/puzzles")
	public List<Puzzle> getAllPuzzles() {
		return puzzleService.getAllPuzzles();
	}


	/**
	 * This method will Get a Puzzle Based on an Id parameter
	 * @param id
	 * @return
	 */
	@GetMapping("/puzzles/{id}")
	public Optional<Puzzle> getPuzzleById(@PathVariable("id") Integer id) {
		return puzzleService.getPuzzleById(id);
	}


	/**
	 * This method is used to create a puzzle
	 * @param puzzle
	 */
	@PostMapping("/puzzles")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPuzzle(@RequestBody Puzzle puzzle) {
		puzzleService.addPuzzle(puzzle);
	}


	/**
	 * This method is used to update a puzzle based on Id parameter
	 * @param id
	 * @param puzzle
	 * @return
	 */
	@PutMapping("/puzzle/{id}")
	public Puzzle updatePuzzle(@PathVariable Integer id, @RequestBody Puzzle puzzle) {
		return puzzleService.updatePuzzle(id, puzzle);
	}


	/**
	 * This method is used to delete a comment based on Id parameter
	 * @param id
	 */
	@DeleteMapping("/puzzle/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePuzzleById(@PathVariable("id") Integer id) {
		puzzleService.deletePuzzle(id);
	}
}
