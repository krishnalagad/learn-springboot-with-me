package com.revise.practice_api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "standard_data")
public class Standard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "standard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Student> students = new ArrayList<Student>();

	public Standard() {

	}

	public Standard(int id, String name) {
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Standard [id=" + id + ", name=" + name + ", students=" + students + "]";
	}

	
}