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

/**
 * Represents a telecommunication number according to german standard DIN 5008.
 * <p>
 * The telecommunication number usually consists of three parts representing the
 * telecommunication number as a whole.
 * </p>
 * <ol>
 * <li>Country Code - the country telecommunication code
 * <li>Area Code - the city telecommunication code
 * <li>Personal Code - the individual member code
 * </ol>
 * 
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class Telco {

	/**
	 * Enumeration for {@code Java} whitespace characters.
	 *
	 * @author mbsusr01
	 */
	private enum TelcoType {

		ASSISTANCE("A"), PHONE("P"), FAX("F"), MOBILE("M"), EMERGENCY("E"), CORPORATE("C");

		/**
		 * The identification value of a teleco type
		 */
		private String value;

		/**
		 * Constructor
		 *
		 * @param value
		 */
		private TelcoType(String value) {
			this.value = value;
		}

		/**
		 * Delivers the identification value of a telco type.
		 * 
		 * @return the identification value
		 */
		public String getValue() {
			return value;
		}

	}

	/**
	 * The hyphen character.
	 */
	private static final String HYPHEN = "-";

	/**
	 * The telecommunication number type.
	 */
	private TelcoType telcoType;

	/**
	 * Länderkennzahl (LKz).
	 */
	private String countryCode;

	/**
	 * Ortsnetzkennzahl (ONKz).
	 */
	private String areaCode;

	/**
	 * Durchwahlrufnummer (Basisnummer).
	 */
	private String dialNumber;

	/**
	 * Nebenstellennummer, wenn der Teilnehmer an einer Telefonanlage
	 * angeschlossen ist.
	 */
	private String extensionNumber;

	/**
	 * Constructor
	 */
	public Telco() {
		super();
	}

	/**
	 * Constructor without extension number property.
	 *
	 * @param telcoType
	 *            the telco type
	 * @param countryCode
	 *            the country code
	 * @param areaCode
	 *            the area code
	 * @param dialNumber
	 *            the dial number
	 */
	public Telco(TelcoType telcoType, String countryCode, String areaCode, String dialNumber) {
		super();
		this.telcoType = telcoType;
		this.countryCode = countryCode.trim();
		this.areaCode = areaCode.trim();
		this.dialNumber = dialNumber.trim();
		this.extensionNumber = null;
	}

	/**
	 * Constructor using all properties.
	 *
	 * @param telcoType
	 *            the telco type
	 * @param countryCode
	 *            the country code
	 * @param areaCode
	 *            the area code
	 * @param dialNumber
	 *            the dial number
	 * @param extensionNumber
	 *            the extension number
	 */
	public Telco(TelcoType telcoType, String countryCode, String areaCode, String dialNumber, String extensionNumber) {
		super();
		this.telcoType = telcoType;
		this.countryCode = countryCode.trim();
		this.areaCode = areaCode.trim();
		this.dialNumber = dialNumber.trim();
		this.extensionNumber = extensionNumber.trim();
	}

	/**
	 * Delivers the telecommunication number as a sequence according to the
	 * <em>Uniform Resource Identifier (URI)</em>.
	 * <p>
	 * E.G.:<br>
	 * Phone number:&nbsp;<strong>+49-30-1234567</strong><br>
	 * Corporate number:&nbsp;<strong>+49-89-1234-100</strong><br>
	 * Emergency number:&nbsp;<strong>110</strong><br>
	 * Assitance number:&nbsp;<strong>111</strong>
	 * 
	 * @return the telco number as a URI string
	 */
	public final String getTelcoNumberAsURI() {
		StringBuilder telco = new StringBuilder();
		switch (telcoType) {
		case ASSISTANCE:
		case EMERGENCY:
			telco.append(dialNumber);
			break;
		case PHONE:
		case FAX:
		case MOBILE:
			if (countryCode != null) {
				telco.append(countryCode).append(HYPHEN);
			}
			if (areaCode != null) {
				telco.append(areaCode).append(HYPHEN);
			}
			if (dialNumber != null) {
				telco.append(dialNumber);
			}
			break;
		case CORPORATE:
			if (countryCode != null) {
				telco.append(countryCode).append(HYPHEN);
			}
			if (areaCode != null) {
				telco.append(areaCode).append(HYPHEN);
			}
			if (dialNumber != null) {
				telco.append(dialNumber).append(HYPHEN);
			}
			if (extensionNumber != null) {
				telco.append(extensionNumber);
			}
			break;
		default:
			if (countryCode != null) {
				telco.append(countryCode).append(HYPHEN);
			}
			if (areaCode != null) {
				telco.append(areaCode).append(HYPHEN);
			}
			if (dialNumber != null) {
				telco.append(dialNumber);
			}
		}
		return telco.toString();
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode
	 *            the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * @return the dialNumber
	 */
	public String getDialNumber() {
		return dialNumber;
	}

	/**
	 * @param dialNumber
	 *            the dialNumber to set
	 */
	public void setDialNumber(String dialNumber) {
		this.dialNumber = dialNumber;
	}

	/**
	 * @return the extensionNumber
	 */
	public String getExtensionNumber() {
		return extensionNumber;
	}

	/**
	 * @param extensionNumber
	 *            the extensionNumber to set
	 */
	public void setExtensionNumber(String extensionNumber) {
		this.extensionNumber = extensionNumber;
	}

}
