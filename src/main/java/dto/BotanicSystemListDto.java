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

import entity.BotanicSystem;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class BotanicSystemListDto {

	/**
	 * the list of {@code BotanicSystem}s
	 */
	private List<BotanicSystem> botanicSystemList;

	/**
	 * Insert Constructor description here...
	 */
	public BotanicSystemListDto() {
		super();
	}

	/**
	 * @return the {@code BotanicSystem} instance
	 */
	public List<BotanicSystem> getBotanicSystemList() {
		return botanicSystemList;
	}

	/**
	 * @param botanicSystemList
	 *            the list of {@code BotanicSystem}s to set
	 */
	public void setBotanicSystems(List<BotanicSystem> botanicSystemList) {
		this.botanicSystemList = botanicSystemList;
	}

}
