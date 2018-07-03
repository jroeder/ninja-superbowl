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

import dto.BowlDto;
import entity.TimberOrigin;
import types.SuperbowlHelper;

/**
 * Form data fields related to a {@code Bowl} instance.
 *
 * @author mbsusr01
 */
public class BowlForm implements Form {

	/**
	 * the unique technical identifier of a {@code Bowl}
	 */
	private String id;

	/**
	 * the version number of a {@code Bowl}
	 */
	private String version;

	/**
	 * the index number of a {@code Bowl}
	 */
	private String index;

	/**
	 * the unique technical identifier of a {@code GeoRegion}
	 */
	@NotBlank(message = "{bowl.georegion.id.blank}")
	// @NotNull(message = "{bowl.georegion.id.null}")
	private String geoRegionId;

	/**
	 * the {@code GeoRegion} code
	 */
	private String geoRegionCode;

	/**
	 * the unique technical identifier of a {@code Manufacture}
	 */
	@NotBlank(message = "{bowl.manufacture.id.blank}")
	// @NotNull(message = "{bowl.manufacture.id.null}")
	private String manufactureId;

	/**
	 * the unique technical identifier of a {@code Status}
	 */
	@NotBlank(message = "{bowl.status.id.blank}")
	// @NotNull(message = "{bowl.status.id.null}")
	private String statusId;

	/**
	 * the {@code Status} code
	 */
	private String statusCode;

	/**
	 * the unique technical identifier of a {@code Timber}
	 */
	@NotBlank(message = "{bowl.timber.id.blank}")
	// @NotNull(message = "{bowl.timber.id.null}")
	private String timberId;

	/**
	 * the {@code Timber} code
	 */
	private String timberCode;

	/**
	 * the unique technical identifier of a {@code TimberOrigin}
	 */
	@NotBlank(message = "{bowl.timber.origin.id.blank}")
	// @NotNull(message = "{bowl.timber.origin.null}")
	private String timberOriginId;

	/**
	 * the unique technical identifier of a {@code Customer}
	 */
	private String customerId;

	/**
	 * the unique technical identifier of a {@code Exhibition}
	 */
	private String exhibitionId;

	/**
	 * the ordinal number
	 */
	@NotBlank(message = "{bowl.ordinal.blank}")
	// @NotNull(message = "bowl.ordinal.null}")
	private String ordinal;

	/**
	 * the manufacturing year
	 */
	private String year;

	/**
	 * the image name
	 */
	private String imageName;

	/**
	 * the price value
	 */
	@NotBlank(message = "{bowl.price.blank}")
	// @NotNull(message = "bowl.price.null}")
	private String price;

	/**
	 * the cent value
	 */
	private String cent;

	/**
	 * the sales price value
	 */
	private String salesPrice;

	/**
	 * the sales cent value
	 */
	private String salesCent;

	/**
	 * the sales location
	 */
	private String salesLocation;

	/**
	 * the sales date
	 */
	private String salesDate;

	/**
	 * the comment
	 */
	private String comment;

	/**
	 * Indicates whether a {@code Bowl} instance exists already in the data base
	 * table ({@code true}) or not ({@code false})
	 */
	private String emptyTable;

	/**
	 * the status index
	 */
	private String statusIndex;

	/**
	 * the status text
	 */
	private String statusText;

	/**
	 * the customer given name
	 */
	private String customerGivenName;

	/**
	 * the customer family name
	 */
	private String customerFamilyName;

	/**
	 * the exhibition name
	 */
	private String exhibitionName;

	/**
	 * the timber name
	 */
	private String timberName;

	/**
	 * the timber origin full name
	 */
	private String timberOriginName;

	/**
	 * Constructor.
	 */
	public BowlForm() {
		super();
	}

	/**
	 * Constructor using {@code BowlDto}.
	 * 
	 * @param bowlDto
	 *            the {@code BowlDto} instance
	 */
	public BowlForm(BowlDto bowlDto) {
		super();
		this.id = bowlDto.getId().toString();
		this.version = bowlDto.getVersion().toString();
		this.index = bowlDto.getIndex().toString();
		this.geoRegionId = null;
		this.manufactureId = bowlDto.getManufacture().getId().toString();
		this.statusId = bowlDto.getStatus().getId().toString();
		this.timberId = bowlDto.getTimber().getId().toString();
		this.timberOriginId = bowlDto.getTimberOrigin().getId().toString();
		this.customerId = null;
		if (bowlDto.getCustomer() != null) {
			this.customerId = bowlDto.getCustomer().getId().toString();
			this.customerGivenName = bowlDto.getCustomer().getGivenName();
			this.customerFamilyName = bowlDto.getCustomer().getFamilyName();
		}
		this.exhibitionId = null;
		if (bowlDto.getExhibition() != null) {
			this.exhibitionId = bowlDto.getExhibition().getId().toString();
			this.exhibitionName = bowlDto.getExhibition().getName();
		}
		this.ordinal = bowlDto.getOrdinal().toString();
		this.year = bowlDto.getYear();
		this.imageName = bowlDto.getImageName();
		this.price = bowlDto.getPrice().toString();
		this.cent = "00";
		if (bowlDto.getSalesPrice() == null) {
			this.salesPrice = null;
			this.salesCent = null;
		} else {
			this.salesPrice = bowlDto.getSalesPrice().toString();
			this.salesCent = "00";
		}
		if (bowlDto.getSalesLocation() == null) {
			this.salesLocation = null;
		} else {
			this.salesLocation = bowlDto.getSalesLocation();
		}
		if (bowlDto.getSalesDate() == null) {
			this.salesDate = null;
		} else {
			this.salesDate = bowlDto.getSalesDate().toString();
		}
		this.comment = bowlDto.getComment();
		this.statusIndex = bowlDto.getStatus().getIndex().toString();
		this.statusText = bowlDto.getStatus().getText();
		this.timberName = bowlDto.getTimber().getName();
		TimberOrigin timberOrigin = bowlDto.getTimberOrigin();
		if (timberOrigin == null) {
			this.timberOriginName = "Unknown";
		} else {
			this.timberOriginName = timberOrigin.getTimberOriginName();
		}

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
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
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
	 * @return the timberOriginId
	 */
	public String getTimberOriginId() {
		return timberOriginId;
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
	 * @param timberOriginId
	 *            the timberOriginId to set
	 */
	public void setTimberOriginId(String timberOriginId) {
		this.timberOriginId = timberOriginId;
	}

	/**
	 * @return the manufactureId
	 */
	public String getManufactureId() {
		return manufactureId;
	}

	/**
	 * @param manufactureId
	 *            the manufactureId to set
	 */
	public void setManufactureId(String manufactureId) {
		this.manufactureId = manufactureId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the exhibitionId
	 */
	public String getExhibitionId() {
		return exhibitionId;
	}

	/**
	 * @param exhibitionId
	 *            the exhibitionId to set
	 */
	public void setExhibitionId(String exhibitionId) {
		this.exhibitionId = exhibitionId;
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
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the cent
	 */
	public String getCent() {
		return cent;
	}

	/**
	 * @param cent
	 *            the cent to set
	 */
	public void setCent(String cent) {
		this.cent = cent;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return SuperbowlHelper.checkImageName(imageName);
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = SuperbowlHelper.checkImageName(imageName);
	}

	/**
	 * @return the salesPrice
	 */
	public String getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice
	 *            the sales price to set
	 */
	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the salesCent
	 */
	public String getSalesCent() {
		return salesCent;
	}

	/**
	 * @param salesCent
	 *            the sales cent to set
	 */
	public void setSalesCent(String salesCent) {
		this.salesCent = salesCent;
	}

	/**
	 * @return the salesLocation
	 */
	public String getSalesLocation() {
		return salesLocation;
	}

	/**
	 * @param salesLocation
	 *            the sales location to set
	 */
	public void setSalesLocation(String salesLocation) {
		this.salesLocation = salesLocation;
	}

	/**
	 * @return the salesDate
	 */
	public String getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate
	 *            the sales date to set
	 */
	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
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

	/**
	 * @return the statusIndex
	 */
	public String getStatusIndex() {
		return statusIndex;
	}

	/**
	 * @param statusIndex
	 *            the statusIndex to set
	 */
	public void setStatusIndex(String statusIndex) {
		this.statusIndex = statusIndex;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText
	 *            the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
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
	 * @return the timberOriginName
	 */
	public String getTimberOriginName() {
		return timberOriginName;
	}

	/**
	 * @param timberOriginName
	 *            the timberOriginName to set
	 */
	public void setTimberOriginName(String timberOriginName) {
		this.timberOriginName = timberOriginName;
	}

	/**
	 * @return the exhibitionName
	 */
	public String getExhibitionName() {
		return exhibitionName;
	}

	/**
	 * @param exhibitionName
	 *            the exhibitionName to set
	 */
	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
	}

	/**
	 * @return the customerGivenName
	 */
	public String getCustomerGivenName() {
		return customerGivenName;
	}

	/**
	 * @param customerGivenName
	 *            the customerGivenName to set
	 */
	public void setCustomerGivenName(String customerGivenName) {
		this.customerGivenName = customerGivenName;
	}

	/**
	 * @return the customerFamilyName
	 */
	public String getCustomerFamilyName() {
		return customerFamilyName;
	}

	/**
	 * @param customerFamilyName
	 *            the customerFamilyName to set
	 */
	public void setCustomerFamilyName(String customerFamilyName) {
		this.customerFamilyName = customerFamilyName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BowlForm [id=" + id + ", version=" + version + ", index=" + index + ", geoRegionId=" + geoRegionId
				+ ", geoRegionCode=" + geoRegionCode + ", manufactureId=" + manufactureId + ", statusId=" + statusId
				+ ", statusCode=" + statusCode + ", timberId=" + timberId + ", timberCode=" + timberCode
				+ ", timberOriginId=" + timberOriginId + ", manufactureId=" + manufactureId + ", customerId="
				+ customerId + ", exhibitionId=" + exhibitionId + ", ordinal=" + ordinal + ", year=" + year
				+ ", imageName=" + imageName + ", price=" + price + ", cent=" + cent + ", salesPrice=" + salesPrice
				+ ", salesCent=" + salesCent + ", salesLocation=" + salesLocation + ", salesDate=" + salesDate
				+ ", comment=" + comment + ", emptyTable=" + emptyTable + ", statusIndex=" + statusIndex
				+ ", statusText=" + statusText + ", customerGivenName=" + customerGivenName + ", customerFamilyName="
				+ customerFamilyName + ", exhibitionName=" + exhibitionName + ", timberName=" + timberName
				+ ", timberOriginName=" + timberOriginName + "]";
	}

}
