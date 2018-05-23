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

import dto.RoadmapDto;
import entity.Roadmap;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code ROADMAP} of the underlying database system.
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
 * ninja-superbowl 20.04.2017 mbsusr01 
 */
public class RoadmapDao extends AbstractDao {

	/**
	 * Constructor.
	 */
	public RoadmapDao() {
		super();
	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code RoadmapDto} instances
	 */
	@UnitOfWork
	public List<RoadmapDto> listRoadmap() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Roadmap> typedQuery = entityManager.createQuery("select r from Roadmap r order by r.id",
				Roadmap.class);
		List<Roadmap> typedQueryResultList = typedQuery.getResultList();

		List<RoadmapDto> roadmapDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Roadmap instance found in data store!");
		} else {
			logger.info("{} Roadmap instance(s) found in data store!", typedQueryResultList.size());
			RoadmapDto roadmapDto = null;
			for (Roadmap roadmap : typedQueryResultList) {
				logRoadmap(roadmap);
				roadmapDto = new RoadmapDto(roadmap.getId(), roadmap.getVersion(), roadmap.getFeature(),
						roadmap.getStatus(), roadmap.getComment(), roadmap.getVersionnumber());
				roadmapDtos.add(roadmapDto);
			}
		}
		return roadmapDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param roadmap
	 *            instance of type {@code Roadmap}
	 */
	private final void logRoadmap(Roadmap roadmap) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.roadmap"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Roadmap ~~~~~~~~~~~~~~~");
			logger.info("id           : " + roadmap.getId());
			logger.info("version      : " + roadmap.getVersion());
			logger.info("feature      : " + roadmap.getFeature());
			logger.info("status       : " + roadmap.getStatus());
			logger.info("comment      : " + roadmap.getComment());
			logger.info("versionnumber: " + roadmap.getVersionnumber());
		}
	}

}
