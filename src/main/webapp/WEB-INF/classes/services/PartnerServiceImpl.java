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

import dto.PartnerDto;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 *
 * ninja-superbowl 20.04.2017 mbsusr01 
 */
public class PartnerServiceImpl extends AbstractService implements PartnerService {
	
	/**
	 * The {@code PartnerDao} instance
	 */
//	@Inject
//	private PartnerDao partnerDao;

	/**
	 * Constructor.
	 */
	public PartnerServiceImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see services.PartnerService#listPartner()
	 */
	@Override
	public List<PartnerDto> listPartner() {
		// TODO Auto-generated method stub
		return null;
	}

}
