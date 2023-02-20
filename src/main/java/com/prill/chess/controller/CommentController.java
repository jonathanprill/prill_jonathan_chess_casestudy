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

import com.prill.chess.model.Comment;
import com.prill.chess.service.CommentService;
/**
 * This Class is used as a Rest Controller that contains all routes involved in Comment CRUD manipulation
 * @author Jonathan Prill
 *
 */
@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	
	/**
	 * This method Gets All Comments
	 * @return
	 */
	@GetMapping("/comments")
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
	/**
	 * This method will Get a Comment Based on an Id parameter
	 * @param id
	 * @return
	 */
	@GetMapping("/comments/{id}")
	public Optional<Comment> getCommentById(@PathVariable("id") Integer id) {
		return commentService.getCommentById(id);
	}
	
	/**
	 * This method is used to create a comment
	 * @param comment
	 */
	@PostMapping("/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public void createComment(@RequestBody Comment comment) {
		commentService.addComment(comment);
	}
	
	/**
	 * This method is used to update a comment based on Id parameter
	 * @param id
	 * @param comment
	 * @return
	 */
	@PutMapping("/comment/{id}")
	public Comment updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
		return commentService.updateComment(id, comment);
	}
	
	/**
	 * This method is used to delete a comment based on Id parameter
	 * @param id
	 */
	@DeleteMapping("/comment/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCommentById(@PathVariable("id") Integer id) {
		commentService.deleteComment(id);
	}
}
