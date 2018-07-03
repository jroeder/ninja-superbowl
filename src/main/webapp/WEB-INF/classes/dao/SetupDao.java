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
package dao;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import entity.Subuser;

/**
 * Data Access Object to initially create a new <code>SUBUSER</code> to be able
 * to login into the application in case no <code>SUBUSER</code> exists already.
 *
 * @author mbsusr01
 */
@SuppressWarnings("unchecked")
public class SetupDao {


	/**
	 * These are the system wide entity manager
	 */
	@Inject
	private Provider<EntityManager> entityManagerProvider;

	/**
	 * Insert method description here...
	 */
	@Transactional
	public void setup() {

		EntityManager entityManager = entityManagerProvider.get();

		Query q = entityManager.createQuery("SELECT x FROM Subuser x");
		List<Subuser> subusers = (List<Subuser>) q.getResultList();

		if (subusers.size() == 0) {

			// (1a) create a java timestamp object that represents the current
			// time (i.e., a "current timestamp") --> Java 8
			Instant instant = Instant.now().minus(24, ChronoUnit.HOURS);
			Timestamp timestamp = Timestamp.from(instant);

			// (1b) create a java timestamp object that represents the current
			// time (i.e., a "current timestamp") --> Java 7
//			Calendar calendar = Calendar.getInstance();
//			java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());

			// (2) Create a new user
			Subuser user = new Subuser(0, "tpildner", "keule", "Thomas Pildner", "tp@thomas-pildner.de", timestamp, null, 1,
					false);

			// (3) Persist the new user
			entityManager.persist(user);
			entityManager.setFlushMode(FlushModeType.COMMIT);
			entityManager.flush();

		}

	}

}
