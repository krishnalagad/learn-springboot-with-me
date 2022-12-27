package com.revise.custome_finder_methods.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
