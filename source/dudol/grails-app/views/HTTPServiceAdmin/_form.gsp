<%@ page import="br.treto.dudol.HTTPService" %>



<div class="form-group fieldcontain ${hasErrors(bean: HTTPServiceInstance, field: 'baseUrl', 'error')} ">
	<label for="baseUrl" class="col-sm-2 control-label">
		<g:message code="HTTPService.baseUrl.label" default="Base Url" />
		
	</label>
	<div class="col-sm-10">
		<g:field type="url" name="baseUrl" value="${HTTPServiceInstance?.baseUrl}"/>
	</div>
</div>

<div class="form-group fieldcontain ${hasErrors(bean: HTTPServiceInstance, field: 'key', 'error')} ">
	<label for="key" class="col-sm-2 control-label">
		<g:message code="HTTPService.key.label" default="Key" />
		
	</label>
	<div class="col-sm-10">
		<g:textField name="key" value="${HTTPServiceInstance?.key}"/>
	</div>
</div>

