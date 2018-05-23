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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.inject.Singleton;

import dto.Dto;
import dto.ExhibitionDto;
import dto.ManufactureDto;
import forms.ExhibitionForm;
import forms.Form;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import services.ExhibitionService;
import services.ManufactureService;
import types.Checker;
import types.SuperbowlHelper;
import types.Whitespace;

/**
 * Controller instance to handle user requests regarding a {@code Exhibition}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 12.05.2017 mbsusr01
 */
@Singleton
public class ExhibitionController extends AbstractEntityController {

	/**
	 * Default {@code Manufacture} index for {@code Manufacture} year -> value
	 * is '7'
	 */
	private static final Integer DEFAULT_MANUFACTURE_INDEX = 7;

	/**
	 * This is the superbowl exhibition service
	 */
	@Inject
	private ExhibitionService exhibitionService;

	/**
	 * This is the superbowl manufacture service
	 */
	@Inject
	private ManufactureService manufactureService;

	/**
	 * Retrieves all customers from data store.
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result list(Context context) {
		Session session = context.getSession();
		session.clear();
		List<ExhibitionDto> exhibitionList = exhibitionService.listExhibitions();
		return Results.html().render("exhibitions", exhibitionList);
	}

	/**
	 * Process a new {@code Exhibition} instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code TimberOrigin} table has entries
	 * @param manufactureId
	 *            the query parameter for a {@code Manufacture} year or not
	 * @return {@code Result} instance
	 */
	public Result registerExhibition(Session session, Context context, @Param("emptyTable") Boolean emptyTable,
			@Param("manufactureId") String manufactureId) {
		logger.info("ExhibitionController.registerExhibition -> Session id.......: " + session.getId());
		logger.info("ExhibitionController.registerExhibition -> Empty Table......: " + emptyTable.toString());
		logger.info("ExhibitionController.registerExhibition -> Manufacture id...: " + manufactureId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("exEmptyTable", emptyTable.toString());

		List<ManufactureDto> manufactureList = manufactureService.listManufacture();
		map.put("manufactureList", manufactureList);

		Long selectedManufactureId = null;
		Integer selectedManufactureIndex = null;
		String selectedManufactureYear = null;

		ExhibitionDto sessionExhibitionDto = null;
		ExhibitionDto exhibitionDto = null;
		Integer exhibitionIndex = null;
		Integer manufactureIndex = null;

		ManufactureDto manufactureDto = null;

		if (emptyTable) {
			exhibitionIndex = 0;
		} else {
			// Exhibition max index ermitteln
			exhibitionDto = exhibitionService.getExhibitionMaxIndex();
			exhibitionIndex = exhibitionDto.getIndex() + 1;
		}

		map.put("exhibitionIndex", exhibitionIndex);

		if (manufactureId.isEmpty()) {
			// alles auf default setzen
			// session.clear();
			manufactureIndex = DEFAULT_MANUFACTURE_INDEX;
			selectedManufactureId = manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getId();
			selectedManufactureIndex = manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getIndex();
			selectedManufactureYear = manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getYear();
		} else {
			sessionExhibitionDto = (ExhibitionDto) getDtoFromSession(session);
			manufactureDto = manufactureService.getManufactureById(new Long(manufactureId));
			manufactureIndex = manufactureDto.getIndex();
			selectedManufactureId = manufactureList.get(manufactureDto.getIndex()).getId();
			selectedManufactureIndex = manufactureList.get(manufactureDto.getIndex()).getIndex();
			selectedManufactureYear = manufactureDto.getYear();
		}

		manufactureId = selectedManufactureId.toString();

		logExtraData(manufactureList, emptyTable, selectedManufactureId, selectedManufactureYear, exhibitionIndex,
				manufactureIndex, manufactureId);

		map.put("manufactureId", manufactureId);
		map.put("manufactureIndex", manufactureIndex);
		map.put("selectedManufactureId", selectedManufactureId);
		map.put("selectedManufactureIndex", selectedManufactureIndex);
		map.put("selectedManufactureYear", selectedManufactureYear);

		if (sessionExhibitionDto != null) {
			logger.info(sessionExhibitionDto.asString());
			if (sessionExhibitionDto.getIndex() != null) {
				map.put("exIndex", sessionExhibitionDto.getIndex());
			} else {
				map.put("exIndex", EMPTY);
			}
			if (sessionExhibitionDto.getName() != null) {
				map.put("exName", SuperbowlHelper.replaceSpaceByMarker(sessionExhibitionDto.getName()));
			} else {
				map.put("exName", EMPTY);
			}
			if (sessionExhibitionDto.getInstitution() != null) {
				map.put("exInstitution", SuperbowlHelper.replaceSpaceByMarker(sessionExhibitionDto.getInstitution()));
			} else {
				map.put("exInstitution", EMPTY);
			}
			if (sessionExhibitionDto.getYear() != null) {
				map.put("exYear", sessionExhibitionDto.getYear());
			} else {
				map.put("exYear", EMPTY);
			}
			if (sessionExhibitionDto.getDateFrom() != null) {
				map.put("exDateFrom", SuperbowlHelper.convertDate(sessionExhibitionDto.getDateFrom().toString()));
			} else {
				map.put("exDateFrom", EMPTY);
			}
			if (sessionExhibitionDto.getDateTo() != null) {
				map.put("exDateTo", SuperbowlHelper.convertDate(sessionExhibitionDto.getDateTo().toString()));
			} else {
				map.put("exDateTo", EMPTY);
			}
			if (sessionExhibitionDto.getCity() != null) {
				map.put("exCity", SuperbowlHelper.replaceSpaceByMarker(sessionExhibitionDto.getCity()));
			} else {
				map.put("exCity", EMPTY);
			}
			if (sessionExhibitionDto.getCountry() != null) {
				map.put("exCountry", SuperbowlHelper.replaceSpaceByMarker(sessionExhibitionDto.getCountry()));
			} else {
				map.put("exCountry", EMPTY);
			}
			if (sessionExhibitionDto.getComment() != null) {
				map.put("exComment", SuperbowlHelper.replaceSpaceByMarker(sessionExhibitionDto.getComment()));
			} else {
				map.put("exComment", EMPTY);
			}
			if (sessionExhibitionDto.getManufactureId() != null) {
				map.put("exManufactureId", sessionExhibitionDto.getManufactureId());
			} else {
				map.put("exManufactureId", manufactureId);
			}
		} else {
			map.put("exIndex", exhibitionIndex.toString());
			map.put("exName", EMPTY);
			map.put("exInstitution", EMPTY);
			map.put("exYear", EMPTY);
			map.put("exDateFrom", EMPTY);
			map.put("exDateTo", EMPTY);
			map.put("exCity", EMPTY);
			map.put("exCountry", EMPTY);
			map.put("exComment", EMPTY);
			map.put("exManufactureId", manufactureId);
		}

		return Results.html().render(map).template("views/ExhibitionController/registerExhibition.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param exhibitionForm
	 *            the {@code ExhibitionForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerExhibitionConfirmation(@JSR303Validation ExhibitionForm exhibitionForm, Session session,
			Validation validation, Context context) {
		logger.info("ExhibitionController.registerExhibitionConfirmation -> Session id...: " + session.getId());
		logger.info("ExhibitionController.registerExhibitionConfirmation -> Session empty: " + session.isEmpty());
		logger.info(
				"ExhibitionController.registerExhibitionConfirmation -> Context......: " + context.getRequestPath());
		logger.info(
				"ExhibitionController.registerExhibitionConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("========== ExhibitionController.registerExhibitionConfirmation ==========");
		logger.info("ExhibitionController:ExhibitionForm -> Index........: " + exhibitionForm.getIndex());
		logger.info("ExhibitionController:ExhibitionForm -> Name.........: " + exhibitionForm.getName());
		logger.info("ExhibitionController:ExhibitionForm -> Institution..: " + exhibitionForm.getInstitution());
		logger.info("ExhibitionController:ExhibitionForm -> Year.........: " + exhibitionForm.getYear());
		logger.info("ExhibitionController:ExhibitionForm -> DateFrom.....: " + exhibitionForm.getDateFrom());
		logger.info("ExhibitionController:ExhibitionForm -> DateTo.......: " + exhibitionForm.getDateTo());
		logger.info("ExhibitionController:ExhibitionForm -> City.........: " + exhibitionForm.getCity());
		logger.info("ExhibitionController:ExhibitionForm -> Country......: " + exhibitionForm.getCountry());
		logger.info("ExhibitionController:ExhibitionForm -> Comment......: " + exhibitionForm.getComment());
		logger.info("ExhibitionController:ExhibitionForm -> EmptyTable...: " + exhibitionForm.getEmptyTable());
		logger.info("ExhibitionController:ExhibitionForm -> ManufactureId: " + exhibitionForm.getManufactureId());
		logger.info("========== End ==========");

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("year".equals(fieldViolation.getFieldKey())
						&& "{exhibition.year.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("name".equals(fieldViolation.getFieldKey())
						&& "{exhibition.name.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("dateFrom".equals(fieldViolation.getFieldKey())
						&& "{exhibition.dateFrom.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("dateTo".equals(fieldViolation.getFieldKey())
						&& "{exhibition.dateTo.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			List<ManufactureDto> manufactureList = manufactureService.listManufacture();

			ManufactureDto manufactureDto = manufactureService
					.getManufactureById(new Long(exhibitionForm.getManufactureId()));

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("exIndex", exhibitionForm.getIndex());
			map.put("exName", SuperbowlHelper.replaceSpaceByMarker(exhibitionForm.getName()));
			map.put("exInstitution", SuperbowlHelper.replaceSpaceByMarker(exhibitionForm.getInstitution()));
			map.put("exYear", SuperbowlHelper.replaceSpaceByMarker(exhibitionForm.getYear()));
			map.put("exDateFrom", SuperbowlHelper.convertDate(exhibitionForm.getDateFrom()));
			map.put("exDateTo", SuperbowlHelper.convertDate(exhibitionForm.getDateTo()));
			map.put("exCity", SuperbowlHelper.replaceSpaceByMarker(exhibitionForm.getCity()));
			map.put("exCountry", SuperbowlHelper.replaceSpaceByMarker(exhibitionForm.getCountry()));
			map.put("exComment", SuperbowlHelper.replaceSpaceByMarker(exhibitionForm.getComment()));
			map.put("exEmptyTable", exhibitionForm.getEmptyTable());
			map.put("exManufactureId", exhibitionForm.getManufactureId());
			map.put("manufactureId", exhibitionForm.getManufactureId());
			map.put("manufactureIndex", manufactureDto.getIndex());
			map.put("selectedManufactureYear", manufactureDto.getYear());

			result.render(map);

			result.render("manufactureList", manufactureList);

			result.render("violations", validation.getViolations());
			result.render("exhibition", exhibitionForm);
			result.template("views/ExhibitionController/registerExhibition.ftl.html");

			session.save(context);

			return result;

		} else {

			putFormIntoSession(exhibitionForm, session);

			Result result = Results.html();
			result.render("index", exhibitionForm.getIndex());
			result.render("name", exhibitionForm.getName());
			result.render("institution", exhibitionForm.getInstitution());
			result.render("year", exhibitionForm.getYear());
			result.render("dateFrom", exhibitionForm.getDateFrom());
			result.render("dateTo", exhibitionForm.getDateTo());
			result.render("city", exhibitionForm.getCity());
			result.render("country", exhibitionForm.getCountry());
			result.render("comment", exhibitionForm.getComment());
			result.render("emptyTable", exhibitionForm.getEmptyTable());
			result.render("manufactureId", exhibitionForm.getManufactureId());

			result.render("exhibition", exhibitionForm);

			session.save(context);

			return result;

		}

	}

	/**
	 * Insert method description here...
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerExhibitionCompletion(Session session, Context context) {

		logger.info("ExhibitionController.registerExhibitionCompletion -> Session id: " + session.getId());
		logger.info("ExhibitionController.registerExhibitionCompletion -> Context: " + context.getRequestPath());

		ExhibitionDto exhibitionDto = (ExhibitionDto) getDtoFromSession(session);

		exhibitionService.register(exhibitionDto);

		session.clear();

		return Results.ok().redirect("/superbowl/exhibition");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#getDtoFromSession(ninja.session.Session)
	 */
	@Override
	public Dto getDtoFromSession(Session session) {
		logger.info("ExhibitionController.getExhibitionDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("exhibition.id") != null) {
			id = new Long(session.get("exhibition.id"));
		}
		Integer version = null;
		if (session.get("exhibition.version") != null) {
			version = new Integer(session.get("exhibition.version")).intValue();
		}
		Integer index = null;
		if (session.get("exhibition.index") != null) {
			index = new Integer(session.get("exhibition.index")).intValue();
		}
		String year = null;
		if (session.get("exhibition.year") != null) {
			year = session.get("exhibition.year");
		}
		String name = null;
		if (session.get("exhibition.name") != null) {
			name = session.get("exhibition.name");
		}
		String institution = null;
		if (session.get("exhibition.institution") != null) {
			institution = session.get("exhibition.institution");
		}
		Date dateFrom = null;
		if (session.get("exhibition.dateFrom") != null) {
			dateFrom = SuperbowlHelper.convertToDate(session.get("exhibition.dateFrom"));
		}
		Date dateTo = null;
		if (session.get("exhibition.dateTo") != null) {
			dateTo = SuperbowlHelper.convertToDate(session.get("exhibition.dateTo"));
		}
		String city = null;
		if (session.get("exhibition.city") != null) {
			city = session.get("exhibition.city");
		}
		String country = null;
		if (session.get("exhibition.country") != null) {
			country = session.get("exhibition.country");
		}
		String comment = null;
		if (session.get("exhibition.comment") != null) {
			comment = session.get("exhibition.comment");
		}
		String emptyTable = null;
		if (session.get("exhibition.empty.table") != null) {
			emptyTable = session.get("exhibition.empty.table");
		}
		String manufactureId = null;
		if (session.get("exhibition.manufacture.id") != null) {
			manufactureId = session.get("exhibition.manufacture.id");
		}

		logExhibitionCompletion(session);

		ExhibitionDto exhibitionDto = new ExhibitionDto(id, version, index, name, institution, year, dateFrom, dateTo,
				city, country, comment, emptyTable, manufactureId);

		return exhibitionDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#putFormIntoSession(forms.Form,
	 * ninja.session.Session)
	 */
	@Override
	public void putFormIntoSession(Form form, Session session) {
		logger.info("CustomerController.putFormIntoSession -> Session id: " + session.getId());

		ExhibitionForm exhibitionForm = (ExhibitionForm) form;

		logExhibitionConfirmation(exhibitionForm);

		String data = null;

		logger.info("=== put exhibition form data into session ===");
		session.put("exhibition.id", null);
		session.put("exhibition.version", "0");

		data = exhibitionForm.getIndex().toString();
		logger.info("index........: {}", data);
		session.put("exhibition.index", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getName()) ? exhibitionForm.getName()
				: Whitespace.EMPTY.getValue());
		logger.info("name.........: {}", data);
		session.put("exhibition.name", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getInstitution())
				? exhibitionForm.getInstitution() : Whitespace.EMPTY.getValue());
		logger.info("institution..: {}", data);
		session.put("exhibition.institution", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getYear()) ? exhibitionForm.getYear()
				: Whitespace.EMPTY.getValue());
		logger.info("year.........: {}", data);
		session.put("exhibition.year", data);

		data = exhibitionForm.getDateFrom().toString();
		logger.info("dateFrom.....: {}", data);
		session.put("exhibition.dateFrom", data);

		data = exhibitionForm.getDateTo().toString();
		logger.info("dateTo.......: {}", data);
		session.put("exhibition.dateTo", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getCity()) ? exhibitionForm.getCity()
				: Whitespace.EMPTY.getValue());
		logger.info("city.........: {}", data);
		session.put("exhibition.city", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getCountry())
				? exhibitionForm.getCountry() : Whitespace.EMPTY.getValue());
		logger.info("country......: {}", data);
		session.put("exhibition.country", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getComment())
				? exhibitionForm.getComment() : Whitespace.EMPTY.getValue());
		logger.info("comment......: {}", data);
		session.put("exhibition.comment", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getEmptyTable())
				? exhibitionForm.getEmptyTable() : Whitespace.EMPTY.getValue());
		logger.info("emptyTable...: {}", data);
		session.put("exhibition.empty.table", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(exhibitionForm.getManufactureId())
				? exhibitionForm.getManufactureId() : Whitespace.EMPTY.getValue());
		logger.info("manufactureId: {}", data);
		session.put("exhibition.manufacture.id", data);
		logger.info("=== end put exhibition form data into session ===");
	}

	/**
	 * Log {@code Exhibition} form data.
	 *
	 * @param exhibitionForm
	 *            the {@code ExhibitionForm} instance
	 */
	private final void logExhibitionConfirmation(ExhibitionForm exhibitionForm) {
		logger.info("=== confirm register of exhibition form data ===");
		logger.info("index.........: {}", exhibitionForm.getIndex());
		logger.info("name..........: {}", exhibitionForm.getName());
		logger.info("institution...: {}", exhibitionForm.getInstitution());
		logger.info("year..........: {}", exhibitionForm.getYear());
		logger.info("dateFrom......: {}", exhibitionForm.getDateFrom());
		logger.info("dateTo........: {}", exhibitionForm.getDateTo());
		logger.info("city..........: {}", exhibitionForm.getCity());
		logger.info("country.......: {}", exhibitionForm.getCountry());
		logger.info("comment.......: {}", exhibitionForm.getComment());
		logger.info("empty table...: {}", exhibitionForm.getEmptyTable());
		logger.info("manufacture id: {}", exhibitionForm.getManufactureId());
		logger.info("=== end confirm register of exhibition form data ===");
	}

	/**
	 * Insert method description here...
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	private final void logExhibitionCompletion(Session session) {
		logger.info("ExhibitionController.logExhibitionCompletion -> Session id: " + session.getId());
		Long id = null;
		if (session.get("exhibition.id") != null) {
			id = new Long(session.get("exhibition.id"));
		}
		Integer version = null;
		if (session.get("exhibition.version") != null) {
			version = new Integer(session.get("exhibition.version")).intValue();
		}
		logger.info("=== complete register of exhibition session data ===");
		logger.info("id............: {}", id);
		logger.info("version.......: {}", version);
		logger.info("index.........: {}", session.get("exhibition.index"));
		logger.info("year..........: {}", session.get("exhibition.year"));
		logger.info("name..........: {}", session.get("exhibition.name"));
		logger.info("institution...: {}", session.get("exhibition.institution"));
		logger.info("dateFrom......: {}", session.get("exhibition.dateFrom"));
		logger.info("dateTo........: {}", session.get("exhibition.dateTo"));
		logger.info("city..........: {}", session.get("exhibition.city"));
		logger.info("country.......: {}", session.get("exhibition.country"));
		logger.info("comment.......: {}", session.get("exhibition.comment"));
		logger.info("empty table...: {}", session.get("exhibition.empty.table"));
		logger.info("manufacture id: {}", session.get("exhibition.manufacture.id"));
		logger.info("=== end complete register of exhibition session data ===");
	}

	/**
	 * Logging of confirmation of {@code Exhibition} data.
	 * 
	 * @param manufactureList
	 *            list of {@code Manufacture} years
	 * @param emptyTable
	 *            the empty table indicator
	 * @param selectedManufactureId
	 *            the selected {@code Manufacture} identifier
	 * @param selectedManufactureYear
	 *            the selected {@code Manufacture} year
	 * @param exhibitionIndex
	 *            the {@code Exhibition} index
	 * @param manufactureIndex
	 *            the {@code Manufacture} index
	 * @param manufactureIndex
	 *            the {@code Manufacture} identifier
	 */
	final void logExtraData(List<ManufactureDto> manufactureList, Boolean emptyTable, Long selectedManufactureId,
			String selectedManufactureYear, Integer exhibitionIndex, Integer manufactureIndex, String manufactureId) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.exhibition.extra.data"));
		if (isLog) {
			logger.info("=== exhibition extra data ===");
			logger.info("ExhibitionController.logExtraData emptyTable.............: {}", emptyTable);
			logger.info("ExhibitionController.logExtraData manufactureListSize....: {}", manufactureList.size());
			logger.info("ExhibitionController.logExtraData selectedManufactureId..: {}", selectedManufactureId);
			logger.info("ExhibitionController.logExtraData selectedManufactureYear: {}", selectedManufactureYear);
			logger.info("ExhibitionController.logExtraData exhibitionIndex........: {}", exhibitionIndex);
			logger.info("ExhibitionController.logExtraData manufactureIndex.......: {}", manufactureIndex);
			logger.info("ExhibitionController.logExtraData manufactureId..........: {}", manufactureId);
			logger.info("=== end exhibition extra data ===");
		}
	}

}
