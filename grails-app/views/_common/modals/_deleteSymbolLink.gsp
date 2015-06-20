<a href="#DeleteModal" role="button" class="btn btn-danger btn-sm" data-toggle="modal" title="${message(code: 'default.button.delete.label', default: 'Delete')}">
	<i class="glyphicon glyphicon-trash"></i>
</a>

<g:render template="/_common/modals/deleteDialog" model="[item: item]"/>