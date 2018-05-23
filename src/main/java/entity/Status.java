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

import dto.StatusDto;

/**
 * Repräsentiert den Status einer Schale (Bowl) im manuellen
 * Herstellungsprozess.
 *
 * @author mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Status implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -2742117048028985053L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATUS_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "STATUS_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "STATUS_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "STATUS_CODE", nullable = false)
	private String code;

	@NotNull
	@Column(name = "STATUS_TEXT", nullable = false)
	private String text;

	@NotNull
	@Column(name = "STATUS_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 */
	public Status() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param code
	 *            the code
	 * @param text
	 *            the text
	 * @param comment
	 *            the comment
	 */
	public Status(Integer version, Integer index, String code, String text, String comment) {
		super();
		this.version = version;
		this.index = index;
		this.code = code;
		this.text = text;
		this.comment = comment;
	}

	/**
	 * Insert Constructor description here...
	 *
	 * @param statusDto
	 *            the {@code StatusDto} instance
	 */
	public Status(StatusDto statusDto) {
		super();
		this.id = statusDto.getId();
		this.version = statusDto.getVersion();
		this.index = statusDto.getIndex();
		this.code = statusDto.getCode();
		this.text = statusDto.getText();
		this.comment = statusDto.getComment();
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
	 * @return the {@code Status} instance
	 */
	public Status setId(Long id) {
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
	 * @return the {@code Status} instance
	 */
	public Status setVersion(Integer version) {
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
	 * @return the {@code Status} instance
	 */
	public Status setIndex(Integer index) {
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
	 * @return the {@code Status} instance
	 */
	public Status setCode(String code) {
		this.code = code;
		return this;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 * @return the {@code Status} instance
	 */
	public Status setText(String text) {
		this.text = text;
		return this;
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
	 * @return the {@code Status} instance
	 */
	public Status setComment(String comment) {
		this.comment = comment;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Status [id=" + id + ", version=" + version + ", index=" + index + ", code=" + code + ", text=" + text
				+ ", comment=" + comment + "]";
	}

}
