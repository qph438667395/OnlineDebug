package com.spring.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TestResult")
public class TestResult {
	@Id 
	@Column(name="testCaseId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int testResultId;
	
	@Column(name="functionResult")
	private String functionResult;
	
	@Column(name="functionPointScore")
	private int functionPointScore;
	
	@Column(name="usedTime")
	private long usedTime;
	
	@Column(name="input")
	private String input;
	
	@Column(name="expectedOutput")
	private String expectedOutput;
	
	@Column(name="actualOutput")
	private String actualOutput;
	
	@Column(name="scoreId")
	private int scoreId;
	
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public int getTestResultId() {
		return testResultId;
	}
	public void setTestResultId(int testResultId) {
		this.testResultId = testResultId;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getExpectedOutput() {
		return expectedOutput;
	}
	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}
	public String getActualOutput() {
		return actualOutput;
	}
	public void setActualOutput(String actualOutput) {
		this.actualOutput = actualOutput;
	}
	
	public long getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(long usedTime) {
		this.usedTime = usedTime;
	}
	public String getFunctionResult() {
		return functionResult;
	}
	public void setFunctionResult(String functionResult) {
		this.functionResult = functionResult;
	}
	public int getFunctionPointScore() {
		return functionPointScore;
	}
	public void setFunctionPointScore(int functionPointScore) {
		this.functionPointScore = functionPointScore;
	}

	
}
