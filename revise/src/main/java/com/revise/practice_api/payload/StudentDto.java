package com.revise.practice_api.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentDto {

	private int id;
	private String name;
	private String mob;

	@JsonIgnore
	private StandardDto standard;

	public StudentDto() {

	}

	public StudentDto(int id, String name, String mob) {
		super();
		this.id = id;
		this.name = name;
		this.mob = mob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public StandardDto getStandard() {
		return standard;
	}

	public void setStandard(StandardDto standard) {
		this.standard = standard;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", mob=" + mob + ", standard=" + standard + "]";
	}

}
