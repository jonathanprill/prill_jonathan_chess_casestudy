package com.prill.chess.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //or .AUTO
	private int id;
	private String username;
	private String email;
	private String password;
	private String onlineProfile;
	private String userLocation;
	@ManyToMany(targetEntity = Team.class)
	private Set<Team> teamSet;
	@OneToMany(mappedBy = "userId")
	private Set<Comment> commentSet;
	@OneToMany(mappedBy = "userId")
	private Set<Puzzle> puzzleSet;
	

	public User() {}

	public User(String username, String email, String password, String onlineProfile, String userLocation,
			Set<Team> teamSet, Set<Comment> commentSet) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.onlineProfile = onlineProfile;
		this.userLocation = userLocation;
		this.teamSet = teamSet;
		this.commentSet = commentSet;
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

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public Set<Team> getTeamSet() {
		return teamSet;
	}

	public void setTeamSet(Set<Team> teamSet) {
		this.teamSet = teamSet;
	}

	public Set<Comment> getCommentSet() {
		return commentSet;
	}

	public void setCommentSet(Set<Comment> commentSet) {
		this.commentSet = commentSet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, onlineProfile, password, userLocation, username);
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
		return Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(onlineProfile, other.onlineProfile) && Objects.equals(password, other.password)
				&& Objects.equals(userLocation, other.userLocation) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", onlineProfile=" + onlineProfile + ", userLocation=" + userLocation + "]";
	}
	

	
	

}
