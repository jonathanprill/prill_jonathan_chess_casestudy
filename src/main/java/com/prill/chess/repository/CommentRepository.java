package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prill.chess.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findAllCommentsByUserId(Integer id); //or userId
	
	List<Comment> findAllCommentsByTeamId(Integer teamId);

}
