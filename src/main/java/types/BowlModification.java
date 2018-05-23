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
package types;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 02.05.2017 mbsusr01
 */
public class BowlModification {

	/**
	 * the technical identifier of a {@code BowlMod}
	 */
	private Long bowlModId;

	/**
	 * the version of the {@code BowlMod} instance
	 */
	private Integer bowlModVersion;

	/**
	 * the technical identifier of the referenced {@code Bowl} instance
	 */
	private Long bowlModBowlId;

	/**
	 * the version of the {@code Bowl} instance
	 */
	private Integer bowlModBowlVersion;

	/**
	 * the technical identifier of the referenced {@code BowlModStep} instance
	 */
	private Long bowlModBowlModStepId;

	/**
	 * the name of the referenced {@code BowlModStep} instance
	 */
	private String bowlModBowlModStepName;

	/**
	 * the date of the {@code BowlMod} instance
	 */
	private Date bowlModDate;

	/**
	 * the diameter of the {@code BowlMod} instance
	 */
	private BigDecimal bowlModDiameter;

	/**
	 * the height of the {@code BowlMod} instance
	 */
	private BigDecimal bowlModHeight;

	/**
	 * the wallthicknessMin of the {@code BowlMod} instance
	 */
	private BigDecimal bowlModWallthicknessMin;

	/**
	 * the wallthicknessMin of the {@code BowlMod} instance
	 */
	private BigDecimal bowlModWallthicknessMax;

	/**
	 * the granulation of the {@code BowlMod} instance
	 */
	private Integer bowlModGranulation;

	/**
	 * the tap of the {@code BowlMod} instance
	 */
	private Integer bowlModTap;

	/**
	 * the recess of the {@code BowlMod} instance
	 */
	private Integer bowlModRecess;

	/**
	 * the surface of the {@code BowlMod} instance
	 */
	private String bowlModSurface;

	/**
	 * the comment of a {@code BowlMod}
	 */
	private String bowlModComment;

	/**
	 * the technical identifier of a {@code BowlModItem}
	 */
	private Long bowlModItemId;

	/**
	 * the version of a {@code BowlModItem}
	 */
	private Integer bowlModItemVersion;

	/**
	 * the technical identifier of the referenced {@code BowlMod} instance
	 */
	private Long bowlModItemBowlModId;

	/**
	 * the modification date of a {@code BowlModItem}
	 */
	private Date bowlModItemDate;

	/**
	 * the weight of a {@code BowlModItem}
	 */
	private BigDecimal bowlModItemWeight;

	/**
	 * the moisture of a {@code BowlModItem}
	 */
	private BigDecimal bowlModItemMoisture;

	/**
	 * the comment of a {@code BowlModItem}
	 */
	private String bowlModItemText;

	/**
	 * Transient field to identify duplicate {@code ModStepItem} entries
	 */
	private Boolean duplicateModStepId;

	/**
	 * Constructor.
	 */
	public BowlModification() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param bowlModId
	 * 			  the BowlMod identifier
	 * @param bowlModVersion
	 * 			  the BowlMod version
	 * @param bowlModBowlId
	 * 			  the BowlMod Bowl identifier
	 * @param bowlModBowlVersion
	 * 			  the BowlMod Bowl version
	 * @param bowlModBowlModStepId
	 * 			  the BowlMod BowlModStep identifier
	 * @param bowlModBowlModStepName
	 * 			  the BowlMod BowlModStep name
	 * @param bowlModDate
	 * 			  the BowlMod date
	 * @param bowlModDiameter
	 *            the BowlMod diameter
	 * @param bowlModHeight
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
	 *            the BowlModItem identifier
	 * @param bowlModItemDate
	 *            the BowlModItem date
	 * @param bowlModItemWeight
	 *            the BowlModItem weight
	 * @param bowlModItemMoisture
	 *            the BowlModItem moisture
	 * @param bowlModItemText
	 *            the BowlModItem text
	 */
	public BowlModification(Long bowlModId, Integer bowlModVersion, Long bowlModBowlId, Integer bowlModBowlVersion,
			Long bowlModBowlModStepId, String bowlModBowlModStepName, Date bowlModDate, BigDecimal bowlModDiameter,
			BigDecimal bowlModHeight, BigDecimal bowlModWallthicknessMin, BigDecimal bowlModWallthicknessMax,
			Integer bowlModGranulation, Integer bowlModTap, Integer bowlModRecess, String bowlModSurface,
			String bowlModComment, Long bowlModItemId, Integer bowlModItemVersion, Long bowlModItemBowlModId,
			Date bowlModItemDate, BigDecimal bowlModItemWeight, BigDecimal bowlModItemMoisture,
			String bowlModItemText) {
		super();
		this.bowlModId = bowlModId;
		this.bowlModVersion = bowlModVersion;
		this.bowlModBowlId = bowlModBowlId;
		this.bowlModBowlVersion = bowlModBowlVersion;
		this.bowlModBowlModStepId = bowlModBowlModStepId;
		this.bowlModBowlModStepName = bowlModBowlModStepName;
		this.bowlModDate = bowlModDate;
		this.bowlModDiameter = bowlModDiameter;
		this.bowlModHeight = bowlModHeight;
		this.bowlModWallthicknessMin = bowlModWallthicknessMin;
		this.bowlModWallthicknessMax = bowlModWallthicknessMax;
		this.bowlModGranulation = bowlModGranulation;
		this.bowlModTap = bowlModTap;
		this.bowlModRecess = bowlModRecess;
		this.bowlModSurface = bowlModSurface;
		this.bowlModComment = bowlModComment;
		this.bowlModItemId = bowlModItemId;
		this.bowlModItemVersion = bowlModItemVersion;
		this.bowlModItemBowlModId = bowlModItemBowlModId;
		this.bowlModItemDate = bowlModItemDate;
		this.bowlModItemWeight = bowlModItemWeight;
		this.bowlModItemMoisture = bowlModItemMoisture;
		this.bowlModItemText = bowlModItemText;
	}

	/**
	 * @return the bowlModId
	 */
	public Long getBowlModId() {
		return bowlModId;
	}

	/**
	 * @param bowlModId
	 *            the bowlModId to set
	 */
	public void setBowlModId(Long bowlModId) {
		this.bowlModId = bowlModId;
	}

	/**
	 * @return the bowlModVersion
	 */
	public Integer getBowlModVersion() {
		return bowlModVersion;
	}

	/**
	 * @param bowlModVersion
	 *            the bowlModVersion to set
	 */
	public void setBowlModVersion(Integer bowlModVersion) {
		this.bowlModVersion = bowlModVersion;
	}

	/**
	 * @return the bowlModBowlId
	 */
	public Long getBowlModBowlId() {
		return bowlModBowlId;
	}

	/**
	 * @param bowlModBowlId
	 *            the bowlModBowlId to set
	 */
	public void setBowlModBowlId(Long bowlModBowlId) {
		this.bowlModBowlId = bowlModBowlId;
	}

	/**
	 * @return the bowlModVersion
	 */
	public Integer getBowlModBowlVersion() {
		return bowlModVersion;
	}

	/**
	 * @param bowlModBowlVersion
	 *            the bowlModBowlVersion to set
	 */
	public void setBowlModBowlVersion(Integer bowlModBowlVersion) {
		this.bowlModBowlVersion = bowlModBowlVersion;
	}

	/**
	 * @return the bowlModBowlModStepId
	 */
	public Long getBowlModBowlModStepId() {
		return bowlModBowlModStepId;
	}

	/**
	 * @param bowlModBowlModStepId
	 *            the bowlModBowlModStepId to set
	 */
	public void setBowlModBowlModStepId(Long bowlModBowlModStepId) {
		this.bowlModBowlModStepId = bowlModBowlModStepId;
	}

	/**
	 * @return the bowlModBowlModStepName
	 */
	public String getBowlModBowlModStepName() {
		return bowlModBowlModStepName;
	}

	/**
	 * @param bowlModBowlModStepName
	 *            the bowlModBowlModStepName to set
	 */
	public void setBowlModBowlModStepName(String bowlModBowlModStepName) {
		this.bowlModBowlModStepName = bowlModBowlModStepName;
	}

	/**
	 * @return the bowlModDate
	 */
	public Date getBowlModDate() {
		return bowlModDate;
	}

	/**
	 * @param bowlModDate
	 *            the bowlModDate to set
	 */
	public void setBowlModDate(Date bowlModDate) {
		this.bowlModDate = bowlModDate;
	}

	/**
	 * @return the bowlModDiameter
	 */
	public BigDecimal getBowlModDiameter() {
		return bowlModDiameter;
	}

	/**
	 * @param bowlModDiameter
	 *            the bowlModDiameter to set
	 */
	public void setBowlModDiameter(BigDecimal bowlModDiameter) {
		this.bowlModDiameter = bowlModDiameter;
	}

	/**
	 * @return the bowlModHeight
	 */
	public BigDecimal getBowlModHeight() {
		return bowlModHeight;
	}

	/**
	 * @param bowlModHeight
	 *            the bowlModHeight to set
	 */
	public void setBowlModHeight(BigDecimal bowlModHeight) {
		this.bowlModHeight = bowlModHeight;
	}

	/**
	 * @return the bowlModWallthicknessMin
	 */
	public BigDecimal getBowlModWallthicknessMin() {
		return bowlModWallthicknessMin;
	}

	/**
	 * @param bowlModWallthicknessMin
	 *            the bowlModWallthicknessMin to set
	 */
	public void setBowlModWallthicknessMin(BigDecimal bowlModWallthicknessMin) {
		this.bowlModWallthicknessMin = bowlModWallthicknessMin;
	}

	/**
	 * @return the bowlModWallthicknessMax
	 */
	public BigDecimal getBowlModWallthicknessMax() {
		return bowlModWallthicknessMax;
	}

	/**
	 * @param bowlModWallthicknessMax
	 *            the bowlModWallthicknessMax to set
	 */
	public void setBowlModWallthicknessMax(BigDecimal bowlModWallthicknessMax) {
		this.bowlModWallthicknessMax = bowlModWallthicknessMax;
	}

	/**
	 * @return the bowlModGranulation
	 */
	public Integer getBowlModGranulation() {
		return bowlModGranulation;
	}

	/**
	 * @param bowlModGranulation
	 *            the bowlModGranulation to set
	 */
	public void setBowlModGranulation(Integer bowlModGranulation) {
		this.bowlModGranulation = bowlModGranulation;
	}

	/**
	 * @return the bowlModTap
	 */
	public Integer getBowlModTap() {
		return bowlModTap;
	}

	/**
	 * @param bowlModTap
	 *            the bowlModTap to set
	 */
	public void setBowlModTap(Integer bowlModTap) {
		this.bowlModTap = bowlModTap;
	}

	/**
	 * @return the bowlModRecess
	 */
	public Integer getBowlModRecess() {
		return bowlModRecess;
	}

	/**
	 * @param bowlModRecess
	 *            the bowlModRecess to set
	 */
	public void setBowlModRecess(Integer bowlModRecess) {
		this.bowlModRecess = bowlModRecess;
	}

	/**
	 * @return the bowlModSurface
	 */
	public String getBowlModSurface() {
		return bowlModSurface;
	}

	/**
	 * @param bowlModSurface
	 *            the bowlModSurface to set
	 */
	public void setBowlModSurface(String bowlModSurface) {
		this.bowlModSurface = bowlModSurface;
	}

	/**
	 * @return the bowlModComment
	 */
	public String getBowlModComment() {
		return bowlModComment;
	}

	/**
	 * @param bowlModComment
	 *            the bowlModComment to set
	 */
	public void setBowlModComment(String bowlModComment) {
		this.bowlModComment = bowlModComment;
	}

	/**
	 * @return the bowlModItemId
	 */
	public Long getBowlModItemId() {
		return bowlModItemId;
	}

	/**
	 * @param bowlModItemId
	 *            the bowlModItemId to set
	 */
	public void setBowlModItemId(Long bowlModItemId) {
		this.bowlModItemId = bowlModItemId;
	}

	/**
	 * @return the bowlModItemVersion
	 */
	public Integer getBowlModItemVersion() {
		return bowlModItemVersion;
	}

	/**
	 * @param bowlModItemVersion
	 *            the bowlModItemVersion to set
	 */
	public void setBowlModItemVersion(Integer bowlModItemVersion) {
		this.bowlModItemVersion = bowlModItemVersion;
	}

	/**
	 * @return the bowlModItemBowlModId
	 */
	public Long getBowlModItemBowlModId() {
		return bowlModItemBowlModId;
	}

	/**
	 * @param bowlModItemBowlModId
	 *            the bowlModItemBowlModId to set
	 */
	public void setBowlModItemBowlModId(Long bowlModItemBowlModId) {
		this.bowlModItemBowlModId = bowlModItemBowlModId;
	}

	/**
	 * @return the bowlModItemDate
	 */
	public Date getBowlModItemDate() {
		return bowlModItemDate;
	}

	/**
	 * @param bowlModItemDate
	 *            the bowlModItemDate to set
	 */
	public void setBowlModItemDate(Date bowlModItemDate) {
		this.bowlModItemDate = bowlModItemDate;
	}

	/**
	 * @return the bowlModItemWeight
	 */
	public BigDecimal getBowlModItemWeight() {
		return bowlModItemWeight;
	}

	/**
	 * @param bowlModItemWeight
	 *            the bowlModItemWeight to set
	 */
	public void setBowlModItemWeight(BigDecimal bowlModItemWeight) {
		this.bowlModItemWeight = bowlModItemWeight;
	}

	/**
	 * @return the bowlModItemMoisture
	 */
	public BigDecimal getBowlModItemMoisture() {
		return bowlModItemMoisture;
	}

	/**
	 * @param bowlModItemMoisture
	 *            the bowlModItemMoisture to set
	 */
	public void setBowlModItemMoisture(BigDecimal bowlModItemMoisture) {
		this.bowlModItemMoisture = bowlModItemMoisture;
	}

	/**
	 * @return the bowlModItemText
	 */
	public String getBowlModItemText() {
		return bowlModItemText;
	}

	/**
	 * @param bowlModItemText
	 *            the bowlModItemText to set
	 */
	public void setBowlModItemText(String bowlModItemText) {
		this.bowlModItemText = bowlModItemText;
	}

	/**
	 * @return the duplicateModStepId
	 */
	public Boolean getDuplicateModStepId() {
		return duplicateModStepId;
	}

	/**
	 * @param duplicateModStepId the duplicateModStepId to set
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
		return "BowlModification [bowlModId=" + bowlModId + ", bowlModVersion=" + bowlModVersion + ", bowlModBowlId="
				+ bowlModBowlId + ", bowlModBowlVersion=" + bowlModBowlVersion + ", bowlModBowlModStepId="
				+ bowlModBowlModStepId + ", bowlModBowlModStepName=" + bowlModBowlModStepName + ", bowlModDate="
				+ bowlModDate + ", bowlModDiameter=" + bowlModDiameter + ", bowlModHeight=" + bowlModHeight
				+ ", bowlModWallthicknessMin=" + bowlModWallthicknessMin + ", bowlModWallthicknessMax="
				+ bowlModWallthicknessMax + ", bowlModGranulation=" + bowlModGranulation + ", bowlModTap=" + bowlModTap
				+ ", bowlModRecess=" + bowlModRecess + ", bowlModSurface=" + bowlModSurface + ", bowlModComment="
				+ bowlModComment + ", bowlModItemId=" + bowlModItemId + ", bowlModItemVersion=" + bowlModItemVersion
				+ ", bowlModItemBowlModId=" + bowlModItemBowlModId + ", bowlModItemDate=" + bowlModItemDate
				+ ", bowlModItemWeight=" + bowlModItemWeight + ", bowlModItemMoisture=" + bowlModItemMoisture
				+ ", bowlModItemText=" + bowlModItemText + ", duplicateModStepId=" + duplicateModStepId + "]";
	}

}
