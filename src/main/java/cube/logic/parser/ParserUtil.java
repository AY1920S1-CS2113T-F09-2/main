package cube.logic.parser;

import cube.logic.parser.exception.ParserErrorMessage;
import cube.logic.parser.exception.ParserException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;

/**
 * The collection of all check methods used in parser.
 */
public class ParserUtil {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+8:00");

	/**
	 * Returns the string of date by parsing a date.
	 * @param date the date to be parsed.
	 * @return the string of date.
	 */
	public static String parseDateToString(Date date) {
		if (date == null) {
			return "";
		}
		DATE_FORMAT.setTimeZone(TIME_ZONE);
		return DATE_FORMAT.format(date);
	}

	/**
	 * Returns a Date object by parsing the date String.
	 * Time zone is set as Singapore time by default.
	 *
	 * @param dateString the String describing the date.
	 * @return the date
	 * @throws ParserException exception occurs when unable to parse.
	 */
	public static Date parseStringToDate(String dateString) throws ParserException {
		if (dateString == null) {
			return null;
		}
		SimpleDateFormat formatter = DATE_FORMAT;
		formatter.setLenient(false);
		formatter.setTimeZone(TIME_ZONE);
		Date date;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			throw new ParserException(ParserErrorMessage.INVALID_DATE_FORMAT);
		}
		return date;
	}

	//@@author ZKathrynx
	/**
	 * Find the full name/type until next parameter/end of input.
	 * @param inputs tokens containing the full string to be found.
	 * @param index starting index.
	 * @return the full name/type until next parameter/end of input.
	 */
	public static String findFullString(String[] inputs, int index) {
		String fullString = "";

		for (int i = index; i < inputs.length; i++) {
			if (inputs[i].matches("-(.*)")) {
				break;
			}

			if (i != index) {
				fullString += " ";
			}
			fullString += inputs[i];
		}

		return fullString.trim();
	}

	/**
	 * Checks that the inputs only contains given parameters.
	 * @param inputs tokens containing the parameters to be checked.
	 * @param params set of possible parameters.
	 * @return true if the input has parameter that is not within possible parameter set.
	 *         false otherwise.
	 */
	public static boolean hasInvalidParameters(String[] inputs, String[] params) {
		boolean flag = false;
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i].matches("-(.*)")) {
				flag = false;
				for (int j = 0; j < params.length; j++) {
					if (inputs[i].equals(params[j])) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks whether the inputs have repetitive parameters.
	 * @param inputs tokens containing the full string to be found.
	 * @return true if the input has parameter that is not within possible parameter set.
	 *         false otherwise.
	 */
	public static boolean hasRepetitiveParameters(String[] inputs) {
		HashSet<String> table = new HashSet<String>();
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i].matches("-(.*)")) {
				if (table.contains(inputs[i])) {
					return true;
				} else {
					table.add(inputs[i]);
				}
			}
		}
		return false;
	}

	/**
	 * Find out whether the field value is empty.
	 * @param inputs tokens containing the full string to be found.
	 * @param index the index after which there should be a field value.
	 * @return true if the field value after index is not empty,
	 *         false otherwise.
	 */
	public static boolean hasField(String[] inputs, int index) {
		if (index >= inputs.length || inputs[index].matches("-(.*)")) {
			return false;
		}
		return true;
	}

	/**
	 * Checks whether the number is a valid numeric.
	 * @param input the number to be checked.
	 * @return true if the input is a valid number.
	 *         false otherwise.
	 */
	public static boolean isValidNumber(String input) {
		double number;
		try {
			number = Double.parseDouble(input);
			if (number < 0 || number >= 10000) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

    /**
     * Checks whether the number is a valid integer.
     * @param input the number to be checked.
     * @return true if the input is a valid integer.
     *         false otherwise.
     */
    public static boolean isValidInteger(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
            if (number < 0 || number >= 10000) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	/**
	 * Retrieve the DATE FORMAT that is used in Cube.
	 * @return Date Formatter specified for Cube.
	 */
	public static SimpleDateFormat getDateFormat() {
		return DATE_FORMAT;
	}

	/**
	 * Retrieve the TIME ZONE that is used in Cube.
	 * @return TimeZone specified for Cube.
	 */
	public static TimeZone getTimeZone() {
		return TIME_ZONE;
	}
}