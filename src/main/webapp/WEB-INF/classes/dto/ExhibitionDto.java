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
package dto;

import java.util.Date;

import javax.validation.constraints.Size;

import entity.Exhibition;
import types.SuperbowlHelper;

/**
 * Data transfer object for an {@code Exhibition}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class ExhibitionDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the index
	 */
	private Integer index;

	/**
	 * the official name of the exhibition
	 */
	@Size(max = 32)
	private String name;

	/**
	 * the institution name which organizes the exhibition
	 */
	@Size(max = 32)
	private String institution;

	/**
	 * the year of the exhibition
	 */
	@Size(min = 4, max = 4)
	private String year;

	/**
	 * the start date of the exhibition
	 */
	private Date dateFrom;

	/**
	 * the end date of the exhibition
	 */
	private Date dateTo;

	/**
	 * the city name where the exhibition occurs
	 */
	@Size(max = 32)
	private String city;

	/**
	 * the country name where the exhibition occurs
	 */
	@Size(max = 64)
	private String country;

	/**
	 * the comment related to the exhibition
	 */
	@Size(max = 64)
	private String comment;

	/**
	 * the empty table indicator
	 */
	private String emptyTable;

	/**
	 * the {@code Manufacture} identifier
	 */
	private String manufactureId;

	/**
	 * Constructor.
	 */
	public ExhibitionDto() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the unique technical identifier
	 * @param version
	 *            the version number
	 * @param index
	 *            the index
	 * @param name
	 *            the official exhibition name
	 * @param institution
	 *            the institution name that organizes the exhibition
	 * @param year
	 *            the year of the exhibition
	 * @param dateFrom
	 *            the start date
	 * @param dateTo
	 *            the end date
	 * @param city
	 *            the city name
	 * @param country
	 *            the country name
	 * @param comment
	 *            the comment about the exhibition
	 */
	public ExhibitionDto(Long id, Integer version, Integer index, String name, String institution, String year,
			Date dateFrom, Date dateTo, String city, String country, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.name = name;
		this.institution = institution;
		this.year = year;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.city = city;
		this.country = country;
		this.comment = comment;
		this.emptyTable = Boolean.FALSE.toString();
		this.manufactureId = "";
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the unique technical identifier
	 * @param version
	 *            the version number
	 * @param index
	 *            the index
	 * @param name
	 *            the official exhibition name
	 * @param institution
	 *            the institution name that organizes the exhibition
	 * @param year
	 *            the year of the exhibition
	 * @param dateFrom
	 *            the start date
	 * @param dateTo
	 *            the end date
	 * @param city
	 *            the city name
	 * @param country
	 *            the country name
	 * @param comment
	 *            the comment about the exhibition
	 * @param emptyTable
	 *            the emptyTable indicator
	 * @param manufactureId
	 *            the {@code Manufacture} identifier
	 */
	public ExhibitionDto(Long id, Integer version, Integer index, String name, String institution, String year,
			Date dateFrom, Date dateTo, String city, String country, String comment, String emptyTable,
			String manufactureId) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.name = name;
		this.institution = institution;
		this.year = year;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.city = city;
		this.country = country;
		this.comment = comment;
		this.emptyTable = emptyTable;
		this.manufactureId = manufactureId;
	}

	/**
	 * Constructor.
	 *
	 * @param exhibition
	 *            the {@code Exhibition} instance
	 */
	public ExhibitionDto(Exhibition exhibition) {
		super();
		this.receive(exhibition);
	}

	/**
	 * Receives the data of a {@code Exhibition} entity instance.
	 *
	 * @param exhibition
	 *            the {@code Exhibition} instance
	 */
	public void receive(Exhibition exhibition) {
		if (exhibition == null) {
			init();
		} else {
			this.id = exhibition.getId();
			this.version = exhibition.getVersion();
			this.index = exhibition.getIndex();
			this.name = exhibition.getName();
			this.institution = exhibition.getInstitution();
			this.year = exhibition.getYear();
			this.dateFrom = exhibition.getDateFrom();
			this.dateTo = exhibition.getDateTo();
			this.city = exhibition.getCity();
			this.country = exhibition.getCountry();
			this.comment = exhibition.getComment();
			this.emptyTable = Boolean.FALSE.toString();
			this.manufactureId = "";
		}
	}

	/**
	 * Initiates the properties of {@code ExhibitionDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.index = 0;
		this.name = "";
		this.institution = "";
		this.year = "";
		this.dateFrom = SuperbowlHelper.getActualDate();
		this.dateTo = SuperbowlHelper.getActualDate();
		this.city = "";
		this.country = "";
		this.comment = "";
		this.emptyTable = Boolean.FALSE.toString();
		this.manufactureId = "";
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
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
	 * @return the dateFrom
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom
	 *            the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public Date getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo
	 *            the dateTo to set
	 */
	public void setDateTo(Date dateTo) {
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
	 * @param emptyTable the emptyTable to set
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "ExhibitionDto [id=" + id + ", version=" + version + ", index=" + index + ", name=" + name
				+ ", institution=" + institution + ", year=" + year + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", city=" + city + ", country=" + country + ", comment=" + comment + ", emptyTable=" + emptyTable
				+ ", " + "manufactureId=" + manufactureId + "]";
	}

}
