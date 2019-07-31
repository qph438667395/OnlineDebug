package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.Admin;
import com.spring.entity.Question;
import com.spring.entity.Score;
import com.spring.entity.TestCase;
import com.spring.entity.TestResult;
import com.spring.entity.User;
import com.spring.model.LoginForm;
import com.spring.model.PageBean;
import com.spring.model.PageList;
import com.spring.model.TestCaseModel;
import com.spring.service.Interface.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping("/aindex")
	public String toIndex() {
		return "aindex";
	}

	@RequestMapping("/atoLogin")
	public String toLogin() {
		return "alogin";
	}

	@RequestMapping("/adoLogin")
	public String checkLoginForm(@Valid LoginForm lForm, BindingResult result,
			ModelMap map, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError error : errorList) {
				map.put("ERR_" + error.getField(), error.getDefaultMessage());
			}
			return "alogin";
		}
		return doLogin(lForm, model, request);
	}

	public String doLogin(LoginForm lForm, Model model,
			HttpServletRequest request) {
		Admin admin = adminService.doLogin(lForm.getAccount(),
				lForm.getPassword());
		if (admin == null) {
			model.addAttribute("ERR_account", "账号不存在");
			return "alogin";
		}
		if (!admin.getPassword().equals(lForm.getPassword())) {
			model.addAttribute("ERR_password", "密码错误");
			return "alogin";
		}
		request.getSession().setAttribute("admin", admin);
		return "aindex";
	}

	@RequestMapping("/aselectQuestion")
	public String toSelectQuestion(Model model) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(10);
		PageList<Question> list = adminService.findQuestion(pageBean);
		model.addAttribute("pageList", list);
		return "aselectQuestion";
	}

	@RequestMapping("/aselectUser")
	public String selectUser(Model model) {
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(9);
		PageList<User> list = adminService.findUser(pageBean);
		model.addAttribute("pageList", list);
		return "aselectUser";
	}
	
	@RequestMapping("/afindUserByAccount")
	public void findUserByAccount(HttpServletRequest request,HttpServletResponse response){
		String account="";
		account=request.getParameter("account");
		PrintWriter out = null;
		User user=null;
		try {
			user=adminService.findUserByAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(user!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("<tr><td>");
			sb.append(user.getAccount());
			sb.append("</td><td>");
			sb.append(user.getRealName());
			sb.append("</td><td>");
			if(user.getSex()==1){
				sb.append('男');
			}else{
				sb.append('女');
			}
			sb.append("</td><td>");
			sb.append(user.getBirthday());
			sb.append("</td></tr>");
			out.print(sb.toString());
		}else{
			out.print("");
		}
		
	}

	@RequestMapping("/afindScores")
	public void findScores(Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chaarset=utf-8");
		String classId = request.getParameter("classId");
		String c = request.getParameter("condition");
		int conition = 0;
		if (null != c) {
			conition = Integer.parseInt(c);
		}
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(9);
		PageList<Score> pageList = null;
		if (null == classId || "0".equals(classId) || conition == 0) {
			pageList = adminService.findScore(pageBean);
		} else if ("1".equals(classId)) {
			String hql = "from Score t where t.questionId =?";
			pageList = adminService.findScorebyProps(pageBean, hql, conition);
		} else if ("2".equals(classId)) {
			String hql = "from Score t where t.account =?";
			pageList = adminService.findScorebyProps(pageBean, hql, conition);
		}
		List<Score> list = pageList.getResultList();
		Iterator<Score> iterable = list.iterator();
		StringBuilder sb = new StringBuilder();
		while (iterable.hasNext()) {
			Score score = iterable.next();
			sb.append("<tr><td>");
			sb.append(score.getAccount());
			sb.append("</td><td>");
			Question question = adminService.findQuestionById(score
					.getQuestionId());
			sb.append(question.getQuestionTitle());
			sb.append("</td><td>");
			sb.append(score.getUsedMaxTime());
			sb.append("</td><td>");
			sb.append(score.getScore());
			sb.append("</td><td>");
			sb.append(score.getResult());
			sb.append("</td><td>");
			sb.append(score.getTestTime());
			sb.append("</td><td>");
			sb.append("<a href='ascoreDetails?scoreId=" + score.getScoreId()
					+ "'>详情" + "</a> ");
			sb.append("</td></tr>");
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("tableResult", sb.toString());
		jsonObject.put("pageCount", pageList.getPageBean().getTotalPage());
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(jsonObject.toString());
	}
	
	@RequestMapping("/ascoreDetails")
	public String findScoreDetails(HttpServletRequest request,Model model) throws Exception{
		String string=request.getParameter("scoreId");
		int scoreId=0;
		if(string!=null||"".equals(string)){
			scoreId=Integer.parseInt(string);
		}
		List<TestResult> testResults=adminService.findTestResultsByScoreId(scoreId);
		Score score=adminService.findScoreByScoreId(scoreId);
		Question question=adminService.findQuestionById(score.getQuestionId());
		model.addAttribute("score", score);
		model.addAttribute("question", question);
		model.addAttribute("testResults",testResults);
		return "ascoreDetails";
	}

	@RequestMapping("/aselectScore")
	public String selectScore(Model model, HttpServletRequest request)
			throws Exception {
		PageBean pageBean = new PageBean();
		pageBean.setPage(1);
		pageBean.setPageSize(9);
		PageList<Score> list = null;
		list = adminService.findScore(pageBean);
		List<String> questTitleList = new ArrayList<String>();
		for (Score score : list.getResultList()) {
			String string = adminService
					.findQuestionById(score.getQuestionId()).getQuestionTitle();
			questTitleList.add(string);
		}
		model.addAttribute("pageList", list);
		model.addAttribute("questTitleList", questTitleList);
		return "aselectScore";
	}

	@RequestMapping(value = "/afindScore{pageId}")
	public void findScore(@PathVariable int pageId,
			HttpServletResponse response, HttpServletRequest request) throws Exception {
		PrintWriter out = null;
		String classId = request.getParameter("classId");
		String c = request.getParameter("condition");
		int conition = 0;
		if (null != c) {
			conition = Integer.parseInt(c);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chaarset=utf-8");
		PageBean pageBean = new PageBean();
		pageBean.setPage(pageId);
		pageBean.setPageSize(9);
		PageList<Score> pageList = null;
		if (null == classId || "0".equals(classId)) {
			pageList = adminService.findScore(pageBean);
		} else if ("1".equals(classId)) {
			String hql = "from Score t where t.questionId =?";
			pageList = adminService.findScorebyProps(pageBean, hql, conition);
		} else if ("2".equals(classId)) {
			String hql = "from Score t where t.account =?";
			pageList = adminService.findScorebyProps(pageBean, hql, conition);
		}
		List<Score> list = pageList.getResultList();
		Iterator<Score> iterable = list.iterator();
		StringBuilder sb = new StringBuilder();
		while (iterable.hasNext()) {
			Score score = iterable.next();
			sb.append("<tr><td>");
			sb.append(score.getAccount());
			sb.append("</td><td>");
			Question question = adminService.findQuestionById(score
					.getQuestionId());
			sb.append(question.getQuestionTitle());
			sb.append("</td><td>");
			sb.append(score.getUsedMaxTime());
			sb.append("</td><td>");
			sb.append(score.getScore());
			sb.append("</td><td>");
			sb.append(score.getResult());
			sb.append("</td><td>");
			sb.append(score.getTestTime());
			sb.append("</td><td>");
			sb.append("<a href='ascoreDetails?scoreId=" + score.getScoreId()
					+ "'>详情" + "</a> ");
			sb.append("</td></tr>");
		}
		String str = sb.toString();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(str);
	}

	@RequestMapping(value = "/afindUser{pageId}")
	public void findUser(@PathVariable int pageId,
			HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chaarset=utf-8");
		PageBean pageBean = new PageBean();
		pageBean.setPage(pageId);
		pageBean.setPageSize(9);
		PageList<User> pageList = adminService.findUser(pageBean);
		List<User> list = pageList.getResultList();
		Iterator<User> iterable = list.iterator();
		StringBuilder sb = new StringBuilder();
		while (iterable.hasNext()) {
			User user = iterable.next();
			sb.append("<tr><td>");
			sb.append(user.getAccount());
			sb.append("</td><td>");
			sb.append(user.getRealName());
			sb.append("</td><td>");
			if (user.getSex() == 1) {
				sb.append('男');
			} else {
				sb.append('女');
			}
			sb.append("</td><td>");
			sb.append(user.getBirthday());
			sb.append("</td></tr>");
		}
		String str = sb.toString();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(str);
	}

	@RequestMapping(value = "/afindQuestion{userId}")
	public void findQuestion(@PathVariable int pageId,
			HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chaarset=utf-8");
		PageBean pageBean = new PageBean();
		pageBean.setPage(pageId);
		pageBean.setPageSize(10);
		PageList<Question> pageList = adminService.findQuestion(pageBean);
		List<Question> list = pageList.getResultList();
		Iterator<Question> iterable = list.iterator();
		StringBuilder sb = new StringBuilder();
		while (iterable.hasNext()) {
			Question question = iterable.next();
			sb.append("<tr><td>");
			sb.append(question.getQuestionId());
			sb.append("</td><td>");
			sb.append(question.getDifficulty());
			sb.append("</td><td>");
			sb.append(question.getQuestionTitle());
			sb.append("</td><td>");
			sb.append("<a href='adeleteQuest?questionId="
					+ question.getQuestionId() + "'>删除" + "</a> | ");
			sb.append("<a href='aupdateQuest?questionId="
					+ question.getQuestionId() + "'>修改" + "</a> | ");
			sb.append("<a href='atoQueDetails?questionId="
					+ question.getQuestionId() + "'>详情" + "</a>");
			sb.append("</td></tr>");
		}
		String str = sb.toString();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(str);
	}

	@RequestMapping("/atoQueDetails")
	public String toQuestionDetails(Model model, int questionId) {
		Question question = null;
		try {
			question = adminService.findQuestionById(questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TestCase> testCases = adminService
				.findTestCasesByQuestionId(questionId);
		model.addAttribute("testCases", testCases);
		model.addAttribute("question", question);
		return "aqueDetails";
	}

	@RequestMapping("/atoAddQuestion")
	public String toAddQuestion() {
		return "aaddquestion";
	}

	@RequestMapping("/adoAddQuestion")
	public void doAddQuestion(@Valid Question question, BindingResult result,
			ModelMap map, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (result.hasErrors()) {
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError error : errorList) {
				jsonObject.put("ERR_" + error.getField(),
						error.getDefaultMessage());
			}
		} else {
			try {
				adminService.addQuestion(question);
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("resultStatus", "fail");
			}
			jsonObject.put("resultStatus", "success");
		}
		jsonObject.put("questionId", question.getQuestionId());
		jsonObject.put("questionType", question.getQuestionType());
		String jsonString = jsonObject.toString();
		out.print(jsonString);
	}

	@RequestMapping("/adoAddTestCase")
	public void doAddTestCase(@RequestBody List<TestCaseModel> testCases,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String str = checkTestCases(testCases);
		if (str != null) {
			jsonObject.put("checkResult", str);
		} else {
			try {
				addTestCases(testCases);
			} catch (Exception e) {
				jsonObject.put("addResult", "fail");
				e.printStackTrace();
			}
			jsonObject.put("addResult", "success");
		}
		String jsonString = jsonObject.toString();
		out.print(jsonString);
	}

	private void addTestCases(List<TestCaseModel> testCaseModals)
			throws Exception {
		List<TestCase> testCases = new ArrayList<TestCase>();
		for (TestCaseModel testCaseModal : testCaseModals) {
			TestCase testCase = null;
			if (testCaseModal.getQuestionType() == 2) {
				testCase = new TestCase(testCaseModal.getExpectedOutput(),
						testCaseModal.getQuestionId(),
						testCaseModal.getFunctionPointScore(),
						testCaseModal.getTimeLimit());
			} else {
				testCase = new TestCase(testCaseModal.getInput(),
						testCaseModal.getExpectedOutput(),
						testCaseModal.getQuestionId(),
						testCaseModal.getFunctionPointScore(),
						testCaseModal.getTimeLimit());
			}
			testCases.add(testCase);
		}
		for (TestCase testCase : testCases) {
			adminService.addTestCase(testCase);
		}
	}

	private String checkTestCases(List<TestCaseModel> testCases) {
		for (TestCaseModel testCase : testCases) {
			if (testCase.getQuestionId() == 0
					|| 0 == testCase.getQuestionType()) {
				return "请先录入题目";
			}
			if (1 == testCase.getQuestionType()) {
				if ("".equals(testCase.getInput())) {
					return "有输入的题目，输入不允许为空";
				}
			}
			if ("".equals(testCase.getExpectedOutput())
					|| 0 == testCase.getTimeLimit()
					|| 0 == testCase.getFunctionPointScore()) {
				return "不允许出现空值";
			}
		}
		return null;

	}

	@RequestMapping("/aupdateQuest")
	public String updateQuest(Model model, int questionId) {
		Question question = null;
		try {
			question = adminService.findQuestionById(questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TestCase> testCases = adminService
				.findTestCasesByQuestionId(questionId);
		model.addAttribute("testCases", testCases);
		model.addAttribute("question", question);
		return "aupdateQuestion";
	}

	@RequestMapping("/adoUpdateQuestion")
	public void doUpdateQuestion(@Valid Question question,
			BindingResult result, ModelMap map, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (result.hasErrors()) {
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError error : errorList) {
				jsonObject.put("ERR_" + error.getField(),
						error.getDefaultMessage());
			}
		} else {
			try {
				adminService.updateQuestion(question);
			} catch (Exception e) {
				e.printStackTrace();
				jsonObject.put("resultStatus", "fail");
			}
			jsonObject.put("resultStatus", "success");
		}
		String jsonString = jsonObject.toString();
		out.print(jsonString);
	}

	@RequestMapping("/adoUpdateTestCase")
	public void doUpdateTestCase(@RequestBody List<TestCase> testCases,
			HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String str = checkTestCase(testCases);
		if (str != null) {
			jsonObject.put("checkResult", str);
		} else {
			try {
				updateTestCases(testCases);
			} catch (Exception e) {
				jsonObject.put("addResult", "fail");
				e.printStackTrace();
			}
			jsonObject.put("addResult", "success");
		}
		String jsonString = jsonObject.toString();
		out.print(jsonString);
	}

	private String checkTestCase(List<TestCase> testCases) {
		for (TestCase testCase : testCases) {
			if ("".equals(testCase.getExpectedOutput())
					|| 0 == testCase.getTimeLimit()
					|| 0 == testCase.getFunctionPointScore()) {
				return "不允许出现空值";
			}
		}
		return null;

	}

	private void updateTestCases(List<TestCase> testCases) throws Exception {
		for (TestCase testCase : testCases) {
			adminService.updateTestCase(testCase);
		}
	}

	@RequestMapping("/adeleteQuest")
	public String deleteQuestion(Question question, Model model) {
		try {
			adminService.deleteQuestion(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toSelectQuestion(model);
	}

}
