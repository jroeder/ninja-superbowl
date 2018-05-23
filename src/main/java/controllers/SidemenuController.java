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

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dao.BotanicSystemDao;
import dao.BowlDao;
import dao.CustomerDao;
import dao.GeoRegionDao;
import dao.StatusDao;
import dao.TimberDao;
import dto.BotanicSystemDto;
import dto.CustomerDto;
import dto.GeoRegionDto;
import dto.StatusDto;
import dto.TimberDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
@Singleton
public class SidemenuController {

	/**
	 * This is the superbowl botanic system data access object instance
	 */
	@Inject
	private BotanicSystemDao botanicSystemDao;

	/**
	 * This is the superbowl bowl data access object instance
	 */
	@Inject
	private BowlDao bowlDao;

	/**
	 * This is the superbowl customer data access object instance
	 */
	@Inject
	private CustomerDao customerDao;

	/**
	 * This is the superbowl geo region data access object instance
	 */
	@Inject
	private GeoRegionDao geoRegionDao;

	/**
	 * This is the superbowl status data access object instance
	 */
	@Inject
	private StatusDao statusDao;

	/**
	 * This is the superbowl botanic system data access object instance
	 */
	@Inject
	private TimberDao timberDao;

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result timber(Context context) {

		// TimberListDto timberListDto = timberDao.getAllTimber();
		// context.getFlashScope().put("holzarten", timberListDto);
		// return Results.html().render("holzarten",
		// timberListDto.getTimberList());
		List<TimberDto> timberList = timberDao.getAllTimbers();
		return Results.html().render("holzarten", timberList);
		// return
		// Results.html().template("views/SidemenuController/timber.ftl.html").render("timberTotal",
		// timberListDto.getTimberList());
		// return
		// Results.html().template("views/SidemenuController/timber.ftl.html");

	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result customer(Context context) {

		// CustomerListDto customerDto = customerDao.getAllCustomers();
		// context.getFlashScope().put("customerTotal", customerDto);
		// return
		// Results.html().template("views/SidemenuController/customer.ftl.html").render("customer",
		// customerDto.getCustomerList());
		// return Results.html().render("customer",
		// customerDto.getCustomerList());
		// return
		// Results.html().template("views/SidemenuController/customer.ftl.html");

		List<CustomerDto> customerList = customerDao.getAllCustomers();
		return Results.html().template("views/SidemenuController/customer.ftl.html").render("customers", customerList);

	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result botanicSystem(Context context) {

		// BotanicSystemListDto botanicSystemDto = null;
		// botanicSystemDto = botanicSystemDao.getAllBotanicSystems();
		// context.getFlashScope().put("botanicSystemTotal", botanicSystemDto);
		// return Results.html().render("/staticdata/botanicSystem/",
		// botanicSystemDto.getBotanicSystemList());

		// return Results.html().redirect("/staticdata/botanicSystem/");
		// return
		// Results.redirect(router.getReverseRoute(SidemenuController.class,
		// "botanicSystem"));
		// return
		// Results.html().template("views/SidemenuController/staticdata.ftl.html");

		List<BotanicSystemDto> botanicSystemList = botanicSystemDao.getAllBotanicSystems();
		return Results.html().template("views/SidemenuController/botanicSystem.ftl.html").render("botanicSystems",
				botanicSystemList);

	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result geoRegion(Context context) {

		// GeoRegionListDto geoRegionDto = null;
		// geoRegionDto = geoRegionDao.getAllGeoRegions();
		// context.getFlashScope().put("geoRegionTotal", geoRegionDto);
		// return Results.html().render("/staticdata/geoRegion/",
		// geoRegionDto.getGeoRegionList());

		// return Results.html().redirect("/staticdata/geoRegion/");

		List<GeoRegionDto> geoRegionList = geoRegionDao.listGeoRegions();
		// return Results.html().render("geoRegions", geoRegionList);
		// return Results.html().render("geoRegions",
		// geoRegionList).template("views/SidemenuController/geoRegion.ftl.html");
		return Results.html().template("views/SidemenuController/geoRegion.ftl.html").render("geoRegions",
				geoRegionList);
		// return
		// Results.html().template("views/SidemenuController/staticdata/geoRegion.ftl.html");

	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result status(Context context) {

		// StatusListDto statusDto = statusDao.getAllStatus();
		// context.getFlashScope().put("statusTotal", statusDto);
		// return Results.html().render("status", statusDto.getStatusList());
		// return
		// Results.html().template("views/SidemenuController/staticdata/status.ftl.html");
		List<StatusDto> statusList = statusDao.getAllStati();
		return Results.html().template("views/SidemenuController/status.ftl.html").render("stati", statusList);

	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result calendar(Context context) {

		return Results.html().template("views/SidemenuController/calendar.ftl.html");

	}

}
