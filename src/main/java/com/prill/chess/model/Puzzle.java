package com.prill.chess.model;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


/**
 * This class creates Puzzle with constructors, getter/setters, toString, hashCode/equals and creates Puzzle Table in SQL Database
 * @author Jonathan Prill
 *
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "puzzle")
public class Puzzle implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //or .AUTO
	private int id;
	private String puzzleName;
	private String puzzleIcon;
	private boolean puzzleStatus;
	
	public Puzzle() {}

	public Puzzle(String puzzleName, String puzzleIcon, boolean puzzleStatus) {
		this.puzzleName = puzzleName;
		this.puzzleIcon = puzzleIcon;
		this.puzzleStatus = puzzleStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPuzzleName() {
		return puzzleName;
	}

	public void setPuzzleName(String puzzleName) {
		this.puzzleName = puzzleName;
	}

	public String getPuzzleIcon() {
		return puzzleIcon;
	}

	public void setPuzzleIcon(String puzzleIcon) {
		this.puzzleIcon = puzzleIcon;
	}

	public boolean isPuzzleStatus() {
		return puzzleStatus;
	}

	public void setPuzzleStatus(boolean puzzleStatus) {
		this.puzzleStatus = puzzleStatus;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, puzzleIcon, puzzleName, puzzleStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puzzle other = (Puzzle) obj;
		return id == other.id && Objects.equals(puzzleIcon, other.puzzleIcon)
				&& Objects.equals(puzzleName, other.puzzleName) && puzzleStatus == other.puzzleStatus;
	}

	@Override
	public String toString() {
		return "Puzzle [id=" + id + ", puzzleName=" + puzzleName + ", puzzleIcon=" + puzzleIcon + ", puzzleStatus="
				+ puzzleStatus + ", userId="  + "]";
	}
	
	
	
	
}
