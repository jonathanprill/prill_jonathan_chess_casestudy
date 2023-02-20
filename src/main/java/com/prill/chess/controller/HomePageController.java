package com.prill.chess.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prill.chess.model.Comment;
import com.prill.chess.model.Puzzle;
import com.prill.chess.model.Team;
import com.prill.chess.model.User;
import com.prill.chess.repository.CommentRepository;
import com.prill.chess.repository.PuzzleRepository;
import com.prill.chess.repository.TeamRepository;
import com.prill.chess.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

/**
 * This Class is used as a Controller that hits GET routes involved in the application when running in the browser
 * @author Jonathan Prill
 *
 */
@Controller
public class HomePageController {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PuzzleRepository puzzleRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	UserRepository userRepository;


	/**
	 * This method GETS login.html when /login route is hit
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		return "login";
	}

	/**
	 * This method routes to /users/logout when a logged in user clicks logout button. It then invalidates their session.
	 * @param request
	 * @return
	 */
	@GetMapping("/users/logout")
	public String logout(HttpServletRequest request) {
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		return "redirect:/login";
	}


	/**
	 * This method GETS the (/) route and renders homepage.html. 
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/")
	public String createHomepage(Model model, HttpServletRequest request) {
		User sessionUser = new User();

		if (request.getSession(false) != null) {
			sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		} else {
			model.addAttribute("loggedIn", false);
		}

		List<Team> teamList = teamRepository.findAll();
//	    Possible list out users here

		model.addAttribute("teamList", teamList);
		model.addAttribute("loggedIn", sessionUser.isLoggedIn());

		return "homepage";
	}
	

	/**
	 * This method hit route /allteams and renders the teams-page if the user if logged in.
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/allteams")
	public String createTeamsPage(Model model, HttpServletRequest request) {
		User sessionUser = new User();

		if (request.getSession(false) != null) {
			sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		} else {
			model.addAttribute("loggedIn", false);
			return "redirect:/login";
		}

		List<Team> teamList = teamRepository.findAll();
//	    Possible list out users here
		model.addAttribute("user", sessionUser);
		model.addAttribute("teamList", teamList);
		model.addAttribute("loggedIn", sessionUser.isLoggedIn());

		return "teams-page";
	}


	/**
	 * If a user is logged in this method routes to /dashboard rendering dashboard.html
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/dashboard")
	public String createDashboard(Model model, HttpServletRequest request) throws Exception {
		if (request.getSession(false) != null) {
			setupDashboard(model, request);
			return "dashboard";
		} else {
			model.addAttribute("user", new User());
			return "login";
		}
	}

	/**
	 * This method is a tool for createDashboard. It contains all the logic for rendering the users teams and puzzles
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Model setupDashboard(Model model, HttpServletRequest request) throws Exception {
		User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
		Integer userId = sessionUser.getId();
		List<Team> teamList = teamRepository.findAllTeamsByUserId(userId);
		List<Puzzle> puzzleList = puzzleRepository.findAllPuzzlesByUserId(userId);
		
		model.addAttribute("user", sessionUser);
		model.addAttribute("teamList", teamList);
		model.addAttribute("puzzleList", puzzleList);
		model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		model.addAttribute("team", new Team());
		return model;
	}
	

	/**
	 * This method directs to singleTeam/{id} rendering one teams page
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/singleTeam/{id}")
	public String createSingleTeamPage(@PathVariable int id, Model model, HttpServletRequest request) {
		setupSingleTeamPage(id, model, request);
		return "single-team";
	}

	/**
	 * This method is a tool for createSingleTeamPage. It contains all the logic for rendering the teams info and comments
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	public Model setupSingleTeamPage(int id, Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("sessionUser", sessionUser);
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		}
		Optional<Team> tempTeam = teamRepository.findById(id);
		Team team = tempTeam.get();
//	    Possible list out users here
		List<Comment> commentList = commentRepository.findAllCommentsByTeamId(team.getId());
		
		model.addAttribute("team", team);
		model.addAttribute("commentList", commentList);
		model.addAttribute("comment", new Comment());
		return model;
	}
	
	

	/**
	 * This method renders puzzle-page when a logged in user hits the route /allpuzzles, this page lists all puzzles in the db
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/allpuzzles")
	public String createPuzzlesPage(Model model, HttpServletRequest request) {
		User sessionUser = new User();

		if (request.getSession(false) != null) {
			sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		} else {
			model.addAttribute("loggedIn", false);
		}

		List<Puzzle> puzzleList = puzzleRepository.findAll();

		model.addAttribute("puzzleList", puzzleList);
		model.addAttribute("loggedIn", sessionUser.isLoggedIn());

		return "puzzles-page";
	}
	

	/**
	 * This method renders single-puzzle.html when singlePuzzle/{id} is hit. It renders that specific puzzles attributes
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/singlePuzzle/{id}")
	public String createSinglePuzzlePage(@PathVariable int id, Model model, HttpServletRequest request) {
		setupSinglePuzzlePage(id, model, request);
		return "single-puzzle";
	}

	/**
	 * This method is a tool for createSinglePuzzlePage. It contains all the logic for rendering the puzzles info
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	public Model setupSinglePuzzlePage(int id, Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("sessionUser", sessionUser);
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		}
		Optional<Puzzle> tempPuzzle = puzzleRepository.findById(id);
		Puzzle puzzle = tempPuzzle.get();

		model.addAttribute("puzzle", puzzle);
		return model;
	}

}
