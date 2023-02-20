package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Puzzle;

import com.prill.chess.repository.PuzzleRepository;


/**
 * This Service Class gives logic to the Puzzle Controller utilizing the PuzzleRepository
 * @author Jonathan Prill
 *
 */
@Service
public class PuzzleService {

	@Autowired
	PuzzleRepository puzzleRepository;
	
	/**
	 * This method contains logic for creating a list of puzzles, pulling them from the database
	 * @return
	 */
	public List<Puzzle> getAllPuzzles() {
		List<Puzzle> puzzleList = puzzleRepository.findAll();
		return puzzleList;
	}
	
	/**
	 * This method contains logic for getting a puzzle by ID
	 * @param id
	 * @return
	 */
	public Optional<Puzzle> getPuzzleById(Integer id) {
		Optional<Puzzle> returnPuzzle = puzzleRepository.findById(id);
		return returnPuzzle;
	}
	
	/**
	 * This method contains the logic for creating a puzzle and adding it to the database
	 * @param puzzle
	 * @return
	 */
	public Puzzle addPuzzle(Puzzle puzzle) {
		return puzzleRepository.save(puzzle);
	}
	

	/**
	 * This method contains the logic for updating a puzzle based on Id and updating the database 
	 * @param id
	 * @param puzzle
	 * @return
	 */
	public Puzzle updatePuzzle(Integer id, Puzzle puzzle) {
		Optional<Puzzle> tempPuzzle = puzzleRepository.findById(id);
		Puzzle puzzleObj = tempPuzzle.get();
		if (!tempPuzzle.equals(null)) {
			puzzle.setId(puzzleObj.getId());
			puzzleRepository.save(puzzle);
		}
		return puzzle;
	}
	
	/**
	 * This method contains the logic for deleting a puzzle based on Id and updating the database 
	 * @param id
	 */
	public void deletePuzzle(Integer id) {
		puzzleRepository.deleteById(id);
	}
	

}
