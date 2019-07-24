package com.clc.one.to.many;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class Employee {
	
	private int id;
	private String name;
	private float salary;
	private List<Bank> listOfBanks;
	

}
