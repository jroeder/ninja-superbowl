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

import com.google.inject.Inject;

import dto.Dto;
import dto.StatusDto;
import forms.Form;
import forms.StatusForm;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.params.PathParam;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import services.StatusService;
import types.Checker;
import types.Whitespace;

/**
 * IController instance to handle user requests regarding a {@code Status}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
public class StatusController extends AbstractEntityController {

	/**
	 * This is the superbowl status service
	 */
	@Inject
	private StatusService statusService;

	/**
	 * Insert Constructor description here...
	 */
	public StatusController() {
		super();
	}

	/**
	 * Retrieves all status from data store.
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return rendered status page with a list of bowls
	 */
	public Result list(Context context) {
		Session session = context.getSession();
		session.clear();
		List<StatusDto> statusList = statusService.listStatus();
		return Results.html().render("stati", statusList);
	}

	/**
	 * Retrieve a bowl from database by identifier.
	 *
	 * @param id
	 *            the unique status technical identifier
	 * @return {@code Result} instance
	 */
	public Result retrieve(@PathParam("id") Long id) {
		StatusDto statusDto = null;
		if (id != null) {
			statusDto = statusService.getStatusById(id);
		} else {
			return Results.html();
		}
		return Results.html().render("stati", statusDto);
	}

	/**
	 * Handles the registration of a new {@code Status}.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param statusIndex
	 *            the current maximum index number of persisted {@code Status}
	 *            incremented by one (1)
	 * @param statusCode
	 *            the {@code Status} code
	 * @param statusText
	 *            the {@code Status} text
	 * @param statusComment
	 *            the {@code Status} comment
	 * @return {@code Result} instance
	 */
	public Result registerStatus(Session session, Context context, @Param("statusIndex") String statusIndex,
			@Param("statusCode") String statusCode, @Param("statusText") String statusText,
			@Param("statusComment") String statusComment) {
		logger.info("StatusController.registerStatus -> Session id......: " + session.getId());
		logger.info("StatusController.registerStatus -> Context path....: " + context.getContextPath());
		logger.info("StatusController.registerStatus -> Status  index...: " + statusIndex);
		logger.info("StatusController.registerStatus -> Status  code....: " + statusCode);
		logger.info("StatusController.registerStatus -> Status  text....: " + statusText);
		logger.info("StatusController.registerStatus -> Status  comment.: " + statusComment);

		StatusDto statusDto = statusService.getStatusMaxIndex();
		logger.info("StatusController.registerStatus -> Status max index: " + statusDto.getIndex());
		// Add 1 to current max index
		statusDto.setIndex(statusDto.getIndex() + 1);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusMaxIndex", statusDto.getIndex());
		map.put("statusIndex", statusIndex);
		map.put("statusCode", statusCode);
		map.put("statusText", statusText);
		map.put("statusComment", statusComment);

		session.clear();

		return Results.html().render(map);
		// return
		// Results.html().render(map).template("views/StatusController/registerStatus.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param statusForm
	 *            the {@code Ninja StatusForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerStatusConfirmation(@JSR303Validation StatusForm statusForm, Session session,
			Validation validation, Context context) {

		logger.info("StatusController.registerStatusConfirmation -> Session id...: " + session.getId());
		logger.info("StatusController.registerStatusConfirmation -> Session empty: " + session.isEmpty());
		logger.info("StatusController.registerStatusConfirmation -> Context......: " + context.getRequestPath());
		logger.info("StatusController.registerStatusConfirmation -> Violations...: " + validation.hasViolations());

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("code".equals(fieldViolation.getFieldKey())
						&& "{code.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			StatusDto statusDto = statusService.getStatusMaxIndex();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusMaxIndex", statusDto.getIndex() + 1);

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("status", statusForm);
			result.template("views/StatusController/registerStatus.ftl.html");

			session.save(context);

			return result;

		} else {

			// Status in Session speichern
			putFormIntoSession(statusForm, session);

			Result result = Results.html();
			result.render("index", statusForm.getIndex());
			result.render("code", statusForm.getCode());
			result.render("text", statusForm.getText());
			result.render("comment", statusForm.getComment());

			result.render("status", statusForm);

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
	public Result registerStatusCompletion(Session session, Context context) {

		logger.info("StatusController.registerStatusCompletion -> Session id: " + session.getId());
		logger.info("StatusController.registerStatusCompletion -> Context: " + context.getRequestPath());

		StatusDto statusDto = (StatusDto) getDtoFromSession(session);

		statusService.register(statusDto);

		session.clear();

		// return Results.redirect("/status");
		return Results.ok().redirect("/superbowl/status");

	}

	/**
	 * Puts status data from a user interface form into the session instance.
	 * <p>
	 * Checks every status value from session instance for <code>null</code>
	 * value. If status value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param form
	 *            the {@code Ninja StatusForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	public void putFormIntoSession(Form form, Session session) {

		logger.info("StatusController.putFormIntoSession -> Session id: " + session.getId());

		StatusForm statusForm = (StatusForm) form;

		logStatusConfirmation(statusForm);

		String data = null;

		logger.info("=== put status form into session ===");
		session.put("status.id", null);
		session.put("status.version", "0");
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(statusForm.getIndex()) ? statusForm.getIndex()
				: Whitespace.EMPTY.getValue());
		logger.info("index....: {}", data);
		session.put("status.index", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(statusForm.getCode()) ? statusForm.getCode()
				: Whitespace.EMPTY.getValue());
		logger.info("code.....: {}", data);
		session.put("status.code", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(statusForm.getText()) ? statusForm.getText()
				: Whitespace.EMPTY.getValue());
		logger.info("text.....: {}", data);
		session.put("status.text", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(statusForm.getComment()) ? statusForm.getComment()
				: Whitespace.EMPTY.getValue());
		logger.info("comment....: {}", data);
		session.put("status.comment", data);
		logger.info("=== end put status form into session ===");

	}

	/**
	 * Provides a StatusDto instance with status data from session instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @return the {@code Ninja StatusDto} instance
	 */
	public Dto getDtoFromSession(Session session) {

		logger.info("StatusController.getDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("status.id") != null) {
			id = new Long(session.get("status.id"));
		}
		Integer version = null;
		if (session.get("status.version") != null) {
			version = new Integer(session.get("status.version")).intValue();
		}
		Integer index = null;
		if (session.get("status.index") != null) {
			index = new Integer(session.get("status.index")).intValue();
		}
		String code = session.get("status.code");
		String text = session.get("status.text");
		String comment = session.get("status.comment");

		Dto statusDto = new StatusDto(id, version, index, code, text, comment);

		logStatusCompletion(session);

		return statusDto;

	}

	/**
	 * Puts status data from a user interface form into the session instance.
	 * <p>
	 * Checks every status value from session instance for <code>null</code>
	 * value. If status value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param dto
	 *            the {@code StatusDto} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	public void putDtoIntoSession(Dto dto, Session session) {
		// ToDo: Implement code to put timber dto into session
	}

	/**
	 * Insert method description here...
	 *
	 * @param statusForm
	 *            the {@code Ninja StatusForm} instance
	 */
	private final void logStatusConfirmation(StatusForm statusForm) {
		logger.info("=== confirm register of status (form data) ===");
		logger.info("index......: {}", statusForm.getIndex());
		logger.info("code.......: {}", statusForm.getCode());
		logger.info("text.......: {}", statusForm.getText());
		logger.info("comment....: {}", statusForm.getComment());
		logger.info("=== end confirm register of status (form data) ===");
	}

	/**
	 * Insert method description here...
	 *
	 * @param statusForm
	 *            the {@code Ninja StatusForm} instance
	 */
	private final void logStatusCompletion(StatusForm statusForm) {
		logger.info("=== complete register of status (form data) ===");
		logger.info("index......: {}", statusForm.getIndex());
		logger.info("code.......: {}", statusForm.getCode());
		logger.info("text.......: {}", statusForm.getText());
		logger.info("comment....: {}", statusForm.getComment());
		logger.info("=== end complete register of status (form data) ===");
	}

	/**
	 * Insert method description here...
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	private final void logStatusCompletion(Session session) {
		logger.info("StatusController.logStatusCompletion -> Session id: " + session.getId());
		Long id = null;
		if (session.get("status.id") != null) {
			id = new Long(session.get("status.id"));
		}
		Integer version = null;
		if (session.get("status.version") != null) {
			version = new Integer(session.get("status.version")).intValue();
		}
		Integer index = null;
		if (session.get("status.index") != null) {
			index = new Integer(session.get("status.index")).intValue();
		}
		logger.info("=== complete register of status (session data) ===");
		logger.info("id.........: {}", id);
		logger.info("version....: {}", version);
		logger.info("index  ....: {}", index);
		logger.info("code.......: {}", session.get("status.code"));
		logger.info("text.......: {}", session.get("status.text"));
		logger.info("comment....: {}", session.get("status.comment"));
		logger.info("=== end complete register of status (session data) ===");
	}

}
