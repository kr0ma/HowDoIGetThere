<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html>
<v:head title="User preferences"></v:head>
<body>
	<v:menu title="user" />
	<div class="container">

		<div class="jumbotron">
			<div class="container">
				<div class="alert alert-success center" role="alert">
					<h3>Control panel</h3>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-md-3 sidebar">
							<ul class="nav nav-sidebar">
								<li><a
									href='<c:url value ="/user/controlpanel/userdetails"/>'>Userdetails
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="active"><a
									href='<c:url value ="/user/controlpanel/search"/>'>SearchPreferences</a></li>
								<li><a
									href='<c:url value ="/user/controlpanel/password"/>'>Change password</a></li>
							</ul>
						</div>
						<div class="col-md-9 main">
							<form:form commandName='searchPreferenceForm'
								cssClass="form-horizontal">

								<div class="form-group">
									<c:forEach items="${travelPreferences}" var="travelPreference">
										<div class="has-feedback">
											<form:checkbox path="travelPreferences"
												value="${travelPreference}" label="${travelPreference}" />
										</div>
									</c:forEach>
									<div class="has-error">
										<form:errors path="travelPreferences"
											element="label class='control-label'" />
									</div>
								</div>
								<div class="form-group">
									<input type='submit' value='Save' id='savebutton'
										class="btn btn-primary btn-lg btn-block">
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
	<v:bootstrapFooter />
	<script>
		document.getElementById('preferencesform').onsubmit = function() {
			document.getElementById('savebutton').disabled = true;
		};
	</script>
</body>
</html>