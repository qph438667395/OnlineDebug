package com.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TestCase")
public class TestCase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "testCaseId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testCaseId;

	@Column(name = "input")
	private String input;

	@Column(name = "expectedOutput")
	private String expectedOutput;

	@Column(name = "questionId")
	private int questionId;

	@Column(name = "functionPointScore")
	private int functionPointScore;

	@Column(name = "timeLimit")
	private long timeLimit;

	public TestCase() {
		
	}
	
	public TestCase(String input,String expectedOutput,int questIonId,int functionPointScore,long timeLimit) {
		this.input=input;
		this.expectedOutput=expectedOutput;
		this.questionId=questIonId;
		this.functionPointScore=functionPointScore;
		this.timeLimit=timeLimit;
	}
	
	public TestCase(String expectedOutput,int questIonId,int functionPointScore,long timeLimit) {
		this.expectedOutput=expectedOutput;
		this.questionId=questIonId;
		this.functionPointScore=functionPointScore;
		this.timeLimit=timeLimit;
	}
	
	
	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getFunctionPointScore() {
		return functionPointScore;
	}

	public void setFunctionPointScore(int functionPointScore) {
		this.functionPointScore = functionPointScore;
	}

	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public TestCase(String input,String expectedOutput,int functionPointScore,long timeLimit) {
		this.input=input;
		this.expectedOutput=expectedOutput;
		this.functionPointScore=functionPointScore;
		this.timeLimit=timeLimit;
	}
	
	@Override
	public String toString() {
		return "TestCase[input=" + input + ",expectedOutput=" + expectedOutput
				+ ",functionPointScore=" + functionPointScore + ",timeLimit="
				+ timeLimit + ",questionId="+questionId+",testCaseId="+testCaseId+"]";
	}
	

}
