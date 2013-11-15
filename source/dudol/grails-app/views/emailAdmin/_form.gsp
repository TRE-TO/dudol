<%@ page import="br.treto.dudol.Email" %>


<div class="form-group fieldcontain ${hasErrors(bean: emailInstance, field: 'host', 'error')} ">
	<label for="host" class="col-sm-2 control-label">
		<g:message code="email.host.label" default="Host" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField name="host" class="form-control" value="${emailInstance?.host}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: emailInstance, field: 'port', 'error')} required">
	<label for="port" class="col-sm-2 control-label">
		<g:message code="email.port.label" default="Port" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:field name="port" type="number" class="form-control" value="${emailInstance.port}" required=""/>
	</div>
</div>

<hr/>

<div class="form-group fieldcontain ${hasErrors(bean: emailInstance, field: 'username', 'error')} ">
	<label for="username" class="col-sm-2 control-label" class="col-sm-2 control-label">
		<g:message code="email.username.label" default="Username" />
		
	</label>
	<div class="col-sm-10">
		<g:textField name="username" class="form-control" class="form-control" value="${emailInstance?.username}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: emailInstance, field: 'password', 'error')} ">
	<label for="password" class="col-sm-2 control-label">
		<g:message code="email.password.label" default="Password" />
		
	</label>
	<div class="col-sm-10">
		<g:textField name="password" class="form-control" value="${emailInstance?.password}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: emailInstance, field: 'ssl', 'error')} ">
	<label for="ssl" class="col-sm-2 control-label">SSL</label>
	<div class="col-sm-10">
		<g:checkBox name="ssl" class="form-control" value="${emailInstance?.ssl}" />
	</div>
</div>

