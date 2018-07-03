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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.TimberDto;

/**
 * Repräsentiert eine Holzart.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Timber implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 2165989770045121060L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TIMBER_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "TIMBER_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "TIMBER_INDEX", nullable = false)
	private Integer index;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TIMBER_GEOREGION_ID", nullable = false, updatable = false)
	private GeoRegion geoRegion;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TIMBER_BOTANICSYSTEM_ID", nullable = false, updatable = false)
	private BotanicSystem botanicSystem;

	@NotNull
	@Column(name = "TIMBER_TYPE", nullable = false)
	private String type;

	@NotNull
	@Column(name = "TIMBER_CODE", nullable = false)
	private String code;

	@NotNull
	@Column(name = "TIMBER_NAME", nullable = false)
	private String name;

	@NotNull
	@Column(name = "TIMBER_IMAGENAME", nullable = false)
	private String imageName;

	@NotNull
	@Column(name = "TIMBER_ACADEMICNAME", nullable = false)
	private String academicName;

	@NotNull
	@Column(name = "TIMBER_GROSS_DENSITY", nullable = false)
	private String grossDensity;

	@NotNull
	@Column(name = "TIMBER_TENSILE_STRENGTH", nullable = false)
	private String tensileStrength;

	@NotNull
	@Column(name = "TIMBER_BURST_STRENGTH", nullable = false)
	private String burstStrength;

	@NotNull
	@Column(name = "TIMBER_BENDING_STRENGTH", nullable = false)
	private String bendingStrength;

	@NotNull
	@Column(name = "TIMBER_SHEAR_STRENGTH", nullable = false)
	private String shearStrength;

	@NotNull
	@Column(name = "TIMBER_BRINELL_HARDNESS1", nullable = false)
	private String brinellHardnessOne;

	@NotNull
	@Column(name = "TIMBER_BRINELL_HARDNESS2", nullable = false)
	private String brinellHardnessTwo;

	@NotNull
	@Column(name = "TIMBER_TANGENT_SHRINKAGE", nullable = false)
	private String tangentShrinkage;

	@NotNull
	@Column(name = "TIMBER_RADIAL_SHRINKAGE", nullable = false)
	private String radialShrinkage;

	/**
	 * Constructor.
	 */
	public Timber() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param geoRegion
	 *            the referenced {@code GeoRegion} instance
	 * @param botanicSystem
	 *            the referenced {@code BotanicSystem} instance
	 * @param type
	 *            the type
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param imageName
	 *            the image name
	 * @param academicName
	 *            the academic name
	 * @param grossDensity
	 *            the gross Density
	 * @param tensileStrength
	 *            the tencile strength
	 * @param burstStrength
	 *            the burst strength
	 * @param bendingStrength
	 *            the bending strength
	 * @param shearStrength
	 *            the shear strenegth
	 * @param brinellHardnessOne
	 *            the brinell hardness one
	 * @param brinellHardnessTwo
	 *            the brinell hardness two
	 * @param tangentShrinkage
	 *            the tangent shrinkage
	 * @param radialShrinkage
	 *            the radial shrinkage
	 */
	public Timber(Integer version, Integer index, GeoRegion geoRegion, BotanicSystem botanicSystem, String type,
			String code, String name, String imageName, String academicName, String grossDensity, String tensileStrength,
			String burstStrength, String bendingStrength, String shearStrength, String brinellHardnessOne,
			String brinellHardnessTwo, String tangentShrinkage, String radialShrinkage) {
		super();
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
		this.tensileStrength= tensileStrength;
		this.burstStrength = burstStrength;
		this.bendingStrength = bendingStrength;
		this.shearStrength = shearStrength;
		this.brinellHardnessOne = brinellHardnessOne;
		this.brinellHardnessTwo = brinellHardnessTwo;
		this.tangentShrinkage = tangentShrinkage;
		this.radialShrinkage = radialShrinkage;
	}

	/**
	 * Constructor using a {@code TimberDto} instance.
	 *
	 * @param timberDto
	 *            the {@code TimberDto} instance
	 */
	public Timber(TimberDto timberDto) {
		super();
		this.id = timberDto.getId();
		this.version = timberDto.getVersion();
		this.index = timberDto.getIndex();
		this.geoRegion = timberDto.getGeoRegion();
		this.botanicSystem = timberDto.getBotanicSystem();
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
	 * @return the {@code Timber} instance
	 */
	public Timber setId(Long id) {
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
	 * @param version
	 *            the version to set
	 * @return the {@code Timber} instance
	 */
	public Timber setVersion(Integer version) {
		this.version = version;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setIndex(Integer index) {
		this.index = index;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setGeoRegion(GeoRegion geoRegion) {
		this.geoRegion = geoRegion;
		return this;
	}

	/**
	 * @return the botanicSystem
	 */
	public BotanicSystem getBotanicSystem() {
		return botanicSystem;
	}

	/**
	 * @param botanicSystem
	 *            the botanic system to set
	 * @return the {@code Timber} instance
	 */
	public Timber setBotanicSystem(BotanicSystem botanicSystem) {
		this.botanicSystem = botanicSystem;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setType(String type) {
		this.type = type;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setCode(String code) {
		this.code = code;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setName(String name) {
		this.name = name;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setImageName(String imageName) {
		this.imageName = imageName;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setAcademicName(String academicName) {
		this.academicName = academicName;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setGrossDensity(String grossDensity) {
		this.grossDensity = grossDensity;
		return this;
	}

	/**
	 * @return the tensileStrength
	 */
	public String getTensileStrength() {
		return tensileStrength;
	}

	/**
	 * @param tensileStrength
	 *            the tencile strength to set
	 * @return the {@code Timber} instance
	 */
	public Timber setTensileStrength(String tensileStrength) {
		this.tensileStrength = tensileStrength;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setBurstStrength(String burstStrength) {
		this.burstStrength = burstStrength;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setBendingStrength(String bendingStrength) {
		this.bendingStrength = bendingStrength;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setShearStrength(String shearStrength) {
		this.shearStrength = shearStrength;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setBrinellHardnessOne(String brinellHardnessOne) {
		this.brinellHardnessOne = brinellHardnessOne;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setBrinellHardnessTwo(String brinellHardnessTwo) {
		this.brinellHardnessTwo = brinellHardnessTwo;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setTangentShrinkage(String tangentShrinkage) {
		this.tangentShrinkage = tangentShrinkage;
		return this;
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
	 * @return the {@code Timber} instance
	 */
	public Timber setRadialShrinkage(String radialShrinkage) {
		this.radialShrinkage = radialShrinkage;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Timber [id=" + id + ", version=" + version + ", index=" + index + ", geoRegion=" + geoRegion
				+ ", botanicSystem=" + botanicSystem + ", type=" + type + ", code=" + code + ", name=" + name
				+ ", imageName=" + imageName + ", academicName=" + academicName + ", grossDensity=" + grossDensity
				+ ", tensileStrength=" + tensileStrength + ", burstStrength=" + burstStrength + ", bendingStrength=" + bendingStrength
				+ ", shearStrength=" + shearStrength + ", brinellHardnessOne=" + brinellHardnessOne
				+ ", brinellHardnessTwo=" + brinellHardnessTwo + ", tangentShrinkage=" + tangentShrinkage
				+ ", radialShrinkage=" + radialShrinkage + "]";
	}

}
