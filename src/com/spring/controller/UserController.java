package com.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.Question;
import com.spring.entity.User;
import com.spring.model.LoginForm;
import com.spring.model.PageBean;
import com.spring.model.PageList;
import com.spring.model.TestResultList;
import com.spring.model.URegisterForm;
import com.spring.service.Interface.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;

	@Autowired
	private JSONObject jsonObject;

	@Autowired
	private TestResultList testResultList;

	@Autowired
	private PageBean pageBean;

	@RequestMapping("/uprogramming")
	public String toProgramming() {
		return "uprogramming";
	}

	@RequestMapping("/uindex")
	public String toindex() {
		return "uindex";
	}

	@RequestMapping("/utoLogin")
	public String toLogin() {
		return "ulogin";
	}

	@RequestMapping("/uselectQuestion")
	public String toSelectQuestion(Model model) {
		pageBean.setPage(1);
		PageList<Question> list = userService.findQuestion(pageBean);
		model.addAttribute("pageList", list);
		return "uselectQuestion";
	}

	@RequestMapping("/udoRun")
	public void doRun(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chaarset=utf-8");
		String javaCode = request.getParameter("javaCode");
		String inputString = request.getParameter("input");
		StringBuffer outputString = new StringBuffer();
		boolean result = userService.doRun(javaCode, inputString, outputString);
		jsonObject.put("flag", result);
		jsonObject.put("result", outputString.toString());
		String jsonString = jsonObject.toString();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(jsonString);

	}

	@RequestMapping(value = "/ufindQuestion{pageId}")
	public void findQuestion(@PathVariable int pageId,
			HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chaarset=utf-8");
		pageBean.setPage(pageId);
		PageList<Question> pageList = userService.findQuestion(pageBean);
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
			sb.append("<a href='utoTest?questionId=" + question.getQuestionId()
					+ "'>" + question.getQuestionTitle() + "</a>");
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

	@RequestMapping("/utoTest")
	public String toTest(String questionId, HttpSession session) {
		Question question = userService.findQuestionById(Integer
				.parseInt(questionId));
		session.setAttribute("question", question);
		return "utest";
	}

	@RequestMapping("/udoTest")
	public String doTest(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Question question = (Question) request.getSession().getAttribute(
				"question");
		String javaCode = request.getParameter("javaCode");
		User user=(User) request.getSession().getAttribute("user");
		testResultList = userService.doTest(javaCode, question,user);
		model.addAttribute(testResultList);
		return "uscoreDetail";
	}

	@RequestMapping("/utoRegister")
	public String toRegister() {
		return "uregister";
	}

	@RequestMapping("/udoRegister")
	private String checkRegisterForm(@Valid URegisterForm uForm,
			BindingResult result, ModelMap map, HttpServletRequest request) {
		if (result.hasErrors()) {
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError error : errorList) {
				map.put("ERR_" + error.getField(), error.getDefaultMessage());
			}
			return "uregister";
		}
		if (!uForm.getPassword().equals(request.getParameter("qpassword"))) {
			map.put("ERR_qpassword", "两次密码输入不一致");
			return "uregister";
		}
		return doRegister(uForm);

	}

	public String doRegister(URegisterForm uForm) {
		User user = new User(uForm);
		userService.register(user);
		System.out.println(user.getRealName());
		return "ulogin";
	}

	@RequestMapping("/udoLogin")
	public String checkLoginForm(@Valid LoginForm lForm, BindingResult result,
			ModelMap map, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			List<FieldError> errorList = result.getFieldErrors();
			for (FieldError error : errorList) {
				map.put("ERR_" + error.getField(), error.getDefaultMessage());
			}
			return "ulogin";
		}
		return doLogin(lForm, model, request);
	}

	public String doLogin(LoginForm lForm, Model model,
			HttpServletRequest request) {
		User user = userService
				.doLogin(lForm.getAccount(), lForm.getPassword());
		if (user == null) {
			model.addAttribute("ERR_account", "用户名不存在");
			return "ulogin";
		}
		if (!user.getPassword().equals(lForm.getPassword())) {
			model.addAttribute("ERR_password", "密码错误");
			return "ulogin";
		}
		request.getSession().setAttribute("user", user);
		return "uindex";
	}
}
