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
<link rel="stylesheet" href="css/index.css">

</head>
<body>
	<div id="head">
		<jsp:include page="uhead.jsp"></jsp:include>
	</div>
	<div id="content">
		<ul class="img">
			<li><img alt="" src="images/index5.jpg" width="100%"
				height="100%"></li>
			<li><img alt="" src="images/index4.jpg" width="100%"
				height="100%"></li>
			<li><img alt="" src="images/index3.jpg" width="100%"
				height="100%"></li>
			<li><img alt="" src="images/index1.jpg" width="100%"
				height="100%"></li>
			<li><img alt="" src="images/index2.jpg" width="100%"
				height="100%"></li>
		</ul>
		<ul class="num">
		</ul>
		<div class="left btn">&lt;</div>
		<div class="right btn">&gt;</div>
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>