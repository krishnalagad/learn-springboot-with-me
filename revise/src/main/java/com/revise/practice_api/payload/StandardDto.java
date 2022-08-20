package com.revise.practice_api.payload;

import java.util.ArrayList;
import java.util.List;

public class StandardDto {

	private int id;
	private String name;
	
	private List<StudentDto> students = new ArrayList<>();

	public StandardDto() {}

	public StandardDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public List<StudentDto> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDto> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "StandardDto [id=" + id + ", name=" + name + ", students=" + students + "]";
	}

}
