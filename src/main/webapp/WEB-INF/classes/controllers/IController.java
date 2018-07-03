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

import dto.Dto;
import forms.Form;
import ninja.Context;
import ninja.Result;
import ninja.session.Session;

/**
 * Common {@code IController} interface for all persistent type controllers.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 14.04.2017 mbsusr01
 */
public interface IController {

	/**
	 * List all items of a specific type.
	 * 
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return the item list
	 */
	Result list(Context context);

	/**
	 * Provides a {@code Dto} (Data Transfer Object) instance with bowl data from session instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @return instance of Dto with data from session
	 */
	Dto getDtoFromSession(Session session);

	/**
	 * Put data from a user interface form into the session instance.
	 * <p>
	 * Checks every value from session instance for <code>null</code> value. If
	 * a value is <code>null</code> it will be replaced by a whitespace
	 * character.
	 *
	 * @param dto
	 *            the {@code Dto} (Data Transfer Object) instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putDtoIntoSession(Dto dto, Session session);

	/**
	 * Puts bowl data from a user interface form into the session instance.
	 * <p>
	 * Checks every bowl value from session instance for <code>null</code>
	 * value. If bowl value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param form
	 *            the {@code Form} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putFormIntoSession(Form form, Session session);

}
