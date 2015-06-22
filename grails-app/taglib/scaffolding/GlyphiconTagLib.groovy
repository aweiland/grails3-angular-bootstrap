package scaffolding

import org.grails.taglib.GrailsTagException


/**
 * Taglib as a short cut to make glyphicons
 */
class GlyphiconTagLib {
	
	static namespace = "bootstrap"
	
	/**
	 * Render a glyphicon
	 * 
	 * @emptyTag
	 * 
	 * @attr icon REQUIRED The glyphicon to render
	 */
	def glyphicon = { attrs ->
		
		def icon = attrs?.icon
		
		if (!icon) {
			throw new GrailsTagException("'icon' is required")
		}
		
		out << '<span class="glyphicon glyphicon-' << icon << '"></span>'
		
	}
	
}
