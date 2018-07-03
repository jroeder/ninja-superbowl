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
package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.ExhibitionDto;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Exhibition implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -3095842355704904203L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EXHIBITION_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "EXHIBITION_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "EXHIBITION_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "EXHIBITION_NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "EXHIBITION_INSTITUTION", nullable = false)
	private String institution;

	@NotNull
	@Column(name = "EXHIBITION_YEAR", nullable = false)
	private String year;

	@NotNull
	@Column(name = "EXHIBITION_DATE_FROM", nullable = false)
	private Date dateFrom;

	@NotNull
	@Column(name = "EXHIBITION_DATE_TO", nullable = false)
	private Date dateTo;

	@NotNull
	@Column(name = "EXHIBITION_CITY", nullable = false)
	private String city;

	@NotNull
	@Column(name = "EXHIBITION_COUNTRY", nullable = false)
	private String country;

	@NotNull
	@Column(name = "EXHIBITION_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 */
	public Exhibition() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param name
	 *            the official name
	 * @param institution
	 *            the institution name
	 * @param year
	 *            the year of the exhibition
	 * @param dateFrom
	 *            the start date
	 * @param dateTo
	 *            the end date
	 * @param city
	 *            the city
	 * @param country
	 *            the country
	 * @param comment
	 *            the comment
	 */
	public Exhibition(Integer version, Integer index, String name, String institution, String year, Date dateFrom,
			Date dateTo, String city, String country, String comment) {
		super();
		this.id = null;
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
	}

	/**
	 * Constructor using {@code ExhibitionDto}.
	 *
	 * @param exhibitionDto
	 *            the {@code ExhibitionDto} instance
	 */
	public Exhibition(ExhibitionDto exhibitionDto) {
		this.id = exhibitionDto.getId();
		this.version = exhibitionDto.getVersion();
		this.index = exhibitionDto.getIndex();
		this.name = exhibitionDto.getName();
		this.institution = exhibitionDto.getInstitution();
		this.year = exhibitionDto.getYear();
		this.dateFrom = exhibitionDto.getDateFrom();
		this.dateTo = exhibitionDto.getDateTo();
		this.city = exhibitionDto.getCity();
		this.country = exhibitionDto.getCountry();
		this.comment = exhibitionDto.getComment();
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

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Exhibition [id=" + id + ", version=" + version + ", index=" + index + ", name=" + name
				+ ", institution=" + institution + ", year=" + year + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", city=" + city + ", country=" + country + ", comment=" + comment + "]";
	}

}
