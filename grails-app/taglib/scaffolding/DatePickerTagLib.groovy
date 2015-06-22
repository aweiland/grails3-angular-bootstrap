package scaffolding

import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.web.servlet.support.RequestContextUtils

import java.text.DateFormat
import java.text.DateFormatSymbols

class DatePickerTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	
	static namespace = "bootstrap"
	
	def requestDataValueProcessor
	def messageSource
	
	/**
	 * A simple date picker that renders a date as selects.<br/>
	 * This is just an initial hack - can be widely improved!
	 * e.g. &lt;bs:datePicker name="myDate" value="${new Date()}" /&gt;
	 *
	 * @emptyTag
	 *
	 * @attr name REQUIRED The name of the date picker field set
	 * @attr value The current value of the date picker; defaults to now if not specified
	 * @attr precision The desired granularity of the date to be rendered
	 * @attr noSelection A single-entry map detailing the key and value to use for the "no selection made" choice in the select box. If there is no current selection this will be shown as it is first in the list, and if submitted with this selected, the key that you provide will be submitted. Typically this will be blank.
	 * @attr years A list or range of years to display, in the order specified. i.e. specify 2007..1900 for a reverse order list going back to 1900. If this attribute is not specified, a range of years from the current year - 100 to current year + 100 will be shown.
	 * @attr relativeYears A range of int representing values relative to value. For example, a relativeYears of -2..7 and a value of today will render a list of 10 years starting with 2 years ago through 7 years in the future. This can be useful for things like credit card expiration dates or birthdates which should be bound relative to today.
	 * @attr id the DOM element id
	 * @attr disabled Makes the resulting inputs and selects to be disabled. Is treated as a Groovy Truth.
	 * @attr readonly Makes the resulting inputs and selects to be made read only. Is treated as a Groovy Truth.
	 */
	def datePicker = { attrs ->
		def out = out // let x = x ?
		def inputClasses = attrs['class']
		def xdefault = attrs['default']
		if (xdefault == null) {
			xdefault =  new Date()
		} else if (xdefault.toString() != 'none') {
			if (xdefault instanceof String) {
				xdefault = DateFormat.getInstance().parse(xdefault)
			} else if (!(xdefault instanceof Date)) {
				throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
			}
		} else {
			xdefault = null
		}
		def years = attrs.years
		def relativeYears = attrs.relativeYears
		if (years != null && relativeYears != null) {
			throwTagError 'Tag [datePicker] does not allow both the years and relativeYears attributes to be used together.'
		}

		if (relativeYears != null) {
			if (!(relativeYears instanceof IntRange)) {
				// allow for a syntax like relativeYears="[-2..5]". The value there is a List containing an IntRage.
				if ((!(relativeYears instanceof List)) || (relativeYears.size() != 1) || (!(relativeYears[0] instanceof IntRange))){
					throwTagError 'The [datePicker] relativeYears attribute must be a range of int.'
				}
				relativeYears = relativeYears[0]
			}
		}
		def value = attrs.value
		if (value.toString() == 'none') {
			value = null
		} else if (!value) {
			value = xdefault
		}
		def name = attrs.name
		def id = attrs.id ?: name

		def noSelection = attrs.noSelection
		if (noSelection != null) {
			noSelection = noSelection.entrySet().iterator().next()
		}

		final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
		def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
			(grailsApplication.config.grails.tags.datePicker.default.precision ?
				PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
				PRECISION_RANKINGS["minute"]))

		def day
		def month
		def year
		def hour
		def minute
		def dfs = new DateFormatSymbols(RequestContextUtils.getLocale(request))

		def c = null
		if (value instanceof Calendar) {
			c = value
		} else if (value != null) {
			c = new GregorianCalendar()
			c.setTime(value)
		}

		if (c != null) {
			day = c.get(GregorianCalendar.DAY_OF_MONTH)
			month = c.get(GregorianCalendar.MONTH) + 1		// add one, as Java stores month from 0..11
			year = c.get(GregorianCalendar.YEAR)
			hour = c.get(GregorianCalendar.HOUR_OF_DAY)
			minute = c.get(GregorianCalendar.MINUTE)
		}

		if (years == null) {
			def tempyear

			if (year == null) {
				// If no year, we need to get current year to setup a default range... ugly
				def tempc = new GregorianCalendar()
				tempc.setTime(new Date())
				tempyear = tempc.get(GregorianCalendar.YEAR)
			} else {
				tempyear = year
			}

			if (relativeYears) {
				if (relativeYears.reverse) {
					years = (tempyear + relativeYears.toInt)..(tempyear + relativeYears.fromInt)
				} else {
					years = (tempyear + relativeYears.fromInt)..(tempyear + relativeYears.toInt)
				}
			} else {
				years = (tempyear - 100)..(tempyear + 100)
			}
		}

		booleanToAttribute(attrs, 'disabled')
		booleanToAttribute(attrs, 'readonly')

		// get the localized format for dates. NOTE: datepicker only uses Lowercase syntax and does not understand hours, seconds, etc. (it uses: dd, d, mm, m, yyyy, yy)
		 String dateFormat = messageSource.getMessage("default.date.datepicker.format",null,null,LocaleContextHolder.locale )
		if (!dateFormat) { // if date.datepicker.format is not used use date.format but remove characters not used by datepicker
			dateFormat = messageSource.getMessage("default.date.format",null,'mm/dd/yyyy',LocaleContextHolder.locale )\
				.replace('z', '').replace('Z', '')\
				.replace('h', '').replace('H', '')\
				.replace('k', '').replace('K', '')\
				.replace('w', '').replace('W', '')\
				.replace('s', '').replace('S', '')\
				.replace('m', '').replace('a', '').replace('D', '').replace('E', '').replace('F', '').replace('G', '').replace(':', '')\
				.replace('MMM', 'MM').replace('ddd', 'dd')\
				.trim()\
				.toLowerCase()
		}
		String formattedDate = g.formatDate(format: dateFormat.replace('m', 'M'), date: c?.getTime())
		out.println "	<input id=\"${id}\" name=\"${name}\" class=\"datepicker ${inputClasses}\" size=\"16\" type=\"text\" value=\"${formattedDate}\" data-date-format=\"${dateFormat}\"/>"
	}



	 /**
	  * Dump out attributes in HTML compliant fashion.
	  */
	void outputAttributes(attrs, writer, boolean useNameAsIdIfIdDoesNotExist = false) {
		attrs.remove('tagName') // Just in case one is left
		attrs.each { k, v ->
			if(v != null) {
				writer << k
				writer << '="'
				writer << v.encodeAsHTML()
				writer << '" '
			}
		}
		if (useNameAsIdIfIdDoesNotExist) {
			outputNameAsIdIfIdDoesNotExist(attrs, writer)
		}
	}

	/**
	 * getter to obtain RequestDataValueProcessor from
	 */
    private getRequestDataValueProcessor() {
        if (requestDataValueProcessor == null && grailsAttributes.getApplicationContext().containsBean("requestDataValueProcessor")){
            requestDataValueProcessor = grailsAttributes.getApplicationContext().getBean("requestDataValueProcessor")
        }
        return requestDataValueProcessor
    }

	 private processFormFieldValueIfNecessary(name, value, type) {
		 def requestDataValueProcessor = getRequestDataValueProcessor()
		 def processedValue = value
		 if(requestDataValueProcessor != null) {
			 processedValue = requestDataValueProcessor.processFormFieldValue(request, name, "${value}", type)
		 }
		 return processedValue
	 }

	/**
	* Some attributes can be defined as Boolean values, but the html specification
	* mandates the attribute must have the same value as its name. For example,
	* disabled, readonly and checked.
	*/
	private void booleanToAttribute(attrs, String attrName) {
		def attrValue = attrs.remove(attrName)
		// If the value is the same as the name or if it is a boolean value,
		// reintroduce the attribute to the map according to the w3c rules, so it is output later
		if (Boolean.valueOf(attrValue) ||
		  (attrValue instanceof String && attrValue?.equalsIgnoreCase(attrName))) {
			attrs.put(attrName, attrName)
		} else if (attrValue instanceof String && !attrValue?.equalsIgnoreCase('false')) {
			// If the value is not the string 'false', then we should just pass it on to
			// keep compatibility with existing code
			attrs.put(attrName, attrValue)
		}
	}
	
}
