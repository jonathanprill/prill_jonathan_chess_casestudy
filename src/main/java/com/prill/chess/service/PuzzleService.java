package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Puzzle;

import com.prill.chess.repository.PuzzleRepository;

@Service
public class PuzzleService {

	@Autowired
	PuzzleRepository puzzleRepository;
	
//	Get All Puzzles
	public List<Puzzle> getAllPuzzles() {
		List<Puzzle> puzzleList = puzzleRepository.findAll();
		return puzzleList;
	}
	
//	Get Puzzle By Id
	public Optional<Puzzle> getPuzzleById(Integer id) {
		Optional<Puzzle> returnPuzzle = puzzleRepository.findById(id);
		return returnPuzzle;
	}
	
//	Add Puzzle
	public Puzzle addPuzzle(Puzzle puzzle) {
		return puzzleRepository.save(puzzle);
	}
	

//	Update Puzzle
	public Puzzle updatePuzzle(Integer id, Puzzle puzzle) {
		Optional<Puzzle> tempPuzzle = puzzleRepository.findById(id);
		Puzzle puzzleObj = tempPuzzle.get();
		if (!tempPuzzle.equals(null)) {
			puzzle.setId(puzzleObj.getId());
			puzzleRepository.save(puzzle);
		}
		return puzzle;
	}
	
//	Delete Puzzle
	public void deletePuzzle(Integer id) {
		puzzleRepository.deleteById(id);
	}
	

}
