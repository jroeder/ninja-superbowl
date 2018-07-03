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

import dto.BowlModStepDto;

/**
 * Repräsentiert die Daten zur Trocknung des Holzes im Bearbeitungsprozess einer
 * Schale (Bowl).
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 18.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class BowlModStep implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -8109771941769929822L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BMS_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "BMS_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "BMS_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "BMS_CODE", nullable = false)
	private String code;

	@NotNull
	@Column(name = "BMS_NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "BMS_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 *
	 */
	public BowlModStep() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the technical identifier
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param code
	 *            the code
	 * @param name
	 *            the name of the modification step
	 * @param comment
	 *            the comment about the modification step
	 */
	public BowlModStep(Long id, Integer version, Integer index, String code, String name, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.code = code;
		this.name = name;
		this.comment = comment;
	}

	/**
	 * Constructor using data transfer object.
	 *
	 * @param bowlModStepDto
	 *            instance of type {@code BowlModStepDto}
	 */
	public BowlModStep(BowlModStepDto bowlModStepDto) {
		if (bowlModStepDto.getId() != null) {
			this.id = bowlModStepDto.getId();
		}
		this.version = bowlModStepDto.getVersion();
		this.index = bowlModStepDto.getIndex();
		this.code = bowlModStepDto.getCode();
		this.name = bowlModStepDto.getName();
		this.comment = bowlModStepDto.getComment();
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

}
