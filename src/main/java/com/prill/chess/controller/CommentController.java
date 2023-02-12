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

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	
//	Get all Comments
	@GetMapping("/comments")
	public List<Comment> getAllComments() {
		return commentService.getAllComments();
	}
	
//	Get comment by ID
	@GetMapping("/comments/{id}")
	public Optional<Comment> getCommentById(@PathVariable("id") Integer id) {
		return commentService.getCommentById(id);
	}
	
//	Add Comment
	@PostMapping("/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public void createComment(@RequestBody Comment comment) {
		commentService.addComment(comment);
	}
	
//	update comment
	@PutMapping("/comment/{id}")
	public Comment updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
		return commentService.updateComment(id, comment);
	}
	
//	Delete Comment
	@DeleteMapping("/comment/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCommentById(@PathVariable("id") Integer id) {
		commentService.deleteComment(id);
	}
}
