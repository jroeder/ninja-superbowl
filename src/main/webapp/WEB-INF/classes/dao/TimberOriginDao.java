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

import dto.TimberOriginDto;
import entity.TimberOrigin;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * <code>TIMBER</code> of the underlying database system.
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
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
public class TimberOriginDao extends AbstractDao {

	/**
	 * Constructor.
	 */
	public TimberOriginDao() {
		super();
	}

	/**
	 * Delivers the max index number of a persisted {@code TimberOrigin}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM TimberOrigin t WHERE t.index = SELECT max(x.index) FROM TimberOrigin
	 * x;
	 *
	 * @return a persisted {@code TimberOrigin} as a data transfer object
	 */
	@UnitOfWork
	public TimberOriginDto getTimberOriginMaxIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<TimberOrigin> typedQuery = entityManager
				.createQuery("select t from TimberOrigin t where t.index = (select max(x.index) from TimberOrigin x)", TimberOrigin.class);
		TimberOrigin timberOrigin = (TimberOrigin) typedQuery.getSingleResult();

		TimberOriginDto timberOriginDto = new TimberOriginDto();
		timberOriginDto.receive(timberOrigin);

		return timberOriginDto;

	}

	/**
	 * Delivers a persisted {@code TimberOrigin} instance by it's unique
	 * technical identifier.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code TimberOrigin}
	 * @return the {@code TimberOriginDto} instance
	 */
	@UnitOfWork
	public TimberOriginDto getTimberOriginById(@NotNull Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<TimberOrigin> typedQuery = entityManager.createQuery("select x from TimberOrigin x where x.id = :id",
				TimberOrigin.class);

		TimberOrigin timberOrigin = (TimberOrigin) typedQuery.setParameter("id", id).getSingleResult();

		TimberOriginDto timberOriginDto = new TimberOriginDto();
		timberOriginDto.receive(timberOrigin);

		return timberOriginDto;

	}

	/**
	 * Delivers a persisted {@code TimberOrigin} instance by it's unique
	 * technical identifier.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code TimberOrigin}
	 * @return the {@code TimberOriginDto} instance
	 */
	@UnitOfWork
	public TimberOriginDto getTimberOriginById(@NotNull String id) {
		return this.getTimberOriginById(new Long(id));
	}

	/**
	 * Delivers all persisted {@code TimberOrigin} instances.
	 *
	 * @return the list of {@code TimberOriginDto} instances
	 */
	@UnitOfWork
	public List<TimberOriginDto> listTimberOrigin() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<TimberOrigin> typedQuery = entityManager.createQuery("select x from TimberOrigin x order by x.id",
				TimberOrigin.class);
		List<TimberOrigin> typedQueryResultList = typedQuery.getResultList();

		List<TimberOriginDto> timberOriginDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No TimberOrigin instance found in data store!");
		} else {
			logger.info("{} TimberOrigin instance(s) found in data store!", typedQueryResultList.size());
			TimberOriginDto timberOriginDto = null;
			for (TimberOrigin timberOrigin : typedQueryResultList) {
				logTimberOrigin(timberOrigin);
				timberOriginDto = new TimberOriginDto(timberOrigin.getId(), timberOrigin.getVersion(),
						timberOrigin.getTimber(), timberOrigin.getIndex(), timberOrigin.getCity(),
						timberOrigin.getLocation(), timberOrigin.getLocationText(), timberOrigin.getCutdown(),
						timberOrigin.getComment());
				timberOriginDtoList.add(timberOriginDto);
			}
		}
		return timberOriginDtoList;

	}

	/**
	 * Delivers all persisted {@code TimberOrigin} instances related to the
	 * specified {@code Timber} identifier.
	 *
	 * @param timberId
	 *            the unique technical identifier of a {@code Timber}
	 * @return the list of {@code TimberOriginDto} instances
	 */
	@UnitOfWork
	public List<TimberOriginDto> listTimberOriginByTimberId(@NotNull Long timberId) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<TimberOrigin> typedQuery = entityManager.createQuery(
				"select x from TimberOrigin x where x.timber.id = :id order by x.index", TimberOrigin.class);

		List<TimberOrigin> typedQueryResultList = typedQuery.setParameter("id", timberId).getResultList();

		List<TimberOriginDto> timberOriginDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No timberOrigin found in data store!");
		} else {
			logger.info("{} timberOrigin found in data store!", typedQueryResultList.size());
			for (TimberOrigin timberOrigin : typedQueryResultList) {
				logTimberOrigin(timberOrigin);
				timberOriginDtos.add(new TimberOriginDto(timberOrigin));
			}
		}
		return timberOriginDtos;

	}

	/**
	 * Persists a new {@code TimberOrigin} in the data store.
	 *
	 * @param timberOriginDto
	 *            the {@code TimberOriginDto} instance
	 */
	@Transactional
	public void register(TimberOriginDto timberOriginDto) {

		EntityManager entityManager = entityManagerProvider.get();

		TimberOrigin timberOrigin = new TimberOrigin(timberOriginDto);
		entityManager.persist(timberOrigin);

		entityManager.flush();

		logger.info("New registered timberOrigin id: {}", timberOrigin.getId());

	}

	/**
	 * Logging of a retrieved {@code TimberOrigin} instance.
	 *
	 * @param timberOrigin
	 *            instance of type {@code TimberOrigin}
	 */
	private final void logTimberOrigin(TimberOrigin timberOrigin) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.timber.origin"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ TimberOrigin ~~~~~~~~~~~~~~~");
			logger.info("id              : " + timberOrigin.getId());
			logger.info("version         : " + timberOrigin.getVersion());
			logger.info("timber          : " + timberOrigin.getTimber());
			logger.info("index           : " + timberOrigin.getIndex());
			logger.info("city            : " + timberOrigin.getCity());
			logger.info("location        : " + timberOrigin.getLocation());
			logger.info("locationText    : " + timberOrigin.getLocationText());
			logger.info("cutdown         : " + timberOrigin.getCutdown());
			logger.info("comment         : " + timberOrigin.getComment());
		}
	}

}
