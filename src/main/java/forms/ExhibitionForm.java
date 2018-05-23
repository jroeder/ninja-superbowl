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
package forms;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Form data fields related to a {@code Exhibition} instance.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 15.05.2017 mbsusr01
 */
public class ExhibitionForm implements Form {

	/**
	 * the unique technical identifier of a {@code Exhibition}
	 */
	private String id;

	/**
	 * the version number of a {@code Exhibition}
	 */
	private String version;

	/**
	 * the {@code Exhibition} index
	 */
	// @NotBlank(message = "{exhibition.index.blank}")
	// @NotNull(message = "{exhibition.index.null}")
	private String index;

	@NotBlank(message = "{exhibition.year.blank}")
	// @NotNull(message = "{exhibition.year.blank}")
	private String year;

	@NotBlank(message = "{exhibition.name.blank}")
	// @NotNull(message = "{exhibition.name.blank}")
	private String name;

	private String institution;

	@NotBlank(message = "{exhibition.dateFrom.blank}")
	// @NotNull(message = "{exhibition.dateFrom.blank}")
	private String dateFrom;

	@NotBlank(message = "{exhibition.dateTo.blank}")
	// @NotNull(message = "{exhibition.dateTo.blank}")
	private String dateTo;

	private String city;

	private String country;

	private String comment;

	/**
	 * Indicates whether a {@code Exhibition} instance exists already in the
	 * data base table ({@code true}) or not ({@code false})
	 */
	private String emptyTable;

	/**
	 * The {@code Manufacure} identifier
	 */
	private String manufactureId;

	/**
	 * Constructor.
	 */
	public ExhibitionForm() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
	 * @return the institution
	 */
	public String getInstitution() {
		return institution;
	}

	/**
	 * @param institution
	 *            the institution to set
	 */
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	/**
	 * @return the dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom
	 *            the dateFrom to set
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo
	 *            the dateTo to set
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the emptyTable
	 */
	public String getEmptyTable() {
		return emptyTable;
	}

	/**
	 * @param emptyTable
	 *            the emptyTable to set
	 */
	public void setEmptyTable(String emptyTable) {
		this.emptyTable = emptyTable;
	}

	/**
	 * @return the manufactureId
	 */
	public String getManufactureId() {
		return manufactureId;
	}

	/**
	 * @param manufactureId the manufactureId to set
	 */
	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "ExhibitionForm [id=" + id + ", version=" + version + ", index=" + index + ", year=" + year + ", name="
				+ name + ", institution=" + institution + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", city="
				+ city + ", country=" + country + ", comment=" + comment + ", emptyTable=" + emptyTable
				+ ", manufactureId=" + manufactureId + "]";
	}

}
