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
package dto;

import java.util.List;

import entity.Timber;

/**
 * Data Transfer Object for list of Entity <code>Timber</code>.
 *
 * @author mbsusr01
 *
 */
public class TimberListDto {

	private List<Timber> timberList;

	/**
	 * Insert Constructor description here...
	 */
	public TimberListDto() {
		super();
	}

	/**
	 * @return the timberList
	 */
	public List<Timber> getTimberList() {
		return timberList;
	}

	/**
	 * @param timberList
	 *            the timberList to set
	 */
	public void setTimberList(List<Timber> timberList) {
		this.timberList = timberList;
	}

}
