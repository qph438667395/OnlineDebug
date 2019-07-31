package com.spring.model;

import java.io.Serializable;

public class TestCaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String input;

	private String expectedOutput;

	private int functionPointScore;
	
	private long timeLimit;
	
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

	public int getFunctionPointScore() {
		return functionPointScore;
	}

	public void setFunctionPointScore(int functionPointScore) {
		this.functionPointScore = functionPointScore;
	}

	public long getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(long timeLimit) {
		this.timeLimit = timeLimit;
	}


	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	private int questionId;
	private int questionType;

	@Override
	public String toString() {
		return "TestCaseModal[input=" + input + ",expectedOutput=" + expectedOutput
				+ ",functionPointScore=" + functionPointScore + ",timeLimit="
				+ timeLimit + ",questionId="+questionId+",questionType="+questionType+"]";
	}
}
