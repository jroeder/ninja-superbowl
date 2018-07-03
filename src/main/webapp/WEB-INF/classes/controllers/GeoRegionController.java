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

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import dto.Dto;
import dto.GeoRegionDto;
import forms.Form;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.SecureFilter;
import ninja.params.PathParam;
import ninja.session.Session;
import ninja.validation.Validation;
import services.GeoRegionService;

/**
 * Controller instance to handle user requests regarding a {@code GeoRegion}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
@Singleton
public class GeoRegionController extends AbstractEntityController {

	/**
	 * This is the superbowl geo region service
	 */
	@Inject
	private GeoRegionService geoRegionService;

	/**
	 * Constructor.
	 */
	public GeoRegionController() {
		super();
	}

	/**
	 * Create new geoRegion.
	 *
	 * @return the result page
	 */
	@FilterWith(SecureFilter.class)
	public Result geoRegionNew() {
		return Results.html();
	}

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
		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		return Results.html().render("geoRegions", geoRegionList);
	}

	/**
	 * Shows an <code>GeoRegion</code> with the provided identifier.
	 *
	 * @param id
	 *            the geoRegion unique technical identifier
	 * @param validation
	 *            the {@code Validation} instance
	 * @return {@code Result} instance
	 */
	public Result geoRegionById(@PathParam("id") Long id, Validation validation) {
		Map<String, Object> map = Maps.newHashMap();
		if (validation.hasViolations()) {
			map.put("errors", validation.getViolations());
			map.put("id", id);
			return Results.html().render(map).template("views/GeoRegionController/geoRegion.ftl.html");
		}
		GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionById(id);
		return Results.html().render("geoRegion", geoRegionDto);
	}

	/**
	 * Shows an <code>GeoRegion</code> with the provided code.
	 *
	 * @param code
	 *            the geoRegion code
	 * @param validation
	 *            the {@code Validation} instance
	 * @return {@code Result} instance
	 */
	public Result geoRegionByCode(@PathParam("code") String code, Validation validation) {
		Map<String, Object> map = Maps.newHashMap();
		if (validation.hasViolations()) {
			map.put("errors", validation.getViolations());
			map.put("code", code);
			return Results.html().render(map).template("views/GeoRegionController/geoRegion.ftl.html");
		}
		GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionByCode(code);
		return Results.html().render("geoRegion", geoRegionDto);
	}

	/**
	 * Puts geo region data from a user interface form into the session instance.
	 * <p>
	 * Checks every geo region value from session instance for <code>null</code>
	 * value. If geo region value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	public Dto getDtoFromSession(Session session) {
		return null;
	}

	/**
	 * Puts geo region data from a user interface form into the session instance.
	 * <p>
	 * Checks every geo region value from session instance for <code>null</code>
	 * value. If geo region value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param dto
	 *            the {@code GeoRegionDto} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	public void putDtoIntoSession(Dto dto, Session session) {
			// ToDo: Implement code to put timber dto into session
	}

	/**
	 * Puts geo region data from a user interface form into the session instance.
	 * <p>
	 * Checks every geo region value from session instance for <code>null</code>
	 * value. If tgeo region value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param form
	 *            the {@code Form} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	public void putFormIntoSession(Form form, Session session) {
		// ToDo: Implement code to put timber form into session
	}

}
