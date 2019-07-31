package com.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="Question")
public class Question implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id 
	@Column(name="questionId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int questionId;
	
	
	@Column(name="questionTitle")
	@NotBlank(message="{Pattern.notEmpty}")
	private String questionTitle;
	
	
	@Column(name="questionContent")
	@NotBlank(message="{Pattern.notEmpty}")
	private String questionContent;
	
	@Column(name="difficulty")
	@Range(min=1,max=4,message="{Pattern.notEmpty}")
	private int difficulty;
	
	@Column(name="questionType")
	@Range(min=1,max=2,message="{Pattern.notEmpty}")
	private int questionType;
	
	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}




}
