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

import com.google.inject.persist.Transactional;

import dto.BotanicSystemDto;
import dto.BotanicSystemListDto;
import entity.BotanicSystem;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code BOTANICSYSTEM} of the underlying database system.
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
 */
public class BotanicSystemDao extends AbstractDao {

	/**
	 * Insert method description here...
	 * 
	 * @return {@code BotanicSystemListDto} instance
	 */
	@UnitOfWork
	public BotanicSystemListDto getAllBotanicSystem() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> query = entityManager.createQuery("select x from BotanicSystem x",
				BotanicSystem.class);
		List<BotanicSystem> botanicSystems = query.getResultList();

		BotanicSystemListDto botanicSystemsDto = new BotanicSystemListDto();
		botanicSystemsDto.setBotanicSystems(botanicSystems);

		return botanicSystemsDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @return List of {@code BotanicSystemDto} instances
	 */
	@UnitOfWork
	public List<BotanicSystemDto> getAllBotanicSystems() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> query = entityManager.createQuery("select x from BotanicSystem x",
				BotanicSystem.class);
		List<BotanicSystem> botanicSystemList = query.getResultList();
		logger.info("BotanicSystemDao.getAllBotanicSystems() -> no of botanic system item(s) selected: "
				+ botanicSystemList.size());

		List<BotanicSystemDto> botanicSystemDtos = new ArrayList<>();

		if (botanicSystemList.isEmpty()) {
			logger.info("No botanic system found in data store!");
		} else {
			logger.info("{} botanic system(s) found in data store!", botanicSystemList.size());
			BotanicSystemDto botanicSystemDto = null;
			for (BotanicSystem botanicSystem : botanicSystemList) {
				logBotanicSystem(botanicSystem);
				botanicSystemDto = new BotanicSystemDto(botanicSystem.getId(), botanicSystem.getVersion(),
						botanicSystem.getOrdinal(), botanicSystem.getOrderIndex(), botanicSystem.getFamilyIndex(),
						botanicSystem.getSubFamilyIndex(), botanicSystem.getOrder(), botanicSystem.getFamily(),
						botanicSystem.getSubFamily());
				botanicSystemDtos.add(botanicSystemDto);
			}
		}

		return botanicSystemDtos;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param id
	 *            the unique technical identifier
	 * @return {@code BotanicSystemDto} instance
	 */
	@UnitOfWork
	public BotanicSystemDto getBotanicSystemById(Long id) {

		BotanicSystemDto botanicSystemDto = new BotanicSystemDto();

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> typedQuery = entityManager
				.createQuery("select x from BotanicSystem x where x.id = :id", BotanicSystem.class);

		BotanicSystem botanicSystem = (BotanicSystem) typedQuery.setParameter("id", id).getSingleResult();

		botanicSystemDto = new BotanicSystemDto(botanicSystem.getId(), botanicSystem.getVersion(),
				botanicSystem.getOrdinal(), botanicSystem.getOrderIndex(), botanicSystem.getFamilyIndex(),
				botanicSystem.getSubFamilyIndex(), botanicSystem.getOrder(), botanicSystem.getFamily(),
				botanicSystem.getSubFamily());

		return botanicSystemDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param id
	 *            the unique technical identifier
	 * @return {@code BotanicSystemDto} instance
	 */
	@UnitOfWork
	public BotanicSystemDto getBotanicSystemById(String id) {
		return this.getBotanicSystemById(new Long(id));
	}

	/**
	 * Insert method description here...
	 * 
	 * @param order
	 *            the botanical order
	 * @return {@code BotanicSystemDto} instance
	 */
	@UnitOfWork
	public String getBotanicSystemByOrder(String order) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<String> typedQuery = entityManager
				.createQuery("select distinct(x.order) from BotanicSystem x where x.order = :order", String.class);

		typedQuery.setParameter("order", order);
		String botanicSystemOrder = (String) typedQuery.getSingleResult();

		return botanicSystemOrder;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param family
	 *            the botanical family
	 * @return {@code BotanicSystemDto} instance
	 */
	@UnitOfWork
	public String getBotanicSystemByFamily(String family) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<String> typedQuery = entityManager
				.createQuery("select distinct(x.family) from BotanicSystem x where x.family = :family", String.class);

		typedQuery.setParameter("family", family);
		String botanicSystemFamily = (String) typedQuery.getSingleResult();

		return botanicSystemFamily;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param subFamily
	 *            the botanical subFamily
	 * @return {@code BotanicSystemDto} instance
	 */
	@UnitOfWork
	public String getBotanicSystemBySubFamily(String subFamily) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<String> typedQuery = entityManager.createQuery(
				"select distinct(x.subFamily) from BotanicSystem x where x.subFamily = :subFamily", String.class);

		typedQuery.setParameter("subFamily", subFamily);
		String botanicSystemSubFamily = (String) typedQuery.getSingleResult();

		return botanicSystemSubFamily;

	}

	/**
	 * Delivers the max index number of a persisted {@code BotanicSystem}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM BotanicSystem b WHERE b.ordinal = SELECT max(x.ordinal)
	 * FROM BotanicSystem x;
	 *
	 * @return a persisted {@code BotanicSystem} as a data transfer object
	 */
	@UnitOfWork
	public BotanicSystemDto getBotanicSystemMaxOrdinal() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> typedQuery = entityManager.createQuery(
				"select b from BotanicSystem b where b.ordinal = (select max(x.ordinal) from BotanicSystem x)",
				BotanicSystem.class);
		BotanicSystem botanicSystem = (BotanicSystem) typedQuery.getSingleResult();

		BotanicSystemDto botanicSystemDto = new BotanicSystemDto();
		botanicSystemDto.receive(botanicSystem);

		return botanicSystemDto;

	}

	/**
	 * Delivers the max index number of a persisted {@code BotanicSystem}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM BotanicSystem b WHERE b.orderIndex = SELECT
	 * max(x.orderIndex) FROM BotanicSystem x;
	 *
	 * @return a persisted {@code BotanicSystem} as a data transfer object
	 */
	@UnitOfWork
	public BotanicSystemDto getBotanicSystemMaxOrderIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> typedQuery = entityManager.createQuery(
				"select b from BotanicSystem b where b.orderIndex = (select max(x.orderIndex) from BotanicSystem x)",
				BotanicSystem.class);
		BotanicSystem botanicSystem = (BotanicSystem) typedQuery.getSingleResult();

		BotanicSystemDto botanicSystemDto = new BotanicSystemDto();
		botanicSystemDto.receive(botanicSystem);

		return botanicSystemDto;

	}

	/**
	 * Delivers the max index number of a persisted {@code BotanicSystem}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM BotanicSystem b WHERE b.familyIndex = SELECT
	 * max(x.familyIndex) FROM BotanicSystem x;
	 *
	 * @return a persisted {@code BotanicSystem} as a data transfer object
	 */
	@UnitOfWork
	public BotanicSystemDto getBotanicSystemMaxFamilyIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> typedQuery = entityManager.createQuery(
				"select b from BotanicSystem b where b.familyIndex = (select max(x.familyIndex) from BotanicSystem x)",
				BotanicSystem.class);
		BotanicSystem botanicSystem = (BotanicSystem) typedQuery.getSingleResult();

		BotanicSystemDto botanicSystemDto = new BotanicSystemDto();
		botanicSystemDto.receive(botanicSystem);

		return botanicSystemDto;

	}

	/**
	 * Delivers the max index number of a persisted {@code BotanicSystem}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM BotanicSystem b WHERE b.subFamilyIndex = SELECT
	 * max(x.subFamilyIndex) FROM BotanicSystem x;
	 *
	 * @return a persisted {@code BotanicSystem} as a data transfer object
	 */
	@UnitOfWork
	public BotanicSystemDto getBotanicSystemMaxSubFamilyIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> typedQuery = entityManager.createQuery(
				"select b from BotanicSystem b where b.subFamilyIndex = (select max(x.subFamilyIndex) from BotanicSystem x)",
				BotanicSystem.class);
		BotanicSystem botanicSystem = (BotanicSystem) typedQuery.getSingleResult();

		BotanicSystemDto botanicSystemDto = new BotanicSystemDto();
		botanicSystemDto.receive(botanicSystem);

		return botanicSystemDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param order
	 *            the botanical order
	 * @param family
	 *            the botanical family
	 * @param subFamily
	 *            the botanical subFamily
	 * @return {@code BotanicSystemDto} instance
	 */
	@UnitOfWork
	public Long getBotanicSystemId(String order, String family, String subFamily) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Long> typedQuery = entityManager.createQuery(
				"select x.id from BotanicSystem x where x.order = :order and x.family = :family and x.subFamily = :subFamily",
				Long.class);

		typedQuery.setParameter("order", order);
		typedQuery.setParameter("family", family);
		typedQuery.setParameter("subFamily", subFamily);

		Long botanicSystemId = ZERO;

		try {
			botanicSystemId = (Long) typedQuery.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			logger.warn("BotanicSystemDao.getBotanicSystemId::No entity found for query with parameter(s) Order=["
					+ order + "] family=[" + family + "] subFamily=[" + subFamily + "]");
		}

		return botanicSystemId;

	}

	/**
	 * Select all instances of type {@code BotanicSystem}.
	 *
	 * @return List of {@code BotanicSystemDto} instances
	 */
	@UnitOfWork
	public List<BotanicSystemDto> listBotanicSystem() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BotanicSystem> typedQuery = entityManager.createQuery("select x from BotanicSystem x order by x.id",
				BotanicSystem.class);
		List<BotanicSystem> typedQueryResultList = typedQuery.getResultList();

		List<BotanicSystemDto> botanicSystemDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No BotanicSystem instance found in data store!");
		} else {
			logger.info("{} BotanicSystem instance(s) found in data store!", typedQueryResultList.size());
			BotanicSystemDto botanicSystemDto = null;
			for (BotanicSystem botanicSystem : typedQueryResultList) {
				logBotanicSystem(botanicSystem);
				botanicSystemDto = new BotanicSystemDto(botanicSystem.getId(), botanicSystem.getVersion(),
						botanicSystem.getOrdinal(), botanicSystem.getOrderIndex(), botanicSystem.getFamilyIndex(),
						botanicSystem.getSubFamilyIndex(), botanicSystem.getOrder(), botanicSystem.getFamily(),
						botanicSystem.getSubFamily());
				botanicSystemDtoList.add(botanicSystemDto);
			}
		}
		return botanicSystemDtoList;

	}

	/**
	 * Select all distinct order instances of type {@code BotanicSystem}.
	 * <p>
	 * SELECT distinct(x.bs_order) FROM BotanicSystem x ORDER BY x.bs_order;
	 *
	 * @return List of {@code BotanicSystemDto} instances
	 */
	@UnitOfWork
	public List<String> listBotanicSystemOrder() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<String> typedQuery = entityManager
				.createQuery("select distinct(x.order) from BotanicSystem x order by x.order", String.class);
		List<String> typedQueryResultList = typedQuery.getResultList();

		List<String> botanicSystemOrderList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No BotanicSystem order instance found in data store!");
		} else {
			logger.info("{} BotanicSystem order instance(s) found in data store!", typedQueryResultList.size());
			for (String botanicSystemOrder : typedQueryResultList) {
				botanicSystemOrderList.add(botanicSystemOrder);
			}
		}

		return botanicSystemOrderList;

	}

	/**
	 * Select all distinct family instances of type {@code BotanicSystem}.
	 *
	 * @return List of {@code BotanicSystemDto} instances
	 */
	@UnitOfWork
	public List<String> listBotanicSystemFamily() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<String> typedQuery = entityManager
				.createQuery("select distinct(x.family) from BotanicSystem x order by x.family", String.class);
		List<String> typedQueryResultList = typedQuery.getResultList();

		List<String> botanicSystemFamilyList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No BotanicSystem family instance found in data store!");
		} else {
			logger.info("{} BotanicSystem family instance(s) found in data store!", typedQueryResultList.size());
			for (String botanicSystemFamily : typedQueryResultList) {
				botanicSystemFamilyList.add(botanicSystemFamily);
			}
		}

		return botanicSystemFamilyList;

	}

	/**
	 * Select all distinct family instances of type {@code BotanicSystem}.
	 *
	 * @return List of {@code BotanicSystemDto} instances
	 */
	@UnitOfWork
	public List<String> listBotanicSystemSubFamily() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<String> typedQuery = entityManager.createQuery(
				"select distinct(x.subFamily) from BotanicSystem x where x.subFamily != '' order by x.subFamily",
				String.class);
		List<String> typedQueryResultList = typedQuery.getResultList();

		List<String> botanicSystemSubFamilyList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No BotanicSystem subFamily instance found in data store!");
		} else {
			logger.info("{} BotanicSystem subFamily instance(s) found in data store!", typedQueryResultList.size());
			for (String botanicSystemSubFamily : typedQueryResultList) {
				botanicSystemSubFamilyList.add(botanicSystemSubFamily);
			}
		}

		return botanicSystemSubFamilyList;

	}

	/**
	 * Persists a new {@code BotanicSystem} in the data store.
	 *
	 * @param botanicSystemDto
	 *            the {@code BotanicSystemDao} instance
	 */
	@Transactional
	public void register(BotanicSystemDto botanicSystemDto) {

		EntityManager entityManager = entityManagerProvider.get();

		BotanicSystem botanicSystem = new BotanicSystem(botanicSystemDto);
		entityManager.persist(botanicSystem);

		entityManager.flush();

		logger.info("New registered botanicSystem id: {}", botanicSystem.getId());

	}

	/**
	 * Insert method description here...
	 *
	 * @param botanicSystem
	 *            instance of type {@code BotanicSystem}
	 */
	private final void logBotanicSystem(BotanicSystem botanicSystem) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.botanicsystem"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ BotanicSystem ~~~~~~~~~~~~~~~");
			logger.info("id              : " + botanicSystem.getId());
			logger.info("version         : " + botanicSystem.getVersion());
			logger.info("ordinal         : " + botanicSystem.getOrdinal());
			logger.info("order index     : " + botanicSystem.getOrderIndex());
			logger.info("family index    : " + botanicSystem.getFamilyIndex());
			logger.info("sub family index: " + botanicSystem.getSubFamilyIndex());
			logger.info("order           : " + botanicSystem.getOrder());
			logger.info("family          : " + botanicSystem.getFamily());
			logger.info("subFamily       : " + botanicSystem.getSubFamily());
		}
	}

}
