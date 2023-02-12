package com.prill.chess.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prill.chess.model.Comment;
import com.prill.chess.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;

//	Get All Comments
	public List<Comment> getAllComments() {
		List<Comment> commentList = commentRepository.findAll();
		return commentList;
	}

//	Get Comments By Id
	public Optional<Comment> getCommentById(Integer id) {
		Optional<Comment> returnComment = commentRepository.findById(id);
		return returnComment;
	}

//	Create Comment
	public Comment addComment(Comment comment) {
		return commentRepository.save(comment);
	}

//	Update Comment
	public Comment updateComment(Integer id, Comment comment) {
		Optional<Comment> tempComment = commentRepository.findById(id);
		Comment commentObj = tempComment.get();
		if (!tempComment.equals(null)) {
			comment.setId(commentObj.getId());
			commentRepository.save(comment);
		}
		return comment;
	}

//	Delete Comment
	public void deleteComment(Integer id) {
		commentRepository.deleteById(id);
	}

}
