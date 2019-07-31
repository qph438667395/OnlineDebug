<%@page import="com.spring.entity.Question"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="jPaginate - jQuery Pagination Plugin" />
<meta name="keywords" content="jquery, plugin, pagination, fancy" />
<title><spring:message code="title"></spring:message></title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/queDetails.css">
</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div id="content">
	<div id="question">
		<table  class="table table-striped table-bordered table-hover" id="result">
			<tr>
				<th><spring:message code="qtitle"></spring:message></th>
				<td>${question.questionTitle}</td>
			</tr>
			<tr>
				<th><spring:message code="qcontent"></spring:message></th>
				<td>${question.questionContent}</td>
			</tr>
			<tr>
				<th><spring:message code="difficulty"></spring:message></th>
				<td>${question.difficulty}</td>
			</tr>
			<tr>
				<th><spring:message code="questionType"></spring:message></th>
				<td><%if(((Question)request.getAttribute("question")).getQuestionType()==1){ %>
					<spring:message code="haveInput"></spring:message>
				<%	}else{ %>
				<spring:message
											code="notHaveInput"></spring:message>
				<%} %></td>
			</tr>
		</table>
	</div>
	<div id="testcase">
			<table  class="table table-striped table-bordered table-hover" id="result">
			<thead>
				<tr>
					<th><spring:message code="input"></spring:message> </th>
					<th><spring:message code="expectedOutput"></spring:message></th>
					<th><spring:message code="timeLimit"></spring:message></th>
					<th><spring:message code="functionPointScore"></spring:message></th>
				</tr>
			</thead>
			<c:forEach items="${testCases}" var="p">
			<tr>
				<td>${p.input}</td>
				<td>${p.expectedOutput}</td>
				<td>${p.timeLimit}</td>
				<td>${p.functionPointScore}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script src="js/jquery.paginate.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/uselectQuestion.js"></script>
</body>
</html>