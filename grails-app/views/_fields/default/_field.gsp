<%@ page defaultCodec="html"%>
<div class="form-group ${invalid ? 'has-error' : ''}">
	<label class="control-label" for="${property}">${label}</label>
	<g:if test="${addonPrefix || addonPostfix }">
		<div class="input-group">
	</g:if>
	<g:if test="${addonPrefix}">
		<span class="input-group-addon">${addonPrefix}</span>
	</g:if>
	<f:input bean="${bean}" property="${property}" class="form-control" />
	<g:if test="${addonPostfix}">
		<span class="input-group-addon">
			${addonPostfix}
		</span>
	</g:if>
	<g:if test="${addonPrefix || addonPostfix }">
		</div>
	</g:if>

	<g:if test="${invalid}">
		<p class="help-block">${errors.join('<br />')}</p>
	</g:if>
</div>