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

import dao.TimberOriginDao;
import dto.TimberOriginDto;
import ninja.cache.NinjaCache;

/**
 * Implementation of {@code TimberOriginService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code TimberOrigin} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 03.05.2017 mbsusr01
 */
public class TimberOriginServiceImpl extends AbstractService implements TimberOriginService {

	/**
	 * The {@code NinjaCache} instance
	 */
	@Inject
	private NinjaCache ninjaCache;

	/**
	 * The {@code TimberOriginDao} instance
	 */
	@Inject
	private TimberOriginDao timberOriginDao;

	/**
	 * Constructor.
	 */
	public TimberOriginServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberOriginService#getTimberOriginById(java.lang.Long)
	 */
	@Override
	public TimberOriginDto getTimberOriginById(Long id) {
		return timberOriginDao.getTimberOriginById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberOriginService#getTimberOriginById(java.lang.String)
	 */
	@Override
	public TimberOriginDto getTimberOriginById(String id) {
		return timberOriginDao.getTimberOriginById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberOriginService#getTimberOriginMaxIndex()
	 */
	@Override
	public TimberOriginDto getTimberOriginMaxIndex() {
		return timberOriginDao.getTimberOriginMaxIndex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberOriginService#listTimberOrigin()
	 */
	@Override
	public List<TimberOriginDto> listTimberOrigin() {
		return timberOriginDao.listTimberOrigin();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberOriginService#listTimberOriginByTimberId(java.lang.Long)
	 */
	@Override
	public List<TimberOriginDto> listTimberOriginByTimberId(Long timberId) {
		return timberOriginDao.listTimberOriginByTimberId(timberId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.TimberOriginService#register(dto.TimberOriginDto)
	 */
	@Override
	public void register(@NotNull TimberOriginDto timberOriginDto) {
		timberOriginDao.register(timberOriginDto);
	}

}
