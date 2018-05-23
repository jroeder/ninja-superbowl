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

import javax.validation.constraints.Size;

import entity.Manufacture;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class ManufactureDto implements Dto {

	/**
	 * the technical identifier
	 */
	// @Size(min = 5)
	private Long id;

	/**
	 * the version
	 */
	// @Size(min = 5)
	private Integer version;

	/**
	 * the index
	 */
	// @Size(min = 5)
	private Integer index;

	/**
	 * the manufacturing year
	 */
	@Size(min = 4, max = 4)
	private String year;

	/**
	 * Insert Constructor description here...
	 */
	public ManufactureDto() {
		super();
	}

	/**
	 * Insert Constructor description here...
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param year
	 *            the manufacturing year
	 */
	public ManufactureDto(Long id, Integer version, Integer index, String year) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.year = year;
	}

	/**
	 * Receives the data of a {@code Manufacture} entity instance.
	 *
	 * @param manufacture
	 *            the {@code Manufacture} instance
	 */
	public void receive(Manufacture manufacture) {
		if (manufacture == null) {
			init();
		} else {
			this.id = manufacture.getId();
			this.version = manufacture.getVersion();
			this.index = manufacture.getIndex();
			this.year = manufacture.getYear();
		}
	}

	/**
	 * Initiates the properties of {@code ManufactureDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.index = 0;
		this.year = "";
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

}
