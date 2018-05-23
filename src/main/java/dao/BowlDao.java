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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.google.inject.persist.Transactional;

import dto.BowlDto;
import dto.BowlModDto;
import dto.BowlModItemDto;
import dto.BowlModStepDto;
import dto.BowlModificationDto;
import entity.Bowl;
import entity.BowlMod;
import entity.BowlModItem;
import entity.BowlModStep;
import entity.Manufacture;
import ninja.jpa.UnitOfWork;
import types.BowlModification;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * <code>BOWL</code> of the underlying database system.
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
public class BowlDao extends AbstractDao {

	/**
	 * Delivers a persisted {@code Bowl} from the database with the provided
	 * technical identifier.
	 *
	 * @param id
	 *            the unique {@code Bowl} technical identifier
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public BowlDto getBowlById(Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager.createQuery("select b from Bowl b where b.id = :id", Bowl.class);
		typedQuery.setParameter("id", id);
		Bowl bowl = (Bowl) typedQuery.getSingleResult();

		BowlDto bowlDto = new BowlDto();
		bowlDto.receive(bowl);

		return bowlDto;

	}

	/**
	 * Delivers a persisted {@code Bowl} from the database with the provided
	 * technical identifier.
	 *
	 * @param id
	 *            the unique {@code Bowl} technical identifier
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public BowlDto getBowlById(String id) {
		return this.getBowlById(new Long(id));
	}

	/**
	 * Delivers a persisted {@code BowlMod} from the database with the provided
	 * technical identifier.
	 *
	 * @param id
	 *            the unique {@code BowlMod} technical identifier
	 * @return a persisted {@code BowlMod} as a data transfer object
	 */
	@UnitOfWork
	public BowlModDto getBowlModById(Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BowlMod> typedQuery = entityManager.createQuery("select b from BowlMod b where b.id = :id",
				BowlMod.class);
		typedQuery.setParameter("id", id);
		BowlMod bowlMod = (BowlMod) typedQuery.getSingleResult();

		BowlModDto bowlModDto = new BowlModDto();
		bowlModDto.receive(bowlMod);

		return bowlModDto;

	}

	/**
	 * Delivers a persisted {@code BowlMod} from the database with the provided
	 * technical identifier.
	 *
	 * @param id
	 *            the unique {@code BowlMod} technical identifier
	 * @return a persisted {@code BowlMod} as a data transfer object
	 */
	@UnitOfWork
	public BowlModDto getBowlModById(String id) {
		return this.getBowlModById(new Long(id));
	}

	/**
	 * Delivers a persisted {@code BowlModItem} from the database with the
	 * provided technical identifier.
	 *
	 * @param id
	 *            the unique {@code BowlModItem} technical identifier
	 * @return a persisted {@code BowlModItem} as a data transfer object
	 */
	@UnitOfWork
	public BowlModItemDto getBowlModItemById(Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BowlModItem> typedQuery = entityManager.createQuery("select b from BowlModItem b where b.id = :id",
				BowlModItem.class);
		typedQuery.setParameter("id", id);
		BowlModItem bowlModItem = (BowlModItem) typedQuery.getSingleResult();

		BowlModItemDto bowlModItemDto = new BowlModItemDto();
		bowlModItemDto.receive(bowlModItem);

		return bowlModItemDto;

	}

	/**
	 * Delivers a persisted {@code BowlModItem} from the database with the
	 * provided technical identifier.
	 *
	 * @param id
	 *            the unique {@code BowlModItem} technical identifier
	 * @return a persisted {@code BowlModItem} as a data transfer object
	 */
	@UnitOfWork
	public BowlModItemDto getBowlModItemById(String id) {
		return this.getBowlModItemById(new Long(id));
	}

	/**
	 * Delivers a persisted {@code BowlModStep} from the database with the
	 * provided technical identifier.
	 *
	 * @param id
	 *            the unique {@code BowlModStep} technical identifier
	 * @return a persisted {@code BowlModStep} as a data transfer object
	 */
	@UnitOfWork
	public BowlModStepDto getBowlModStepById(Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BowlModStep> typedQuery = entityManager.createQuery("select b from BowlModStep b where b.id = :id",
				BowlModStep.class);
		typedQuery.setParameter("id", id);
		BowlModStep bowlModStep = (BowlModStep) typedQuery.getSingleResult();

		BowlModStepDto bowlModStepDto = new BowlModStepDto();
		bowlModStepDto.receive(bowlModStep);

		return bowlModStepDto;

	}

	/**
	 * Delivers a persisted {@code BowlModStep} from the database with the
	 * provided technical identifier.
	 *
	 * @param id
	 *            the unique {@code BowlModStep} technical identifier
	 * @return a persisted {@code BowlModStep} as a data transfer object
	 */
	@UnitOfWork
	public BowlModStepDto getBowlModStepById(String id) {
		return this.getBowlModStepById(new Long(id));
	}

	/**
	 * Delivers the max index number of a persisted {@code Bowl}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM Bowl b WHERE b.index = SELECT max(x.index) FROM Bowl x;
	 *
	 * @return a {@code BowlDto} instance
	 */
	@UnitOfWork
	public BowlDto getBowlMaxIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager
				.createQuery("select b from Bowl b where b.index = (select max(x.index) from Bowl x)", Bowl.class);
		Bowl bowl = (Bowl) typedQuery.getSingleResult();

		BowlDto bowlDto = new BowlDto();
		bowlDto.receive(bowl);

		return bowlDto;

	}

	/**
	 * Delivers the max index number of a persisted {@code Bowl}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT MAX(b.index) FROM Bowl b;
	 *
	 * @return a {@code BowlDto} instance
	 */
	@UnitOfWork
	public Integer getBowlMaxIndexAsInteger() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Integer> typedQuery = entityManager.createQuery("select max(b.index) from Bowl b)", Integer.class);
		Integer bowlMaxIndex = (Integer) typedQuery.getSingleResult();

		return bowlMaxIndex;

	}

	/**
	 * Delivers the max ordinal number of a persisted {@code Bowl}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM bowl b WHERE b.ordinal = SELECT max(x.ordinal) FROM bowl x;
	 *
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public BowlDto getBowlMaxOrdinal() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager
				.createQuery("select b from Bowl b where b.ordinal = (select max(x.ordinal) from Bowl x)", Bowl.class);
		Bowl bowl = (Bowl) typedQuery.getSingleResult();

		BowlDto bowlDto = new BowlDto();
		bowlDto.receive(bowl);

		return bowlDto;

	}

	/**
	 * Delivers the max ordinal number of a persisted {@code Bowl}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT MAX(b.ordinal) FROM bowl b;
	 *
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public Integer getBowlMaxOrdinalAsInteger() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Integer> typedQuery = entityManager.createQuery("select max(b.ordinal) from Bowl b)", Integer.class);
		Integer bowlMaxOrdinal = (Integer) typedQuery.getSingleResult();

		return bowlMaxOrdinal;

	}

	/**
	 * Delivers a persisted {@code Bowl} from the database with the provided
	 * {@code Status} identifier.
	 *
	 * @param statusId
	 *            the unique {@code Status} technical identifier
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public List<BowlDto> getBowlsByStatus(@NotNull Long statusId) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager
				.createQuery("select b from Bowl b where b.status_id = :id order by b.ordinal", Bowl.class);
		typedQuery.setParameter("id", statusId);
		List<Bowl> typedQueryResultList = typedQuery.getResultList();

		List<BowlDto> bowlDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No bowl(s) with production status id {} found in data store!", statusId);
		} else {
			logger.info("{} bowl(s) found by status id {} in data store!", typedQueryResultList.size(), statusId);
			BowlDto bowlDto = null;
			for (Bowl bowl : typedQueryResultList) {
				bowlDto = new BowlDto(bowl.getId(), bowl.getVersion(), bowl.getIndex(), bowl.getManufacture(),
						bowl.getStatus(), bowl.getTimber(), bowl.getTimberOrigin(), bowl.getCustomer(),
						bowl.getExhibition(), bowl.getOrdinal(), bowl.getManufacture().getYear(), bowl.getImageName(),
						bowl.getPrice(), bowl.getSalesPrice(), bowl.getSalesLocation(), bowl.getSalesDate(),
						bowl.getComment());
				bowlDtoList.add(bowlDto);
			}
		}

		return bowlDtoList;

	}

	/**
	 * Delivers a persisted {@code Bowl} from the database with the provided
	 * {@code Timber} identifier.
	 *
	 * @param timberId
	 *            the unique {@code Timber} technical identifier
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public List<BowlDto> getBowlsByTimber(@NotNull Long timberId) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager
				.createQuery("select b from Bowl b where b.timber_id = :id order by b.ordinal", Bowl.class);
		typedQuery.setParameter("id", timberId);
		List<Bowl> typedQueryResultList = typedQuery.getResultList();

		List<BowlDto> bowlDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No bowl(s) with timber id {} found in data store!", timberId);
		} else {
			logger.info("{} bowl(s) found by timber id {} in data store!", typedQueryResultList.size(), timberId);
			BowlDto bowlDto = null;
			for (Bowl bowl : typedQueryResultList) {
				bowlDto = new BowlDto(bowl.getId(), bowl.getVersion(), bowl.getIndex(), bowl.getManufacture(),
						bowl.getStatus(), bowl.getTimber(), bowl.getTimberOrigin(), bowl.getCustomer(),
						bowl.getExhibition(), bowl.getOrdinal(), bowl.getManufacture().getYear(), bowl.getImageName(),
						bowl.getPrice(), bowl.getSalesPrice(), bowl.getSalesLocation(), bowl.getSalesDate(),
						bowl.getComment());
				bowlDtoList.add(bowlDto);
			}
		}

		return bowlDtoList;

	}

	/**
	 * Delivers a persisted {@code Bowl} from the database with the provided
	 * ordinal number.
	 *
	 * @param ordinal
	 *            the ordinal number
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public List<BowlDto> getBowlsByOrdinal(@NotNull Integer ordinal) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager
				.createQuery("select b from Bowl b where b.ordinal = :ordinal order by b.ordinal", Bowl.class);
		typedQuery.setParameter("ordinal", ordinal);
		List<Bowl> typedQueryResultList = typedQuery.getResultList();

		List<BowlDto> bowlDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No bowl(s) with ordinal {} found in data store!", ordinal);
		} else {
			logger.info("{} bowl(s) found by ordinal {} in data store!", typedQueryResultList.size(), ordinal);
			BowlDto bowlDto = null;
			for (Bowl bowl : typedQueryResultList) {
				bowlDto = new BowlDto(bowl.getId(), bowl.getVersion(), bowl.getIndex(), bowl.getManufacture(),
						bowl.getStatus(), bowl.getTimber(), bowl.getTimberOrigin(), bowl.getCustomer(),
						bowl.getExhibition(), bowl.getOrdinal(), bowl.getManufacture().getYear(), bowl.getImageName(),
						bowl.getPrice(), bowl.getSalesPrice(), bowl.getSalesLocation(), bowl.getSalesDate(),
						bowl.getComment());
				bowlDtoList.add(bowlDto);
			}
		}

		return bowlDtoList;

	}

	/**
	 * Delivers a persisted {@code Bowl} from the database with the provided
	 * 4-digit manufacturing year.
	 *
	 * @param year
	 *            the 4-digit manufacturing year
	 * @return a persisted {@code Bowl} as a data transfer object
	 */
	@UnitOfWork
	public List<BowlDto> getBowlsByYear(@NotNull String year) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager.createQuery(
				"select b from Bowl b where b.year = :year order by b.bowl_year and b.bowl_ordinal", Bowl.class);
		typedQuery.setParameter("year", year);
		List<Bowl> typedQueryResultList = typedQuery.getResultList();

		List<BowlDto> bowlDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No bowl(s) with production year {} found in data store!", year);
		} else {
			logger.info("{} bowl(s) found by production year {} in data store!", typedQueryResultList.size(), year);
			BowlDto bowlDto = null;
			for (Bowl bowl : typedQueryResultList) {
				bowlDto = new BowlDto(bowl.getId(), bowl.getVersion(), bowl.getIndex(), bowl.getManufacture(),
						bowl.getStatus(), bowl.getTimber(), bowl.getTimberOrigin(), bowl.getCustomer(),
						bowl.getExhibition(), bowl.getOrdinal(), bowl.getManufacture().getYear(), bowl.getImageName(),
						bowl.getPrice(), bowl.getSalesPrice(), bowl.getSalesLocation(), bowl.getSalesDate(),
						bowl.getComment());
				bowlDtoList.add(bowlDto);
			}
		}

		return bowlDtoList;

	}

	/**
	 * Provides all persisted bowls as a list of {@code Bowl} instances.
	 *
	 * @return list of all persisted bowls
	 */
	@UnitOfWork
	public List<BowlDto> listBowls() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Bowl> typedQuery = entityManager.createQuery("select b from Bowl b order by b.id", Bowl.class);
		List<Bowl> bowlList = typedQuery.getResultList();

		List<BowlDto> bowlDtoList = new ArrayList<>();

		if (bowlList.isEmpty()) {
			logger.info("No Bowl instance found in data store!");
		} else {
			logger.info("{} Bowl instance(s) found in data store!", bowlList.size());
			BowlDto bowlDto = null;
			for (Bowl bowl : bowlList) {
				logBowl(bowl);
				bowlDto = new BowlDto(bowl.getId(), bowl.getVersion(), bowl.getIndex(), bowl.getManufacture(),
						bowl.getStatus(), bowl.getTimber(), bowl.getTimberOrigin(), bowl.getCustomer(),
						bowl.getExhibition(), bowl.getOrdinal(), bowl.getManufacture().getYear(), bowl.getImageName(),
						bowl.getPrice(), bowl.getSalesPrice(), bowl.getSalesLocation(), bowl.getSalesDate(),
						bowl.getComment());
				bowlDtoList.add(bowlDto);
			}
		}

		return bowlDtoList;

	}

	/**
	 * Provides all persisted bowl modifications as a list of {@code BowlModDto}
	 * instances.
	 *
	 * @param bowlId
	 *            unique technical identifier of a {@code Bowl} instance
	 * @return list of all persisted bowl modifications
	 */
	@UnitOfWork
	public List<BowlModDto> listBowlModsByBowlId(Long bowlId) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BowlMod> typedQuery = entityManager
				.createQuery("select x from BowlMod x where x.bowl.id = :id order by x.id", BowlMod.class);
		typedQuery.setParameter("id", bowlId);
		List<BowlMod> bowlModList = typedQuery.getResultList();

		List<BowlModDto> bowlModDtoList = new ArrayList<>();

		if (bowlModList.isEmpty()) {
			logger.info("No Bowl modification found in data store!");
		} else {
			logger.info("{} Bowl modification(s) found in data store!", bowlModList.size());
			BowlModDto bowlModDto = null;
			for (BowlMod bowlMod : bowlModList) {
				logBowlMod(bowlMod);
				bowlModDto = new BowlModDto(bowlMod.getId(), bowlMod.getVersion(), bowlMod.getBowl(),
						bowlMod.getBowlModStep(), bowlMod.getDate(), bowlMod.getDiameter(), bowlMod.getHeight(),
						bowlMod.getWallthicknessMin(), bowlMod.getWallthicknessMax(), bowlMod.getGranulation(),
						bowlMod.getTap(), bowlMod.getRecess(), bowlMod.getSurface(), bowlMod.getComment());
				bowlModDtoList.add(bowlModDto);
			}
		}

		return bowlModDtoList;

	}

	/**
	 * Provides all persisted bowl modifications as a list of {@code BowlMod}
	 * instances.
	 *
	 * @param bowlModId
	 *            unique technical identifier of a {@code BowlMod} instance
	 * @return list of all persisted bowl modification items
	 */
	@UnitOfWork
	public List<BowlModItemDto> listBowlModItemsByBowlModId(Long bowlModId) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BowlModItem> typedQuery = entityManager.createQuery(
				"select b from BowlModItem b where b.bowlMod.id = :bowlModId order by b.id", BowlModItem.class);
		typedQuery.setParameter("bowlModId", bowlModId);
		List<BowlModItem> bowlModItemList = typedQuery.getResultList();

		List<BowlModItemDto> bowlModItemDtoList = new ArrayList<>();

		if (bowlModItemList.isEmpty()) {
			logger.info("No Bowl modification item found in data store!");
		} else {
			logger.info("{} Bowl modification item(s) found in data store!", bowlModItemList.size());
			BowlModItemDto bowlModItemDto = null;
			for (BowlModItem bowlModItem : bowlModItemList) {
				logBowlModItem(bowlModItem);
				bowlModItemDto = new BowlModItemDto(bowlModItem.getId(), bowlModItem.getVersion(),
						bowlModItem.getBowlMod(), bowlModItem.getText(), bowlModItem.getDate(), bowlModItem.getWeight(),
						bowlModItem.getMoisture());
				bowlModItemDtoList.add(bowlModItemDto);
			}
		}

		return bowlModItemDtoList;

	}

	/**
	 * Provides all persisted bowl modification steps as a list of
	 * {@code BowlModStep} instances.
	 *
	 * @return list of all persisted bowl modification steps
	 */
	@UnitOfWork
	public List<BowlModStepDto> listBowlModSteps() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<BowlModStep> typedQuery = entityManager.createQuery("select b from BowlModStep b order by b.id",
				BowlModStep.class);
		List<BowlModStep> bowlModStepList = typedQuery.getResultList();

		List<BowlModStepDto> bowlModStepDtoList = new ArrayList<>();

		if (bowlModStepList.isEmpty()) {
			logger.info("No Bowl modification step found in data store!");
		} else {
			logger.info("{} Bowl modification step(s) found in data store!", bowlModStepList.size());
			BowlModStepDto bowlModStepDto = null;
			for (BowlModStep bowlModStep : bowlModStepList) {
				logBowlModStep(bowlModStep);
				bowlModStepDto = new BowlModStepDto(bowlModStep.getId(), bowlModStep.getVersion(),
						bowlModStep.getIndex(), bowlModStep.getCode(), bowlModStep.getName(), bowlModStep.getComment());
				bowlModStepDtoList.add(bowlModStepDto);
			}
		}

		return bowlModStepDtoList;

	}

	/**
	 * Provides all persisted bowl modifications as a list of
	 * {@code BowlModificationDto} instances.
	 *
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @return list of all bowl modifications related to a specific {@code Bowl}
	 */
	@UnitOfWork
	public List<BowlModificationDto> listJoinedBowlModificationsByBowlId(Long bowlId) {
		logger.info("listJoinedBowlModificationsByBowlId: BowlId to search for is {}!", bowlId);

		EntityManager entityManager = entityManagerProvider.get();

		logger.info("listJoinedBowlModificationsByBowlId: EntityManager is {}", entityManager);

		String queryString = "select new types.BowlModification(bm.id, bm.version, bm.bowl.id,"
				+ " bm.bowl.version, bm.bowlModStep.id, bm.bowlModStep.name,"
				+ " bm.date, bm.diameter, bm.height, bm.wallthicknessMin, bm.wallthicknessMax,"
				+ " bm.granulation, bm.tap, bm.recess, bm.surface, bm.comment, bmi.id, bmi.version,"
				+ " bmi.bowlMod.id, bmi.date, bmi.weight, bmi.moisture, bmi.text)"
				+ " from BowlMod as bm left outer join bm.bowlModItems bmi where bm.bowl.id = :bowlId"
				+ " order by bm.bowlModStep.id asc";

		logger.info("listJoinedBowlModificationsByBowlId: QueryString is {}", queryString);

		Query query = entityManager.createQuery(queryString);
		query.setParameter("bowlId", bowlId);
		List<Object> resultList = query.getResultList();

		List<BowlModificationDto> bowlModificationDtoList = new ArrayList<>();

		if (resultList.isEmpty()) {
			logger.info("No joined bowl modification(s) found in data store!");
		} else {
			logger.info("{} joined bowl modification(s) found in data store!", resultList.size());
			BowlModificationDto bowlModificationDto = null;
			BowlModification bowlModification = null;
			long comparableBowlModStepId = 0;
			long actualBowlModStepId = 0;
			for (Object obj : resultList) {
				if (obj instanceof BowlModification) {
					bowlModification = (BowlModification) obj;
					actualBowlModStepId = bowlModification.getBowlModBowlModStepId();
					if (comparableBowlModStepId == actualBowlModStepId) {
						bowlModification.setDuplicateModStepId(Boolean.TRUE);
					} else {
						comparableBowlModStepId = actualBowlModStepId;
						bowlModification.setDuplicateModStepId(Boolean.FALSE);
					}
					logBowlModification(bowlModification);
					bowlModificationDto = new BowlModificationDto(bowlModification);
					bowlModificationDtoList.add(bowlModificationDto);
				}
			}
		}

		return bowlModificationDtoList;

	}

	/**
	 * Inserts or updates a {@code Bowl} in the database.
	 *
	 * @param bowlDto
	 *            the {@code Bowl} data transfer object
	 */
	@Transactional
	public void merge(BowlDto bowlDto) {
		EntityManager entityManager = entityManagerProvider.get();
		Bowl bowl = new Bowl(bowlDto);
		entityManager.merge(bowl);
		entityManager.flush();
		logger.info("Merged bowl id: {}", bowl.getId());
	}

	/**
	 * Persists a {@code Bowl} in the database.
	 *
	 * @param bowlDto
	 *            the {@code Bowl} data transfer object
	 */
	@Transactional
	public void register(BowlDto bowlDto) {
		EntityManager entityManager = entityManagerProvider.get();

		Manufacture manufacture = bowlDto.getManufacture();
		if (manufacture != null) {
			TypedQuery<Manufacture> typedQuery = entityManager
					.createQuery("select x from Manufacture x where x.id = :id", Manufacture.class);
			Long manufactureId = bowlDto.getManufacture().getId();
			logger.info("Selected manufacture id: {}", manufactureId);
			typedQuery.setParameter("id", manufactureId);
			manufacture = (Manufacture) typedQuery.getSingleResult();
			bowlDto.setManufacture(manufacture);
		}

		Bowl bowl = new Bowl(bowlDto);
		entityManager.persist(bowl);
		entityManager.flush();
		logger.info("New registered bowl id: {}", bowl.getId());
	}

	/**
	 * Inserts or updates a {@code BowlMod} in the database.
	 *
	 * @param bowlModDto
	 *            the {@code BowlMod} data transfer object
	 */
	@Transactional
	public void merge(BowlModDto bowlModDto) {
		EntityManager entityManager = entityManagerProvider.get();
		BowlMod bowlMod = new BowlMod(bowlModDto);
		entityManager.merge(bowlMod);
		entityManager.flush();
		logger.info("Merged bowlMod id: {}", bowlMod.getId());
	}

	/**
	 * Persists a {@code BowlMod} in the database.
	 *
	 * @param bowlModDto
	 *            the {@code BowlMod} data transfer object
	 */
	@Transactional
	public void register(BowlModDto bowlModDto) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<BowlModStep> typedQuery = entityManager.createQuery("select b from BowlModStep b where b.id = :id",
				BowlModStep.class);
		typedQuery.setParameter("id", bowlModDto.getBowlModStep().getId());
		BowlModStep bowlModStep = (BowlModStep) typedQuery.getSingleResult();
		bowlModDto.setBowlModStep(bowlModStep);
		BowlMod bowlMod = new BowlMod(bowlModDto);
		entityManager.persist(bowlMod);
		entityManager.flush();
		logger.info("New registered bowlMod id: {}", bowlMod.getId());
	}

	/**
	 * Inserts or updates a {@code BowlModItem} in the database.
	 *
	 * @param bowlModItemDto
	 *            the {@code BowlModItem} data transfer object
	 */
	@Transactional
	public void merge(BowlModItemDto bowlModItemDto) {
		EntityManager entityManager = entityManagerProvider.get();
		BowlModItem bowlModItem = new BowlModItem(bowlModItemDto);
		entityManager.merge(bowlModItem);
		entityManager.flush();
		logger.info("Merged bowlModItem id: {}", bowlModItem.getId());
	}

	/**
	 * Persists a {@code BowlModItem} in the database.
	 *
	 * @param bowlModItemDto
	 *            the {@code BowlModItem} data transfer object
	 */
	@Transactional
	public void register(BowlModItemDto bowlModItemDto) {
		EntityManager entityManager = entityManagerProvider.get();
		TypedQuery<BowlMod> typedQuery = entityManager.createQuery("select b from BowlMod b where b.id = :id",
				BowlMod.class);
		typedQuery.setParameter("id", bowlModItemDto.getBowlMod().getId());
		BowlMod bowlMod = (BowlMod) typedQuery.getSingleResult();
		bowlModItemDto.setBowlMod(bowlMod);
		BowlModItem bowlModItem = new BowlModItem(bowlModItemDto);
		entityManager.persist(bowlModItem);
		entityManager.flush();
		logger.info("New registered bowlModItem id: {}", bowlModItem.getId());
	}

	/**
	 * Logs a {@code Bowl}.
	 *
	 * @param bowl
	 *            instance of type {@code Bowl}
	 */
	private final void logBowl(Bowl bowl) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.bowl"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Bowl ~~~~~~~~~~~~~~~");
			logger.info("id           : " + bowl.getId());
			logger.info("version      : " + bowl.getVersion());
			logger.info(
					"manufacture  : " + bowl.getManufacture().getId() + " [" + bowl.getManufacture().getYear() + "]");
			logger.info("status       : " + bowl.getStatus().getId() + " [" + bowl.getStatus().getText() + "]");
			logger.info("timber       : " + bowl.getTimber().getId() + " [" + bowl.getTimber().getName() + "]");
			logger.info("timberOrigin : " + bowl.getTimberOrigin().getId() + " [" + bowl.getTimberOrigin().getCity()
					+ " " + bowl.getTimberOrigin().getLocation() + " " + bowl.getTimberOrigin().getLocationText()
					+ "]");
			logger.info("ordinal      : " + bowl.getOrdinal());
			logger.info("imageName    : " + bowl.getImageName());
			logger.info("price        : " + bowl.getPrice());
			logger.info("salesPrice   : " + bowl.getSalesPrice());
			logger.info("salesLocation: " + bowl.getSalesLocation());
			logger.info("salesDate    : " + bowl.getSalesDate());
			logger.info("comment      : " + bowl.getComment());
		}
	}

	/**
	 * Logs a {@code BowlMod}.
	 *
	 * @param bowlMod
	 *            instance of type {@code BowlMod}
	 */
	private final void logBowlMod(BowlMod bowlMod) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.bowlmod"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~ BowlMod ~~~~~~~~~~~~~");
			logger.info("id              : " + bowlMod.getId());
			logger.info("version         : " + bowlMod.getVersion());
			logger.info("bowl            : " + bowlMod.getBowl().getId());
			logger.info("bowlModStep     : " + bowlMod.getBowlModStep().getId());
			logger.info("date            : " + bowlMod.getDate());
			logger.info("diameter        : " + bowlMod.getDiameter());
			logger.info("height          : " + bowlMod.getHeight());
			logger.info("wallthicknessmin: " + bowlMod.getWallthicknessMin());
			logger.info("wallthicknessmax: " + bowlMod.getWallthicknessMax());
			logger.info("granulation     : " + bowlMod.getGranulation());
			logger.info("tap             : " + bowlMod.getTap());
			logger.info("recess          : " + bowlMod.getRecess());
			logger.info("surface         : " + bowlMod.getSurface());
			logger.info("comment         : " + bowlMod.getComment());
		}
	}

	/**
	 * Logs a {@code BowlModItem}.
	 *
	 * @param bowlModItem
	 *            instance of type {@code BowlModItem}
	 */
	private final void logBowlModItem(BowlModItem bowlModItem) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.bowlmoditem"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~ BowlModItem ~~~~~~~~~~~~~");
			logger.info("id       : " + bowlModItem.getId());
			logger.info("version  : " + bowlModItem.getVersion());
			logger.info("bowlmodid: " + bowlModItem.getBowlMod().getId());
			logger.info("date     : " + bowlModItem.getDate());
			logger.info("weight   : " + bowlModItem.getWeight());
			logger.info("moisture : " + bowlModItem.getMoisture());
		}
	}

	/**
	 * Logs a {@code BowlModStep}.
	 *
	 * @param bowlModStep
	 *            instance of type {@code BowlModStep}
	 */
	private final void logBowlModStep(BowlModStep bowlModStep) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.bowlmodstep"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~ BowlModStep ~~~~~~~~~~~~~");
			logger.info("id     : " + bowlModStep.getId());
			logger.info("version: " + bowlModStep.getVersion());
			logger.info("name   : " + bowlModStep.getName());
			logger.info("comment: " + bowlModStep.getComment());
		}
	}

	/**
	 * Logs a joined {@code BowlModification}.
	 *
	 * @param bowlModification
	 *            instance of type {@code BowlModification}
	 */
	private final void logBowlModification(BowlModification bowlModification) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.bowl.mod.joined"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~ BowlModification ~~~~~~~~~~~~~");
			logger.info("bowlmodjoined id               : " + bowlModification.getBowlModId());
			logger.info("bowlmodjoined version          : " + bowlModification.getBowlModVersion());
			logger.info("bowlmodjoined bowl id          : " + bowlModification.getBowlModBowlId());
			logger.info("bowlmodjoined bowlmodstep id   : " + bowlModification.getBowlModBowlModStepId());
			logger.info("bowlmodjoined date             : " + bowlModification.getBowlModDate());
			logger.info("bowlmodjoined diameter         : " + bowlModification.getBowlModDiameter());
			logger.info("bowlmodjoined height           : " + bowlModification.getBowlModHeight());
			logger.info("bowlmodjoined wallthickness min: " + bowlModification.getBowlModWallthicknessMin());
			logger.info("bowlmodjoined wallthickness max: " + bowlModification.getBowlModWallthicknessMin());
			logger.info("bowlmodjoined granulation      : " + bowlModification.getBowlModGranulation());
			logger.info("bowlmodjoined tap              : " + bowlModification.getBowlModTap());
			logger.info("bowlmodjoined recess           : " + bowlModification.getBowlModRecess());
			logger.info("bowlmodjoined surface          : " + bowlModification.getBowlModSurface());
			logger.info("bowlmodjoined comment          : " + bowlModification.getBowlModComment());
			logger.info("bowlmodjoineditem id           : " + bowlModification.getBowlModItemId());
			logger.info("bowlmodjoineditem version      : " + bowlModification.getBowlModItemVersion());
			logger.info("bowlmodjoineditem bowlmod id   : " + bowlModification.getBowlModItemBowlModId());
			logger.info("bowlmodjoineditem date         : " + bowlModification.getBowlModItemDate());
			logger.info("bowlmodjoineditem weight       : " + bowlModification.getBowlModItemWeight());
			logger.info("bowlmodjoineditem moisture     : " + bowlModification.getBowlModItemMoisture());
			logger.info("bowlmodjoineditem text         : " + bowlModification.getBowlModItemText());
			logger.info("duplicate     modstepid        : " + bowlModification.getDuplicateModStepId());
		}
	}

}
