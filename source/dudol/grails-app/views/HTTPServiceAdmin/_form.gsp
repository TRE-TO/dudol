<%@ page import="br.treto.dudol.HTTPService" %>



<div class="form-group fieldcontain ${hasErrors(bean: HTTPServiceInstance, field: 'baseUrl', 'error')} ">
	<label for="baseUrl" class="col-sm-2 control-label">
		<g:message code="HTTPService.baseUrl.label" default="Base Url" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:field type="url" name="baseUrl" class="form-control" value="${HTTPServiceInstance?.baseUrl}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: HTTPServiceInstance, field: 'key', 'error')} ">
	<label for="key" class="col-sm-2 control-label">
		<g:message code="HTTPService.key.label" default="Key" />
		<span class="required-indicator">*</span>
	</label>
	<div class="col-sm-10">
		<g:textField name="key" class="form-control" value="${HTTPServiceInstance?.key}"/>
	</div>
</div>

