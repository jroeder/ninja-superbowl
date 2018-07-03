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
import java.sql.Date;

import javax.validation.constraints.Size;

import entity.Bowl;
import entity.BowlMod;
import entity.BowlModStep;

/**
 * The data transfer object of {@code BowlMod}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 18.04.2017 mbsusr01
 */
public class BowlModDto implements Dto {

	/**
	 * the technical identifier of a {@code BowlMod}
	 */
	private Long id;

	/**
	 * the version of a {@code BowlMod}
	 */
	private Integer version;

	/**
	 * the {@code Bowl} instance
	 */
	private Bowl bowl;

	/**
	 * the {@code BowlModStep} instance
	 */
	private BowlModStep bowlModStep;

	/**
	 * the date
	 */
	@Size(min = 10)
	private Date date;

	/**
	 * the diameter
	 */
	private BigDecimal diameter;

	/**
	 * the height
	 */
	private BigDecimal height;

	/**
	 * the minimum wallthickness
	 */
	private BigDecimal wallthicknessMin;

	/**
	 * the maximum wallthickness
	 */
	private BigDecimal wallthicknessMax;

	/**
	 * the granulation
	 */
	private Integer granulation;

	/**
	 * the tap
	 */
	private Integer tap;

	/**
	 * the recess
	 */
	private Integer recess;

	/**
	 * the version
	 */
	@Size(max = 64)
	private String surface;

	/**
	 * the comment
	 */
	@Size(max = 256)
	private String comment;

	/**
	 * Constructor.
	 *
	 */
	public BowlModDto() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the technical identifier
	 * @param version
	 *            the version
	 * @param bowl
	 *            the {@code Bowl} reference instance
	 * @param bowlModStep
	 *            the {@code BowlModStep} reference instance
	 * @param date
	 *            the modification date
	 * @param diameter
	 *            the diameter
	 * @param height
	 *            the height
	 * @param wallthicknessMin
	 *            the minimum wall thickness
	 * @param wallthicknessMax
	 *            the maximum wall thickness
	 * @param granulation
	 *            the granulation
	 * @param tap
	 *            the tap
	 * @param recess
	 *            the recess
	 * @param surface
	 *            the surface state
	 * @param comment
	 *            the comment
	 */
	public BowlModDto(Long id, Integer version, Bowl bowl, BowlModStep bowlModStep, Date date, BigDecimal diameter,
			BigDecimal height, BigDecimal wallthicknessMin, BigDecimal wallthicknessMax, Integer granulation,
			Integer tap, Integer recess, String surface, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.bowl = bowl;
		this.bowlModStep = bowlModStep;
		this.date = date;
		this.diameter = diameter;
		this.height = height;
		this.wallthicknessMin = wallthicknessMin;
		this.wallthicknessMax = wallthicknessMax;
		this.granulation = granulation;
		this.tap = tap;
		this.recess = recess;
		this.surface = surface;
		this.comment = comment;
	}

	/**
	 * Receives the data of a {@code BowlMod} entity instance.
	 *
	 * @param bowlMod
	 *            the {@code BowlMod} instance
	 */
	public void receive(BowlMod bowlMod) {
		this.id = bowlMod.getId();
		this.version = bowlMod.getVersion();
		this.bowl = bowlMod.getBowl();
		this.bowlModStep = bowlMod.getBowlModStep();
		this.date = bowlMod.getDate();
		this.diameter = bowlMod.getDiameter();
		this.height = bowlMod.getHeight();
		this.wallthicknessMin = bowlMod.getWallthicknessMin();
		this.wallthicknessMax = bowlMod.getWallthicknessMax();
		this.granulation = bowlMod.getGranulation();
		this.tap = bowlMod.getTap();
		this.recess = bowlMod.getRecess();
		this.surface = bowlMod.getSurface();
		this.comment = bowlMod.getComment();
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
	 * @return the bowl
	 */
	public Bowl getBowl() {
		return bowl;
	}

	/**
	 * @param bowl
	 *            the bowl to set
	 */
	public void setBowl(Bowl bowl) {
		this.bowl = bowl;
	}

	/**
	 * @return the bowlModStep
	 */
	public BowlModStep getBowlModStep() {
		return bowlModStep;
	}

	/**
	 * @param bowlModStep
	 *            the bowlModStep to set
	 */
	public void setBowlModStep(BowlModStep bowlModStep) {
		this.bowlModStep = bowlModStep;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the diameter
	 */
	public BigDecimal getDiameter() {
		return diameter;
	}

	/**
	 * @param diameter
	 *            the diameter to set
	 */
	public void setDiameter(BigDecimal diameter) {
		this.diameter = diameter;
	}

	/**
	 * @return the height
	 */
	public BigDecimal getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	/**
	 * @return the wallthicknessMin
	 */
	public BigDecimal getWallthicknessMin() {
		return wallthicknessMin;
	}

	/**
	 * @param wallthicknessMin
	 *            the wallthicknessMin to set
	 */
	public void setWallthicknessMin(BigDecimal wallthicknessMin) {
		this.wallthicknessMin = wallthicknessMin;
	}

	/**
	 * @return the wallthicknessMax
	 */
	public BigDecimal getWallthicknessMax() {
		return wallthicknessMax;
	}

	/**
	 * @param wallthicknessMax
	 *            the wallthicknessMax to set
	 */
	public void setWallthicknessMax(BigDecimal wallthicknessMax) {
		this.wallthicknessMax = wallthicknessMax;
	}

	/**
	 * @return the granulation
	 */
	public Integer getGranulation() {
		return granulation;
	}

	/**
	 * @param granulation
	 *            the granulation to set
	 */
	public void setGranulation(Integer granulation) {
		this.granulation = granulation;
	}

	/**
	 * @return the tap
	 */
	public Integer getTap() {
		return tap;
	}

	/**
	 * @param tap
	 *            the tap to set
	 */
	public void setTap(Integer tap) {
		this.tap = tap;
	}

	/**
	 * @return the recess
	 */
	public Integer getRecess() {
		return recess;
	}

	/**
	 * @param recess
	 *            the recess to set
	 */
	public void setRecess(Integer recess) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String asString() {
		return "BowlModDto [id=" + id + ", version=" + version + ", bowl=" + bowl.getId() + ", bowlModStep="
				+ bowlModStep.getId() + ", date=" + date + ", diameter=" + diameter + ", height=" + height
				+ ", wallthicknessMin=" + wallthicknessMin + ", wallthicknessMax=" + wallthicknessMax + ", granulation="
				+ granulation + ", surface=" + surface + ", tap=" + tap + ", recess=" + recess + ", comment=" + comment
				+ "]";
	}

}
