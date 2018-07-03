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

import entity.Timber;
import entity.TimberOrigin;

/**
 * Data Transfer Object of Entity {@code TimberOrigin}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
public class TimberOriginDto implements Dto {

	/**
	 * 2-character space divider.
	 */
	private static final String DIVIDER = "  ";

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
	 * the {@code Timber} reference
	 */
	@NotNull
	private Timber timber;

	/**
	 * the index
	 */
	@NotNull
	private Integer index;

	/**
	 * the origin city name
	 */
	@NotNull
	@Size(max = 32)
	private String city;

	/**
	 * the origin location
	 */
	@NotNull
	@Size(max = 32)
	private String location;

	/**
	 * the origin locationText
	 */
	@NotNull
	@Size(max = 32)
	private String locationText;

	/**
	 * the cut down date
	 */
	@NotNull
	@Size(max = 12)
	private String cutdown;

	/**
	 * the comment
	 */
	@NotNull
	@Size(max = 64)
	private String comment;

	/**
	 * the composite name of {@code TimberOrigin}
	 */
	private String compositeName;

	/**
	 * the empty table indicator
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public TimberOriginDto() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param timber
	 *            the {@code Timber} reference
	 * @param index
	 *            the index
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
	public TimberOriginDto(Long id, Integer version, Timber timber, Integer index, String city, String location,
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
		this.emptyTable = Boolean.FALSE.toString();
		this.compositeName = getCompositeName();
	}

	/**
	 * Constructor using fields.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param timber
	 *            the {@code Timber} reference
	 * @param index
	 *            the index
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
	 * @param emptyTable
	 *            the emptyTable indicator
	 */
	public TimberOriginDto(Long id, Integer version, Timber timber, Integer index, String city, String location,
			String locationText, String cutdown, String comment, String emptyTable) {
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
		this.emptyTable = emptyTable;
		this.compositeName = getCompositeName();
	}

	/**
	 * Constructor.
	 *
	 * @param timberOrigin
	 *            the {@code TimberOrigin} instance
	 */
	public TimberOriginDto(TimberOrigin timberOrigin) {
		super();
		this.receive(timberOrigin);
	}

	/**
	 * Receives the data of a {@code TimberOrigin} entity instance.
	 *
	 * @param timberOrigin
	 *            the {@code TimberOrigin} instance
	 */
	public void receive(TimberOrigin timberOrigin) {
		if (timberOrigin == null) {
			init();
		} else {
			this.id = timberOrigin.getId();
			this.version = timberOrigin.getVersion();
			this.timber = timberOrigin.getTimber();
			this.index = timberOrigin.getIndex();
			this.city = timberOrigin.getCity();
			this.location = timberOrigin.getLocation();
			this.locationText = timberOrigin.getLocationText();
			this.cutdown = timberOrigin.getCutdown();
			this.comment = timberOrigin.getComment();
			this.emptyTable = Boolean.FALSE.toString();
			this.compositeName = getCompositeName();
		}
	}

	/**
	 * Initiates the properties of {@code TimberOriginDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.timber = null;
		this.index = 0;
		this.city = "";
		this.location = "";
		this.locationText = "";
		this.cutdown = "";
		this.comment = "";
		this.emptyTable = Boolean.FALSE.toString();
		this.compositeName = "";
	}

	/**
	 * Returns the composite name of {@code TimberOrigin} consisting of fields
	 * {@code city}, {@code location} and {@code locationText}.
	 * 
	 * @return the composite name of {@code TimberOrigin}
	 */
	public final String getCompositeName() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.city).append(DIVIDER).append(this.location).append(DIVIDER).append(this.locationText);
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

	/**
	 * Delivers the composite {@code TimberOrigin} name.
	 * 
	 * @return the composite {@code TimberOrigin} name
	 */
	public String getTimberOriginName() {
		return this.getCompositeName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "TimberOriginDto [id=" + id + ", version=" + version + ", timber=" + timber + ", index=" + index
				+ ", city=" + city + ", location=" + location + ", locationText=" + locationText + ", cutdown="
				+ cutdown + ", comment=" + comment + ", emptyTable=" + emptyTable + "]";
	}

}
