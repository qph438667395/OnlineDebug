package com.spring.service.Interface;

import com.spring.entity.Question;
import com.spring.entity.User;
import com.spring.model.PageBean;
import com.spring.model.PageList;
import com.spring.model.TestResultList;

public interface UserService {
	public boolean doRun(String javaCode,String inputString,StringBuffer output);
	public TestResultList doTest(String javaCode,Question question,User user);
	public void register(User user);
	public User doLogin(String account, String password);
	public PageList<Question> findQuestion(PageBean pageBean);
	public Question findQuestionById(int id);
}
