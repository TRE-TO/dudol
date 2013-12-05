<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span>&nbsp;<g:message code="default.list.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
    		<h1><g:message code="default.create.label" args="[entityName]" /> <small>Novo cadastro</small></h1>
		</div>

		<g:if test="${flash.message}">
			<div class="alert alert-success">${flash.message}</div>
		</g:if>

		<div id="create-schedule" class="panel panel-default" role="main">
			<div class="panel-body">
				<g:hasErrors bean="${scheduleInstance}">
					<div class="alert alert-danger" role="alert">
						<g:eachError bean="${scheduleInstance}" var="error">
							<p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
						</g:eachError>
					</div>
				</g:hasErrors>
				<g:form url="[resource:scheduleInstance, action:'save']"  role="form" class="form-horizontal">
					<g:render template="form"/>
					<div class="form-group">
	        			<div class="col-sm-offset-2 col-sm-10">
							<g:submitButton name="create" class="btn btn-primary" value="${message(code: 'default.button.create.label', default: 'Create')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
