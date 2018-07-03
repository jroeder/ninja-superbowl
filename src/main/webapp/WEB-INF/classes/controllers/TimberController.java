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
package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dto.BotanicSystemDto;
import dto.Dto;
import dto.GeoRegionDto;
import dto.TimberDto;
import entity.BotanicSystem;
import entity.GeoRegion;
import forms.Form;
import forms.TimberForm;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import services.BotanicSystemService;
import services.GeoRegionService;
import services.TimberService;
import types.Checker;
import types.SuperbowlHelper;
import types.Whitespace;

/**
 * Controller instance to handle user requests regarding a {@code Timber}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
public class TimberController extends AbstractEntityController {

	/**
	 * The {@code BotanicSystemService} instance
	 */
	@Inject
	private BotanicSystemService botanicSystemService;

	/**
	 * The {@code GeoRegionService} instance
	 */
	@Inject
	private GeoRegionService geoRegionService;

	/**
	 * The {@code TimberService} instance
	 */
	@Inject
	private TimberService timberService;

	/**
	 * Default {@code GeoRegion} identifier for european timber -> value is '10'
	 */
	private static final Long DEFAULT_GEOREGION_ID = 10L;

	/**
	 * Default {@code GeoRegion} index for {@code Europe} -> value is '9'
	 */
	private static final Integer DEFAULT_GEOREGION_INDEX = 9;

	/**
	 * Default {@code GeoRegion} code for {@code Europe} -> value is 'EU'
	 */
	private static final String DEFAULT_GEOREGION_CODE = "EU";

	/**
	 * Default {@code GeoRegion} name for {@code Europe} -> value is 'Europa'
	 */
	private static final String DEFAULT_GEOREGION_NAME = "Europa";

	/**
	 * Default {@code BotanicSystem} identifier for BORAGINALES -> value is '1'
	 */
	private static final Long DEFAULT_BOTANICSYSTEM_ID = 1L;

	/**
	 * Default {@code BotanicSystem} index -> value is '0'
	 */
	private static final Integer DEFAULT_BOTANICSYSTEM_INDEX = 0;

	/**
	 * Default {@code BotanicSystem} order index -> value is '0'
	 */
	private static final Integer DEFAULT_BOTANICSYSTEM_ORDER_INDEX = 0;

	/**
	 * Default {@code BotanicSystem} family index -> value is '0'
	 */
	private static final Integer DEFAULT_BOTANICSYSTEM_FAMILY_INDEX = 0;

	/**
	 * Default {@code BotanicSystem} subfamily index -> value is '0'
	 */
	private static final Integer DEFAULT_BOTANICSYSTEM_SUBFAMILY_INDEX = 0;

	/**
	 * Default {@code BotanicSystem} ordinal number -> value is '0'
	 */
	private static final Integer DEFAULT_BOTANICSYSTEM_ORDINAL_NUMBER = 0;

	/**
	 * Default {@code BotanicSystem} order for -> value is 'BORAGINALES'
	 */
	private static final String DEFAULT_BOTANICSYSTEM_ORDER_NAME = "BORAGINALES";

	/**
	 * Default {@code BotanicSystem} family for -> value is 'Boraginaceae'
	 */
	private static final String DEFAULT_BOTANICSYSTEM_FAMILY_NAME = "Boraginaceae";

	/**
	 * Default {@code BotanicSystem} subfamily for -> value is 'Cordioideae'
	 */
	private static final String DEFAULT_BOTANICSYSTEM_SUBFAMILY_NAME = "Cordioideae";

	/**
	 * Constructor.
	 */
	public TimberController() {
		super();
	}

	/**
	 * Retrieves all timber from data store.
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result list(Context context) {
		Session session = context.getSession();
		session.clear();
		List<TimberDto> timberList = timberService.listTimber();
		return Results.html().render("holzarten", timberList);
	}

	/**
	 * Register a new {@code Timber}.
	 *
	 * @param session
	 *            the Ninja {@code Session} instance
	 * @param context
	 *            the Ninja {@code Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code Timber} table has entries or not
	 * @param geoRegionCode
	 *            the {@code GeoRagion} code
	 * @param botanicSystemOrder
	 *            the {@code BotanicSystem} order value
	 * @param botanicSystemFamily
	 *            the {@code BotanicSystem} family value
	 * @param botanicSystemSubFamily
	 *            the {@code BotanicSystem} sub family value
	 * @return {@code Result} instance
	 */
	public Result registerTimber(Session session, Context context, @Param("emptyTable") Boolean emptyTable,
			@Param("geoRegionCode") String geoRegionCode, @Param("botanicSystemOrder") String botanicSystemOrder,
			@Param("botanicSystemFamily") String botanicSystemFamily,
			@Param("botanicSystemSubFamily") String botanicSystemSubFamily) {
		logger.info("===== Register Timber =====");
		logger.info("TimberController.registerTimber -> Session id.............: " + session.getId());
		logger.info("TimberController.registerTimber -> Empty Table............: " + emptyTable.toString());
		logger.info("TimberController.registerTimber -> GeoRegion Code.........: " + geoRegionCode);
		logger.info("TimberController.registerTimber -> BotanicSystem Order....: " + botanicSystemOrder);
		logger.info("TimberController.registerTimber -> BotanicSystem Family...: " + botanicSystemFamily);
		logger.info("TimberController.registerTimber -> BotanicSystem SubFamily: " + botanicSystemSubFamily);
		logger.info("=== End Register Timber ===");

		Result result = Results.html();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tEmptyTable", emptyTable.toString());

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		map.put("geoRegionList", geoRegionList);

		List<BotanicSystemDto> botanicSystemList = botanicSystemService.listBotanicSystem();
		map.put("botanicSystemList", botanicSystemList);

		List<String> botanicSystemOrderList = botanicSystemService.listBotanicSystemOrder();
		map.put("botanicSystemOrderList", botanicSystemOrderList);

		List<String> botanicSystemFamilyList = botanicSystemService.listBotanicSystemFamily();
		map.put("botanicSystemFamilyList", botanicSystemFamilyList);

		List<String> botanicSystemSubFamilyList = botanicSystemService.listBotanicSystemSubFamily();
		List<String> tempList = new ArrayList<String>();
		tempList.add("<Unbekannt>");
		Iterator<String> it = botanicSystemSubFamilyList.iterator();
		while (it.hasNext()) {
			tempList.add(it.next());
		}
		map.put("botanicSystemSubFamilyList", tempList);

		Long selectedGeoRegionId = null;
		Integer selectedGeoRegionIndex = null;
		String selectedGeoRegionCode = null;
		String selectedGeoRegionName = null;

		String selectedBotanicSystemOrder = null;
		String selectedBotanicSystemFamily = null;
		String selectedBotanicSystemSubFamily = null;

		TimberDto timberDto = null;
		TimberDto sessionTimberDto = null;

		Integer timberIndex = null;

		Long botanicSystemId = null;

		if (emptyTable) {
			timberIndex = 0;
		} else {
			// Timber max index ermitteln
			timberDto = timberService.getTimberMaxIndex();
			timberIndex = timberDto.getIndex() + 1;
		}

		map.put("timberIndex", timberIndex);

		/*
		 * Analyse der Parameter mit entsprechender Verarbeitung
		 */
		if (geoRegionCode.isEmpty() && (botanicSystemOrder == null || botanicSystemOrder.isEmpty())
				&& (botanicSystemFamily == null || botanicSystemFamily.isEmpty())
				&& (botanicSystemSubFamily == null || botanicSystemSubFamily.isEmpty())) {
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~");
			logger.info("~~~ Nothing Selected ~~~");
			logger.info("~~~~~~~~~~~~~~~~~~~~~~~~");
			geoRegionCode = DEFAULT_GEOREGION_CODE;
			selectedGeoRegionId = DEFAULT_GEOREGION_ID;
			selectedGeoRegionIndex = DEFAULT_GEOREGION_INDEX;
			selectedGeoRegionCode = DEFAULT_GEOREGION_CODE;
			selectedGeoRegionName = DEFAULT_GEOREGION_NAME;
			botanicSystemId = DEFAULT_BOTANICSYSTEM_ID;
			selectedBotanicSystemOrder = DEFAULT_BOTANICSYSTEM_ORDER_NAME;
			selectedBotanicSystemFamily = DEFAULT_BOTANICSYSTEM_FAMILY_NAME;
			selectedBotanicSystemSubFamily = DEFAULT_BOTANICSYSTEM_SUBFAMILY_NAME; // tempList.get(0);
		} else {
			//
			sessionTimberDto = (TimberDto) getDtoFromSession(session);
			//
			if (!geoRegionCode.isEmpty() && (botanicSystemOrder == null || botanicSystemOrder.isEmpty())
					&& (botanicSystemFamily == null || botanicSystemFamily.isEmpty())
					&& (botanicSystemSubFamily == null || botanicSystemSubFamily.isEmpty())) {
				logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~");
				logger.info("~~~ GeoRegion Selected ~~~");
				logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~");
				selectedGeoRegionIndex = geoRegionService.getGeoRegionByCode(geoRegionCode).getIndex();
				selectedGeoRegionId = geoRegionList.get(selectedGeoRegionIndex).getId();
				selectedGeoRegionCode = geoRegionList.get(selectedGeoRegionIndex).getCode();
				selectedGeoRegionName = geoRegionList.get(selectedGeoRegionIndex).getName();
				botanicSystemId = DEFAULT_BOTANICSYSTEM_ID;
				selectedBotanicSystemOrder = DEFAULT_BOTANICSYSTEM_ORDER_NAME;
				selectedBotanicSystemFamily = DEFAULT_BOTANICSYSTEM_FAMILY_NAME;
				selectedBotanicSystemSubFamily = DEFAULT_BOTANICSYSTEM_SUBFAMILY_NAME; // tempList.get(0);
			} else {
				if (geoRegionCode.isEmpty() && (botanicSystemOrder != null && !botanicSystemOrder.isEmpty())
						&& (botanicSystemFamily != null && !botanicSystemFamily.isEmpty())
						&& (botanicSystemSubFamily != null && !botanicSystemSubFamily.isEmpty())) {
					logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					logger.info("~~~ BotanicSystem Order or Family or SubFamilySelected ~~~");
					logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					// timber mit code ermitteln und werte aus session
					// übernehmen
					// diese Kombination sollte nicht vorkommen
					geoRegionCode = DEFAULT_GEOREGION_CODE;
					selectedGeoRegionId = DEFAULT_GEOREGION_ID;
					selectedGeoRegionIndex = DEFAULT_GEOREGION_INDEX;
					selectedGeoRegionCode = DEFAULT_GEOREGION_CODE;
					selectedGeoRegionName = DEFAULT_GEOREGION_NAME;
					botanicSystemId = DEFAULT_BOTANICSYSTEM_ID;
					selectedBotanicSystemOrder = DEFAULT_BOTANICSYSTEM_ORDER_NAME;
					selectedBotanicSystemFamily = DEFAULT_BOTANICSYSTEM_FAMILY_NAME;
					selectedBotanicSystemSubFamily = DEFAULT_BOTANICSYSTEM_SUBFAMILY_NAME;
				} else {
					selectedGeoRegionIndex = geoRegionService.getGeoRegionByCode(geoRegionCode).getIndex();
					selectedGeoRegionId = geoRegionList.get(selectedGeoRegionIndex).getId();
					selectedGeoRegionCode = geoRegionList.get(selectedGeoRegionIndex).getCode();
					selectedGeoRegionName = geoRegionList.get(selectedGeoRegionIndex).getName();
					selectedBotanicSystemOrder = DEFAULT_BOTANICSYSTEM_ORDER_NAME;
					selectedBotanicSystemFamily = DEFAULT_BOTANICSYSTEM_FAMILY_NAME;
					selectedBotanicSystemSubFamily = DEFAULT_BOTANICSYSTEM_SUBFAMILY_NAME; // tempList.get(0);
					if (botanicSystemOrder != null && !botanicSystemOrder.isEmpty()
							&& botanicSystemOrder.length() > 0) {
						logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						logger.info("~~~ BotanicSystem Order Selected ~~~");
						logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						selectedBotanicSystemOrder = botanicSystemService.getBotanicSystemByOrder(botanicSystemOrder);
						logger.info("selectedBotanicSystemOrder -> {} ", selectedBotanicSystemOrder);
					}
					if (botanicSystemFamily != null && !botanicSystemFamily.isEmpty()
							&& botanicSystemFamily.length() > 0) {
						logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						logger.info("~~~ BotanicSystem Family Selected ~~~");
						logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						selectedBotanicSystemFamily = botanicSystemService
								.getBotanicSystemByFamily(botanicSystemFamily);
						logger.info("selectedBotanicSystemFamily -> {} ", selectedBotanicSystemFamily);
					}
					if (botanicSystemSubFamily != null && !botanicSystemSubFamily.isEmpty()
							&& botanicSystemSubFamily.length() > 0) {
						if (botanicSystemSubFamily.equals("<Unbekannt>")) {
							logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							logger.info("~~~ BotanicSystem SubFamily <Unbekannt> Selected ~~~");
							logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							selectedBotanicSystemSubFamily = EMPTY;
							logger.info("selectedBotanicSystemSubFamily -> {} ", selectedBotanicSystemSubFamily);
						} else {
							logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							logger.info("~~~ BotanicSystem SubFamily Selected ~~~");
							logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							selectedBotanicSystemSubFamily = botanicSystemService
									.getBotanicSystemBySubFamily(botanicSystemSubFamily);
							logger.info("selectedBotanicSystemSubFamily -> {} ", selectedBotanicSystemSubFamily);
						}
					}
					botanicSystemId = botanicSystemService.getBotanicSystemId(selectedBotanicSystemOrder,
							selectedBotanicSystemFamily, selectedBotanicSystemSubFamily);
				}
			}
		}

		map.put("selectedGeoRegionId", selectedGeoRegionId);
		map.put("selectedGeoRegionIndex", selectedGeoRegionIndex);
		map.put("selectedGeoRegionCode", selectedGeoRegionCode);
		map.put("selectedGeoRegionName", selectedGeoRegionName);
		//
		map.put("selectedBotanicSystemOrder", selectedBotanicSystemOrder);
		map.put("selectedBotanicSystemFamily", selectedBotanicSystemFamily);
		if (selectedBotanicSystemSubFamily.equals(EMPTY)) {
			selectedBotanicSystemSubFamily = UNBEKANNT;
		}
		map.put("selectedBotanicSystemSubFamily", selectedBotanicSystemSubFamily);
		//
		map.put("botanicSystemId", botanicSystemId);
		map.put("botanicSystemOrder", selectedBotanicSystemOrder);
		map.put("botanicSystemFamily", selectedBotanicSystemFamily);
		map.put("botanicSystemSubFamily", selectedBotanicSystemSubFamily);

		logExtraData(emptyTable, geoRegionList, botanicSystemList, botanicSystemOrderList, botanicSystemFamilyList,
				botanicSystemSubFamilyList, selectedGeoRegionIndex, geoRegionCode, selectedGeoRegionId,
				selectedGeoRegionCode, botanicSystemId, selectedBotanicSystemOrder, selectedBotanicSystemFamily,
				selectedBotanicSystemSubFamily);

		if (sessionTimberDto != null) {
			logger.info(sessionTimberDto.asString());
			if (sessionTimberDto.getIndex() != null) {
				map.put("tIndex", sessionTimberDto.getIndex());
			} else {
				map.put("tIndex", EMPTY);
			}
			if (sessionTimberDto.getType() != null) {
				map.put("tType", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getType()));
			} else {
				map.put("tType", EMPTY);
			}
			if (sessionTimberDto.getCode() != null) {
				map.put("tCode", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getCode()));
			} else {
				map.put("tCode", EMPTY);
			}
			if (sessionTimberDto.getName() != null) {
				map.put("tName", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getName()));
			} else {
				map.put("tName", EMPTY);
			}
			if (sessionTimberDto.getImageName() != null) {
				map.put("tImageName", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getImageName()));
			} else {
				map.put("tImageName", EMPTY);
			}
			if (sessionTimberDto.getAcademicName() != null) {
				map.put("tAcademicName", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getAcademicName()));
			} else {
				map.put("tAcademicName", EMPTY);
			}
			if (sessionTimberDto.getGrossDensity() != null) {
				map.put("tGrossDensity", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getGrossDensity()));
			} else {
				map.put("tGrossDensity", EMPTY);
			}
			if (sessionTimberDto.getTensileStrength() != null) {
				map.put("tTensileStrength",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getTensileStrength()));
			} else {
				map.put("tTensileStrength", EMPTY);
			}
			if (sessionTimberDto.getBurstStrength() != null) {
				map.put("tBurstStrength", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getBurstStrength()));
			} else {
				map.put("tBurstStrength", EMPTY);
			}
			if (sessionTimberDto.getBendingStrength() != null) {
				map.put("tBendingStrength",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getBendingStrength()));
			} else {
				map.put("tBendingStrength", EMPTY);
			}
			if (sessionTimberDto.getShearStrength() != null) {
				map.put("tShearStrength", SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getShearStrength()));
			} else {
				map.put("tShearStrength", EMPTY);
			}
			if (sessionTimberDto.getBrinellHardnessOne() != null) {
				map.put("tBrinellHardnessOne",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getBrinellHardnessOne()));
			} else {
				map.put("tBrinellHardnessOne", EMPTY);
			}
			if (sessionTimberDto.getBrinellHardnessTwo() != null) {
				map.put("tBrinellHardnessTwo",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getBrinellHardnessTwo()));
			} else {
				map.put("tBrinellHardnessTwo", EMPTY);
			}
			if (sessionTimberDto.getTangentShrinkage() != null) {
				map.put("tTangentShrinkage",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getTangentShrinkage()));
			} else {
				map.put("tTangentShrinkage", EMPTY);
			}
			if (sessionTimberDto.getRadialShrinkage() != null) {
				map.put("tRadialShrinkage",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberDto.getRadialShrinkage()));
			} else {
				map.put("tRadialShrinkage", EMPTY);
			}
		} else {
			map.put("tIndex", timberIndex.toString());
			map.put("tType", EMPTY);
			map.put("tCode", EMPTY);
			map.put("tName", EMPTY);
			map.put("tImageName", EMPTY);
			map.put("tAcademicName", EMPTY);
			map.put("tGrossDensity", EMPTY);
			map.put("tTensileStrength", EMPTY);
			map.put("tBurstStrength", EMPTY);
			map.put("tBendingStrength", EMPTY);
			map.put("tShearStrength", EMPTY);
			map.put("tBrinellHardnessOne", EMPTY);
			map.put("tBrinellHardnessTwo", EMPTY);
			map.put("tTangentShrinkage", EMPTY);
			map.put("tRadialShrinkage", EMPTY);
		}

		return result.render(map).template("views/TimberController/registerTimber.ftl.html");
		// return
		// Results.html().render(map).template("views/TimberController/registerTimber.ftl.html");
	}

	/**
	 * Register {@code Timber} confirmation.
	 *
	 * @param timberForm
	 *            the {@code Ninja TimberForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerTimberConfirmation(@JSR303Validation TimberForm timberForm, Session session,
			Validation validation, Context context) {
		logger.info("TimberController.registerTimberConfirmation -> Session id...: " + session.getId());
		logger.info("TimberController.registerTimberConfirmation -> Session empty: " + session.isEmpty());
		logger.info("TimberController.registerTimberConfirmation -> Context......: " + context.getRequestPath());
		logger.info("TimberController.registerTimberConfirmation -> Violations...: " + validation.hasViolations());
		logger.info("===== Register Timber Confirmation =====");
		logger.info("TimberController:TimberOriginForm -> GeoRegionId...........: " + timberForm.getGeoRegionId());
		logger.info("TimberController:TimberOriginForm -> GeoRegionCode.........: " + timberForm.getGeoRegionCode());
		logger.info("TimberController:TimberOriginForm -> GeoRegionName.........: " + timberForm.getGeoRegionName());
		logger.info("TimberController:TimberOriginForm -> BotanicSystemId.......: " + timberForm.getBotanicSystemId());
		logger.info(
				"TimberController:TimberOriginForm -> BotanicSystemOrder....: " + timberForm.getBotanicSystemOrder());
		logger.info(
				"TimberController:TimberOriginForm -> BotanicSystemFamily...: " + timberForm.getBotanicSystemFamily());
		logger.info("TimberController:TimberOriginForm -> BotanicSystemSubFamily: "
				+ timberForm.getBotanicSystemSubFamily());
		logger.info("TimberController:TimberOriginForm -> Type..................: " + timberForm.getType());
		logger.info("TimberController:TimberOriginForm -> Code..................: " + timberForm.getCode());
		logger.info("TimberController:TimberOriginForm -> Name..................: " + timberForm.getName());
		logger.info("TimberController:TimberOriginForm -> ImageName.............: " + timberForm.getImageName());
		logger.info("TimberController:TimberOriginForm -> AcademicName..........: " + timberForm.getAcademicName());
		logger.info("TimberController:TimberOriginForm -> GrossDensity..........: " + timberForm.getGrossDensity());
		logger.info("TimberController:TimberOriginForm -> TensileStrength.......: " + timberForm.getTensileStrength());
		logger.info("TimberController:TimberOriginForm -> BurstStrength.........: " + timberForm.getBurstStrength());
		logger.info("TimberController:TimberOriginForm -> BendingStrength.......: " + timberForm.getBendingStrength());
		logger.info("TimberController:TimberOriginForm -> ShearStrength.........: " + timberForm.getShearStrength());
		logger.info(
				"TimberController:TimberOriginForm -> BrinellHardnessOne....: " + timberForm.getBrinellHardnessOne());
		logger.info(
				"TimberController:TimberOriginForm -> BrinellHardnessTwo....: " + timberForm.getBrinellHardnessTwo());
		logger.info("TimberController:TimberOriginForm -> TangentShrinkage......: " + timberForm.getTangentShrinkage());
		logger.info("TimberController:TimberOriginForm -> RadialShrinkage.......: " + timberForm.getRadialShrinkage());
		logger.info("TimberController:TimberOriginForm -> EmptyTable............: " + timberForm.getEmptyTable());
		logger.info("=== End Register Timber Confirmation ===");

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field............................: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("geoRegionId".equals(fieldViolation.getFieldKey())
						&& "{timber.georegion.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				if ("geoRegionCode".equals(fieldViolation.getFieldKey())
						&& "{timber.georegion.code.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				// if ("geoRegionName".equals(fieldViolation.getFieldKey())
				// &&
				// "{timber.georegion.name.blank}".equals(fieldViolation.getMessageKey()))
				// {
				// throw new BadRequestException();
				// }
				if ("botanicSystemId".equals(fieldViolation.getFieldKey())
						&& "{timber.botanicSystem.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				// if ("botanicSystemOrder".equals(fieldViolation.getFieldKey())
				// &&
				// "{timber.botanicSystem.order.blank}".equals(fieldViolation.getMessageKey()))
				// {
				// throw new BadRequestException();
				// }
				// if
				// ("botanicSystemFamily".equals(fieldViolation.getFieldKey())
				// &&
				// "{timber.botanicSystem.family.blank}".equals(fieldViolation.getMessageKey()))
				// {
				// throw new BadRequestException();
				// }
				// if
				// ("botanicSystemSubFamily".equals(fieldViolation.getFieldKey())
				// &&
				// "{timber.botanicSystem.sub.family.null}".equals(fieldViolation.getMessageKey()))
				// {
				// throw new BadRequestException();
				// }
				if ("code".equals(fieldViolation.getFieldKey())
						&& "{timber.code.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				if ("name".equals(fieldViolation.getFieldKey())
						&& "{timber.name.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				if ("type".equals(fieldViolation.getFieldKey())
						&& "{timber.type.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Long botanicSystemId = null;
			String botanicSystemOrder = null;
			String botanicSystemFamily = null;
			String botanicSystemSubFamily = null;

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();

			List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();

			List<BotanicSystemDto> botanicSystemList = botanicSystemService.listBotanicSystem();

			List<String> botanicSystemOrderList = botanicSystemService.listBotanicSystemOrder();

			List<String> botanicSystemFamilyList = botanicSystemService.listBotanicSystemFamily();

			List<String> botanicSystemSubFamilyList = botanicSystemService.listBotanicSystemSubFamily();
			List<String> tempList = new ArrayList<String>();
			tempList.add("<Unbekannt>");
			Iterator<String> it = botanicSystemSubFamilyList.iterator();
			while (it.hasNext()) {
				tempList.add(it.next());
			}

			GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionByCode(timberForm.getGeoRegionCode());
			map.put("geoRegionIndex", geoRegionDto.getIndex());
			map.put("geoRegionCode", geoRegionDto.getCode());
			map.put("selectedGeoRegion", geoRegionDto.getRegion());
			map.put("selectedGeoRegionId", geoRegionDto.getId());
			map.put("selectedGeoRegionIndex", geoRegionDto.getIndex());
			map.put("selectedGeoRegionCode", geoRegionDto.getCode());
			//
			BotanicSystemDto botanicSystemDto = botanicSystemService
					.getBotanicSystemById(timberForm.getBotanicSystemId());
			botanicSystemId = botanicSystemDto.getId();
			botanicSystemOrder = botanicSystemDto.getOrder();
			botanicSystemFamily = botanicSystemDto.getFamily();
			botanicSystemSubFamily = botanicSystemDto.getSubFamily();
			//
			map.put("selectedBotanicSystemId", botanicSystemId);
			map.put("selectedBotanicSystemOrder", botanicSystemOrder);
			map.put("selectedBotanicSystemFamily", botanicSystemFamily);
			map.put("selectedBotanicSystemSubFamily", botanicSystemSubFamily);
			//
			map.put("botanicSystemId", botanicSystemId);
			map.put("botanicSystemOrder", botanicSystemOrder);
			map.put("botanicSystemFamily", botanicSystemFamily);
			map.put("botanicSystemSubFamily", botanicSystemSubFamily);
			//
			map.put("tIndex", timberForm.getIndex());
			map.put("tCode", SuperbowlHelper.replaceSpaceByMarker(timberForm.getCode()));
			map.put("tName", SuperbowlHelper.replaceSpaceByMarker(timberForm.getName()));
			map.put("tType", SuperbowlHelper.replaceSpaceByMarker(timberForm.getType()));
			map.put("tImageName", SuperbowlHelper.replaceSpaceByMarker(timberForm.getImageName()));
			map.put("tAcademicName", SuperbowlHelper.replaceSpaceByMarker(timberForm.getAcademicName()));
			map.put("tGrossDensity", timberForm.getGrossDensity());
			map.put("tTensileStrength", timberForm.getTensileStrength());
			map.put("tBurstStrength", timberForm.getBurstStrength());
			map.put("tBendingStrength", timberForm.getBendingStrength());
			map.put("tShearStrength", timberForm.getShearStrength());
			map.put("tBrinellHardnessOne", timberForm.getBrinellHardnessOne());
			map.put("tBrinellHardnessTwo", timberForm.getBrinellHardnessTwo());
			map.put("tTangentShrinkage", timberForm.getTangentShrinkage());
			map.put("tRadialShrinkage", timberForm.getRadialShrinkage());
			map.put("tEmptyTable", timberForm.getEmptyTable());

			result.render(map);

			result.render("geoRegionList", geoRegionList);
			result.render("botanicSystemList", botanicSystemList);
			result.render("botanicSystemOrderList", botanicSystemOrderList);
			result.render("botanicSystemFamilyList", botanicSystemFamilyList);
			result.render("botanicSystemSubFamilyList", tempList);

			result.render("violations", validation.getViolations());
			result.render("timber", timberForm);
			result.template("views/TimberController/registerTimber.ftl.html");

			session.save(context);

			return result;

		} else {

			putFormIntoSession(timberForm, session);

			Result result = Results.html();
			result.render("geoRegionId", timberForm.getGeoRegionId());
			result.render("geoRegionCode", timberForm.getGeoRegionCode());
			result.render("geoRegionName", timberForm.getGeoRegionCode());
			result.render("botanicSystemId", timberForm.getBotanicSystemId());
			result.render("botanicSystemOrder", timberForm.getBotanicSystemOrder());
			result.render("botanicSystemFamily", timberForm.getBotanicSystemFamily());
			result.render("botanicSystemSubFamily", timberForm.getBotanicSystemSubFamily());
			result.render("index", timberForm.getIndex());
			result.render("code", SuperbowlHelper.replaceSpaceByMarker(timberForm.getCode()));
			result.render("name", SuperbowlHelper.replaceSpaceByMarker(timberForm.getName()));
			result.render("type", SuperbowlHelper.replaceSpaceByMarker(timberForm.getType()));
			result.render("imageName", SuperbowlHelper.replaceSpaceByMarker(timberForm.getImageName()));
			result.render("academicName", SuperbowlHelper.replaceSpaceByMarker(timberForm.getAcademicName()));
			result.render("grossDensity", timberForm.getGrossDensity());
			result.render("tensileStrength", timberForm.getTensileStrength());
			result.render("burstStrength", timberForm.getBurstStrength());
			result.render("bendingStrength", timberForm.getBendingStrength());
			result.render("shearStrength", timberForm.getShearStrength());
			result.render("brinellHardnessOne", timberForm.getBrinellHardnessOne());
			result.render("brinellHardnessTwo", timberForm.getBrinellHardnessTwo());
			result.render("tangentShrinkage", timberForm.getTangentShrinkage());
			result.render("radialShrinkage", timberForm.getRadialShrinkage());
			result.render("emptyTable", timberForm.getEmptyTable());

			result.render("timber", timberForm);

			session.save(context);

			return result;

		}
	}

	/**
	 * Register {@code Timber} completion.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerTimberCompletion(Session session, Context context) {
		logger.info("TimberController.registerTimberCompletion -> Session id: " + session.getId());
		logger.info("TimberController.registerTimberCompletion -> Context: " + context.getRequestPath());

		TimberDto timberDto = (TimberDto) getDtoFromSession(session);

		timberService.register(timberDto);

		session.clear();

		return Results.ok().redirect("/superbowl/timber");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.Controller#getDtoFromSession(ninja.session.Session)
	 */
	@Override
	public Dto getDtoFromSession(Session session) {
		logger.info("TimberOriginController.getDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("timber.id") != null) {
			id = new Long(session.get("timber.id"));
		}
		Integer version = null;
		if (session.get("timber.version") != null) {
			version = new Integer(session.get("timber.version")).intValue();
		}
		GeoRegion geoRegion = null;
		if (session.get("timber.georegion.id") != null) {
			String geoRegionId = session.get("timber.georegion.id");
			geoRegion = new GeoRegion(geoRegionService.getGeoRegionById(geoRegionId));
		}
		BotanicSystem botanicSystem = null;
		if (session.get("timber.botanicsystem.id") != null) {
			String botanicSystemId = session.get("timber.botanicsystem.id");
			botanicSystem = new BotanicSystem(botanicSystemService.getBotanicSystemById(botanicSystemId));
		}
		String index = null;
		if (session.get("timber.index") != null) {
			index = session.get("timber.index");
		}
		String code = null;
		if (session.get("timber.code") != null) {
			code = session.get("timber.code");
		}
		String type = null;
		if (session.get("timber.type") != null) {
			type = session.get("timber.type");
		}
		String name = null;
		if (session.get("timber.name") != null) {
			name = session.get("timber.name");
		}
		String imageName = null;
		if (session.get("timber.image.name") != null) {
			imageName = session.get("timber.image.name");
		}
		String academicName = null;
		if (session.get("timber.academic.name") != null) {
			academicName = session.get("timber.academic.name");
		}
		String grossDensity = null;
		if (session.get("timber.gross.density") != null) {
			grossDensity = session.get("timber.gross.density");
		}
		String tensileStrength = null;
		if (session.get("timber.tensile.strength") != null) {
			tensileStrength = session.get("timber.tensile.strength");
		}
		String burstStrength = null;
		if (session.get("timber.burst.strength") != null) {
			burstStrength = session.get("timber.burst.strength");
		}
		String bendingStrength = null;
		if (session.get("timber.bending.strength") != null) {
			bendingStrength = session.get("timber.bending.strength");
		}
		String shearStrength = null;
		if (session.get("timber.shear.strength") != null) {
			shearStrength = session.get("timber.shear.strength");
		}
		String brinellHardnessOne = null;
		if (session.get("timber.brinell.hardness.one") != null) {
			brinellHardnessOne = session.get("timber.brinell.hardness.one");
		}
		String brinellHardnessTwo = null;
		if (session.get("timber.brinell.hardness.two") != null) {
			brinellHardnessTwo = session.get("timber.brinell.hardness.two");
		}
		String tangentShrinkage = null;
		if (session.get("timber.tangent.shrinkage") != null) {
			tangentShrinkage = session.get("timber.tangent.shrinkage");
		}
		String radialShrinkage = null;
		if (session.get("timber.radial.shrinkage") != null) {
			radialShrinkage = session.get("timber.radial.shrinkage");
		}
		String emptyTable = null;
		if (session.get("timber.origin.empty.table") != null) {
			emptyTable = session.get("timber.origin.empty.table");
		}

		logTimberCompletion(session);
		TimberDto timberDto = null;

		if (index == null) {
			Integer intIndex = null;
			timberDto = new TimberDto(id, version, intIndex, geoRegion, botanicSystem, type, code, name, imageName,
					academicName, grossDensity, tensileStrength, burstStrength, bendingStrength, shearStrength,
					brinellHardnessOne, brinellHardnessTwo, tangentShrinkage, radialShrinkage, emptyTable);
		} else {
			timberDto = new TimberDto(id, version, new Integer(index), geoRegion, botanicSystem, type, code, name,
					imageName, academicName, grossDensity, tensileStrength, burstStrength, bendingStrength,
					shearStrength, brinellHardnessOne, brinellHardnessTwo, tangentShrinkage, radialShrinkage,
					emptyTable);
		}

		return timberDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#putDtoIntoSession(dto.Dto,
	 * ninja.session.Session)
	 */
	@Override
	public void putDtoIntoSession(Dto dto, Session session) {
		// ToDo: Implement code to put timber dto into session
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.Controller#putFormIntoSession(forms.Form,
	 * ninja.session.Session)
	 */
	@Override
	public void putFormIntoSession(Form form, Session session) {
		logger.info("TimberController.putFormIntoSession -> Session id: " + session.getId());

		TimberForm timberForm = (TimberForm) form;

		logTimberConfirmation(timberForm);

		String data = null;

		logger.info("=== put timber form data into session ===");

		session.put("timber.id", null);
		logger.info("id....................: {}", timberForm.getId());
		if (timberForm.getId() == null) {
			session.put("timber.id", null);
		} else {
			session.put("timber.id", timberForm.getId());
		}

		logger.info("version...............: {}", timberForm.getVersion());
		if (timberForm.getVersion() == null) {
			session.put("timber.version", "0");
		} else {
			session.put("timber.version", timberForm.getVersion());
		}

		data = timberForm.getIndex().toString();
		logger.info("index.................: {}", data);
		session.put("timber.index", data);

		data = timberForm.getGeoRegionId();
		logger.info("georegionId...........: {}", data);
		session.put("timber.georegion.id", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getGeoRegionCode())
				? timberForm.getGeoRegionCode() : Whitespace.EMPTY.getValue());
		logger.info("georegionCode.........: {}", data);
		session.put("timber.georegion.code", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getGeoRegionName())
				? timberForm.getGeoRegionName() : Whitespace.EMPTY.getValue());
		logger.info("georegionName.........: {}", data);
		session.put("timber.georegion.name", data);

		data = timberForm.getBotanicSystemId();
		logger.info("botanicSystemId.......: {}", data);
		session.put("timber.botanicsystem.id", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBotanicSystemOrder())
				? timberForm.getBotanicSystemOrder() : Whitespace.EMPTY.getValue());
		logger.info("botanicSystemOrder....: {}", data);
		session.put("timber.botanicsystem.order", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBotanicSystemFamily())
				? timberForm.getBotanicSystemFamily() : Whitespace.EMPTY.getValue());
		logger.info("botanicSystemFamily...: {}", data);
		session.put("timber.botanicsystem.family", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBotanicSystemSubFamily())
				? timberForm.getBotanicSystemSubFamily() : Whitespace.EMPTY.getValue());
		logger.info("botanicSystemSubFamily: {}", data);
		session.put("timber.botanicsystem.subfamily", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getCode()) ? timberForm.getCode()
				: Whitespace.EMPTY.getValue());
		logger.info("code..................: {}", data);
		session.put("timber.code", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getType()) ? timberForm.getType()
				: Whitespace.EMPTY.getValue());
		logger.info("type..................: {}", data);
		session.put("timber.type", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getName()) ? timberForm.getName()
				: Whitespace.EMPTY.getValue());
		logger.info("name..................: {}", data);
		session.put("timber.name", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getImageName())
				? timberForm.getImageName() : Whitespace.EMPTY.getValue());
		logger.info("timberImageName.......: {}", data);
		session.put("timber.image.name", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getAcademicName())
				? timberForm.getAcademicName() : Whitespace.EMPTY.getValue());
		logger.info("academicName..........: {}", data);
		session.put("timber.academic.name", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getGrossDensity())
				? timberForm.getGrossDensity() : Whitespace.EMPTY.getValue());
		logger.info("GrossDensity..........: {}", data);
		session.put("timber.gross.density", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getTensileStrength())
				? timberForm.getTensileStrength() : Whitespace.EMPTY.getValue());
		logger.info("tensileStrength.......: {}", data);
		session.put("timber.tensile.strength", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBurstStrength())
				? timberForm.getBurstStrength() : Whitespace.EMPTY.getValue());
		logger.info("burstStrength.........: {}", data);
		session.put("timber.burst.strength", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBendingStrength())
				? timberForm.getBendingStrength() : Whitespace.EMPTY.getValue());
		logger.info("bendingStrength.......: {}", data);
		session.put("timber.bending.strength", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getShearStrength())
				? timberForm.getShearStrength() : Whitespace.EMPTY.getValue());
		logger.info("shearStrength.........: {}", data);
		session.put("timber.shear.strength", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBrinellHardnessOne())
				? timberForm.getBrinellHardnessOne() : Whitespace.EMPTY.getValue());
		logger.info("brinellHardnessOne....: {}", data);
		session.put("timber.brinell.hardness.one", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getBrinellHardnessTwo())
				? timberForm.getBrinellHardnessTwo() : Whitespace.EMPTY.getValue());
		logger.info("brinellHardnessTwo....: {}", data);
		session.put("timber.brinell.hardness.two", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getTangentShrinkage())
				? timberForm.getTangentShrinkage() : Whitespace.EMPTY.getValue());
		logger.info("tangentShrinkage......: {}", data);
		session.put("timber.tangent.shrinkage", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getRadialShrinkage())
				? timberForm.getRadialShrinkage() : Whitespace.EMPTY.getValue());
		logger.info("radialShrinkage......: {}", data);
		session.put("timber.radial.shrinkage", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberForm.getEmptyTable())
				? timberForm.getEmptyTable() : Whitespace.EMPTY.getValue());
		logger.info("emptyTable...: {}", data);
		session.put("timber.empty.table", data);

		logger.info("=== end put timber form data into session ===");
	}

	/**
	 * Logging of completion of {@code Timber} data.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	private final void logTimberCompletion(Session session) {
		logger.info("TimberController.logTimberCompletion -> Session    id: " + session.getId());
		logger.info("TimberController.logTimberCompletion -> Session empty: " + session.isEmpty());
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.timber.completion"));
		if (isLog) {
			Long id = null;
			if (session.get("timber.id") != null) {
				id = new Long(session.get("timber.id"));
			}
			Integer version = null;
			if (session.get("timber.version") != null) {
				version = new Integer(session.get("timber.version")).intValue();
			}
			logger.info("=== complete register of timber (session data) ===");
			logger.info("id....................: {}", id);
			logger.info("version...............: {}", version);
			logger.info("index.................: {}", session.get("timber.index"));
			logger.info("geoRegionId...........: {}", session.get("timber.georegion.id"));
			logger.info("geoRegionCode.........: {}", session.get("timber.georegion.code"));
			logger.info("geoRegionName.........: {}", session.get("timber.georegion.name"));
			logger.info("botanicSystemId.......: {}", session.get("timber.botanicsystem.id"));
			logger.info("botanicSystemOrder....: {}", session.get("timber.botanicsystem.order"));
			logger.info("botanicSystemFamily...: {}", session.get("timber.botanicsystem.family"));
			logger.info("botanicSystemSubFamily: {}", session.get("timber.botanicsystem.subfamily"));
			logger.info("code..................: {}", session.get("timber.code"));
			logger.info("type..................: {}", session.get("timber.type"));
			logger.info("name..................: {}", session.get("timber.name"));
			logger.info("imageName.............: {}", session.get("timber.image.name"));
			logger.info("academicName..........: {}", session.get("timber.academic.name"));
			logger.info("grossDesnity..........: {}", session.get("timber.gross.density"));
			logger.info("tensileStrength.......: {}", session.get("timber.tensile.strength"));
			logger.info("burstStrength.........: {}", session.get("timber.burst.strength"));
			logger.info("bendingStrength.......: {}", session.get("timber.bending.strength"));
			logger.info("shearStrength.........: {}", session.get("timber.shear.strength"));
			logger.info("brinellHardnessOne....: {}", session.get("timber.brinell.hardness.one"));
			logger.info("brinellHardnessTwo....: {}", session.get("timber.brinell.hardness.two"));
			logger.info("tengentShrinkage......: {}", session.get("timber.tangent.shrinkage"));
			logger.info("radialShrinkage.......: {}", session.get("timber.radial.shrinkage"));
			logger.info("emptyTable............: {}", session.get("timber.empty.table"));
			logger.info("=== end timber completion (session data) ===");
		}
	}

	/**
	 * Logging of confirmation of {@code Timber} data.
	 *
	 * @param TimberForm
	 *            the {@code TimberForm} instance
	 */
	private final void logTimberConfirmation(TimberForm timberForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.timber.confirmation"));
		if (isLog) {
			logger.info("=== timber form data confirmation ===");
			logger.info("id....................: {}", timberForm.getId());
			logger.info("version...............: {}", timberForm.getVersion());
			logger.info("geoRegionId...........: {}", timberForm.getGeoRegionId());
			logger.info("geoRegionCode.........: {}", timberForm.getGeoRegionCode());
			logger.info("geoRegionName.........: {}", timberForm.getGeoRegionName());
			logger.info("botanicSystemId.......: {}", (timberForm.getBotanicSystemId() == null)
					? String.valueOf(timberForm.getBotanicSystemId()) : timberForm.getBotanicSystemId());
			logger.info("botanicSystemOrder....: {}", timberForm.getBotanicSystemOrder());
			logger.info("botanicSystemFamily...: {}", timberForm.getBotanicSystemFamily());
			logger.info("botanicSystemSubFamily: {}", timberForm.getBotanicSystemSubFamily());
			logger.info("code..................: {}", timberForm.getCode());
			logger.info("name..................: {}", timberForm.getName());
			logger.info("imageName.............: {}", timberForm.getImageName());
			logger.info("academicName..........: {}", timberForm.getAcademicName());
			logger.info("grossDensity..........: {}", timberForm.getGrossDensity());
			logger.info("tensileStrength.......: {}", timberForm.getTensileStrength());
			logger.info("burstStrength.........: {}", timberForm.getBurstStrength());
			logger.info("bendingStrength.......: {}", timberForm.getBendingStrength());
			logger.info("shearStrength.........: {}", timberForm.getShearStrength());
			logger.info("brinellHardnessOne....: {}", timberForm.getBrinellHardnessOne());
			logger.info("brinellHardnessTwo....: {}", timberForm.getBrinellHardnessTwo());
			logger.info("tangentShrinkage......: {}", timberForm.getTangentShrinkage());
			logger.info("radialShrinkage.......: {}", timberForm.getRadialShrinkage());
			logger.info("emptyTable............: {}", timberForm.getEmptyTable());
			logger.info("=== end timber form data confirmation ===");
		}
	}

	/**
	 * Logging of confirmation of {@code Timber} data.
	 * 
	 * @param emptyTable
	 *            the empty table indicator
	 * @param geoRegionList
	 *            the list of {@code GeoRegion}
	 * @param botanicSystemList
	 *            the list of all {@code BotanicSystem}
	 * @param botanicSystemOrderList
	 *            the list of distinct {@code BotanicSystem} order
	 * @param botanicSystemFamilyList
	 *            the list of distinct {@code BotanicSystem} family
	 * @param botanicSystemSubFamilyList
	 *            the list of distinct {@code BotanicSystem} subFamily
	 * @param selectedGeoRegionIndex
	 *            selected {@code GeoRegion} index
	 * @param geoRegionCode
	 *            the {@code GeoRegion} code
	 * @param selectedGeoRegionId
	 *            selected {@code GeoRegion} identifier
	 * @param selectedGeoRegionCode
	 *            {@code GeoRegion} code
	 * @param selectedBotanicSystemId
	 *            selected {@code BotanicSystem} id
	 * @param selectedBotanicSystemOrder
	 *            selected {@code BotanicSystem} order
	 * @param selectedBotanicSystemFamily
	 *            selected {@code BotanicSystem} family
	 * @param selectedBotanicSystemSubFamily
	 *            selected {@code BotanicSystem} subFamily
	 */
	private final void logExtraData(Boolean emptyTable, List<GeoRegionDto> geoRegionList,
			List<BotanicSystemDto> botanicSystemList, List<String> botanicSystemOrderList,
			List<String> botanicSystemFamilyList, List<String> botanicSystemSubFamilyList,
			Integer selectedGeoRegionIndex, String geoRegionCode, Long selectedGeoRegionId,
			String selectedGeoRegionCode, Long selectedBotanicSystemId, String selectedBotanicSystemOrder,
			String selectedBotanicSystemFamily, String selectedBotanicSystemSubFamily) {
		logger.info("=== timber extra data ===");
		logger.info("TimberController.registerTimber emptyTable.........................: {}", emptyTable);
		logger.info("TimberController.registerTimber geoRegionListSize..................: {}", geoRegionList.size());
		logger.info("TimberController.registerTimber botanicSystemListSize..............: {}",
				botanicSystemList.size());
		logger.info("TimberController.registerTimber botanicSystemOrderListSize.........: {}",
				botanicSystemOrderList.size());
		logger.info("TimberController.registerTimber botanicSystemFamilyListSize........: {}",
				botanicSystemFamilyList.size());
		logger.info("TimberController.registerTimber botanicSystemSubFamilyListSize.....: {}",
				botanicSystemSubFamilyList.size());
		logger.info("TimberController.registerTimber geoRegionCode......................: {}", geoRegionCode);
		logger.info("TimberController.registerTimber selectedGeoRegionId................: {}", selectedGeoRegionId);
		logger.info("TimberController.registerTimber selectedGeoRegionIndex.............: {}", selectedGeoRegionIndex);
		logger.info("TimberController.registerTimber selectedGeoRegionCode..............: {}", selectedGeoRegionCode);
		logger.info("TimberController.registerTimber selectedBotanicSystemId............: {}", selectedBotanicSystemId);
		logger.info("TimberController.registerTimber selectedBotanicSystemOrder.........: {}",
				selectedBotanicSystemOrder);
		logger.info("TimberController.registerTimber selectedBotanicSystemFamily........: {}",
				selectedBotanicSystemFamily);
		logger.info("TimberController.registerTimber selectedBotanicSystemSubFamily.....: {}",
				selectedBotanicSystemSubFamily);
		logger.info("=== end timber  extra data ===");
	}

}
