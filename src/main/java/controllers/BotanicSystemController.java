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

import com.google.common.collect.Maps;
import com.google.inject.Inject;

import dto.BotanicSystemDto;
import dto.Dto;
import forms.BotanicSystemForm;
import forms.Form;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import services.BotanicSystemService;
import types.Checker;
import types.SuperbowlHelper;
import types.Whitespace;

/**
 * Controller instance to handle user requests regarding a
 * {@code BotanicSystem}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
public class BotanicSystemController extends AbstractEntityController {

	/**
	 * Constant value of numeric zero (0).
	 */
	private static final Integer ZERO = new Integer(0);

	/**
	 * Constant value of numeric one (1).
	 */
	private static final Integer ONE = new Integer(1);

	/**
	 * This is the superbowl {@code BotanicSystem} service
	 */
	@Inject
	private BotanicSystemService botanicSystemService;

	/**
	 * Create new {@code BotanicSystem}.
	 *
	 * @return the result page
	 */
	@FilterWith(SecureFilter.class)
	public Result botanicSystemNew() {
		return Results.html();
	}

	/**
	 * Retrieves all {@code BotanicSystem}s from data store.
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return rendered {@code BotanicSystem}s page with a list of
	 *         {@code BotanicSystem}s
	 */
	public Result list(Context context) {
		Session session = context.getSession();
		session.clear();
		List<BotanicSystemDto> botanicSystemList = botanicSystemService.listBotanicSystem();
		return Results.html().render("botanicSystems", botanicSystemList);
	}

	/**
	 * Register a new {@code BotanicSystem}.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code BotanicSystem} table has entries
	 *            or not
	 * @return {@code Result} instance
	 */
	public Result registerBotanicSystem(Session session, Context context, @Param("emptyTable") Boolean emptyTable) {
		logger.info("BotanicSystemController.registerBotanicSystem -> Session id....: " + session.getId());
		logger.info("BotanicSystemController.registerBotanicSystem -> Empty Table...: " + emptyTable.toString());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bsEmptyTable", emptyTable.toString());

		BotanicSystemDto sessionBotanicSystemDto = null;
		BotanicSystemDto botanicSystemDto = null;
		Integer botanicSystemOrderIndex = null;
		Integer botanicSystemFamilyIndex = null;
		Integer botanicSystemSubFamilyIndex = null;
		Integer botanicSystemMaxOrdinal = null;

		if (emptyTable) {
			botanicSystemOrderIndex = ZERO;
			botanicSystemFamilyIndex = ZERO;
			botanicSystemSubFamilyIndex = ZERO;
			botanicSystemMaxOrdinal = ZERO;
		} else {
			// BotanicSystem max index ermitteln
			botanicSystemDto = botanicSystemService.getBotanicSystemMaxOrderIndex();
			botanicSystemOrderIndex = botanicSystemDto.getOrderIndex() + 1;
			botanicSystemDto = botanicSystemService.getBotanicSystemMaxFamilyIndex();
			botanicSystemFamilyIndex = botanicSystemDto.getFamilyIndex() + 1;
			botanicSystemDto = botanicSystemService.getBotanicSystemMaxSubFamilyIndex();
			botanicSystemSubFamilyIndex = botanicSystemDto.getSubFamilyIndex() + 1;
			botanicSystemDto = botanicSystemService.getBotanicSystemMaxOrdinal();
			botanicSystemMaxOrdinal = botanicSystemDto.getOrdinal() + 1;
		}

		map.put("botanicSystemOrderIndex", botanicSystemOrderIndex);
		map.put("botanicSystemFamilyIndex", botanicSystemFamilyIndex);
		map.put("botanicSystemSubFamilyIndex", botanicSystemSubFamilyIndex);
		map.put("botanicSystemMaxOrdinal", botanicSystemMaxOrdinal);

		logExtraData(emptyTable, botanicSystemMaxOrdinal, botanicSystemOrderIndex, botanicSystemFamilyIndex,
				botanicSystemSubFamilyIndex);

		if (botanicSystemOrderIndex.equals(ZERO) && botanicSystemFamilyIndex.equals(ZERO)
				&& botanicSystemSubFamilyIndex.equals(ZERO)) {
			// alles auf default setzen
		} else {
			sessionBotanicSystemDto = (BotanicSystemDto) getDtoFromSession(session);
		}

		if (sessionBotanicSystemDto != null) {
			logger.info(sessionBotanicSystemDto.asString());
			if (sessionBotanicSystemDto.getOrdinal() != null) {
				map.put("bsOrdinal", sessionBotanicSystemDto.getOrdinal());
			} else {
				map.put("bsOrdinal", EMPTY);
			}
			if (sessionBotanicSystemDto.getOrderIndex() != null) {
				map.put("bsOrderIndex", sessionBotanicSystemDto.getOrderIndex());
			} else {
				map.put("bsOrderIndex", EMPTY);
			}
			if (sessionBotanicSystemDto.getFamilyIndex() != null) {
				map.put("bsFamilyIndex", sessionBotanicSystemDto.getFamilyIndex());
			} else {
				map.put("bsFamilyIndex", EMPTY);
			}
			if (sessionBotanicSystemDto.getSubFamilyIndex() != null) {
				map.put("bsSubFamilyIndex", sessionBotanicSystemDto.getSubFamilyIndex());
			} else {
				map.put("bsSubFamilyIndex", EMPTY);
			}
			if (sessionBotanicSystemDto.getOrder() != null) {
				map.put("bsOrder", SuperbowlHelper.replaceSpaceByMarker(sessionBotanicSystemDto.getOrder()));
			} else {
				map.put("bsOrder", EMPTY);
			}
			if (sessionBotanicSystemDto.getFamily() != null) {
				map.put("bsFamily", SuperbowlHelper.replaceSpaceByMarker(sessionBotanicSystemDto.getFamily()));
			} else {
				map.put("bsFamily", EMPTY);
			}
			if (sessionBotanicSystemDto.getSubFamily() != null) {
				map.put("bsSubFamily", SuperbowlHelper.replaceSpaceByMarker(sessionBotanicSystemDto.getSubFamily()));
			} else {
				map.put("bsSubFamily", EMPTY);
			}
		} else {
			map.put("bsOrdinal", EMPTY);
			map.put("bsOrderIndex", botanicSystemOrderIndex.toString());
			map.put("bsFamilyIndex", botanicSystemFamilyIndex.toString());
			map.put("bsSubFamilyIndex", botanicSystemSubFamilyIndex.toString());
			map.put("bsOrder", EMPTY);
			map.put("bsFamily", EMPTY);
			map.put("bsSubFamily", EMPTY);
		}

		return Results.html().render(map).template("views/BotanicSystemController/registerBotanicSystem.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param botanicSystemForm
	 *            the {@code BotanicSystemForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerBotanicSystemConfirmation(@JSR303Validation BotanicSystemForm botanicSystemForm,
			Session session, Validation validation, Context context) {
		logger.info("BotanicSystemController.registerBotanicSystemConfirmation -> Session    id: " + session.getId());
		logger.info("BotanicSystemController.registerBotanicSystemConfirmation -> Session empty: " + session.isEmpty());
		logger.info("BotanicSystemController.registerBotanicSystemConfirmation -> Context......: "
				+ context.getRequestPath());
		logger.info("BotanicSystemController.registerBotanicSystemConfirmation -> Violations...: "
				+ validation.hasViolations());

		logger.info("========== BotanicSystemController.registerBotanicSystemConfirmation ==========");
		logger.info("BotanicSystemController:BotanicSystemForm -> Id.............: " + botanicSystemForm.getId());
		logger.info("BotanicSystemController:BotanicSystemForm -> Version........: " + botanicSystemForm.getVersion());
		logger.info("BotanicSystemController:BotanicSystemForm -> Ordinal........: " + botanicSystemForm.getOrdinal());
		logger.info(
				"BotanicSystemController:BotanicSystemForm -> Order Index....: " + botanicSystemForm.getOrderIndex());
		logger.info(
				"BotanicSystemController:BotanicSystemForm -> Family Index...: " + botanicSystemForm.getFamilyIndex());
		logger.info("BotanicSystemController:BotanicSystemForm -> SubFamily Index: "
				+ botanicSystemForm.getSubFamilyIndex());
		logger.info("BotanicSystemController:BotanicSystemForm -> Order..........: " + botanicSystemForm.getOrder());
		logger.info("BotanicSystemController:BotanicSystemForm -> Family.........: " + botanicSystemForm.getFamily());
		logger.info(
				"BotanicSystemController:BotanicSystemForm -> SubFamily......: " + botanicSystemForm.getSubFamily());
		logger.info(
				"BotanicSystemController:BotanicSystemForm -> EmptyTable.....: " + botanicSystemForm.getEmptyTable());
		logger.info("============================= End ===================================");

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("order".equals(fieldViolation.getFieldKey())
						&& "{botanicsystem.order.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("family".equals(fieldViolation.getFieldKey())
						&& "{botanicsystem.family.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("botanicSystemOrderIndex", botanicSystemForm.getOrderIndex());
			map.put("botanicSystemFamilyIndex", botanicSystemForm.getFamilyIndex());
			map.put("botanicSystemSubFamilyIndex", botanicSystemForm.getSubFamilyIndex());
			map.put("bsOrderIndex", botanicSystemForm.getOrderIndex());
			map.put("bsFamilyIndex", botanicSystemForm.getFamilyIndex());
			map.put("bsSubFamilyIndex", botanicSystemForm.getSubFamilyIndex());
			map.put("bsOrdinal", botanicSystemForm.getOrdinal());
			map.put("bsOrder", SuperbowlHelper.replaceSpaceByMarker(botanicSystemForm.getOrder()));
			map.put("bsFamily", SuperbowlHelper.replaceSpaceByMarker(botanicSystemForm.getFamily()));
			map.put("bsSubFamily", SuperbowlHelper.replaceSpaceByMarker(botanicSystemForm.getSubFamily()));
			map.put("bsEmptyTable", botanicSystemForm.getEmptyTable());

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("botanicSystem", botanicSystemForm);
			result.template("views/BotanicSystemController/registerBotanicSystem.ftl.html");

			session.save(context);

			return result;

		} else {

			putFormIntoSession(botanicSystemForm, session);

			Result result = Results.html();
			result.render("bsOrdinal", botanicSystemForm.getOrdinal());
			result.render("bSOrderIndex", botanicSystemForm.getOrderIndex());
			result.render("bsFamilyIndex", botanicSystemForm.getFamilyIndex());
			result.render("bsSubFamilyIndex", botanicSystemForm.getSubFamilyIndex());
			result.render("bsOrder", botanicSystemForm.getOrder());
			result.render("bsFamily", botanicSystemForm.getFamily());
			result.render("bsSubFamily", botanicSystemForm.getSubFamily());
			result.render("bsEmptyTable", botanicSystemForm.getEmptyTable());

			result.render("botanicSystem", botanicSystemForm);

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
	public Result registerBotanicSystemCompletion(Session session, Context context) {
		logger.info("BotanicSystemController.registerBotanicSystemCompletion -> Session    id: " + session.getId());
		logger.info("BotanicSystemController.registerBotanicSystemCompletion -> Session empty: " + session.isEmpty());
		logger.info("BotanicSystemController.registerBotanicSystemCompletion -> Context......: "
				+ context.getRequestPath());

		BotanicSystemDto botanicSystemDto = (BotanicSystemDto) getDtoFromSession(session);

		botanicSystemService.register(botanicSystemDto);

		session.clear();

		return Results.ok().redirect("/superbowl/botanicSystem");
	}

	/**
	 * Shows an {@code BotanicSystem} with the provided identifier.
	 *
	 * @param id
	 *            the unique botanic system identifier
	 * @param validation
	 *            the {@code Validation} instance
	 * @return the {@code BotanicSystem} instance or <code>null</code>
	 */
	public Result botanicSystemById(@PathParam("id") Long id, Validation validation) {
		Map<String, Object> map = Maps.newHashMap();
		if (validation.hasViolations()) {
			map.put("errors", validation.getViolations());
			map.put("id", id);
			return Results.html().render(map).template("views/BotanicSystemController/botanicSystem.ftl.html");
		}
		BotanicSystemDto botanicSystemDto = botanicSystemService.getBotanicSystemById(id);
		return Results.html().render("botanicSystem", botanicSystemDto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.BotanicSystemController#getDtoFromSession(ninja.session.
	 * Session)
	 */
	@Override
	public Dto getDtoFromSession(Session session) {
		logger.info("BotanicSystemController.getDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("bs.id") != null) {
			id = new Long(session.get("bs.id"));
		}
		Integer version = null;
		if (session.get("bs.version") != null) {
			version = new Integer(session.get("bs.version")).intValue();
		}
		String ordinal = null;
		if (session.get("bs.ordinal") != null) {
			ordinal = session.get("bs.ordinal");
		}
		Integer orderIndex = null;
		if (session.get("bs.order.index") != null) {
			String oIndex = session.get("bs.order.index");
			if (oIndex.isEmpty()) {
				orderIndex = new Integer(ZERO);
			} else {
				orderIndex = new Integer(session.get("bs.order.index")).intValue();
			}
		}
		Integer familyIndex = null;
		if (session.get("bs.family.index") != null) {
			String fIndex = session.get("bs.family.index");
			if (fIndex.isEmpty()) {
				familyIndex = new Integer(ZERO);
			} else {
				familyIndex = new Integer(session.get("bs.family.index")).intValue();
			}
		}
		Integer subFamilyIndex = null;
		if (session.get("bs.sub.family.index") != null) {
			String sfIndex = session.get("bs.sub.family.index");
			if (sfIndex.isEmpty()) {
				subFamilyIndex = new Integer(ZERO);
			} else {
				subFamilyIndex = new Integer(session.get("bs.sub.family.index")).intValue();
			}
		}
		String order = null;
		if (session.get("bs.order") != null) {
			order = session.get("bs.order");
		}
		String family = null;
		if (session.get("bs.family") != null) {
			family = session.get("bs.family");
		}
		String subFamily = null;
		if (session.get("bs.sub.family") != null) {
			subFamily = session.get("bs.sub.family");
		}
		String emptyTable = null;
		if (session.get("bs.empty.table") != null) {
			emptyTable = session.get("bs.empty.table");
		}

		logBotanicSystemCompletion(session);

		Dto botanicSystemDto = null;

		if (ordinal != null) {
			botanicSystemDto = new BotanicSystemDto(id, version, new Integer(ordinal), orderIndex, familyIndex,
					subFamilyIndex, order, family, subFamily, emptyTable);
		}

		return botanicSystemDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.BotanicSystemController#putDtoIntoSession(dto.Dto,
	 * ninja.session.Session)
	 */
	@Override
	public void putDtoIntoSession(Dto dto, Session session) {
		// ToDo: Implement code to put botanic system dto into session
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.BotanicSystemController#putFormIntoSession(forms.Form,
	 * ninja.session.Session)
	 */
	@Override
	public void putFormIntoSession(Form form, Session session) {
		logger.info("BotanicSystemController.putFormIntoSession -> Session id: " + session.getId());

		BotanicSystemForm botanicSystemForm = (BotanicSystemForm) form;
		logger.info("BotanicSystemController.putFormIntoSession -> BotanicSystemForm: " + botanicSystemForm.asString());

		logBotanicSystemConfirmation(botanicSystemForm);

		String data = null;

		logger.info("=== put botanic system form data into session ===");
		session.put("bs.id", null);
		session.put("bs.version", "0");

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(botanicSystemForm.getOrdinal())
				? botanicSystemForm.getOrdinal() : Whitespace.EMPTY.getValue());
		logger.info("ordinal.......: {}", data);
		session.put("bs.ordinal", data);

		logger.info("orderIndex....: {}", botanicSystemForm.getOrderIndex());
		session.put("bs.order.index", botanicSystemForm.getOrderIndex());

		logger.info("familyIndex...: {}", botanicSystemForm.getFamilyIndex());
		session.put("bs.family.index", botanicSystemForm.getFamilyIndex());

		logger.info("subFamilyIndex: {}", botanicSystemForm.getSubFamilyIndex());
		session.put("bs.sub.family.index", botanicSystemForm.getSubFamilyIndex());

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(botanicSystemForm.getOrder())
				? botanicSystemForm.getOrder() : Whitespace.EMPTY.getValue());
		logger.info("order.......... {}", data);
		session.put("bs.order", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(botanicSystemForm.getFamily())
				? botanicSystemForm.getFamily() : Whitespace.EMPTY.getValue());
		logger.info("family........: {}", data);
		session.put("bs.family", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(botanicSystemForm.getSubFamily())
				? botanicSystemForm.getSubFamily() : Whitespace.EMPTY.getValue());
		logger.info("subFamily.....: {}", data);
		session.put("bs.sub.family", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(botanicSystemForm.getEmptyTable())
				? botanicSystemForm.getEmptyTable() : Whitespace.EMPTY.getValue());
		logger.info("emptyTable....: {}", data);
		session.put("bs.empty.table", data);
		logger.info("=== end put botanic system form data into session ===");
	}

	/**
	 * Log {@code BotanicSyste} form data.
	 *
	 * @param botanicSysteForm
	 *            the {@code Ninja BotanicSysteForm} instance
	 */
	private final void logBotanicSystemConfirmation(BotanicSystemForm botanicSystemForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.botanic.system.confirmation"));
		if (isLog) {
			logger.info("=== botanic system confirmation (form data) ===");
			logger.info("id..............: {}", botanicSystemForm.getId());
			logger.info("version.........: {}", botanicSystemForm.getVersion());
			logger.info("ordinal.........: {}", botanicSystemForm.getOrdinal());
			logger.info("order index.....: {}", botanicSystemForm.getOrderIndex());
			logger.info("family index....: {}", botanicSystemForm.getFamilyIndex());
			logger.info("sub family index: {}", botanicSystemForm.getSubFamilyIndex());
			logger.info("order...........: {}", botanicSystemForm.getOrder());
			logger.info("family..........: {}", botanicSystemForm.getFamily());
			logger.info("subFamily.......: {}", botanicSystemForm.getSubFamily());
			logger.info("emptyTable......: {}", botanicSystemForm.getEmptyTable());
			logger.info("=== end botanic system confirmation (form data) ===");
		}
	}

	/**
	 * Insert method description here...
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	private final void logBotanicSystemCompletion(Session session) {
		logger.info("BotanicSystemController.logBotanicSystemCompletion -> Session id   : " + session.getId());
		logger.info("BotanicSystemController.logBotanicSystemCompletion -> Session empty: " + session.isEmpty());
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.botanic.system.completion"));
		if (isLog) {
			Long id = null;
			if (session.get("bs.id") != null) {
				id = new Long(session.get("bs.id"));
			}
			Integer version = null;
			if (session.get("bs.version") != null) {
				version = new Integer(session.get("bs.version")).intValue();
			}
			logger.info("=== botanic system completion (session data) ===");
			logger.info("id..............: {}", id);
			logger.info("version.........: {}", version);
			logger.info("ordinal.........: {}", session.get("bs.ordinal"));
			logger.info("order index.....: {}", session.get("bs.order.index"));
			logger.info("family index....: {}", session.get("bs.family.index"));
			logger.info("sub family index: {}", session.get("bs.sub.family.index"));
			logger.info("order...........: {}", session.get("bs.order"));
			logger.info("family..........: {}", session.get("bs.family"));
			logger.info("subFamily.......: {}", session.get("bs.sub.family"));
			logger.info("emptyTable......: {}", session.get("bs.empty.table"));
			logger.info("=== end botanic system completion (session data) ===");
		}
	}

	/**
	 * Logging of confirmation of {@code BotanicSystem} data.
	 * 
	 * @param emptyTable
	 *            the empty table indicator
	 * @param botanicSystemMaxOrdinal
	 *            the {@code BotanicSystem} maximum ordinal number
	 * @param botanicSystemOrderIndex
	 *            the {@code BotanicSystem} order index
	 * @param botanicSystemFamilyIndex
	 *            the {@code BotanicSystem} family index
	 * @param botanicSystemSubFamilyIndex
	 *            the {@code BotanicSystem} sub family index
	 */
	private final void logExtraData(Boolean emptyTable, Integer botanicSystemMaxOrdinal,
			Integer botanicSystemOrderIndex, Integer botanicSystemFamilyIndex, Integer botanicSystemSubFamilyIndex) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.botanic.system.extra.data"));
		if (isLog) {
			logger.info("=== botanic system extra data ===");
			logger.info("BotanicSystemController.registerBotanicSystem emptyTable.................: {}", emptyTable);
			logger.info("BotanicSystemController.registerBotanicSystem botanicSystemMaxOrdinal....: {}",
					botanicSystemMaxOrdinal);
			logger.info("BotanicSystemController.registerBotanicSystem botanicSystemOrderIndex....: {}",
					botanicSystemOrderIndex);
			logger.info("BotanicSystemController.registerBotanicSystem botanicSystemFamilyIndex...: {}",
					botanicSystemFamilyIndex);
			logger.info("BotanicSystemController.registerBotanicSystem botanicSystemSubFamilyIndex: {}",
					botanicSystemSubFamilyIndex);
			logger.info("=== end botanic system extra data ===");
		}
	}

}
