package com.prill.chess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prill.chess.model.Comment;
import com.prill.chess.model.Puzzle;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findAllCommentsByUserId(Integer id); //or userId
	
	List<Comment> findAllCommentsByTeamId(Integer teamId);
	
//	  @Query(value = "SELECT user.username, comment.user_id, comment.id, comment.title, comment.body, comment.team_id, comment.timestamp FROM comment JOIN user ON comment.user_id = user.id WHERE comment.team_id = :teamId", nativeQuery = true)
//	  List<Comment> customfindAllCommentsByTeamId(@Param("teamId") Integer teamId);

}
