package com.spring.service.Interface.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.QuestionDao;
import com.spring.dao.ScoreDao;
import com.spring.dao.TestCaseDao;
import com.spring.dao.TestResultDao;
import com.spring.dao.UserDao;
import com.spring.entity.Question;
import com.spring.entity.Score;
import com.spring.entity.TestCase;
import com.spring.entity.TestResult;
import com.spring.entity.User;
import com.spring.model.PageBean;
import com.spring.model.PageList;
import com.spring.model.TestResultList;
import com.spring.service.Interface.Compiler;
import com.spring.service.Interface.IRedirection;
import com.spring.service.Interface.ORedirection;
import com.spring.service.Interface.UserService;
import com.spring.service.Interface.WriteToFile;


@Service
public class UserServiceImpl implements UserService {

	@Resource(name = "writeToJavaFileA")
	private WriteToFile writeToJavaFile;

	@Resource(name = "compilerJavaFileA")
	private Compiler compilerJavaFile;

	@Resource(name = "ORedirectToPipeA")
	private ORedirection oRedirectToPipe;
	
	@Resource(name="IRedirectToPipeA")
	private IRedirection iRedirectToPipe;

	@Resource(name = "runClassA")
	private RunClass runClass;
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	private ScoreDao scoreDao;
	
	@Autowired
	private TestResultDao testResultDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TestResultList testResultList;
	
	@Override
	public boolean doRun(String javaCode, String inputString,
			StringBuffer output) {
		boolean flag = false;
		writeToJavaFile.write(javaCode);
		oRedirectToPipe.oRedirect();
		oRedirectToPipe.clear();
		if (compilerJavaFile.compiler()) {
			if (!"".equals(inputString)){
				iRedirectToPipe.IRedirect();
				iRedirectToPipe.clear();
				iRedirectToPipe.write(inputString);
				iRedirectToPipe.writeClose();
				flag = runClass.run();
				iRedirectToPipe.IReset();
				iRedirectToPipe.readClose();
			}else{
				flag = runClass.run();
			}
			output.append(oRedirectToPipe.read());
		} else {
			output.append(oRedirectToPipe.read());
		}
		oRedirectToPipe.oReset();
		oRedirectToPipe.close();
		return flag;
	}
	
	public PageList<Question> findQuestion(PageBean pageBean){
		 PageList<Question> list=null;
		try {
			list=questionDao.findAll(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Question findQuestionById(int id){
		Question question=null;
		try {
			question=questionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	@Override
	public TestResultList doTest(String javaCode,Question question,User user) {
		writeToJavaFile.write(javaCode);
		int totalScore=0;
		oRedirectToPipe.oRedirect();
		if (compilerJavaFile.compiler()) {
			testResultList.setCompiledTest(true);
			List<TestCase> list=findTestCasesByQuestionId(question.getQuestionId());
			Iterator<TestCase> iterable = list.iterator();
			List<TestResult> results=new ArrayList<TestResult>();
			testResultList.setResultlist(results);
			while (iterable.hasNext()) {
				TestCase testCase= iterable.next();
				totalScore+=testCase.getFunctionPointScore();
				if(question.getQuestionType()==1){
					iRedirectToPipe.IRedirect();
					iRedirectToPipe.clear();
					iRedirectToPipe.write(testCase.getInput());
					iRedirectToPipe.writeClose();
					oRedirectToPipe.oRedirect();
					oRedirectToPipe.clear();
					this.testResult(testCase);
					oRedirectToPipe.oReset();
					oRedirectToPipe.close();		
					iRedirectToPipe.IReset();
					iRedirectToPipe.readClose();
				}else{
					oRedirectToPipe.oRedirect();
					oRedirectToPipe.clear();
					this.testResult(testCase);
					oRedirectToPipe.oReset();
					oRedirectToPipe.close();		
				}
				testResultList.setCompiledInfo(null);
			}
		} else {
			testResultList.setCompiledTest(false);
			testResultList.setResultlist(null);
			testResultList.setCompiledInfo(oRedirectToPipe.read());
			oRedirectToPipe.oReset();
			oRedirectToPipe.close();
		}
		Score score=evaluate(testResultList,totalScore);
		score.setQuestionId(question.getQuestionId());
		score.setTestTime(new Date().toString());
		score.setAccount(user.getAccount());
		saveScore(score);
		testResultList.setScore(score);
		testResultList.setJavaCode(javaCode);
		saveTestResults(testResultList.getResultlist(), score.getScoreId());
		return testResultList;
	}
	
	public void saveTestResults(List<TestResult> testResults,int scoreId){
		Iterator<TestResult> iterable = testResults.iterator();
		while (iterable.hasNext()) {
			TestResult testResult=iterable.next();
			testResult.setScoreId(scoreId);
			try {
				testResultDao.add(testResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void saveScore(Score score){
		try {
			scoreDao.add(score);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Score evaluate(TestResultList testReasultList,int totalScore){
		Score score=new Score();
		if(testReasultList.isCompiledTest()){
			List<TestResult> list=testReasultList.getResultlist();
			Iterator<TestResult> iterable = list.iterator();
			int s=0;
			long usedMaxTime=0;
			while (iterable.hasNext()) {
				TestResult testResult=iterable.next();
				s+=testResult.getFunctionPointScore();
				if(usedMaxTime<testResult.getUsedTime())
					usedMaxTime=testResult.getUsedTime();
			}
			score.setUsedMaxTime(usedMaxTime);
			score.setScore(s);
			if(s==totalScore){
				score.setResult("success");
			}else{
				score.setResult("error");
			}
		}
		return score;
	}
	
	public void testResult(TestCase testCase){
		long startTime=System.currentTimeMillis();
		runClass.run();
		long endTime=System.currentTimeMillis();
		TestResult testResult=new TestResult();	
		String output=oRedirectToPipe.read();
		if(output!=null&&testCase.getExpectedOutput().trim().equals(output.trim())&&(endTime-startTime)<testCase.getTimeLimit()){
			testResult.setFunctionPointScore(testCase.getFunctionPointScore());
			testResult.setFunctionResult("success");
		}else{
			testResult.setFunctionPointScore(0);
			testResult.setFunctionResult("error");
		}
		testResult.setActualOutput(output);
		testResult.setUsedTime(endTime-startTime);
		testResult.setInput(testCase.getInput());
		testResult.setExpectedOutput(testCase.getExpectedOutput());
		testResultList.getResultlist().add(testResult);	
	}
	
	public List<TestCase> findTestCasesByQuestionId(int questionId){
		String hql="from TestCase t where t.questionId =?";
		List<TestCase> list=null;
		try {
			list=testCaseDao.findByPropertys(hql, questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void register(User user) {
		try {
			System.out.println(user.getRealName());
			userDao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User doLogin(String account, String password){
		User user = null;
		try {
			user=userDao.findById(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	
}
