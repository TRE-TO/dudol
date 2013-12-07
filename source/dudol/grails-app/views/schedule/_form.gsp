<%@ page import="br.treto.dudol.Schedule" %>



<div class="form-group fieldcontain ${hasErrors(bean: scheduleInstance, field: 'executable', 'error')} ">
	<label for="executable" class="col-sm-2 control-label">
		<g:message code="schedule.executable.label" default="Executable" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="executable" value="${scheduleInstance?.executable}" required=""/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: scheduleInstance, field: 'key', 'error')} ">
	<label for="key" class="col-sm-2 control-label">
		<g:message code="schedule.key.label" default="Key" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField class="form-control" name="key" value="${scheduleInstance?.key}" required=""/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: scheduleInstance, field: 'rateInSeconds', 'error')} required">
	<label for="rateInSeconds" class="col-sm-2 control-label">
		<g:message code="schedule.rateInSeconds.label" default="Rate In Seconds" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:field class="form-control"  name="rateInSeconds" type="number" value="${scheduleInstance.rateInSeconds}" required=""/>
	</div>
</div>

