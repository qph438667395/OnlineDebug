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
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/register.css">
<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker();
		document.documentElement.style.overflow='hidden';
	})
</script>
</head>
<body>
	<div id="head">
		<jsp:include page="uhead.jsp"></jsp:include>
	</div>
	<div id="content">
		<div id="form">
			<h3 id="title"><spring:message code="register"></spring:message></h3>
			<hr>
			<form action="udoRegister" role="form" class="form-horizontal col-sm-7" method="post" >
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
				<label class="col-sm-3 control-label"><spring:message
						code="qpassword"></spring:message></label>
				<div class="col-sm-6">
					<input type="password"
						placeholder=<spring:message code="passwordMsg"></spring:message>
						class="form-control" name="qpassword">
				</div>
				<label class="col-sm-3 control-label" id="errorMsg">${ERR_qpassword}</label>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message
						code="name"></spring:message></label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="realName"
						placeholder=<spring:message code="nameMsg"></spring:message>
					>
				</div>
				<label class="col-sm-3 control-label" id="errorMsg">${ERR_realName}</label>	
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message
						code="sex"></spring:message></label>
				<div class="col-sm-6">
					<div class="radio">
						<label class="col-sm-4"> <input type="radio" value="1"
							name="sex"> <spring:message code="man"></spring:message>
						</label> <label> <input type="radio" value="2" name="sex">
							<spring:message code="woman"></spring:message>
						</label>
					</div>
				</div>
				<label class="col-sm-3 control-label" id="errorMsg">${ERR_sex}</label>	
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><spring:message
						code="birthday"></spring:message></label>
				<div class="col-sm-6">
					<input type="text" id="datepicker"
						placeholder=<spring:message code="birthdayMsg"></spring:message>
						class="form-control" name="birthday">
				</div>
				<label class="col-sm-3 control-label" id="errorMsg">${ERR_birthday}</label>	
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-7">
					<button type="submit" class="btn btn-primary col-sm-11">
						<spring:message code="register"></spring:message>
					</button>
				</div>
			</div>
		</form>
		<div class="col-sm-5">
			<img alt="" src="images/register.jpg" width="100%" height="100%">
		</div>
		</div>
	
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>

</body>
</html>