package com.prill.chess.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //or .AUTO
	private int id;
	private String title;
	private String body;
	@Temporal(TemporalType.DATE)
	@Column(name = "timestamp")
	private Date timestamp = new Date();
	private Integer teamId;
	private Integer userId;

	public Comment() {
	}

	public Comment(String title, String body, Date timestamp, Integer teamId, Integer userId) {
		this.title = title;
		this.body = body;
		this.timestamp = timestamp;
		this.teamId = teamId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(body, id, teamId, timestamp, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(body, other.body) && id == other.id && Objects.equals(teamId, other.teamId)
				&& Objects.equals(timestamp, other.timestamp) && Objects.equals(title, other.title)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", title=" + title + ", body=" + body + ", timestamp=" + timestamp + ", teamId="
				+ teamId + ", userId=" + userId + "]";
	}

}
