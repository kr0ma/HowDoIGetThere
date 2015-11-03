<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<v:head title="User registration"></v:head>
<body>
	<v:menu title="routeplanner"/>
	<div class="container">

		<div class="starter-template">
			<h1>Routeplanner</h1>
			<p class="lead">
				i'll need a search form here :)
			</p>
			<c:if test="${not empty routes}">
			test
			<c:forEach items="${routes}" var="route">
				<p>${route.name}</p>
			</c:forEach>
		</c:if>
		</div>
		

	</div>
	<v:bootstrapFooter/>
</body>
</html>