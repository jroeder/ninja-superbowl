/**
 * 
 */
package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import dto.SettingDto;

/**
 * Repräsentiert die zur Programmierung der Superbowl-Applikation benötigten
 * dispositiven Parameter.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 09.11.2017 mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Setting implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -2781035235472728640L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SETTING_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "SETTING_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "SETTING_PARAM_NAME", nullable = false)
	private String paramName;

	@NotNull
	@Column(name = "SETTING_PARAM_VALUE", nullable = false)
	private String paramValue;

	@NotNull
	@Column(name = "SETTING_COMMENT", nullable = false)
	private String comment;

	/**
	 * Constructor.
	 */
	public Setting() {
		super();
	}

	/**
	 * Constructor using properties.
	 *
	 * @param id
	 *            the technical identifier
	 * @param version
	 *            the version
	 * @param paramName
	 *            the parameter name
	 * @param paramValue
	 *            the parameter value
	 * @param description
	 *            the description
	 */
	public Setting(Long id, Integer version, String paramName, String paramValue, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.comment = comment;
	}

	/**
	 * Constructor using data transfer object.
	 *
	 * @param settingDto
	 *            the {@code SettingDto} instance
	 */
	public Setting(SettingDto settingDto) {
		super();
		this.id = settingDto.getId();
		this.version = settingDto.getVersion();
		this.paramName = settingDto.getParamName();
		this.paramValue = settingDto.getParamValue();
		this.comment = settingDto.getComment();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
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
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}

	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String asString() {
		return "Setting [id=" + id + ", version=" + version + ", paramName=" + paramName + ", paramValue=" + paramValue
				+ ", comment=" + comment + "]";
	}
	
}
