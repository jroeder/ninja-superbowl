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

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 */
public class GeoRegionDto {

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
	 * the ordinal number
	 */
	// @Size(min = 5)
	private Integer ordinal;

	/**
	 * the index
	 */
	// @Size(min = 5)
	private Integer index;

	/**
	 * the code
	 */
	// @Size(min = 5)
	private String code;

	/**
	 * the name
	 */
	// @Size(min = 5)
	private String name;

	/**
	 * the region
	 */
	// @Size(min = 5)
	private String region;

	/**
	 * Insert Constructor description here...
	 */
	public GeoRegionDto() {
		super();
	}

	/**
	 * Insert Constructor description here...
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param ordinal
	 *            the ordinal number
	 * @param index
	 *            the index
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param region
	 *            teh region
	 */
	public GeoRegionDto(Long id, Integer version, Integer ordinal, Integer index, String code,
			String name, String region) {
		super();
		this.id = id;
		this.version = version;
		this.ordinal = ordinal;
		this.index = index;
		this.code = code;
		this.name = name;
		this.region = region;
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
	 * @return the ordinal
	 */
	public Integer getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal
	 *            the ordinal to set
	 */
	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "GeoRegionDto [id=" + id + ", version=" + version + ", ordinal=" + ordinal + ", index=" + index
				+ ", code=" + code + ", name=" + name + ", region=" + region + "]";
	}

}
