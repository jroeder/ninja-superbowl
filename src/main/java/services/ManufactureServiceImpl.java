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
package services;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import dao.ManufactureDao;
import dto.ManufactureDto;
import ninja.cache.NinjaCache;

/**
 * Implementation of {@code ManufactureService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Manufacture} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class ManufactureServiceImpl implements ManufactureService {

	/**
	 * The {@code NinjaCache} instance
	 */
	@Inject
	private NinjaCache ninjaCache;

	/**
	 * The {@code ManufactureDao} instance
	 */
	@Inject
	private ManufactureDao manufactureDao;

	/**
	 * Insert Constructor description here...
	 */
	public ManufactureServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see services.ManufactureService#getManufactureById(java.lang.Long)
	 */
	@Override
	public ManufactureDto getManufactureById(@NotNull Long id) {
		return manufactureDao.getManufactureById(id);
	}

	/* (non-Javadoc)
	 * @see services.ManufactureService#getManufactureById(java.lang.String)
	 */
	@Override
	public ManufactureDto getManufactureById(@NotNull String id) {
		return manufactureDao.getManufactureById(id);
	}

	/* (non-Javadoc)
	 * @see services.ManufactureService#listManufacture()
	 */
	@Override
	public List<ManufactureDto> listManufacture() {
		List<ManufactureDto> manufactures = ninjaCache.get("manufactures", List.class);
		if (manufactures == null) {
			manufactures = manufactureDao.listManufacture();
			ninjaCache.set("manufactures", manufactures, "30mn");
		}
		return manufactures;
	}

	/* (non-Javadoc)
	 * @see services.ManufactureService#register(dto.ManufactureDto)
	 */
	@Override
	public void register(@NotNull ManufactureDto manufactureDto) {
		manufactureDao.register(manufactureDto);
	}

}
