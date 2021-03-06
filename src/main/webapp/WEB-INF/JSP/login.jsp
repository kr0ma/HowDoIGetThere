<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security'
	uri='http://www.springframework.org/security/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html>
<head>
<v:head title="Routeplanner"></v:head>
<link href='<c:url value="/styles/signin.css"/>' rel="stylesheet">
</head>
<body>
	<v:menu title="home" />
	
	<div class="container">
	<!--  
		<div class="jumbotron">
			<div class="container">
				<h1>Sign in</h1>
			</div>
		</div>
	-->

		<div class="jumbotron">
			<div class="container">
			<div class="alert alert-success center" role="alert">
					<h3>Login</h3>
				</div>
				<c:url var="loginURL" value="/login" />
				<form class="form-signin" method="post" action="${loginURL}">
					<c:if test='${param.error != null}'>
						<div class="form-group has-error">
							<div class='help-block'>
								<spring:message code="errorUsernamePassword" />
							</div>
						</div>
					</c:if>
					<input type="text" placeholder="Username" class="form-control"
						name="username"> <input type="password"
						placeholder="Password" class="form-control" name="password">
					<security:csrfInput />
					<button type="submit" class="btn btn-lg btn-primary btn-block">Sign
						in</button>
				</form>
			</div>
		</div>

	</div>
	<!-- /.container -->

	<v:bootstrapFooter />
</body>
</html>