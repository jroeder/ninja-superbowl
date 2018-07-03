/**
 * entity/Actorjava
 */
package entity;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.CustomerDto;

/**
 * Repräsentiert den Käufer einer Schale (Bowl).
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 *
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Customer implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -830933549594511164L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "CUSTOMER_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "CUSTOMER_INDEX", nullable = false)
	private Integer index;

	@NotNull
	@Column(name = "CUSTOMER_SALUTATION", nullable = false)
	private String salutation;

	@NotNull
	@Column(name = "CUSTOMER_GRADUATION", nullable = false)
	private String graduation;

	@NotNull
	@Column(name = "CUSTOMER_GIVENNAME", nullable = false)
	private String givenName;

	@NotNull
	@Column(name = "CUSTOMER_FAMILYNAME", nullable = false)
	private String familyName;

	@NotNull
	@Column(name = "CUSTOMER_PHONE", nullable = false)
	private String phone;

	@NotNull
	@Column(name = "CUSTOMER_FAX", nullable = false)
	private String fax;

	@NotNull
	@Column(name = "CUSTOMER_MOBILE", nullable = false)
	private String mobile;

	@NotNull
	@Column(name = "CUSTOMER_EMAIL", nullable = false)
	private String email;

	@NotNull
	@Column(name = "CUSTOMER_STREET", nullable = false)
	private String street;

	@NotNull
	@Column(name = "CUSTOMER_HOUSENUMBER", nullable = false)
	private String houseNumber;

	@NotNull
	@Column(name = "CUSTOMER_ZIPCODE", nullable = false)
	private String zipCode;

	@NotNull
	@Column(name = "CUSTOMER_CITY", nullable = false)
	private String city;

	@NotNull
	@Column(name = "CUSTOMER_COUNTRYCODE", nullable = false)
	private String countryCode;

	@NotNull
	@Column(name = "CUSTOMER_COUNTRY", nullable = false)
	private String country;

	@NotNull
	@Column(name = "CUSTOMER_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 */
	public Customer() {
		super();
	}

	/**
	 * Constructor using fields.
	 *
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
	 * @param zipcode
	 *            the zip code
	 * @param city
	 *            the city name
	 * @param countryCode
	 *            the country code
	 * @param country
	 *            the country name
	 * @param comment
	 *            the comment
	 */
	public Customer(Integer version, Integer index, String salutation, String graduation, String givenName,
			String familyName, String phone, String fax, String mobile, String email, String street, String houseNumber,
			String zipcode, String city, String countryCode, String country, String comment) {
		super();
		this.id = null;
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
		this.zipCode = zipcode;
		this.city = city;
		this.countryCode = countryCode;
		this.country = country;
		this.comment = comment;
	}

	/**
	 * Constructor using {@code CustomerDto}.
	 *
	 * @param customerDto
	 *            the {@code CustomerDto} instance
	 */
	public Customer(CustomerDto customerDto) {
		this.id = customerDto.getId();
		this.version = customerDto.getVersion();
		this.index = customerDto.getIndex();
		this.salutation = customerDto.getSalutation();
		this.graduation = customerDto.getGraduation();
		this.givenName = customerDto.getGivenName();
		this.familyName = customerDto.getFamilyName();
		this.phone = customerDto.getPhone();
		this.fax = customerDto.getFax();
		this.mobile = customerDto.getMobile();
		this.email = customerDto.getEmail();
		this.street = customerDto.getStreet();
		this.houseNumber = customerDto.getHouseNumber();
		this.zipCode = customerDto.getZipCode();
		this.city = customerDto.getCity();
		this.countryCode = customerDto.getCountryCode();
		this.country = customerDto.getCountry();
		this.comment = customerDto.getComment();
	}

	/**
	 * Convenience method to set all parts of the name.
	 *
	 * @param givenName
	 *            the given name
	 * @param familyName
	 *            the family name
	 */
	public void setName(String givenName, String familyName) {
		this.givenName = givenName;
		this.familyName = familyName;
	}

	/**
	 * Convenience method to get the full name.
	 * 
	 * @return the full customer name
	 */
	public String getName() {
		StringJoiner sj = new StringJoiner(" ");
		sj.add(this.givenName).add(this.familyName);
		return sj.toString();
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
	 * @return the {@code Customer} instance
	 */
	public Customer setId(Long id) {
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
	 * @return the {@code Customer} instance
	 */
	public Customer setVersion(Integer version) {
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
	 * @return the {@code Customer} instance
	 */
	public Customer setIndex(Integer index) {
		this.index = index;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setSalutation(String salutation) {
		this.salutation = salutation;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setGraduation(String graduation) {
		this.graduation = graduation;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setGivenName(String givenName) {
		this.givenName = givenName;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setFamilyName(String familyName) {
		this.familyName = familyName;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setPhone(String phone) {
		this.phone = phone;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setFax(String fax) {
		this.fax = fax;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setMobile(String mobile) {
		this.mobile = mobile;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setEmail(String email) {
		this.email = email;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setStreet(String street) {
		this.street = street;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
		return this;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 * @return the {@code Customer} instance
	 */
	public Customer setZipCode(String zipcode) {
		this.zipCode = zipcode;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setCity(String city) {
		this.city = city;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		return this;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setCountry(String country) {
		this.country = country;
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
	 * @return the {@code Customer} instance
	 */
	public Customer setComment(String comment) {
		this.comment = comment;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Customer [id=" + id + ", version=" + version + ", index=" + index + ", salutation=" + salutation
				+ ", graduation=" + graduation + ", givenName=" + givenName + ", familyName=" + familyName + ", phone="
				+ phone + ", fax=" + fax + ", mobile=" + mobile + ", email=" + email + ", street=" + street
				+ ", houseNumber=" + houseNumber + ", zipCode=" + zipCode + ", city=" + city + ", countryCode="
				+ countryCode + ", country=" + country + ", comment=" + comment + "]";
	}

}
