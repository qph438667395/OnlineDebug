<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title"></spring:message></title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/addquestion.css">
<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="js/addquetion.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
	<div id="head">
		<jsp:include page="ahead.jsp"></jsp:include>
	</div>
	<div id="content">
		<div id="question">
				<form role="form" id="questionForm"
					class="form-horizontal col-sm-12" method="post">
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="qtitle"></spring:message></label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="questionTitle">
						</div>
						<label class="col-sm-4 control-label ERR_questionTitle"
							id="errorMsg"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="qcontent"></spring:message></label>
						<div class="col-sm-6">
							<textarea rows="6" cols="" class="form-control"
								name="questionContent"></textarea>
						</div>
						<label class="col-sm-4 control-label ERR_questionContent"
							id="errorMsg"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="difficulty"></spring:message></label>
						<div class="col-sm-6">
							<div class="radio">
								<label class="col-sm-3"> <input type="radio" value="1"
									name="difficulty">1
								</label> <label class="col-sm-3"> <input type="radio" value="2"
									name="difficulty"> 2
								</label> <label class="col-sm-3"> <input type="radio" value="3"
									name="difficulty">3
								</label> <label class="col-sm-3"> <input type="radio" value="4"
									name="difficulty"> 4
								</label>
							</div>
						</div>
						<label class="col-sm-4 control-label ERR_difficulty" id="errorMsg"></label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label"><spring:message
								code="qtype"></spring:message></label>
						<div class="col-sm-6">
							<div class="radio">
								<label class="col-sm-4"> <input type="radio" value="1"
									name="questionType"> <spring:message code="haveInput"></spring:message>
								</label> <label> <input type="radio" value="2"
									name="questionType"> <spring:message
										code="notHaveInput"></spring:message>
								</label>
							</div>
						</div>
						<label class="col-sm-4 control-label ERR_questionType"
							id="errorMsg"></label>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-1 col-sm-7">
							<button id="questionSubmit" class="btn btn-primary col-sm-11">
								<spring:message code="submit"></spring:message>
							</button>
						</div>
					</div>
				</form>
		</div>
		<div id="testcase">
				<form role="form" class="col-sm-12" method="post" id="testCaseForm">
					<table class="table">
						<tr>
							<th><spring:message code="input"></spring:message></th>
							<th><spring:message code="expectedOutput"></spring:message></th>
							<th><spring:message code="timeLimit"></spring:message></th>
							<th><spring:message code="functionPointScore"></spring:message>
							</th>
						</tr>
						<tr>
							<td><input type="text" name="input" class="form-control"
								class="input"></td>
							<td><input type="text" class="form-control"
								name="expectedOutput"></td>
							<td><input type="text" class="form-control" name="timeLimit"></td>
							<td><input type="text" class="form-control"
								name="functionPointScore"></td>
							<td style="display: none"><input name="questionId"
								class="questionId"></td>
							<td style="display: none"><input name="questionType"
								class="questionType"></td>
						</tr>
					</table>
					<div id="addIcon">
						<div id="icon">
							<p id="addFont">+</p>
						</div>
					</div>
					<div class="form-group submit">
						<div class="col-sm-offset-2 col-sm-8">
							<button class="btn btn-primary col-sm-11" id="testCaseSubmit">
								<spring:message code="submit"></spring:message>
							</button>
						</div>
					</div>
				</form>
		</div>
	</div>
	<div class="modal" id="addmodal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-lable="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="model-title" id="myModalLabel"></h4>
				</div>
				<div class="modal-body">
					<p id="modalContent"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<spring:message code="ok"></spring:message>
					</button>
					<button type="button" class="btn btn-primary">
						<spring:message code="cancel"></spring:message>
					</button>
				</div>
			</div>
		</div>
	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
</body>
</html>