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

import entity.Status;

/**
 * The {@code Status} Data Transfer Object implementation class.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class StatusDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the index
	 */
	private Integer index;

	/**
	 * the code
	 */
	@Size(min = 4, max = 4)
	private String code;

	/**
	 * the text
	 */
	@Size(max = 35)
	private String text;

	/**
	 * the comment
	 */
	@Size(max = 128)
	private String comment;

	/**
	 * Constructor.
	 */
	public StatusDto() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the id
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
	public StatusDto(Long id, Integer version, Integer index, String code, String text, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.code = code;
		this.text = text;
		this.comment = comment;
	}

	/**
	 * Receives the data of a {@code Status} entity instance.
	 *
	 * @param status the {@code Status} instance
	 */
	public void receive(Status status) {
		this.id = status.getId();
		this.version = status.getVersion();
		this.index = status.getIndex();
		this.code = status.getCode();
		this.text = status.getText();
		this.comment = status.getComment();
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "StatusDto [id=" + id + ", version=" + version + ", index=" + index + ", code=" + code + ", text=" + text
				+ ", comment=" + comment + "]";
	}

}
