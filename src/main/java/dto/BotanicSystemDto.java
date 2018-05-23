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

import javax.validation.constraints.Size;

import entity.BotanicSystem;

/**
 * Data transfer object of entity {@code BotanicSystem}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 18.04.2017 mbsusr01
 */
public class BotanicSystemDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the ordinal number
	 */
	private Integer ordinal;

	/**
	 * the order index
	 */
	private Integer orderIndex;

	/**
	 * the family index
	 */
	private Integer familyIndex;

	/**
	 * the sub family index
	 */
	private Integer subFamilyIndex;

	/**
	 * the order number
	 */
	@Size(max = 35)
	private String order;

	/**
	 * the family name
	 */
	@Size(max = 35)
	private String family;

	/**
	 * the sub family name
	 */
	@Size(max = 35)
	private String subFamily;

	/**
	 * the empty table indicator
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public BotanicSystemDto() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
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
	 *            the order number
	 * @param family
	 *            the family name
	 * @param subFamily
	 *            the sub family name
	 */
	public BotanicSystemDto(Long id, Integer version, Integer ordinal, Integer orderIndex, Integer familyIndex,
			Integer subFamilyIndex, String order, String family, String subFamily) {
		super();
		this.id = id;
		this.version = version;
		this.ordinal = ordinal;
		this.orderIndex = orderIndex;
		this.familyIndex = familyIndex;
		this.subFamilyIndex = subFamilyIndex;
		this.order = order;
		this.family = family;
		this.subFamily = subFamily;
		this.emptyTable = Boolean.FALSE.toString();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
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
	 *            the order number
	 * @param family
	 *            the family name
	 * @param subFamily
	 *            the sub family name
	 * @param emptyTable
	 *            the emptyTable indicator
	 */
	public BotanicSystemDto(Long id, Integer version, Integer ordinal, Integer orderIndex, Integer familyIndex,
			Integer subFamilyIndex, String order, String family, String subFamily, String emptyTable) {
		super();
		this.id = id;
		this.version = version;
		this.ordinal = ordinal;
		this.orderIndex = orderIndex;
		this.familyIndex = familyIndex;
		this.subFamilyIndex = subFamilyIndex;
		this.order = order;
		this.family = family;
		this.subFamily = subFamily;
		this.emptyTable = emptyTable;
	}

	/**
	 * Receives the data of a {@code BotanicSystem} entity instance.
	 *
	 * @param botanicSystem
	 *            the {@code BotanicSystem} instance
	 */
	public void receive(BotanicSystem botanicSystem) {
		if (botanicSystem == null) {
			init();
		} else {
			this.id = botanicSystem.getId();
			this.version = botanicSystem.getVersion();
			this.ordinal = botanicSystem.getOrdinal();
			this.orderIndex = botanicSystem.getOrderIndex();
			this.familyIndex = botanicSystem.getFamilyIndex();
			this.subFamilyIndex = botanicSystem.getSubFamilyIndex();
			this.order = botanicSystem.getOrder();
			this.family = botanicSystem.getFamily();
			this.subFamily = botanicSystem.getSubFamily();
			this.emptyTable = Boolean.FALSE.toString();
		}
	}

	/**
	 * Initiates the properties of {@code BotanicSystemDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.ordinal = 0;
		this.orderIndex = 0;
		this.familyIndex = 0;
		this.subFamilyIndex = 0;
		this.order = "";
		this.family = "";
		this.subFamily = "";
		this.emptyTable = Boolean.FALSE.toString();
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
	 * @return the ordinal
	 */
	public Integer getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal
	 *            the ordinal to set
	 */
	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	/**
	 * @return the order index
	 */
	public Integer getOrderIndex() {
		return orderIndex;
	}

	/**
	 * @param orderIndex
	 *            the order index to set
	 */
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	/**
	 * @return the family index
	 */
	public Integer getFamilyIndex() {
		return familyIndex;
	}

	/**
	 * @param familyIndex
	 *            the family index to set
	 */
	public void setFamilyIndex(Integer familyIndex) {
		this.familyIndex = familyIndex;
	}

	/**
	 * @return the sub family index
	 */
	public Integer getSubFamilyIndex() {
		return subFamilyIndex;
	}

	/**
	 * @param subFamilyIndex
	 *            the sub family index to set
	 */
	public void setSubFamilyIndex(Integer subFamilyIndex) {
		this.subFamilyIndex = subFamilyIndex;
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
	 */
	public void setOrder(String order) {
		this.order = order;
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
	 */
	public void setFamily(String family) {
		this.family = family;
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
	 */
	public void setSubFamily(String subFamily) {
		this.subFamily = subFamily;
	}

	/**
	 * @return the emptyTable
	 */
	public String getEmptyTable() {
		return emptyTable;
	}

	/**
	 * @param emptyTable
	 *            the emptyTable to set
	 */
	public void setEmptyTable(String emptyTable) {
		this.emptyTable = emptyTable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BotanicSystemDto [id=" + id + ", version=" + version + ", ordinal=" + ordinal + ", orderIndex="
				+ orderIndex + ", familyIndex=" + familyIndex + ", subFamilyIndex=" + subFamilyIndex + ", order="
				+ order + ", family=" + family + ", subFamily=" + subFamily + ", emptyTable=" + emptyTable + "]";
	}

}
