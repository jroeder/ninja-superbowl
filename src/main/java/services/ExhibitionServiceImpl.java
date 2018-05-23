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

import dao.ExhibitionDao;
import dto.ExhibitionDto;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 20.04.2017 mbsusr01
 */
public class ExhibitionServiceImpl extends AbstractService implements ExhibitionService {

	/**
	 * The {@code ExhibitionDao} instance
	 */
	@Inject
	private ExhibitionDao exhibitionDao;

	/**
	 * Constructor.
	 */
	public ExhibitionServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.ExhibitionService#listExhibitions()
	 */
	@Override
	public List<ExhibitionDto> listExhibitions() {
		return exhibitionDao.listExhibitions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.ExhibitionService#getExhibitionById(java.lang.Long)
	 */
	@Override
	public ExhibitionDto getExhibitionById(Long id) {
		return exhibitionDao.getExhibitionById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.ExhibitionService#getExhibitionById(vString)
	 */
	@Override
	public ExhibitionDto getExhibitionById(String id) {
		return exhibitionDao.getExhibitionById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.ExhibitionService#getExhibitionMaxIndex()
	 */
	@Override
	public ExhibitionDto getExhibitionMaxIndex() {
		return exhibitionDao.getExhibitionMaxIndex();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.ExhibitionService#register(dto.ExhibitionDto)
	 */
	@Override
	public void register(@NotNull ExhibitionDto exhibitionDto) {
		exhibitionDao.register(exhibitionDto);
	}

}
