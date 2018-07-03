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

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import entity.Customer;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class CustomerDto implements Dto {

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
	 * the salutation
	 */
	private String salutation;

	/**
	 * the graduation
	 */
	private String graduation;

	/**
	 * the given name
	 */
	private String givenName;

	/**
	 * the family name
	 */
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
	 * the email address
	 */
	@Email
	private String email;

	/**
	 * the street
	 */
	private String street;

	/**
	 * the house number
	 */
	@Size(max = 10)
	private String houseNumber;

	/**
	 * the zip code
	 */
	@Size(min = 5)
	private String zipCode;

	/**
	 * the ciyt
	 */
	private String city;

	/**
	 * the country code
	 */
	@Size(max = 3)
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
	 * the user part of an email address
	 */
	private String emailUser;

	/**
	 * the user part of an email address
	 */
	private String emailDomain;

	/**
	 * the empty table indicator
	 */
	private String emptyTable;

	/**
	 * Constructor.
	 *
	 */
	public CustomerDto() {
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
	 * @param salutation
	 *            the salutation
	 * @param graduation
	 *            the graduation
	 * @param givenName
	 *            the given name
	 * @param familyName
	 *            the family name
	 * @param phone
	 *            the phone number
	 * @param fax
	 *            the fax number
	 * @param mobile
	 *            the mobile number
	 * @param email
	 *            the email address
	 * @param street
	 *            the street
	 * @param houseNumber
	 *            the house number
	 * @param zipCode
	 *            the zip code
	 * @param city
	 *            the city
	 * @param countryCode
	 *            the country code
	 * @param country
	 *            the country name
	 * @param comment
	 *            the comment
	 */
	public CustomerDto(Long id, Integer version, Integer index, String salutation, String graduation, String givenName,
			String familyName, String phone, String fax, String mobile, String email, String street, String houseNumber,
			String zipCode, String city, String countryCode, String country, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.salutation = salutation;
		this.graduation = graduation;
		this.givenName = givenName;
		this.familyName = familyName;
		this.phone = phone;
		this.fax = fax;
		this.mobile = mobile;
		this.email = email;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.city = city;
		this.countryCode = countryCode;
		this.country = country;
		this.comment = comment;
		this.emptyTable = Boolean.FALSE.toString();
		splitEmail(email);
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
	 * @param salutation
	 *            the salutation
	 * @param graduation
	 *            the graduation
	 * @param givenName
	 *            the given name
	 * @param familyName
	 *            the family name
	 * @param phone
	 *            the phone number
	 * @param fax
	 *            the fax number
	 * @param mobile
	 *            the mobile number
	 * @param email
	 *            the email address
	 * @param street
	 *            the street
	 * @param houseNumber
	 *            the house number
	 * @param zipCode
	 *            the zip code
	 * @param city
	 *            the city
	 * @param countryCode
	 *            the country code
	 * @param country
	 *            the country name
	 * @param comment
	 *            the comment
	 * @param emptyTable
	 *            the emptyTable indicator
	 */
	public CustomerDto(Long id, Integer version, Integer index, String salutation, String graduation, String givenName,
			String familyName, String phone, String fax, String mobile, String email, String street, String houseNumber,
			String zipCode, String city, String countryCode, String country, String comment, String emptyTable) {
		super();
		this.id = id;
		this.version = version;
		this.index = index;
		this.salutation = salutation;
		this.graduation = graduation;
		this.givenName = givenName;
		this.familyName = familyName;
		this.phone = phone;
		this.fax = fax;
		this.mobile = mobile;
		this.email = email;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.city = city;
		this.countryCode = countryCode;
		this.country = country;
		this.comment = comment;
		this.emptyTable = emptyTable;
		splitEmail(email);
	}

	/**
	 * Receives the data of a {@code Customer} entity instance.
	 *
	 * @param customer
	 *            the {@code Customer} instance
	 */
	public void receive(Customer customer) {
		if (customer == null) {
			init();
		} else {
			this.id = customer.getId();
			this.version = customer.getVersion();
			this.index = customer.getIndex();
			this.salutation = customer.getSalutation();
			this.graduation = customer.getGraduation();
			this.givenName = customer.getGivenName();
			this.familyName = customer.getFamilyName();
			this.phone = customer.getPhone();
			this.fax = customer.getFax();
			this.mobile = customer.getStreet();
			this.email = customer.getMobile();
			this.street = customer.getEmail();
			this.houseNumber = customer.getHouseNumber();
			this.zipCode = customer.getZipCode();
			this.city = customer.getCity();
			this.countryCode = customer.getCountryCode();
			this.country = customer.getCountry();
			this.comment = customer.getComment();
			this.emptyTable = Boolean.FALSE.toString();
		}
	}

	/**
	 * Initiates the properties of {@code CustomerDto} with default values.
	 */
	private final void init() {
		this.id = 0L;
		this.version = 0;
		this.index = 0;
		this.salutation = "";
		this.graduation = "";
		this.givenName = "";
		this.familyName = "";
		this.phone = "";
		this.fax = "";
		this.mobile = "";
		this.email = "";
		this.street = "";
		this.houseNumber = "";
		this.zipCode = "";
		this.city = "";
		this.countryCode = "";
		this.country = "";
		this.comment = "";
		this.emptyTable = Boolean.FALSE.toString();
	}

	/**
	 * Splits an email address in it's user and domain part.
	 * 
	 * @param email
	 *            the email address
	 */
	private final void splitEmail(String email) {
		if (email != null) {
			String[] parts = email.split("@");
			// now parts[0] contains "example"
			// and parts[1] contains "domain.com"
			emailUser = parts[0];
			emailDomain = parts[1];
		}
	}

	/**
	 * @return the user part of an email address
	 */
	public final String getEmailUser() {
		return emailUser;
	}

	/**
	 * @return the domain part of an email address
	 */
	public final String getEmailDomain() {
		return emailDomain;
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
	 * @param index the index to set
	 */
	public void setIndex(Integer index) {
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	public void setHousenumber(String houseNumber) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "CustomerDto [id=" + id + ", version=" + version + ", index=" + index + ", salutation=" + salutation
				+ ", graduation=" + graduation + ", givenName=" + givenName + ", familyName=" + familyName + ", phone="
				+ phone + ", fax=" + fax + ", mobile=" + mobile + ", email=" + email + ", street=" + street
				+ ", houseNumber=" + houseNumber + ", zipCode=" + zipCode + ", city=" + city + ", countryCode="
				+ countryCode + ", country=" + country + ", comment=" + comment + ", emailUser=" + emailUser
				+ ", emailDomain=" + emailDomain + ", emptyTable=" + emptyTable + "]";
	}

}
