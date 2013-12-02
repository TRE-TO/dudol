<%@ page import="br.treto.dudol.Schedule" %>



<div class="form-group fieldcontain ${hasErrors(bean: scheduleInstance, field: 'executable', 'error')} ">
	<label for="executable" class="col-sm-2 control-label">
		<g:message code="schedule.executable.label" default="Executable" />
		
	</label>
	<div class="col-sm-10">
		<g:textField name="executable" value="${scheduleInstance?.executable}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: scheduleInstance, field: 'key', 'error')} ">
	<label for="key" class="col-sm-2 control-label">
		<g:message code="schedule.key.label" default="Key" />
		
	</label>
	<div class="col-sm-10">
		<g:textField name="key" value="${scheduleInstance?.key}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: scheduleInstance, field: 'rateInSeconds', 'error')} required">
	<label for="rateInSeconds" class="col-sm-2 control-label">
		<g:message code="schedule.rateInSeconds.label" default="Rate In Seconds" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:field name="rateInSeconds" type="number" value="${scheduleInstance.rateInSeconds}" required=""/>
	</div>
</div>

