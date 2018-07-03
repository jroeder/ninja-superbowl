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

import dto.SoftwareDto;

/**
 * Repräsentiert die zur Programmierung der Superbowl-Applikation benötigten
 * Softwarekomponenten.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Software implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 6296726331708219748L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOFTWARE_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "SOFTWARE_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "SOFTWARE_VENDOR", nullable = false)
	private String vendor;

	@NotNull
	@Column(name = "SOFTWARE_NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "SOFTWARE_URL", nullable = false)
	private String url;

	@NotNull
	@Column(name = "SOFTWARE_VERSIONNUMBER", nullable = false)
	private String versionnumber;

	@NotNull
	@Column(name = "SOFTWARE_TYPE", nullable = false)
	private String type;

	@NotNull
	@Column(name = "SOFTWARE_DESCRIPTION", nullable = false)
	private String description;

	/**
	 * Constructor.
	 */
	public Software() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the technical identifier
	 * @param version
	 *            the version
	 * @param vendor
	 *            the vendor name
	 * @param name
	 *            the software product name
	 * @param url
	 *            the vendor url
	 * @param versionnumber
	 *            the version number
	 * @param type
	 *            the software type
	 * @param description
	 *            the description
	 */
	public Software(Long id, Integer version, String vendor, String name, String url, String versionnumber, String type,
			String description) {
		super();
		this.id = id;
		this.version = version;
		this.vendor = vendor;
		this.name = name;
		this.url = url;
		this.versionnumber = versionnumber;
		this.type = type;
		this.description = description;
	}

	/**
	 * Constructor using data transfer object.
	 *
	 * @param softwareDto
	 *            the {@code SoftwareDto} instance
	 */
	public Software(SoftwareDto softwareDto) {
		super();
		this.id = softwareDto.getId();
		this.version = softwareDto.getVersion();
		this.vendor = softwareDto.getVendor();
		this.name = softwareDto.getName();
		this.url = softwareDto.getUrl();
		this.versionnumber = softwareDto.getVersionnumber();
		this.type = softwareDto.getType();
		this.description = softwareDto.getDescription();
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
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the versionnumber
	 */
	public String getVersionnumber() {
		return versionnumber;
	}

	/**
	 * @param versionnumber
	 *            the versionnumber to set
	 */
	public void setVersionnumber(String versionnumber) {
		this.versionnumber = versionnumber;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Software [id=" + id + ", version=" + version + ", vendor=" + vendor + ", name=" + name + ", url=" + url
				+ ", versionnumber=" + versionnumber + ", type=" + type + ", description=" + description + "]";
	}

}
