package com.spring.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class URegisterForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Pattern(regexp="[0-9a-zA-Z_]{6,12}",message="{Pattern.account}")
	private String account;
	
	@Pattern(regexp="[0-9a-zA-Z_]{8,16}",message="{Pattern.password}")
	private String password;
	
	@Pattern(regexp="[0-9a-zA-Z_]{8,16}",message="{Pattern.password}")
	private String qpassword;
	
	@NotBlank(message="{Pattern.notEmpty}")
	private String birthday;
	
	@NotBlank(message="{Pattern.notEmpty}")
	private String realName;
	
	@Range(min=1,max=2,message="{Pattern.sex}")
	private int sex;

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

	public String getQpassword() {
		return qpassword;
	}

	public void setQpassword(String qpassword) {
		this.qpassword = qpassword;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName=realName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}
}
