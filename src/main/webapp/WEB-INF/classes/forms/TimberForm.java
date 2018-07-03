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

import dto.TimberDto;

/**
 * Form data fields related to a {@code Timber} instance.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
public class TimberForm implements Form {

	/**
	 * the unique technical identifier of a {@code Timber}
	 */
	private String id;

	/**
	 * the version number of a {@code Timber}
	 */
	private String version;

	// @NotBlank(message = "{timber.index.blank}")
	// @NotNull(message = "{timber.index.null}")
	private String index;

	/**
	 * the unique technical identifier of a {@code GeoRegion}
	 */
	@NotBlank(message = "{timber.georegion.id.blank}")
	// @NotNull(message = "{timber.georegion.id.null}")
	private String geoRegionId;

	/**
	 * the {@code GeoRegion} code
	 */
	 @NotBlank(message = "{timber.georegion.code.blank}")
	// @NotNull(message = "{timber.georegion.code.null}")
	private String geoRegionCode;

	/**
	 * the {@code GeoRegion} name
	 */
//	 @NotBlank(message = "{timber.georegion.name.blank}")
	// @NotNull(message = "{timber.georegion.name.null}")
	private String geoRegionName;

	/**
	 * the unique technical identifier of a {@code BotanicSystem}
	 */
	@NotBlank(message = "{timber.botanicSystem.id.blank}")
//	@NotNull(message = "{timber.botanicSystem.id.null}")
	private String botanicSystemId;

	/**
	 * the {@code BotanicSystem} order name
	 */
//	@NotBlank(message = "{timber.botanicSystem.order.blank}")
//	@NotNull(message = "{timber.botanicSystem.order.null}")
	private String botanicSystemOrder;

	/**
	 * the {@code BotanicSystem} family name
	 */
//	@NotBlank(message = "{timber.botanicSystem.family.blank}")
//	@NotNull(message = "{timber.botanicSystem.family.null}")
	private String botanicSystemFamily;

	/**
	 * the {@code BotanicSystem} sub family name
	 */
	// @NotBlank(message = "{timber.botanicSystem.sub.family.blank}")
//	@NotNull(message = "{timber.botanicSystem.sub.family.null}")
	private String botanicSystemSubFamily;

	/**
	 * the {@code Timber} type
	 */
	@NotBlank(message = "{timber.type.blank}")
	private String type;

	/**
	 * the {@code Timber} code
	 */
	@NotBlank(message = "{timber.code.blank}")
	private String code;

	/**
	 * the {@code Timber} name
	 */
	@NotBlank(message = "{timber.name.blank}")
	private String name;

	/**
	 * the {@code Timber} image name
	 */
	private String imageName;

	/**
	 * the {@code Timber} academic name
	 */
	private String academicName;

	/**
	 * the {@code Timber} gross density value
	 */
	private String grossDensity;

	/**
	 * the {@code Timber} tensile strength value
	 */
	private String tensileStrength;

	/**
	 * the {@code Timber} burst strength value
	 */
	private String burstStrength;

	/**
	 * the {@code Timber} bending strength value
	 */
	private String bendingStrength;

	/**
	 * the {@code Timber} shear strength value
	 */
	private String shearStrength;

	/**
	 * the {@code Timber} brinell hardness one value
	 */
	private String brinellHardnessOne;
	/**
	 * the {@code Timber} brinell hardness two value
	 */
	private String brinellHardnessTwo;
	/**
	 * the {@code Timber} tangential shrinkage value
	 */
	private String tangentShrinkage;
	/**
	 * the {@code Timber} radial shrinkage value
	 */
	private String radialShrinkage;

	/**
	 * Indicates whether a {@code TimberOrigin} instance exists already in the
	 * data base table ({@code true}) or not ({@code false})
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public TimberForm() {
		super();
	}

	/**
	 * Constructor using {@code TimberDto}.
	 * 
	 * @param timberDto
	 *            the {@code TimberDto} instance
	 */
	public TimberForm(TimberDto timberDto) {
		super();
		this.id = timberDto.getId().toString();
		this.version = timberDto.getVersion().toString();
		this.geoRegionId = timberDto.getGeoRegion().getId().toString();
		this.geoRegionCode = timberDto.getGeoRegion().getCode();
		this.geoRegionName = timberDto.getGeoRegion().getName();
		this.botanicSystemId = timberDto.getBotanicSystem().getId().toString();
		this.botanicSystemOrder = timberDto.getBotanicSystem().getOrder();
		this.botanicSystemFamily = timberDto.getBotanicSystem().getFamily();
		this.botanicSystemSubFamily = timberDto.getBotanicSystem().getSubFamily();
		this.type = timberDto.getType();
		this.code = timberDto.getCode();
		this.name = timberDto.getName();
		this.imageName = timberDto.getImageName();
		this.academicName = timberDto.getAcademicName();
		this.grossDensity = timberDto.getGrossDensity();
		this.tensileStrength = timberDto.getTensileStrength();
		this.burstStrength = timberDto.getBurstStrength();
		this.bendingStrength = timberDto.getBendingStrength();
		this.shearStrength = timberDto.getShearStrength();
		this.brinellHardnessOne = timberDto.getBrinellHardnessOne();
		this.brinellHardnessTwo = timberDto.getBrinellHardnessTwo();
		this.tangentShrinkage = timberDto.getTangentShrinkage();
		this.radialShrinkage = timberDto.getRadialShrinkage();
		this.emptyTable = timberDto.getEmptyTable();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the index
	 */
	public String getIndex() {
		return index.toString();
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the geoRegionId
	 */
	public String getGeoRegionId() {
		return geoRegionId;
	}

	/**
	 * @param geoRegionId
	 *            the geoRegionId to set
	 */
	public void setGeoRegionId(String geoRegionId) {
		this.geoRegionId = geoRegionId;
	}

	/**
	 * @return the geoRegionCode
	 */
	public String getGeoRegionCode() {
		return geoRegionCode;
	}

	/**
	 * @param geoRegionCode
	 *            the geoRegionCode to set
	 */
	public void setGeoRegionCode(String geoRegionCode) {
		this.geoRegionCode = geoRegionCode;
	}

	/**
	 * @return the geoRegionName
	 */
	public String getGeoRegionName() {
		return geoRegionName;
	}

	/**
	 * @param geoRegionName the geoRegionName to set
	 */
	public void setGeoRegionName(String geoRegionName) {
		this.geoRegionName = geoRegionName;
	}

	/**
	 * @return the botanicSystemOrder
	 */
	public String getBotanicSystemOrder() {
		return botanicSystemOrder;
	}

	/**
	 * @param botanicSystemOrder the botanicSystemOrder to set
	 */
	public void setBotanicSystemOrder(String botanicSystemOrder) {
		this.botanicSystemOrder = botanicSystemOrder;
	}

	/**
	 * @return the botanicSystemFamily
	 */
	public String getBotanicSystemFamily() {
		return botanicSystemFamily;
	}

	/**
	 * @param botanicSystemFamily the botanicSystemFamily to set
	 */
	public void setBotanicSystemFamily(String botanicSystemFamily) {
		this.botanicSystemFamily = botanicSystemFamily;
	}

	/**
	 * @return the botanicSystemSubFamily
	 */
	public String getBotanicSystemSubFamily() {
		return botanicSystemSubFamily;
	}

	/**
	 * @param botanicSystemSubFamily the botanicSystemSubFamily to set
	 */
	public void setBotanicSystemSubFamily(String botanicSystemSubFamily) {
		this.botanicSystemSubFamily = botanicSystemSubFamily;
	}

	/**
	 * @return the botanicSystemId
	 */
	public String getBotanicSystemId() {
		return botanicSystemId;
	}

	/**
	 * @param botanicSystemId
	 *            the botanicSystemId to set
	 */
	public void setBotanicSystemId(String botanicSystemId) {
		this.botanicSystemId = botanicSystemId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the academicName
	 */
	public String getAcademicName() {
		return academicName;
	}

	/**
	 * @param academicName
	 *            the academicName to set
	 */
	public void setAcademicName(String academicName) {
		this.academicName = academicName;
	}

	/**
	 * @return the grossDensity
	 */
	public String getGrossDensity() {
		return grossDensity;
	}

	/**
	 * @param grossDensity
	 *            the grossDensity to set
	 */
	public void setGrossDensity(String grossDensity) {
		this.grossDensity = grossDensity;
	}

	/**
	 * @return the tensileStrength
	 */
	public String getTensileStrength() {
		return tensileStrength;
	}

	/**
	 * @param tensileStrength
	 *            the tensileStrength to set
	 */
	public void setTensileStrength(String tensileStrength) {
		this.tensileStrength = tensileStrength;
	}

	/**
	 * @return the burstStrength
	 */
	public String getBurstStrength() {
		return burstStrength;
	}

	/**
	 * @param burstStrength
	 *            the burstStrength to set
	 */
	public void setBurstStrength(String burstStrength) {
		this.burstStrength = burstStrength;
	}

	/**
	 * @return the bendingStrength
	 */
	public String getBendingStrength() {
		return bendingStrength;
	}

	/**
	 * @param bendingStrength
	 *            the bendingStrength to set
	 */
	public void setBendingStrength(String bendingStrength) {
		this.bendingStrength = bendingStrength;
	}

	/**
	 * @return the shearStrength
	 */
	public String getShearStrength() {
		return shearStrength;
	}

	/**
	 * @param shearStrength
	 *            the shearStrength to set
	 */
	public void setShearStrength(String shearStrength) {
		this.shearStrength = shearStrength;
	}

	/**
	 * @return the brinellHardnessOne
	 */
	public String getBrinellHardnessOne() {
		return brinellHardnessOne;
	}

	/**
	 * @param brinellHardnessOne
	 *            the brinellHardnessOne to set
	 */
	public void setBrinellHardnessOne(String brinellHardnessOne) {
		this.brinellHardnessOne = brinellHardnessOne;
	}

	/**
	 * @return the brinellHardnessTwo
	 */
	public String getBrinellHardnessTwo() {
		return brinellHardnessTwo;
	}

	/**
	 * @param brinellHardnessTwo
	 *            the brinellHardnessTwo to set
	 */
	public void setBrinellHardnessTwo(String brinellHardnessTwo) {
		this.brinellHardnessTwo = brinellHardnessTwo;
	}

	/**
	 * @return the tangentShrinkage
	 */
	public String getTangentShrinkage() {
		return tangentShrinkage;
	}

	/**
	 * @param tangentShrinkage
	 *            the tangentShrinkage to set
	 */
	public void setTangentShrinkage(String tangentShrinkage) {
		this.tangentShrinkage = tangentShrinkage;
	}

	/**
	 * @return the radialShrinkage
	 */
	public String getRadialShrinkage() {
		return radialShrinkage;
	}

	/**
	 * @param radialShrinkage
	 *            the radialShrinkage to set
	 */
	public void setRadialShrinkage(String radialShrinkage) {
		this.radialShrinkage = radialShrinkage;
	}

	/**
	 * @return the empty table indicator
	 */
	public String getEmptyTable() {
		return emptyTable;
	}

	/**
	 * @param emptyTable
	 *            the empty table indicator to set
	 */
	public void setEmptyTable(String emptyTable) {
		this.emptyTable = emptyTable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "TimberForm [id=" + id + ", version=" + version + ", index=" + index + ", geoRegionId=" + geoRegionId
				+ ", geoRegionCode=" + geoRegionCode + ", geoRegionName=" + geoRegionName + ", botanicSystemId="
				+ botanicSystemId + ", botanicSystemOrder=" + botanicSystemOrder + ", botanicSystemFamily="
				+ botanicSystemFamily + ", botanicSystemSubFamily=" + botanicSystemSubFamily + ", type=" + type
				+ ", code=" + code + ", name=" + name + ", imageName=" + imageName + ", academicName=" + academicName
				+ ", grossDensity=" + grossDensity + ", tensileStrength=" + tensileStrength + ", burstStrength="
				+ burstStrength + ", bendingStrength=" + bendingStrength + ", shearStrength=" + shearStrength
				+ ", brinellHardnessOne=" + brinellHardnessOne + ", brinellHardnessTwo=" + brinellHardnessTwo
				+ ", tangentShrinkage=" + tangentShrinkage + ", radialShrinkage=" + radialShrinkage + ", emptyTable="
				+ emptyTable + "]";
	}

}
