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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.BowlModDto;

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
public class BowlMod implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -7913084962848847064L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BM_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "BM_VERSION", nullable = false)
	private Integer version;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BM_BOWL_ID", nullable = false, updatable = false)
	private Bowl bowl;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL, optional = false)
	@JoinColumn(name = "BM_BMS_ID", nullable = false, updatable = false)
	private BowlModStep bowlModStep;

	@NotNull
	@Column(name = "BM_DATE", nullable = false)
	private Date date;

	@NotNull
	@Column(name = "BM_DIAMETER", nullable = false)
	private BigDecimal diameter;

	@NotNull
	@Column(name = "BM_HEIGHT", nullable = false)
	private BigDecimal height;

	@NotNull
	@Column(name = "BM_WALLTHICKNESS_MIN", nullable = false)
	private BigDecimal wallthicknessMin;

	@NotNull
	@Column(name = "BM_WALLTHICKNESS_MAX", nullable = false)
	private BigDecimal wallthicknessMax;

	@NotNull
	@Column(name = "BM_GRANULATION", nullable = false)
	private Integer granulation;

	@NotNull
	@Column(name = "BM_TAP", nullable = false)
	private Integer tap;

	@NotNull
	@Column(name = "BM_RECESS", nullable = false)
	private Integer recess;

	@NotNull
	@Column(name = "BM_SURFACE", nullable = false)
	private String surface;

	@NotNull
	@Column(name = "BM_COMMENT", nullable = false)
	private String comment;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "bowlMod")
	private Set<BowlModItem> bowlModItems = new HashSet<BowlModItem>(0);

	/**
	 * Constructor.
	 *
	 */
	public BowlMod() {
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
	 *            the {@code Bowl} instance
	 * @param bowlModStep
	 *            the {@code BowlModStep} instance
	 * @param date
	 *            the modification date of a {@code Bowl}
	 * @param diameter
	 *            the diameter of a {@code Bowl}
	 * @param height
	 *            the height of a {@code Bowl}
	 * @param wallthicknessMin
	 *            the minimum wall thickness of a {@code Bowl}
	 * @param wallthicknessMax
	 *            the maximum wall thickness of a {@code Bowl}
	 * @param granulation
	 *            the granulation of a {@code Bowl}
	 * @param tap
	 *            the tap ofd a {@code Bowl}
	 * @param recess
	 *            the recess of a {@code Bowl}
	 * @param surface
	 *            the surface state of a {@code Bowl}
	 * @param comment
	 *            the comment about the modification of a {@code Bowl}
	 */
	public BowlMod(Long id, Integer version, Bowl bowl, BowlModStep bowlModStep, Date date, BigDecimal diameter,
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
	 * Constructor using data transfer object.
	 *
	 * @param bowlModDto
	 *            instance of type {@code BowlModDto}
	 */
	public BowlMod(BowlModDto bowlModDto) {
		if (bowlModDto.getId() != null) {
			this.id = bowlModDto.getId();
		}
		this.version = bowlModDto.getVersion();
		this.bowl= bowlModDto.getBowl();
		this.bowlModStep = bowlModDto.getBowlModStep();
		this.date = bowlModDto.getDate();
		this.diameter = bowlModDto.getDiameter();
		this.height = bowlModDto.getHeight();
		this.wallthicknessMin = bowlModDto.getWallthicknessMin();
		this.wallthicknessMax = bowlModDto.getWallthicknessMax();
		this.granulation = bowlModDto.getGranulation();
		this.tap = bowlModDto.getTap();
		this.recess = bowlModDto.getRecess();
		this.surface = bowlModDto.getSurface();
		this.comment = bowlModDto.getComment();
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

	/**
	 * @return the list of mapped {@code BowlModItem} instances
	 */
	public Set<BowlModItem> getBowlModItems() {
		return this.bowlModItems;
	}

	/**
	 * @param bowlModItems
	 *            the {@code BowlModItem} instances to set
	 */
	public void setBowlModItems(Set<BowlModItem> bowlModItems) {
		this.bowlModItems = bowlModItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BowlMod [id=" + id + ", version=" + version + ", bowl=" + bowl.getId() + ", bowlModStep=" + bowlModStep.getId()
				+ ", date=" + date + ", diameter=" + diameter + ", height=" + height + ", wallthicknessMin="
				+ wallthicknessMin + ", wallthicknessMax=" + wallthicknessMax + ", granulation=" + granulation
				+ ", tap=" + tap + ", recess=" + recess + ", surface=" + surface + ", comment=" + comment + "]";
	}

}
