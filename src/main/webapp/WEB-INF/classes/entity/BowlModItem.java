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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.BowlModItemDto;

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
public class BowlModItem implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -1508628727619026006L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BMI_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "BMI_VERSION", nullable = false)
	private Integer version;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name = "BMI_BM_ID", nullable = false, updatable = false)
	@Fetch(FetchMode.SELECT)
	private BowlMod bowlMod;

	@NotNull
	@Column(name = "BMI_TEXT", nullable = false)
	private String text;

	@NotNull
	@Column(name = "BMI_DATE", nullable = false, unique = false)
	private Date date;

	@NotNull
	@Column(name = "BMI_WEIGHT", nullable = false)
	private BigDecimal weight;

	@NotNull
	@Column(name = "BMI_MOISTURE", nullable = false)
	private BigDecimal moisture;

	/**
	 * Constructor.
	 *
	 */
	public BowlModItem() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the technical identifier
	 * @param version
	 *            the version
	 * @param bowlMod
	 *            the {@code BowlMod} instance
	 * @param text
	 *            the description text
	 * @param date
	 *            the modification date
	 * @param weight
	 *            the weight of a modified {@code Bowl}
	 * @param moisture
	 *            the moisture of a modified {@code Bowl}
	 */
	public BowlModItem(Long id, Integer version, BowlMod bowlMod, String text, Date date, BigDecimal weight, BigDecimal moisture) {
		super();
		this.id = id;
		this.version = version;
		this.bowlMod = bowlMod;
		this.text = text;
		this.date = date;
		this.weight = weight;
		this.moisture = moisture;
	}

	/**
	 * Insert Constructor description here...
	 *
	 * @param bowlModItemDto
	 *            instance of type {@code BowlModItemDto}
	 */
	public BowlModItem(BowlModItemDto bowlModItemDto) {
		if (bowlModItemDto.getId() != null) {
			this.id = bowlModItemDto.getId();
		}
		this.version = bowlModItemDto.getVersion();
		this.bowlMod = bowlModItemDto.getBowlMod();
		this.text = bowlModItemDto.getText();
		this.date = bowlModItemDto.getDate();
		this.weight = bowlModItemDto.getWeight();
		this.moisture = bowlModItemDto.getMoisture();
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
	 * @return the bowlMod
	 */
	public BowlMod getBowlMod() {
		return bowlMod;
	}

	/**
	 * @param bowlMod
	 *            the bowlMod to set
	 */
	public void setBowlMod(BowlMod bowlMod) {
		this.bowlMod = bowlMod;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
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
	 * @return the weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	/**
	 * @return the moisture
	 */
	public BigDecimal getMoisture() {
		return moisture;
	}

	/**
	 * @param moisture
	 *            the moisture to set
	 */
	public void setMoisture(BigDecimal moisture) {
		this.moisture = moisture;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BowlModItem [id=" + id + ", version=" + version + ", bowlMod=" + bowlMod + ", text=" + text + ", date="
				+ date + ", weight=" + weight + ", moisture=" + moisture + "]";
	}

}
