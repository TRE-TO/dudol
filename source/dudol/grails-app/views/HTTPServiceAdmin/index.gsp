
<%@ page import="br.treto.dudol.HTTPService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'HTTPService.label', default: 'HTTPService')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="create" controller="HTTPServiceAdmin" action="create"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;Novo Serviço HTTP</g:link></p>
		</div>
		<div class="page-header">
			<h1>Serviços HTTP <small>Listagem</small></h1>
		</div>

		<div id="list-HTTPService" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="alert alert-info" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="baseUrl" title="${message(code: 'HTTPService.baseUrl.label', default: 'Base Url')}" />
					
						<g:sortableColumn property="key" title="${message(code: 'HTTPService.key.label', default: 'Key')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${HTTPServiceInstanceList}" status="i" var="HTTPServiceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${HTTPServiceInstance.id}">${fieldValue(bean: HTTPServiceInstance, field: "baseUrl")}</g:link></td>
					
						<td>${fieldValue(bean: HTTPServiceInstance, field: "key")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${HTTPServiceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
