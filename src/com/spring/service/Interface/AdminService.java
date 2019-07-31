package com.spring.service.Interface;

import java.util.List;

import com.spring.entity.Admin;
import com.spring.entity.Question;
import com.spring.entity.Score;
import com.spring.entity.TestCase;
import com.spring.entity.TestResult;
import com.spring.entity.User;
import com.spring.model.PageBean;
import com.spring.model.PageList;

public interface AdminService {
	public Admin doLogin(String account, String password);
	public PageList<Question> findQuestion(PageBean pageBean);
	public PageList<User> findUser(PageBean pageBean);
	public PageList<Score> findScore(PageBean pageBean);
	public PageList<Score> findScorebyProps(PageBean pageBean,final String hql, final Object... objects) throws Exception;
	public Question findQuestionById(int id) throws Exception;
	public User findUserByAccount(String account) throws Exception;
	public Score findScoreByScoreId(int scoreId) throws Exception;
	public List<TestCase> findTestCasesByQuestionId(int questionId);	
	public List<TestResult> findTestResultsByScoreId(int scoreId);	
	public void addQuestion(Question question) throws Exception;
	public void updateQuestion(Question question) throws Exception;
	public void deleteQuestion(Question question) throws Exception;
	public void addTestCase(TestCase testCase) throws Exception;
	public void updateTestCase(TestCase testCase) throws Exception;
}
