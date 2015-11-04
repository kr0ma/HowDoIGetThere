<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<v:head title="User registration"></v:head>
<body>
	<v:menu title="routeplanner" />
	<div class="container">

		<div class="jumbotron">
			<div class="container">
				<h1>Routeplanner</h1>
			</div>
		</div>

		<div class="jumbotron">
			<div class="container">
				<p class="lead">i'll need a search form here :)</p>
				<c:url value='/route/search' var='url' />		
				<form:form method="get" commandName="searchForm" id="searchForm" action="${url}">
					<form:label path="origin">From</form:label>
					<form:input path="origin" autofocus='autofocus'/>					
					<form:label path="destination">To</form:label>
					<form:input path="destination" autofocus='autofocus'/>
					<input type='submit' value='Search'/>
				</form:form>		
			</div>
		</div>
		
		<c:if test="${not empty routeplanning}">
			<div class="jumbotron">
				<div class="container">					
					<c:forEach items="${routeplanning.routes}" var="route">
							<p>${route.name} ${route.distance}km ${route.duration}minutes</p>
					</c:forEach>					
				</div>
			</div>
		</c:if>
	</div>
	<v:bootstrapFooter />
</body>
</html>