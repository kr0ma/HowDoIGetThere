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
								<li><a href='<c:url value ="/user/controlpanel/search"/>'>SearchPreferences</a></li>
								<li class="active"><a
									href='<c:url value ="/user/controlpanel/password"/>'>Change
										password</a></li>
							</ul>
						</div>
						<div class="col-md-9 main">
							<form:form commandName='changePasswordForm'
								cssClass="form-horizontal">
								
								<div class="form-group">
									<form:label path="newPassword" cssClass="col-md-6 control-label">New password:</form:label>
									<div class="col-md-3">
										<form:password path="newPassword" autofocus='autofocus'
											 cssClass="form-control" />
									</div>
									<div class="col-md-5 has-error">
										<form:errors path="newPassword" cssClass="help-block" />
									</div>
								</div>
								
								<div class="form-group">
									<form:label path="confirmNewPassword" cssClass="col-md-6 control-label">Confirm new password:</form:label>
									<div class="col-md-3">
										<form:password path="confirmNewPassword" autofocus='autofocus'
											cssClass="form-control" />
									</div>
									<div class="col-md-5 has-error">
										<form:errors path="confirmNewPassword" cssClass="help-block" />
									</div>
								</div>
								
								<div class="form-group">
									<form:label path="oldPassword" cssClass="col-md-6 control-label">old password:</form:label>
									<div class="col-md-3">
										<form:password path="oldPassword" autofocus='autofocus'
											 cssClass="form-control" />
									</div>
									<div class="col-md-5 has-error">
										<form:errors path="oldPassword" cssClass="help-block" />
									</div>
								</div>
								<div class="form-group has-error col-md-offset-3">
									<form:errors cssClass='help-block' />
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