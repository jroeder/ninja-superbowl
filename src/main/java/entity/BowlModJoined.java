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
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 27.04.2017 mbsusr01
 */
@Entity
public class BowlModJoined implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -7808484025117225025L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOWLMODJOINED_ID", nullable = false)
	private Long bowlModJoinedId;

	@NotNull
	@Column(name = "BOWLMODJOINED_VERSION", nullable = false)
	private Integer bowlModJoinedVersion;

	/**
	 * the technical identifier of a {@code BowlMod}
	 */
	@Column(name = "BOWLMODJOINED_BOWLMOD_ID", nullable = true)
	private Long bowlModJoinedBowlModId;

	/**
	 * the version of a {@code BowlMod}
	 */
	@Column(name = "BOWLMODJOINED_BOWLMOD_VERSION", nullable = true)
	private Integer bowlModJoinedBowlModVersion;

	/**
	 * the {@code Bowl} instance
	 */
	@Column(name = "BOWLMODJOINED_BOWL_ID", nullable = true)
	private Bowl bowlModJoinedBowl;

	/**
	 * the {@code BowlModStep} instance
	 */
	@Column(name = "BOWLMODJOINED_BOWLMODSTEP_ID", nullable = true)
	private BowlModStep bowlModJoinedBowlModStep;

	/**
	 * the date
	 */
	@Column(name = "BOWLMODJOINED_DATE", nullable = true)
	private Date bowlModJoinedDate;

	/**
	 * the diameter
	 */
	@Column(name = "BOWLMODJOINED_DIAMETER", nullable = true)
	private BigDecimal bowlModJoinedDiameter;

	/**
	 * the height
	 */
	@Column(name = "BOWLMODJOINED_HEIGHT", nullable = true)
	private BigDecimal bowlModJoinedHeight;

	/**
	 * the minimum wallthickness
	 */
	@Column(name = "BOWLMODJOINED_WALLTHICKNESS_MIN", nullable = true)
	private BigDecimal bowlModJoinedWallthicknessMin;

	/**
	 * the maximum wallthickness
	 */
	@Column(name = "BOWLMODJOINED_WALLTHICKNESS_MAX", nullable = true)
	private BigDecimal bowlModJoinedWallthicknessMax;

	/**
	 * the granulation
	 */
	@Column(name = "BOWLMODJOINED_GRANULATION", nullable = true)
	private Integer bowlModJoinedGranulation;

	/**
	 * the tap
	 */
	@Column(name = "BOWLMODJOINED_TAP", nullable = true)
	private Integer bowlModJoinedTap;

	/**
	 * the recess
	 */
	@Column(name = "BOWLMODJOINED_RECESS", nullable = true)
	private Integer bowlModJoinedRecess;

	/**
	 * the version
	 */
	@Column(name = "BOWLMODJOINED_SURFACE", nullable = true)
	private String bowlModJoinedSurface;

	/**
	 * the comment
	 */
	@Column(name = "BOWLMODJOINED_COMMENT", nullable = true)
	private String bowlModJoinedComment;

	/**
	 * the technical identifier
	 */
	@Column(name = "BOWLMODJOINED_ITEM_ID", nullable = true)
	private Long bowlModJoinedItemId;

	/**
	 * the version
	 */
	@Column(name = "BOWLMODJOINED_ITEM_VERSION", nullable = true)
	private Integer bowlModJoinedItemVersion;

	/**
	 * the {@code BowlMod} instance
	 */
	@Column(name = "BOWLMODJOINED_ITEM_BOWLMOD_ID", nullable = true)
	private BowlMod bowlModJoinedItemBowlMod;

	/**
	 * the text
	 */
	@Column(name = "BOWLMODJOINED_ITEM_TEXT", nullable = true)
	private String bowlModJoinedItemText;

	/**
	 * the date
	 */
	@Column(name = "BOWLMODJOINED_ITEM_DATE", nullable = true)
	private Date bowlModJoinedItemDate;

	/**
	 * the diameter
	 */
	@Column(name = "BOWLMODJOINED_ITEM_WEIGHT", nullable = true)
	private BigDecimal bowlModJoinedItemWeight;

	/**
	 * the height
	 */
	@NotNull
	@Column(name = "BOWLMODJOINED_ITEM_MOISTURE", nullable = true)
	private BigDecimal bowlModJoinedItemMoisture;

	/**
	 * Constructor.
	 */
	public BowlModJoined() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param bowlModJoinedId
	 *            the identifier
	 * @param bowlModJoinedVersion
	 *            the version
	 * @param bowlModJoinedBowlModId
	 *            the BowlMod identifier
	 * @param bowlModJoinedBowlModVersion
	 *            the BowlMod version
	 * @param bowlModJoinedBowl
	 *            the Bowl reference
	 * @param bowlModJoinedBowlModStep
	 *            the BowlModStep reference
	 * @param bowlModJoinedDate
	 *            the date
	 * @param bowlModJoinedDiameter
	 *            the diameter
	 * @param bowlModJoinedHeight
	 *            the height
	 * @param bowlModJoinedWallthicknessMin
	 *            the wall thickness min
	 * @param bowlModJoinedWallthicknessMax
	 *            the wall thickness max
	 * @param bowlModJoinedGranulation
	 *            the granulation
	 * @param bowlModJoinedTap
	 *            the tap
	 * @param bowlModJoinedRecess
	 *            the recess
	 * @param bowlModJoinedSurface
	 *            the surface
	 * @param bowlModJoinedComment
	 *            the comment
	 * @param bowlModJoinedItemId
	 *            the BowlModItem identifier
	 * @param bowlModJoinedItemVersion
	 *            the BowlModItem version
	 * @param bowlModJoinedItemBowlMod
	 *            the BowlMod reference
	 * @param bowlModJoinedItemText
	 *            the BowlModItem text
	 * @param bowlModJoinedItemDate
	 *            the BowlModItem date
	 * @param bowlModJoinedItemWeight
	 *            the BowlModItem weight
	 * @param bowlModJoinedItemMoisture
	 *            the BowlModItem moisture
	 */
	public BowlModJoined(Long bowlModJoinedId, Integer bowlModJoinedVersion, Long bowlModJoinedBowlModId,
			Integer bowlModJoinedBowlModVersion, Bowl bowlModJoinedBowl, BowlModStep bowlModJoinedBowlModStep,
			Date bowlModJoinedDate, BigDecimal bowlModJoinedDiameter, BigDecimal bowlModJoinedHeight,
			BigDecimal bowlModJoinedWallthicknessMin, BigDecimal bowlModJoinedWallthicknessMax,
			Integer bowlModJoinedGranulation, Integer bowlModJoinedTap, Integer bowlModJoinedRecess,
			String bowlModJoinedSurface, String bowlModJoinedComment, Long bowlModJoinedItemId,
			Integer bowlModJoinedItemVersion, BowlMod bowlModJoinedItemBowlMod, String bowlModJoinedItemText,
			Date bowlModJoinedItemDate, BigDecimal bowlModJoinedItemWeight, BigDecimal bowlModJoinedItemMoisture) {
		super();
		this.bowlModJoinedId = bowlModJoinedId;
		this.bowlModJoinedVersion = bowlModJoinedVersion;
		this.bowlModJoinedBowlModId = bowlModJoinedBowlModId;
		this.bowlModJoinedBowlModVersion = bowlModJoinedBowlModVersion;
		this.bowlModJoinedBowl = bowlModJoinedBowl;
		this.bowlModJoinedBowlModStep = bowlModJoinedBowlModStep;
		this.bowlModJoinedDate = bowlModJoinedDate;
		this.bowlModJoinedDiameter = bowlModJoinedDiameter;
		this.bowlModJoinedHeight = bowlModJoinedHeight;
		this.bowlModJoinedWallthicknessMin = bowlModJoinedWallthicknessMin;
		this.bowlModJoinedWallthicknessMax = bowlModJoinedWallthicknessMax;
		this.bowlModJoinedGranulation = bowlModJoinedGranulation;
		this.bowlModJoinedTap = bowlModJoinedTap;
		this.bowlModJoinedRecess = bowlModJoinedRecess;
		this.bowlModJoinedSurface = bowlModJoinedSurface;
		this.bowlModJoinedComment = bowlModJoinedComment;
		this.bowlModJoinedItemId = bowlModJoinedItemId;
		this.bowlModJoinedItemVersion = bowlModJoinedItemVersion;
		this.bowlModJoinedItemBowlMod = bowlModJoinedItemBowlMod;
		this.bowlModJoinedItemText = bowlModJoinedItemText;
		this.bowlModJoinedItemDate = bowlModJoinedItemDate;
		this.bowlModJoinedItemWeight = bowlModJoinedItemWeight;
		this.bowlModJoinedItemMoisture = bowlModJoinedItemMoisture;
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
	 * @return the bowlModJoinedBowlModId
	 */
	public Long getBowlModJoinedBowlModId() {
		return bowlModJoinedBowlModId;
	}

	/**
	 * @param bowlModJoinedBowlModId
	 *            the bowlModJoinedBowlModId to set
	 */
	public void setBowlModJoinedBowlModId(Long bowlModJoinedBowlModId) {
		this.bowlModJoinedBowlModId = bowlModJoinedBowlModId;
	}

	/**
	 * @return the bowlModJoinedBowlModVersion
	 */
	public Integer getBowlModJoinedBowlModVersion() {
		return bowlModJoinedBowlModVersion;
	}

	/**
	 * @param bowlModJoinedBowlModVersion
	 *            the bowlModJoinedBowlModVersion to set
	 */
	public void setBowlModJoinedBowlModVersion(Integer bowlModJoinedBowlModVersion) {
		this.bowlModJoinedBowlModVersion = bowlModJoinedBowlModVersion;
	}

	/**
	 * @return the bowlModJoinedBowl
	 */
	public Bowl getBowlModJoinedBowl() {
		return bowlModJoinedBowl;
	}

	/**
	 * @param bowlModJoinedBowl
	 *            the bowlModJoinedBowl to set
	 */
	public void setBowlModJoinedBowl(Bowl bowlModJoinedBowl) {
		this.bowlModJoinedBowl = bowlModJoinedBowl;
	}

	/**
	 * @return the bowlModJoinedBowlModStep
	 */
	public BowlModStep getBowlModJoinedBowlModStep() {
		return bowlModJoinedBowlModStep;
	}

	/**
	 * @param bowlModJoinedBowlModStep
	 *            the bowlModJoinedBowlModStep to set
	 */
	public void setBowlModJoinedBowlModStep(BowlModStep bowlModJoinedBowlModStep) {
		this.bowlModJoinedBowlModStep = bowlModJoinedBowlModStep;
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
	 * @return the bowlModJoinedItemBowlMod
	 */
	public BowlMod getBowlModJoinedItemBowlMod() {
		return bowlModJoinedItemBowlMod;
	}

	/**
	 * @param bowlModJoinedItemBowlMod
	 *            the bowlModJoinedItemBowlMod to set
	 */
	public void setBowlModJoinedItemBowlMod(BowlMod bowlModJoinedItemBowlMod) {
		this.bowlModJoinedItemBowlMod = bowlModJoinedItemBowlMod;
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

}
