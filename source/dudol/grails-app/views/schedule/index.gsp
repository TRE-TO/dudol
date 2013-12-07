
<%@ page import="br.treto.dudol.Schedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="create" action="create"><span class="glyphicon glyphicon-plus-sign"></span>&nbsp;<g:message code="default.new.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
			<h1>Agendamento <small>Listagem</small></h1>
		</div>

		<div id="list-schedule" class="content scaffold-list" role="main">
			<g:if test="${flash.message}">
				<div class="alert alert-success" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-bordered table-hover">
			<thead>
					<tr>
					
						<g:sortableColumn property="executable" title="${message(code: 'schedule.executable.label', default: 'Executable')}" />
					
						<g:sortableColumn property="key" title="${message(code: 'schedule.key.label', default: 'Key')}" />
					
						<g:sortableColumn property="rateInSeconds" title="${message(code: 'schedule.rateInSeconds.label', default: 'Rate In Seconds')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${scheduleInstanceList}" status="i" var="scheduleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${scheduleInstance.id}">${fieldValue(bean: scheduleInstance, field: "executable")}</g:link></td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "key")}</td>
					
						<td>${fieldValue(bean: scheduleInstance, field: "rateInSeconds")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${scheduleInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
