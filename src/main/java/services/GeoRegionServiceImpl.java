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

import dao.GeoRegionDao;
import dto.GeoRegionDto;
import ninja.cache.NinjaCache;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class GeoRegionServiceImpl implements GeoRegionService {

	@Inject
	private NinjaCache ninjaCache;

	@Inject
	private GeoRegionDao geoRegionDao;

	/**
	 * Insert Constructor description here...
	 *
	 */
	public GeoRegionServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.GeoRegionService#getGeoRegionById(java.lang.Long)
	 */
	@Override
	public GeoRegionDto getGeoRegionById(Long id) {
		return geoRegionDao.getGeoRegionById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.GeoRegionService#getGeoRegionById(java.lang.String)
	 */
	@Override
	public GeoRegionDto getGeoRegionById(String id) {
		return geoRegionDao.getGeoRegionById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.GeoRegionService#getGeoRegionByCode(java.lang.String)
	 */
	@Override
	public GeoRegionDto getGeoRegionByCode(String code) {
		return geoRegionDao.getGeoRegionByCode(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.GeoRegionService#listGeoRegions()
	 */
	@Override
	public List<GeoRegionDto> listGeoRegions() {
		List<GeoRegionDto> geoRegions = ninjaCache.get("geoRegions", List.class);
		if (geoRegions == null) {
			geoRegions = geoRegionDao.listGeoRegions();
			ninjaCache.set("geoRegions", geoRegions, "30mn");
		}
		return geoRegions;
	}

}
