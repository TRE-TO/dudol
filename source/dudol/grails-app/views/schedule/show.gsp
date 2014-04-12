
<%@ page import="br.treto.dudol.Schedule" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'schedule.label', default: 'Schedule')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<p><g:link class="list" action="index"><span class="glyphicon glyphicon-list"></span>&nbsp;<g:message code="default.list.label" args="[entityName]" /></g:link></p>
		</div>
		<div class="page-header">
			<h1><g:message code="top.schedules" default="HTTP Service" /> <small><g:message code="subtitle.details" default="Details" /></small></h1>
		</div>
		<g:if test="${flash.message}">
			<div class="alert alert-success" role="status">${flash.message}</div>
		</g:if>

		<div id="show-schedule" class="panel panel-default" role="main">
			<dl class="dl-horizontal">
				<g:if test="${scheduleInstance?.executable}">
					<dt id="executable-label" class="property-label"><g:message code="schedule.executable.label" default="Executable" />:</dt>
					
					<dd class="property-value" aria-labelledby="executable-label"><g:fieldValue bean="${scheduleInstance}" field="executable"/></dd>
					
				</g:if>
			</dl>
		
			<dl class="dl-horizontal">
				<g:if test="${scheduleInstance?.key}">
					<dt id="key-label" class="property-label"><g:message code="schedule.key.label" default="Key" />:</dt>
					
						<dd class="property-value" aria-labelledby="key-label"><g:fieldValue bean="${scheduleInstance}" field="key"/></dd>
					
				</g:if>
			</dl>
		
			<dl class="dl-horizontal">
				<g:if test="${scheduleInstance?.rateInSeconds}">
					<dt id="rateInSeconds-label" class="property-label"><g:message code="schedule.rateInSeconds.label" default="Rate In Seconds" />:</dt>
					
						<dd class="property-value" aria-labelledby="rateInSeconds-label"><g:fieldValue bean="${scheduleInstance}" field="rateInSeconds"/></dd>
					
				</g:if>
			</dl>
			
			<g:form url="[resource:scheduleInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${scheduleInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link> ou 
					<g:actionSubmit class="btn btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
