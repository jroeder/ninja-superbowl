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

import entity.BowlModStep;

/**
 * Repräsentiert die Schritte zur Modifikation einer Holzschale im Bearbeitungsprozess.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public class BowlModStepDto implements Dto {

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
	@Size(min=2, max = 2)
	private String code;

	/**
	 * the name
	 */
	@Size(max = 35)
	private String name;

	/**
	 * the comment
	 */
	@Size(max = 256)
	private String comment;

	/**
	 * Constructor.
	 *
	 */
	public BowlModStepDto() {
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
	 *            the name
	 * @param comment
	 *            the comment
	 */
	public BowlModStepDto(Long id, Integer version, Integer index, String code, String name, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.code = code;
		this.name = name;
		this.comment = comment;
	}

	/**
	 * Receives the data of a bowl modification step entity instance.
	 *
	 * @param bowlModStep
	 *            the {@code BowlModStep} instance
	 */
	public void receive(BowlModStep bowlModStep) {
		this.id = bowlModStep.getId();
		this.version = bowlModStep.getVersion();
		this.index = bowlModStep.getIndex();
		this.code = bowlModStep.getCode();
		this.name = bowlModStep.getName();
		this.comment = bowlModStep.getComment();
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
