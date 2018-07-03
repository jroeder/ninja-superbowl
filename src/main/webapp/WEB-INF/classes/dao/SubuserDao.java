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
package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entity.Subuser;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code SUBUSER} of the underlying database system.
 * <p>
 * The Data Access Object (DAO) pattern is now a widely accepted mechanism to
 * abstract away the details of persistence in an application.<br>
 * The idea is that instead of having the domain logic communicate directly with
 * the database, file system, web service, or whatever persistence mechanism
 * your application uses, the domain logic speaks to a DAO layer instead. This
 * DAO layer then communicates with the underlying persistence system or
 * service.
 *
 * @author mbsusr01
 */
public class SubuserDao extends AbstractDao {

	/**
	 * Insert method description here...
	 * 
	 * @param userId
	 *            the user id
	 * @param password
	 *            the password
	 * @return valid password indicator (true=valid)
	 */
	@UnitOfWork
	public boolean isUserAndPasswordValid(String userId, String password) {

		boolean result = false;

		if (userId != null && password != null) {

			EntityManager entityManager = entityManagerProvider.get();

			TypedQuery<Subuser> q = entityManager.createQuery("select x from Subuser x where x.userId = :userIdParam",
					Subuser.class);
			Subuser user = getSingleResult(q.setParameter("userIdParam", userId));

			if (user != null) {
				if (user.getPassword().equals(password)) {
					result = true;
				}
			}

		}

		return result;

	}

	/**
	 * Get single result without throwing NoResultException, you can of course
	 * just catch the exception and return null, it's up to you.
	 * 
	 * @param query
	 *            the query
	 * @return generic result list
	 */
	private static <T> T getSingleResult(TypedQuery<T> query) {
		query.setMaxResults(1);
		List<T> list = query.getResultList();
		return (list == null || list.isEmpty()) ? null : list.get(0);
	}

	/**
	 * Insert method description here...
	 *
	 * @param bowl
	 *            instance of type {@code Subuser}
	 */
	private final void logSubuser(Subuser subuser) {
		logger.info("~~~~~~~~~~~~~~~ Subuser ~~~~~~~~~~~~~~~");
		logger.info("id........: " + subuser.getId());
		logger.info("version...: " + subuser.getVersion());
		logger.info("userid....: " + subuser.getUserId());
		logger.info("password..: " + subuser.getPassword());
		logger.info("username..: " + subuser.getUserName());
		logger.info("email.....: " + subuser.getEmail());
		logger.info("lastLogin.: " + subuser.getLastLogout());
		logger.info("loginCount: " + subuser.getLoginCount());
		logger.info("locked....: " + subuser.isLocked());
	}

}
