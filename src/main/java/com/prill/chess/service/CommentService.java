package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Comment;
import com.prill.chess.repository.CommentRepository;

/**
 * This Service Class gives logic to the Comment Controller utilizing the CommentRepository
 * @author Jonathan Prill
 *
 */
@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;


	/**
	 * This method contains logic for creating a list of comments, pulling them from the database
	 * @return
	 */
	public List<Comment> getAllComments() {
		List<Comment> commentList = commentRepository.findAll();
		return commentList;
	}


	/**
	 * This method contains logic for getting a comment by ID
	 * @param id
	 * @return
	 */
	public Optional<Comment> getCommentById(Integer id) {
		Optional<Comment> returnComment = commentRepository.findById(id);
		return returnComment;
	}


	/**
	 * This method contains the logic for creating a comment and adding it to the database
	 * @param comment
	 * @return
	 */
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}


	/**
	 * This method contains the logic for updating a comment based on Id and updating the database 
	 * @param id
	 * @param comment
	 * @return
	 */
	public Comment updateComment(Integer id, Comment comment) {
		Optional<Comment> tempComment = commentRepository.findById(id);
		Comment commentObj = tempComment.get();
		if (!tempComment.equals(null)) {
			comment.setId(commentObj.getId());
			commentRepository.save(comment);
		}
		return comment;
	}


	/**
	 * This method contains the logic for deleting a comment based on Id and updating the database 
	 * @param id
	 */
	public void deleteComment(Integer id) {
		commentRepository.deleteById(id);
	}

}
