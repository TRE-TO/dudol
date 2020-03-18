
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
			<p>
				<g:link class="create" controller="HTTPServiceAdmin" action="create">
					<span class="glyphicon glyphicon-plus-sign"></span>
					&nbsp;<g:message code="default.add.label" default="Add new" />
				</g:link>
			</p>
		</div>
		<div class="page-header">
			<h1><g:message code="top.httpservices" default="HTTP Service" /> <small><g:message code="subtitle.listing" default="Listing" /></small></h1>
		</div>

		<div id="list-HTTPService" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="key" title="${message(code: 'HTTPService.key.label', default: 'Key')}" />
					
						<g:sortableColumn property="baseUrl" title="${message(code: 'HTTPService.baseUrl.label', default: 'Base Url')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${HTTPServiceInstanceList}" status="i" var="HTTPServiceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${HTTPServiceInstance.id}">${fieldValue(bean: HTTPServiceInstance, field: "key")}</g:link></td>
					
						<td>${fieldValue(bean: HTTPServiceInstance, field: "baseUrl")}</td>
					
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
