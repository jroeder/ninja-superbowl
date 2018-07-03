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

import dao.RoadmapDao;
import dto.RoadmapDto;

/**
 * Implementation of {@code RoadmapService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Roadmap} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class RoadmapServiceImpl extends AbstractService implements RoadmapService {

	/**
	 * The {@code RoadmapDao} instance
	 */
	@Inject
	private RoadmapDao roadmapDao;

	/**
	 * Constructor.
	 */
	public RoadmapServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.RoadmapService#listRoadmap()
	 */
	@Override
	public List<RoadmapDto> listRoadmap() {
		List<RoadmapDto> roadmapList = ninjaCache.get("roadmaps", List.class);
		if (roadmapList == null) {
			roadmapList = roadmapDao.listRoadmap();
			ninjaCache.set("roadmaps", roadmapList, "30mn");
		}
		return roadmapList;
	}

}
