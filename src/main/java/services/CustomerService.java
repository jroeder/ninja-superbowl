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

import dto.CustomerDto;

/**
 * Insert class description here...
 *
 * @author mbsusr01
 */
public interface CustomerService {

	CustomerDto getCustomerById(Long id);

	CustomerDto getCustomerById(String id);

	CustomerDto getCustomerMaxIndex();

	List<CustomerDto> getCustomersByFamilyName(String familyName);

	List<CustomerDto> getCustomersByName(String givenName, String familyName);

	List<CustomerDto> listCustomers();

	void register(CustomerDto customerDto);

}
