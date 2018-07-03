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

import dto.RoadmapDto;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Roadmap implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -819873023118020580L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROADMAP_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "ROADMAP_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "ROADMAP_FEATURE", nullable = false)
	private String feature;

	@NotNull
	@Column(name = "ROADMAP_STATUS", nullable = false)
	private String status;

	@NotNull
	@Column(name = "ROADMAP_COMMENT", nullable = false)
	private String comment;

	@NotNull
	@Column(name = "ROADMAP_VERSIONNUMBER", nullable = false)
	private String versionnumber;

	/**
	 * Constructor.
	 */
	public Roadmap() {
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
	 *            the status
	 * @param comment
	 *            the comment
	 * @param versionnumber
	 *            the version number
	 */
	public Roadmap(Long id, Integer version, String feature, String status, String comment, String versionnumber) {
		super();
		this.id = id;
		this.version = version;
		this.feature = feature;
		this.status = status;
		this.comment = comment;
		this.versionnumber = versionnumber;
	}

	/**
	 * Constructor using data transfer object.
	 *
	 * @param roadmapDto
	 *            the {@code RoadmapDto} instance
	 */
	public Roadmap(RoadmapDto roadmapDto) {
		super();
		this.id = roadmapDto.getId();
		this.version = roadmapDto.getVersion();
		this.feature = roadmapDto.getFeature();
		this.status = roadmapDto.getStatus();
		this.comment = roadmapDto.getComment();
		this.versionnumber = roadmapDto.getVersionnumber();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param version the version to set
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
	 * @param feature the feature to set
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
	 * @param status the status to set
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
	 * @param comment the comment to set
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
	 * @param versionnumber the versionnumber to set
	 */
	public void setVersionnumber(String versionnumber) {
		this.versionnumber = versionnumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Roadmap [id=" + id + ", version=" + version + ", feature=" + feature + ", status=" + status
				+ ", comment=" + comment + ", versionnumber=" + versionnumber + "]";
	}

}
