package com.prill.chess.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.prill.chess.model.Comment;
import com.prill.chess.model.Puzzle;
import com.prill.chess.model.Team;
import com.prill.chess.model.User;
import com.prill.chess.repository.CommentRepository;
import com.prill.chess.repository.PuzzleRepository;
import com.prill.chess.repository.TeamRepository;
import com.prill.chess.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This Class is used as a Controller that hits POST and PUT routes involved in the application when running in the browser
 * @author Jonathan Prill
 *
 */
@Controller
public class DataController {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PuzzleRepository puzzleRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is used to login an existing user, sets them as SESSION_USER and includes validation
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/users/login")
	public String login(@ModelAttribute User user, Model model, HttpServletRequest request) throws Exception {

		if ((user.getPassword().equals(null) || user.getPassword().isEmpty())
				|| (user.getEmail().equals(null) || user.getPassword().isEmpty())) {
			model.addAttribute("notice", "Please enter a valid email and password!");
			return "login";
		}

		User sessionUser = userRepository.findUserByEmail(user.getEmail());

		try {
			if (sessionUser.equals(null)) {

			}
		} catch (NullPointerException e) {
			model.addAttribute("notice", "Email not in database!");
			return "login";
		}

		// Validation
		String sessionUserPassword = sessionUser.getPassword();
		boolean isPasswordValid = BCrypt.checkpw(user.getPassword(), sessionUserPassword);
		if (isPasswordValid == false) {
			model.addAttribute("notice", "Invalid Password!");
			return "login";
		}

		sessionUser.setLoggedIn(true);
		request.getSession().setAttribute("SESSION_USER", sessionUser);

		return "redirect:/dashboard";
	}

	/**
	 * This method allows a new user to sign up and then sets them as SESSION_USER
	 * @param user
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/users/signup")
	public String signup(@ModelAttribute User user, Model model, HttpServletRequest request) throws Exception {

		if ((user.getUsername().equals(null) || user.getUsername().isEmpty())
				|| (user.getPassword().equals(null) || user.getPassword().isEmpty())
				|| (user.getEmail().equals(null) || user.getPassword().isEmpty())) {
			model.addAttribute("notice", "Enter a valid username, email and password!");
			return "login";
		}

		try {
			// Encrypt password
			user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			model.addAttribute("notice", "Enter a valid Email!");
			return "login";
		}

		User sessionUser = userRepository.findUserByEmail(user.getEmail());

		try {
			if (sessionUser.equals(null)) {

			}
		} catch (NullPointerException e) {
			model.addAttribute("notice", "User Not in database!");
			return "login";
		}

		sessionUser.setLoggedIn(true);
		request.getSession().setAttribute("SESSION_USER", sessionUser);

		return "redirect:/dashboard";
	}

	/**
	 * This method allows only signed in users to create a team
	 * @param team
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping("/createteam")
	public String addPostDashboardPage(@ModelAttribute Team team, Model model, HttpServletRequest request) {

		if ((team.getTeamName().equals(null) || team.getTeamName().isEmpty())
				|| (team.getTeamDesc().equals(null) || team.getTeamDesc().isEmpty())
				|| (team.getTeamLocation().equals(null))) {
			return "redirect:/dashboard";
		}

		if (request.getSession(false) == null) {
			return "redirect:/login";
		} else {
			teamRepository.save(team);

			return "redirect:/dashboard";
		}
	}


	/**
	 * This method allows a signed in user to write a comment on the teams page, then redirects them to that same team page
	 * @param comment
	 * @param model
	 * @param request
	 * @return
	 */
	@PostMapping("/createcomment")
	public String createCommentLogic(@ModelAttribute Comment comment, Model model, HttpServletRequest request) {

		if (comment.getBody().isEmpty() || comment.getBody().equals(null) || comment.getTitle().equals(null)
				|| comment.getTitle().isEmpty()) {
			return "redirect:/allteams";
		} else {
			if (request.getSession(false) != null) {
				User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
				comment.setUserId(sessionUser.getId());
				commentRepository.save(comment);
				int tempTeamId = comment.getTeamId();
				return "redirect:/singleTeam/" + tempTeamId;
			} else {
				return "login";
			}
		}
	}


	/**
	 * This method allows a signed in user to join a team, uses custom INSERT repo method joinTeam()
	 * @param team
	 * @param request
	 * @param response
	 */
	@PutMapping("/teams/join")
	public void joinATeam(@RequestBody Team team, HttpServletRequest request, HttpServletResponse response) {

		if (request.getSession(false) != null) {
			User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			Integer userId = sessionUser.getId();
			userRepository.joinTeam(userId, team.getId());
		}
	}
	

	/**
	 * This method is triggered when a signed in user completes a puzzle. Uses custom INSERT method puzzleComplete()
	 * @param puzzle
	 * @param request
	 * @param response
	 */
	@PutMapping("/puzzles/completed")
	public void puzzleCompleted(@RequestBody Puzzle puzzle, HttpServletRequest request, HttpServletResponse response) {

		if (request.getSession(false) != null) {
			User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			Integer userId = sessionUser.getId();
			userRepository.puzzleComplete(userId, puzzle.getId());
		}
	}
	
	
}
