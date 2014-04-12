<%@ page import="br.treto.dudol.HTTPService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'HTTPService.label', default: 'HTTPService')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span>&nbsp;<g:message code="default.list.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
			<h1><g:message code="top.httpservices" default="HTTP Service" /> <small><g:message code="subtitle.edit" default="Edit" /></small></h1>
		</div>

		<g:if test="${flash.message}">
			<div class="alert alert-success">${flash.message}</div>
		</g:if>

		<div id="edit-HTTPService" class="panel panel-default" role="main">
  			<div class="panel-body">
				<g:hasErrors bean="${HTTPServiceInstance}">
					<div class="alert alert-danger" role="alert">
						<g:eachError bean="${HTTPServiceInstance}" var="error">
							<p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
						</g:eachError>
					</div>
				</g:hasErrors>
				<g:form controller="HTTPServiceAdmin" action="update" id="${HTTPServiceInstance.id}" method="PUT" class="form-horizontal">
					<g:hiddenField name="version" value="${HTTPServiceInstance?.version}" />
					<g:render template="form"/>
					<div class="form-group">
	        			<div class="col-sm-offset-2 col-sm-10">
							<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
