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

import entity.Roadmap;

/**
 * The {@code Roadmap} Data Transfer Object implementation class.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class RoadmapDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the feature
	 */
	@Size(max = 128)
	private String feature;

	/**
	 * the status
	 */
	@Size(max = 35)
	private String status;

	/**
	 * the comment
	 */
	@Size(max = 128)
	private String comment;

	/**
	 * the version number
	 */
	@Size(max = 10)
	private String versionnumber;

	/**
	 * Constructor.
	 */
	public RoadmapDto() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the technical identifier
	 * @param version
	 *            the version
	 * @param feature
	 *            the feature description
	 * @param status
	 *            the implementation status
	 * @param comment
	 *            the comment
	 * @param versionnumber
	 *            the version number of the feature
	 */
	public RoadmapDto(Long id, Integer version, String feature, String status, String comment, String versionnumber) {
		super();
		this.id = id;
		this.version = version;
		this.feature = feature;
		this.status = status;
		this.comment = comment;
		this.versionnumber = versionnumber;
	}

	/**
	 * Receives the data of a {@code Roadmap} entity instance.
	 *
	 * @param roadmap
	 *            the {@code Roadmap} instance
	 */
	public void receive(Roadmap roadmap) {
		this.id = roadmap.getId();
		this.version = roadmap.getVersion();
		this.feature = roadmap.getFeature();
		this.status = roadmap.getStatus();
		this.comment = roadmap.getComment();
		this.versionnumber = roadmap.getVersionnumber();
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
	 * @return the feature
	 */
	public String getFeature() {
		return feature;
	}

	/**
	 * @param feature
	 *            the feature to set
	 */
	public void setFeature(String feature) {
		this.feature = feature;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

}
