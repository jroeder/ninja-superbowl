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
package services;

import org.slf4j.Logger;

import com.google.inject.Inject;

import ninja.cache.NinjaCache;
import ninja.utils.NinjaProperties;

/**
 * Abstract service class to provide all service implementation classes with a
 * default set of injected services.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
public class AbstractService {

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
	 * The {@code NinjaCache} instance
	 */
	@Inject
	NinjaCache ninjaCache;

	/**
	 * Constructor.
	 */
	public AbstractService() {
		super();
	}

}
