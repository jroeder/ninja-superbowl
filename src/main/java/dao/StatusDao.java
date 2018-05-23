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
import javax.validation.constraints.NotNull;

import com.google.inject.persist.Transactional;

import dto.StatusDto;
import dto.StatusListDto;
import entity.Status;
import ninja.jpa.UnitOfWork;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class StatusDao extends AbstractDao {

	/**
	 * Insert method description here...
	 *
	 * @return the {@code StatusListDto} instance
	 */
	@UnitOfWork
	public StatusListDto getAllStatus() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Status> query = entityManager.createQuery("select s from Status s order by s.index", Status.class);
		List<Status> statiList = query.getResultList();

		StatusListDto statiDto = new StatusListDto();
		statiDto.setStatusList(statiList);

		return statiDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code StatusDto} instances
	 */
	@UnitOfWork
	public List<StatusDto> getAllStati() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Status> query = entityManager.createQuery("select s from Status s order by s.index", Status.class);
		List<Status> statusList = query.getResultList();
		// logger.info("GeoRegionDao.getAllGeoRegion() -> No. of geo region
		// item(s) selected: " + statusList.size());

		List<StatusDto> statusDtos = new ArrayList<>();

		if (statusList.isEmpty()) {
			logger.info("No status found in data store!");
		} else {
			Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.status"));
			if (isLog) {
				logger.info("{} stati found in data store!", statusList.size());
			}
			StatusDto statusDto = null;
			for (Status status : statusList) {
				logStatus(status);
				statusDto = convert(status);
				statusDtos.add(statusDto);
			}
		}

		return statusDtos;

	}

	/**
	 * Delivers the max index number of a persisted {@code Status}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM status s WHERE s.index = SELECT max(s.index) FROM status
	 * b1;
	 *
	 * @return a persisted {@code Status} as a data transfer object
	 */
	@UnitOfWork
	public StatusDto getStatusMaxIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Status> typedQuery = entityManager.createQuery(
				"select s from Status s where s.index = (select max(x.index) from Status x)", Status.class);
		Status status = (Status) typedQuery.getSingleResult();

		StatusDto statusDto = new StatusDto();
		statusDto.receive(status);

		return statusDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the id
	 * @return the {@code StatusDto} instance
	 */
	@UnitOfWork
	public StatusDto getStatusById(@NotNull Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Status> typedQuery = entityManager.createQuery("select s from Status s where s.id = :id",
				Status.class);
		// typedQuery.setParameter("id", id);
		Status status = (Status) typedQuery.setParameter("id", id).getSingleResult();

		return convert(status);

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the id
	 * @return the {@code StatusDto} instance
	 */
	@UnitOfWork
	public StatusDto getStatusById(@NotNull String id) {
		return this.getStatusById(new Long(id));
	}

	/**
	 * Insert method description here...
	 *
	 * @param code
	 *            the code
	 * @return the {@code StatusDto} instance
	 */
	@UnitOfWork
	public StatusDto getStatusByCode(@NotNull String code) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Status> typedQuery = entityManager.createQuery("select s from Status s where s.code = :code",
				Status.class);
		// typedQuery.setParameter("code", code);
		Status status = (Status) typedQuery.setParameter("code", code).getSingleResult();

		return convert(status);

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code StatusDto} instances
	 */
	@UnitOfWork
	public List<StatusDto> listStatus() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Status> typedQuery = entityManager.createQuery("select s from Status s order by s.index",
				Status.class);
		List<Status> typedQueryResultList = typedQuery.getResultList();

		List<StatusDto> statusDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Status instance found in data store!");
		} else {
			Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.status"));
			if (isLog) {
				logger.info("{} Status instance(s) found in data store!", typedQueryResultList.size());
			}
			StatusDto statusDto = null;
			for (Status status : typedQueryResultList) {
				logStatus(status);
				statusDto = convert(status);
				statusDtos.add(statusDto);
			}
		}

		return statusDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param statusDto
	 *            the {@code StatusDto} instance
	 */
	@Transactional
	public void register(StatusDto statusDto) {

		EntityManager entityManager = entityManagerProvider.get();

		Status status = new Status(statusDto);
		entityManager.persist(status);

		entityManager.flush();

		logger.info("New registered status id: {}", status.getId());

	}

	/**
	 * Insert method description here...
	 *
	 * @param status
	 *            the {@code Status} instance
	 * @return the {@code StatusDto} instance
	 */
	private final StatusDto convert(Status status) {
		return new StatusDto(status.getId(), status.getVersion(), status.getIndex(), status.getCode(), status.getText(),
				status.getComment());
	}

	/**
	 * Insert method description here...
	 *
	 * @param status
	 *            the {@code Status} instance
	 */
	private final void logStatus(Status status) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.status"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Status ~~~~~~~~~~~~~~~");
			logger.info("id         : " + status.getId());
			logger.info("version    : " + status.getVersion());
			logger.info("index      : " + status.getIndex());
			logger.info("text       : " + status.getText());
			logger.info("comment    : " + status.getComment());
		}
	}

}
