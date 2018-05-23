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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import entity.BotanicSystem;
import entity.GeoRegion;
import entity.Timber;

/**
 * Data Transfer Object of Entity {@code Timber}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
public class TimberDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	@NotNull
	private Integer version;

	/**
	 * the index
	 */
	@NotNull
	private Integer index;

	/**
	 * the {@code GeoRegion} reference
	 */
	@NotNull
	private GeoRegion geoRegion;

	/**
	 * the {@code BotanicSystem} reference
	 */
	@NotNull
	private BotanicSystem botanicSystem;

	/**
	 * the type
	 */
	@NotNull
	private String type;

	/**
	 * the code
	 */
	@Size(min = 4, max = 4)
	private String code;

	/**
	 * the iname
	 */
	private String name;

	/**
	 * the image name
	 */
	private String imageName;

	/**
	 * the academic name
	 */
	private String academicName;

	/**
	 * the gross density
	 */
	private String grossDensity;

	/**
	 * the tencile strength
	 */
	private String tensileStrength;

	/**
	 * the burst strength
	 */
	private String burstStrength;

	/**
	 * the bending strength
	 */
	private String bendingStrength;

	/**
	 * the shear strength
	 */
	private String shearStrength;

	/**
	 * the brinell hardness one
	 */
	private String brinellHardnessOne;

	/**
	 * the brinell hardness two
	 */
	private String brinellHardnessTwo;

	/**
	 * the tangent shrinkage
	 */
	private String tangentShrinkage;

	/**
	 * the radial shrinkage
	 */
	private String radialShrinkage;

	/**
	 * the empty table indicator
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public TimberDto() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param geoRegion
	 *            the {@code GeoRegion} reference
	 * @param botanicSystem
	 *            the {@code BotanicSystem} reference
	 * @param type
	 *            the type
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param imageName
	 *            the iamge name
	 * @param academicName
	 *            the academic name
	 * @param grossDensity
	 *            the gross density
	 * @param tensileStrength
	 *            the tencile strength
	 * @param burstStrength
	 *            the burst strength
	 * @param bendingStrength
	 *            the bending strength
	 * @param shearStrength
	 *            the shear strength
	 * @param brinellHardnessOne
	 *            the brinell hardness one
	 * @param brinellHardnessTwo
	 *            the brinell hardness two
	 * @param tangentShrinkage
	 *            the tangent shrinkage
	 * @param radialShrinkage
	 *            the radial shrinkage
	 */
	public TimberDto(Long id, Integer version, Integer index, GeoRegion geoRegion, BotanicSystem botanicSystem,
			String type, String code, String name, String imageName, String academicName, String grossDensity,
			String tensileStrength, String burstStrength, String bendingStrength, String shearStrength,
			String brinellHardnessOne, String brinellHardnessTwo, String tangentShrinkage, String radialShrinkage) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.geoRegion = geoRegion;
		this.botanicSystem = botanicSystem;
		this.type = type;
		this.code = code;
		this.name = name;
		this.imageName = imageName;
		this.academicName = academicName;
		this.grossDensity = grossDensity;
		this.tensileStrength = tensileStrength;
		this.burstStrength = burstStrength;
		this.bendingStrength = bendingStrength;
		this.shearStrength = shearStrength;
		this.brinellHardnessOne = brinellHardnessOne;
		this.brinellHardnessTwo = brinellHardnessTwo;
		this.tangentShrinkage = tangentShrinkage;
		this.radialShrinkage = radialShrinkage;
		this.emptyTable = Boolean.FALSE.toString();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param geoRegion
	 *            the {@code GeoRegion} reference
	 * @param botanicSystem
	 *            the {@code BotanicSystem} reference
	 * @param type
	 *            the type
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param imageName
	 *            the iamge name
	 * @param academicName
	 *            the academic name
	 * @param grossDensity
	 *            the gross density
	 * @param tensileStrength
	 *            the tencile strength
	 * @param burstStrength
	 *            the burst strength
	 * @param bendingStrength
	 *            the bending strength
	 * @param shearStrength
	 *            the shear strength
	 * @param brinellHardnessOne
	 *            the brinell hardness one
	 * @param brinellHardnessTwo
	 *            the brinell hardness two
	 * @param tangentShrinkage
	 *            the tangent shrinkage
	 * @param radialShrinkage
	 *            the radial shrinkage
	 * @param emptyTable
	 *            the emptyTable indicator
	 */
	public TimberDto(Long id, Integer version, Integer index, GeoRegion geoRegion, BotanicSystem botanicSystem,
			String type, String code, String name, String imageName, String academicName, String grossDensity,
			String tensileStrength, String burstStrength, String bendingStrength, String shearStrength,
			String brinellHardnessOne, String brinellHardnessTwo, String tangentShrinkage, String radialShrinkage,
			String emptyTable) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.geoRegion = geoRegion;
		this.botanicSystem = botanicSystem;
		this.type = type;
		this.code = code;
		this.name = name;
		this.imageName = imageName;
		this.academicName = academicName;
		this.grossDensity = grossDensity;
		this.tensileStrength = tensileStrength;
		this.burstStrength = burstStrength;
		this.bendingStrength = bendingStrength;
		this.shearStrength = shearStrength;
		this.brinellHardnessOne = brinellHardnessOne;
		this.brinellHardnessTwo = brinellHardnessTwo;
		this.tangentShrinkage = tangentShrinkage;
		this.radialShrinkage = radialShrinkage;
		this.emptyTable = emptyTable;
	}

	/**
	 * Constructor.
	 *
	 * @param timber
	 *            the {@code Timber} instance
	 */
	public TimberDto(Timber timber) {
		super();
		this.receive(timber);
	}

	/**
	 * Receives the data of a {@code Timber} entity instance.
	 *
	 * @param timber
	 *            the {@code Timber} instance
	 */
	public void receive(Timber timber) {
		if (timber == null) {
			init();
		} else {
			this.id = timber.getId();
			this.version = timber.getVersion();
			this.index = timber.getIndex();
			this.geoRegion = timber.getGeoRegion();
			this.botanicSystem = timber.getBotanicSystem();
			this.type = timber.getType();
			this.code = timber.getCode();
			this.name = timber.getName();
			this.imageName = timber.getImageName();
			this.academicName = timber.getAcademicName();
			this.grossDensity = timber.getGrossDensity();
			this.tensileStrength = timber.getTensileStrength();
			this.burstStrength = timber.getBurstStrength();
			this.bendingStrength = timber.getBendingStrength();
			this.shearStrength = timber.getShearStrength();
			this.brinellHardnessOne = timber.getBrinellHardnessOne();
			this.brinellHardnessTwo = timber.getBrinellHardnessTwo();
			this.tangentShrinkage = timber.getTangentShrinkage();
			this.radialShrinkage = timber.getRadialShrinkage();
			this.emptyTable = Boolean.FALSE.toString();
		}
	}

	/**
	 * Initiates the properties of {@code TimberDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.index = 0;
		this.geoRegion = null;
		this.botanicSystem = null;
		this.type = "";
		this.code = "";
		this.name = "";
		this.imageName = "";
		this.academicName = "";
		this.grossDensity = "";
		this.tensileStrength = "";
		this.burstStrength = "";
		this.bendingStrength = "";
		this.shearStrength = "";
		this.brinellHardnessOne = "";
		this.brinellHardnessTwo = "";
		this.tangentShrinkage = "";
		this.radialShrinkage = "";
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
	public void setIdn(Long id) {
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
	 * @return the index
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 * @return the geoRegion
	 */
	public GeoRegion getGeoRegion() {
		return geoRegion;
	}

	/**
	 * @param geoRegion
	 *            the geoRegion to set
	 */
	public void setGeoRegion(GeoRegion geoRegion) {
		this.geoRegion = geoRegion;
	}

	/**
	 * @return the botanicSystem
	 */
	public BotanicSystem getBotanicSystem() {
		return botanicSystem;
	}

	/**
	 * @param botanicSystem
	 *            the botanicSystem to set
	 */
	public void setBotanicSystem(BotanicSystem botanicSystem) {
		this.botanicSystem = botanicSystem;
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
	 *            the tensile strength to set
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
	public void setBrinellHardnesssOne(String brinellHardnessOne) {
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
		return "TimberDto [id=" + id + ", version=" + version + ", index=" + index + ", geoRegion=" + geoRegion
				+ ", botanicSystem=" + botanicSystem + ", type=" + type + ", code=" + code + ", name=" + name
				+ ", imageName=" + imageName + ", academicName=" + academicName + ", grossDensity=" + grossDensity
				+ ", tensileStrength=" + tensileStrength + ", burstStrength=" + burstStrength + ", bendingStrength="
				+ bendingStrength + ", shearStrength=" + shearStrength + ", brinellHardnessOne=" + brinellHardnessOne
				+ ", brinellHardnessTwo=" + brinellHardnessTwo + ", tangentShrinkage=" + tangentShrinkage
				+ ", radialShrinkage=" + radialShrinkage + ", emptyTable=" + emptyTable + "]";
	}

}
