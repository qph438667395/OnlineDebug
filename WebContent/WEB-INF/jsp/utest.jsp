<%@page import="com.spring.constant.SysConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="title"></spring:message></title>

<link rel="stylesheet" href="css/bootstrap.min.css">

<link rel="stylesheet" href="css/theme/3024-day.css">
<link rel="stylesheet" href="css/theme/3024-night.css">
<link rel="stylesheet" href="css/theme/abcdef.css">
<link rel="stylesheet" href="css/theme/ambiance.css">
<link rel="stylesheet" href="css/theme/base16-dark.css">
<link rel="stylesheet" href="css/theme/bespin.css">
<link rel="stylesheet" href="css/theme/base16-light.css">
<link rel="stylesheet" href="css/theme/blackboard.css">
<link rel="stylesheet" href="css/theme/cobalt.css">
<link rel="stylesheet" href="css/theme/colorforth.css">
<link rel="stylesheet" href="css/theme/dracula.css">
<link rel="stylesheet" href="css/theme/eclipse.css">
<link rel="stylesheet" href="css/theme/elegant.css">
<link rel="stylesheet" href="css/theme/erlang-dark.css">
<link rel="stylesheet" href="css/theme/hopscotch.css">
<link rel="stylesheet" href="css/theme/icecoder.css">
<link rel="stylesheet" href="css/theme/isotope.css">
<link rel="stylesheet" href="css/theme/lesser-dark.css">
<link rel="stylesheet" href="css/theme/liquibyte.css">
<link rel="stylesheet" href="css/theme/material.css">
<link rel="stylesheet" href="css/theme/mbo.css">
<link rel="stylesheet" href="css/theme/mdn-like.css">
<link rel="stylesheet" href="css/theme/midnight.css">
<link rel="stylesheet" href="css/theme/monokai.css">
<link rel="stylesheet" href="css/theme/neat.css">
<link rel="stylesheet" href="css/theme/neo.css">
<link rel="stylesheet" href="css/theme/night.css">
<link rel="stylesheet" href="css/theme/paraiso-dark.css">
<link rel="stylesheet" href="css/theme/paraiso-light.css">
<link rel="stylesheet" href="css/theme/pastel-on-dark.css">
<link rel="stylesheet" href="css/theme/railscasts.css">
<link rel="stylesheet" href="css/theme/rubyblue.css">
<link rel="stylesheet" href="css/theme/seti.css">
<link rel="stylesheet" href="css/theme/solarized.css">
<link rel="stylesheet" href="css/theme/the-matrix.css">
<link rel="stylesheet" href="css/theme/tomorrow-night-bright.css">
<link rel="stylesheet" href="css/theme/tomorrow-night-eighties.css">
<link rel="stylesheet" href="css/theme/ttcn.css">
<link rel="stylesheet" href="css/theme/twilight.css">
<link rel="stylesheet" href="css/theme/vibrant-ink.css">
<link rel="stylesheet" href="css/theme/xq-dark.css">
<link rel="stylesheet" href="css/theme/xq-light.css">
<link rel="stylesheet" href="css/theme/yeti.css">
<link rel="stylesheet" href="css/theme/zenburn.css">

<link rel=stylesheet href="css/docs.css">
<link rel="stylesheet" href="css/codemirror.css">
<link rel="stylesheet" href="css/show-hint.css">

<link rel="stylesheet" href="css/programming.css">

<script src="js/codemirror.js"></script>
	<script src="js/matchbrackets.js"></script>
	<script src="js/show-hint.js"></script>
	<script src="js/clike.js"></script>
	<script src="js/javascript-hint.js"></script>
	<script src="js/javascript.js"></script>
	<script src="js/active-line.js"></script>

	<script type="text/javascript" src="js/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="js/programming.js"></script>
	<script type="text/javascript" src="js/test.js"></script>
	<script type="text/javascript">
	var input = document.getElementById("select");
	function selectTheme() {
		var theme = input.options[input.selectedIndex].textContent;
		editor.setOption("theme", theme);
		location.hash = "#" + theme;
	}
	var choice = (location.hash && location.hash.slice(1))
			|| (document.location.search && decodeURIComponent(document.location.search
					.slice(1)));
	if (choice) {
		input.value = choice;
		editor.setOption("theme", choice);
	}
	CodeMirror.on(window, "hashchange", function() {
		var theme = location.hash.slice(1);
		if (theme) {
			input.value = theme;
			selectTheme();
		}
	});
	
	</script>
</head>
<body>
	<div id="head">
		<jsp:include page="uhead.jsp"></jsp:include>
	</div>
	<div id="content">
	<pre id="question-content" class="col-sm-12">${question.questionContent}</pre>
		
		<p>
			<label><spring:message code="Select_a_theme"></spring:message>
				:</label> <select onchange="selectTheme()" id=select class="select">
				<option selected>default</option>
				<option>3024-day</option>
				<option>3024-night</option>
				<option>abcdef</option>
				<option>ambiance</option>
				<option>base16-dark</option>
				<option>base16-light</option>
				<option>bespin</option>
				<option>blackboard</option>
				<option>cobalt</option>
				<option>colorforth</option>
				<option>dracula</option>
				<option>eclipse</option>
				<option>elegant</option>
				<option>erlang-dark</option>
				<option>hopscotch</option>
				<option>icecoder</option>
				<option>isotope</option>
				<option>lesser-dark</option>
				<option>liquibyte</option>
				<option>material</option>
				<option>mbo</option>
				<option>mdn-like</option>
				<option>midnight</option>
				<option>monokai</option>
				<option>neat</option>
				<option>neo</option>
				<option>night</option>
				<option>paraiso-dark</option>
				<option>paraiso-light</option>
				<option>pastel-on-dark</option>
				<option>railscasts</option>
				<option>rubyblue</option>
				<option>seti</option>
				<option>solarized dark</option>
				<option>solarized light</option>
				<option>the-matrix</option>
				<option>tomorrow-night-bright</option>
				<option>tomorrow-night-eighties</option>
				<option>ttcn</option>
				<option>twilight</option>
				<option>vibrant-ink</option>
				<option>xq-dark</option>
				<option>xq-light</option>
				<option>yeti</option>
				<option>zenburn</option>
			</select>
		</p>
		<form id="form1">
			<div id="coding">
				<textarea id="java-code" name="javaCode">
					import java.util.*;		
			public class  <%=SysConfig.javaName%>{
						public static void main(String[] args){
						Scanner scanner=new Scanner(System.in);
                        int a=scanner.nextInt();
                        int b=scanner.nextInt();
                        System.out.println(a+b);
                        }
				}
			</textarea>
			</div>

		</form>

		<div>
			<label><spring:message code="enter_input"></spring:message> :</label><br>
			<textarea id="input"></textarea>
		</div>


		<div class="col-sm-3 col-sm-offset-9" id="btn">
			<div class="col-sm-3">
				<a class="btn btn-default" id="btn-run"><spring:message
						code="run"></spring:message></a>
			</div>
			<div class="col-sm-3 col-sm-offset-4">
				<input class="btn btn-default" id="btn-submit" type="submit"
					formaction="udoTest" form="form1" formmethod="post"
					value=<spring:message
						code="submit"></spring:message>>
			</div>

		</div>

		<div id="result">
			<label><spring:message code="enter_output"></spring:message>
				:</label><br>
			<pre id="output">
			</pre>
		</div>

	</div>
	<div id="foot">
		<jsp:include page="ufoot.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript">
	var editor = CodeMirror.fromTextArea(document
			.getElementById("java-code"), {
		lineNumbers : true,
		matchBrackets : true,
		styleActiveLine : true,
		mode : "text/x-java"
	});
	</script>
</body>
</html>