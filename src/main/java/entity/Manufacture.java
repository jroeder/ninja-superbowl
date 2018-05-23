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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.ManufactureDto;

/**
 * Represents the {@code Manufacture} entity to hold information about the
 * manufacturing process of a {@code Bowl}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Manufacture implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 3419190084034854785L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MANU_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "MANU_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "MANU_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "MANU_YEAR", nullable = false)
	private String year;

	/**
	 * Constructor.
	 */
	public Manufacture() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param year
	 *            the year
	 */
	public Manufacture(Long id, Integer version, Integer index, String year) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.year = year;
	}

	/**
	 * Constructor using data transfer object.
	 *
	 * @param manufactureDto
	 *            the {@code ManufactureDto} instance
	 */
	public Manufacture(ManufactureDto manufactureDto) {
		super();
		this.id = manufactureDto.getId();
		this.version = manufactureDto.getVersion();
		this.index = manufactureDto.getIndex();
		this.year = manufactureDto.getYear();
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
	 * @return the {@code Manufacture} instance
	 */
	public Manufacture setId(Long id) {
		this.id = id;
		return this;
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
	 * @return the {@code Manufacture} instance
	 */
	public Manufacture setVersion(Integer version) {
		this.version = version;
		return this;
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
	 * @return the {@code Manufacture} instance
	 */
	public Manufacture setIndex(Integer index) {
		this.index = index;
		return this;
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
	 * @return the {@code Manufacture} instance
	 */
	public Manufacture setYear(String year) {
		this.year = year;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Manufacture [id=" + id + ", version=" + version + ", index=" + index + ", year=" + year + "]";
	}

}
