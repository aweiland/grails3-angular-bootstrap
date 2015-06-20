<div class="input-group">
	<div class="input-group-addon">@</div>
<%
	def attrs = [type: 'email', name: property, value: value]
	if (required) attrs.required = ''
	out << g.field(attrs)
%>
</div>