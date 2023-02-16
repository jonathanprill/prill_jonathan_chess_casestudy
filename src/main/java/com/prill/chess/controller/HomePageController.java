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

//	Login Route
	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		return "login";
	}

//	Logout Route
	@GetMapping("/users/logout")
	public String logout(HttpServletRequest request) {
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}
		return "redirect:/login";
	}

//	Home page Route
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
	
//	View ALL Teams Route
	@GetMapping("/allteams")
	public String createTeamsPage(Model model, HttpServletRequest request) {
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

		return "teams-page";
	}

//	Dashboard Route
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
	
//	Single team route
	@GetMapping("/singleTeam/{id}")
	public String createSingleTeamPage(@PathVariable int id, Model model, HttpServletRequest request) {
		setupSingleTeamPage(id, model, request);
		return "single-team";
	}

	public Model setupSingleTeamPage(int id, Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
			model.addAttribute("sessionUser", sessionUser);
			model.addAttribute("loggedIn", sessionUser.isLoggedIn());
		}
		Optional<Team> tempTeam = teamRepository.findById(id);
		Team team = tempTeam.get();

		List<Comment> commentList = commentRepository.findAllCommentsByTeamId(team.getId());
		
		model.addAttribute("team", team);
		model.addAttribute("commentList", commentList);
		model.addAttribute("comment", new Comment());
		return model;
	}
	
	
//	View ALL Puzzles Route
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
//	    Possible list out users here

		model.addAttribute("puzzleList", puzzleList);
		model.addAttribute("loggedIn", sessionUser.isLoggedIn());

		return "puzzles-page";
	}
	
//	Single Puzzle route
	@GetMapping("/singlePuzzle/{id}")
	public String createSinglePuzzlePage(@PathVariable int id, Model model, HttpServletRequest request) {
		setupSinglePuzzlePage(id, model, request);
		return "single-puzzle";
	}

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
