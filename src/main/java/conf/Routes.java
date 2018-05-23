/**
 * Copyright (C) 2012 the original author or authors.
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
package conf;

import org.slf4j.Logger;

import com.google.inject.Inject;

import controllers.ApplicationController;
import controllers.BotanicSystemController;
import controllers.BowlController;
import controllers.CustomerController;
import controllers.ExhibitionController;
import controllers.GeoRegionController;
import controllers.StatusController;
import controllers.TimberController;
import controllers.TimberOriginController;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class Routes implements ApplicationRoutes {

	/**
	 * This is the system wide logger
	 */
	@Inject
	Logger logger;

	@Inject
	NinjaProperties ninjaProperties;

	/**
	 * Enumeration to handle ninja modes.
	 *
	 * @author mbsusr01
	 */
	private enum NinjaModes {

		MODE_DEV(0), MODE_PROD(1), MODE_TEST(2);

		/**
		 * Constructor.
		 *
		 * @param mode
		 */
		private NinjaModes(final int mode) {
			this.mode = mode;
		}

		// Ninja mode
		private final int mode;

		public int getMode() {
			return mode;
		}

		public static NinjaModes getMode(NinjaProperties ninjaProperties) {
			if (ninjaProperties.isDev()) {
				return MODE_DEV;
			} else if (ninjaProperties.isTest()) {
				return MODE_TEST;
			} else {
				return MODE_PROD;
			}
		}

	}

	/**
	 * Using a (almost) nice DSL we can configure the router.
	 *
	 * The second argument NinjaModuleDemoRouter contains all routes of a
	 * submodule. By simply injecting it we activate the routes.
	 *
	 * @param router
	 *            The default router of this application
	 */
	@Override
	public void init(Router router) {

		/*
		 * Check ninja mode and do some initialization
		 */
		switch (NinjaModes.getMode(ninjaProperties)) {
		case MODE_DEV:
			initDevelopment(router);
			break;

		case MODE_PROD:
			initProduction(router);
			break;

		case MODE_TEST:
			initTest(router);
			break;

		default:
			logger.info("In index ");
			break;
		}

		///////////////////////////////////////////////////////////////////////
		// Index / shows index page
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/").with(ApplicationController.class, "index");
		// router.GET().route("/hello_world.json").with(ApplicationController.class,
		// "helloWorldJson");
		router.GET().route("/superbowl").with(ApplicationController.class, "index");
		// router.GET().route("/superbowl/hello_world.json").with(ApplicationController.class,
		// "helloWorldJson");
		router.GET().route("/exhibitions").with(ApplicationController.class, "exhibitions");
		router.GET().route("/events").with(ApplicationController.class, "events");
		router.GET().route("/news").with(ApplicationController.class, "news");
		router.GET().route("/partners").with(ApplicationController.class, "partners");
		router.GET().route("/profile").with(ApplicationController.class, "profile");
		router.GET().route("/roadmap").with(ApplicationController.class, "roadmap");
		router.GET().route("/settings").with(ApplicationController.class, "settings");
		router.GET().route("/software").with(ApplicationController.class, "software");
		router.GET().route("/specification").with(ApplicationController.class, "specification");
		router.GET().route("/login").with(ApplicationController.class, "login");
		///////////////////////////////////////////////////////////////////////
		// Superbowl Routes
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/superbowl/exhibitions").with(ApplicationController.class, "exhibitions");
		router.GET().route("/superbowl/events").with(ApplicationController.class, "events");
		router.GET().route("/superbowl/news").with(ApplicationController.class, "news");
		router.GET().route("/superbowl/partners").with(ApplicationController.class, "partners");
		router.GET().route("/superbowl/profile").with(ApplicationController.class, "profile");
		router.GET().route("/superbowl/roadmap").with(ApplicationController.class, "roadmap");
		router.GET().route("/superbowl/settings").with(ApplicationController.class, "settings");
		router.GET().route("/superbowl/software").with(ApplicationController.class, "software");
		router.GET().route("/superbowl/specification").with(ApplicationController.class, "specification");
		router.GET().route("/superbowl/login").with(ApplicationController.class, "login");

		///////////////////////////////////////////////////////////////////////
		// SideMenu
		///////////////////////////////////////////////////////////////////////
		// router.GET().route("/calendar").with(SidemenuController.class,
		// "calendar");
		// router.GET().route("/superbowl/calendar").with(SidemenuController.class,
		// "calendar");

		///////////////////////////////////////////////////////////////////////
		// Process BotanicSystem
		///////////////////////////////////////////////////////////////////////

		// List BotanicSystems
		router.GET().route("/botanicSystem").with(BotanicSystemController.class, "list");
		router.GET().route("/registerBotanicSystem").with(BotanicSystemController.class, "registerBotanicSystem");
		router.POST().route("/registerBotanicSystem").with(BotanicSystemController.class, "registerBotanicSystem");
		router.POST().route("/registerBotanicSystemConfirmation").with(BotanicSystemController.class, "registerBotanicSystemConfirmation");
		router.POST().route("/registerBotanicSystemCompletion").with(BotanicSystemController.class, "registerBotanicSystemCompletion");

		// List BotanicSystems
		router.GET().route("/superbowl/botanicSystem").with(BotanicSystemController.class, "list");
		router.GET().route("/superbowl/registerBotanicSystem").with(BotanicSystemController.class, "registerBotanicSystem");
		router.POST().route("/superbowl/registerBotanicSystem").with(BotanicSystemController.class, "registerBotanicSystem");
		router.POST().route("/superbowl/registerBotanicSystemConfirmation").with(BotanicSystemController.class, "registerBotanicSystemConfirmation");
		router.POST().route("/superbowl/registerBotanicSystemCompletion").with(BotanicSystemController.class, "registerBotanicSystemCompletion");

		///////////////////////////////////////////////////////////////////////
		// Process Bowl
		///////////////////////////////////////////////////////////////////////

		// List Bowls
		router.GET().route("/bowl").with(BowlController.class, "listBowl");
		// Bowl Portfolio /Filtering and Sorting)
		router.GET().route("/portfolioBowl").with(BowlController.class, "portfolioBowl");
		// Edit Bowl
		router.GET().route("/editBowl").with(BowlController.class, "editBowl");
		router.GET().route("/editBowlByStatusCode").with(BowlController.class, "editBowl");
		router.POST().route("/editBowlConfirmation").with(BowlController.class, "editBowlConfirmation");
		router.POST().route("/editBowlCompletion").with(BowlController.class, "editBowlCompletion");
		// Update Bowl
		router.GET().route("/updateBowl").with(BowlController.class, "updateBowl");
		router.POST().route("/updateBowlConfirmation").with(BowlController.class, "updateBowlConfirmation");
		router.POST().route("/updateBowlCompletion").with(BowlController.class, "updateBowlCompletion");
		// Modify Bowl
		router.GET().route("/modifyBowl").with(BowlController.class, "modifyBowl");
		// Register Bowl
		router.GET().route("/registerBowl").with(BowlController.class, "registerBowl");
		router.POST().route("/registerBowl").with(BowlController.class, "registerBowl");
		router.GET().route("/registerBowlByGeoRegionCode").with(BowlController.class, "registerBowlByGeoRegionCode");
		router.GET().route("/registerBowlByManufactureYear").with(BowlController.class,
				"registerBowlByManufactureYear");
		router.GET().route("/registerBowlByStatusCode").with(BowlController.class, "registerBowlByStatusCode");
		router.GET().route("/registerBowlByTimberCode").with(BowlController.class, "registerBowlByTimberCode");
		router.POST().route("/registerBowlConfirmation").with(BowlController.class, "registerBowlConfirmation");
		router.POST().route("/registerBowlCompletion").with(BowlController.class, "registerBowlCompletion");
		// Register BowlMod
		router.GET().route("/registerBowlMod").with(BowlController.class, "registerBowlMod");
		router.POST().route("/registerBowlMod").with(BowlController.class, "registerBowlMod");
		router.POST().route("/registerBowlModConfirmation").with(BowlController.class, "registerBowlModConfirmation");
		router.POST().route("/registerBowlModCompletion").with(BowlController.class, "registerBowlModCompletion");
		// Register BowlModItem
		router.GET().route("/registerBowlModItem").with(BowlController.class, "registerBowlModItem");
		router.POST().route("/registerBowlModItem").with(BowlController.class, "registerBowlModItem");
		router.POST().route("/registerBowlModItemConfirmation").with(BowlController.class,
				"registerBowlModItemConfirmation");
		router.POST().route("/registerBowlModItemCompletion").with(BowlController.class,
				"registerBowlModItemCompletion");

		// List Bowls
		router.GET().route("/superbowl/bowl").with(BowlController.class, "listBowl");
		// Bowl Portfolio /Filtering and Sorting)
		router.GET().route("/superbowl/portfolioBowl").with(BowlController.class, "portfolioBowl");
		// Edit Bowl
		router.GET().route("/superbowl/editBowl").with(BowlController.class, "editBowl");
		router.GET().route("/superbowl/editBowlByStatusCode").with(BowlController.class, "editBowl");
		router.POST().route("/superbowl/editBowlConfirmation").with(BowlController.class, "editBowlConfirmation");
		router.POST().route("/superbowl/editBowlCompletion").with(BowlController.class, "editBowlCompletion");
		// Update Bowl
		router.GET().route("/superbowl/updateBowl").with(BowlController.class, "updateBowl");
		router.POST().route("/superbowl/updateBowlConfirmation").with(BowlController.class, "updateBowlConfirmation");
		router.POST().route("/superbowl/updateBowlCompletion").with(BowlController.class, "updateBowlCompletion");
		// Modify Bowl
		router.GET().route("/superbowl/modifyBowl").with(BowlController.class, "modifyBowl");
		// Register Bowl
		router.GET().route("/superbowl/registerBowl").with(BowlController.class, "registerBowl");
		router.POST().route("/superbowl/registerBowl").with(BowlController.class, "registerBowl");
		router.GET().route("/superbowl/registerBowlByGeoRegionCode").with(BowlController.class,
				"registerBowlByGeoRegionCode");
		router.GET().route("/superbowl/registerBowlByManufactureYear").with(BowlController.class,
				"registerBowlByManufactureYear");
		router.GET().route("/superbowl/registerBowlByStatusCode").with(BowlController.class,
				"registerBowlByStatusCode");
		router.GET().route("/superbowl/registerBowlByTimberCode").with(BowlController.class,
				"registerBowlByTimberCode");
		router.POST().route("/superbowl/registerBowlConfirmation").with(BowlController.class,
				"registerBowlConfirmation");
		router.POST().route("/superbowl/registerBowlCompletion").with(BowlController.class, "registerBowlCompletion");
		// Register BowlMod
		router.GET().route("/superbowl/registerBowlMod").with(BowlController.class, "registerBowlMod");
		router.POST().route("/superbowl/registerBowlMod").with(BowlController.class, "registerBowlMod");
		router.POST().route("/superbowl/registerBowlModConfirmation").with(BowlController.class,
				"registerBowlModConfirmation");
		router.POST().route("/superbowl/registerBowlModCompletion").with(BowlController.class,
				"registerBowlModCompletion");
		// Register BowlModItem
		router.GET().route("/superbowl/registerBowlModItem").with(BowlController.class, "registerBowlModItem");
		router.POST().route("/superbowl/registerBowlModItem").with(BowlController.class, "registerBowlModItem");
		router.POST().route("/superbowl/registerBowlModItemConfirmation").with(BowlController.class,
				"registerBowlModItemConfirmation");
		router.POST().route("/superbowl/registerBowlModItemCompletion").with(BowlController.class,
				"registerBowlModItemCompletion");

		///////////////////////////////////////////////////////////////////////
		// Process Customer
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/customer").with(CustomerController.class, "list");
		router.GET().route("/registerCustomer").with(CustomerController.class, "registerCustomer");
		router.POST().route("/registerCustomer").with(CustomerController.class, "registerCustomer");
		router.POST().route("/registerCustomerConfirmation").with(CustomerController.class,
				"registerCustomerConfirmation");
		router.POST().route("/registerCustomerCompletion").with(CustomerController.class, "registerCustomerCompletion");

		router.GET().route("/superbowl/customer").with(CustomerController.class, "list");
		router.GET().route("/superbowl/registerCustomer").with(CustomerController.class, "registerCustomer");
		router.POST().route("/superbowl/registerCustomer").with(CustomerController.class, "registerCustomer");
		router.POST().route("/superbowl/registerCustomerConfirmation").with(CustomerController.class,
				"registerCustomerConfirmation");
		router.POST().route("/superbowl/registerCustomerCompletion").with(CustomerController.class,
				"registerCustomerCompletion");

		///////////////////////////////////////////////////////////////////////
		// Process Exhibition
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/exhibition").with(ExhibitionController.class, "list");
		router.GET().route("/registerExhibition").with(ExhibitionController.class, "registerExhibition");
		router.POST().route("/registerExhibition").with(ExhibitionController.class, "registerExhibition");
		router.POST().route("/registerExhibitionConfirmation").with(ExhibitionController.class,
				"registerExhibitionConfirmation");
		router.POST().route("/registerExhibitionCompletion").with(ExhibitionController.class,
				"registerExhibitionCompletion");
		router.GET().route("/superbowl/exhibition").with(ExhibitionController.class, "list");
		router.GET().route("/superbowl/registerExhibition").with(ExhibitionController.class, "registerExhibition");
		router.POST().route("/superbowl/registerExhibition").with(ExhibitionController.class, "registerExhibition");
		router.POST().route("/superbowl/registerExhibitionConfirmation").with(ExhibitionController.class,
				"registerExhibitionConfirmation");
		router.POST().route("/superbowl/registerExhibitionCompletion").with(ExhibitionController.class,
				"registerExhibitionCompletion");

		///////////////////////////////////////////////////////////////////////
		// Process GeoRegion
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/geoRegion").with(GeoRegionController.class, "list");
		router.GET().route("/superbowl/geoRegion").with(GeoRegionController.class, "list");

		///////////////////////////////////////////////////////////////////////
		// Process Status
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/status").with(StatusController.class, "list");
		router.GET().route("/registerStatus").with(StatusController.class, "registerStatus");
		router.POST().route("/registerStatus").with(StatusController.class, "registerStatus");
		router.POST().route("/registerStatusConfirmation").with(StatusController.class, "registerStatusConfirmation");
		router.POST().route("/registerStatusCompletion").with(StatusController.class, "registerStatusCompletion");

		router.GET().route("/superbowl/status").with(StatusController.class, "list");
		router.GET().route("/superbowl/registerStatus").with(StatusController.class, "registerStatus");
		router.POST().route("/superbowl/registerStatus").with(StatusController.class, "registerStatus");
		router.POST().route("/superbowl/registerStatusConfirmation").with(StatusController.class,
				"registerStatusConfirmation");
		router.POST().route("/superbowl/registerStatusCompletion").with(StatusController.class,
				"registerStatusCompletion");

		///////////////////////////////////////////////////////////////////////
		// Process Timber
		///////////////////////////////////////////////////////////////////////

		// List Timbers
		router.GET().route("/timber").with(TimberController.class, "list");
		router.GET().route("/registerTimber").with(TimberController.class, "registerTimber");
		router.POST().route("/registerTimber").with(TimberController.class, "registerTimber");
		router.POST().route("/registerTimberConfirmation").with(TimberController.class, "registerTimberConfirmation");
		router.POST().route("/registerTimberCompletion").with(TimberController.class, "registerTimberCompletion");

		// List Timbers
		router.GET().route("/superbowl/timber").with(TimberController.class, "list");
		router.GET().route("/superbowl/registerTimber").with(TimberController.class, "registerTimber");
		router.POST().route("/superbowl/registerTimber").with(TimberController.class, "registerTimber");
		router.POST().route("/superbowl/registerTimberConfirmation").with(TimberController.class, "registerTimberConfirmation");
		router.POST().route("/superbowl/registerTimberCompletion").with(TimberController.class, "registerTimberCompletion");

		///////////////////////////////////////////////////////////////////////
		// Process TimberOrigin
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/timberOrigin").with(TimberOriginController.class, "list");
		router.GET().route("/registerTimberOrigin").with(TimberOriginController.class, "registerTimberOrigin");
		router.POST().route("/registerTimberOrigin").with(TimberOriginController.class, "registerTimberOrigin");
		router.POST().route("/registerTimberOriginConfirmation").with(TimberOriginController.class,
				"registerTimberOriginConfirmation");
		router.POST().route("/registerTimberOriginCompletion").with(TimberOriginController.class,
				"registerTimberOriginCompletion");

		router.GET().route("/superbowl/timberOrigin").with(TimberOriginController.class, "list");
		router.GET().route("/superbowl/registerTimberOrigin").with(TimberOriginController.class,
				"registerTimberOrigin");
		router.POST().route("/superbowl/registerTimberOrigin").with(TimberOriginController.class,
				"registerTimberOrigin");
		router.POST().route("/superbowl/registerTimberOriginConfirmation").with(TimberOriginController.class,
				"registerTimberOriginConfirmation");
		router.POST().route("/superbowl/registerTimberOriginCompletion").with(TimberOriginController.class,
				"registerTimberOriginCompletion");

		///////////////////////////////////////////////////////////////////////
		// WebJars
		// The WebJars project (http://www.webjars.org/) started by James Ward
		// is an excellent initiative that unites good old Java dependency
		// management with static web libraries like Bootstrap.
		// In order to activate support for WebJars you need to add the
		// following route(s) to your project:
		///////////////////////////////////////////////////////////////////////
		router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController.class, "serveWebJars");
		router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");
		///////////////////////////////////////////////////////////////////////
		// You can reference Bootstrap from your HTML pages via the following
		// URL:
		// <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"
		/////////////////////////////////////////////////////////////////////// rel="stylesheet">
		///////////////////////////////////////////////////////////////////////
		// ====================================================================
		// Actually WebJars do nothing magic. It simply uses a Java Servlet 3.x
		// convention that allows to reference and arbitrary static resources
		// of a libraries' META-INF/resources folder in your application.
		///////////////////////////////////////////////////////////////////////

		///////////////////////////////////////////////////////////////////////
		// Puts test subuser into Database
		///////////////////////////////////////////////////////////////////////
		if (!ninjaProperties.isProd()) {
			router.GET().route("/superbowl/setup").with(ApplicationController.class, "setup");
		}

	}

	/**
	 * Initialize if application runs in development mode.
	 *
	 * @param router
	 */
	private void initDevelopment(Router router) {

	}

	/**
	 * Initialize if application runs in production mode.
	 *
	 * @param router
	 */
	private void initProduction(Router router) {

	}

	/**
	 * Initialize if application runs in test mode.
	 *
	 * @param router
	 */
	private void initTest(Router router) {

	}

}
