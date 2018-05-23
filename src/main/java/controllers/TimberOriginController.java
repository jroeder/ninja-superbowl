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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dto.Dto;
import dto.GeoRegionDto;
import dto.TimberDto;
import dto.TimberOriginDto;
import entity.Timber;
import forms.Form;
import forms.TimberOriginForm;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import services.GeoRegionService;
import services.TimberOriginService;
import services.TimberService;
import types.Checker;
import types.SuperbowlHelper;
import types.Whitespace;

/**
 * Controller instance to handle user requests regarding a {@code TimberOrigin}.
 *
 * @author mbsusr01c
 */
public class TimberOriginController extends AbstractEntityController {

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
	 * The {@code TimberOriginService} instance
	 */
	@Inject
	private TimberOriginService timberOriginService;

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
	 * Default {@code Timber} index for selection of {@code Timber} in a
	 * selection box -> value is '0'
	 */
	private static final Integer DEFAULT_TIMBER_INDEX = new Integer(0);

	/**
	 * Default {@code Timber} code for selection of {@code Timber} in a
	 * selection box -> value is 'MLSY'
	 */
	private static final String DEFAULT_TIMBER_CODE = "MLSY";

	/**
	 * Default {@code Timber} name -> value is 'Apfelbaum'
	 */
	private static final String DEFAULT_TIMBER_NAME = "Apfelbaum";

	/**
	 * Constructor.
	 */
	public TimberOriginController() {
		super();
	}

	/**
	 * Retrieves all {@code TimberOrigin} instances from data store.
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result list(Context context) {
		Session session = context.getSession();
		session.clear();
		List<TimberOriginDto> timberOriginList = timberOriginService.listTimberOrigin();
		return Results.html().render("timberorigins", timberOriginList);
	}

	/**
	 * Process a new {@code TimberOrigin} instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code TimberOrigin} table has entries
	 *            or not
	 * @param geoRegionCode
	 *            the query parameter for a {@code GeoRegion} code
	 * @param timberCode
	 *            the query parameter for a {@code Timber} code
	 * @return {@code Result} instance
	 */
	public Result registerTimberOrigin(Session session, Context context, @Param("emptyTable") Boolean emptyTable,
			@Param("geoRegionCode") String geoRegionCode, @Param("timberCode") String timberCode) {
		logger.info("TimberOriginController.registerTimberOrigin -> Session id....: " + session.getId());
		logger.info("TimberOriginController.registerTimberOrigin -> Empty Table...: " + emptyTable.toString());
		logger.info("TimberOriginController.registerTimberOrigin -> GeoRegion Code: " + geoRegionCode);
		logger.info("TimberOriginController.registerTimberOrigin -> Timber Code...: " + timberCode);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pEmptyTable", emptyTable.toString());

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		map.put("geoRegionList", geoRegionList);

		List<TimberDto> timberList = null;
		Long selectedGeoRegionId = null;
		String selectedGeoRegionCode = null;
		Long selectedTimberId = null;
		String selectedTimberCode = null;
		Integer geoRegionIndex = null;
		Integer timberIndex = null;
		String timberName = null;

		TimberDto timberDto = null;
		TimberOriginDto sessionTimberOriginDto = null;
		TimberOriginDto timberOriginDto = null;
		Integer timberOriginIndex = null;

		if (emptyTable) {
			timberOriginIndex = 0;
		} else {
			// TimberOrigin max index ermitteln
			timberOriginDto = timberOriginService.getTimberOriginMaxIndex();
			timberOriginIndex = timberOriginDto.getIndex() + 1;
		}
		if (geoRegionCode.isEmpty() && timberCode.isEmpty()) {
			// alles auf default setzen
			// session.clear();
			selectedGeoRegionId = geoRegionList.get(DEFAULT_GEOREGION_INDEX).getId();
			selectedGeoRegionCode = DEFAULT_GEOREGION_CODE;
			timberList = timberService.listTimberByGeoRegionCode(DEFAULT_GEOREGION_CODE);
			selectedTimberId = timberList.get(DEFAULT_TIMBER_INDEX).getId();
			selectedTimberCode = DEFAULT_TIMBER_CODE;
			geoRegionIndex = DEFAULT_GEOREGION_INDEX;
			geoRegionCode = DEFAULT_GEOREGION_CODE;
			timberIndex = DEFAULT_TIMBER_INDEX;
			timberCode = DEFAULT_TIMBER_CODE;
			timberName = DEFAULT_TIMBER_NAME;
		} else {
			if (!geoRegionCode.isEmpty() && timberCode.isEmpty()) {
				// geoRegion mit code ermitteln und werte aus session übernehmen
				geoRegionIndex = geoRegionService.getGeoRegionByCode(geoRegionCode).getIndex();
				selectedGeoRegionId = geoRegionList.get(geoRegionIndex).getId();
				selectedGeoRegionCode = geoRegionCode;
				timberList = timberService.listTimberByGeoRegionCode(geoRegionCode);
				selectedTimberId = timberList.get(DEFAULT_TIMBER_INDEX).getId();
				timberDto = timberService.getTimberById(selectedTimberId);
				selectedTimberCode = timberDto.getCode();
				timberIndex = timberDto.getIndex();
				timberCode = timberDto.getCode();
				timberName = timberDto.getName();
			} else {
				if (geoRegionCode.isEmpty() && !timberCode.isEmpty()) {
					// timber mit code ermitteln und werte aus session
					// übernehmen
					// diese Kombination sollte nicht vorkommen
				} else {
					// geoRegion mit code ermitteln und timber mit code
					// ermitteln und werte aus session übernehmen
					geoRegionIndex = geoRegionService.getGeoRegionByCode(geoRegionCode).getIndex();
					selectedGeoRegionId = geoRegionList.get(geoRegionIndex).getId();
					selectedGeoRegionCode = geoRegionCode;
					timberList = timberService.listTimberByGeoRegionCode(geoRegionCode);
					timberDto = timberService.getTimberByCode(timberCode);
					selectedTimberId = timberList.get(timberDto.getIndex()).getId();
					selectedTimberCode = timberDto.getCode();
					timberIndex = timberDto.getIndex();
					timberCode = timberDto.getCode();
					timberName = timberDto.getName();
				}
			}
			sessionTimberOriginDto = (TimberOriginDto) getDtoFromSession(session);
		}

		logExtraData(geoRegionList, timberList, emptyTable, selectedGeoRegionId, selectedGeoRegionCode,
				selectedTimberId, selectedTimberCode, geoRegionIndex, geoRegionCode, timberIndex, timberName,
				timberCode, timberOriginIndex);

		map.put("geoRegionIndex", geoRegionIndex);
		map.put("timberList", timberList);
		map.put("timberIndex", timberIndex);
		map.put("timberName", timberName);
		map.put("timberOriginIndex", timberOriginIndex);
		map.put("selectedGeoRegionId", selectedGeoRegionId);
		map.put("selectedGeoRegionCode", selectedGeoRegionCode);
		map.put("selectedTimberId", selectedTimberId);
		map.put("selectedTimberCode", selectedTimberCode);

		if (sessionTimberOriginDto != null) {
			logger.info(sessionTimberOriginDto.asString());
			if (sessionTimberOriginDto.getCutdown() != null) {
				map.put("pCutdown", SuperbowlHelper.replaceSpaceByMarker(sessionTimberOriginDto.getCutdown()));
			} else {
				map.put("pCutdown", EMPTY);
			}
			if (sessionTimberOriginDto.getIndex() != null) {
				map.put("pIndex", sessionTimberOriginDto.getIndex());
			} else {
				map.put("pIndex", ZERO);
			}
			if (sessionTimberOriginDto.getCity() != null) {
				map.put("pCity", SuperbowlHelper.replaceSpaceByMarker(sessionTimberOriginDto.getCity()));
			} else {
				map.put("pCity", EMPTY);
			}
			if (sessionTimberOriginDto.getLocation() != null) {
				map.put("pLocation", SuperbowlHelper.replaceSpaceByMarker(sessionTimberOriginDto.getLocation()));
			} else {
				map.put("pLocation", EMPTY);
			}
			if (sessionTimberOriginDto.getLocationText() != null) {
				map.put("pLocationText",
						SuperbowlHelper.replaceSpaceByMarker(sessionTimberOriginDto.getLocationText()));
			} else {
				map.put("pLocationText", EMPTY);
			}
			if (sessionTimberOriginDto.getComment() != null) {
				map.put("pComment", SuperbowlHelper.replaceSpaceByMarker(sessionTimberOriginDto.getComment()));
			} else {
				map.put("pComment", EMPTY);
			}
		} else {
			map.put("pIndex", timberOriginIndex.toString());
			map.put("pCutdown", EMPTY);
			map.put("pCity", EMPTY);
			map.put("pLocation", EMPTY);
			map.put("pLocationText", EMPTY);
			map.put("pComment", EMPTY);
		}

		return Results.html().render(map).template("views/TimberOriginController/registerTimberOrigin.ftl.html");
	}

	/**
	 * Register {@code TimberOrigin} confirmation.
	 *
	 * @param timberOriginForm
	 *            the {@code Ninja TimberOriginForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerTimberOriginConfirmation(@JSR303Validation TimberOriginForm timberOriginForm, Session session,
			Validation validation, Context context) {
		logger.info("TimberOriginController.registerTimberOriginConfirmation -> Session id...: " + session.getId());
		logger.info("TimberOriginController.registerTimberOriginConfirmation -> Session empty: " + session.isEmpty());
		logger.info("TimberOriginController.registerTimberOriginConfirmation -> Context......: "
				+ context.getRequestPath());
		logger.info("TimberOriginController.registerTimberOriginConfirmation -> Violations...: "
				+ validation.hasViolations());

		logger.info("TimberOriginController:TimberOriginForm -> GeoRegionCode: " + timberOriginForm.getGeoRegionCode());
		logger.info("TimberOriginController:TimberOriginForm -> TimberId.....: " + timberOriginForm.getTimberId());
		logger.info("TimberOriginController:TimberOriginForm -> TimberIndex..: " + timberOriginForm.getTimberIndex());
		logger.info("TimberOriginController:TimberOriginForm -> TimberName...: " + timberOriginForm.getTimberName());
		logger.info("TimberOriginController:TimberOriginForm -> TimberCode...: " + timberOriginForm.getTimberCode());
		logger.info("TimberOriginController:TimberOriginForm -> Index........: " + timberOriginForm.getIndex());
		logger.info("TimberOriginController:TimberOriginForm -> City.........: " + timberOriginForm.getCity());
		logger.info("TimberOriginController:TimberOriginForm -> Location.....: " + timberOriginForm.getLocation());
		logger.info("TimberOriginController:TimberOriginForm -> LocationText.: " + timberOriginForm.getLocationText());
		logger.info("TimberOriginController:TimberOriginForm -> Cutdown......: " + timberOriginForm.getCutdown());
		logger.info("TimberOriginController:TimberOriginForm -> Comment......: " + timberOriginForm.getComment());
		logger.info("TimberOriginController:TimberOriginForm -> EmptyTable...: " + timberOriginForm.getEmptyTable());

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field............................: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("city".equals(fieldViolation.getFieldKey())
						&& "{timberorigin.city.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				if ("location".equals(fieldViolation.getFieldKey())
						&& "{timberorigin.location.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			String timberId = null;
			String timberIndex = null;
			String timberCode = null;
			String timberName = null;

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();

			List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();

			List<TimberDto> timberList = timberService.listTimberByGeoRegionCode(timberOriginForm.getGeoRegionCode());

			GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionByCode(timberOriginForm.getGeoRegionCode());
			map.put("geoRegionIndex", geoRegionDto.getIndex());
			map.put("geoRegionCode", geoRegionDto.getCode());
			map.put("selectedGeoRegion", geoRegionDto.getRegion());
			map.put("selectedGeoRegionId", geoRegionDto.getId());
			map.put("selectedGeoRegionCode", geoRegionDto.getCode());
			map.put("selectedTimberId", timberOriginForm.getTimberId());
			//
			TimberDto timberDto = timberService.getTimberByCode(timberOriginForm.getTimberCode());
			timberId = timberDto.getId().toString();
			timberIndex = timberDto.getIndex().toString();
			timberCode = timberDto.getCode();
			timberName = timberDto.getName();
			//
			map.put("timberId", timberId);
			map.put("timberIndex", timberIndex);
			map.put("timberCode", timberCode);
			map.put("timberName", timberName);
			//
			map.put("pIndex", timberOriginForm.getIndex());
			map.put("pCutdown", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getCutdown()));
			map.put("pCity", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getCity()));
			map.put("pLocation", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getLocation()));
			map.put("pLocationText", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getLocationText()));
			map.put("pComment", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getComment()));
			map.put("pEmptyTable", timberOriginForm.getEmptyTable());

			result.render(map);

			result.render("geoRegionList", geoRegionList);
			result.render("timberList", timberList);

			result.render("violations", validation.getViolations());
			result.render("timberOrigin", timberOriginForm);
			result.template("views/TimberOriginController/registerTimberOrigin.ftl.html");

			session.save(context);

			return result;

		} else {

			putFormIntoSession(timberOriginForm, session);

			Result result = Results.html();
			result.render("geoRegionCode", timberOriginForm.getGeoRegionCode());
			result.render("timberId", timberOriginForm.getTimberId());
			result.render("timberCode", timberOriginForm.getTimberCode());
			result.render("timberName", timberOriginForm.getTimberName());
			result.render("index", timberOriginForm.getIndex());
			result.render("city", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getCity()));
			result.render("location", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getLocation()));
			result.render("locationText", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getLocationText()));
			result.render("cutdown", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getCutdown()));
			result.render("comment", SuperbowlHelper.replaceSpaceByMarker(timberOriginForm.getComment()));
			result.render("emptyTable", timberOriginForm.getEmptyTable());

			result.render("timberOrigin", timberOriginForm);

			session.save(context);

			return result;

		}
	}

	/**
	 * Register {@code TimberOrigin} completion.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerTimberOriginCompletion(Session session, Context context) {
		logger.info("TimberOriginController.registerTimberOriginCompletion -> Session id: " + session.getId());
		logger.info("TimberOriginController.registerTimberOriginCompletion -> Context: " + context.getRequestPath());

		TimberOriginDto timberOriginDto = (TimberOriginDto) getDtoFromSession(session);

		timberOriginService.register(timberOriginDto);

		session.clear();

		return Results.ok().redirect("/superbowl/timberOrigin");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#getDtoFromSession(ninja.session.Session)
	 */
	@Override
	public Dto getDtoFromSession(Session session) {
		logger.info("TimberOriginController.getDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("timber.origin.id") != null) {
			id = new Long(session.get("timber.origin.id"));
		}
		Integer version = null;
		if (session.get("timber.origin.version") != null) {
			version = new Integer(session.get("timber.origin.version")).intValue();
		}
		String geoRegionId = null;
		if (session.get("timber.origin.georegion.id") != null) {
			geoRegionId = session.get("timber.origin.georegion.id");
		}
		String geoRegionCode = null;
		if (session.get("timber.origin.georegion.code") != null) {
			geoRegionCode = session.get("timber.origin.georegion.code");
		}
		Timber timber = null;
		if (session.get("timber.origin.timber.id") != null) {
			String timberId = session.get("timber.origin.timber.id");
			timber = new Timber(timberService.getTimberById(new Long(timberId)));
		}
		String timberIndex = null;
		if (session.get("timber.origin.timber.index") != null) {
			timberIndex = session.get("timber.origin.timber.index");
		}
		String timberCode = null;
		if (session.get("timber.origin.timber.code") != null) {
			timberCode = session.get("timber.origin.timber.code");
		}
		String timberName = null;
		if (session.get("timber.origin.timber.name") != null) {
			timberName = session.get("timber.origin.timber.name");
		}
		String cutdown = null;
		if (session.get("timber.origin.cutdown") != null) {
			cutdown = session.get("timber.origin.cutdown");
		}
		Integer index = null;
		if (session.get("timber.origin.index") != null) {
			index = new Integer(session.get("timber.origin.index")).intValue();
		}
		String city = null;
		if (session.get("timber.origin.city") != null) {
			city = session.get("timber.origin.city");
		}
		String location = null;
		if (session.get("timber.origin.location") != null) {
			location = session.get("timber.origin.location");
		}
		String locationText = null;
		if (session.get("timber.origin.location.text") != null) {
			locationText = session.get("timber.origin.location.text");
		}
		String comment = null;
		if (session.get("timber.origin.comment") != null) {
			comment = session.get("timber.origin.comment");
		}
		String emptyTable = null;
		if (session.get("timber.origin.empty.table") != null) {
			emptyTable = session.get("timber.origin.empty.table");
		}

		logTimberOriginCompletion(session);

		TimberOriginDto timberOriginDto = new TimberOriginDto(id, version, timber, index, city, location, locationText,
				cutdown, comment, emptyTable);

		return timberOriginDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#putDtoIntoSession(dto.Dto,
	 * ninja.session.Session)
	 */
	@Override
	public void putDtoIntoSession(Dto dto, Session session) {
		logger.info("TimberOriginController.putDtoIntoSession -> Session id: " + session.getId());

		TimberOriginDto timberOriginDto = (TimberOriginDto) dto;

		logTimberOrigin(timberOriginDto);

		String data = null;

		logger.info("=== put bowl dto into session ===");
		logger.info("id...........: {}", timberOriginDto.getId().toString());
		session.put("timber.origin.id", timberOriginDto.getId().toString());

		logger.info("version......: {}", timberOriginDto.getVersion().toString());
		session.put("timber.origin.version", timberOriginDto.getVersion().toString());

		logger.info("geoRegionId..: {}", timberOriginDto.getTimber().getGeoRegion().getId());
		session.put("timber.origin.georegion.id", timberOriginDto.getTimber().getGeoRegion().getId().toString());

		logger.info("geoRegionCode: {}", timberOriginDto.getTimber().getGeoRegion().getCode());
		session.put("timber.origin.georegion.code", timberOriginDto.getTimber().getGeoRegion().getCode());

		logger.info("timberId.....: {}", timberOriginDto.getTimber().getId());
		session.put("timber.origin.timber.id", timberOriginDto.getTimber().getId().toString());

		logger.info("timberIndex..: {}", timberOriginDto.getTimber().getIndex());
		session.put("timber.origin.timber.index", timberOriginDto.getTimber().getIndex().toString());

		logger.info("timberCode...: {}", timberOriginDto.getTimber().getCode());
		session.put("timber.origin.timber.code", timberOriginDto.getTimber().getCode());

		logger.info("timberName...: {}", timberOriginDto.getTimber().getName());
		session.put("timber.origin.timber.name", timberOriginDto.getTimber().getName());

		data = timberOriginDto.getIndex().toString();
		logger.info("index........: {}", data);
		session.put("timber.origin.index", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginDto.getCity())
				? timberOriginDto.getCity() : Whitespace.EMPTY.getValue());
		logger.info("city.........: {}", data);
		session.put("timber.origin.city", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginDto.getLocation())
				? timberOriginDto.getLocation() : Whitespace.EMPTY.getValue());
		logger.info("location.....: {}", data);
		session.put("timber.origin.location", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginDto.getLocationText())
				? timberOriginDto.getLocationText() : Whitespace.EMPTY.getValue());
		logger.info("locationText.: {}", data);
		session.put("timber.origin.location.text", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginDto.getCutdown())
				? timberOriginDto.getCutdown() : Whitespace.EMPTY.getValue());
		logger.info("cutdown.....: {}", data);
		session.put("timber.origin.cutdown", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginDto.getComment())
				? timberOriginDto.getComment() : Whitespace.EMPTY.getValue());
		logger.info("comment.....: {}", data);
		session.put("timber.origin.comment", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginDto.getEmptyTable())
				? timberOriginDto.getEmptyTable() : Whitespace.EMPTY.getValue());
		logger.info("emptyTable..: {}", data);
		session.put("timber.origin.empty.table", data);
		logger.info("=== end put timber origin dto into session ===");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#putFormIntoSession(forms.Form,
	 * ninja.session.Session)
	 */
	@Override
	public void putFormIntoSession(Form form, Session session) {
		logger.info("TimberOriginController.putFormIntoSession -> Session id: " + session.getId());

		TimberOriginForm timberOriginForm = (TimberOriginForm) form;

		logTimberOriginConfirmation(timberOriginForm);

		String data = null;

		logger.info("=== put timber origin form data into session ===");

		session.put("timber.origin.id", null);
		logger.info("id...........: {}", timberOriginForm.getId());
		if (timberOriginForm.getId() == null) {
			session.put("timber.origin.id", null);
		} else {
			session.put("timber.origin.id", timberOriginForm.getId());
		}

		logger.info("version......: {}", timberOriginForm.getVersion());
		if (timberOriginForm.getVersion() == null) {
			session.put("timber.origin.version", "0");
		} else {
			session.put("timber.origin.version", timberOriginForm.getVersion());
		}

		data = timberOriginForm.getGeoRegionId();
		logger.info("georegionId..: {}", data);
		session.put("timber.origin.georegion.id", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getGeoRegionCode())
				? timberOriginForm.getGeoRegionCode() : Whitespace.EMPTY.getValue());
		logger.info("georegionCode: {}", data);
		session.put("timber.origin.georegion.code", data);

		logger.info("timberId.....: {}", timberOriginForm.getTimberId());
		session.put("timber.origin.timber.id", timberOriginForm.getTimberId().toString());

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getTimberCode())
				? timberOriginForm.getTimberCode() : Whitespace.EMPTY.getValue());
		logger.info("timberCode...: {}", data);
		session.put("timber.origin.timber.code", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getTimberName())
				? timberOriginForm.getTimberName() : Whitespace.EMPTY.getValue());
		logger.info("timberName...: {}", data);
		session.put("timber.origin.timber.name", data);

		data = timberOriginForm.getTimberIndex();
		logger.info("timberIndex..: {}", data);
		session.put("timber.origin.timber.index", data);

		data = timberOriginForm.getIndex().toString();
		logger.info("index........: {}", data);
		session.put("timber.origin.index", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getCity())
				? timberOriginForm.getCity() : Whitespace.EMPTY.getValue());
		logger.info("city.........: {}", data);
		session.put("timber.origin.city", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getLocation())
				? timberOriginForm.getLocation() : Whitespace.EMPTY.getValue());
		logger.info("location.....: {}", data);
		session.put("timber.origin.location", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getLocationText())
				? timberOriginForm.getLocationText() : Whitespace.EMPTY.getValue());
		logger.info("locationText.: {}", data);
		session.put("timber.origin.location.text", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getCutdown())
				? timberOriginForm.getCutdown() : Whitespace.EMPTY.getValue());
		logger.info("cutdown......: {}", data);
		session.put("timber.origin.cutdown", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getComment())
				? timberOriginForm.getComment() : Whitespace.EMPTY.getValue());
		logger.info("comment......: {}", data);
		session.put("timber.origin.comment", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(timberOriginForm.getEmptyTable())
				? timberOriginForm.getEmptyTable() : Whitespace.EMPTY.getValue());
		logger.info("emptyTable...: {}", data);
		session.put("timber.origin.empty.table", data);

		logger.info("=== end put timber origin form data into session ===");
	}

	/**
	 * Logging of {@code TimberOrigin} data.
	 *
	 * @param timberOriginDto
	 *            the {@code TimberOriginDto} instance
	 */
	private final void logTimberOrigin(TimberOriginDto timberOriginDto) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.timber.origin"));
		if (isLog) {
			logger.info("=== timber origin dto ===");
			logger.info("id...........: {}", timberOriginDto.getId());
			logger.info("version......: {}", timberOriginDto.getVersion());
			logger.info("geoRegionId..: {}", timberOriginDto.getTimber().getGeoRegion().getId());
			logger.info("geoRegionCode: {}", timberOriginDto.getTimber().getGeoRegion().getCode());
			logger.info("timberId.....: {}", (timberOriginDto.getTimber().getId() == null)
					? String.valueOf(timberOriginDto.getTimber().getId()) : timberOriginDto.getTimber().getId());
			logger.info("timberCode...: {}", timberOriginDto.getTimber().getCode());
			logger.info("timberName...: {}", timberOriginDto.getTimber().getName());
			logger.info("index........: {}", timberOriginDto.getIndex());
			logger.info("city.........: {}", timberOriginDto.getCity());
			logger.info("location.....: {}", timberOriginDto.getLocation());
			logger.info("locationText.: {}", timberOriginDto.getLocationText());
			logger.info("cutdown......: {}", timberOriginDto.getCutdown());
			logger.info("comment......: {}", timberOriginDto.getComment());
			logger.info("emptyTable...: {}", timberOriginDto.getEmptyTable());
			logger.info("=== end timber origin dto ===");
		}
	}

	/**
	 * Logging of completion of {@code TimberOrigin} data.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	private final void logTimberOriginCompletion(Session session) {
		logger.info("TimberOriginController.logTimberOriginCompletion -> Session    id: " + session.getId());
		logger.info("TimberOriginController.logTimberOriginCompletion -> Session empty: " + session.isEmpty());
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.timber.origin.completion"));
		if (isLog) {
			Long id = null;
			if (session.get("timber.origin.id") != null) {
				id = new Long(session.get("timber.origin.id"));
			}
			Integer version = null;
			if (session.get("timber.origin.version") != null) {
				version = new Integer(session.get("timber.origin.version")).intValue();
			}
			logger.info("=== complete register of timber origin (session data) ===");
			logger.info("id...........: {}", id);
			logger.info("version......: {}", version);
			logger.info("geoRegionId..: {}", session.get("timber.origin.georegion.id"));
			logger.info("geoRegionCode: {}", session.get("timber.origin.georegion.code"));
			logger.info("timberId.....: {}", session.get("timber.origin.timber.id"));
			logger.info("timberCode...: {}", session.get("timber.origin.timber.code"));
			logger.info("timberName...: {}", session.get("timber.origin.timber.name"));
			logger.info("index........: {}", session.get("timber.origin.index"));
			logger.info("city.........: {}", session.get("timber.origin.city"));
			logger.info("location.....: {}", session.get("timber.origin.location"));
			logger.info("locationText.: {}", session.get("timber.origin.location.text"));
			logger.info("cutdown......: {}", session.get("timber.origin.cutdown"));
			logger.info("comment......: {}", session.get("timber.origin.comment"));
			logger.info("emptyTable...: {}", session.get("timber.origin.empty.table"));
			logger.info("=== end timber origin completion (session data) ===");
		}
	}

	/**
	 * Logging of confirmation of {@code TimberOrigin} data.
	 *
	 * @param TimberOriginForm
	 *            the {@code TimberOriginForm} instance
	 */
	private final void logTimberOriginConfirmation(TimberOriginForm timberOriginForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.timber.origin.confirmation"));
		if (isLog) {
			logger.info("=== timber origin form data confirmation ===");
			logger.info("id...........: {}", timberOriginForm.getId());
			logger.info("version......: {}", timberOriginForm.getVersion());
			logger.info("geoRegionId..: {}", timberOriginForm.getGeoRegionId());
			logger.info("geoRegionCode: {}", timberOriginForm.getGeoRegionCode());
			logger.info("timberId.....: {}", (timberOriginForm.getTimberId() == null)
					? String.valueOf(timberOriginForm.getTimberId()) : timberOriginForm.getTimberId());
			logger.info("timberCode...: {}", timberOriginForm.getTimberCode());
			logger.info("timberName...: {}", timberOriginForm.getTimberName());
			logger.info("index........: {}", timberOriginForm.getIndex());
			logger.info("city.........: {}", timberOriginForm.getCity());
			logger.info("location.....: {}", timberOriginForm.getLocation());
			logger.info("locationText.: {}", timberOriginForm.getLocationText());
			logger.info("cutdown......: {}", timberOriginForm.getCutdown());
			logger.info("comment......: {}", timberOriginForm.getComment());
			logger.info("emptyTable...: {}", timberOriginForm.getEmptyTable());
			logger.info("=== end timber origin form data confirmation ===");
		}
	}

	/**
	 * Logging of confirmation of {@code TimberOrigin} data.
	 * 
	 * @param geoRegionList
	 *            the list of {@code GeoRegion}
	 * @param timberList
	 *            the list of {@code Timber}
	 * @param emptyTable
	 *            the empty table indicator
	 * @param selectedGeoRegionId
	 *            selected {@code GeoRegion} identifier
	 * @param selectedGeoRegionCodeselected
	 *            {@code GeoRegion} code
	 * @param selectedTimberId
	 *            selected {@code Timber} identifier
	 * @param selectedTimberCode
	 *            selected {@code Timber} code
	 * @param geoRegionIndex
	 *            the {@code GeoRegion} index
	 * @param geoRegionCode
	 *            the {@code GeoRegion} code
	 * @param timberIndex
	 *            the {@code Timber} index
	 * @param timberName
	 *            the {@code Timber} name
	 * @param timberCode
	 *            the {@code Timber} code
	 * @param timberOriginIndex
	 *            the {@code TimberOrigin} index
	 */
	private final void logExtraData(List<GeoRegionDto> geoRegionList, List<TimberDto> timberList, Boolean emptyTable,
			Long selectedGeoRegionId, String selectedGeoRegionCode, Long selectedTimberId, String selectedTimberCode,
			Integer geoRegionIndex, String geoRegionCode, Integer timberIndex, String timberName, String timberCode,
			Integer timberOriginIndex) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.timber.extra.data"));
		if (isLog) {
			logger.info("=== timber origin extra data ===");
			logger.info("TimberOriginController.registerTimberOrigin emptyTable...........: {}", emptyTable);
			logger.info("TimberOriginController.registerTimberOrigin geoRegionListSize....: {}", geoRegionList.size());
			logger.info("TimberOriginController.registerTimberOrigin timberListSize.......: {}", timberList.size());
			logger.info("TimberOriginController.registerTimberOrigin selectedGeoRegionId..: {}", selectedGeoRegionId);
			logger.info("TimberOriginController.registerTimberOrigin selectedGeoRegionCode: {}", selectedGeoRegionCode);
			logger.info("TimberOriginController.registerTimberOrigin selectedTimberId.....: {}", selectedTimberId);
			logger.info("TimberOriginController.registerTimberOrigin geoRegionIndex.......: {}", geoRegionIndex);
			logger.info("TimberOriginController.registerTimberOrigin geoRegionCode........: {}", geoRegionCode);
			logger.info("TimberOriginController.registerTimberOrigin timberIndex..........: {}", timberIndex);
			logger.info("TimberOriginController.registerTimberOrigin timberName...........: {}", timberName);
			logger.info("TimberOriginController.registerTimberOrigin timberCode...........: {}", timberCode);
			logger.info("TimberOriginController.registerTimberOrigin timberOriginIndex....: {}", timberOriginIndex);
			logger.info("=== end timber origin extra data ===");
		}
	}

}
