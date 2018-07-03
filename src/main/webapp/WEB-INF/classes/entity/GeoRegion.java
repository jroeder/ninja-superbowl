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

import dto.GeoRegionDto;

/**
 * Repräsentiert eine Schale (Bowl), manuell gedrechselt aus dem natürlichen
 * Rohstoff Holz.
 *
 * @author mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class GeoRegion implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 5993986416481616271L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GR_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "GR_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "GR_ORDINAL", nullable = false)
	private Integer ordinal;

	@NotNull
	@Column(name = "GR_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "GR_CODE", nullable = false)
	private String code;

	@NotNull
	@Column(name = "GR_NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "GR_REGION", nullable = false)
	private String region;

	/**
	 * Constructor.
	 */
	public GeoRegion() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param version
	 *            the version
	 * @param ordinal
	 *            teh ordinal
	 * @param index
	 *            the index
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param region
	 *            the region
	 */
	public GeoRegion(Integer version, Integer ordinal, Integer index, String code, String name,
			String region) {
		super();
		this.version = version;
		this.ordinal = ordinal;
		this.index = index;
		this.code = code;
		this.name = name;
		this.region = region;
	}

	/**
	 * Constructor using {@code GeoRegionDto}.
	 *
	 * @param geoRegionDto
	 *            the {@code GeoRegionDto} instance
	 */
	public GeoRegion(GeoRegionDto geoRegionDto) {
		this.id = geoRegionDto.getId();
		this.version = geoRegionDto.getVersion();
		this.index = geoRegionDto.getIndex();
		this.ordinal = geoRegionDto.getOrdinal();
		this.code = geoRegionDto.getCode();
		this.ordinal = geoRegionDto.getOrdinal();
		this.name = geoRegionDto.getName();
		this.region = geoRegionDto.getRegion();
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setId(Long id) {
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setVersion(Integer version) {
		this.version = version;
		return this;
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setIndex(Integer index) {
		this.index = index;
		return this;
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setCode(String code) {
		this.code = code;
		return this;
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setName(String name) {
		this.name = name;
		return this;
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
	 * @return the {@code GeoRegion} instance
	 */
	public GeoRegion setRegion(String region) {
		this.region = region;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "GeoRegion [id=" + id + ", version=" + version + ", ordinal=" + ordinal + ", index=" + index
				+ ", code=" + code + ", name=" + name + ", region=" + region + "]";
	}

}
