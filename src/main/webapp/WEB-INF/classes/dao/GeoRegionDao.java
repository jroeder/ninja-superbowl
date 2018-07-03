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

import dto.GeoRegionDto;
import dto.GeoRegionListDto;
import entity.GeoRegion;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code GEOREGION} of the underlying database system.
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
public class GeoRegionDao extends AbstractDao {

	/**
	 * Insert method description here...
	 * 
	 * @return the list of {@code GeoRegionDto} instances
	 */
	@UnitOfWork
	public GeoRegionListDto getAllGeoRegion() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<GeoRegion> query = entityManager.createQuery("select x from GeoRegion x order by x.code",
				GeoRegion.class);
		List<GeoRegion> geoRegions = query.getResultList();

		GeoRegionListDto geoRegionsDto = new GeoRegionListDto();
		geoRegionsDto.setGeoRegions(geoRegions);

		return geoRegionsDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param id
	 *            the unique technical identifier
	 * @return the {@code GeoRegionDto} instance
	 */
	@UnitOfWork
	public GeoRegionDto getGeoRegionById(@NotNull Long id) {

		GeoRegionDto geoRegionDto = new GeoRegionDto();

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<GeoRegion> typedQuery = entityManager
				.createQuery("select x from GeoRegion x where x.id = :id", GeoRegion.class);

		GeoRegion geoRegion = (GeoRegion) typedQuery.setParameter("id", id).getSingleResult();

		geoRegionDto = new GeoRegionDto(geoRegion.getId(), geoRegion.getVersion(), geoRegion.getOrdinal(),
				geoRegion.getIndex(), geoRegion.getCode(), geoRegion.getName(),
				geoRegion.getRegion());

		return geoRegionDto;

	}

	/**
	 * Insert method description here...
	 * 
	 * @param id
	 *            the unique technical identifier
	 * @return the {@code GeoRegionDto} instance
	 */
	@UnitOfWork
	public GeoRegionDto getGeoRegionById(@NotNull String id) {
		return this.getGeoRegionById(new Long(id));
	}

	/**
	 * Insert method description here...
	 * 
	 * @param code
	 *            the {@code GeoRegion code}
	 * @return the {@code GeoRegionDto} instance
	 */
	@UnitOfWork
	public GeoRegionDto getGeoRegionByCode(@NotNull String code) {

		GeoRegionDto geoRegionDto = new GeoRegionDto();

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<GeoRegion> typedQuery = entityManager
				.createQuery("select x from GeoRegion x where x.code = :code", GeoRegion.class);

		GeoRegion geoRegion = (GeoRegion) typedQuery.setParameter("code", code).getSingleResult();

		geoRegionDto = new GeoRegionDto(geoRegion.getId(), geoRegion.getVersion(), geoRegion.getOrdinal(),
				geoRegion.getIndex(), geoRegion.getCode(), geoRegion.getName(),
				geoRegion.getRegion());

		return geoRegionDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code GeoRegionDto} instances
	 */
	@UnitOfWork
	public List<GeoRegionDto> listGeoRegions() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<GeoRegion> typedQuery = entityManager
				.createQuery("select x from GeoRegion x order by x.index", GeoRegion.class);
		List<GeoRegion> typedQueryResultList = typedQuery.getResultList();

		List<GeoRegionDto> geoRegionDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No GeoRegion instance found in data store!");
		} else {
			logger.info("{} GeoRegion instance(s) found in data store!", typedQueryResultList.size());
			GeoRegionDto geoRegionDto = null;
			for (GeoRegion geoRegion : typedQueryResultList) {
				logGeoRegion(geoRegion);
				geoRegionDto = new GeoRegionDto(geoRegion.getId(), geoRegion.getVersion(), geoRegion.getOrdinal(),
						geoRegion.getIndex(), geoRegion.getCode(), geoRegion.getName(),
						geoRegion.getRegion());
				geoRegionDtos.add(geoRegionDto);
			}
		}
		return geoRegionDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param geoRegion
	 *            the {@code GeoRegion instance}
	 */
	private final void logGeoRegion(GeoRegion geoRegion) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.georegion"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ GeoRegion ~~~~~~~~~~~~~~~");
			logger.info("id         : " + geoRegion.getId());
			logger.info("version    : " + geoRegion.getVersion());
			logger.info("ordinal    : " + geoRegion.getOrdinal());
			logger.info("index      : " + geoRegion.getIndex());
			logger.info("code       : " + geoRegion.getCode());
			logger.info("name       : " + geoRegion.getName());
			logger.info("region     : " + geoRegion.getRegion());
		}
	}

}
