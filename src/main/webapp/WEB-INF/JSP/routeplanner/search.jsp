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

		<div class="jumbotron center">
			<div class="container">
				<h2>Find your way of travelling!</h2>
				<c:url value='/route/search' var='url' />
				<form:form method="get" commandName="searchForm" id="searchForm"
					action="${url}">
					<form:label path="origin">From</form:label>
					<form:errors path="origin" />
					<form:input path="origin" autofocus='autofocus' />
					<form:label path="destination">To</form:label>
					<form:errors path="destination" />
					<form:input path="destination" autofocus='autofocus' />
				<form:label path='travelPreferences'>Way of travel:<form:errors path='travelPreferences'/></form:label>
				<form:checkboxes items='${travelPreferences}' path='travelPreferences' element='div'/>
					<input type='submit' value='Search' />
				</form:form>
			</div>
		</div>

		<c:if test="${not empty routeplanning}">		
			<div class="alert alert-success center" role="alert">				
				<h2>From ${routeplanning.getOriginPlace().name} to ${routeplanning.getDestinationPlace().name} </h2>
			</div>			
			<c:forEach items="${routeplanning.routes}" var="route">
				<div class="jumbotron">
					<div class="container">
						<div class="alert alert-success center" role="alert">
							<h3><strong>${route.name}</strong>(${route.distance}km - ${route.duration}minutes)</h3>
						</div>
						<p>${route.distance}km ${route.duration}minutes</p>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<v:bootstrapFooter />
</body>
</html>