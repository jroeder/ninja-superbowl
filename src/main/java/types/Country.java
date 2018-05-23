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
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class Country {

	/**
	 * English short country name officially used by the ISO 3166 Maintenance
	 * Agency (ISO 3166/MA).
	 */
	private String name;

	/**
	 * The international two letter country code (ISO alpha-2).
	 * <p>
	 * Used in the Internet as the country code Top Level Domain identifiers
	 * (ccTLDs). The code is based on the ISO 3166-1 "Country Codes".
	 */
	private String iso_Alpha_2;

	/**
	 * The international three letter country code (ISO alpha-3).
	 */
	private String iso_Alpha_3;

	/**
	 * ISO 3166-1 numeric-3.
	 * <p>
	 * A set of 3-digit codes, independent of a writing system, developed and
	 * maintained by the United Nations Statistics Division to represent the
	 * names of countries and dependent areas.
	 */
	private String iso_3166_1;

	/**
	 * Geopolitical Entities and Codes (formerly FIPS PUB 10-4).
	 * <p>
	 * Intended for general use throughout the US Government, especially in
	 * activities associated with the mission of the US Department of State and
	 * US defense programs.
	 */
	private String fips_10_4;

	/**
	 * Corresponding country code top-level domain.
	 */
	private String ccTLD;

	/**
	 * Insert Constructor description here...
	 *
	 */
	public Country() {
		super();
	}

	/**
	 * Insert Constructor description here...
	 *
	 * @param name
	 *            the name
	 * @param iso_Alpha_2
	 *            the iso_Alpha_2
	 * @param iso_Alpha_3
	 *            the iso_Alpha_3
	 * @param iso_3166_1
	 *            the iso_3166_1
	 * @param fips_10_4
	 *            the fips_10_4
	 * @param ccTLD
	 *            the ccTLD
	 */
	public Country(String name, String iso_Alpha_2, String iso_Alpha_3, String iso_3166_1, String fips_10_4,
			String ccTLD) {
		super();
		this.name = name;
		this.iso_Alpha_2 = iso_Alpha_2;
		this.iso_Alpha_3 = iso_Alpha_3;
		this.iso_3166_1 = iso_3166_1;
		this.fips_10_4 = fips_10_4;
		this.ccTLD = ccTLD;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iso_Alpha_2
	 */
	public String getIso_Alpha_2() {
		return iso_Alpha_2;
	}

	/**
	 * @param iso_Alpha_2
	 *            the iso_Alpha_2 to set
	 */
	public void setIso_Alpha_2(String iso_Alpha_2) {
		this.iso_Alpha_2 = iso_Alpha_2;
	}

	/**
	 * @return the iso_Alpha_3
	 */
	public String getIso_Alpha_3() {
		return iso_Alpha_3;
	}

	/**
	 * @param iso_Alpha_3
	 *            the iso_Alpha_3 to set
	 */
	public void setIso_Alpha_3(String iso_Alpha_3) {
		this.iso_Alpha_3 = iso_Alpha_3;
	}

	/**
	 * @return the iso_3166_1
	 */
	public String getIso_3166_1() {
		return iso_3166_1;
	}

	/**
	 * @param iso_3166_1
	 *            the iso_3166_1 to set
	 */
	public void setIso_3166_1(String iso_3166_1) {
		this.iso_3166_1 = iso_3166_1;
	}

	/**
	 * @return the fips_10_4
	 */
	public String getFips_10_4() {
		return fips_10_4;
	}

	/**
	 * @param fips_10_4
	 *            the fips_10_4 to set
	 */
	public void setFips_10_4(String fips_10_4) {
		this.fips_10_4 = fips_10_4;
	}

	/**
	 * @return the ccTLD
	 */
	public String getCcTLD() {
		return ccTLD;
	}

	/**
	 * @param ccTLD
	 *            the ccTLD to set
	 */
	public void setCcTLD(String ccTLD) {
		this.ccTLD = ccTLD;
	}

}
