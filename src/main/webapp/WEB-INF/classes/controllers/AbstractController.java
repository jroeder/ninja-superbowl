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

import org.slf4j.Logger;

import com.google.inject.Inject;

import ninja.utils.NinjaProperties;
import types.SuperbowlHelper;

/**
 * Common abstract {@code IController} for all persistence type controllers.
 *
 * @author mbsusr01
 *
 * ninja-superbowl 13.04.2017 mbsusr01 
 */
public abstract class AbstractController {

	/**
	 * This is the system wide logger
	 */
	@Inject
	Logger logger;

	/**
	 * These are the system wide ninja properties
	 */
	@Inject
	NinjaProperties ninjaProperties;

	/**
	 * The Superbowl helper
	 */
	@Inject
	SuperbowlHelper helper;

	/**
	 * Insert Constructor description here...
	 */
	public AbstractController() {
		super();
	}

}
