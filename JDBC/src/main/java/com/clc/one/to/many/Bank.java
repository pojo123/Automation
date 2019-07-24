package com.clc.one.to.many;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

public class Bank {
	
	private int id;
	private String name;
	private String type;
	private String accountNo;
	private Employee employee;
	

}
