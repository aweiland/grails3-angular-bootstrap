package scaffolding
/**
 * Form tag lib for Bootstrap form elements
 *
 */
class FormTagLib {

	static namespace = "bf"
	
	def fieldError = { attrs ->
		def bean = attrs.bean
		def field = attrs.field
		fieldErrorInternal(bean, field)
	}
	
	def text = { attrs ->
		def label = attrs.remove('label')
		def bean = attrs.remove('bean')
		def field = attrs.remove('field')
		def cssClass = attrs.remove('class') ?: '';
		boolean required = attrs.remove('required') ? true: false;

		def fVal = g.fieldValue(bean: bean, field: field)
		
		out << '<div class="form-group' << 
			g.hasErrors(bean: bean, field: field) {
				out << ' has-error'
			}  << '">'
		out << "<label class=\"control-label\" for=\"${field}\">${label}" << (required == true ? '<span class="required">*</span>' : '') << '</label>'
		out << "<input type=\"text\" name=\"${field}\" id=\"${field}\" class=\"form-control ${cssClass}\" value=\"${fVal}\" "
		attrs.each  { k, v ->
			out << "${k} = \"${v}\" "
		}
		out << " />"
		
		fieldErrorInternal(bean, field)
		out << '</div>';
		
	}
	
	def fieldErrorInternal(def bean, def field) {
		out << g.hasErrors(bean: bean, field: field) {
			g.eachError(bean: bean, field: field) {
				out << '<span class="help-inline">'
				out << g.message(error: it)
				out << '</span>'
			}
		}
	}
	
}
