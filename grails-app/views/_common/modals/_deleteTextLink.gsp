<a href="#DeleteModal" role="button" class="" data-toggle="modal" title="${message(code: 'default.button.delete.label', default: 'Delete')}">
	<i class="glyphicon glyphicon-trash"></i>
	<g:message code="default.button.delete.label" default="Delete"/>
</a>

<g:render template="/_common/modals/deleteDialog" model="[item: item]"/>