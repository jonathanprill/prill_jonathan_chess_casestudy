package com.prill.chess.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "team")
public class Team implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //or .AUTO
	private int id;
	private String teamName;
	private String teamLocation;
	private String teamDesc;

	@OneToMany(mappedBy = "teamId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Comment> comments;

	public Team() {
	}

	public Team(String teamName, String teamLocation, String teamDesc, List<Comment> comments) {
		this.teamName = teamName;
		this.teamLocation = teamLocation;
		this.teamDesc = teamDesc;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamLocation() {
		return teamLocation;
	}

	public void setTeamLocation(String teamLocation) {
		this.teamLocation = teamLocation;
	}

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comments, id, teamDesc, teamLocation, teamName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return Objects.equals(comments, other.comments) && id == other.id && Objects.equals(teamDesc, other.teamDesc)
				&& Objects.equals(teamLocation, other.teamLocation) && Objects.equals(teamName, other.teamName);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", teamName=" + teamName + ", teamLocation=" + teamLocation + ", teamDesc=" + teamDesc
				+ ", comments=" + comments + "]";
	}


}
