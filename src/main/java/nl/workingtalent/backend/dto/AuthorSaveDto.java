package nl.workingtalent.backend.dto;

import nl.workingtalent.backend.entity.Author;

public class AuthorSaveDto {
	private String name;
    private int birthYear;
	private String nationality;
	
	//Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
