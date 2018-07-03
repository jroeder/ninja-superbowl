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

import dto.TimberDto;
import dto.TimberListDto;
import entity.BotanicSystem;
import entity.GeoRegion;
import entity.Timber;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * <code>TIMBERORIGIN</code> of the underlying database system.
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
public class TimberDao extends AbstractDao {

	/**
	 * Insert method description here...
	 * 
	 * @return the {@code TimberListDto} instance
	 */
	@UnitOfWork
	public TimberListDto getAllTimber() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> query = entityManager.createQuery("select x from Timber x", Timber.class);
		List<Timber> timberList = query.getResultList();
		logger.info("TimberDao.getAllTimber() -> no of timber item() selected: " + timberList.size());

		TimberListDto timberListDto = new TimberListDto();
		timberListDto.setTimberList(timberList);

		return timberListDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @return the list of {@code TimberDto} instances
	 */
	@UnitOfWork
	public List<TimberDto> getAllTimbers() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> query = entityManager.createQuery("select x from Timber x", Timber.class);
		List<Timber> timberList = query.getResultList();
		logger.info("TimberDao.getAllTimber() -> no of timber item(s) selected: " + timberList.size());

		List<TimberDto> timberDtos = new ArrayList<>();

		if (timberList.isEmpty()) {
			logger.warn("No timber found in data store!");
		} else {
			logger.info("{} timber found in data store!", timberList.size());
			TimberDto timberDto = null;
			for (Timber timber : timberList) {
				logTimber(timber);
				timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
						timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(),
						timber.getImageName(), timber.getAcademicName(), timber.getGrossDensity(),
						timber.getTensileStrength(), timber.getBurstStrength(), timber.getBendingStrength(),
						timber.getShearStrength(), timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(),
						timber.getTangentShrinkage(), timber.getRadialShrinkage());
				timberDtos.add(timberDto);
			}
		}

		return timberDtos;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param id
	 *            the id
	 * @return the {@code TimberDto} instance
	 */
	@UnitOfWork
	public TimberDto getTimberById(@NotNull Long id) {

		TimberDto timberDto = new TimberDto();

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager.createQuery("select x from Timber x where x.id = :id",
				Timber.class);

		Timber timber = (Timber) typedQuery.setParameter("id", id).getSingleResult();

		timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
				timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(), timber.getImageName(),
				timber.getAcademicName(), timber.getGrossDensity(), timber.getTensileStrength(),
				timber.getBurstStrength(), timber.getBendingStrength(), timber.getShearStrength(),
				timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(), timber.getTangentShrinkage(),
				timber.getRadialShrinkage());

		return timberDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param id
	 *            the id
	 * @return the {@code TimberDto} instance
	 */
	@UnitOfWork
	public TimberDto getTimberById(@NotNull String id) {
		return this.getTimberById(new Long(id));
	}

	/**
	 * Insert method description here...
	 * 
	 * @param code
	 *            the code
	 * @return the {@code TimberDto} instance
	 */
	@UnitOfWork
	public TimberDto getTimberByCode(@NotNull String code) {
		logger.info("TimberDao.getTimberByCode -> Timber code: " + code);

		TimberDto timberDto = new TimberDto();

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager.createQuery("select x from Timber x where x.code = :code",
				Timber.class);

		Timber timber = (Timber) typedQuery.setParameter("code", code).getSingleResult();

		timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
				timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(), timber.getImageName(),
				timber.getAcademicName(), timber.getGrossDensity(), timber.getTensileStrength(),
				timber.getBurstStrength(), timber.getBendingStrength(), timber.getShearStrength(),
				timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(), timber.getTangentShrinkage(),
				timber.getRadialShrinkage());

		return timberDto;

	}

	/**
	 * Delivers the max index number of a persisted {@code Timber}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM Timber t WHERE t.index = SELECT max(x.index) FROM Timber x;
	 *
	 * @return a persisted {@code Timber} as a data transfer object
	 */
	@UnitOfWork
	public TimberDto getTimberMaxIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager.createQuery(
				"select t from Timber t where t.index = (select max(x.index) from Timber x)", Timber.class);
		Timber timber = (Timber) typedQuery.getSingleResult();

		TimberDto timberDto = new TimberDto();
		timberDto.receive(timber);

		return timberDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code TimberDto} instances
	 */
	@UnitOfWork
	public List<TimberDto> listTimber() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager.createQuery("select x from Timber x order by x.id", Timber.class);
		List<Timber> typedQueryResultList = typedQuery.getResultList();

		List<TimberDto> timberDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Timber instance found in data store!");
		} else {
			logger.info("{} Timber instance(s) found in data store!", typedQueryResultList.size());
			TimberDto timberDto = null;
			for (Timber timber : typedQueryResultList) {
				logTimber(timber);
				timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
						timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(),
						timber.getImageName(), timber.getAcademicName(), timber.getGrossDensity(),
						timber.getTensileStrength(), timber.getBurstStrength(), timber.getBendingStrength(),
						timber.getShearStrength(), timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(),
						timber.getTangentShrinkage(), timber.getRadialShrinkage());
				timberDtos.add(timberDto);
			}
		}
		return timberDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param geoRegion
	 *            the unique technical identifier of a {@code GeoRegion}
	 * @return the list of {@code TimberDto} instances
	 */
	@UnitOfWork
	public List<TimberDto> listTimberByGeoRegion(GeoRegion geoRegion) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager
				.createQuery("select x from Timber x where x.geoRegion.id = :id order by x.id", Timber.class);

		List<Timber> typedQueryResultList = typedQuery.setParameter("id", geoRegion.getId()).getResultList();

		List<TimberDto> timberDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Timber instance found in data store!");
		} else {
			logger.info("{} Timber instance(s) found in data store!", typedQueryResultList.size());
			TimberDto timberDto = null;
			for (Timber timber : typedQueryResultList) {
				logTimber(timber);
				timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
						timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(),
						timber.getImageName(), timber.getAcademicName(), timber.getGrossDensity(),
						timber.getTensileStrength(), timber.getBurstStrength(), timber.getBendingStrength(),
						timber.getShearStrength(), timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(),
						timber.getTangentShrinkage(), timber.getRadialShrinkage());
				timberDtos.add(timberDto);
			}
		}
		return timberDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the unique technical identifier of a {@code GeoRegion}
	 * @return the list of {@code TimberDto} instances
	 */
	@UnitOfWork
	public List<TimberDto> listTimberByGeoRegionId(Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager
				.createQuery("select x from Timber x where x.geoRegion.id = :id order by x.index", Timber.class);

		List<Timber> typedQueryResultList = typedQuery.setParameter("id", id).getResultList();

		List<TimberDto> timberDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Timber instance found in data store!");
		} else {
			logger.info("{} Timber instance(s) found in data store!", typedQueryResultList.size());
			TimberDto timberDto = null;
			for (Timber timber : typedQueryResultList) {
				logTimber(timber);
				timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
						timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(),
						timber.getImageName(), timber.getAcademicName(), timber.getGrossDensity(),
						timber.getTensileStrength(), timber.getBurstStrength(), timber.getBendingStrength(),
						timber.getShearStrength(), timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(),
						timber.getTangentShrinkage(), timber.getRadialShrinkage());
				timberDtos.add(timberDto);
			}
		}
		return timberDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param code
	 *            the {@code GeoRegion} code
	 * @return the list of {@code TimberDto} instances
	 */
	@UnitOfWork
	public List<TimberDto> listTimberByGeoRegionCode(String code) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Timber> typedQuery = entityManager
				.createQuery("select x from Timber x where x.geoRegion.code = :code order by x.index", Timber.class);

		List<Timber> typedQueryResultList = typedQuery.setParameter("code", code).getResultList();

		List<TimberDto> timberDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Timber instance found in data store!");
		} else {
			logger.info("{} Timber instance(s) found in data store!", typedQueryResultList.size());
			TimberDto timberDto = null;
			for (Timber timber : typedQueryResultList) {
				logTimber(timber);
				timberDto = new TimberDto(timber.getId(), timber.getVersion(), timber.getIndex(), timber.getGeoRegion(),
						timber.getBotanicSystem(), timber.getType(), timber.getCode(), timber.getName(),
						timber.getImageName(), timber.getAcademicName(), timber.getGrossDensity(),
						timber.getTensileStrength(), timber.getBurstStrength(), timber.getBendingStrength(),
						timber.getShearStrength(), timber.getBrinellHardnessOne(), timber.getBrinellHardnessTwo(),
						timber.getTangentShrinkage(), timber.getRadialShrinkage());
				timberDtos.add(timberDto);
			}
		}
		return timberDtos;

	}

	/**
	 * Persists a new {@code Timber} in the data store.
	 *
	 * @param timberDto
	 *            the {@code TimberDto} instance
	 */
	@Transactional
	public void register(TimberDto timberDto) {
		EntityManager entityManager = entityManagerProvider.get();
		// Attach GeoRegion to Timber
		GeoRegion geoRegion = timberDto.getGeoRegion();
		if (geoRegion != null) {
			TypedQuery<GeoRegion> typedQuery = entityManager.createQuery("select x from GeoRegion x where x.id = :id",
					GeoRegion.class);
			Long geoRegionId = timberDto.getGeoRegion().getId();
			logger.info("Selected GeoRegion Id: {}", geoRegionId);
			typedQuery.setParameter("id", geoRegionId);
			geoRegion = (GeoRegion) typedQuery.getSingleResult();
			timberDto.setGeoRegion(geoRegion);
		}
		// Attach BotanicSystem to Timber
		BotanicSystem botanicSystem = timberDto.getBotanicSystem();
		if (botanicSystem != null) {
			TypedQuery<BotanicSystem> typedQuery = entityManager
					.createQuery("select x from BotanicSystem x where x.id = :id", BotanicSystem.class);
			Long botanicSystemId = timberDto.getBotanicSystem().getId();
			logger.info("Selected BotanicSystem Id: {}", botanicSystemId);
			typedQuery.setParameter("id", botanicSystemId);
			botanicSystem = (BotanicSystem) typedQuery.getSingleResult();
			timberDto.setBotanicSystem(botanicSystem);
		}

		Timber timber = new Timber(timberDto);
		entityManager.persist(timber);
		entityManager.flush();

		logger.info("New registered timber id: {}", timber.getId());
	}

	/**
	 * Insert method description here...
	 *
	 * @param timber
	 *            instance of type {@code Timber}
	 */
	private final void logTimber(Timber timber) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.timber"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Timber ~~~~~~~~~~~~~~~");
			logger.info("id              : " + timber.getId());
			logger.info("version         : " + timber.getVersion());
			logger.info("index           : " + timber.getIndex());
			logger.info("geoRegion       : " + timber.getGeoRegion());
			logger.info("botanicSystem   : " + timber.getBotanicSystem());
			logger.info("type            : " + timber.getType());
			logger.info("code            : " + timber.getCode());
			logger.info("name            : " + timber.getName());
			logger.info("imageName       : " + timber.getImageName());
			logger.info("academicName    : " + timber.getAcademicName());
			logger.info("grossDensity    : " + timber.getGrossDensity());
			logger.info("tensileStrength : " + timber.getTensileStrength());
			logger.info("burstStrength   : " + timber.getBurstStrength());
			logger.info("bendingStrength : " + timber.getBendingStrength());
			logger.info("shearStrength   : " + timber.getShearStrength());
			logger.info("brinellHardness1: " + timber.getBrinellHardnessOne());
			logger.info("brinellHardness2: " + timber.getBrinellHardnessTwo());
			logger.info("tangentShrinkage: " + timber.getTangentShrinkage());
			logger.info("radialShrinkage : " + timber.getRadialShrinkage());
		}
	}

}
