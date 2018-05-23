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

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Strings;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 */
public class Checker {

	/**
	 * Demonstrate checking for String that is not null, not empty, and not
	 * white space only using standard Java classes.
	 *
	 * @param string
	 *            String to be checked for not null, not empty, and not white
	 *            space only.
	 * @return {@code true} if provided String is not null, is not empty, and
	 *         has at least one character that is not considered white space.
	 */
	public static boolean isNotNullNotEmptyNotWhiteSpaceOnlyByJava(final String string) {
		return string != null && !string.isEmpty() && !string.trim().isEmpty();
	}

	/**
	 * Demonstrate checking for String that is not null, not empty, and not
	 * white space only using Guava.
	 *
	 * @param string
	 *            String to be checked for not null, not empty, and not white
	 *            space only.
	 * @return {@code true} if provided String is not null, is not empty, and
	 *         has at least one character that is not considered white space.
	 */
	public static boolean isNotNullNotEmptyNotWhiteSpaceOnlyByGuava(final String string) {
		return !Strings.isNullOrEmpty(string) && !string.trim().isEmpty();
	}

	/**
	 * Demonstrate checking for String that is not null, not empty, and not
	 * white space only using Apache Commons Lang classes.
	 *
	 * @param string
	 *            String to be checked for not null, not empty, and not white
	 *            space only.
	 * @return {@code true} if provided String is not null, is not empty, and
	 *         has at least one character that is not considered white space.
	 */
	public static boolean isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(final String string) {
		return StringUtils.isNotBlank(string);
	}

}
