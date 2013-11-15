
<%@ page import="br.treto.dudol.HTTPService" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'HTTPService.label', default: 'HTTPService')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-HTTPService" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-HTTPService" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
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
