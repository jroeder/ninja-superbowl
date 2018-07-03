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

import org.hibernate.validator.constraints.NotBlank;

import dto.BotanicSystemDto;

/**
 * Class to hold input form data of a {@code BotanicSystem}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public class BotanicSystemForm implements Form {

	/**
	 * the unique technical identifier of a {@code BotanicSystem}
	 */
	private String id;

	/**
	 * the version number of a {@code BotanicSystem}
	 */
	private String version;

	/**
	 * the {@code BotanicSystem} ordinal number
	 */
	private String ordinal;

	/**
	 * the {@code BotanicSystem} order index
	 */
	private String orderIndex;

	/**
	 * the {@code BotanicSystem} family index
	 */
	private String familyIndex;

	/**
	 * the {@code BotanicSystem} sub family index
	 */
	private String subFamilyIndex;

	/**
	 * the {@code BotanicSystem} order
	 */
	@NotBlank(message = "{botanicsystem.order.blank}")
	// @NotNull(message = "botanicsystem.order.null}")
	private String order;

	/**
	 * the {@code BotanicSystem} family
	 */
	@NotBlank(message = "botanicsystem.family.blank}")
	// @NotNull(message = "botanicsystem.family.null}")
	private String family;

	/**
	 * the {@code BotanicSystem} subfamily
	 */
	// @NotBlank(message = "botanicsystem.subFamily.blank}")
	// @NotNull(message = "botanicsystem.subFamily.null}")
	private String subFamily;

	/**
	 * Indicates whether a {@code BotanicSystem} instance exists already in the
	 * data base table ({@code true}) or not ({@code false})
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public BotanicSystemForm() {
		super();
	}

	/**
	 * Constructor using {@code BotanicSystemDto}.
	 * 
	 * @param botanicSystemDto
	 *            the {@code BotanicSystemDto} instance
	 */
	public BotanicSystemForm(BotanicSystemDto botanicSystemDto) {
		super();
		this.id = botanicSystemDto.getId().toString();
		this.version = botanicSystemDto.getVersion().toString();
		this.ordinal = botanicSystemDto.getOrdinal().toString();
		this.orderIndex = botanicSystemDto.getOrderIndex().toString();
		this.familyIndex = botanicSystemDto.getFamilyIndex().toString();
		this.subFamilyIndex = botanicSystemDto.getSubFamilyIndex().toString();
		this.order = botanicSystemDto.getOrder();
		this.family = botanicSystemDto.getFamily();
		this.subFamily = botanicSystemDto.getSubFamily();
		this.emptyTable = botanicSystemDto.getEmptyTable();
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
	 * @return the ordinal
	 */
	public String getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal
	 *            the ordinal to set
	 */
	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	/**
	 * @return the order index
	 */
	public String getOrderIndex() {
		return orderIndex;
	}

	/**
	 * @param orderIndex
	 *            the order index to set
	 */
	public void setOrderIndex(String orderIndex) {
		this.orderIndex = orderIndex;
	}

	/**
	 * @return the family index
	 */
	public String getFamilyIndex() {
		return familyIndex;
	}

	/**
	 * @param familyIndex
	 *            the family index to set
	 */
	public void setFamilyIndex(String familyIndex) {
		this.familyIndex = familyIndex;
	}

	/**
	 * @return the sub family index
	 */
	public String getSubFamilyIndex() {
		return subFamilyIndex;
	}

	/**
	 * @param subFamilyIndex
	 *            the sub family index to set
	 */
	public void setSubFamilyIndex(String subFamilyIndex) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BotanicSystemForm [id=" + id + ", version=" + version + ", ordinal=" + ordinal + ", orderIndex="
				+ orderIndex + ", familyIndex=" + familyIndex + ", subFamilyIndex=" + subFamilyIndex + ", order="
				+ order + ", family=" + family + ", subFamily=" + subFamily + ", emptyTable=" + emptyTable + "]";
	}

}
