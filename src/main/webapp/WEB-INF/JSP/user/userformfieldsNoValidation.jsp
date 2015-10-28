<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class = "form-group">
	<form:label path="username" cssClass="col-sm-5 control-label">Username:
	</form:label>
	<div class="col-sm-3">
		<form:input path="username" autofocus='autofocus' cssClass="form-control"  />		
	</div>		
	<div class="col-sm-3 has-error">
		<form:errors path="username" cssClass="help-block"/>
	</div>
</div>

<div class = "form-group">
	<form:label path="password" cssClass="col-sm-5 control-label">Password:
	</form:label>
	<div class="col-sm-3">
		<form:password path="password" autofocus='autofocus' cssClass="form-control"/>		
	</div>
	<div class="col-sm-3 has-error">
		<form:errors path="password" cssClass="help-block"/>
	</div>
</div>

<div class = "form-group">
	<form:label path="name" cssClass="col-sm-5 control-label">Name:
	</form:label>
	<div class="col-sm-3">
		<form:input path="name" autofocus='autofocus' cssClass="form-control"/>		
	</div>
	<div class="col-sm-3 has-error">
		<form:errors path="name" cssClass="help-block" />
	</div>
</div>

<div class = "form-group">
	<form:label path="surname" cssClass="col-sm-5 control-label">Surname:
	</form:label>
	<div class="col-sm-3">
		<form:input path="surname" autofocus='autofocus' cssClass="form-control"/>		
	</div>
	<div class="col-sm-3 has-error">
		<form:errors path="surname" cssClass="help-block"/>
	</div>
</div>

<div class = "form-group">
	<form:label path="address.street" cssClass="col-sm-5 control-label">Street:
	</form:label>
	<div class="col-sm-3">
		<form:input path="address.street" cssClass="form-control"/>		
	</div>
	<div class="col-sm-3 has-error">
		<form:errors path="address.street" cssClass="help-block"/>
	</div>
</div>

<div class = "form-group">
	<form:label path="address.houseNr" cssClass="col-sm-5 control-label">Housenumber:
	</form:label>
	<div class="col-sm-3">
		<form:input path="address.houseNr" cssClass="form-control"/>		
	</div>
	<div class="col-sm-3 has-error">
		<form:errors path="address.houseNr" cssClass="help-block"/>
	</div>
</div>

<div class = "form-group">
	<form:label path="address.postcode" cssClass="col-sm-5 control-label">Postcode:
	</form:label>
	<div class="col-sm-2">
		<form:input path="address.postcode" cssClass="form-control"/>		
	</div>
	<div class="col-sm-offset-0 col-sm-2 has-error">
		<form:errors path="address.postcode" cssClass="help-block"/>
	</div>
</div>

<div class = "form-group">
	<form:label path="address.city" cssClass="col-sm-5 control-label">City:
	</form:label>
	<div class="col-sm-3">
		<form:input path="address.city" cssClass="form-control" />		
	</div>
	<div class="col-sm-3 has-error">
		<form:errors path="address.city" cssClass="help-block" />
	</div>
</div>