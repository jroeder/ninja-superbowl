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

import dto.TimberOriginDto;

/**
 * Form data fields related to a {@code TimberOrigin} instance.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
public class TimberOriginForm implements Form {

	/**
	 * the unique technical identifier of a {@code TimberOrigin}
	 */
	private String id;

	/**
	 * the version number of a {@code TimberOrigin}
	 */
	private String version;

	/**
	 * the unique technical identifier of a {@code GeoRegion}
	 */
	// @NotBlank(message = "{georegion.code.blank}")
	// @NotNull(message = "{georegion.code.null}")
	private String geoRegionId;

	/**
	 * the {@code GeoRegion} code
	 */
	// @NotBlank(message = "{georegion.code.blank}")
	// @NotNull(message = "{georegion.code.null}")
	private String geoRegionCode;

	/**
	 * the unique technical identifier of a {@code Timber} instance
	 */
	// @NotBlank(message = "{timber.id.blank}")
	// @NotNull(message = "{timber.id.null}")
	private String timberId;

	/**
	 * the {@code Timber} index
	 */
	// @NotBlank(message = "{timber.index.blank}")
	// @NotNull(message = "{timber.index.null}")
	private String timberIndex;

	/**
	 * the {@code Timber}r code
	 */
	// @NotBlank(message = "{timber.code.blank}")
	// @NotNull(message = "{timber.codex.null}")
	private String timberCode;

	/**
	 * the {@code Timber} name
	 */
	// @NotBlank(message = "{timber.name.blank}")
	// @NotNull(message = "{timber.name.null}")
	private String timberName;

	/**
	 * the {@code TimberOrigin} index
	 */
	// @NotBlank(message = "{timberorigin.index.blank}")
	// @NotNull(message = "{timberorigin.index.null}")
	private String index;

	/**
	 * the {@code TimberOrigin} cut down date
	 */
	// @NotBlank(message = "{timberorigin.cutdown.blank}")
	// @NotNull(message = "{timberorigin.cutdown.null}")
	private String cutdown;

	/**
	 * the {@code TimberOrigin} city name
	 */
	@NotBlank(message = "{timberorigin.city.blank}")
	// @NotNull(message = "{timberorigin.city.null}")
	private String city;

	/**
	 * the {@code TimberOrigin} location
	 */
	@NotBlank(message = "{timberorigin.location.blank}")
	// @NotNull(message = "{timberorigin.location.null}")
	private String location;

	/**
	 * the extended textual description of a location
	 */
	// @NotBlank(message = "{timberorigin.location.text.blank}")
	// @NotNull(message = "{timberorigin.location.text.null}")
	private String locationText;

	/**
	 * the {@code TimberOrigin} comment
	 */
	// @NotBlank(message = "{timberorigin.comment.blank}")
	// @NotNull(message = "{timberorigin.comment.null}")
	private String comment;

	/**
	 * Indicates whether a {@code TimberOrigin} instance exists already in the
	 * data base table ({@code true}) or not ({@code false})
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public TimberOriginForm() {
		super();
	}

	/**
	 * Constructor using {@code TimberOriginDto}.
	 * 
	 * @param timberOriginDto
	 *            the {@code TimberOriginDto} instance
	 */
	public TimberOriginForm(TimberOriginDto timberOriginDto) {
		super();
		this.id = timberOriginDto.getId().toString();
		this.version = timberOriginDto.getVersion().toString();
		this.geoRegionId = timberOriginDto.getTimber().getGeoRegion().getId().toString();
		this.geoRegionCode = timberOriginDto.getTimber().getGeoRegion().getCode();
		this.timberId = timberOriginDto.getTimber().getId().toString();
		this.timberIndex = timberOriginDto.getTimber().getIndex().toString();
		this.timberCode = timberOriginDto.getTimber().getCode();
		this.timberName = timberOriginDto.getTimber().getName();
		this.index = timberOriginDto.getIndex().toString();
		this.city = timberOriginDto.getCity();
		this.location = timberOriginDto.getLocation();
		this.locationText = timberOriginDto.getLocationText();
		this.cutdown = timberOriginDto.getCutdown();
		this.comment = timberOriginDto.getComment();
		this.emptyTable = timberOriginDto.getEmptyTable();
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
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index) {
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
	 * @return the timberId
	 */
	public String getTimberId() {
		return timberId;
	}

	/**
	 * @param timberId
	 *            the timberId to set
	 */
	public void setTimberId(String timberId) {
		this.timberId = timberId;
	}

	/**
	 * @return the timberIndex
	 */
	public String getTimberIndex() {
		return timberIndex;
	}

	/**
	 * @param timberIndex
	 *            the timberIndex to set
	 */
	public void setTimberIndex(String timberIndex) {
		this.timberIndex = timberIndex;
	}

	/**
	 * @return the timberCode
	 */
	public String getTimberCode() {
		return timberCode;
	}

	/**
	 * @param timberCode
	 *            the timberCode to set
	 */
	public void setTimberCode(String timberCode) {
		this.timberCode = timberCode;
	}

	/**
	 * @return the timberName
	 */
	public String getTimberName() {
		return timberName;
	}

	/**
	 * @param timberName
	 *            the timberName to set
	 */
	public void setTimberName(String timberName) {
		this.timberName = timberName;
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

}
