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
<link rel="stylesheet" href="css/selectQuestion.css">
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div id="content">
			<table class="table table-striped table-hover" id="result">
				<thead>
					<tr>
						<th><spring:message code="num"></spring:message></th>
						<th><spring:message code="difficulty"></spring:message></th>
						<th><spring:message code="qtitle"></spring:message></th>
						<th><spring:message code="operate"></spring:message></th>
					</tr>
				</thead>
				<c:forEach items="${pageList.resultList}" var="p">
					<tr>
						<td>${p.questionId}</td>
						<td>${p.difficulty}</td>
						<td>${p.questionTitle}</td>
						<td><a id="deleteConfirm" href="adeleteQuest?questionId=${p.questionId}" ><spring:message
									code="delete"></spring:message></a> | <a
							href="aupdateQuest?questionId=${p.questionId}"><spring:message
									code="update"></spring:message></a> | <a
							href="atoQueDetails?questionId=${p.questionId}"><spring:message
									code="details"></spring:message></a></td>
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
		<div id="add">
			<a href="atoAddQuestion"><img id="img" alt="" src="images/add.jpg"></a>
		</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script src="js/jquery.paginate.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/aselectQuestion.js"></script>
</body>
</html>