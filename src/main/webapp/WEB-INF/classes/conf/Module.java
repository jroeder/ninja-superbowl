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
package conf;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import controllers.AbstractBowlController;
import controllers.BowlController;
import services.BotanicSystemService;
import services.BotanicSystemServiceImpl;
import services.BowlService;
import services.BowlServiceImpl;
import services.CustomerService;
import services.CustomerServiceImpl;
import services.ExhibitionService;
import services.ExhibitionServiceImpl;
import services.GeoRegionService;
import services.GeoRegionServiceImpl;
import services.ManufactureService;
import services.ManufactureServiceImpl;
import services.PartnerService;
import services.PartnerServiceImpl;
import services.RoadmapService;
import services.RoadmapServiceImpl;
import services.SoftwareService;
import services.SoftwareServiceImpl;
import services.StatusService;
import services.StatusServiceImpl;
import services.TimberOriginService;
import services.TimberOriginServiceImpl;
import services.TimberService;
import services.TimberServiceImpl;

/**
 * Implementation class to bind service implementation classes to their
 * interfaces.
 *
 * @author mbsusr01 bind(TimberService.class).to(TimberServiceImpl.class);
 */
@Singleton
public class Module extends AbstractModule {

	/**
	 * Bind interfaces to implementation classes
	 */
	protected void configure() {
		bind(StartupActions.class);
		bind(AbstractBowlController.class).to(BowlController.class);
		bind(BotanicSystemService.class).to(BotanicSystemServiceImpl.class);
		bind(BowlService.class).to(BowlServiceImpl.class);
		bind(CustomerService.class).to(CustomerServiceImpl.class);
		bind(ExhibitionService.class).to(ExhibitionServiceImpl.class);
		bind(GeoRegionService.class).to(GeoRegionServiceImpl.class);
		bind(ManufactureService.class).to(ManufactureServiceImpl.class);
		bind(PartnerService.class).to(PartnerServiceImpl.class);
		bind(RoadmapService.class).to(RoadmapServiceImpl.class);
		bind(SoftwareService.class).to(SoftwareServiceImpl.class);
		bind(StatusService.class).to(StatusServiceImpl.class);
		bind(TimberService.class).to(TimberServiceImpl.class);
		bind(TimberOriginService.class).to(TimberOriginServiceImpl.class);
	}

}
