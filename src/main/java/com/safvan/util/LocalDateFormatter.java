package com.safvan.util;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.util.Locale;

/**
 * 
 * @author safvan
 *
 *         By implementing these methods in the LocalDateFormatter class and
 *         registering it as a Spring bean with the @Component annotation, you
 *         enable Spring to automatically use this formatter for converting
 *         LocalDate objects to and from String representations. This allows
 *         seamless binding of LocalDate properties in your Spring MVC
 *         controllers and forms.
 * 
 */

@Component
public class LocalDateFormatter implements Formatter<LocalDate> {

	/**
	 * used to parse the text using the DateTimeFormatter.ISO_DATE formatter, which
	 * follows the ISO-8601 standard format for dates. The parsed LocalDate object
	 * is then returned.
	 */

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
	}

	/**
	 * used to format the LocalDate object as a String in the ISO-8601 date format.
	 * The formatted String is then returned.
	 */
	@Override
	public String print(LocalDate object, Locale locale) {
		return DateTimeFormatter.ISO_DATE.format(object);
	}
}
