<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.spring.entity.User"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
#head {
	width: 100%;
	height: 50px;
}

#headpict {
	border-radius: 50%;
}
</style>
<script type="text/javascript">
	function changeLocale(lang) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + 365);
		document.cookie = "lang=" + lang + ";expires=" + exdate.toGMTString();
		location.reload();
	}
</script>
<div id="head">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a href="uindex" class="navbar-brand"><spring:message
						code="home"></spring:message></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="uprogramming"><spring:message
								code="nav_online_programming"></spring:message></a></li>
					<li><a href="uselectQuestion"><spring:message
								code="nav_online_score"></spring:message></a></li>
					<li><a href="#"><spring:message code="about"></spring:message></a></li>
				</ul>
				<p class="navbar-text pull-right">
					<%
						User user = (User) request.getSession().getAttribute("user");
						if (user == null) {
					%>
					<a href="utoLogin"><spring:message code="login"></spring:message></a>
					<%
						} else {
					%>
					<a href="#"><img id="headpict" alt="" src="images/head.jpg"
						width="35px" height="22px"></a>
					<%
						}
					%>
				</p>
				<p class="navbar-text pull-right">
					<a href="utoRegister"><spring:message code="register"></spring:message></a>
				</p>
				<p class="navbar-text pull-right">
					<a href="javascript: changeLocale('zh')"> <spring:message
							code="Chinese"></spring:message>
					</a>
				</p>
				<p class="navbar-text pull-right">
					<a href="javascript: changeLocale('en')"> <spring:message
							code="English"></spring:message>
					</a>
				</p>

			</div>
		</div>
	</nav>
</div>
