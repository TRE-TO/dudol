<%=packageName%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div class="page-header">
		  <h1><g:message code="default.edit.label" args="[entityName]" /><small>Coloque um aposto aqui</small></h1>
		</div>
		<g:if test="\${flash.message}">
			<div class="alert alert-success">\${flash.message}</div>
		</g:if>

		<div class="panel panel-default">
  			<div class="panel-body">

		%{-- <div id="edit-${domainClass.propertyName}" class="content scaffold-edit" role="main"> --}%
			<g:hasErrors bean="\${${propertyName}}">
				<div class="errors" role="alert">
					<g:eachError bean="\${${propertyName}}" var="error">
						<p <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></p>
					</g:eachError>
				</div>
			</g:hasErrors>
			<g:form url="[resource:${propertyName}, action:'update']" method="PUT" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
				<g:hiddenField name="version" value="\${${propertyName}?.version}" />
				<g:render template="form"/>
				<div class="form-group">
        			<div class="col-sm-offset-2 col-sm-10">
						<g:actionSubmit class="save" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" />
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>
