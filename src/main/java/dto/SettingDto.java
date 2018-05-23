/**
 * 
 */
package dto;

import javax.validation.constraints.Size;

import entity.Setting;

/**
 * The {@code Setting} Data Transfer Object implementation class.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 09.11.2017 mbsusr01
 */
public class SettingDto implements Dto {

	/**
	 * the technical identifier
	 */
	private Long id;

	/**
	 * the version
	 */
	private Integer version;

	/**
	 * the parameter name
	 */
	@Size(max = 25)
	private String paramName;

	/**
	 * the parameter value
	 */
	@Size(max = 35)
	private String paramValue;

	/**
	 * the comment
	 */
	@Size(max = 128)
	private String comment;

	/**
	 * Constructor.
	 */
	public SettingDto() {
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
	 * @param comment
	 *            the comment
	 */
	public SettingDto(Long id, Integer version, String paramName, String paramValue, String comment) {
		super();
		this.id = id;
		this.version = version;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.comment = comment;
	}

	/**
	 * Receives the data of a {@code Setting} entity instance.
	 *
	 * @param setting
	 *            the {@code Setting} instance
	 */
	public void receive(Setting setting) {
		this.id = setting.getId();
		this.version = setting.getVersion();
		this.paramName = setting.getParamName();
		this.paramValue = setting.getParamValue();
		this.comment = setting.getComment();
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

}
