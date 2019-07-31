<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="jPaginate - jQuery Pagination Plugin" />
<meta name="keywords" content="jquery, plugin, pagination, fancy" />
<title><spring:message code="title"></spring:message></title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
<link rel="stylesheet" href="css/selectScore.css">
</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div id="select">
	<div class="search radius6">
			<form name="searchform" method="post" id="searchForm">
			<select name="classId" id="classId">
					<option value="0" selected="selected" >全部成绩</option>
					<option value="1">根据题目查询</option>
					<option value="2">根据账号查询</option>
			</select> 
				<input class="inp_srh" name="condition"  placeholder="请输入您要搜索的相关信息" id="key">
				<input class="btn_srh" name="submit" value="搜索" id="btn_srh" type="submit">
			</form>
	</div>
	</div>
	<div id="content">
			<table class="table table-striped table-hover" id="result">
				<thead>
					<tr>
						<th><spring:message code="account"></spring:message></th>
						<th><spring:message code="questionTitle"></spring:message></th>
						<th><spring:message code="usedTime"></spring:message></th>
						<th><spring:message code="nav_score"></spring:message></th>
						<th><spring:message code="testCaseResult"></spring:message></th>
						<th><spring:message code="scoreTime"></spring:message></th>
						<th><spring:message code="operate"></spring:message></th>
					</tr>
				</thead>
			<c:forEach items="${pageList.resultList}" var="p" varStatus="loop">
					<tr>
						<td>${p.account}</td>
						<td>${questTitleList[loop.count-1]}</td>
						<td>${p.usedMaxTime}</td>
						<td>${p.score}</td>
						<td>${p.result}</td>
						<td>${p.testTime}</td>
						<td><a href="ascoreDetails?scoreId=${p.scoreId}"><spring:message code="details"></spring:message> </a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="col-sm-4 pull-right">
				<div id="Pagination"></div>
			</div>
			<div style="display:none">
				<input id="pageCount" value="${pageList.pageBean.getTotalPage()}">
				<input id="selectId"  value="0">
				<input id="condition" value="0">
			</div>
			
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
	<script src="js/jquery.paginate.js" type="text/javascript"></script>	
	<script type="text/javascript" src="js/aselectScore.js"></script>
</body>
</html>