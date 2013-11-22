<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-${domainClass.propertyName}" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<p><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span>&nbsp;<g:message code="default.list.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
    		<h1><g:message code="default.create.label" args="[entityName]" /> <small>Novo cadastro</small></h1>
		</div>

		<div id="create-${domainClass.propertyName}" class="panel panel-default" role="main">
			<div class="panel-body">
				<g:if test="\${flash.message}">
				<div class="message" role="status">\${flash.message}</div>
				</g:if>
				<g:hasErrors bean="\${${propertyName}}">
				<ul class="errors" role="alert">
					<g:eachError bean="\${${propertyName}}" var="error">
					<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
					</g:eachError>
				</ul>
				</g:hasErrors>
				<g:form url="[resource:${propertyName}, action:'save']" <%= multiPart ? ' enctype="multipart/form-data"' : '' %> class="form-horizontal">
					<g:render template="form"/>
					<div class="form-group">
	        			<div class="col-sm-offset-2 col-sm-10">
							<g:submitButton name="create" class="save" value="\${message(code: 'default.button.create.label', default: 'Create')}" />
						</div>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
