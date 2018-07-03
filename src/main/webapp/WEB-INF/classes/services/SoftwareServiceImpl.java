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

import dao.SoftwareDao;
import dto.SoftwareDto;

/**
 * Implementation of {@code SoftwareService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Software} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class SoftwareServiceImpl extends AbstractService implements SoftwareService {

	/**
	 * The {@code SoftwareDao} instance
	 */
	@Inject
	private SoftwareDao softwareDao;

	/**
	 * Constructor.
	 */
	public SoftwareServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.SoftwareService#listSoftware()
	 */
	@Override
	public List<SoftwareDto> listSoftware() {
		List<SoftwareDto> softwareList = ninjaCache.get("softwares", List.class);
		if (softwareList == null) {
			softwareList = softwareDao.listSoftware();
			ninjaCache.set("softwares", softwareList, "30mn");
		}
		return softwareList;
	}

}
