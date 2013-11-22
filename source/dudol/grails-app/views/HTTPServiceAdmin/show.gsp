
<%@ page import="br.treto.dudol.HTTPService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'HTTPService.label', default: 'HTTPService')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
			<h1>Serviço HTTP <small>Detalhes</small></h1>
		</div>

		<div id="show-HTTPService" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list HTTPService">
			
				<g:if test="${HTTPServiceInstance?.baseUrl}">
				<li class="fieldcontain">
					<span id="baseUrl-label" class="property-label"><g:message code="HTTPService.baseUrl.label" default="Base Url" /></span>
					
						<span class="property-value" aria-labelledby="baseUrl-label"><g:fieldValue bean="${HTTPServiceInstance}" field="baseUrl"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${HTTPServiceInstance?.key}">
				<li class="fieldcontain">
					<span id="key-label" class="property-label"><g:message code="HTTPService.key.label" default="Key" /></span>
					
						<span class="property-value" aria-labelledby="key-label"><g:fieldValue bean="${HTTPServiceInstance}" field="key"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[controller: 'HTTPServiceAdmin', action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" controller="HTTPServiceAdmin"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
