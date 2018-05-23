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
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Subuser implements Serializable {

	/**
	 * Unique serial version identifier.
	 */
	private static final long serialVersionUID = -1673834139179356769L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBUSER_ID", nullable = false)
	private Long id;

	@Version
	@NotNull
	@Column(name = "SUBUSER_VERSION", nullable = false)
	private Integer version;

	@NotNull
	@Column(name = "SUBUSER_USERID", nullable = false)
	private String userId;

	@NotNull
	@Column(name = "SUBUSER_PASSWORD", nullable = false)
	private String password;

	@NotNull
	@Column(name = "SUBUSER_USERNAME", nullable = false)
	private String userName;

	@NotNull
	@Column(name = "SUBUSER_EMAIL", nullable = false)
	private String email;

	@Column(name = "SUBUSER_LASTLOGIN", nullable = true)
	private Timestamp lastLogin;

	@Column(name = "SUBUSER_LASTLOGOUT", nullable = true)
	private Timestamp lastLogout;

	@NotNull
	@Column(name = "SUBUSER_LOGINCOUNT", nullable = false)
	private Integer loginCount;

	@NotNull
	@Column(name = "SUBUSER_LOCKED", nullable = false)
	private Boolean locked;

	/**
	 * Constructor.
	 */
	public Subuser() {
		super();
	}

	/**
	 * Constructor using properties.
	 * 
	 * @param version
	 *            the version
	 * @param userId
	 *            the user identifier
	 * @param password
	 *            the password
	 * @param userName
	 *            the user name
	 * @param email
	 *            the email address
	 * @param lastLogin
	 *            the last login date/time
	 * @param lastLogout
	 *            the last logout date/time
	 * @param loginCount
	 *            the number of logins
	 * @param locked
	 *            the locked state
	 */
	public Subuser(Integer version, String userId, String password, String userName, String email, Timestamp lastLogin,
			Timestamp lastLogout, Integer loginCount, Boolean locked) {
		super();
		this.version = version;
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.email = email;
		this.lastLogin = lastLogin;
		this.lastLogout = lastLogout;
		this.loginCount = loginCount;
		this.locked = locked;
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
	 * @return the {@code Subuser} instance
	 */
	public Subuser setId(Long id) {
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
	 * @return the {@code Subuser} instance
	 */
	public Subuser setVersion(Integer version) {
		this.version = version;
		return this;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setPassword(String password) {
		this.password = password;
		return this;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setUserName(String userName) {
		this.userName = userName;
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
	 * @return the {@code Subuser} instance
	 */
	public Subuser setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @return the lastLogin
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
		return this;
	}

	/**
	 * @return the lastLogout
	 */
	public Timestamp getLastLogout() {
		return lastLogout;
	}

	/**
	 * @param lastLogout
	 *            the lastLogout to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setLastLogout(Timestamp lastLogout) {
		this.lastLogout = lastLogout;
		return this;
	}

	/**
	 * @return the loginCount
	 */
	public Integer getLoginCount() {
		return loginCount;
	}

	/**
	 * @param loginCount
	 *            the loginCount to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
		return this;
	}

	/**
	 * @return the locked
	 */
	public Boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 * @return the {@code Subuser} instance
	 */
	public Subuser setLocked(Boolean locked) {
		this.locked = locked;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#asString()
	 */
	public String asString() {
		return "Subuser [id=" + id + ", version=" + version + ", userId=" + userId + ", password=" + password
				+ ", userName=" + userName + ", email=" + email + ", lastLogin=" + lastLogin + ", lastLogout="
				+ lastLogout + ", loginCount=" + loginCount + ", locked=" + locked + "]";
	}

}
