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

import dao.BotanicSystemDao;
import dto.BotanicSystemDto;

/**
 * Implementation of {@code BotanicSystemService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code BotanicSystem} data from
 * underlying data store.
 *
 * @author mbsusr01
 */
public class BotanicSystemServiceImpl extends AbstractService implements BotanicSystemService {

	/**
	 * The {@code BotanicSystemDao} instance
	 */
	@Inject
	private BotanicSystemDao botanicSystemDao;

	/**
	 * Constructor.
	 */
	public BotanicSystemServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemById(java.lang.Long)
	 */
	@Override
	public BotanicSystemDto getBotanicSystemById(@NotNull Long id) {
		return botanicSystemDao.getBotanicSystemById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemById(java.lang.String)
	 */
	@Override
	public BotanicSystemDto getBotanicSystemById(@NotNull String id) {
		return botanicSystemDao.getBotanicSystemById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemMaxOrdinal()
	 */
	@Override
	public BotanicSystemDto getBotanicSystemMaxOrdinal() {
		return botanicSystemDao.getBotanicSystemMaxOrdinal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemMaxOrderIndex()
	 */
	@Override
	public BotanicSystemDto getBotanicSystemMaxOrderIndex() {
		return botanicSystemDao.getBotanicSystemMaxOrderIndex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemMaxFamilyIndex()
	 */
	@Override
	public BotanicSystemDto getBotanicSystemMaxFamilyIndex() {
		return botanicSystemDao.getBotanicSystemMaxFamilyIndex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemMaxSubFamilyIndex()
	 */
	@Override
	public BotanicSystemDto getBotanicSystemMaxSubFamilyIndex() {
		return botanicSystemDao.getBotanicSystemMaxSubFamilyIndex();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * services.BotanicSystemService#getBotanicSystemByOrder(java.lang.String)
	 */
	@Override
	public String getBotanicSystemByOrder(@NotNull String order) {
		return botanicSystemDao.getBotanicSystemByOrder(order);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * services.BotanicSystemService#getBotanicSystemByFamily(java.lang.String)
	 */
	@Override
	public String getBotanicSystemByFamily(@NotNull String family) {
		return botanicSystemDao.getBotanicSystemByFamily(family);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemBySubFamily(java.lang.
	 * String)
	 */
	@Override
	public String getBotanicSystemBySubFamily(@NotNull String subFamily) {
		return botanicSystemDao.getBotanicSystemBySubFamily(subFamily);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#getBotanicSystemId(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Long getBotanicSystemId(@NotNull String order, String family, String subFamily) {
		return botanicSystemDao.getBotanicSystemId(order, family, subFamily);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#listBotanicSystem()
	 */
	@Override
	public List<BotanicSystemDto> listBotanicSystem() {
		return botanicSystemDao.listBotanicSystem();
		// List<BotanicSystemDto> botanicSystemList =
		// ninjaCache.get("botanicSystem", List.class);
		// if (botanicSystemList == null) {
		// botanicSystemList = botanicSystemDao.listBotanicSystem();
		// ninjaCache.set("botanicSystems", botanicSystem, "30mn");
		// }
		// return botanicSystemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#listBotanicSystemOrder()
	 */
	@Override
	public List<String> listBotanicSystemOrder() {
		return botanicSystemDao.listBotanicSystemOrder();
		// List<String> botanicSystemOrderList =
		// ninjaCache.get("botanicSystemOrder", List.class);
		// if (botanicSystemOrderList == null) {
		// botanicSystemOrderList = botanicSystemDao.listBotanicSystemOrder();
		// ninjaCache.set("botanicSystemOrder", botanicSystemOrder, "30mn");
		// }
		// return botanicSystemOrderList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#listBotanicSystemFamily()
	 */
	@Override
	public List<String> listBotanicSystemFamily() {
		return botanicSystemDao.listBotanicSystemFamily();
		// List<String> botanicSystemFamilyList =
		// ninjaCache.get("botanicSystemFamily", List.class);
		// if (botanicSystemFamilyList == null) {
		// botanicSystemFamilyList = botanicSystemDao.listBotanicSystemFamily();
		// ninjaCache.set("botanicSystemFamily", botanicSystemFamily, "30mn");
		// }
		// return botanicSystemFamilyList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.BotanicSystemService#listBotanicSystemSubFamily()
	 */
	@Override
	public List<String> listBotanicSystemSubFamily() {
		return botanicSystemDao.listBotanicSystemSubFamily();
		// List<String> botanicSystemSubFamilyList =
		// ninjaCache.get("botanicSystemSubFamily", List.class);
		// if (botanicSystemSubFamilyList == null) {
		// botanicSystemSubFamilyList =
		// botanicSystemDao.listBotanicSystemSubFamily();
		// ninjaCache.set("botanicSystemSubFamily", botanicSystemSubFamily,
		// "30mn");
		// }
		// return botanicSystemSubFamilyList;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.BotanicSystemService#register(dto.BotanicSystemDto)
	 */
	@Override
	public void register(@NotNull BotanicSystemDto botanicSystemDto) {
		botanicSystemDao.register(botanicSystemDto);
	}

}
