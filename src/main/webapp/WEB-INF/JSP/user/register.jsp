<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>

<!doctype html>
<html>
<v:head title="User registration"></v:head>
<body>
	<v:menu title="user" />
	<div class="container">

		<div class="starter-template">
			<h1>User registration</h1>
			<c:url value='/user/register' var='url' />
			<form:form action='${url}' commandName='user' id='registrationform' cssClass="form-horizontal" >
				<jsp:include page="userformfieldsNoValidation.jsp" />
				<div class="form-group"> 
					<div class="col-sm-offset-5 col-sm-1">
						<input type='submit' value='Register' id='registerbutton' class="btn btn-primary btn-lg" >
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<v:bootstrapFooter />
	<script>
		document.getElementById('registrationform').onsubmit = function() {
			document.getElementById('registerbutton').disabled = true;
		};
	</script>
</body>
</html>