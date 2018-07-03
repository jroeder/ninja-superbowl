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

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Size;

import types.BowlModification;

/**
 * The {@code BowlModification} Data Transfer Object implementation class.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 27.04.2017 mbsusr01
 */
public class BowlModificationDto implements Dto {

	/**
	 * the technical identifier of a {@code BowlMod}
	 */
	private Long bowlModJoinedId;

	/**
	 * the version of a {@code BowlMod}
	 */
	private Integer bowlModJoinedVersion;

	/**
	 * the technical identifier of the referenced {@code Bowl} instance
	 */
	private Long bowlModJoinedBowlId;

	/**
	 * the version of a {@code Bowl}
	 */
	private Integer bowlModJoinedBowlVersion;

	/**
	 * the technical identifier of the referenced {@code BowlModStep} instance
	 */
	private Long bowlModJoinedBowlModStepId;

	/**
	 * the name of the referenced {@code BowlModStep} instance
	 */
	private String bowlModJoinedBowlModStepName;

	/**
	 * the date
	 */
	@Size(min = 10)
	private Date bowlModJoinedDate;

	/**
	 * the diameter
	 */
	private BigDecimal bowlModJoinedDiameter;

	/**
	 * the height
	 */
	private BigDecimal bowlModJoinedHeight;

	/**
	 * the minimum wallthickness
	 */
	private BigDecimal bowlModJoinedWallthicknessMin;

	/**
	 * the maximum wallthickness
	 */
	private BigDecimal bowlModJoinedWallthicknessMax;

	/**
	 * the granulation
	 */
	private Integer bowlModJoinedGranulation;

	/**
	 * the tap
	 */
	private Integer bowlModJoinedTap;

	/**
	 * the recess
	 */
	private Integer bowlModJoinedRecess;

	/**
	 * the version
	 */
	@Size(max = 64)
	private String bowlModJoinedSurface;

	/**
	 * the comment
	 */
	@Size(max = 256)
	private String bowlModJoinedComment;

	/**
	 * the technical identifier
	 */
	private Long bowlModJoinedItemId;

	/**
	 * the version
	 */
	private Integer bowlModJoinedItemVersion;

	/**
	 * the {@code BowlMod} instance
	 */
	private Long bowlModJoinedItemBowlModId;

	/**
	 * the date
	 */
	@Size(max = 10)
	private Date bowlModJoinedItemDate;

	/**
	 * the diameter
	 */
	private BigDecimal bowlModJoinedItemWeight;

	/**
	 * the height
	 */
	private BigDecimal bowlModJoinedItemMoisture;

	/**
	 * the text
	 */
	@Size(max = 64)
	private String bowlModJoinedItemText;

	/**
	 * Transient field to identify duplicate {@code ModStepItem} entries
	 */
	private Boolean duplicateModStepId = Boolean.FALSE;

	/**
	 * Constructor.
	 */
	public BowlModificationDto() {
		super();
	}

	/**
	 * 
	 * Constructor.
	 *
	 * @param bowlModId
	 *            the BowlMod identifier
	 * @param bowlModVersion
	 *            the BowlMod identifier
	 * @param bowlModBowlId
	 *            the BowlMod Bowl identifier
	 * @param bowlModBowlVersion
	 *            the BowlMod Bowl version
	 * @param bowlModBowlModStepId
	 *            the BowlMod BowlModStep identifier
	 * @param bowlModBowlModStepName
	 *            the BowlMod BowlModStep name
	 * @param bowlModDate
	 *            the BowlMod date
	 * @param bowlModDiameter
	 *            the BowlMod diamter
	 * @param bowlModJoinedHeight
	 *            the BowlMod height
	 * @param bowlModWallthicknessMin
	 *            the BowlMod wall thickness min
	 * @param bowlModWallthicknessMax
	 *            the BowlMod wall thickness max
	 * @param bowlModGranulation
	 *            the BowlMod granulation
	 * @param bowlModTap
	 *            the BowlMod tap
	 * @param bowlModRecess
	 *            the BowlMod recess
	 * @param bowlModSurface
	 *            the BowlMod surface
	 * @param bowlModComment
	 *            the BowlMod comment
	 * @param bowlModItemId
	 *            the BowlModItem identifier
	 * @param bowlModItemVersion
	 *            the BowlModItem version
	 * @param bowlModItemBowlModId
	 *            the BowlModItem BowlMod identifier
	 * @param bowlModItemDate
	 *            the BowlModItem date
	 * @param bowlModItemWeight
	 *            the BowlModItem weight
	 * @param bowlModItemMoisture
	 *            the BowlModItem moisture
	 * @param bowlModItemText
	 *            the BowlModItem text
	 */
	public BowlModificationDto(Long bowlModId, Integer bowlModVersion, Long bowlModBowlId, Integer bowlModBowlVersion,
			Long bowlModBowlModStepId, String bowlModBowlModStepName, Date bowlModDate, BigDecimal bowlModDiameter,
			BigDecimal bowlModJoinedHeight, BigDecimal bowlModWallthicknessMin, BigDecimal bowlModWallthicknessMax,
			Integer bowlModGranulation, Integer bowlModTap, Integer bowlModRecess, String bowlModSurface,
			String bowlModComment, Long bowlModItemId, Integer bowlModItemVersion, Long bowlModItemBowlModId,
			Date bowlModItemDate, BigDecimal bowlModItemWeight, BigDecimal bowlModItemMoisture,
			String bowlModItemText) {
		super();
		this.bowlModJoinedId = bowlModId;
		this.bowlModJoinedVersion = bowlModVersion;
		this.bowlModJoinedBowlId = bowlModBowlId;
		this.bowlModJoinedBowlVersion = bowlModBowlVersion;
		this.bowlModJoinedBowlModStepId = bowlModBowlModStepId;
		this.bowlModJoinedBowlModStepName = bowlModBowlModStepName;
		this.bowlModJoinedDate = bowlModDate;
		this.bowlModJoinedDiameter = bowlModDiameter;
		this.bowlModJoinedHeight = bowlModJoinedHeight;
		this.bowlModJoinedWallthicknessMin = bowlModWallthicknessMin;
		this.bowlModJoinedWallthicknessMax = bowlModWallthicknessMax;
		this.bowlModJoinedGranulation = bowlModGranulation;
		this.bowlModJoinedTap = bowlModTap;
		this.bowlModJoinedRecess = bowlModRecess;
		this.bowlModJoinedSurface = bowlModSurface;
		this.bowlModJoinedComment = bowlModComment;
		this.bowlModJoinedItemId = bowlModItemId;
		this.bowlModJoinedItemVersion = bowlModItemVersion;
		this.bowlModJoinedItemBowlModId = bowlModItemBowlModId;
		this.bowlModJoinedItemDate = bowlModItemDate;
		this.bowlModJoinedItemWeight = bowlModItemWeight;
		this.bowlModJoinedItemMoisture = bowlModItemMoisture;
		this.bowlModJoinedItemText = bowlModItemText;
	}

	/**
	 * Constructor.
	 *
	 * @param bowlModification
	 *            the {@code BowlModification} instance
	 */
	public BowlModificationDto(BowlModification bowlModification) {
		super();
		this.bowlModJoinedId = bowlModification.getBowlModId();
		this.bowlModJoinedVersion = bowlModification.getBowlModVersion();
		this.bowlModJoinedBowlId = bowlModification.getBowlModBowlId();
		this.bowlModJoinedBowlVersion = bowlModification.getBowlModBowlVersion();
		this.bowlModJoinedBowlModStepId = bowlModification.getBowlModBowlModStepId();
		this.bowlModJoinedBowlModStepName = bowlModification.getBowlModBowlModStepName();
		this.bowlModJoinedDate = bowlModification.getBowlModDate();
		this.bowlModJoinedDiameter = bowlModification.getBowlModDiameter();
		this.bowlModJoinedHeight = bowlModification.getBowlModHeight();
		this.bowlModJoinedWallthicknessMin = bowlModification.getBowlModWallthicknessMin();
		this.bowlModJoinedWallthicknessMax = bowlModification.getBowlModWallthicknessMax();
		this.bowlModJoinedGranulation = bowlModification.getBowlModGranulation();
		this.bowlModJoinedTap = bowlModification.getBowlModTap();
		this.bowlModJoinedRecess = bowlModification.getBowlModRecess();
		this.bowlModJoinedSurface = bowlModification.getBowlModSurface();
		this.bowlModJoinedComment = bowlModification.getBowlModComment();
		this.bowlModJoinedItemId = bowlModification.getBowlModItemId();
		this.bowlModJoinedItemVersion = bowlModification.getBowlModItemVersion();
		this.bowlModJoinedItemBowlModId = bowlModification.getBowlModItemBowlModId();
		this.bowlModJoinedItemDate = bowlModification.getBowlModItemDate();
		this.bowlModJoinedItemWeight = bowlModification.getBowlModItemWeight();
		this.bowlModJoinedItemMoisture = bowlModification.getBowlModItemMoisture();
		this.bowlModJoinedItemText = bowlModification.getBowlModItemText();
		this.duplicateModStepId = bowlModification.getDuplicateModStepId();
	}

	/**
	 * @return the bowlModJoinedId
	 */
	public Long getBowlModJoinedId() {
		return bowlModJoinedId;
	}

	/**
	 * @param bowlModJoinedId
	 *            the bowlModJoinedId to set
	 */
	public void setBowlModJoinedId(Long bowlModJoinedId) {
		this.bowlModJoinedId = bowlModJoinedId;
	}

	/**
	 * @return the bowlModJoinedVersion
	 */
	public Integer getBowlModJoinedVersion() {
		return bowlModJoinedVersion;
	}

	/**
	 * @param bowlModJoinedVersion
	 *            the bowlModJoinedVersion to set
	 */
	public void setBowlModJoinedVersion(Integer bowlModJoinedVersion) {
		this.bowlModJoinedVersion = bowlModJoinedVersion;
	}

	/**
	 * @return the bowlModJoinedBowl
	 */
	public Long getBowlModJoinedBowlId() {
		return bowlModJoinedBowlId;
	}

	/**
	 * @param bowlModJoinedBowlId
	 *            the bowlModJoinedBowlId to set
	 */
	public void setBowlModJoinedBowlId(Long bowlModJoinedBowlId) {
		this.bowlModJoinedBowlId = bowlModJoinedBowlId;
	}

	/**
	 * @return the bowlModJoinedBowlVersion
	 */
	public Integer getBowlModJoinedBowlVersion() {
		return bowlModJoinedBowlVersion;
	}

	/**
	 * @param bowlModJoinedBowlVersion
	 *            the bowlModJoinedBowlVersion to set
	 */
	public void setBowlModJoinedBowlVersion(Integer bowlModJoinedBowlVersion) {
		this.bowlModJoinedBowlVersion = bowlModJoinedBowlVersion;
	}

	/**
	 * @return the bowlModJoinedBowlModStepId
	 */
	public Long getBowlModJoinedBowlModStepId() {
		return bowlModJoinedBowlModStepId;
	}

	/**
	 * @param bowlModJoinedBowlModStepId
	 *            the bowlModJoinedBowlModStepId to set
	 */
	public void setBowlModJoinedBowlModStepId(Long bowlModJoinedBowlModStepId) {
		this.bowlModJoinedBowlModStepId = bowlModJoinedBowlModStepId;
	}

	/**
	 * @return the bowlModJoinedBowlModStepName
	 */
	public String getBowlModJoinedBowlModStepName() {
		return bowlModJoinedBowlModStepName;
	}

	/**
	 * @param bowlModJoinedBowlModStepName
	 *            the bowlModJoinedBowlModStepNameto set
	 */
	public void setBowlModJoinedBowlModStepName(String bowlModJoinedBowlModStepName) {
		this.bowlModJoinedBowlModStepName = bowlModJoinedBowlModStepName;
	}

	/**
	 * @return the bowlModJoinedDate
	 */
	public Date getBowlModJoinedDate() {
		return bowlModJoinedDate;
	}

	/**
	 * @param bowlModJoinedDate
	 *            the bowlModJoinedDate to set
	 */
	public void setBowlModJoinedDate(Date bowlModJoinedDate) {
		this.bowlModJoinedDate = bowlModJoinedDate;
	}

	/**
	 * @return the bowlModJoinedDiameter
	 */
	public BigDecimal getBowlModJoinedDiameter() {
		return bowlModJoinedDiameter;
	}

	/**
	 * @param bowlModJoinedDiameter
	 *            the bowlModJoinedDiameter to set
	 */
	public void setBowlModJoinedDiameter(BigDecimal bowlModJoinedDiameter) {
		this.bowlModJoinedDiameter = bowlModJoinedDiameter;
	}

	/**
	 * @return the bowlModJoinedHeight
	 */
	public BigDecimal getBowlModJoinedHeight() {
		return bowlModJoinedHeight;
	}

	/**
	 * @param bowlModJoinedHeight
	 *            the bowlModJoinedHeight to set
	 */
	public void setBowlModJoinedHeight(BigDecimal bowlModJoinedHeight) {
		this.bowlModJoinedHeight = bowlModJoinedHeight;
	}

	/**
	 * @return the bowlModJoinedWallthicknessMin
	 */
	public BigDecimal getBowlModJoinedWallthicknessMin() {
		return bowlModJoinedWallthicknessMin;
	}

	/**
	 * @param bowlModJoinedWallthicknessMin
	 *            the bowlModJoinedWallthicknessMin to set
	 */
	public void setBowlModJoinedWallthicknessMin(BigDecimal bowlModJoinedWallthicknessMin) {
		this.bowlModJoinedWallthicknessMin = bowlModJoinedWallthicknessMin;
	}

	/**
	 * @return the bowlModJoinedWallthicknessMax
	 */
	public BigDecimal getBowlModJoinedWallthicknessMax() {
		return bowlModJoinedWallthicknessMax;
	}

	/**
	 * @param bowlModJoinedWallthicknessMax
	 *            the bowlModJoinedWallthicknessMax to set
	 */
	public void setBowlModJoinedWallthicknessMax(BigDecimal bowlModJoinedWallthicknessMax) {
		this.bowlModJoinedWallthicknessMax = bowlModJoinedWallthicknessMax;
	}

	/**
	 * @return the bowlModJoinedGranulation
	 */
	public Integer getBowlModJoinedGranulation() {
		return bowlModJoinedGranulation;
	}

	/**
	 * @param bowlModJoinedGranulation
	 *            the bowlModJoinedGranulation to set
	 */
	public void setBowlModJoinedGranulation(Integer bowlModJoinedGranulation) {
		this.bowlModJoinedGranulation = bowlModJoinedGranulation;
	}

	/**
	 * @return the bowlModJoinedTap
	 */
	public Integer getBowlModJoinedTap() {
		return bowlModJoinedTap;
	}

	/**
	 * @param bowlModJoinedTap
	 *            the bowlModJoinedTap to set
	 */
	public void setBowlModJoinedTap(Integer bowlModJoinedTap) {
		this.bowlModJoinedTap = bowlModJoinedTap;
	}

	/**
	 * @return the bowlModJoinedRecess
	 */
	public Integer getBowlModJoinedRecess() {
		return bowlModJoinedRecess;
	}

	/**
	 * @param bowlModJoinedRecess
	 *            the bowlModJoinedRecess to set
	 */
	public void setBowlModJoinedRecess(Integer bowlModJoinedRecess) {
		this.bowlModJoinedRecess = bowlModJoinedRecess;
	}

	/**
	 * @return the bowlModJoinedSurface
	 */
	public String getBowlModJoinedSurface() {
		return bowlModJoinedSurface;
	}

	/**
	 * @param bowlModJoinedSurface
	 *            the bowlModJoinedSurface to set
	 */
	public void setBowlModJoinedSurface(String bowlModJoinedSurface) {
		this.bowlModJoinedSurface = bowlModJoinedSurface;
	}

	/**
	 * @return the bowlModJoinedComment
	 */
	public String getBowlModJoinedComment() {
		return bowlModJoinedComment;
	}

	/**
	 * @param bowlModJoinedComment
	 *            the bowlModJoinedComment to set
	 */
	public void setBowlModJoinedComment(String bowlModJoinedComment) {
		this.bowlModJoinedComment = bowlModJoinedComment;
	}

	/**
	 * @return the bowlModJoinedItemId
	 */
	public Long getBowlModJoinedItemId() {
		return bowlModJoinedItemId;
	}

	/**
	 * @param bowlModJoinedItemId
	 *            the bowlModJoinedItemId to set
	 */
	public void setBowlModJoinedItemId(Long bowlModJoinedItemId) {
		this.bowlModJoinedItemId = bowlModJoinedItemId;
	}

	/**
	 * @return the bowlModJoinedItemVersion
	 */
	public Integer getBowlModJoinedItemVersion() {
		return bowlModJoinedItemVersion;
	}

	/**
	 * @param bowlModJoinedItemVersion
	 *            the bowlModJoinedItemVersion to set
	 */
	public void setBowlModJoinedItemVersion(Integer bowlModJoinedItemVersion) {
		this.bowlModJoinedItemVersion = bowlModJoinedItemVersion;
	}

	/**
	 * @return the bowlModJoinedItemBowlModId
	 */
	public Long getBowlModJoinedItemBowlModId() {
		return bowlModJoinedItemBowlModId;
	}

	/**
	 * @param bowlModJoinedItemBowlModId
	 *            the bowlModJoinedItemBowlModId to set
	 */
	public void setBowlModJoinedItemBowlModId(Long bowlModJoinedItemBowlModId) {
		this.bowlModJoinedItemBowlModId = bowlModJoinedItemBowlModId;
	}

	/**
	 * @return the bowlModJoinedItemText
	 */
	public String getBowlModJoinedItemText() {
		return bowlModJoinedItemText;
	}

	/**
	 * @param bowlModJoinedItemText
	 *            the bowlModJoinedItemText to set
	 */
	public void setBowlModJoinedItemText(String bowlModJoinedItemText) {
		this.bowlModJoinedItemText = bowlModJoinedItemText;
	}

	/**
	 * @return the bowlModJoinedItemDate
	 */
	public Date getBowlModJoinedItemDate() {
		return bowlModJoinedItemDate;
	}

	/**
	 * @param bowlModJoinedItemDate
	 *            the bowlModJoinedItemDate to set
	 */
	public void setBowlModJoinedItemDate(Date bowlModJoinedItemDate) {
		this.bowlModJoinedItemDate = bowlModJoinedItemDate;
	}

	/**
	 * @return the bowlModJoinedItemWeight
	 */
	public BigDecimal getBowlModJoinedItemWeight() {
		return bowlModJoinedItemWeight;
	}

	/**
	 * @param bowlModJoinedItemWeight
	 *            the bowlModJoinedItemWeight to set
	 */
	public void setBowlModJoinedItemWeight(BigDecimal bowlModJoinedItemWeight) {
		this.bowlModJoinedItemWeight = bowlModJoinedItemWeight;
	}

	/**
	 * @return the bowlModJoinedItemMoisture
	 */
	public BigDecimal getBowlModJoinedItemMoisture() {
		return bowlModJoinedItemMoisture;
	}

	/**
	 * @param bowlModJoinedItemMoisture
	 *            the bowlModJoinedItemMoisture to set
	 */
	public void setBowlModJoinedItemMoisture(BigDecimal bowlModJoinedItemMoisture) {
		this.bowlModJoinedItemMoisture = bowlModJoinedItemMoisture;
	}

	/**
	 * @return the duplicateModStepId
	 */
	public Boolean getDuplicateModStepId() {
		return duplicateModStepId;
	}

	/**
	 * @param duplicateModStepId
	 *            the duplicateModStepId to set
	 */
	public void setDuplicateModStepId(Boolean duplicateModStepId) {
		this.duplicateModStepId = duplicateModStepId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BowlModificationDto [bowlModJoinedId=" + bowlModJoinedId + ", bowlModJoinedVersion="
				+ bowlModJoinedVersion + ", bowlModJoinedBowlId=" + bowlModJoinedBowlId + ", bowlModJoinedBowlVersion="
				+ bowlModJoinedBowlVersion + ", bowlModJoinedBowlModStepId=" + bowlModJoinedBowlModStepId
				+ ", bowlModJoinedBowlModStepName=" + bowlModJoinedBowlModStepName + ", bowlModJoinedDate="
				+ bowlModJoinedDate + ", bowlModJoinedDiameter=" + bowlModJoinedDiameter + ", bowlModJoinedHeight="
				+ bowlModJoinedHeight + ", bowlModJoinedWallthicknessMin=" + bowlModJoinedWallthicknessMin
				+ ", bowlModJoinedWallthicknessMax=" + bowlModJoinedWallthicknessMax + ", bowlModJoinedGranulation="
				+ bowlModJoinedGranulation + ", bowlModJoinedTap=" + bowlModJoinedTap + ", bowlModJoinedRecess="
				+ bowlModJoinedRecess + ", bowlModJoinedSurface=" + bowlModJoinedSurface + ", bowlModJoinedComment="
				+ bowlModJoinedComment + ", bowlModJoinedItemId=" + bowlModJoinedItemId + ", bowlModJoinedItemVersion="
				+ bowlModJoinedItemVersion + ", bowlModJoinedItemBowlModId=" + bowlModJoinedItemBowlModId
				+ ", bowlModJoinedItemDate=" + bowlModJoinedItemDate + ", bowlModJoinedItemWeight="
				+ bowlModJoinedItemWeight + ", bowlModJoinedItemMoisture=" + bowlModJoinedItemMoisture
				+ ", bowlModJoinedItemText=" + bowlModJoinedItemText + ", duplicateModStepId=" + duplicateModStepId
				+ "]";
	}

}
