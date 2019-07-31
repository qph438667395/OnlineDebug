package com.spring.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class LoginForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Pattern(regexp="[0-9a-zA-Z_]{6,12}",message="{Pattern.account}")
	private String account;
	
	@Pattern(regexp="[0-9a-zA-Z_]{8,16}",message="{Pattern.password}")
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
