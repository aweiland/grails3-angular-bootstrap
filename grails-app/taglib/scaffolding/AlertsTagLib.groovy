package scaffolding

import org.grails.taglib.GrailsTagException


class AlertsTagLib {

	static namespace = "bootstrap"

	static List<String> validTypes = [ "info", "success", "warning", "danger", "error" ]
	
	/**
	 * Create a Bootstrap alert
	 * 
	 * @attr type REQUIRED One of the valid Bootstrap alert types: "info", "success", "warning", "danger"
	 * @attr dismissable If the alert is dismissable, ie there is a close button and it goes away.  Default is true;
	 */
	def alert = { attrs, body ->
		def type = attrs.type ?: "info";
		def dismissable = Boolean.valueOf(attrs.dismissable ?: true);
		
		type = (type.equals("error")) ?: "danger"; // Little helper foo
		
		if (!validTypes.contains(type)) {
			throw new GrailsTagException("Invalid alert type")
		}
		
		out << '<div class="alert alert-' << type << '">'
		if (dismissable) {
			out << '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'
		}
		out << body()
		out << '</div>'
	}

}
