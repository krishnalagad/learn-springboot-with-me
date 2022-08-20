package com.revise.practice_api.payload;

public class ParentDto {

	private int id;
	private String name;
	private String mob;

	public ParentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParentDto(int id, String name, String mob) {
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

}
