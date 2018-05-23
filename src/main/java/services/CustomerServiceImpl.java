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

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import dao.CustomerDao;
import dto.CustomerDto;
import ninja.cache.NinjaCache;

/**
 * Implementation of {@code CustomerService} interface.
 * <p>
 * Uses data access objects (DAO) to retrieve {@code Customer} data from
 * underlying data store.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 05.04.2017 mbsusr01
 */
public class CustomerServiceImpl implements CustomerService {

	/**
	 * The {@code NinjaCache} instance
	 */
	@Inject
	private NinjaCache ninjaCache;

	/**
	 * The {@code CustomerDao} instance
	 */
	@Inject
	private CustomerDao customerDao;

	/**
	 * Constructor.
	 */
	public CustomerServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.CustomerService#getCustomerById(java.lang.Long)
	 */
	@Override
	public CustomerDto getCustomerById(@NotNull Long id) {
		return customerDao.getCustomerById(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.CustomerService#getCustomerById(java.lang.String)
	 */
	@Override
	public CustomerDto getCustomerById(@NotNull String id) {
		return customerDao.getCustomerById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.CustomerService#getCustomerMaxIndex()
	 */
	@Override
	public CustomerDto getCustomerMaxIndex() {
		return customerDao.getCustomerMaxIndex();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.CustomerService#getCustomersByName(String givenName, String
	 * familyName)
	 */
	@Override
	public List<CustomerDto> getCustomersByName(@NotNull String givenName, @NotNull String familyName) {
		return customerDao.getCustomersByName(givenName, familyName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.CustomerService#getCustomersByFamilyName(String familyName)
	 */
	@Override
	public List<CustomerDto> getCustomersByFamilyName(@NotNull String familyName) {
		return customerDao.getCustomersByFamilyName(familyName);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.CustomerService#list()
	 */
	@Override
	public List<CustomerDto> listCustomers() {
		return customerDao.listCustomers();
		// List<CustomerDto> customers = ninjaCache.get("customers",
		// List.class);
		// if (customers == null) {
		// customers = customerDao.listCustomers();
		// ninjaCache.set("customers", customers, "30mn");
		// }
		// return customers;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see services.CustomerService#register(dto.CustomerDto)
	 */
	@Override
	public void register(@NotNull CustomerDto customerDto) {
		customerDao.register(customerDto);
	}

}
