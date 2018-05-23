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
package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dto.SettingDto;
import entity.Setting;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code SETTING} of the underlying database system.
 * <p>
 * The Data Access Object (DAO) pattern is now a widely accepted mechanism to
 * abstract away the details of persistence in an application.<br>
 * The idea is that instead of having the domain logic communicate directly with
 * the database, file system, web service, or whatever persistence mechanism
 * your application uses, the domain logic speaks to a DAO layer instead. This
 * DAO layer then communicates with the underlying persistence system or
 * service.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 09.11.2017 mbsusr01
 */
public class SettingDao extends AbstractDao {

	/**
	 * Constructor.
	 */
	public SettingDao() {
		super();
	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code SettingDto} instances
	 */
	@UnitOfWork
	public List<SettingDto> listSetting() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Setting> typedQuery = entityManager.createQuery("select s from Setting s order by s.id",
				Setting.class);
		List<Setting> typedQueryResultList = typedQuery.getResultList();

		List<SettingDto> settingDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Setting instance found in data store!");
		} else {
			logger.info("{} Setting instance(s) found in data store!", typedQueryResultList.size());
			SettingDto settingDto = null;
			for (Setting setting : typedQueryResultList) {
				logSetting(setting);
				settingDto = new SettingDto(setting.getId(), setting.getVersion(), setting.getParamName(),
						setting.getParamValue(), setting.getComment());
				settingDtos.add(settingDto);
			}
		}
		return settingDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param setting
	 *            instance of type {@code Setting}
	 */
	private final void logSetting(Setting setting) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.setting"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Setting ~~~~~~~~~~~~~~~");
			logger.info("id        : " + setting.getId());
			logger.info("version   : " + setting.getVersion());
			logger.info("paramName : " + setting.getParamName());
			logger.info("paramValue: " + setting.getParamValue());
			logger.info("comment   : " + setting.getComment());
		}
	}

}
