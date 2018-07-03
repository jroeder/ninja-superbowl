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

import dao.TimberDao;
import dto.TimberDto;
import entity.GeoRegion;

/**
 * Implementation of {@code TimberService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Timber} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class TimberServiceImpl extends AbstractService implements TimberService {

	/**
	 * The {@code TimberDao} instance
	 */
	@Inject
	private TimberDao timberDao;

	/**
	 * Insert Constructor description here...
	 *
	 */
	public TimberServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#getTimberById(java.lang.Long)
	 */
	@Override
	public TimberDto getTimberById(@NotNull Long id) {
		return timberDao.getTimberById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#getTimberById(java.lang.String)
	 */
	@Override
	public TimberDto getTimberById(@NotNull String id) {
		return timberDao.getTimberById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#getTimberByCode(java.lang.String)
	 */
	@Override
	public TimberDto getTimberByCode(@NotNull String code) {
		return timberDao.getTimberByCode(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#getTimberMaxIndex()
	 */
	@Override
	public TimberDto getTimberMaxIndex() {
		return timberDao.getTimberMaxIndex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#listTimber()
	 */
	@Override
	public List<TimberDto> listTimber() {
		return timberDao.listTimber();
		// List<TimberDto> timbers = ninjaCache.get("timbers", List.class);
		// if (timbers == null) {
		// timbers = timberDao.listTimber();
		// ninjaCache.set("timbers", timbers, "30mn");
		// }
		// return timbers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#listTimberByGeoRegion(entity.GeoRegion)
	 */
	@Override
	public List<TimberDto> listTimberByGeoRegion(@NotNull GeoRegion geoRegion) {
		return timberDao.listTimberByGeoRegion(geoRegion);
		// List<TimberDto> timbers = null;
		// GeoRegion lGeoRegion = ninjaCache.get("geoRegion", GeoRegion.class);
		// if (lGeoRegion == null) {
		// lGeoRegion = geoRegion;
		// ninjaCache.set("geoRegion", lGeoRegion, "30mn");
		// timbers = ninjaCache.get("timbers", List.class);
		// if (timbers == null) {
		// timbers = timberDao.listTimberByGeoRegion(geoRegion);
		// ninjaCache.set("timbers", timbers, "30mn");
		// }
		// } else {
		// if (lGeoRegion.getId().equals(geoRegion.getId())) {
		// timbers = ninjaCache.get("timbers", List.class);
		// } else {
		// timbers = timberDao.listTimberByGeoRegion(geoRegion);
		// }
		// }
		// return timbers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#listTimberByGeoRegionId(java.lang.Long)
	 */
	@Override
	public List<TimberDto> listTimberByGeoRegionId(@NotNull Long id) {
		return timberDao.listTimberByGeoRegionId(id);
		// List<TimberDto> timbers = null;
		// Long lGeoRegionId = ninjaCache.get("geoRegionId", Long.class);
		// if (lGeoRegionId == null) {
		// lGeoRegionId = id;
		// ninjaCache.set("geoRegionId", lGeoRegionId, "30mn");
		// timbers = ninjaCache.get("timbers", List.class);
		// if (timbers == null) {
		// timbers = timberDao.listTimberByGeoRegionId(id);
		// ninjaCache.set("timbers", timbers, "30mn");
		// }
		// } else {
		// if (lGeoRegionId.equals(id)) {
		// timbers = ninjaCache.get("timbers", List.class);
		// } else {
		// timbers = timberDao.listTimberByGeoRegionId(id);
		// }
		// }
		// return timbers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.TimberService#listTimberByGeoRegionCode(java.lang.String)
	 */
	@Override
	public List<TimberDto> listTimberByGeoRegionCode(@NotNull String code) {
		return timberDao.listTimberByGeoRegionCode(code);
		// List<TimberDto> timbers = null;
		// String cachedCode = ninjaCache.get("geoRegionCode", String.class);
		// if (cachedCode == null) {
		// ninjaCache.set("geoRegionCode", code, "30mn");
		// timbers = ninjaCache.get("timbers", List.class);
		// if (timbers == null) {
		// timbers = timberDao.listTimberByGeoRegionCode(code);
		// ninjaCache.set("timbers", timbers, "30mn");
		// }
		// } else {
		// if (cachedCode.equals(code)) {
		// timbers = ninjaCache.get("timbers", List.class);
		// } else {
		// timbers = timberDao.listTimberByGeoRegionCode(code);
		// }
		// }
		// return timbers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.TimberService#register(dto.TimberDto)
	 */
	@Override
	public void register(@NotNull TimberDto timberDto) {
		timberDao.register(timberDto);
	}

}
