<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="form-group">
	<form:label path="name" cssClass="col-md-4 control-label">Name:
	</form:label>
	<div class="col-md-3">
		<form:input path="name" autofocus='autofocus' required='required' cssClass="form-control" />
	</div>
	<div class="col-md-5 has-error">
		<form:errors path="name" cssClass="help-block" />
	</div>
</div>

<div class="form-group">
	<form:label path="surname" cssClass="col-md-4 control-label">Surname:
	</form:label>
	<div class="col-md-3">
		<form:input path="surname" autofocus='autofocus' required='required'
			cssClass="form-control" />
	</div>
	<div class="col-md-5 has-error">
		<form:errors path="surname" cssClass="help-block" />
	</div>
</div>

<div class="form-group">
	<form:label path="address.street" cssClass="col-md-4 control-label">Street:
	</form:label>
	<div class="col-md-3">
		<form:input path="address.street" required='required' cssClass="form-control" />
	</div>
	<div class="col-md-5 has-error">
		<form:errors path="address.street" cssClass="help-block" />
	</div>
</div>

<div class="form-group">
	<form:label path="address.houseNr" cssClass="col-md-4 control-label">Housenumber:
	</form:label>
	<div class="col-md-3">
		<form:input path="address.houseNr" required='required' cssClass="form-control" />
	</div>
	<div class="col-md-5 has-error">
		<form:errors path="address.houseNr" cssClass="help-block" />
	</div>
</div>

<div class="form-group">
	<form:label path="address.postcode" cssClass="col-md-4 control-label">Postcode:
	</form:label>
	<div class="col-md-2">
		<form:input path="address.postcode" required='required'  type='number' min='1000' max='9999' cssClass="form-control" />
	</div>
	<div class="col-md-offset-0 col-md-3 has-error" id="postcodeError">
		<form:errors path="address.postcode" cssClass="help-block" />
	</div>
</div>

<div class="form-group">
	<form:label path="address.city" cssClass="col-md-4 control-label">City:
	</form:label>
	<div class="col-md-3">
		<form:input path="address.city" required='required' cssClass="form-control" />
	</div>
	<div class="col-md-5 has-error">
		<form:errors path="address.city" cssClass="help-block" />
	</div>
</div>