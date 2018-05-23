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
package forms;

import org.hibernate.validator.constraints.NotBlank;

import dto.BowlModItemDto;

/**
 * Class to hold input form data of a {@code BowlModItem}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public class BowlModItemForm implements Form {

	/**
	 * the unique technical identifier of a {@code BowlModItem}
	 */
	private String id;

	/**
	 * the version number of a {@code BowlModItem}
	 */
	private String version;

	/**
	 * the textual description of a {@code BowlModItem}
	 * <p>
	 * input field
	 */
	// @NotBlank(message = "{bowl.mod.item.text.blank}")
	// @NotNull(message = "{bowl.mod.item.text.null}")
	private String text;

	/**
	 * the modification date of a {@code BowlModItem}
	 * <p>
	 * input field
	 */
	@NotBlank(message = "{bowl.mod.item.date.blank}")
	// @NotNull(message = "{bowl.mod.item.date.null}")
	private String date;

	/**
	 * the weight of a {@code BowlModItem}
	 * <p>
	 * input field
	 */
	@NotBlank(message = "{bowl.mod.item.weight.blank}")
	// @NotNull(message = "{bowl.mod.item.weight.null}")
	private String weight;

	/**
	 * the moisture of a {@code BowlModItem}
	 * <p>
	 * input field
	 */
	@NotBlank(message = "{bowl.mod.item.moisture.blank}")
	// @NotNull(message = "{bowl.mod.item.moisture.null}")
	private String moisture;

	/**
	 * the unique technical identifier of a {@code BowlMod}
	 * <p>
	 * readonly field
	 */
	// @NotBlank(message = "{bowl.mod.item.bowlmod.blank}")
	// @NotNull(message = "{bowl.mod.item.bowlmod.null}")
	private String bowlModId;

	/**
	 * the {@code Bowl technical identifier}
	 * <p>
	 * readonly field
	 */
	// @NotBlank(message = "{bowl.mod.item.bow.blank}")
	// @NotNull(message = "{bowl.mod.item.bowl.null}")
	private String bowlId;

	/**
	 * the {@code Timber} name of a {@code Bowl}
	 * <p>
	 * readonly field
	 */
	private String bowlTimberName;

	/**
	 * the {@code BowlModStep} name
	 * <p>
	 * readonly field
	 */
	private String bowlModStepName;

	/**
	 * Constructor.
	 */
	public BowlModItemForm() {
		super();
	}

	/**
	 * Constructor using {@code BowlModDto}.
	 * 
	 * @param bowlModItemDto
	 *            the {@code BowlModItemDto} instance
	 */
	public BowlModItemForm(BowlModItemDto bowlModItemDto) {
		super();
		this.id = bowlModItemDto.getId().toString();
		this.version = bowlModItemDto.getVersion().toString();
		this.bowlModId = bowlModItemDto.getBowlMod().getId().toString();
		this.text = bowlModItemDto.getText();
		this.date = bowlModItemDto.getDate().toString();
		this.weight = bowlModItemDto.getWeight().toString();
		this.moisture = bowlModItemDto.getMoisture().toString();
		this.bowlId = bowlModItemDto.getBowlMod().getBowl().getId().toString();
		this.bowlTimberName = bowlModItemDto.getBowlMod().getBowl().getTimber().getName();
		this.bowlModStepName = bowlModItemDto.getBowlMod().getBowlModStep().getName();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the bowlModId
	 */
	public String getBowlModId() {
		return bowlModId;
	}

	/**
	 * @param bowlModId
	 *            the bowlModId to set
	 */
	public void setBowlModId(String bowlModId) {
		this.bowlModId = bowlModId;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the moisture
	 */
	public String getMoisture() {
		return moisture;
	}

	/**
	 * @param moisture
	 *            the moisture to set
	 */
	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	/**
	 * @return the bowlId
	 */
	public String getBowlId() {
		return bowlId;
	}

	/**
	 * @param bowlId
	 *            the bowlId to set
	 */
	public void setBowlId(String bowlId) {
		this.bowlId = bowlId;
	}

	/**
	 * @return the bowlTimberName
	 */
	public String getBowlTimberName() {
		return bowlTimberName;
	}

	/**
	 * @param bowlTimberName
	 *            the bowlTimberName to set
	 */
	public void setBowlTimberName(String bowlTimberName) {
		this.bowlTimberName = bowlTimberName;
	}

	/**
	 * @return the bowlModStepName
	 */
	public String getBowlModStepName() {
		return bowlModStepName;
	}

	/**
	 * @param bowlModStepName
	 *            the bowlModStepName to set
	 */
	public void setBowlModStepName(String bowlModStepName) {
		this.bowlModStepName = bowlModStepName;
	}

}
