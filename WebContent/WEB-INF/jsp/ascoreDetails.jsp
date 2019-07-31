<%@page import="java.util.List"%>
<%@page import="com.spring.entity.TestCase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title"></spring:message></title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel=stylesheet href="css/docs.css">
<link rel="stylesheet" href="css/codemirror.css">
<link rel="stylesheet" href="css/show-hint.css">
<link rel="stylesheet" href="css/ascoreDetail.css">

</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div id="content">
		<table class="table table-hover">
			<tr>
				<td class="title"><spring:message code="scoreId"></spring:message></td>
				<td>${score.scoreId}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="userId"></spring:message></td>
				<td>${score.account}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="scoreTime"></spring:message></td>
				<td>${score.testTime}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="testResult"></spring:message></td>
				<td>${score.result}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="score"></spring:message></td>
				<td>${score.score}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="usedTime"></spring:message></td>
				<td>${score.usedMaxTime}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="questionContent"></spring:message></td>
				<td>${question.questionTitle}</td>
			</tr>
			<tr>
				<td class="title"><spring:message code="detailsInfo"></spring:message></td>
				<td><table class="table table-hover">
					<tr>
						<th class="childTitle"><spring:message code="testCaseNum"></spring:message></th>
						<th class="childTitle"><spring:message code="input"></spring:message></th>
						<th class="childTitle"><spring:message code="expectedOutput"></spring:message></th>
						<th class="childTitle"><spring:message code="actualOutput"></spring:message></th>
						<th class="childTitle"><spring:message code="testCaseResult"></spring:message></th>
						<th class="childTitle"><spring:message code="score"></spring:message></th>
						<th class="childTitle"><spring:message code="usedTime"></spring:message></th>
					</tr>
					<c:set var="num" value="0"></c:set>
					<c:forEach  items="${testResults}" var="result">
					<c:set var="num" value="${num+1}"></c:set>
					<tr>
						<td>${num}</td>
						<td>${result.input}</td>
						<td>${result.expectedOutput}</td>
						<td>${result.actualOutput}</td>
						<td>${result.functionResult}</td>
						<td>${result.functionPointScore}</td>
						<td>${result.usedTime}</td>
					</tr>
					</c:forEach>
				</table></td>
			</tr>
		</table>
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>

	
	<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
	
</body>
</html>