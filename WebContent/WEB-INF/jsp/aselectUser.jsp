<%@page import="com.spring.model.PageList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="jPaginate - jQuery Pagination Plugin" />
<meta name="keywords" content="jquery, plugin, pagination, fancy" />
<title><spring:message code="title"></spring:message></title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/selectUser.css">
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div class="select">
		<form role="form" id="questionForm" class="form-horizontal col-sm-12"
			method="post">
			<div class="form-group">
				<label class="col-sm-1 control-label"><spring:message
						code="account"></spring:message></label>
				<div class="col-sm-6">
					<input type="text"
						placeholder=<spring:message code="accountMsg"></spring:message>
						class="form-control" name="account">
				</div>
				<div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary col-sm-12"
							formaction="">
							<spring:message code="select"></spring:message>
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div id="content">
		<table class="table table-striped table-hover" id="result">
			<thead>
				<tr>
					<th><spring:message code="account"></spring:message></th>
					<th><spring:message code="name"></spring:message></th>
					<th><spring:message code="sex"></spring:message></th>
					<th><spring:message code="birthday"></spring:message></th>
				</tr>
			</thead>
			<c:forEach items="${pageList.resultList}" var="p">
				<tr>
					<td>${p.account}</td>
					<td>${p.realName}</td>
						<td><c:if test="${p.sex==1}">
							<spring:message code="man"></spring:message>
						</c:if> <c:if test="${p.sex==2}">
							<spring:message code="woman"></spring:message>
						</c:if></td>
					<td>${p.birthday}</td>
				</tr>
			</c:forEach>
		</table>
			<div style="display: none">
				<input id="pageCount" value="${pageList.pageBean.getTotalPage()}">
			</div>
			<div class="col-sm-4 pull-right">
				<div id="Pagination"></div>
			</div>
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script src="js/jquery.paginate.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/aselectUser.js"></script>
</body>
</html>