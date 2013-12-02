<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'HTTPService.label', default: 'HTTPService')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span>&nbsp;Ver todos</g:link></p>
		</div>
		<div class="page-header">
    		<h1>Serviço HTTP <small>Novo cadastro</small></h1>
		</div>
		
		<div id="create-HTTPService" class="panel panel-default" role="main">
			<div class="panel-body">
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${HTTPServiceInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${HTTPServiceInstance}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</g:hasErrors>
				<g:form url="[controller:'HTTPServiceAdmin', action:'save']" role="form" class="form-horizontal">
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
