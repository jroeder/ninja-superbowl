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
package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.google.inject.persist.Transactional;

import dto.CustomerDto;
import dto.CustomerListDto;
import entity.Customer;
import ninja.jpa.UnitOfWork;

/**
 * Data Access Object to perform all data manipulation steps against the table
 * {@code CUSTOMER} of the underlying database system.
 * <p>
 * The Data Access Object (DAO) pattern is now a widely accepted mechanism to
 * abstract away the details of persistence in an application.<br>
 * The idea is that instead of having the domain logic communicate directly with
 * the database, file system, web service, or whatever persistence mechanism
 * your application uses, the domain logic speaks to a DAO layer instead. This
 * DAO layer then communicates with the underlying persistence system or
 * service.
 *
 * @author mbsusr01
 */
public class CustomerDao extends AbstractDao {

	/**
	 * Insert method description here...
	 *
	 * @return the {@code CustomerListDto} instance
	 */
	@UnitOfWork
	public CustomerListDto getAllCustomer() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c order by c.id",
				Customer.class);
		List<Customer> customerList = query.getResultList();

		CustomerListDto customersDto = new CustomerListDto();
		customersDto.setCustomerList(customerList);

		return customersDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code CustomerListDto} instances
	 */
	@UnitOfWork
	public List<CustomerDto> getAllCustomers() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> query = entityManager.createQuery("select x from Customer x", Customer.class);
		List<Customer> customerList = query.getResultList();
		logger.info(
				"CustomerDao.getAllBotanicSystems() -> no of botanic system item(s) selected: " + customerList.size());

		List<CustomerDto> customerDtos = new ArrayList<>();

		if (customerList.isEmpty()) {
			logger.info("No customer found in data store!");
		} else {
			logger.info("{} customer(s) found in data store!", customerList.size());
			CustomerDto customerDto = null;
			for (Customer customer : customerList) {
				logCustomer(customer);
				customerDto = new CustomerDto(customer.getId(), customer.getVersion(), customer.getIndex(),
						customer.getSalutation(), customer.getGraduation(), customer.getGivenName(),
						customer.getFamilyName(), customer.getPhone(), customer.getFax(), customer.getMobile(),
						customer.getEmail(), customer.getStreet(), customer.getHouseNumber(), customer.getZipCode(),
						customer.getCity(), customer.getCountryCode(), customer.getCountry(), customer.getComment());
				customerDtos.add(customerDto);
			}
		}

		return customerDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the unique technical identifier
	 * @return the {@code CustomerDto} instance
	 */
	@UnitOfWork
	public CustomerDto getCustomerById(@NotNull Long id) {

		CustomerDto customerDto = new CustomerDto();

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> typedQuery = entityManager.createQuery("select c from Customer c where c.id = :id",
				Customer.class);

		Customer customer = (Customer) typedQuery.setParameter("id", id).getSingleResult();

		customerDto = new CustomerDto(customer.getId(), customer.getVersion(), customer.getIndex(),
				customer.getSalutation(), customer.getGraduation(), customer.getGivenName(), customer.getFamilyName(),
				customer.getPhone(), customer.getFax(), customer.getMobile(), customer.getEmail(), customer.getStreet(),
				customer.getHouseNumber(), customer.getZipCode(), customer.getCity(), customer.getCountryCode(),
				customer.getCountry(), customer.getComment());

		return customerDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @param id
	 *            the unique technical identifier
	 * @return the {@code CustomerDto} instance
	 */
	@UnitOfWork
	public CustomerDto getCustomerById(@NotNull String id) {
		return this.getCustomerById(new Long(id));
	}

	/**
	 * Insert method description here...
	 *
	 * @param givenName
	 *            the given name
	 * @param familyName
	 *            the family name
	 * @return the list of {@code CustomerDto} instances
	 */
	@UnitOfWork
	public List<CustomerDto> getCustomersByName(@NotNull String givenName, @NotNull String familyName) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> typedQuery = entityManager
				.createQuery("select c from Customer c where c.givenName = :givenName and"
						+ "c.familyName = :familyName order by c.id", Customer.class);
		typedQuery.setParameter("givenName", givenName);
		typedQuery.setParameter("familyName", familyName);
		List<Customer> typedQueryResultList = typedQuery.getResultList();

		List<CustomerDto> customerDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No customer with name {0} {1} found in data store!", givenName, familyName);
		} else {
			logger.info("{} customer(s) found by name in data store!", typedQueryResultList.size());
			for (Customer customer : typedQueryResultList) {
				CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getVersion(), customer.getIndex(),
						customer.getSalutation(), customer.getGraduation(), customer.getGivenName(),
						customer.getFamilyName(), customer.getPhone(), customer.getFax(), customer.getMobile(),
						customer.getEmail(), customer.getStreet(), customer.getHouseNumber(), customer.getZipCode(),
						customer.getCity(), customer.getCountryCode(), customer.getCountry(), customer.getComment());
				customerDtos.add(customerDto);
			}
		}

		return customerDtos;

	}

	/**
	 * Insert method description here...
	 *
	 * @param familyName
	 *            the family name
	 * @return the list of {@code CustomerDto} instances
	 */
	@UnitOfWork
	public List<CustomerDto> getCustomersByFamilyName(@NotNull String familyName) {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> typedQuery = entityManager.createQuery(
				"select c from Customer c where c.customer_familyName = :familyName order by c.id", Customer.class);
		typedQuery.setParameter("familyName", familyName);
		List<Customer> typedQueryResultList = typedQuery.getResultList();

		List<CustomerDto> customerDtos = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.warn("No customer with family name {} found in data store!", familyName);
		} else {
			logger.info("{} customer(s) found by family name in data store!", typedQueryResultList.size());
			for (Customer customer : typedQueryResultList) {
				CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getVersion(), customer.getIndex(),
						customer.getSalutation(), customer.getGraduation(), customer.getGivenName(),
						customer.getFamilyName(), customer.getPhone(), customer.getFax(), customer.getMobile(),
						customer.getEmail(), customer.getStreet(), customer.getHouseNumber(), customer.getZipCode(),
						customer.getCity(), customer.getCountryCode(), customer.getCountry(), customer.getComment());
				customerDtos.add(customerDto);
			}
		}
		return customerDtos;

	}

	/**
	 * Delivers the max index number of a persisted {@code Customer}.
	 * <p>
	 * SQL Statement:
	 * <p>
	 * SELECT * FROM customer c WHERE c.index = SELECT max(c.index) FROM
	 * customer x;
	 *
	 * @return a persisted {@code Customer} as a data transfer object
	 */
	@UnitOfWork
	public CustomerDto getCustomerMaxIndex() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> typedQuery = entityManager.createQuery(
				"select c from Customer c where c.index = (select max(x.index) from Customer x)", Customer.class);
		Customer customer = (Customer) typedQuery.getSingleResult();

		CustomerDto customerDto = new CustomerDto();
		customerDto.receive(customer);

		return customerDto;

	}

	/**
	 * Insert method description here...
	 *
	 * @return the list of {@code CustomerDto} instances
	 */
	@UnitOfWork
	public List<CustomerDto> listCustomers() {

		EntityManager entityManager = entityManagerProvider.get();

		TypedQuery<Customer> typedQuery = entityManager.createQuery("select c from Customer c order by c.id",
				Customer.class);
		List<Customer> typedQueryResultList = typedQuery.getResultList();

		List<CustomerDto> customerDtoList = new ArrayList<>();

		if (typedQueryResultList.isEmpty()) {
			logger.info("No Customer instance found in data store!");
		} else {
			logger.info("{} Customer instance(s) found in data store!", typedQueryResultList.size());
			CustomerDto customerDto = null;
			for (Customer customer : typedQueryResultList) {
				logCustomer(customer);
				customerDto = new CustomerDto(customer.getId(), customer.getVersion(), customer.getIndex(),
						customer.getSalutation(), customer.getGraduation(), customer.getGivenName(),
						customer.getFamilyName(), customer.getPhone(), customer.getFax(), customer.getMobile(),
						customer.getEmail(), customer.getStreet(), customer.getHouseNumber(), customer.getZipCode(),
						customer.getCity(), customer.getCountryCode(), customer.getCountry(), customer.getComment());
				customerDtoList.add(customerDto);
			}
		}
		return customerDtoList;

	}

	/**
	 * Persists a new {@code Customer} in the data store.
	 *
	 * @param customerDto
	 *            the {@code CustomerDao} instance
	 */
	@Transactional
	public void register(CustomerDto customerDto) {

		EntityManager entityManager = entityManagerProvider.get();

		Customer customer = new Customer(customerDto);
		entityManager.persist(customer);

		entityManager.flush();

		logger.info("New registered customer id: {}", customer.getId());

	}

	/**
	 * Insert method description here...
	 *
	 * @param customer
	 *            the {@code Customer} instance
	 */
	private final void logCustomer(Customer customer) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.dao.customer"));
		if (isLog) {
			logger.info("~~~~~~~~~~~~~~~ Customer ~~~~~~~~~~~~~~~");
			logger.info("id         : " + customer.getId());
			logger.info("version    : " + customer.getVersion());
			logger.info("salutation : " + customer.getSalutation());
			logger.info("graduation : " + customer.getGraduation());
			logger.info("givenName  : " + customer.getGivenName());
			logger.info("familyName : " + customer.getFamilyName());
			logger.info("phone      : " + customer.getPhone());
			logger.info("fax        : " + customer.getFax());
			logger.info("mobile     : " + customer.getMobile());
			logger.info("email      : " + customer.getEmail());
			logger.info("street     : " + customer.getStreet());
			logger.info("houseNumber: " + customer.getHouseNumber());
			logger.info("city       : " + customer.getCity());
			logger.info("zipCode    : " + customer.getZipCode());
			logger.info("countryCode: " + customer.getCountryCode());
			logger.info("country    : " + customer.getCountry());
			logger.info("comment    : " + customer.getComment());
		}
	}

}
