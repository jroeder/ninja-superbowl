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

import entity.BowlMod;
import entity.BowlModItem;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public class BowlModItemDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the {@code BowlMod} instance
	 */
	private BowlMod bowlMod;

	/**
	 * the text
	 */
	@Size(max = 64)
	private String text;

	/**
	 * the date
	 */
	@Size(max = 10)
	private Date date;

	/**
	 * the diameter
	 */
	private BigDecimal weight;

	/**
	 * the height
	 */
	private BigDecimal moisture;

	/**
	 * Constructor.
	 *
	 */
	public BowlModItemDto() {
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
	 *            the text
	 * @param date
	 *            the date
	 * @param weight
	 *            the weight of a {@code Bowl}
	 * @param moisture
	 *            the moisture of a {@code Bowl}
	 */
	public BowlModItemDto(Long id, Integer version, BowlMod bowlMod, String text, Date date, BigDecimal weight,
			BigDecimal moisture) {
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
	 * Receives the data of a bowl modification step entity instance.
	 *
	 * @param bowlModItem
	 *            the {@code BowlModItem} instance
	 */
	public void receive(BowlModItem bowlModItem) {
		this.id = bowlModItem.getId();
		this.version = bowlModItem.getVersion();
		this.bowlMod = bowlModItem.getBowlMod();
		this.text = bowlModItem.getText();
		this.date = bowlModItem.getDate();
		this.weight = bowlModItem.getWeight();
		this.moisture = bowlModItem.getMoisture();
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
	 * @param text
	 *            the text to set
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BowlModItemDto [id=" + id + ", version=" + version + ", bowlMod=" + bowlMod.getId() + ", text=" + text
				+ ", date=" + date + ", weight=" + weight + ", moisture=" + moisture + "]";
	}

}
