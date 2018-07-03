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

import entity.GeoRegion;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public class GeoRegionListDto {

	/**
	 * the list of {@code GeoRegion}s
	 */
	private List<GeoRegion> geoRegionList;

	/**
	 * Insert Constructor description here...
	 */
	public GeoRegionListDto() {
		super();
	}

	/**
	 * @return the list of {@code GeoRegion} instances
	 */
	public List<GeoRegion> getGeoRegionList() {
		return geoRegionList;
	}

	/**
	 * @param geoRegionList
	 *            the {@code GeoRegion}s to set
	 */
	public void setGeoRegions(List<GeoRegion> geoRegionList) {
		this.geoRegionList = geoRegionList;
	}

}
