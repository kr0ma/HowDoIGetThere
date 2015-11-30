<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!doctype html>
<html>
<v:head title="User registration"></v:head>
<body>
	<v:menu title="routeplanner" />
	<div class="container">
		<div class="jumbotron center">
			<div class="container">
				<div class="alert alert-success center" role="alert">
					<h3>Find your way of travelling!</h3>
				</div>

				<c:url value='/route/search' var='url' />
				<form:form method="get" commandName="searchForm" id="searchForm"
					action="${url}" cssClass="form-inline">
					<div class="form-group">
						<div class="form-horizontal col-md-5">
							<div class="form-group">
								<form:input path="origin" autofocus='autofocus'
									cssClass="form-control" placeholder="From" />
								<div class="has-error">
									<form:errors path="origin"
										element="label class='control-label'" />
								</div>
							</div>
						</div>
						<div class="form-horizontal col-md-5 col-md-offset-2">
							<div class="form-group">
								<form:input path="destination" autofocus='autofocus'
									cssClass="form-control" placeholder="To" />
								<div class="has-error">
									<form:errors path="destination"
										element="label class='control-label'" />
								</div>
							</div>
						</div>
					</div>
					<div class="form-horizontal">
						<div class="form-group">
							<form:checkboxes items='${travelPreferences}'
								path='travelPreferences' element="span class='checkbox-inline'" />							
							<div class="has-error">
								<form:errors path="travelPreferences"
									element="label class='control-label'" />
							</div>
						</div>
					</div>

					<input type='submit' value='Search' class="btn btn-info btn-lg" />
				</form:form>
			</div>
		</div>
		<c:if
			test="${(empty routeplanning or empty routeplanning.routes) and not empty param.origin and not empty param.destination and not empty param.travelPreferences}">
			<div class="alert alert-info center" role="alert">
				<h2>
					<spring:message code="NoRoutesFound" />
				</h2>
			</div>
		</c:if>

		<c:if
			test="${not empty routeplanning and not empty routeplanning.routes}">
			<div class="alert alert-success center" role="alert">
				<h2>From ${routeplanning.getOriginPlace().name} to
					${routeplanning.getDestinationPlace().name}</h2>
			</div>
			<c:forEach items="${routeplanning.routes}" var="route">
				<div class="jumbotron">
					<div class="container">
						<div class="alert alert-success center" role="alert">
							<h3>
								<strong>${route.name}</strong>(${route.distance}km -
								<spring:eval expression='route.duration' />
								)
							</h3>
						</div>
						<div class="alert alert-success">
							<c:forEach items="${route.segments}" var="segment">

								<div class="list-group">
									<div class="icon">
										<c:url value="/icons/${segment.kind}.png" var="imgUrl" />
										<img src="${imgUrl}" />
									</div>

									<h4 class="list-group-item-heading">${segment.kind}
										- ${segment.distance}km -
										<spring:eval expression='segment.duration' />
									</h4>
									<p class="list-group-item-text">${segment.origin}-${segment.destination}</p>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<v:bootstrapFooter />
</body>
</html>