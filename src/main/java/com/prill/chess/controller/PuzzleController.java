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

@RestController
public class PuzzleController {

	@Autowired
	PuzzleService puzzleService;

//	Get all puzzles
	@GetMapping("/puzzles")
	public List<Puzzle> getAllPuzzles() {
		return puzzleService.getAllPuzzles();
	}

//	Get puzzle by ID
	@GetMapping("/puzzles/{id}")
	public Optional<Puzzle> getPuzzleById(@PathVariable("id") Integer id) {
		return puzzleService.getPuzzleById(id);
	}

//	Add puzzle
	@PostMapping("/puzzles")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPuzzle(@RequestBody Puzzle puzzle) {
		puzzleService.addPuzzle(puzzle);
	}

//	update puzzle
	@PutMapping("/puzzle/{id}")
	public Puzzle updatePuzzle(@PathVariable Integer id, @RequestBody Puzzle puzzle) {
		return puzzleService.updatePuzzle(id, puzzle);
	}

//	Delete puzzle
	@DeleteMapping("/puzzle/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePuzzleById(@PathVariable("id") Integer id) {
		puzzleService.deletePuzzle(id);
	}
}
