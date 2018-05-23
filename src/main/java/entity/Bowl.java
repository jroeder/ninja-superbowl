/**
 * entity/Artifact.java
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.BowlDto;

/**
 * Repräsentiert eine Schale (Bowl), manuell gedrechselt aus dem natürlichen
 * Rohstoff Holz.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 18.04.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Bowl implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -5854137476318859417L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOWL_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "BOWL_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "BOWL_INDEX", nullable = false)
	private Integer index;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "BOWL_MANUFACTURE_ID", nullable = false, updatable = true)
	private Manufacture manufacture;

	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "BOWL_STATUS_ID", nullable = false, updatable = true)
	private Status status;

	@ManyToOne(optional = false)
	@JoinColumn(name = "BOWL_TIMBER_ID", nullable = false, updatable = false)
	private Timber timber;

	@ManyToOne(optional = false)
	@JoinColumn(name = "BOWL_TIMBERORIGIN_ID", nullable = true, updatable = true)
	private TimberOrigin timberOrigin;

	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "BOWL_CUSTOMER_ID", nullable = true, updatable = true)
	private Customer customer;

	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "BOWL_EXHIBITION_ID", nullable = true, updatable = true)
	private Exhibition exhibition;

	@NotNull
	@Column(name = "BOWL_ORDINAL", nullable = false)
	private Integer ordinal;

	@NotNull
	@Column(name = "BOWL_IMAGENAME", nullable = false)
	private String imageName;

	@NotNull
	@Column(name = "BOWL_PRICE", nullable = false)
	private BigDecimal price;

	@Transient
	private BigDecimal cent;

	@Column(name = "BOWL_SALES_PRICE", nullable = true)
	private BigDecimal salesPrice;

	@Transient
	private BigDecimal salesCent;

	@Column(name = "BOWL_SALES_LOCATION", nullable = true)
	private String salesLocation;

	@Column(name = "BOWL_SALES_DATE", nullable = true)
	private Date salesDate;

	@NotNull
	@Column(name = "BOWL_COMMENT", nullable = false)
	private String comment;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bowl")
	private Set<BowlMod> bowlMods = new HashSet<BowlMod>(0);

	/**
	 * Constructor.
	 */
	public Bowl() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param version
	 *            the version
	 * @param index
	 *            the index
	 * @param manufacture
	 *            the referenced {@code Manufacture} instance
	 * @param status
	 *            the referenced {@code Status} instance
	 * @param timber
	 *            the referenced {@code Timber} instance
	 * @param timberOrigin
	 *            the referenced {@code TimberOrigin} instance
	 * @param customer
	 *            the referenced {@code Customer} instance
	 * @param exhibition
	 *            the referenced {@code Exhibition} instance
	 * @param ordinal
	 *            the ordinal number
	 * @param imageName
	 *            the image name
	 * @param price
	 *            the price
	 * @param cent
	 *            the cent value of the price
	 * @param salesPrice
	 *            the sales price
	 * @param salesCent
	 *            the sales cent value of the price
	 * @param salesLocation
	 *            the sales location
	 * @param salesDate
	 *            the sales date
	 * @param comment
	 *            the comment
	 */
	public Bowl(Integer version, Integer index, Manufacture manufacture, Status status, Timber timber,
			TimberOrigin timberOrigin, Customer customer, Exhibition exhibition, Integer ordinal, String imageName,
			BigDecimal price, BigDecimal cent, BigDecimal salesPrice, BigDecimal salesCent, String salesLocation,
			Date salesDate, String comment) {
		super();
		this.version = version;
		this.index = index;
		this.manufacture = manufacture;
		this.status = status;
		this.timber = timber;
		this.timberOrigin = timberOrigin;
		this.customer = customer;
		this.exhibition = exhibition;
		this.ordinal = ordinal;
		this.imageName = imageName;
		this.price = price;
		this.cent = cent;
		this.salesPrice = salesPrice;
		this.salesCent = salesCent;
		this.salesLocation = salesLocation;
		this.salesDate = salesDate;
		this.comment = comment;
	}

	/**
	 * Constructor using {@code BowlDto} instance.
	 *
	 * @param bowlDto
	 *            instance of type {@code BowlDto}
	 */
	public Bowl(BowlDto bowlDto) {
		if (bowlDto.getId() != null) {
			this.id = bowlDto.getId();
		}
		this.version = bowlDto.getVersion();
		this.index = bowlDto.getIndex();
		this.manufacture = bowlDto.getManufacture();
		this.status = bowlDto.getStatus();
		this.timber = bowlDto.getTimber();
		this.timberOrigin = bowlDto.getTimberOrigin();
		this.customer = bowlDto.getCustomer();
		this.exhibition = bowlDto.getExhibition();
		this.ordinal = bowlDto.getOrdinal();
		this.imageName = bowlDto.getImageName();
		this.price = bowlDto.getPrice();
		this.cent = bowlDto.getCent();
		this.salesPrice = bowlDto.getSalesPrice();
		this.salesCent = bowlDto.getSalesCent();
		this.salesLocation = bowlDto.getSalesLocation();
		this.salesDate = bowlDto.getSalesDate();
		this.comment = bowlDto.getComment();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the unique technical identifier to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setId(Long id) {
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
	 * @return the {@code Bowl} instance
	 */
	public Bowl setVersion(Integer version) {
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
	 *            the {@code Manufacture} to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setManufacture(Manufacture manufacture) {
		this.manufacture = manufacture;
		return this;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the {@code Status} to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setStatus(Status status) {
		this.status = status;
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
	 * @return the {@code Timber} instance
	 */
	public Timber getTimber() {
		return timber;
	}

	/**
	 * @param timber
	 *            the {@code Timber} to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setTimber(Timber timber) {
		this.timber = timber;
		return this;
	}

	/**
	 * @return the {@code TimberOrigin} instance
	 */
	public TimberOrigin getTimberOrigin() {
		return timberOrigin;
	}

	/**
	 * @param timberOrigin
	 *            the {@code TimberOrigin} to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setTimberOrigin(TimberOrigin timberOrigin) {
		this.timberOrigin = timberOrigin;
		return this;
	}

	/**
	 * @return the ordinal number
	 */
	public Integer getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal
	 *            the ordinal number set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
		return this;
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
	 * @return the {@code Bowl} instance
	 */
	public Bowl setImageName(String imageName) {
		this.imageName = imageName;
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
	 * @return the {@code Bowl} instance
	 */
	public Bowl setPrice(BigDecimal price) {
		this.price = price;
		return this;
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
	 * @return the {@code Bowl} instance
	 */
	public Bowl setCent(BigDecimal cent) {
		this.cent = cent;
		return this;
	}

	/**
	 * @return the sales price
	 */
	public BigDecimal getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice
	 *            the sales price to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setSalesPrice(BigDecimal salesPrice) {
		this.salesPrice = salesPrice;
		return this;
	}

	/**
	 * @return the sales cent
	 */
	public BigDecimal getSalesCent() {
		return salesCent;
	}

	/**
	 * @param salesCent
	 *            the sales cent to set
	 * @return the {@code Bowl} instance
	 */
	public Bowl setSalesCent(BigDecimal salesCent) {
		this.salesCent = salesCent;
		return this;
	}

	/**
	 * @return the salesLocation
	 */
	public String getSalesLocation() {
		return salesLocation;
	}

	/**
	 * @param salesLocation
	 *            the salesLocation to set
	 */
	public void setSalesLocation(String salesLocation) {
		this.salesLocation = salesLocation;
	}

	/**
	 * @return the salesDate
	 */
	public Date getSalesDate() {
		return salesDate;
	}

	/**
	 * @param salesDate
	 *            the salesDate to set
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
	 * @return the {@code Bowl} instance
	 */
	public Bowl setComment(String comment) {
		this.comment = comment;
		return this;
	}

	/**
	 * @return the list of mapped {@code BowlMod} instances
	 */
	public Set<BowlMod> getBowlMods() {
		return this.bowlMods;
	}

	/**
	 * @param bowlMods
	 *            the {@code BowlMod} instances to set
	 */
	public void setBowlMods(Set<BowlMod> bowlMods) {
		this.bowlMods = bowlMods;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Bowl [id=" + id + ", version=" + version + ", index=" + index + ", manufacture=" + manufacture
				+ ", status=" + status + ", timber=" + timber + ", timberOrigin=" + timberOrigin + ", customer="
				+ customer + ", exhibition=" + exhibition + ", ordinal=" + ordinal + ", imageName=" + imageName
				+ ", price=" + price + ", cent=" + cent + ", salesPrice=" + salesPrice + ", salesCent=" + salesCent
				+ ", salesLocation=" + salesLocation + ", salesDate=" + salesDate + ", comment=" + comment
				+ ", bowlMods=" + bowlMods + "]";
	}

}
