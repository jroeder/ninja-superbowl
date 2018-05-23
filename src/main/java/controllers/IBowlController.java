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

import dto.BowlDto;
import dto.BowlModDto;
import dto.BowlModItemDto;
import dto.BowlModStepDto;
import forms.BowlForm;
import forms.BowlModForm;
import forms.BowlModItemForm;
import ninja.Context;
import ninja.Result;
import ninja.session.Session;

/**
 * Common {@code IController} interface for all bowl controllers.
 *
 * @author mbsusr01
 *
 * ninja-superbowl 19.04.2017 mbsusr01 
 */
public interface IBowlController {

	/**
	 * List all {@code Bowl}s.
	 * 
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return the {@code Bowl} list
	 */
	Result listBowl(Context context);

	/**
	 * List all {@code Bowl} modifications of a specific type.
	 * 
	 * @param bowlId
	 *            the technical identifier of a {@code Bowl} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return the {@code Bowl} modification list
	 */
	Result listBowlMod(Long bowlId, Context context);

	/**
	 * List all {@code Bowl} modification items of a specific type.
	 * 
	 * @param bowlModId
	 *            the technical identifier of a {@code BowlMod} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return the {@code Bowl} modification item list
	 */
	Result listBowlModItem(Long bowlModId, Context context);

	/**
	 * List all {@code Bowl} modification steps of a specific type.
	 * 
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return the {@code Bowl} modification step list
	 */
	Result listBowlModStep(Context context);

	/**
	 * Provides a {@code BowlDto} (Data Transfer Object) instance with bowl data from session instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @return instance of {@code BowlDto} with data from session
	 */
	BowlDto getBowlDtoFromSession(Session session);

	/**
	 * Provides a {@code BowlModDto} (Data Transfer Object) instance with bowl data from session instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @return instance of {@code BowlModDto} with data from session
	 */
	BowlModDto getBowlModDtoFromSession(Session session);

	/**
	 * Provides a {@code BowlModItemDto} (Data Transfer Object) instance with bowl data from session instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @return instance of {@code BowlModItemDto} with data from session
	 */
	BowlModItemDto getBowlModItemDtoFromSession(Session session);

	/**
	 * Provides a {@code BowlModStepDto} (Data Transfer Object) instance with bowl data from session instance.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @return instance of {@code BowlModStepDto} with data from session
	 */
	BowlModStepDto getBowlModStepDtoFromSession(Session session);

	/**
	 * Put data from a user interface form into the session instance.
	 * <p>
	 * Checks every value from session instance for <code>null</code> value. If
	 * a value is <code>null</code> it will be replaced by a whitespace
	 * character.
	 *
	 * @param bowlDo
	 *            the {@code BowlDto} (Data Transfer Object) instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlDtoIntoSession(BowlDto bowlDo, Session session);

	/**
	 * Put data from a user interface form into the session instance.
	 * <p>
	 * Checks every value from session instance for <code>null</code> value. If
	 * a value is <code>null</code> it will be replaced by a whitespace
	 * character.
	 *
	 * @param bowlModDto
	 *            the {@code BowlModDto} (Data Transfer Object) instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlModDtoIntoSession(BowlModDto bowlModDto, Session session);

	/**
	 * Put data from a user interface form into the session instance.
	 * <p>
	 * Checks every value from session instance for <code>null</code> value. If
	 * a value is <code>null</code> it will be replaced by a whitespace
	 * character.
	 *
	 * @param bowlModItemDto
	 *            the {@code BowlModItemDto} (Data Transfer Object) instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlModItemDtoIntoSession(BowlModItemDto bowlModItemDto, Session session);

	/**
	 * Put data from a user interface form into the session instance.
	 * <p>
	 * Checks every value from session instance for <code>null</code> value. If
	 * a value is <code>null</code> it will be replaced by a whitespace
	 * character.
	 *
	 * @param bowlModStepDto
	 *            the {@code BowlModStepDto} (Data Transfer Object) instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlModStepDtoIntoSession(BowlModStepDto bowlModStepDto, Session session);

	/**
	 * Puts bowl data from a user interface form into the session instance.
	 * <p>
	 * Checks every bowl value from session instance for <code>null</code>
	 * value. If bowl value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param bowlForm
	 *            the {@code BowlForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlFormIntoSession(BowlForm bowlForm, Session session);

	/**
	 * Puts bowl data from a user interface form into the session instance.
	 * <p>
	 * Checks every bowl value from session instance for <code>null</code>
	 * value. If bowl value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param bowlModForm
	 *            the {@code BowlModForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlModFormIntoSession(BowlModForm bowlModForm, Session session);

	/**
	 * Puts bowl data from a user interface form into the session instance.
	 * <p>
	 * Checks every bowl value from session instance for <code>null</code>
	 * value. If bowl value is <code>null</code> it will be replaced by a
	 * whitespace character.
	 *
	 * @param bowlModItemForm
	 *            the {@code BowlModItemForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	void putBowlModItemFormIntoSession(BowlModItemForm bowlModItemForm, Session session);

}
