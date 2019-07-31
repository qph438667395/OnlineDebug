package com.spring.model;

import java.util.List;

import com.spring.entity.Score;
import com.spring.entity.TestResult;

public class TestResultList {
	private String javaCode;
	private List<TestResult> resultlist;
	private Score score;
	private boolean compiledTest;
	private String compiledInfo;
	
	public String getJavaCode() {
		return javaCode;
	}
	public void setJavaCode(String javaCode) {
		this.javaCode = javaCode;
	}
	public String getCompiledInfo() {
		return compiledInfo;
	}
	public void setCompiledInfo(String compiledInfo) {
		this.compiledInfo = compiledInfo;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	
	public boolean isCompiledTest() {
		return compiledTest;
	}
	public void setCompiledTest(boolean compiledTest) {
		this.compiledTest = compiledTest;
	}
	public TestResultList(){
		
	}
	public TestResultList(List<TestResult> list,boolean compilerReasult){
		this.resultlist=list;
	}
	public List<TestResult> getResultlist() {
		return resultlist;
	}
	public void setResultlist(List<TestResult> resultlist) {
		this.resultlist = resultlist;
	}
	
	
	
}
