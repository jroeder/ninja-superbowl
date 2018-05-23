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

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import entity.Bowl;
import entity.Customer;

/**
 * Form data fields related to a {@code Sales} instance.
 *
 * @author mbsusr01
 *
 * ninja-superbowl 15.05.2017 mbsusr01 
 *
 */
public class SalesForm implements Form {

	/**
	 * the unique technical identifier of a {@code Sales}
	 */
	private String id;

	/**
	 * the version number of a {@code Sales}
	 */
	private String version;

	@NotBlank(message = "{bowl.blank}")
	@NotNull(message = "{bowl.null}")
	private Bowl bowl;

	@NotBlank(message = "{customer.blank}")
	@NotNull(message = "{customer.null}")
	private Customer customer;

	@NotBlank(message = "price.blank}")
	@NotNull(message = "price.null}")
	private Integer price;

	@NotBlank(message = "{date.blank}")
	@NotNull(message = "{date.blank}")
	private Date date;

	private String location;

	private String comment;

	/**
	 * Insert Constructor description here...
	 *
	 */
	public SalesForm() {
		super();
	}

	/**
	 * @return the bowl
	 */
	public Bowl getBowl() {
		return bowl;
	}

	/**
	 * @param bowl the bowl to set
	 */
	public void setBowl(Bowl bowl) {
		this.bowl = bowl;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
