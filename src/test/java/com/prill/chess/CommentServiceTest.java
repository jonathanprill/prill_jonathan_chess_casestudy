package com.prill.chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.prill.chess.controller.CommentController;
import com.prill.chess.model.Comment;
import com.prill.chess.service.CommentService;

/**
 * This Class is used for testing the comment controller
 * @author Jonathan Prill
 *
 */
@WebMvcTest(CommentController.class)
public class CommentServiceTest {

	@MockBean
	CommentService commentService;

	@Autowired
	MockMvc mockMVC;

//	tests that the route to /comments is a valid route
	@Test
	public void testCommentRoute() throws Exception {
		mockMVC.perform(get("/comments")) // perform get
				.andExpect(status().isOk()); // expects http 200 - is successfuul
	}
	
//	tests get method for retrieving all comments
	@Test
	public void testCommentService() {
		List<Comment> commentList = commentService.getAllComments();
		
		assertThat(commentService.getAllComments().equals(commentList));
	}

}
