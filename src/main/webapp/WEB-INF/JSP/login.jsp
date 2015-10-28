<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<!doctype html>
<html>
<head>
<v:head title="Routeplanner"></v:head>
<link href='<c:url value="/styles/signin.css"/>' rel="stylesheet">
</head>
<body>
	<v:menu title="home"/>

	<div class="container">
	<c:url var="loginURL" value="/login"/>
		<form class="form-signin" method="post" action="${loginURL}">
        <h2 class="form-signin-heading">Please sign in</h2>
        <c:if test='${param.error != null}'>
	        <div class="form-group has-error">
				<div class='help-block'>Verkeerde gebruikersnaam of paswoord.</div>
			</div>
		</c:if>
        <input type="text" placeholder="Username" class="form-control" name="username" >
        <input type="password" placeholder="Password" class="form-control" name="password">
        <security:csrfInput />        
        <button type="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>      
      </form>

	</div>
	<!-- /.container -->

	<v:bootstrapFooter/>
</body>
</html>