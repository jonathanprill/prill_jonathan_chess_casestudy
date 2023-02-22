package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.prill.chess.model.Puzzle;
import com.prill.chess.repository.PuzzleRepository;

/**
 * This Class is used for testing the Puzzle Repository
 * @author Jonathan Prill
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PuzzleRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	PuzzleRepository puzzleRepository;
	

//	Tests that puzzle repository has puzzles inside
	@Test
	public void shouldFindPuzzlesInRepo() {
		assertThat(puzzleRepository.findAll()).isNotEmpty();
	}
	
//	Tests FindByID Method and FindAll Methods
	@Test
	public void findByIDTest() throws Exception {
		Optional<Puzzle> tempPuzzle = puzzleRepository.findById(1);
		Puzzle puzzleInDB = tempPuzzle.get();
		
		List<Puzzle> puzzleList = puzzleRepository.findAll();
		assertThat(puzzleList.contains(puzzleInDB));
	}
	
//	Test custom query findAllPuzzlesByUserId and findById 
	@Test
	public void findAllPuzzlesByUserIdTest() {
		List<Puzzle> puzzleList = puzzleRepository.findAllPuzzlesByUserId(1);
		Optional<Puzzle> tempPuzzle = puzzleRepository.findById(1);
		Puzzle puzzleInDb = tempPuzzle.get();
		assertThat(puzzleList.contains(puzzleInDb));
		
	}
	

}