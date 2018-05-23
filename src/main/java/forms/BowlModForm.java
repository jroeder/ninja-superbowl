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

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import dto.BowlModDto;

/**
 * Class to hold input form data of a {@code BowlMod}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public class BowlModForm implements Form {

	/**
	 * the unique technical identifier of a {@code BowlMod}
	 */
	private String id;

	/**
	 * the version number of a {@code BowlMod}
	 */
	private String version;

	/**
	 * the unique technical identifier of a {@code Bowl}
	 */
//	@NotBlank(message = "{bowl.mod.bowlid.blank}")
//	@NotNull(message = "{bowl.mod.bowlid.null}")
	private String bowlId;

	/**
	 * the unique technical identifier of a {@code BowlModStep}
	 */
//	@NotBlank(message = "{bowl.mod.bowlmodstepid.blank}")
//	@NotNull(message = "{bowl.mod.bowlmodstepid.null}")
	private String bowlModStepId;

	/**
	 * the modification date of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.date.blank}")
	// @NotNull(message = "{bowl.mod.date.null}")
	private String date;

	/**
	 * the diameter of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.diameter.blank}")
	// @NotNull(message = "{bowl.mod.diameter.null}")
	private String diameter;

	/**
	 * the height of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.height.blank}")
	// @NotNull(message = "{bowl.mod.height.null}")
	private String height;

	/**
	 * the wallthicknessMin of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.wallthickness.min.blank}")
	// @NotNull(message = "{bowl.mod.wallthickness.min.null}")
	private String wallthicknessMin;

	/**
	 * the wallthicknessMax of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.wallthickness.max.blank}")
	// @NotNull(message = "{bowl.mod.wallthickness.max.null}")
	private String wallthicknessMax;

	/**
	 * the granulation of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.granulation.blank}")
	// @NotNull(message = "{bowl.mod.granulation.null}")
	private String granulation;

	/**
	 * the tap of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.tap.blank}")
	// @NotNull(message = "{bowl.mod.tap.null}")
	private String tap;

	/**
	 * the recess of a {@code Bowl}
	 */
	@NotBlank(message = "{bowl.mod.recess.blank}")
	// @NotNull(message = "{bowl.mod.recess.null}")
	private String recess;

	/**
	 * the surface of a {@code Bowl}
	 */
	 @NotBlank(message = "{bowl.mod.surface.blank}")
	// @NotNull(message = "{bowl.mod.surface.null}")
	private String surface;

	/**
	 * the comment of a {@code Bowl}
	 */
	// @NotBlank(message = "{bowl.mod.comment.blank}")
	// @NotNull(message = "{bowl.mod.comment.null}")
	private String comment;

	/**
	 * the {@code BowlModStep} index
	 */
	private String bowlModStepIndex;

	/**
	 * the {@code BowlModStep} code
	 */
	private String bowlModStepCode;

	/**
	 * the {@code BowlModStep} name
	 */
	private String bowlModStepName;

	/**
	 * Constructor.
	 *
	 */
	public BowlModForm() {
		super();
	}

	/**
	 * Constructor using {@code BowlModDto}.
	 * 
	 * @param bowlModDto
	 *            the {@code BowlModDto} instance
	 */
	public BowlModForm(BowlModDto bowlModDto) {
		super();
		this.id = bowlModDto.getId().toString();
		this.version = bowlModDto.getVersion().toString();
		this.date = bowlModDto.getDate().toString();
		this.diameter = bowlModDto.getDiameter().toString();
		this.wallthicknessMin = bowlModDto.getWallthicknessMin().toString();
		this.wallthicknessMax = bowlModDto.getWallthicknessMax().toString();
		this.height = bowlModDto.getHeight().toString();
		this.granulation = bowlModDto.getGranulation().toString();
		this.surface = bowlModDto.getSurface();
		this.tap = bowlModDto.getTap().toString();
		this.recess = bowlModDto.getRecess().toString();
		this.comment = bowlModDto.getComment();
		this.bowlId = bowlModDto.getBowl().getId().toString();
		this.bowlModStepId = bowlModDto.getBowlModStep().getId().toString();
		this.bowlModStepIndex = bowlModDto.getBowlModStep().getIndex().toString();
		this.bowlModStepCode = bowlModDto.getBowlModStep().getCode();
		this.bowlModStepName = bowlModDto.getBowlModStep().getName();
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
	 * @return the bowlModStepId
	 */
	public String getBowlModStepId() {
		return bowlModStepId;
	}

	/**
	 * @param bowlModStepId
	 *            the bowlModStepId to set
	 */
	public void setBowlModStepId(String bowlModStepId) {
		this.bowlModStepId = bowlModStepId;
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
	 * @return the diameter
	 */
	public String getDiameter() {
		return diameter;
	}

	/**
	 * @param diameter
	 *            the diameter to set
	 */
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * @return the wallthicknessMin
	 */
	public String getWallthicknessMin() {
		return wallthicknessMin;
	}

	/**
	 * @param wallthicknessMin
	 *            the wallthicknessMin to set
	 */
	public void setWallthicknessMin(String wallthicknessMin) {
		this.wallthicknessMin = wallthicknessMin;
	}

	/**
	 * @return the wallthicknessMax
	 */
	public String getWallthicknessMax() {
		return wallthicknessMax;
	}

	/**
	 * @param wallthicknessMax
	 *            the wallthicknessMax to set
	 */
	public void setWallthicknessMax(String wallthicknessMax) {
		this.wallthicknessMax = wallthicknessMax;
	}

	/**
	 * @return the granulation
	 */
	public String getGranulation() {
		return granulation;
	}

	/**
	 * @param granulation
	 *            the granulation to set
	 */
	public void setGranulation(String granulation) {
		this.granulation = granulation;
	}

	/**
	 * @return the tap
	 */
	public String getTap() {
		return tap;
	}

	/**
	 * @param tap
	 *            the tap to set
	 */
	public void setTap(String tap) {
		this.tap = tap;
	}

	/**
	 * @return the recess
	 */
	public String getRecess() {
		return recess;
	}

	/**
	 * @param recess
	 *            the recess to set
	 */
	public void setRecess(String recess) {
		this.recess = recess;
	}

	/**
	 * @return the surface
	 */
	public String getSurface() {
		return surface;
	}

	/**
	 * @param surface
	 *            the surface to set
	 */
	public void setSurface(String surface) {
		this.surface = surface;
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
	 * @return the bowlModStepIndex
	 */
	public String getBowlModStepIndex() {
		return bowlModStepIndex;
	}

	/**
	 * @param bowlModStepIndex
	 *            the bowlModStepIndex to set
	 */
	public void setBowlModStepIndex(String bowlModStepIndex) {
		this.bowlModStepIndex = bowlModStepIndex;
	}

	/**
	 * @return the bowlModStepCode
	 */
	public String getBowlModStepCode() {
		return bowlModStepCode;
	}

	/**
	 * @param bowlModStepCode
	 *            the bowlModStepCode to set
	 */
	public void setBowlModStepCode(String bowlModStepCode) {
		this.bowlModStepCode = bowlModStepCode;
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
