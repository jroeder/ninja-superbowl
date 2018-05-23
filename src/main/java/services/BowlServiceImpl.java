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

import dao.BowlDao;
import dto.BowlDto;
import dto.BowlModDto;
import dto.BowlModItemDto;
import dto.BowlModStepDto;
import dto.BowlModificationDto;
import ninja.cache.NinjaCache;

/**
 * Implementation of {@code BowlService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Bowl} data from underlying
 * data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class BowlServiceImpl implements BowlService {

	/**
	 * The {@code NinjaCache} instance
	 */
	@Inject
	private NinjaCache ninjaCache;

	/**
	 * The {@code BowlDao} instance
	 */
	@Inject
	private BowlDao bowlDao;

	/**
	 * Constructor.
	 */
	public BowlServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlById(java.lang.Long)
	 */
	@Override
	public BowlDto getBowlById(@NotNull Long id) {
		return bowlDao.getBowlById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlById(java.lang.String)
	 */
	@Override
	public BowlDto getBowlById(@NotNull String id) {
		return bowlDao.getBowlById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlModById(java.lang.Long)
	 */
	@Override
	public BowlModDto getBowlModById(@NotNull Long id) {
		return bowlDao.getBowlModById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BowlService#getBowlMaxIndex()
	 */
	@Override
	public BowlDto getBowlMaxIndex() {
		return bowlDao.getBowlMaxIndex();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlMaxOrdinal()
	 */
	public BowlDto getBowlMaxOrdinal() {
		return bowlDao.getBowlMaxOrdinal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BowlService#getBowlMaxIndexAsInteger()
	 */
	@Override
	public Integer getBowlMaxIndexAsInteger() {
		return bowlDao.getBowlMaxIndexAsInteger();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlMaxOrdinalAsInteger()
	 */
	public Integer getBowlMaxOrdinalAsInteger() {
		return bowlDao.getBowlMaxOrdinalAsInteger();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlModById(java.lang.String)
	 */
	@Override
	public BowlModDto getBowlModById(@NotNull String id) {
		return bowlDao.getBowlModById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlModItemById(java.lang.Long)
	 */
	@Override
	public BowlModItemDto getBowlModItemById(@NotNull Long id) {
		return bowlDao.getBowlModItemById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlModItemById(java.lang.String)
	 */
	@Override
	public BowlModItemDto getBowlModItemById(@NotNull String id) {
		return bowlDao.getBowlModItemById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlModStepById(java.lang.Long)
	 */
	@Override
	public BowlModStepDto getBowlModStepById(@NotNull Long id) {
		return bowlDao.getBowlModStepById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlModStepById(java.lang.String)
	 */
	@Override
	public BowlModStepDto getBowlModStepById(@NotNull String id) {
		return bowlDao.getBowlModStepById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlsByStatus(java.lang.Long)
	 */
	@Override
	public List<BowlDto> getBowlsByStatus(@NotNull Long statusId) {
		return bowlDao.getBowlsByStatus(statusId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlsByTimber(java.lang.Long)
	 */
	@Override
	public List<BowlDto> getBowlsByTimber(@NotNull Long timberId) {
		return bowlDao.getBowlsByTimber(timberId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlsByOrdinal(java.lang.Integer)
	 */
	@Override
	public List<BowlDto> getBowlsByOrdinal(@NotNull Integer ordinal) {
		List<BowlDto> bowls = null;
		Integer lOrdinal = ninjaCache.get("ordinal", Integer.class);
		if (lOrdinal == null) {
			lOrdinal = ordinal;
			ninjaCache.set("ordinal", lOrdinal, "30mn");
			bowls = ninjaCache.get("bowls", List.class);
			if (bowls == null) {
				bowls = bowlDao.getBowlsByOrdinal(ordinal);
				ninjaCache.set("bowls", bowls, "30mn");
			}
		} else {
			if (lOrdinal == ordinal) {
				bowls = ninjaCache.get("bowls", List.class);
			} else {
				bowls = bowlDao.getBowlsByOrdinal(ordinal);
			}
		}
		return bowls;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#getBowlsByYear(java.lang.String)
	 */
	@Override
	public List<BowlDto> getBowlsByYear(@NotNull String year) {
		List<BowlDto> bowls = null;
		String lYear = ninjaCache.get("year", String.class);
		if (lYear == null) {
			lYear = year;
			ninjaCache.set("year", lYear, "30mn");
			bowls = ninjaCache.get("bowls", List.class);
			if (bowls == null) {
				bowls = bowlDao.getBowlsByYear(year);
				ninjaCache.set("bowls", bowls, "30mn");
			}
		} else {
			if (lYear == year) {
				bowls = ninjaCache.get("bowls", List.class);
			} else {
				bowls = bowlDao.getBowlsByYear(year);
			}
		}
		return bowls;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#listBowls()
	 */
	@Override
	public List<BowlDto> listBowls() {
		return bowlDao.listBowls();
		// List<BowlDto> bowls = ninjaCache.get("bowls", List.class);
		// if (bowls == null) {
		// bowls = bowlDao.listBowls();
		// ninjaCache.set("bowls", bowls, "30mn");
		// }
		// return bowls;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#listBowlModifications(Long)
	 */
	@Override
	public List<BowlModDto> listBowlModsByBowlId(Long bowlId) {
		return bowlDao.listBowlModsByBowlId(bowlId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#listBowlModItems(Long)
	 */
	@Override
	public List<BowlModItemDto> listBowlModItemsByBowlModId(Long bowlModId) {
		return bowlDao.listBowlModItemsByBowlModId(bowlModId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#listBowlModSteps()
	 */
	@Override
	public List<BowlModStepDto> listBowlModSteps() {
		return bowlDao.listBowlModSteps();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#listJoinedBowlModificationsByBowlId(Long)
	 */
	@Override
	public List<BowlModificationDto> listJoinedBowlModificationsByBowlId(Long bowlId) {
		return bowlDao.listJoinedBowlModificationsByBowlId(bowlId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#merge(dto.BowlDto)
	 */
	@Override
	public void merge(@NotNull BowlDto bowlDto) {
		bowlDao.merge(bowlDto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#register(dto.BowlDto)
	 */
	@Override
	public void register(@NotNull BowlDto bowlDto) {
		bowlDao.register(bowlDto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#merge(dto.BowlModDto)
	 */
	@Override
	public void merge(@NotNull BowlModDto bowlModDto) {
		bowlDao.merge(bowlModDto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#register(dto.BowlModDto)
	 */
	@Override
	public void register(@NotNull BowlModDto bowlModDto) {
		bowlDao.register(bowlModDto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#merge(dto.BowlModItemDto)
	 */
	@Override
	public void merge(@NotNull BowlModItemDto bowlModItemDto) {
		bowlDao.merge(bowlModItemDto);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BowlService#register(dto.BowlModItemDto)
	 */
	@Override
	public void register(@NotNull BowlModItemDto bowlModItemDto) {
		bowlDao.register(bowlModItemDto);
	}

}
