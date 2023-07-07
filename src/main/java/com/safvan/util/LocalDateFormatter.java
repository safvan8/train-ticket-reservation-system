package com.safvan.util;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Locale;

/**
 * LocalDateFormatter class is a formatter for LocalDate objects.
 *
 * This class implements the Formatter interface provided by Spring Framework.
 * It is used to parse LocalDate objects from text and format LocalDate objects
 * to text. Spring automatically use this formatter for converting LocalDate
 * objects to and from String representations. This allows seamless binding of
 * LocalDate properties in your Spring MVC controllers and forms.
 *
 * @author Safvan
 * @version 1.0
 * @since 1.0
 * 
 * For more information, refer to the Spring Framework Formatter documentation:
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/format/Formatter.html
 */
@Component
public class LocalDateFormatter implements Formatter<LocalDate> {

	/**
	 * Parse the text using the DateTimeFormatter.ISO_DATE formatter, which follows
	 * the ISO-8601 standard format for dates. The parsed LocalDate object is then
	 * returned.
	 *
	 * @param text   the text to parse
	 * @param locale the locale to use for parsing (ignored in this implementation)
	 * @return the parsed LocalDate object
	 * @throws ParseException if the text cannot be parsed as a LocalDate
	 */
	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
	}

	/**
	 * Format the LocalDate object as a String in the ISO-8601 date format. The
	 * formatted String is then returned.
	 *
	 * @param object the LocalDate object to format
	 * @param locale the locale to use for formatting (ignored in this
	 *               implementation)
	 * @return the formatted String
	 */
	@Override
	public String print(LocalDate object, Locale locale) {
		return DateTimeFormatter.ISO_DATE.format(object);
	}
}
