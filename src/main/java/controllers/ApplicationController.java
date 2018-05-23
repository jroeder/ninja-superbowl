/**
 * Copyright (C) 2013 the original author or authors.
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

import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import dao.SetupDao;
import dto.RoadmapDto;
import dto.SettingDto;
import dto.SoftwareDto;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.ReverseRouter;
import ninja.Router;
import ninja.cache.NinjaCache;
import ninja.i18n.Lang;
import ninja.i18n.Messages;
import ninja.session.Session;
import ninja.utils.NinjaProperties;
import services.ExhibitionService;
import services.PartnerService;
import services.RoadmapService;
import services.SettingService;
import services.SoftwareService;

/**
 * The central ApplicationController class of Superbowl application.
 *
 * @author mbsusr01
 */
@Singleton
public class ApplicationController {

	/**
	 * This is the system wide logger
	 */
	@Inject
	private Logger logger;

	/**
	 * Ninja system wide properties
	 */
	@Inject
	NinjaProperties ninjaProperties;

	/**
	 * Ninja system wide cache
	 */
	@Inject
	NinjaCache ninjaCache;

	/**
	 * Ninja lang instance
	 */
	@Inject
	Lang lang;

	/**
	 * Ninja messages instance
	 */
	@Inject
	Messages messages;

	/**
	 * Ninja reverse router instance
	 */
	@Inject
	ReverseRouter reverseRouter;

	/**
	 * Ninja router instance
	 */
	@Inject
	Router router;

	/**
	 * This is the system wide JPA entity manager
	 */
	@Inject
	Provider<EntityManager> entitiyManagerProvider;

	/**
	 * The data access object used to setup the application
	 */
	@Inject
	SetupDao setupDao;

	/**
	 * This is the superbowl exhibition service
	 */
	@Inject
	ExhibitionService exhibitionService;

	/**
	 * This is the superbowl partner service
	 */
	@Inject
	PartnerService partnerService;

	/**
	 * This is the superbowl roadmap service
	 */
	@Inject
	RoadmapService roadmapService;

	/**
	 * This is the superbowl software service
	 */
	@Inject
	SoftwareService softwareService;

	/**
	 * This is the superbowl setting service
	 */
	@Inject
	SettingService settingService;

	/**
	 * Inner class SimplePojo.
	 *
	 * @author mbsusr01
	 */
	public static class SimplePojo {
		public String content;
	}

	/**
	 * Returns the Result html page.
	 *
	 * @return the {@code Result} instance
	 */
	// public Result index() {
	// return Results.html();
	// }

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	// @Timed
	public Result index(Context context) {
		logger.info("ApplicationController.index(context) ");
		logger.info("ApplicationController -> ContextPath (Ninja Properties): " + ninjaProperties.getContextPath());
		logger.info("ApplicationController -> ContextPath (Context).........: " + context.getContextPath());

		// Default rendering is simple by convention
		// This renders the page in views/ApplicationController/index.ftl.html
		return Results.html();
		// return Results.html().render(presentationPage).render("previousPage",
		// page - 1).render("nextPage", page + 1);
		/*
		 * If we want to redirect the request to another endpoint, passing in
		 * everything as a fresh request. May we want to do some processing in
		 * the createPerson() method, and then redirect the request to the
		 * index() handler. The handler would then be enabled to return a
		 * response as if it were handling the request. For that use case we
		 * need what is called a reverse route: reverse because we go from the
		 * handler method to get the route. To do this, we would replace our
		 * return value with the code line below which would achieve a redirect
		 * to /. The router.getReverseRoute method takes the handler endpoint
		 * and discovers the route URL, and Result.redirect issues the redirect
		 * for us.
		 */
		// return
		// Results.redirect(router.getReverseRoute(ApplicationController.class,
		// "index"));
	}

	/**
	 * Returns the JSON Result page.
	 *
	 * @return the {@code Result} instance
	 */
	public Result helloWorldJson() {
		SimplePojo simplePojo = new SimplePojo();
		simplePojo.content = "Hello World! Hello Json!";

		return Results.json().render(simplePojo);
	}

	/**
	 * Method to put initial data in the db...
	 *
	 * @return the {@code Result} instance
	 */
	public Result setup() {
		setupDao.setup();
		return Results.ok();
	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result exhibitions(Context context) {
		return Results.html().template("views/ApplicationController/exhibitions.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result events(Context context) {
		return Results.html().template("views/ApplicationController/events.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result news(Context context) {
		return Results.html().template("views/ApplicationController/news.ftl.html");
	}

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result partners(Context context) {
		return Results.html().template("/views/ApplicationController/partners.ftl.html");
	}

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result profile(Context context) {
		return Results.html().template("/views/ApplicationController/profile.ftl.html").render("msg",
				"Thomas Pildner Profil");
	}

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result roadmap(Context context) {
		Session session = context.getSession();
		session.clear();
		List<RoadmapDto> roadmapList = roadmapService.listRoadmap();
		return Results.html().render("roadmaps", roadmapList);
	}

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result settings(Context context) {
		Session session = context.getSession();
		session.clear();
		List<SettingDto> settingList = settingService.listSetting();
		return Results.html().render("settings", settingList);
//		return Results.html().template("/views/ApplicationController/settings.ftl.html");
	}

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result software(Context context) {
		Session session = context.getSession();
		session.clear();
		List<SoftwareDto> softwareList = softwareService.listSoftware();
		return Results.html().render("softwares", softwareList);
	}

	/**
	 * Returns the Result html page.
	 *
	 * @param context
	 *            the {@code Context} instance
	 * @return the {@code Result} instance
	 */
	public Result specification(Context context) {
		return Results.html().template("/views/ApplicationController/specification.ftl.html");
	}

	/**
	 * Returns the Result html page.
	 *
	 * @return the {@code Result} instance
	 */
	public Result login() {
		return Results.html();
	}

	/**
	 * Returns the Result html page.
	 * <p>
	 * return Results.html().render(presentationPage).render("previousPage",
	 * page - 1).render("nextPage", page + 1);
	 *
	 * @return the {@code Result} instance
	 */
	public Result logout() {
		return Results.html();
	}

	/**
	 * Checks whether a user exists and performs the login into the application.
	 * <p>
	 * In case a user doesn't exist the result with an appropriate message to
	 * login again with a different user is provided.
	 *
	 * @param user
	 *            the superbowl user instance
	 * @param session
	 *            the ninja session instance
	 * @return the {@code Result} instance
	 */
	// public Result performLogin(Subuser user, Session session) {
	// String userId = user.getUserId();
	// if (userExists(userId, user.getPassword())) {
	// session.put("userId", userId);
	// return
	// Results.html().template("/views/ApplicationController/result.ftl.html").render("msg",
	// "Welcome " + user.getUserId());
	// } else {
	// session.clear();
	// }
	// return
	// Results.html().template("/views/ApplicationController/result.ftl.html").render("msg",
	// "Invalid user, please login again!");
	// }

	/**
	 * Checks whether a superbowl user with the provided identifier exists in
	 * table <code>SUBUSER</code>.
	 *
	 * @param userId
	 *            the user identifier
	 * @param password
	 *            the user identifier related password
	 * @return true -> user exists in database; false -> user retrieval failed
	 */
	// private boolean userExists(String userId, String password) {
	// EntityManager entityManager = entitiyManagerProvider.get();
	// List<Subuser> users = entityManager.createQuery("from SUBUSER a where
	// a.userid = ?1 and a.password = ?2")
	// .setParameter(1, userId).setParameter(2, password).getResultList();
	// return (users.size() > 0) ? true : false;
	// }

	/**
	 * Persists an <code>Subuser</code> into the database using the
	 * EntityManager.persist() method
	 * <p>
	 * * If we want to redirect the request to another endpoint, passing in
	 * everything as a fresh request. May we want to do some processing in the
	 * createPerson() method, and then redirect the request to the index()
	 * handler. The handler would then be enabled to return a response as if it
	 * were handling the request. For that use case we need what is called a
	 * reverse route: reverse because we go from the handler method to get the
	 * route. To do this, we would replace our return value with the code line
	 * below which would achieve a redirect to /. The router.getReverseRoute
	 * method takes the handler endpoint and discovers the route URL, and
	 * Result.redirect issues the redirect for us.
	 * <p>
	 * return
	 * Results.redirect(router.getReverseRoute(ApplicationController.class,
	 * "index"));
	 *
	 * @return the {@code Result} instance
	 */
	// @Transactional
	// public Result createSubuser(Subuser subuser) {
	// EntityManager entityManager = entitiyManagerProvider.get();
	// entityManager.persist(subuser);
	// // Send an HTTP 200 OK response back with an empty JSON body
	// return Results.json().render("{}");
	// }

}
