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

import entity.Software;

/**
 * The {@code Software} Data Transfer Object implementation class.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class SoftwareDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the vendor
	 */
	@Size(max = 64)
	private String vendor;

	/**
	 * the name
	 */
	@Size(max = 64)
	private String name;

	/**
	 * the url
	 */
	@Size(max = 64)
	private String url;

	/**
	 * the version number
	 */
	@Size(max = 64)
	private String versionnumber;

	/**
	 * the type [F-Freeware;O-Open Source;C-Commercial]
	 */
	@Size(min = 1, max = 1)
	private String type;

	/**
	 * the decription
	 */
	@Size(max = 16)
	private String description;

	/**
	 * Constructor.
	 */
	public SoftwareDto() {
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
	public SoftwareDto(Long id, Integer version, String vendor, String name, String url, String versionnumber,
			String type, String description) {
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
	 * Receives the data of a {@code Software} entity instance.
	 *
	 * @param software
	 *            the {@code Software} instance
	 */
	public void receive(Software software) {
		this.id = software.getId();
		this.version = software.getVersion();
		this.vendor = software.getVendor();
		this.name = software.getName();
		this.url = software.getUrl();
		this.versionnumber = software.getVersionnumber();
		this.type = software.getType();
		this.description = software.getDescription();
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

}
