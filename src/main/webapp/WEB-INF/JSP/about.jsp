<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<v:head title="Routeplanner"></v:head>
</head>
<body>
	<v:menu title="about" />
	<div class="container">
		<!--  
		<div class="jumbotron">
			<div class="container">
				<h1>About</h1>
			</div>
		</div>
	-->
		<div class="jumbotron">
			<div class="container">
				<div class="alert alert-success center" role="alert">
					<h3>About</h3>
				</div>
				<p class="lead">Small Spring project using Spring security and
					Restservices</p>
				<p>Icons</p>
				<p>
				<ul>
					<li>walker by Alexander Wiefel from the Noun Project</li>
					<li>Train by Stephen McKamey from the Noun Project</li>
					<li>Airplane by Dilon Choudhury from the Noun Project</li>
				</ul>
			</div>
		</div>		
	</div>
	<div class="row">
		<c:url value="/images/uml/howdoigetthere.png" var="imgUrl" />
		<img alt="UML-diagram" src="${imgUrl}" id="uml" class="col-md-8 col-md-offset-2">
	</div>
	
	<!-- /.container -->
	<v:bootstrapFooter />
</body>
</html>