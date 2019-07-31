package com.spring.service.Interface.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.AdminDao;
import com.spring.dao.QuestionDao;
import com.spring.dao.ScoreDao;
import com.spring.dao.TestCaseDao;
import com.spring.dao.TestResultDao;
import com.spring.dao.UserDao;
import com.spring.entity.Admin;
import com.spring.entity.Question;
import com.spring.entity.Score;
import com.spring.entity.TestCase;
import com.spring.entity.TestResult;
import com.spring.entity.User;
import com.spring.model.PageBean;
import com.spring.model.PageList;
import com.spring.service.Interface.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ScoreDao scoreDao;
	
	@Autowired
	private TestResultDao testResultDao;

	@Override
	public Admin doLogin(String account, String password) {
		Admin admin = null;
		try {
			admin = adminDao.findById(account);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return admin;
	}

	@Override
	public Question findQuestionById(int id) {
		Question question = null;
		try {
			question = questionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	@Override
	public PageList<Question> findQuestion(PageBean pageBean) {
		PageList<Question> list = null;
		try {
			list = questionDao.findAll(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void addQuestion(Question question) throws Exception {
		questionDao.add(question);
	}

	@Override
	public void addTestCase(TestCase testCase) throws Exception {
		testCaseDao.add(testCase);

	}

	@Override
	public List<TestCase> findTestCasesByQuestionId(int questionId) {
		String hql = "from TestCase t where t.questionId =?";
		List<TestCase> list = null;
		try {
			list = testCaseDao.findByPropertys(hql, questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateQuestion(Question question) throws Exception {
		questionDao.update(question);

	}

	@Override
	public void updateTestCase(TestCase testCase) throws Exception {
		testCaseDao.update(testCase);
		
	}

	@Override
	public void deleteQuestion(Question question) throws Exception {
		questionDao.delete(question);
		
	}

	@Override
	public PageList<User> findUser(PageBean pageBean) {
		PageList<User> list = null;
		try {
			list = userDao.findAll(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PageList<Score> findScore(PageBean pageBean) {
		PageList<Score> list = null;
		try {
			list = scoreDao.findAll(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PageList<Score> findScorebyProps(PageBean pageBean, String hql,
			Object... objects) throws Exception {
	 	PageList<Score> scoreList=scoreDao.findbyProps(pageBean, hql, objects);
		return scoreList;
	}

	@Override
	public User findUserByAccount(String account) throws Exception {
		return userDao.findById(account);
	}

	@Override
	public List<TestResult> findTestResultsByScoreId(int scoreId) {
		String hql = "from TestResult t where t.scoreId =?";
		List<TestResult> list = null;
		try {
			list = testResultDao.findByPropertys(hql, scoreId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Score findScoreByScoreId(int scoreId) throws Exception {
		return scoreDao.findById(scoreId);
	}
}
