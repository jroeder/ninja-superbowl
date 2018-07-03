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

import dto.TimberOriginDto;

/**
 * Repräsentiert den Herkunftsort und das Einschlagsdatum des Rohmaterials
 * (Holzes) einer Schale.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class TimberOrigin implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 538989052494200849L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TO_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "TO_VERSION", nullable = false)
	private Integer version;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TO_TIMBER_ID", nullable = false, updatable = false)
	private Timber timber;

	@NotNull
	@Column(name = "TO_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "TO_CITY", nullable = false)
	private String city;

	@NotNull
	@Column(name = "TO_LOCATION", nullable = false)
	private String location;

	@NotNull
	@Column(name = "TO_LOCATION_TEXT", nullable = false)
	private String locationText;

	@NotNull
	@Column(name = "TO_CUTDOWN", nullable = false)
	private String cutdown;

	@NotNull
	@Column(name = "TO_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 */
	public TimberOrigin() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param id
	 *            the unique technical identifier
	 * @param version
	 *            the version number
	 * @param timber
	 *            the referenced {@code Timber}
	 * @param index
	 *            the index number
	 * @param city
	 *            the city of origin
	 * @param location
	 *            the location of origin
	 * @param locationText
	 *            the extended textual description related to the location of
	 *            origin
	 * @param cutdown
	 *            the cut down date
	 * @param comment
	 *            the comment
	 */
	public TimberOrigin(Long id, Integer version, Timber timber, Integer index, String city, String location,
			String locationText, String cutdown, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.timber = timber;
		this.index = index;
		this.city = city;
		this.location = location;
		this.locationText = locationText;
		this.cutdown = cutdown;
		this.comment = comment;
	}

	/**
	 * Constructor using a {@code TimberOriginDto} instance.
	 *
	 * @param timberOriginDto
	 *            the {@code TimberDOriginto} instance
	 */
	public TimberOrigin(TimberOriginDto timberOriginDto) {
		super();
		this.id = timberOriginDto.getId();
		this.version = timberOriginDto.getVersion();
		this.timber = timberOriginDto.getTimber();
		this.index = timberOriginDto.getIndex();
		this.city = timberOriginDto.getCity();
		this.location = timberOriginDto.getLocation();
		this.locationText = timberOriginDto.getLocationText();
		this.cutdown = timberOriginDto.getCutdown();
		this.comment = timberOriginDto.getComment();
	}
	
	/**
	 * Delivers the {@code TimberOrigin} composite name.
	 * 
	 * @return the {@code TimberOrigin} composite name
	 */
	public final String getTimberOriginName() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.city).append("  ").append(this.location).append("  ").append(this.locationText);
		return sb.toString();
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
	 * @return the timber
	 */
	public Timber getTimber() {
		return timber;
	}

	/**
	 * @param timber
	 *            the timber to set
	 */
	public void setTimber(Timber timber) {
		this.timber = timber;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the locationText
	 */
	public String getLocationText() {
		return locationText;
	}

	/**
	 * @param locationText
	 *            the locationText to set
	 */
	public void setLocationText(String locationText) {
		this.locationText = locationText;
	}

	/**
	 * @return the cutdown
	 */
	public String getCutdown() {
		return cutdown;
	}

	/**
	 * @param cutdown
	 *            the cutdown to set
	 */
	public void setCutdown(String cutdown) {
		this.cutdown = cutdown;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "TimberOrigin [id=" + id + ", version=" + version + ", timber=" + timber + ", index=" + index + ", city="
				+ city + ", location=" + location + ", locationText=" + locationText + ", cutdown=" + cutdown
				+ ", comment=" + comment + "]";
	}

}
