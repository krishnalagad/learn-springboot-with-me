package com.revise.practice_api.payload;

import com.revise.practice_api.entities.Standard;

public class StudentDto2 {

	private int id;

	private String name;
	private String mob;
	
	private Standard standard;

	public StudentDto2() {
		
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

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	@Override
	public String toString() {
		return "StudentDto2 [id=" + id + ", name=" + name + ", mob=" + mob + ", standard=" + standard + "]";
	}
	
}
