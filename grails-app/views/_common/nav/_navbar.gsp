<div class="navbar navbar-inverse" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${createLink(uri: '/')}">Grails</a>
		</div>


		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li
					<%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a
					href="${createLink(uri: '/')}">Home</a></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Controllers <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<g:each var="c"
							in="${grailsApplication.controllerClasses.sort { it.fullName } }">
							<li
								<%= c.logicalPropertyName == controllerName ? ' class="active"' : '' %>><g:link
									controller="${c.logicalPropertyName}">
									${c.naturalName}
								</g:link></li>
						</g:each>
					</ul></li>
			</ul>
		</div>
	</div>
</div>