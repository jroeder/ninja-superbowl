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

import dao.StatusDao;
import dto.StatusDto;
import ninja.cache.NinjaCache;

/**
 * Implementation of {@code StatusService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Status} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class StatusServiceImpl implements StatusService {

	/**
	 * The {@code NinjaCache} instance
	 */
	@Inject
	private NinjaCache ninjaCache;

	/**
	 * The {@code StatusDao} instance
	 */
	@Inject
	private StatusDao statusDao;

	/**
	 * Insert Constructor description here...
	 */
	public StatusServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.StatusService#getStatusMaxIndex()
	 */
	@Override
	public StatusDto getStatusMaxIndex() {
		return statusDao.getStatusMaxIndex();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.StatusService#getStatusById(java.lang.Long)
	 */
	@Override
	public StatusDto getStatusById(@NotNull Long id) {
		return statusDao.getStatusById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.StatusService#getStatusById(java.lang.String)
	 */
	@Override
	public StatusDto getStatusById(@NotNull String id) {
		return statusDao.getStatusById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.StatusService#getStatusByCode(java.lang.String)
	 */
	@Override
	public StatusDto getStatusByCode(@NotNull String code) {
		return statusDao.getStatusByCode(code);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.StatusService#listStatus()
	 */
	@Override
	public List<StatusDto> listStatus() {
		List<StatusDto> stati = ninjaCache.get("stati", List.class);
		if (stati == null) {
			stati = statusDao.listStatus();
			ninjaCache.set("stati", stati, "30mn");
		}
		return stati;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.StatusService#register(dto.StatusDto)
	 */
	@Override
	public void register(@NotNull StatusDto statusDto) {
		statusDao.register(statusDto);
	}

}
