<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.spring.entity.Admin" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<style>
#head{
	width: 100%;
	height: 50px;
}

#headpict{
	border-radius:50%;
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
				<a href="aindex" class="navbar-brand"><spring:message code="home"></spring:message></a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="aselectQuestion"><spring:message
								code="nav_question"></spring:message></a></li>
					<li><a href="aselectUser"><spring:message code="nav_user"></spring:message></a></li>
					<li><a href="aselectScore"><spring:message code="nav_score"></spring:message></a></li>
					<li><a href="#"><spring:message code="about"></spring:message></a></li>
				</ul>
				<p class="navbar-text pull-right">
					<%
					Admin admin =(Admin)request.getSession().getAttribute("admin");
					if(admin==null){
					
					%>
					<a href="atoLogin"><spring:message code="login"></spring:message></a>
					<%}else{ %>
					<a href="#"><img id="headpict" alt="" src="images/head.jpg" width="35px" height="22px"></a>
					<%} %>
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
