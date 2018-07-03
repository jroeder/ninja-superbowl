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

import dto.SoftwareDto;
import entity.Software;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code SOFTWARE} of the underlying database system.
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
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class SoftwareDao extends AbstractDao {

	/**
	 * Constructor.
	 */
	public SoftwareDao() {
		super();
	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code SoftwareDto} instances
	 */
	@UnitOfWork
	public List<SoftwareDto> listSoftware() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Software> typedQuery = entityManager.createQuery("select s from Software s order by s.id",
				Software.class);
		List<Software> typedQueryResultList = typedQuery.getResultList();

		List<SoftwareDto> softwareDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Software instance found in data store!");
		} else {
			logger.info("{} Software instance(s) found in data store!", typedQueryResultList.size());
			SoftwareDto softwareDto = null;
			for (Software software : typedQueryResultList) {
				logSoftware(software);
				softwareDto = new SoftwareDto(software.getId(), software.getVersion(), software.getVendor(),
						software.getName(), software.getUrl(), software.getVersionnumber(), software.getType(),
						software.getDescription());
				softwareDtos.add(softwareDto);
			}
		}
		return softwareDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param software
	 *            instance of type {@code Software}
	 */
	private final void logSoftware(Software software) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.software"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Software ~~~~~~~~~~~~~~~");
			logger.info("id           : " + software.getId());
			logger.info("version      : " + software.getVersion());
			logger.info("vendor       : " + software.getVendor());
			logger.info("name         : " + software.getName());
			logger.info("url          : " + software.getUrl());
			logger.info("versionnumber: " + software.getVersionnumber());
			logger.info("type         : " + software.getType());
			logger.info("description  : " + software.getDescription());
		}
	}

}
