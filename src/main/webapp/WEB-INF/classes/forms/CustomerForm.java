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

import dto.CustomerDto;

/**
 * Class to hold input form data of a {@code Customer}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public class CustomerForm implements Form {

	/**
	 * the unique technical identifier of a {@code Customer}
	 */
	private String id;

	/**
	 * the version number of a {@code Customer}
	 */
	private String version;

	/**
	 * the {@code Customer} index
	 */
	// @NotBlank(message = "{customer.index.blank}")
	// @NotNull(message = "{customer.index.null}")
	private String index;

	/**
	 * the salutation
	 */
	@NotBlank(message = "{customer.salutation.blank}")
	// @NotNull(message = "{customer.salutation.null}")
	// @Pattern(regexp = "MISTER|MISSES", message = "{salutation.invalid}")
	private String salutation;

	/**
	 * the graduation
	 */
	private String graduation;

	/**
	 * the given name
	 */
	@NotBlank(message = "{customer.given.name.blank}")
	// @NotNull(message = "{customer.given.name.null}")
	private String givenName;

	/**
	 * the family name
	 */
	@NotBlank(message = "{customer.family.name.blank}")
	// @NotNull(message = "{customer.family.name.null}")
	private String familyName;

	/**
	 * the phone number
	 */
	private String phone;

	/**
	 * the fax number
	 */
	private String fax;

	/**
	 * the mobile number
	 */
	private String mobile;

	/**
	 * the user part of a email address
	 */
	@NotBlank(message = "{customer.email.user.blank}")
	// @NotNull(message = "{customer.email.user.null}")
	private String emailUser;

	/**
	 * the domain part of a email address
	 */
	@NotBlank(message = "{customer.email.domain.blank}")
	// @NotNull(message = "{customer.email.domain.null}")
	private String emailDomain;

	/**
	 * the street name
	 */
	private String street;

	/**
	 * the house number
	 */
	private String houseNumber;

	/**
	 * the city zip code
	 */
	private String zipCode;

	/**
	 * the city name
	 */
	private String city;

	/**
	 * the country code
	 */
	private String countryCode;

	/**
	 * the country name
	 */
	private String country;

	/**
	 * the comment
	 */
	private String comment;

	/**
	 * Indicates whether a {@code Customer} instance exists already in the
	 * data base table ({@code true}) or not ({@code false})
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 */
	public CustomerForm() {
		super();
	}

	/**
	 * Constructor using {@code CustomerDto}.
	 * 
	 * @param customerDto
	 *            the {@code CustomerDto} instance
	 */
	public CustomerForm(CustomerDto customerDto) {
		super();
		this.id = customerDto.getId().toString();
		this.version = customerDto.getVersion().toString();
		this.index = customerDto.getIndex().toString();
		this.salutation = customerDto.getSalutation();
		this.graduation = customerDto.getGraduation();
		this.givenName = customerDto.getGivenName();
		this.familyName = customerDto.getFamilyName();
		this.phone = customerDto.getPhone();
		this.fax = customerDto.getFax();
		this.mobile = customerDto.getMobile();
		this.emailUser = customerDto.getEmailUser();
		this.emailDomain = customerDto.getEmailDomain();
		this.street = customerDto.getStreet();
		this.houseNumber = customerDto.getHouseNumber();
		this.zipCode = customerDto.getZipCode();
		this.city = customerDto.getCity();
		this.countryCode = customerDto.getCountryCode();
		this.country = customerDto.getCountry();
		this.comment = customerDto.getComment();
		this.emptyTable = customerDto.getEmptyTable();
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
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @param salutation
	 *            the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * @return the graduation
	 */
	public String getGraduation() {
		return graduation;
	}

	/**
	 * @param graduation
	 *            the graduation to set
	 */
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @param givenName
	 *            the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @param familyName
	 *            the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the emailUser
	 */
	public String getEmailUser() {
		return emailUser;
	}

	/**
	 * @param emailUser
	 *            the emailUser to set
	 */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	/**
	 * @return the emailDomain
	 */
	public String getEmailDomain() {
		return emailDomain;
	}

	/**
	 * @param emailDomain
	 *            the emailDomain to set
	 */
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber
	 *            the houseNumber to set
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode
	 *            the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @param emptyTable the emptyTable to set
	 */
	public void setEmptyTable(String emptyTable) {
		this.emptyTable = emptyTable;
	}

}
