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
 * Common abstract {@code IController} for all persistence type controllers.
 *
 * @author mbsusr01
 *
 * ninja-superbowl 19.04.2017 mbsusr01 
 */
public class AbstractEntityController extends AbstractController implements IController {

	/**
	 * Default value for numeric value zero -> value is '0'
	 */
	static final String ZERO = "0";

	/**
	 * Default value for an empty field -> value is ''
	 */
	static final String EMPTY = "";

	/**
	 * Default value for an unknown field -> value is '<Unbekannt>'
	 */
	static final String UNBEKANNT = "<Unbekannt>";

	/**
	 * Insert Constructor description here...
	 *
	 */
	public AbstractEntityController() {
		super();
	}

	/* (non-Javadoc)
	 * @see controllers.IController#list(ninja.Context)
	 */
	@Override
	public Result list(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controllers.IController#getDtoFromSession(ninja.session.Session)
	 */
	@Override
	public Dto getDtoFromSession(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see controllers.IController#putDtoIntoSession(dto.Dto, ninja.session.Session)
	 */
	@Override
	public void putDtoIntoSession(Dto dto, Session session) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see controllers.IController#putFormIntoSession(forms.Form, ninja.session.Session)
	 */
	@Override
	public void putFormIntoSession(Form form, Session session) {
		// TODO Auto-generated method stub

	}

}
