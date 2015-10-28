<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@attribute name='title' required='true' type='java.lang.String'%>
<%@taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='<c:url value="/" />'>HowDoIGetThere</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li <c:if test="${title == 'home'}">class="active"</c:if>><a href='<c:url value="/" />'>Home</a></li>
				<security:authorize access='isAuthenticated()'>
					<li <c:if test="${title == 'routeplanner'}">class="active"</c:if>><a href='<c:url value='/route' />'>Routeplanner</a></li>
				</security:authorize>
				<li <c:if test="${title == 'about'}">class="active"</c:if>><a href='<c:url value="/about"/>'>About</a></li>
			</ul>
			
			<security:authorize access='isAuthenticated()'>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><security:authentication property="name" />
							<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Preferences</a></li>
							<li role="separator" class="divider"></li>
							<li><form method='post' action='<c:url value="/logout"/>' id='logoutform'>
									<input type='submit' value='Log off' id='logoutbutton' class="btn btn-link">
									<security:csrfInput />
								</form>
							</li>
						</ul>
				</ul>
			</security:authorize>
			
			<security:authorize access='isAnonymous()'>
				<c:url var="loginURL" value="/login"/> 
				<form class="navbar-form navbar-right" method="post" action="${loginURL}"> 
					<div class="form-group">
						<input type="text" placeholder="Username" class="form-control"
							name="username">
					</div>
					<div class="form-group">
						<input type="password" placeholder="Password" class="form-control"
							name="password">
					</div>
					<security:csrfInput />
					<button type="submit" class="btn btn-success">Sign in</button>
					<a href='<c:url value="/user/register"/>' class="btn btn-danger">Register</a>				
				</form>				
			</security:authorize>
			
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>


