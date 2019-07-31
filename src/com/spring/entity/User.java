package com.spring.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.spring.model.URegisterForm;

@Entity
@Table(name="User")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="account")
	private String account;
	
	@Column(name="password")
	private String password;
	
	@Column(name="birthday")
	private String birthday;
	
	@Column(name="realName")
	private String realName;
	
	@Column(name="sex")
	private int sex;

	public User() {
		
	}
	
	public  User(URegisterForm uForm) {
		this.account=uForm.getAccount();
		this.password=uForm.getPassword();
		this.sex=uForm.getSex();
		this.realName=uForm.getRealName();
		this.birthday=uForm.getBirthday();
	}
	
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
