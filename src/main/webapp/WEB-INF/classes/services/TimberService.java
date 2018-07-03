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

import java.util.List;

import dto.TimberDto;
import entity.GeoRegion;

/**
 * Interface for all service methods to retrieve {@code Timber} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public interface TimberService {

	TimberDto getTimberById(Long id);

	TimberDto getTimberById(String id);

	TimberDto getTimberByCode(String code);

	TimberDto getTimberMaxIndex();

	List<TimberDto> listTimber();

	List<TimberDto> listTimberByGeoRegion(GeoRegion geoRegion);

	List<TimberDto> listTimberByGeoRegionId(Long id);

	List<TimberDto> listTimberByGeoRegionCode(String code);

	void register(TimberDto timberDto);

}
