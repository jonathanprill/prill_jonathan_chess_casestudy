package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.prill.chess.model.Comment;
import com.prill.chess.repository.CommentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

	@Autowired
	TestEntityManager entityManager;

	@Autowired
	CommentRepository commentRepository;

//	Tests that puzzle repository has puzzles inside
	@Test
	public void shouldFindPuzzlesInRepo() {
		assertThat(commentRepository.findAll()).isNotEmpty();
	}

//	Tests FindByID Method and FindAll Methods
	@Test
	public void findByIDTest() throws Exception {
		Optional<Comment> tempComment = commentRepository.findById(1);
		Comment commentInDB = tempComment.get();

		List<Comment> commentList = commentRepository.findAll();
		assertThat(commentList.contains(commentInDB));
	}

//	Test custom query findAllCommentsByUserId and findById 
	@Test
	public void findAllCommentsByUserIdTest() {
		List<Comment> commentList = commentRepository.findAllCommentsByUserId(1);
		Optional<Comment> tempComment = commentRepository.findById(1);
		Comment commentInDB = tempComment.get();
		assertThat(commentList.contains(commentInDB));

	}
	
//	Test custom query findAllCommentsByTeamId and findById 
	@Test
	public void findAllCommentsByTeamIdTest() {
		List<Comment> commentList = commentRepository.findAllCommentsByTeamId(1);
		Optional<Comment> tempComment = commentRepository.findById(1);
		Comment commentInDB = tempComment.get();
		assertThat(commentList.contains(commentInDB));
		
	}
}