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

import dto.ManufactureDto;
import dto.TimberOriginDto;
import entity.Manufacture;
import entity.TimberOrigin;
import ninja.jpa.UnitOfWork;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class ManufactureDao extends AbstractDao {

	/**
	 * Constructor.
	 */
	public ManufactureDao() {
		super();
	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the unique technical identifier of a {@code Manufacture}
	 * @return the {@code ManufactureDto} instance
	 */
	@UnitOfWork
	public ManufactureDto getManufactureById(@NotNull Long id) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Manufacture> typedQuery = entityManager.createQuery("select x from Manufacture x where x.id = :id",
				Manufacture.class);
		Manufacture manufacture = (Manufacture) typedQuery.setParameter("id", id).getSingleResult();

		ManufactureDto manufactureDto = new ManufactureDto();
		manufactureDto.receive(manufacture);

		return manufactureDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the id
	 * @return the {@code ManufactureDto} instance
	 */
	@UnitOfWork
	public ManufactureDto getManufactureById(@NotNull String id) {
		return this.getManufactureById(new Long(id));
	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code ManufactureDto} instances
	 */
	@UnitOfWork
	public List<ManufactureDto> listManufacture() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Manufacture> typedQuery = entityManager.createQuery("select x from Manufacture x order by x.year",
				Manufacture.class);
		List<Manufacture> typedQueryResultList = typedQuery.getResultList();

		List<ManufactureDto> manufactureDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Manufacture instance found in data store!");
		} else {
			logger.info("{} Manufacture instance(s) found in data store!", typedQueryResultList.size());
			// Boolean isLog = new
			// Boolean(ninjaProperties.get("superbowl.log.dao.manufacture"));
			// if (isLog) {
			// logger.info("{} Manufacture instance(s) found in data store!",
			// typedQueryResultList.size());
			// }
			ManufactureDto manufactureDto = null;
			for (Manufacture manufacture : typedQueryResultList) {
				logManufacture(manufacture);
				manufactureDto = new ManufactureDto(manufacture.getId(), manufacture.getVersion(),
						manufacture.getIndex(), manufacture.getYear());
				manufactureDtos.add(manufactureDto);
			}
		}

		return manufactureDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param manufactureDto
	 *            the {@code ManufactureDto} instance
	 */
	@UnitOfWork
	public void register(ManufactureDto manufactureDto) {

		EntityManager entityManager = entityManagerProvider.get();

		Manufacture manufacture = new Manufacture(manufactureDto);
		entityManager.persist(manufacture);

		logger.info("New registered manufacture id: {}", manufacture.getId());

	}

	/**
	 * Insert method description here...
	 *
	 * @param manufactureDto
	 *            the {@code Manufacture} instance
	 */
	private final void logManufacture(Manufacture manufacture) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.manufacture"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Manufacture ~~~~~~~~~~~~~~~");
			logger.info("id         : " + manufacture.getId());
			logger.info("version    : " + manufacture.getVersion());
			logger.info("year       : " + manufacture.getYear());
		}
	}

}
