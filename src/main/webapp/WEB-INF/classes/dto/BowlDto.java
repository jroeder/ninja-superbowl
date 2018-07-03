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

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Size;

import entity.Bowl;
import entity.Customer;
import entity.Exhibition;
import entity.Manufacture;
import entity.Status;
import entity.Timber;
import entity.TimberOrigin;

/**
 * The {@code Bowl} Data Transfer Object implementation class.
 *
 * @author mbsusr01
 */
public class BowlDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the index
	 */
	private Integer index;

	/**
	 * the {@code Manufacture} reference
	 */
	private Manufacture manufacture;

	/**
	 * the {@code Status} reference
	 */
	private Status status;

	/**
	 * the {@code Timber} reference
	 */
	private Timber timber;

	/**
	 * the {@code TimberOrigin} reference
	 */
	private TimberOrigin timberOrigin;

	/**
	 * the {@code Customer} reference
	 */
	private Customer customer;

	/**
	 * the {@code Exhibition} reference
	 */
	private Exhibition exhibition;

	/**
	 * the ordinal number
	 */
	private Integer ordinal;

	/**
	 * the manufacturing year
	 */
	@Size(min = 4, max = 4)
	private String year;

	/**
	 * the image name
	 */
	@Size(max = 64)
	private String imageName;

	/**
	 * the price
	 */
	private BigDecimal price;

	/**
	 * the cent
	 */
	private BigDecimal cent;

	/**
	 * the sales price value
	 */
	private BigDecimal salesPrice;

	/**
	 * the sales cent value
	 */
	private BigDecimal salesCent;

	/**
	 * the sales location
	 */
	@Size(max = 32)
	private String salesLocation;

	/**
	 * the sales date
	 */
	private Date salesDate;

	/**
	 * the comment
	 */
	@Size(max = 64)
	private String comment;

	/**
	 * the empty table indicator
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public BowlDto() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param manufacture
	 *            the manufacture
	 * @param status
	 *            the status
	 * @param timber
	 *            the timber
	 * @param timberOrigin
	 *            the timberOrigin
	 * @param customer
	 *            the customer
	 * @param exhibition
	 *            the exhibition
	 * @param ordinal
	 *            the ordinal number
	 * @param year
	 *            the manufacturing year
	 * @param imageName
	 *            the image name
	 * @param price
	 *            the price
	 * @param salesPrice
	 *            the sales price
	 * @param salesLocation
	 *            the sales location
	 * @param salesDate
	 *            the sales date
	 * @param comment
	 *            the comment
	 */
	public BowlDto(Long id, Integer version, Integer index, Manufacture manufacture, Status status, Timber timber,
			TimberOrigin timberOrigin, Customer customer, Exhibition exhibition, Integer ordinal, String year,
			String imageName, BigDecimal price, BigDecimal salesPrice, String salesLocation, Date salesDate,
			String comment) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.manufacture = manufacture;
		this.status = status;
		this.timber = timber;
		this.timberOrigin = timberOrigin;
		this.customer = customer;
		this.exhibition = exhibition;
		this.ordinal = ordinal;
		this.year = year;
		this.imageName = imageName;
		this.price = price;
		this.cent = new BigDecimal("00");
		this.salesPrice = salesPrice;
		if (salesPrice == null) {
			this.salesCent = null;
		} else {
			this.salesCent = new BigDecimal("00");
		}
		this.salesLocation = salesLocation;
		this.salesDate = salesDate;
		this.comment = comment;
		this.emptyTable = Boolean.FALSE.toString();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the id
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param manufacture
	 *            the manufacture
	 * @param status
	 *            the status
	 * @param timber
	 *            the timber
	 * @param timberOrigin
	 *            the timberOrigin
	 * @param customer
	 *            the customer
	 * @param exhibition
	 *            the exhibition
	 * @param ordinal
	 *            the ordinal number
	 * @param year
	 *            the manufacturing year
	 * @param imageName
	 *            the image name
	 * @param price
	 *            the price
	 * @param cent
	 *            the cent
	 * @param salesPrice
	 *            the sales price
	 * @param salesCent
	 *            the sales cent
	 * @param salesLocation
	 *            the sales location
	 * @param salesDate
	 *            the sales date
	 * @param comment
	 *            the comment
	 * @param emptyTable
	 *            the emptyTable indicator
	 */
	public BowlDto(Long id, Integer version, Integer index, Manufacture manufacture, Status status, Timber timber,
			TimberOrigin timberOrigin, Customer customer, Exhibition exhibition, Integer ordinal, String year,
			String imageName, BigDecimal price, BigDecimal cent, BigDecimal salesPrice, BigDecimal salesCent,
			String salesLocation, Date salesDate, String comment, String emptyTable) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.manufacture = manufacture;
		this.status = status;
		this.timber = timber;
		this.timberOrigin = timberOrigin;
		this.customer = customer;
		this.exhibition = exhibition;
		this.ordinal = ordinal;
		this.year = year;
		this.imageName = imageName;
		this.price = price;
		this.cent = cent;
		this.salesPrice = salesPrice;
		this.salesCent = salesCent;
		this.salesLocation = salesLocation;
		this.salesDate = salesDate;
		this.comment = comment;
		this.emptyTable = emptyTable;
	}

	/**
	 * Receives the data of a {@code Bowl} entity instance.
	 *
	 * @param bowl
	 *            the {@code Bowl} instance
	 */
	public void receive(Bowl bowl) {
		if (bowl == null) {
			init();
		} else {
			this.id = bowl.getId();
			this.version = bowl.getVersion();
			this.index = bowl.getIndex();
			this.manufacture = bowl.getManufacture();
			this.status = bowl.getStatus();
			this.timber = bowl.getTimber();
			this.timberOrigin = bowl.getTimberOrigin();
			this.customer = bowl.getCustomer();
			this.exhibition = bowl.getExhibition();
			this.ordinal = bowl.getOrdinal();
			this.year = bowl.getManufacture().getYear();
			this.imageName = bowl.getImageName();
			this.price = bowl.getPrice();
			this.cent = bowl.getCent();
			this.salesPrice = bowl.getSalesPrice();
			this.salesCent = bowl.getSalesCent();
			this.salesLocation = bowl.getSalesLocation();
			this.salesDate = bowl.getSalesDate();
			this.comment = bowl.getComment();
			this.emptyTable = Boolean.FALSE.toString();
		}
	}

	/**
	 * Initiates the properties of {@code BowlDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.index = 0;
		this.manufacture = null;
		this.status = null;
		this.exhibition = null;
		this.timber = null;
		this.timberOrigin = null;
		this.ordinal = 0;
		this.year = null;
		this.imageName = null;
		this.price = new BigDecimal("0");
		this.cent = new BigDecimal("0");
		this.salesPrice = null;
		this.salesCent = null;
		this.salesLocation = null;
		this.salesDate = null;
		this.comment = null;
		this.emptyTable = Boolean.FALSE.toString();
	}

	/**
	 * Checks whether a {@code BowlDto} instance is <em>native</em>, meaning
	 * it's fields are not initialized yet.
	 * 
	 * @return {@code Boolean.TRUE} - the {@code BowlDto} instance hasn't been
	 *         initialized; otherwise {@code Boolean.FALSE}
	 */
	public final Boolean isNative() {
		Boolean result = Boolean.TRUE;
		if (id == null && version == null && index == null && manufacture == null && status == null && timber == null
				&& timberOrigin == null && customer == null && exhibition == null && ordinal == null
				&& year == null & imageName == null && price == null && salesPrice == null && salesCent == null
				&& salesLocation == null && salesDate == null && (comment == null || comment.isEmpty())
				&& emptyTable.equals("false")) {
			// nothing to do
		} else {
			result = Boolean.FALSE;
		}
		return result;
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
	 * @return the manufacture
	 */
	public Manufacture getManufacture() {
		return manufacture;
	}

	/**
	 * @param manufacture
	 *            the manufacture to set
	 */
	public void setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
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
	 * @return the timberOrigin
	 */
	public TimberOrigin getTimberOrigin() {
		return timberOrigin;
	}

	/**
	 * @param timberOrigin
	 *            the timberOrigin to set
	 */
	public void setTimberOrigin(TimberOrigin timberOrigin) {
		this.timberOrigin = timberOrigin;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the exhibition
	 */
	public Exhibition getExhibition() {
		return exhibition;
	}

	/**
	 * @param exhibition
	 *            the exhibition to set
	 */
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
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
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the cent
	 */
	public BigDecimal getCent() {
		return cent;
	}

	/**
	 * @param cent
	 *            the cent to set
	 */
	public void setCent(BigDecimal cent) {
		this.cent = cent;
	}

	/**
	 * @return the salesPrice
	 */
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice
	 *            the sales price to set
	 */
	public void setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the salesCent
	 */
	public BigDecimal getSalesCent() {
		return salesCent;
	}

	/**
	 * @param salesCent
	 *            the sales cent to set
	 */
	public void setSalesCent(BigDecimal salesCent) {
		this.salesCent = salesCent;
	}

	/**
	 * @return the sales location
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
	 * @return the sales date
	 */
	public Date getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate
	 *            the sales date to set
	 */
	public void setSalesDate(Date salesDate) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "BowlDto [id=" + id + ", version=" + version + ", index=" + index + ", manufacture=" + manufacture
				+ ", status=" + status + ", timber=" + timber + ", timberOrigin=" + timberOrigin + ", customer="
				+ customer + ", exhibition=" + exhibition + ", ordinal=" + ordinal + ", year=" + year + ", imageName="
				+ imageName + ", price=" + price + ", cent=" + cent + ", salesPrice=" + salesPrice + ", salesCent="
				+ salesCent + ", salesLocation=" + salesLocation + ", salesDate=" + salesDate + ", comment=" + comment
				+ ", emptyTable=" + emptyTable + "]";
	}

}
