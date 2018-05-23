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

import dto.ExhibitionDto;
import entity.Exhibition;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code CUSTOMER} of the underlying database system.
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
public class ExhibitionDao extends AbstractDao {

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the unique technical identifier
	 * @return the {@code ExhibitionDto} instance
	 */
	@UnitOfWork
	public ExhibitionDto getExhibitionById(@NotNull Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Exhibition> typedQuery = entityManager.createQuery("select e from Exhibition e where e.id = :id",
				Exhibition.class);

		Exhibition exhibition = (Exhibition) typedQuery.setParameter("id", id).getSingleResult();

		ExhibitionDto exhibitionDto = new ExhibitionDto(exhibition.getId(), exhibition.getVersion(),
				exhibition.getIndex(), exhibition.getName(), exhibition.getInstitution(), exhibition.getYear(),
				exhibition.getDateFrom(), exhibition.getDateTo(), exhibition.getCity(), exhibition.getCountry(),
				exhibition.getComment());

		return exhibitionDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the unique technical identifier
	 * @return the {@code ExhibitionDto} instance
	 */
	@UnitOfWork
	public ExhibitionDto getExhibitionById(@NotNull String id) {
		return this.getExhibitionById(new Long(id));
	}

	/**
	 * Delivers the max index number of a persisted {@code Exhibition}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM exhibition e WHERE e.index = SELECT max(x.index) FROM
	 * exhibition x;
	 *
	 * @return a persisted {@code Exhibition} as a data transfer object
	 */
	@UnitOfWork
	public ExhibitionDto getExhibitionMaxIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Exhibition> typedQuery = entityManager.createQuery(
				"select e from Exhibition e where e.index = (select max(x.index) from Exhibition x)", Exhibition.class);
		Exhibition exhibition = (Exhibition) typedQuery.getSingleResult();

		ExhibitionDto exhibitionDto = new ExhibitionDto();
		exhibitionDto.receive(exhibition);

		return exhibitionDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code ExhibitionDto} instances
	 */
	@UnitOfWork
	public List<ExhibitionDto> listExhibitions() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Exhibition> typedQuery = entityManager.createQuery("select e from Exhibition e order by e.id",
				Exhibition.class);
		List<Exhibition> typedQueryResultList = typedQuery.getResultList();

		List<ExhibitionDto> exhibitionDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No exhibition(s) found in data store!");
		} else {
			logger.info("{} exhibition(s) found in data store!", typedQueryResultList.size());
			ExhibitionDto exhibitionDto = null;
			for (Exhibition exhibition : typedQueryResultList) {
				logExhibition(exhibition);
				exhibitionDto = new ExhibitionDto(exhibition.getId(), exhibition.getVersion(), exhibition.getIndex(),
						exhibition.getName(), exhibition.getInstitution(), exhibition.getYear(),
						exhibition.getDateFrom(), exhibition.getDateTo(), exhibition.getCity(), exhibition.getCountry(),
						exhibition.getComment());
				exhibitionDtos.add(exhibitionDto);
			}
		}
		return exhibitionDtos;

	}

	/**
	 * Persists a new {@code Exhibition} in the data store.
	 *
	 * @param exhibitionDto
	 *            the {@code ExhibitionDao} instance
	 */
	@Transactional
	public void register(ExhibitionDto exhibitionDto) {

		EntityManager entityManager = entityManagerProvider.get();

		Exhibition exhibition = new Exhibition(exhibitionDto);
		entityManager.persist(exhibition);

		entityManager.flush();

		logger.info("New registered exhibition id: {}", exhibition.getId());

	}

	/**
	 * Insert method description here...
	 *
	 * @param exhibition
	 *            the {@code Exhibition} instance
	 */
	private final void logExhibition(Exhibition exhibition) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.exhibition"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Exhibition ~~~~~~~~~~~~~~~");
			logger.info("id         : " + exhibition.getId());
			logger.info("version    : " + exhibition.getVersion());
			logger.info("index      : " + exhibition.getIndex());
			logger.info("name       : " + exhibition.getName());
			logger.info("Institution: " + exhibition.getInstitution());
			logger.info("year       : " + exhibition.getYear());
			logger.info("dateFrom   : " + exhibition.getDateFrom());
			logger.info("dateTo     : " + exhibition.getDateTo());
			logger.info("city       : " + exhibition.getCity());
			logger.info("country    : " + exhibition.getCountry());
			logger.info("comment    : " + exhibition.getComment());
		}
	}

}
