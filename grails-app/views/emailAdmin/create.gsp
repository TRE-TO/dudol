<%@ page import="br.treto.dudol.Email" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'email.label', default: 'Email')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="page-header">
		  <h1>Emails <small>Configurações para o envio de emails</small></h1>
		</div>
		<g:if test="${flash.message}">
			<div class="alert alert-success">${flash.message}</div>
		</g:if>
		<div class="panel panel-default">
  			<div class="panel-body">
				<g:hasErrors bean="${emailInstance}">
					<div class="alert alert-danger" role="alert">
						<g:eachError bean="${emailInstance}" var="error">
						<p <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></p>
						</g:eachError>
					</div>
				</g:hasErrors>
				<g:form controller="EmailAdmin" action="create" id="${emailInstance.id}" method="PUT" class="form-horizontal" role="form">
					<g:hiddenField name="version" value="${emailInstance?.version}" />
					<g:render template="form"/>
					<div class="form-group">
	        			<div class="col-sm-offset-2 col-sm-10">
							<g:actionSubmit class="btn btn-primary" action="create" value="Salvar" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>