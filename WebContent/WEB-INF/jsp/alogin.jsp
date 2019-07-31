<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title"></spring:message></title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/login.css">
<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.documentElement.style.overflow='hidden';
	});
</script>
</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div id="body">
		<div id="content">
			<h3 id="title">
				<spring:message code="login"></spring:message>
			</h3>
			<hr>
		
			<div id="form">
				<form role="form" method="post"
					class="form-horizontal">
					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="account"></spring:message></label>
						<div class="col-sm-6">
							<input type="text"
								placeholder=<spring:message code="accountMsg"></spring:message>
								class="form-control" name="account">
						</div>
						<label class="col-sm-3 control-label" id="errorMsg">${ERR_account}</label>
					</div>

					<div class="form-group">
						<label class="col-sm-3 control-label"><spring:message
								code="password"></spring:message></label>
						<div class="col-sm-6">
							<input type="password"
								placeholder=<spring:message code="passwordMsg"></spring:message>
								class="form-control" name="password">
						</div>
						<label class="col-sm-3 control-label" id="errorMsg">${ERR_password}</label>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-7">
							<button type="submit" class="btn btn-primary col-sm-12" formaction="adoLogin" >
								<spring:message code="login"></spring:message>
							</button>
						</div>
					</div>
				</form>
			</div>
			<div id="img">
				<img alt="" src="images/alogin.jpg" width="100%" height="100%">
			</div>
			
		</div>



	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>

</body>
</html>