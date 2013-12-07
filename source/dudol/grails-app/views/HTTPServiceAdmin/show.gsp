
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
			<p><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span>&nbsp;<g:message code="default.list.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
			<h1>Serviço HTTP <small>Detalhes</small></h1>
		</div>
		<g:if test="${flash.message}">
			<div class="alert alert-success" role="status">${flash.message}</div>
		</g:if>

		<div id="show-HTTPService" class="panel panel-default" role="main">
			<dl class="dl-horizontal">
				<g:if test="${HTTPServiceInstance?.baseUrl}">
					<dt id="baseUrl-label" class="property-label"><g:message code="HTTPService.baseUrl.label" default="Base Url" /></dt>
					
					<dd class="property-value" aria-labelledby="baseUrl-label"><g:fieldValue bean="${HTTPServiceInstance}" field="baseUrl"/></dd>
				</g:if>
			</dl>

			<dl class="dl-horizontal">			
				<g:if test="${HTTPServiceInstance?.key}">
					<dt id="key-label" class="property-label"><g:message code="HTTPService.key.label" default="Key" /></dt>
					
					<dd class="property-value" aria-labelledby="key-label"><g:fieldValue bean="${HTTPServiceInstance}" field="key"/></dd>
				</g:if>
			</dl>
			
			<g:form url="[controller: 'HTTPServiceAdmin', action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" controller="HTTPServiceAdmin" id="${HTTPServiceInstance.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link> ou 
					<g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
