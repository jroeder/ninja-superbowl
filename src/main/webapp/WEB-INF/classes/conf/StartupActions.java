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

import javax.inject.Singleton;

import org.slf4j.Logger;

import com.google.inject.Inject;

import dao.SetupDao;
import ninja.lifecycle.Start;
import ninja.utils.NinjaProperties;

/**
 * Erzeugt einen neuen <code>Subuser</code> wenn noch kein Benutzer in der Datenbank vorhanden ist.
 *
 * @author mbsusr01
 *
 */
@Singleton
public class StartupActions {

	/**
	 * This is the system wide logger
	 */
	@Inject
	public Logger logger;

    @Inject
    SetupDao setupDao;

    private NinjaProperties ninjaProperties;

    @Inject
    public StartupActions(NinjaProperties ninjaProperties) {
        this.ninjaProperties = ninjaProperties;
    }

    @Start(order=100)
    public void generateDummyDataWhenInTest() {

		logger.warn("StartupActions:generateDummyDataWhenInTest -> ninjaProperties.isDev() : " + ninjaProperties.isDev());
		logger.warn("StartupActions:generateDummyDataWhenInTest -> ninjaProperties.isProd(): " + ninjaProperties.isProd());
		logger.warn("StartupActions:generateDummyDataWhenInTest -> ninjaProperties.isTest(): " + ninjaProperties.isTest());
		
        if (ninjaProperties.isDev()) {
//            setupDao.setup();
        } else if (ninjaProperties.isProd()) {
//            setupDao.setup();
        } else if (ninjaProperties.isTest()) {
//            setupDao.setup();
        }

    }

}
