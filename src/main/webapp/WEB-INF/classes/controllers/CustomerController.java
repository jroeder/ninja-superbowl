/**
 * Copyright (C) 2012-2017 the original author or authors.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.google.inject.Singleton;

import dto.CustomerDto;
import dto.Dto;
import forms.CustomerForm;
import forms.Form;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import services.CustomerService;
import types.Checker;
import types.SuperbowlHelper;
import types.Whitespace;

/**
 * Controller instance to handle user requests regarding a {@code Customer}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
@Singleton
public class CustomerController extends AbstractEntityController {

	/**
	 * This is the superbowl {@code Customer} service
	 */
	@Inject
	private CustomerService customerService;

	/**
	 * Retrieves all customers from data store.
	 *
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result list(Context context) {
		Session session = context.getSession();
		session.clear();
		List<CustomerDto> customerList = customerService.listCustomers();
		return Results.html().render("customers", customerList);
	}

	/**
	 * Register a new {@code Customer}.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code Customer} table has entries or
	 *            not
	 * @return {@code Result} instance
	 */
	public Result registerCustomer(Session session, Context context, @Param("emptyTable") Boolean emptyTable) {
		logger.info("CustomerController.registerCustomer -> Session id....: " + session.getId());
		logger.info("CustomerController.registerCustomer -> Empty Table...: " + emptyTable.toString());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cEmptyTable", emptyTable.toString());

		CustomerDto sessionCustomerDto = null;
		CustomerDto customerDto = null;
		Integer customerIndex = null;

		if (emptyTable) {
			customerIndex = 0;
		} else {
			// Customer max index ermitteln
			customerDto = customerService.getCustomerMaxIndex();
			customerIndex = customerDto.getIndex() + 1;
		}
		
		map.put("customerIndex", customerIndex);

		sessionCustomerDto = (CustomerDto) getDtoFromSession(session);

		logExtraData(emptyTable, customerIndex);

		if (sessionCustomerDto != null) {
			logger.info(sessionCustomerDto.asString());
			if (sessionCustomerDto.getIndex() != null) {
				map.put("cIndex", sessionCustomerDto.getIndex());
			} else {
				map.put("cIndex", EMPTY);
			}
			if (sessionCustomerDto.getSalutation() != null) {
				map.put("cSalutation", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getSalutation()));
			} else {
				map.put("cSalutation", EMPTY);
			}
			if (sessionCustomerDto.getGraduation() != null) {
				map.put("cGraduation", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getGraduation()));
			} else {
				map.put("cGraduation", EMPTY);
			}
			if (sessionCustomerDto.getGivenName() != null) {
				map.put("cGivenName", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getGivenName()));
			} else {
				map.put("cGivenName", EMPTY);
			}
			if (sessionCustomerDto.getFamilyName() != null) {
				map.put("cFamilyName", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getFamilyName()));
			} else {
				map.put("cFamilyName", EMPTY);
			}
			if (sessionCustomerDto.getPhone() != null) {
				map.put("cPhone", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getPhone()));
			} else {
				map.put("cPhone", EMPTY);
			}
			if (sessionCustomerDto.getFax() != null) {
				map.put("cFax", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getFax()));
			} else {
				map.put("cFax", EMPTY);
			}
			if (sessionCustomerDto.getMobile() != null) {
				map.put("cMobile", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getMobile()));
			} else {
				map.put("cMobile", EMPTY);
			}
			if (sessionCustomerDto.getEmailUser() != null) {
				map.put("cEmailUser", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getEmailUser()));
			} else {
				map.put("cEmailUser", EMPTY);
			}
			if (sessionCustomerDto.getEmailDomain() != null) {
				map.put("cEmailDomain", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getEmailDomain()));
			} else {
				map.put("cEmailDomain", EMPTY);
			}
			if (sessionCustomerDto.getStreet() != null) {
				map.put("cStreet", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getStreet()));
			} else {
				map.put("cStreet", EMPTY);
			}
			if (sessionCustomerDto.getHouseNumber() != null) {
				map.put("cHouseNumber", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getHouseNumber()));
			} else {
				map.put("cHouseNumber", EMPTY);
			}
			if (sessionCustomerDto.getZipCode() != null) {
				map.put("cZipCode", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getZipCode()));
			} else {
				map.put("cZipCode", EMPTY);
			}
			if (sessionCustomerDto.getCity() != null) {
				map.put("cCity", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getCity()));
			} else {
				map.put("cCity", EMPTY);
			}
			if (sessionCustomerDto.getCountryCode() != null) {
				map.put("cCountryCode", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getCountryCode()));
			} else {
				map.put("cCountryCode", EMPTY);
			}
			if (sessionCustomerDto.getCountry() != null) {
				map.put("cCountry", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getCountry()));
			} else {
				map.put("cCountry", EMPTY);
			}
			if (sessionCustomerDto.getComment() != null) {
				map.put("cComment", SuperbowlHelper.replaceSpaceByMarker(sessionCustomerDto.getComment()));
			} else {
				map.put("cComment", EMPTY);
			}
		} else {
			map.put("cIndex", customerIndex.toString());
			map.put("cSalutation", EMPTY);
			map.put("cGraduation", EMPTY);
			map.put("cGivenName", EMPTY);
			map.put("cFamilyName", EMPTY);
			map.put("cPhone", EMPTY);
			map.put("cFax", EMPTY);
			map.put("cMobile", EMPTY);
			map.put("cEmailUser", EMPTY);
			map.put("cEmailDomain", EMPTY);
			map.put("cStreet", EMPTY);
			map.put("cHouseNumber", EMPTY);
			map.put("cZipCode", EMPTY);
			map.put("cCity", EMPTY);
			map.put("cCountryCode", EMPTY);
			map.put("cCountry", EMPTY);
			map.put("cComment", EMPTY);			
		}

		return Results.html().render(map).template("views/CustomerController/registerCustomer.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param customerForm
	 *            the {@code Ninja CustomerForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerCustomerConfirmation(@JSR303Validation CustomerForm customerForm, Session session,
			Validation validation, Context context) {
		logger.info("CustomerController.registerCustomerConfirmation -> Session    id: " + session.getId());
		logger.info("CustomerController.registerCustomerConfirmation -> Session empty: " + session.isEmpty());
		logger.info("CustomerController.registerCustomerConfirmation -> Context......: " + context.getRequestPath());
		logger.info("CustomerController.registerCustomerConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("========== CustomerController.registerCustomerConfirmation ==========");
		logger.info("CustomerController:CustomerForm -> Index........: " + customerForm.getIndex());
		logger.info("CustomerController:CustomerForm -> Salutation...: " + customerForm.getSalutation());
		logger.info("CustomerController:CustomerForm -> Graduation...: " + customerForm.getGraduation());
		logger.info("CustomerController:CustomerForm -> Given Name...: " + customerForm.getGivenName());
		logger.info("CustomerController:CustomerForm -> Family Name..: " + customerForm.getFamilyName());
		logger.info("CustomerController:CustomerForm -> Phone........: " + customerForm.getPhone());
		logger.info("CustomerController:CustomerForm -> Fax..........: " + customerForm.getFax());
		logger.info("CustomerController:CustomerForm -> Mobile.......: " + customerForm.getMobile());
		logger.info("CustomerController:CustomerForm -> Email User...: " + customerForm.getEmailUser());
		logger.info("CustomerController:CustomerForm -> Email Domain.: " + customerForm.getEmailDomain());
		logger.info("CustomerController:CustomerForm -> Street.......: " + customerForm.getStreet());
		logger.info("CustomerController:CustomerForm -> HouseNumber..: " + customerForm.getHouseNumber());
		logger.info("CustomerController:CustomerForm -> City.........: " + customerForm.getCity());
		logger.info("CustomerController:CustomerForm -> CountryCode..: " + customerForm.getCountryCode());
		logger.info("CustomerController:CustomerForm -> Country......: " + customerForm.getCountry());
		logger.info("CustomerController:CustomerForm -> Comment......: " + customerForm.getComment());
		logger.info("CustomerController:CustomerForm -> EmptyTable...: " + customerForm.getEmptyTable());
		logger.info("============================= End ===================================");

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("salutation".equals(fieldViolation.getFieldKey())
						&& "{salutation.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("givenName".equals(fieldViolation.getFieldKey())
						&& "{given.name.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("familyName".equals(fieldViolation.getFieldKey())
						&& "{family.name.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("emailUser".equals(fieldViolation.getFieldKey())
						&& "{email.user.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("emailDomain".equals(fieldViolation.getFieldKey())
						&& "{email.domain.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerIndex", customerForm.getIndex());
			map.put("cIndex", customerForm.getIndex());
			map.put("cSalutation", SuperbowlHelper.replaceSpaceByMarker(customerForm.getSalutation()));
			map.put("cGraduation", SuperbowlHelper.replaceSpaceByMarker(customerForm.getGraduation()));
			map.put("cGivenName", SuperbowlHelper.replaceSpaceByMarker(customerForm.getGivenName()));
			map.put("cFamilyName", SuperbowlHelper.replaceSpaceByMarker(customerForm.getFamilyName()));
			map.put("cPhone", SuperbowlHelper.replaceSpaceByMarker(customerForm.getPhone()));
			map.put("cFax", SuperbowlHelper.replaceSpaceByMarker(customerForm.getFax()));
			map.put("cMobile", SuperbowlHelper.replaceSpaceByMarker(customerForm.getMobile()));
			map.put("cEmailUser", customerForm.getEmailUser());
			map.put("cEmailDomain", customerForm.getEmailDomain());
			map.put("cStreet", SuperbowlHelper.replaceSpaceByMarker(customerForm.getStreet()));
			map.put("cHouseNumber", customerForm.getHouseNumber());
			map.put("cZipCode", customerForm.getZipCode());
			map.put("cCity", SuperbowlHelper.replaceSpaceByMarker(customerForm.getCity()));
			map.put("cCountryCode", customerForm.getCountryCode());
			map.put("cCountry", SuperbowlHelper.replaceSpaceByMarker(customerForm.getCountry()));
			map.put("cComment", SuperbowlHelper.replaceSpaceByMarker(customerForm.getComment()));
			map.put("cEmptyTable", customerForm.getEmptyTable());

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("customer", customerForm);
			result.template("views/CustomerController/registerCustomer.ftl.html");

			session.save(context);

			return result;

		} else {

			putFormIntoSession(customerForm, session);

			String[] array = new String[3];

			Result result = Results.html();
			result.render("index", customerForm.getIndex());
			result.render("salutation", customerForm.getSalutation());
			result.render("graduation", customerForm.getGraduation());
			result.render("givenName", customerForm.getGivenName());
			result.render("familyName", customerForm.getFamilyName());
			result.render("phone", SuperbowlHelper.replaceWhiteSpace(customerForm.getPhone()));
			result.render("fax", SuperbowlHelper.replaceWhiteSpace(customerForm.getFax()));
			result.render("mobile", SuperbowlHelper.replaceWhiteSpace(customerForm.getMobile()));
			array[0] = customerForm.getEmailUser();
			array[1] = "@";
			array[2] = customerForm.getEmailDomain();
			result.render("email", StringUtils.join(array));
			result.render("street", customerForm.getStreet());
			result.render("houseNumber", customerForm.getHouseNumber());
			result.render("zipCode", customerForm.getZipCode());
			result.render("city", customerForm.getCity());
			result.render("countryCode", customerForm.getCountryCode());
			result.render("country", customerForm.getCountry());
			result.render("comment", customerForm.getComment());
			result.render("emptyTable", customerForm.getEmptyTable());

			result.render("customer", customerForm);

			session.save(context);

			return result;

		}

	}

	/**
	 * Insert method description here...
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerCustomerCompletion(Session session, Context context) {
		logger.info("CustomerController.registerCustomerCompletion -> Session    id: " + session.getId());
		logger.info("CustomerController.registerCustomerCompletion -> Session empty: " + session.isEmpty());
		logger.info("CustomerController.registerCustomerCompletion -> Context......: " + context.getRequestPath());

		CustomerDto customerDto = (CustomerDto) getDtoFromSession(session);

		customerService.register(customerDto);

		session.clear();

		return Results.ok().redirect("/customer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#putFormIntoSession(forms.Form,
	 * ninja.session.Session)
	 */
	@Override
	public void putFormIntoSession(Form form, Session session) {
		logger.info("CustomerController.putFormIntoSession -> Session id: " + session.getId());

		CustomerForm customerForm = (CustomerForm) form;

		logCustomerConfirmation(customerForm);

		String data = null;

		logger.info("=== put customer form data into session ===");
		session.put("cust.id", null);
		session.put("cust.version", "0");

		logger.info("index......: {}", customerForm.getIndex());
		session.put("cust.index", customerForm.getIndex());

		logger.info("salutation.: {}", customerForm.getSalutation());
		session.put("cust.salutation", customerForm.getSalutation());

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getGraduation())
				? customerForm.getGraduation() : Whitespace.EMPTY.getValue());
		logger.info("graduation.: {}", data);
		session.put("cust.graduation", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getGivenName())
				? customerForm.getGivenName() : Whitespace.EMPTY.getValue());
		logger.info("givenName..: {}", data);
		session.put("cust.givenName", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getFamilyName())
				? customerForm.getFamilyName() : Whitespace.EMPTY.getValue());
		logger.info("familyName.: {}", data);
		session.put("cust.familyName", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getPhone()) ? customerForm.getPhone()
				: Whitespace.EMPTY.getValue());
		logger.info("phone......: {}", data);
		session.put("cust.phone", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getFax()) ? customerForm.getFax()
				: Whitespace.EMPTY.getValue());
		logger.info("fax........: {}", data);
		session.put("cust.fax", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getMobile()) ? customerForm.getMobile()
				: Whitespace.EMPTY.getValue());
		logger.info("mobile.....: {}", data);
		session.put("cust.mobile", data);

		String[] array = new String[3];
		array[0] = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getEmailUser())
				? customerForm.getEmailUser() : Whitespace.EMPTY.getValue());
		array[1] = "@";
		array[2] = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getEmailDomain())
				? customerForm.getEmailDomain() : Whitespace.EMPTY.getValue());
		data = StringUtils.join(array);
		logger.info("email......: {}", data);
		session.put("cust.email", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getStreet()) ? customerForm.getStreet()
				: Whitespace.EMPTY.getValue());
		logger.info("street.....: {}", data);
		session.put("cust.street", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getHouseNumber())
				? customerForm.getHouseNumber() : Whitespace.EMPTY.getValue());
		logger.info("houseNumber: {}", data);
		session.put("cust.houseNumber", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getZipCode())
				? customerForm.getZipCode() : Whitespace.EMPTY.getValue());
		logger.info("zipCode....: {}", data);
		session.put("cust.zipCode", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getCity()) ? customerForm.getCity()
				: Whitespace.EMPTY.getValue());
		logger.info("city.......: {}", data);
		session.put("cust.city", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getCountryCode())
				? customerForm.getCountryCode() : Whitespace.EMPTY.getValue());
		logger.info("countryCode: {}", data);
		session.put("cust.countryCode", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getCountry())
				? customerForm.getCountry() : Whitespace.EMPTY.getValue());
		logger.info("country....: {}", data);
		session.put("cust.country", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getComment())
				? customerForm.getComment() : Whitespace.EMPTY.getValue());
		logger.info("comment....: {}", data);
		session.put("cust.comment", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(customerForm.getEmptyTable())
				? customerForm.getEmptyTable() : Whitespace.EMPTY.getValue());
		logger.info("emptyTable.: {}", data);
		session.put("cust.empty.table", data);
		logger.info("=== end put customer form data into session ===");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.IController#putDtoIntoSession(dto.Dto,
	 * ninja.session.Session)
	 */
	@Override
	public void putDtoIntoSession(Dto dto, Session session) {
		// ToDo: Implement code to put timber dto into session
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.CustomerController#getDtoFromSession(ninja.session.Session)
	 */
	@Override
	public Dto getDtoFromSession(Session session) {
		logger.info("CustomerController.getDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("cust.id") != null) {
			id = new Long(session.get("cust.id"));
		}
		Integer version = null;
		if (session.get("cust.version") != null) {
			version = new Integer(session.get("cust.version")).intValue();
		}
		Integer index = null;
		if (session.get("cust.index") != null) {
			index = new Integer(session.get("cust.index")).intValue();
		}
		String salutation = null;
		if (session.get("cust.salutation") != null) {
			salutation = session.get("cust.salutation");
		}
		String graduation = null;
		if (session.get("cust.givenName") != null) {
			graduation = session.get("cust.graduation");
		}
		String givenName = null;
		if (session.get("cust.givenName") != null) {
			givenName = session.get("cust.givenName");
		}
		String familyName = null;
		if (session.get("cust.familyName") != null) {
			familyName = session.get("cust.familyName");
		}
		String phone = null;
		if (session.get("cust.phone") != null) {
			phone = session.get("cust.phone");
		}
		String fax = session.get("cust.fax");
		String mobile = null;
		if (session.get("cust.mobile") != null) {
			mobile = session.get("cust.mobile");
		}
		String email = null;
		if (session.get("cust.email") != null) {
			email = session.get("cust.email");
		}
		String street = null;
		if (session.get("cust.street") != null) {
			street = session.get("cust.street");
		}
		String houseNumber = null;
		if (session.get("cust.houseNumber") != null) {
			houseNumber = session.get("cust.houseNumber");
		}
		String zipCode = null;
		if (session.get("cust.zipCode") != null) {
			zipCode = session.get("cust.zipCode");
		}
		String city = null;
		if (session.get("cust.city") != null) {
			city = session.get("cust.city");
		}
		String countryCode = null;
		if (session.get("cust.countryCode") != null) {
			countryCode = session.get("cust.countryCode");
		}
		String country = null;
		if (session.get("cust.country") != null) {
			country = session.get("cust.country");
		}
		String comment = null;
		if (session.get("cust.comment") != null) {
			comment = session.get("cust.comment");
		}
		String emptyTable = null;
		if (session.get("cust.empty.table") != null) {
			emptyTable = session.get("cust.empty.table");
		}

		logCustomerCompletion(session);

		Dto customerDto = new CustomerDto(id, version, index, salutation, graduation, givenName, familyName, phone, fax,
				mobile, email, street, houseNumber, zipCode, city, countryCode, country, comment, emptyTable);

		return customerDto;
	}

	/**
	 * Log {@code Customer} form data.
	 *
	 * @param customerForm
	 *            the {@code Ninja CustomerForm} instance
	 */
	private final void logCustomerConfirmation(CustomerForm customerForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.customer.confirmation"));
		if (isLog) {
			logger.info("=== customer confirmation (form data) ===");
			logger.info("id.........: {}", customerForm.getId());
			logger.info("version....: {}", customerForm.getVersion());
			logger.info("index......: {}", customerForm.getIndex());
			logger.info("salutation.: {}", customerForm.getSalutation());
			logger.info("graduation.: {}", customerForm.getGraduation());
			logger.info("givenName..: {}", customerForm.getGivenName());
			logger.info("familyName.: {}", customerForm.getFamilyName());
			logger.info("phone......: {}", customerForm.getPhone());
			logger.info("fax........: {}", customerForm.getFax());
			logger.info("mobile.....: {}", customerForm.getMobile());
			logger.info("email......: {}", customerForm.getEmailUser() + "@" + customerForm.getEmailDomain());
			logger.info("street.....: {}", customerForm.getStreet());
			logger.info("houseNumber: {}", customerForm.getHouseNumber());
			logger.info("zipCode....: {}", customerForm.getZipCode());
			logger.info("city.......: {}", customerForm.getCity());
			logger.info("countryCode: {}", customerForm.getCountryCode());
			logger.info("country....: {}", customerForm.getCountry());
			logger.info("comment....: {}", customerForm.getComment());
			logger.info("emptyTable.: {}", customerForm.getEmptyTable());
			logger.info("=== end customer confirmation (form data) ===");
		}
	}

	/**
	 * Insert method description here...
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	private final void logCustomerCompletion(Session session) {
		logger.info("CustomerController.logCustomerCompletion -> Session id   : " + session.getId());
		logger.info("CustomerController.logCustomerCompletion -> Session empty: " + session.isEmpty());
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.customer.completion"));
		if (isLog) {
			Long id = null;
			if (session.get("cust.id") != null) {
				id = new Long(session.get("cust.id"));
			}
			Integer version = null;
			if (session.get("cust.version") != null) {
				version = new Integer(session.get("cust.version")).intValue();
			}
			logger.info("=== customer completion (session data) ===");
			logger.info("id.........: {}", id);
			logger.info("version....: {}", version);
			logger.info("index......: {}", session.get("cust.index"));
			logger.info("salutation.: {}", session.get("cust.salutation"));
			logger.info("graduation.: {}", session.get("cust.graduation"));
			logger.info("givenName..: {}", session.get("cust.givenName"));
			logger.info("familyName.: {}", session.get("cust.familyName"));
			logger.info("phone......: {}", session.get("cust.phone"));
			logger.info("fax........: {}", session.get("cust.fax"));
			logger.info("mobile.....: {}", session.get("cust.mobile"));
			logger.info("email......: {}", session.get("cust.email"));
			logger.info("street.....: {}", session.get("cust.street"));
			logger.info("houseNumber: {}", session.get("cust.houseNumber"));
			logger.info("zipCode....: {}", session.get("cust.zipCode"));
			logger.info("city.......: {}", session.get("cust.city"));
			logger.info("countryCode: {}", session.get("cust.countryCode"));
			logger.info("country....: {}", session.get("cust.country"));
			logger.info("comment....: {}", session.get("cust.comment"));
			logger.info("emptyTable.: {}", session.get("cust.emptyTable"));
			logger.info("=== end customer completion (session data) ===");
		}
	}

	/**
	 * Logging of confirmation of {@code Customer} data.
	 * 
	 * @param emptyTable
	 *            the empty table indicator
	 * @param customerIndex
	 *            the {@code Customer} index
	 */
	private final void logExtraData(Boolean emptyTable, Integer customerIndex) {
		logger.info("=== customer extra data ===");
		logger.info("CustomerController.registerCustomer emptyTable...........: {}", emptyTable);
		logger.info("CustomerController.registerCustomer customerIndex........: {}", customerIndex);
		logger.info("=== end customer extra data ===");
	}

}
