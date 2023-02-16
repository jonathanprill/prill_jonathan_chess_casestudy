package com.prill.chess.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // or .AUTO
	private int id;
	private String username;
	private String email;
	private String password;
	private String onlineProfile;
	@Transient
	boolean loggedIn;
	private String userLocation;
	@ManyToMany(targetEntity = Team.class)
	@JoinTable(name = "users_teams", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "teamid"))
	private List<Team> teamList;
	@OneToMany(mappedBy = "userId")
	private List<Comment> commentList;
	@ManyToMany(targetEntity = Puzzle.class)
	@JoinTable(name = "users_puzzles", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "puzzleid"))
	private List<Puzzle> puzzleList;

	public User() {
	}

	public User(String username, String email, String password, String onlineProfile, String userLocation,
			List<Team> teamList, List<Comment> commentList, List<Puzzle> puzzleList) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.onlineProfile = onlineProfile;
		this.userLocation = userLocation;
		this.teamList = teamList;
		this.commentList = commentList;
		this.puzzleList = puzzleList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOnlineProfile() {
		return onlineProfile;
	}

	public void setOnlineProfile(String onlineProfile) {
		this.onlineProfile = onlineProfile;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<Puzzle> getPuzzleList() {
		return puzzleList;
	}

	public void setPuzzleList(List<Puzzle> puzzleList) {
		this.puzzleList = puzzleList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentList, email, id, onlineProfile, loggedIn, password, puzzleList, teamList, userLocation,
				username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(commentList, other.commentList) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(onlineProfile, other.onlineProfile) && Objects.equals(password, other.password)
				&& Objects.equals(puzzleList, other.puzzleList) && Objects.equals(teamList, other.teamList)
				&& Objects.equals(userLocation, other.userLocation) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", onlineProfile=" + onlineProfile + ", userLocation=" + userLocation + ", teamList=" + teamList
				+ ", commentList=" + commentList + ", puzzleList=" + puzzleList +  
                ", loggedIn=" + loggedIn + "]";
	}

}
