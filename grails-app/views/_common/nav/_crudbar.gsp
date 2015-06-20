<%-- Only show the "Pills" navigation menu if a controller exists (but not for home) --%>
<g:if test="${	params.controller != null
			&&	params.controller != ''}">
	<g:set var="entityName" value="${message(code: params.controller+'.label', default: params.controller.substring(0,1).toUpperCase() + params.controller.substring(1).toLowerCase())}" />
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<span class="navbar-brand"> ${entityName}</span>
			</div>
			<div class="collapse navbar-collapse" id="bs-model-navbar-actions">
				<ul class="nav navbar-nav">
					<li class="${ params.action == "index" ? 'active' : '' }">
						<g:link action="index"><span class="glyphicon glyphicon-th-list"></span>
							<g:message code="default.list.label" args="[entityName]" />
						</g:link>
					</li>
					<li class="${ params.action == "create" ? 'active' : '' }">
						<g:link action="create"><span class="glyphicon glyphicon-plus"></span>
							<g:message code="default.new.label" args="[entityName]" />
						</g:link>
					</li>

					<g:if test="${ params.action == 'show' || params.action == 'edit' }">
						<li class="${ params.action == "edit" ? 'active' : '' }"><g:link action="edit" id="${params.id}">
								<span class="glyphicon glyphicon-pencil"></span>
								<g:message code="default.edit.label" args="[entityName]" />
							</g:link></li>
						<li class=""><g:render
								template="/_common/modals/deleteTextLink" /></li>
					</g:if>
				</ul>
			</div>
		</div>
	</nav>
</g:if>