package com.prill.chess.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prill.chess.model.Comment;


/**
 * This Interface creates the Comment Repository and extends JPA Repository. 
 * @author Jonathan Prill
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	/**
	 * This method creates a custom query to find all the comments by userId
	 * @param id
	 * @return
	 */
	List<Comment> findAllCommentsByUserId(Integer id);
	
	/**
	 * This method creates a custom query to find all the comments by teamId
	 * @param teamId
	 * @return
	 */
	List<Comment> findAllCommentsByTeamId(Integer teamId);

}
