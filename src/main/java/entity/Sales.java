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
import java.math.BigDecimal;
import java.sql.Date;

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

/**
 * Repräsentiert die Verbindung zwischen einer Schale und ihrem Erwerber.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 *
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Sales implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = 4301836632486656065L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SALES_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "SALES_VERSION", nullable = false)
	private Integer version;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SALES_BOWL_ID", nullable = false, updatable = false)
	private Bowl bowl;

	@ManyToOne(optional = false)
	@JoinColumn(name = "SALES_CUSTOMER_ID", nullable = false, updatable = false)
	private Customer customer;

	@NotNull
	@Column(name = "SALES_PRICE", nullable = false)
	private BigDecimal price;

	@NotNull
	@Column(name = "SALES_DATE", nullable = false)
	private Date date;

	@NotNull
	@Column(name = "SALES_LOCATION", nullable = false)
	private String location;

	@NotNull
	@Column(name = "SALES_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 */
	public Sales() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param version
	 *            the version
	 * @param bowl
	 *            the referenced {@code Bowl} instance
	 * @param customer
	 *            the referenced {@code Customer} instance
	 * @param price
	 *            the price
	 * @param date
	 *            the sales date
	 * @param location
	 *            the sales location
	 * @param comment
	 *            the comment
	 */
	public Sales(Integer version, Bowl bowl, Customer customer, BigDecimal price, Date date, String location,
			String comment) {
		super();
		this.version = version;
		this.bowl = bowl;
		this.customer = customer;
		this.price = price;
		this.date = date;
		this.location = location;
		this.comment = comment;
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
	 * @return the {@code Sales} instance
	 */
	public Sales setId(Long id) {
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
	 * @return the {@code Sales} instance
	 */
	public Sales setVersion(Integer version) {
		this.version = version;
		return this;
	}

	/**
	 * @return the bowl
	 */
	public Bowl getBowl() {
		return bowl;
	}

	/**
	 * @param bowl
	 *            the bowl to set
	 * @return the {@code Sales} instance
	 */
	public Sales setBowl(Bowl bowl) {
		this.bowl = bowl;
		return this;
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
	 * @return the {@code Sales} instance
	 */
	public Sales setCustomer(Customer customer) {
		this.customer = customer;
		return this;
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
	 * @return the {@code Sales} instance
	 */
	public Sales setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 * @return the {@code Sales} instance
	 */
	public Sales setDate(Date date) {
		this.date = date;
		return this;
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
	 * @return the {@code Sales} instance
	 */
	public Sales setLocation(String location) {
		this.location = location;
		return this;
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
	 * @return the {@code Sales} instance
	 */
	public Sales setComment(String comment) {
		this.comment = comment;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Sales [id=" + id + ", version=" + version + ", bowl=" + bowl + ", customer=" + customer + ", price="
				+ price + ", date=" + date + ", location=" + location + ", comment=" + comment + "]";
	}

}
