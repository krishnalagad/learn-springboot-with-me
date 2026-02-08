package com.revise.custome_finder_methods.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "jpa_module")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Module {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int mId;
	
	private String moduleName;
	private String moduleHead;
	private int teamSize;

}