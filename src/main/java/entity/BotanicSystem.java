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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.BotanicSystemDto;

/**
 * Repräsentiert das botanische System der Holzarten.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 18.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class BotanicSystem implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 190010496589665573L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BS_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "BS_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "BS_ORDINAL", nullable = false)
	private Integer ordinal;

	@NotNull
	@Column(name = "BS_ORDER_INDEX", nullable = false)
	private Integer orderIndex;

	@NotNull
	@Column(name = "BS_FAMILY_INDEX", nullable = false)
	private Integer familyIndex;

	@NotNull
	@Column(name = "BS_SUBFAMILY_INDEX", nullable = false)
	private Integer subFamilyIndex;

	@NotNull
	@Column(name = "BS_ORDER", nullable = false)
	private String order;

	@NotNull
	@Column(name = "BS_FAMILY", nullable = false)
	private String family;

	@NotNull
	@Column(name = "BS_SUBFAMILY", nullable = false)
	private String subFamily;

	/**
	 * Constructor.
	 */
	public BotanicSystem() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param version
	 *            the version
	 * @param ordinal
	 *            the ordinal number
	 * @param orderIndex
	 *            the order index
	 * @param familyIndex
	 *            the family index
	 * @param subFamilyIndex
	 *            the sub family index
	 * @param order
	 *            the botanical order
	 * @param family
	 *            the botanical family
	 * @param subFamily
	 *            the botanical sub family
	 */
	public BotanicSystem(Integer version, Integer ordinal, Integer orderIndex, Integer familyIndex,
			Integer subFamilyIndex, String order, String family, String subFamily) {
		super();
		this.version = version;
		this.ordinal = ordinal;
		this.orderIndex = orderIndex;
		this.familyIndex = familyIndex;
		this.subFamilyIndex = subFamilyIndex;
		this.order = order;
		this.family = family;
		this.subFamily = subFamily;
	}

	/**
	 * Constructor using {@code BotanicSystemDto}.
	 *
	 * @param botanicSystemDto
	 *            the {@code BotanicSystemDto} instance
	 */
	public BotanicSystem(BotanicSystemDto botanicSystemDto) {
		this.id = botanicSystemDto.getId();
		this.version = botanicSystemDto.getVersion();
		this.ordinal = botanicSystemDto.getOrdinal();
		this.orderIndex = botanicSystemDto.getOrderIndex();
		this.familyIndex = botanicSystemDto.getFamilyIndex();
		this.subFamilyIndex = botanicSystemDto.getSubFamilyIndex();
		this.order = botanicSystemDto.getOrder();
		this.family = botanicSystemDto.getFamily();
		this.subFamily = botanicSystemDto.getSubFamily();
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
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setId(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param orderIndex
	 *            the order index to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
		return this;
	}

	/**
	 * @return the order index
	 */
	public Integer getOrderIndex() {
		return orderIndex;
	}

	/**
	 * @param familyIndex
	 *            the family index to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setFamilyIndex(Integer familyIndex) {
		this.familyIndex = familyIndex;
		return this;
	}

	/**
	 * @return the family index
	 */
	public Integer getFamilyIndex() {
		return familyIndex;
	}

	/**
	 * @param subFamilyIndex
	 *            the sub family index to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setSubFamilyIndex(Integer subFamilyIndex) {
		this.subFamilyIndex = subFamilyIndex;
		return this;
	}

	/**
	 * @return the sub family index
	 */
	public Integer getSubFamilyIndex() {
		return subFamilyIndex;
	}

	/**
	 * @param version
	 *            the version to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setVersion(Integer version) {
		this.version = version;
		return this;
	}

	/**
	 * @return the ordinal
	 */
	public Integer getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal
	 *            the ordinal to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
		return this;
	}

	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setOrder(String order) {
		this.order = order;
		return this;
	}

	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family
	 *            the family to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setFamily(String family) {
		this.family = family;
		return this;
	}

	/**
	 * @return the subFamily
	 */
	public String getSubFamily() {
		return subFamily;
	}

	/**
	 * @param subFamily
	 *            the subFamily to set
	 * @return the {@code BotanicSystem} instance
	 */
	public BotanicSystem setSubFamily(String subFamily) {
		this.subFamily = subFamily;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BotanicSystem [id=" + id + ", version=" + version + ", ordinal=" + ordinal + ", orderIndex="
				+ orderIndex + ", familyIndex=" + familyIndex + ", subFamilyIndex=" + subFamilyIndex + ", order="
				+ order + ", family=" + family + ", subFamily=" + subFamily + "]";
	}

}
