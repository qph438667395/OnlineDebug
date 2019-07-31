package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Score")
public class Score {
	@Id
	@Column(name="scoreId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int scoreId;
	
	@Column(name="questionId")
	private int questionId;
	
	@Column(name="score")
	private int score;
	
	@Column(name="result")
	private String result;
	
	@Column(name="usedMaxTime")
	private long usedMaxTime;
	
	@Column(name="testTime")
	private String testTime;
	
	@Column(name="account")
	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getUsedMaxTime() {
		return usedMaxTime;
	}

	public void setUsedMaxTime(long usedMaxTime) {
		this.usedMaxTime = usedMaxTime;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	
	
}
