/**
 * Copyright (C) 2017 Microbeans Software Jürgen Röder.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package types;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * Helper utility class for checking and manipulating values.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
public class SuperbowlHelper {

	/**
	 * The {@code Calendar} instance
	 */
	private static final Calendar cal = Calendar.getInstance();

	/**
	 * The space value <em>('')</em>
	 */
	private static final String SPACE = "";

	/**
	 * The default marker <em>('~')</em>
	 */
	private static final String MARKER = "~";

	/**
	 * The default image name prefix <em>('tp_bowl_')</em>
	 */
	private static final String IMAGENAME_PREFIX_TP = "tp_bowl_";

	/**
	 * The default image name suffix <em>('.jpg')</em>
	 */
	private static final String IMAGENAME_SUFFIX_JPG = ".jpg";

	/**
	 * Constructor.
	 */
	public SuperbowlHelper() {
		super();
	}

	/**
	 * Main execution routine.
	 * 
	 * @param args
	 *            the argument list
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String str = "HG Platzenberg neben grünem Haus ex. Fr. Reiner; 100
		// Jahre alter Baum; gefällt 09.16";
		SuperbowlHelper helper = new SuperbowlHelper();

		// String str = "2015-03-31";
		// Date date = convertToDate(str);
		// System.out.println(date.getTime());
		// System.out.println(date.toLocalDate());

		// String mstr = helper.replaceSpaceByMarker(str);
		// System.out.println(mstr);
		// String ostr = helper.replaceMarkerBySpace(mstr);
		// System.out.println(ostr);
		// String telco = "+49 69 576808";
		// String mtelco = helper.replaceSpaceByMarker(telco);
		// System.out.println(mtelco);
		// String otelco = helper.replaceMarkerBySpace(mtelco);
		// System.out.println(otelco);

		// String code1 = "AM(N)";
		// String code2 = "AM(N)";
		// System.out.println(code1.equals(code2));
		// System.out.println(code1 == code2);

		// String imageName1 = "300.jpg";
		// String imageName2 = "thomas_pildner_300.jpg";
		// System.out.println(helper.checkImageName(imageName1));
		// System.out.println(helper.checkImageName(imageName2));
	}

	/**
	 * Inspects a index of type {@code Integer} whether it is {@code null}.
	 * 
	 * @param index
	 *            the value of type {@code Integer} to be inspected
	 * @return the inspected {@code String}
	 */
	public static final String renderIndex(Integer index) {
		return (index == null ? SPACE : index.toString());
	}

	/**
	 * Inspects a {@code String} and replaces all white space characters by a
	 * hyphen.
	 * 
	 * @param str
	 *            the {@code String} to be inspected
	 * @return the inspected {@code String} with replaced white spaces
	 */
	public static final String replaceWhiteSpace(String str) {
		return str.replaceAll(" ", "-");
	}

	/**
	 * Removes all whitespace characters in a {@code String} and marks all
	 * removed whitespace occurrences by a marker character.
	 * <p>
	 * E.g.:<br>
	 * {@code st.replaceAll("\\s+", "")} <br>
	 * removes all whitespaces and non-visible characters (e.g., tab, \n).
	 * <p>
	 * \w = Anything that is a word character
	 * <p>
	 * \W = Anything that isn't a word character (including punctuation etc)
	 * <p>
	 * \s = Anything that is a space character (including space, tab characters
	 * etc)
	 * <p>
	 * \S = Anything that isn't a space character (including both letters and
	 * numbers, as well as punctuation etc)
	 * 
	 * @param str
	 *            the {@code String} to be inspected
	 * @return the modified {@code String}
	 */
	public static final String replaceSpaceByMarker(@NotNull String str) {
		String result = null;
		if (str != null) {
			result = str.trim().replaceAll("\\s+", MARKER.toString());
		}
		return result;
	}

	/**
	 * Removes all whitespace characters in a {@code String} and marks all
	 * removed whitespace occurrences by a marker character.
	 * <p>
	 * E.g.:<br>
	 * {@code st.replaceAll("\\s+", "")} <br>
	 * removes all whitespaces and non-visible characters (e.g., tab, \n).
	 * 
	 * @param str
	 *            the {@code String} to be inspected
	 * @return the modified {@code String}
	 */
	public static final String replaceMarkerBySpace(@NotNull String str) {
		String result = null;
		if (str != null) {
			result = str.trim().replaceAll(MARKER.toString(), Whitespace.SPACE.getValue());
		}
		return result;
	}

	/**
	 * Checks an image name whether it contains the default prefix and/or suffix
	 * and modifies the image name accordingly.
	 * 
	 * @param imageName
	 *            the image name
	 * @return the imageName the modified image name
	 */
	public static final String checkImageName(String imageName) {
		StringBuilder result = new StringBuilder();
		if (imageName.contains(IMAGENAME_PREFIX_TP) && imageName.contains(IMAGENAME_SUFFIX_JPG)) {
			result.append(imageName);
		} else {
			if (imageName.contains(IMAGENAME_PREFIX_TP) && !imageName.contains(IMAGENAME_SUFFIX_JPG)) {
				result.append(imageName).append(IMAGENAME_SUFFIX_JPG);
			} else {
				if (!imageName.contains(IMAGENAME_PREFIX_TP) && imageName.contains(IMAGENAME_SUFFIX_JPG)) {
					result.append(IMAGENAME_PREFIX_TP).append(imageName);
				} else {
					result.append(IMAGENAME_PREFIX_TP).append(imageName).append(IMAGENAME_SUFFIX_JPG);
				}
			}
		}
		return result.toString();
	}

	/**
	 * Converts a {@code String} of format YYYY-MM-DD into a a {@code String} of
	 * format YYYY-MM-DD.
	 * 
	 * @param date
	 *            the date of format YYYY-MM-DD
	 * @return the date {@code String} of format YYYY-MM-DD
	 */
	public static final String convertDate(String date) {
		if (date == null || date.isEmpty()) {
			return "2017-01-01";
		} else {
			String[] parts = date.split("-");
			return parts[0] + "-" + parts[1] + "-" + parts[2];
		}
	}

	/**
	 * Converts a {@code String} of format YYYY-MM-DD into a
	 * {@code java.sql.Date} instance.
	 * 
	 * @param date
	 *            the date of format YYYY-MM-DD
	 * @return the {@code java.sql.Date} instance
	 */
	public static final java.sql.Date convertToDate(String date) {
		if (date == null) {
			return java.sql.Date.valueOf("2017-01-01");
		} else {
			return java.sql.Date.valueOf(date);
		}
	}

	/**
	 * Delivers the actual date.
	 * 
	 * @return the {@code java.util.Date} instance
	 */
	public static final Date getActualDate() {
		return cal.getTime();
	}

}
