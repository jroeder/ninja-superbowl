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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Singleton;

import dto.BowlDto;
import dto.BowlModDto;
import dto.BowlModItemDto;
import dto.BowlModStepDto;
import dto.BowlModificationDto;
import dto.CustomerDto;
import dto.ExhibitionDto;
import dto.GeoRegionDto;
import dto.ManufactureDto;
import dto.StatusDto;
import dto.TimberDto;
import dto.TimberOriginDto;
import entity.BowlMod;
import entity.Customer;
import entity.Exhibition;
import entity.Status;
import entity.TimberOrigin;
import forms.BowlForm;
import forms.BowlModForm;
import forms.BowlModItemForm;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.exceptions.BadRequestException;
import ninja.params.Param;
import ninja.session.Session;
import ninja.validation.ConstraintViolation;
import ninja.validation.JSR303Validation;
import ninja.validation.Validation;
import types.Checker;
import types.SuperbowlHelper;

/**
 * IController instance to handle user requests regarding a {@code Bowl}.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 13.04.2017 mbsusr01
 */
@Singleton
public class BowlController extends AbstractBowlController {

	/**
	 * Retrieve a bowl from database by identifier.
	 *
	 * @param id
	 *            the unique bowl technical identifier
	 * @return {@code Result} instance
	 */
	public Result retrieve(@Param("id") Long id) {
		BowlDto bowlDto = null;
		if (id != null) {
			bowlDto = bowlService.getBowlById(id);
		} else {
			return Results.html();
		}
		return Results.html().render("bowl", bowlDto);
	}

	/**
	 * Handles the modification of the status, price, origin and comment of a
	 * {@code Bowl}.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @param timberOriginId
	 *            the unique technical identifier of a {@code TimberOrigin}
	 * @param statusCode
	 *            the {@code Status} code
	 * @return {@code Result} instance
	 */
	public Result editBowl(Session session, Context context, @Param("bowlId") String bowlId,
			@Param("timberOriginId") String timberOriginId, @Param("statusCode") String statusCode) {
		logger.info("BowlController.editBowl -> Session      id: " + session.getId());
		logger.info("BowlController.editBowl -> Bowl         id: " + bowlId);
		logger.info("BowlController.editBowl -> TimberOrigin id: " + timberOriginId);
		logger.info("BowlController.editBowl -> Status     code: " + statusCode);

		Map<String, Object> map = new HashMap<String, Object>();

		List<StatusDto> statusList = statusService.listStatus();
		map.put("statusList", statusList);

		List<TimberOriginDto> timberOriginList = timberOriginService.listTimberOrigin();
		map.put("timberOriginList", timberOriginList);

		StatusDto statusDto = null;
		Integer statusIndex = null;
		Integer timberOriginIndex = null;
		Long selectedStatusId = null;
		String selectedStatusCode = null;
		Long selectedTimberOriginId = null;

		BowlDto bowlDto = bowlService.getBowlById(bowlId);
		TimberOriginDto timberOriginDto = timberOriginService.getTimberOriginById(timberOriginId);

		timberOriginIndex = timberOriginDto.getIndex();

		if (statusCode.equals(UNKNOWN_STATUS_CODE)) {
			statusIndex = bowlDto.getStatus().getIndex();
		} else {
			statusDto = statusService.getStatusByCode(statusCode);
			statusIndex = statusDto.getIndex();
		}
		selectedStatusId = statusList.get(statusIndex).getId();
		selectedStatusCode = statusList.get(statusIndex).getCode();
		selectedTimberOriginId = timberOriginList.get(timberOriginIndex).getId();

		map.put("statusIndex", statusIndex);
		map.put("timberOriginIndex", timberOriginIndex);
		map.put("selectedStatusId", selectedStatusId);
		map.put("selectedStatusCode", selectedStatusCode);
		map.put("selectedTimberOriginId", selectedTimberOriginId);

		map.put("bowlId", bowlDto.getId());
		map.put("bowlVersion", bowlDto.getVersion());
		map.put("bowlGeoRegionId", bowlDto.getTimber().getGeoRegion().getId());
		map.put("bowlTimberId", bowlDto.getTimber().getId());
		map.put("bowlManufactureId", bowlDto.getManufacture().getId());
		map.put("bowlYear", bowlDto.getManufacture().getYear());
		map.put("bowlOrdinal", bowlDto.getOrdinal());
		map.put("bowlImageName", bowlDto.getImageName());
		map.put("bowlCent", (bowlDto.getCent() == null ? new BigDecimal("00") : bowlDto.getCent()));

		BowlDto sessionBowlDto = getBowlDtoFromSession(session);

		if (sessionBowlDto.isNative()) {
			map.put("bowlStatusId", bowlDto.getStatus().getId());
			map.put("bowlTimberOriginId", bowlDto.getTimberOrigin().getId());
			map.put("bowlPrice", bowlDto.getPrice());
			map.put("bowlComment", SuperbowlHelper.replaceSpaceByMarker(bowlDto.getComment()));
		} else {
			map.put("bowlStatusId", sessionBowlDto.getStatus().getId());
			map.put("bowlTimberOriginId", sessionBowlDto.getTimberOrigin().getId());
			map.put("bowlPrice", sessionBowlDto.getPrice());
			map.put("bowlComment", SuperbowlHelper.replaceSpaceByMarker(sessionBowlDto.getComment()));
		}

		session.save(context);

		return Results.html().render(map).template("views/BowlController/editBowl.ftl.html");
	}

	/**
	 * Handles the modification of the status, price, origin and comment of a
	 * {@code Bowl}.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @param timberOriginId
	 *            the unique technical identifier of a {@code TimberOrigin}
	 * @param statusCode
	 *            the {@code Status} code
	 * @param exhibitionId
	 *            the unique technical identifier of a {@code Exhibition}
	 * @param customerId
	 *            the unique technical identifier of a {@code Customer}
	 * @return {@code Result} instance
	 */
	public Result updateBowl(Session session, Context context, @Param("bowlId") String bowlId,
			@Param("timberOriginId") String timberOriginId, @Param("statusCode") String statusCode,
			@Param("exhibitionId") String exhibitionId, @Param("customerId") String customerId) {
		logger.info("BowlController.updateBowl -> Session      id: {}", session.getId());
		logger.info("BowlController.updateBowl -> Bowl         id: {}", bowlId);
		logger.info("BowlController.updateBowl -> TimberOrigin id: {}", timberOriginId);
		logger.info("BowlController.updateBowl -> Status     code: {}", statusCode);
		logger.info("BowlController.updateBowl -> Exhibition   id: {}", exhibitionId);
		logger.info("BowlController.updateBowl -> Customer     id: {}", customerId);

		Map<String, Object> map = new HashMap<String, Object>();

		/*
		 * TIMBERORIGIN
		 */
		List<TimberOriginDto> timberOriginList = timberOriginService.listTimberOrigin();
		map.put("timberOriginList", timberOriginList);

		TimberOriginDto timberOriginDto = timberOriginService.getTimberOriginById(timberOriginId);

		Integer timberOriginIndex = timberOriginDto.getIndex();

		Long selectedTimberOriginId = timberOriginList.get(timberOriginIndex).getId();

		/*
		 * STATUS
		 */
		List<StatusDto> statusList = statusService.listStatus();
		map.put("statusList", statusList);

		StatusDto statusDto = statusService.getStatusByCode(statusCode);

		Integer bowlStatusIndex = statusDto.getIndex();

		Long selectedStatusId = statusList.get(bowlStatusIndex).getId();
		String selectedStatusCode = statusList.get(bowlStatusIndex).getCode();

		/*
		 * EXHIBITION und CUSTOMER
		 */
		List<ExhibitionDto> exhibitionList = null;
		List<CustomerDto> customerList = null;

		ExhibitionDto exhibitionDto = null;
		CustomerDto customerDto = null;

		Long selectedExhibitionId = 1L;
		Long selectedCustomerId = 1L;
		Integer exhibitionIndex = 0;
		Integer customerIndex = 0;

		switch (statusCode) {
		case "EXHI":
			exhibitionList = exhibitionService.listExhibitions();
			map.put("exhibitionList", exhibitionList);
			if (exhibitionList.isEmpty()) {
				if (exhibitionId == null) {
					selectedExhibitionId = 1L;
					exhibitionIndex = 0;
				} else {
					if (!exhibitionId.isEmpty()) {
						selectedExhibitionId = new Long(exhibitionId);
						exhibitionIndex = selectedExhibitionId.intValue() - 1;
					}
				}
			} else {
				if (exhibitionId == null) {
					selectedExhibitionId = 1L;
					exhibitionIndex = 0;
				} else {
					if (exhibitionId.isEmpty()) {
						selectedExhibitionId = 1L;
						exhibitionIndex = 0;
					} else {
						selectedExhibitionId = new Long(exhibitionId);
						exhibitionDto = exhibitionService.getExhibitionById(selectedExhibitionId);
						exhibitionIndex = exhibitionDto.getIndex();
					}
				}
			}
			break;
		case "REST":
		case "RSRT":
		case "RSVD":
		case "SOLD":
			customerList = customerService.listCustomers();
			map.put("customerList", customerList);
			if (customerList.isEmpty()) {
				if (customerId == null) {
					selectedCustomerId = 1L;
					customerIndex = 0;
				} else {
					if (!customerId.isEmpty()) {
						selectedCustomerId = new Long(customerId);
						customerIndex = selectedCustomerId.intValue() - 1;
					}
				}
			} else {
				if (customerId == null) {
					selectedCustomerId = 1L;
					customerIndex = 0;
				} else {
					if (customerId.isEmpty()) {
						selectedCustomerId = 1L;
						customerIndex = 0;
					} else {
						selectedCustomerId = new Long(customerId);
						customerDto = customerService.getCustomerById(selectedCustomerId);
						customerIndex = customerDto.getIndex();
					}
				}
			}
			break;
		default:
		}

		map.put("timberOriginIndex", timberOriginIndex);
		map.put("selectedTimberOriginId", selectedTimberOriginId);

		map.put("bowlStatusIndex", bowlStatusIndex);
		map.put("selectedStatusId", selectedStatusId);
		map.put("selectedStatusCode", selectedStatusCode);

		map.put("exhibitionIndex", exhibitionIndex);
		map.put("selectedExhibitionId", selectedExhibitionId);

		map.put("customerIndex", customerIndex);
		map.put("selectedCustomerId", selectedCustomerId);

		BowlDto bowlDto = bowlService.getBowlById(new Long(bowlId));

		map.put("bowlId", bowlDto.getId());
		map.put("bowlVersion", bowlDto.getVersion());
		map.put("bowlGeoRegionId", bowlDto.getTimber().getGeoRegion().getId());
		map.put("bowlTimberId", bowlDto.getTimber().getId());
		map.put("bowlManufactureId", bowlDto.getManufacture().getId());
		map.put("bowlOrdinal", bowlDto.getOrdinal());
		map.put("bowlYear", bowlDto.getYear());
		map.put("bowlImageName", bowlDto.getImageName());
		map.put("bowlCent", (bowlDto.getCent() == null ? "00" : bowlDto.getCent().toString()));
		map.put("bowlEmptyTable", bowlDto.getEmptyTable());

		BowlDto sessionBowlDto = getBowlDtoFromSession(session);

		if (sessionBowlDto.isNative()) {
			map.put("bowlStatusId", bowlDto.getStatus().getId());
			map.put("bowlTimberOriginId", bowlDto.getTimberOrigin().getId());
			map.put("bowlPrice", bowlDto.getPrice());
			if (bowlDto.getSalesPrice() != null) {
				map.put("bowlSalesPrice", bowlDto.getSalesPrice());
			}
			map.put("bowlSalesCent", "00");
			if (bowlDto.getSalesLocation() != null) {
				map.put("bowlSalesLocation", SuperbowlHelper.replaceSpaceByMarker(bowlDto.getSalesLocation()));
			}
			if (bowlDto.getSalesDate() != null) {
				map.put("bowlSalesDate", SuperbowlHelper.convertDate(bowlDto.getSalesDate().toString()));
			}
			map.put("bowlComment", SuperbowlHelper.replaceSpaceByMarker(bowlDto.getComment()));
		} else {
			map.put("bowlStatusId", sessionBowlDto.getStatus().getId());
			map.put("bowlTimberOriginId", sessionBowlDto.getTimberOrigin().getId());
			map.put("bowlPrice", sessionBowlDto.getPrice());
			if (sessionBowlDto.getSalesPrice() != null) {
				map.put("bowlSalesPrice", sessionBowlDto.getSalesPrice());
			}
			map.put("bowlSalesCent", "00");
			if (sessionBowlDto.getSalesLocation() != null) {
				map.put("bowlSalesLocation", SuperbowlHelper.replaceSpaceByMarker(sessionBowlDto.getSalesLocation()));
			}
			if (sessionBowlDto.getSalesDate() != null) {
				map.put("bowlSalesDate", SuperbowlHelper.convertDate(sessionBowlDto.getSalesDate().toString()));
			}
			map.put("bowlComment", SuperbowlHelper.replaceSpaceByMarker(sessionBowlDto.getComment()));
		}

		session.save(context);

		return Results.html().render(map).template("views/BowlController/updateBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param bowlForm
	 *            the {@code BowlForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result updateBowlConfirmation(@JSR303Validation BowlForm bowlForm, Session session, Validation validation,
			Context context) {
		logger.info("BowlController.updateBowlConfirmation -> Session id...: " + session.getId());
		logger.info("BowlController.updateBowlConfirmation -> Session empty: " + session.isEmpty());
		logger.info("BowlController.updateBowlConfirmation -> Context......: " + context.getRequestPath());
		logger.info("BowlController.updateBowlConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("===== Update Bowl Confirmation =====");
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Id............: " + bowlForm.getId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Version.......: " + bowlForm.getVersion());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Ordinal.......: " + bowlForm.getOrdinal());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Index.........: " + bowlForm.getIndex());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Year..........: " + bowlForm.getYear());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> ImageName.....: " + bowlForm.getImageName());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Price.........: " + bowlForm.getPrice());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> SalesPrice....: " + bowlForm.getSalesPrice());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> SalesLocation.: " + bowlForm.getSalesLocation());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> SalesDate.....: " + bowlForm.getSalesDate());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> Comment.......: " + bowlForm.getComment());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> GeoRegionId...: " + bowlForm.getGeoRegionId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> GeoRegionCode.: " + bowlForm.getGeoRegionCode());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> ManufactureId.: " + bowlForm.getManufactureId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> StatusId......: " + bowlForm.getStatusId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> StatusCode....: " + bowlForm.getStatusCode());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> TimberId......: " + bowlForm.getTimberId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> TimberCode....: " + bowlForm.getTimberCode());
		logger.info(
				"BowlController.updateBowlConfirmation BowlForm -> TimberOriginId: " + bowlForm.getTimberOriginId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> CustomerId....: " + bowlForm.getCustomerId());
		logger.info("BowlController.updateBowlConfirmation BowlForm -> ExhibitionId..: " + bowlForm.getExhibitionId());
		logger.info("=== End Update Bowl Confirmation ===");

		TimberDto timberDto = null;
		TimberOriginDto timberOriginDto = null;
		ManufactureDto manufactureDto = null;
		StatusDto statusDto = null;
		CustomerDto customerDto = null;
		ExhibitionDto exhibitionDto = null;

		Long statusId = null;
		Long timberId = null;
		Long timberOriginId = null;
		String customerId = null;
		String exhibitionId = null;

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("geoRegionId".equals(fieldViolation.getFieldKey())
						&& "{bowl.georegion.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("manufactureId".equals(fieldViolation.getFieldKey())
						&& "{bowl.manufacture.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("statusId".equals(fieldViolation.getFieldKey())
						&& "{bowl.status.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("timberId".equals(fieldViolation.getFieldKey())
						&& "{bowl.timber.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("timberOriginId".equals(fieldViolation.getFieldKey())
						&& "{bowl.timber.origin.id.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			List<StatusDto> statusList = statusService.listStatus();

			statusId = new Long(bowlForm.getStatusId());
			statusDto = statusService.getStatusById(statusId);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusList", statusList);
			map.put("statusIndex", bowlForm.getStatusIndex());
			map.put("statusCode", statusDto.getCode());
			map.put("statusText", bowlForm.getStatusText());
			map.put("bowlId", bowlForm.getId());
			map.put("bowlVersion", bowlForm.getVersion());
			map.put("bowlGeoRegionId", bowlForm.getGeoRegionId());
			map.put("bowlTimberId", bowlForm.getTimberId());
			map.put("bowlTimberOriginId", bowlForm.getTimberOriginId());
			map.put("bowlManufactureId", bowlForm.getManufactureId());
			map.put("bowlStatusId", bowlForm.getStatusId());
			map.put("bowlOrdinal", bowlForm.getOrdinal());
			map.put("bowlImageName", bowlForm.getImageName());
			map.put("bowlPrice", bowlForm.getPrice());
			map.put("bowlSalesPrice", bowlForm.getSalesPrice());
			map.put("bowlSalesLocation", bowlForm.getSalesLocation());
			map.put("bowlSalesDate", bowlForm.getSalesDate());
			map.put("bowlComment", bowlForm.getComment());
			map.put("bowlEmptyTable", bowlForm.getEmptyTable());

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("bowl", bowlForm);
			result.template("views/BowlController/editBowl.ftl.html");

			session.save(context);

			return result;

		} else {

			manufactureDto = manufactureService.getManufactureById(bowlForm.getManufactureId());
			statusDto = statusService.getStatusById(bowlForm.getStatusId());
			timberDto = timberService.getTimberById(bowlForm.getTimberId());
			timberOriginDto = timberOriginService.getTimberOriginById(bowlForm.getTimberOriginId());

			bowlForm.setYear(manufactureDto.getYear());
			bowlForm.setStatusText(statusDto.getText());
			bowlForm.setTimberName(timberDto.getName());

			customerId = bowlForm.getCustomerId();
			if (customerId != null) {
				customerDto = customerService.getCustomerById(customerId);
				bowlForm.setCustomerGivenName(customerDto.getGivenName());
				bowlForm.setCustomerFamilyName(customerDto.getFamilyName());
			}

			exhibitionId = bowlForm.getExhibitionId();
			if (exhibitionId != null) {
				exhibitionDto = exhibitionService.getExhibitionById(exhibitionId);
				bowlForm.setExhibitionName(exhibitionDto.getName());
			}

			putBowlFormIntoSession(bowlForm, session);

			Result result = Results.html();
			result.render("manufactureId", bowlForm.getManufactureId());
			result.render("timberOriginId", bowlForm.getTimberOriginId());
			result.render("timberOriginName", bowlForm.getTimberOriginName());
			result.render("statusId", bowlForm.getStatusId());
			result.render("statusCode", statusDto.getCode());
			result.render("statusText", statusDto.getText());
			result.render("price", bowlForm.getPrice());
			result.render("salesPrice", bowlForm.getSalesPrice());
			result.render("salesLocation", bowlForm.getSalesLocation());
			result.render("salesDate", bowlForm.getSalesDate());
			result.render("comment", bowlForm.getComment());
			result.render("emptyTable", bowlForm.getEmptyTable());
			if (customerDto != null) {
				result.render("customerId", customerDto.getId().toString());
			}
			if (exhibitionDto != null) {
				result.render("exhibitionId", exhibitionDto.getId().toString());
			}

			result.render("bowl", bowlForm);

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
	public Result updateBowlCompletion(Session session, Context context) {

		logger.info("BowlController.updateBowlCompletion -> Session Id...: " + session.getId());
		logger.info("BowlController.updateBowlCompletion -> Context......: " + context.getRequestPath());

		BowlDto sessionBowlDto = getBowlDtoFromSession(session);

		logger.info("BowlController.updateBowlCompletion -> BowlDto (Session): " + sessionBowlDto.asString());

		StatusDto statusDto = null;
		TimberOriginDto timberOriginDto = null;
		CustomerDto customerDto = null;
		ExhibitionDto exhibitionDto = null;

		Status status = sessionBowlDto.getStatus();
		logger.info("BowlController.updateBowlCompletion -> Status......: " + status.asString());
		TimberOrigin timberOrigin = sessionBowlDto.getTimberOrigin();
		logger.info("BowlController.updateBowlCompletion -> TimberOrigin: " + timberOrigin.asString());
		Customer customer = sessionBowlDto.getCustomer();
		logger.info("BowlController.updateBowlCompletion -> Customer....: "
				+ (customer == null ? "Customer is null" : customer.asString()));
		Exhibition exhibition = sessionBowlDto.getExhibition();
		logger.info("BowlController.updateBowlCompletion -> Exhibition..: "
				+ (exhibition == null ? "Exhibition is null" : exhibition.asString()));

		Long statusId = null;
		Long timberOriginId = null;
		Long customerId = null;
		Long exhibitionId = null;

		logger.info("BowlController.updateBowlCompletion -> Bowl Id......: " + sessionBowlDto.getId());
		logger.info("BowlController.updateBowlCompletion -> Preis........: " + sessionBowlDto.getPrice());
		logger.info("BowlController.updateBowlCompletion -> Cent.........: " + sessionBowlDto.getCent());
		logger.info("BowlController.updateBowlCompletion -> Comment......: " + sessionBowlDto.getComment());

		/*
		 * BowlDto ist bis hier aus Sicht von Hibernate ein detached object!
		 */
		BowlDto attachedBowlDto = bowlService.getBowlById(sessionBowlDto.getId());

		if (status != null) {
			statusId = status.getId();
			logger.info("BowlController.updateBowlCompletion -> Status Id.....: " + statusId);
			statusDto = statusService.getStatusById(statusId);
			logger.info("BowlController.updateBowlCompletion -> StatusDto (Attached): " + statusDto.asString());
			attachedBowlDto.setStatus(new Status(statusDto));
		}
		if (timberOrigin != null) {
			timberOriginId = timberOrigin.getId();
			logger.info("BowlController.updateBowlCompletion -> TimberOrigin Id: " + timberOriginId);
			timberOriginDto = timberOriginService.getTimberOriginById(timberOriginId);
			logger.info(
					"BowlController.updateBowlCompletion -> TimberOriginDto (Attached): " + timberOriginDto.asString());
			attachedBowlDto.setTimberOrigin(new TimberOrigin(timberOriginDto));
		}
		if (customer == null) {
			attachedBowlDto.setCustomer(null);
			attachedBowlDto.setSalesPrice(null);
			attachedBowlDto.setSalesLocation(null);
			attachedBowlDto.setSalesDate(null);
		} else {
			customerId = customer.getId();
			if (customerId == null) {
				customerId = new Long(session.get("bowl.customer.id"));
			}
			logger.info("BowlController.updateBowlCompletion -> Customer Id....: " + customerId);
			customerDto = customerService.getCustomerById(customerId);
			logger.info("BowlController.updateBowlCompletion -> CustomerDto (Attached): " + customerDto.asString());
			attachedBowlDto.setCustomer(new Customer(customerDto));
			attachedBowlDto.setSalesPrice(sessionBowlDto.getSalesPrice());
			attachedBowlDto.setSalesLocation(sessionBowlDto.getSalesLocation());
			attachedBowlDto.setSalesDate(sessionBowlDto.getSalesDate());
		}
		if (exhibition == null) {
			attachedBowlDto.setExhibition(null);
		} else {
			exhibitionId = exhibition.getId();
			if (exhibitionId == null) {
				exhibitionId = new Long(session.get("bowl.exhibition.id"));
			}
			logger.info("BowlController.updateBowlCompletion -> Exhibition Id..: " + exhibitionId);
			exhibitionDto = exhibitionService.getExhibitionById(exhibitionId);
			logger.info("BowlController.updateBowlCompletion -> ExhibitionDto (Attached): " + exhibitionDto.asString());
			attachedBowlDto.setExhibition(new Exhibition(exhibitionDto));
		}
		attachedBowlDto.setComment(sessionBowlDto.getComment());
		attachedBowlDto.setPrice(sessionBowlDto.getPrice());
		attachedBowlDto.setCent(sessionBowlDto.getCent());

		logger.info("BowlController.updateBowlCompletion -> BowlDto (Attached): " + attachedBowlDto.asString());

		bowlService.merge(attachedBowlDto);

		session.clear();

		return Results.ok().redirect("/superbowl/bowl");

	}

	/**
	 * Insert method description here...
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @param statusCode
	 *            the query parameter for a {@code Status} code
	 * @return {@code Result} instance
	 */
	public Result editBowlByStatusCode(Session session, Context context, @Param("bowlId") String bowlId,
			@Param("statusCode") String statusCode) {
		logger.info("BowlController.editBowlByStatusCode -> Session...id: " + session.getId());
		logger.info("BowlController.editBowlByStatusCode -> Bowl..... id: " + bowlId);
		logger.info("BowlController.editBowlByStatusCode -> Status..code: " + statusCode);

		Map<String, Object> map = new HashMap<String, Object>();

		List<StatusDto> statusList = statusService.listStatus();
		map.put("statusList", statusList);

		StatusDto statusDto = statusService.getStatusByCode(statusCode);
		logger.info("BowlController.editBowlByStatusCode -> Status....id: " + statusDto.getId());
		logger.info("BowlController.editBowlByStatusCode -> Status.index: " + statusDto.getIndex());

		map.put("statusIndex", statusDto.getIndex());
		map.put("selectedStatusId", statusDto.getId());
		map.put("selectedStatusCode", statusDto.getCode());

		BowlDto sessionBowlDto = getBowlDtoFromSession(session);
		logger.info("BowlController.editBowlByStatusCode -> SessionBowlDto Comment: " + sessionBowlDto.getComment());

		BowlDto bowlDto = bowlService.getBowlById(new Long(bowlId));
		Status status = bowlDto.getStatus();
		status.setIndex(statusDto.getIndex());
		bowlDto.setStatus(status);

		map.put("bowlId", bowlDto.getId());
		map.put("bowlVersion", bowlDto.getVersion());
		map.put("bowlGeoRegionId", bowlDto.getTimber().getGeoRegion().getId());
		map.put("bowlTimberId", bowlDto.getTimber().getId());
		map.put("bowlOrdinal", bowlDto.getOrdinal());
		map.put("bowlImageName", bowlDto.getImageName());
		map.put("bowlPrice", bowlDto.getPrice());
		map.put("bowlCent", (bowlDto.getCent() == null ? new BigDecimal("00") : bowlDto.getCent()));
		map.put("bowlComment", helper.replaceSpaceByMarker(bowlDto.getComment()));

		session.save(context);

		return Results.html().render(map).template("views/BowlController/editBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param bowlForm
	 *            the {@code BowlForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result editBowlConfirmation(@JSR303Validation BowlForm bowlForm, Session session, Validation validation,
			Context context) {
		logger.info("BowlController.editBowlConfirmation -> Session id...: " + session.getId());
		logger.info("BowlController.editBowlConfirmation -> Session empty: " + session.isEmpty());
		logger.info("BowlController.editBowlConfirmation -> Context......: " + context.getRequestPath());
		logger.info("BowlController.editBowlConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("===== Edit Bowl Confirmation =====");
		logger.info("BowlController.editBowlConfirmation BowlForm -> Id............: " + bowlForm.getId());
		logger.info("BowlController.editBowlConfirmation BowlForm -> Version.......: " + bowlForm.getVersion());
		logger.info("BowlController.editBowlConfirmation BowlForm -> Ordinal.......: " + bowlForm.getOrdinal());
		logger.info("BowlController.editBowlConfirmation BowlForm -> Index.........: " + bowlForm.getIndex());
		logger.info("BowlController.editBowlConfirmation BowlForm -> TimberId......: " + bowlForm.getTimberId());
		logger.info("BowlController.editBowlConfirmation BowlForm -> TimberOriginId: " + bowlForm.getTimberOriginId());
		logger.info("BowlController.editBowlConfirmation BowlForm -> ManufactureId.: " + bowlForm.getManufactureId());
		logger.info("BowlController.editBowlConfirmation BowlForm -> StatusId......: " + bowlForm.getStatusId());
		logger.info("BowlController.editBowlConfirmation BowlForm -> ExhibitionId..: " + bowlForm.getExhibitionId());
		logger.info("BowlController.editBowlConfirmation BowlForm -> Year..........: " + bowlForm.getYear());
		logger.info("BowlController.editBowlConfirmation BowlForm -> ImageName.....: " + bowlForm.getImageName());
		logger.info("BowlController.editBowlConfirmation BowlForm -> Price.........: " + bowlForm.getPrice());
		logger.info("BowlController.editBowlConfirmation BowlForm -> SalesPrice....: " + bowlForm.getSalesPrice());
		logger.info("BowlController.editBowlConfirmation BowlForm -> SalesLocation.: " + bowlForm.getSalesLocation());
		logger.info("BowlController.editBowlConfirmation BowlForm -> SalesDate.....: " + bowlForm.getSalesDate());
		logger.info("BowlController.editBowlConfirmation BowlForm -> Comment.......: " + bowlForm.getComment());
		logger.info("BowlController.editBowlConfirmation BowlForm -> EmptyTable....: " + bowlForm.getEmptyTable());
		logger.info("=== End Edit Bowl Confirmation ===");

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("price".equals(fieldViolation.getFieldKey())
						&& "{bowl.price.invalid}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			List<StatusDto> statusList = statusService.listStatus();

			StatusDto statusDto = statusService.getStatusById(new Long(bowlForm.getStatusId()));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("statusList", statusList);
			map.put("statusIndex", bowlForm.getStatusIndex());
			map.put("statusText", bowlForm.getStatusText());
			map.put("statusCode", statusDto.getCode());
			map.put("bowlId", bowlForm.getId());
			map.put("bowlVersion", bowlForm.getVersion());
			map.put("bowlGeoRegionId", bowlForm.getGeoRegionId());
			map.put("bowlTimberId", bowlForm.getTimberId());
			map.put("bowlManufactureId", bowlForm.getManufactureId());
			map.put("bowlOrdinal", bowlForm.getOrdinal());
			map.put("bowlYear", bowlForm.getYear());
			map.put("bowlImageName", bowlForm.getImageName());
			map.put("bowlPrice", bowlForm.getPrice());
			map.put("bowlSalesPrice", bowlForm.getSalesPrice());
			map.put("bowlSalesLocation", bowlForm.getSalesLocation());
			map.put("bowlSalesDate", bowlForm.getSalesDate());
			map.put("bowlComment", bowlForm.getComment());
			map.put("bowlEmptyTable", bowlForm.getEmptyTable());

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("bowl", bowlForm);
			result.template("views/BowlController/editBowl.ftl.html");

			session.save(context);

			return result;

		} else {

			ManufactureDto manufactureDto = manufactureService.getManufactureById(bowlForm.getManufactureId());
			StatusDto statusDto = statusService.getStatusById(bowlForm.getStatusId());
			TimberDto timberDto = timberService.getTimberById(bowlForm.getTimberId());
			bowlForm.setYear(manufactureDto.getYear());
			bowlForm.setStatusText(statusDto.getText());
			bowlForm.setTimberName(timberDto.getName());

			// BowlForm in Session speichern
			putBowlFormIntoSession(bowlForm, session);

			Result result = Results.html();
			// result.render("geoRegionId", bowlForm.getGeoRegionId());
			result.render("manufactureId", bowlForm.getManufactureId());
			result.render("statusId", bowlForm.getStatusId());
			result.render("statusText", statusDto.getText());
			result.render("statusCode", statusDto.getCode());
			// result.render("timberId", bowlForm.getTimberId());
			// result.render("timberName", timberDto.getName());
			result.render("timberOriginId", bowlForm.getTimberOriginId());
			result.render("timberOriginName", bowlForm.getTimberOriginName());
			// result.render("ordinal", bowlForm.getOrdinal());
			// result.render("year", bowlForm.getYear());
			result.render("price", bowlForm.getPrice());
			// result.render("cent", bowlForm.getCent());
			// result.render("imageName", bowlForm.getImageName());
			result.render("salesPrice", bowlForm.getSalesPrice());
			result.render("salesLocation", bowlForm.getSalesLocation());
			result.render("salesDate", bowlForm.getSalesDate());
			result.render("comment", bowlForm.getComment());
			result.render("emptyTable", bowlForm.getEmptyTable());

			result.render("bowl", bowlForm);

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
	public Result editBowlCompletion(Session session, Context context) {

		logger.info("BowlController.editBowlCompletion -> SessionId..: " + session.getId());
		logger.info("BowlController.editBowlCompletion -> RequestPath: " + context.getRequestPath());

		BowlDto bowlDto = getBowlDtoFromSession(session);

		logger.info("BowlController.editBowlCompletion -> BowlId.....: " + bowlDto.getId());
		logger.info("BowlController.editBowlCompletion -> StatusId...: " + bowlDto.getStatus().getId());
		logger.info("BowlController.editBowlCompletion -> Preis......: " + bowlDto.getPrice());
		logger.info("BowlController.editBowlCompletion -> Comment....: " + bowlDto.getComment());

		StatusDto statusDto = statusService.getStatusById(bowlDto.getStatus().getId());

		bowlDto.setStatus(new Status(statusDto));

		bowlService.merge(bowlDto);

		session.clear();

		return Results.ok().redirect("/superbowl/bowl");

	}

	/**
	 * Handles the modification of a {@code Bowl} during the manual
	 * manufacturing process.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @return {@code Result} instance
	 */
	public Result modifyBowl(Session session, Context context, @Param("bowlId") String bowlId) {
		logger.info("BowlController.modifyBowl -> SessionId......: " + session.getId());
		logger.info("BowlController.modifyBowl -> Session.IsEmpty: " + session.isEmpty());
		logger.info("BowlController.modifyBowl -> BowlId.........: " + bowlId);

		BowlDto bowlDto = bowlService.getBowlById(bowlId);

		putBowlDtoIntoSession(bowlDto, session);

		BowlForm bowlForm = new BowlForm(bowlDto);

		String timberOriginName = bowlDto.getTimberOrigin().getCity() + "  " + bowlDto.getTimberOrigin().getLocation()
				+ "  " + bowlDto.getTimberOrigin().getLocationText();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bowl", bowlForm);
		map.put("year", bowlDto.getManufacture().getYear());
		map.put("timberOriginName", timberOriginName);

		Result result = Results.html();
		result.render(map);

		/*
		 * Alle joined Modifikationen zu einem Objekt aus der Datenbank holen
		 */
		List<BowlModificationDto> bowlModificationList = bowlService
				.listJoinedBowlModificationsByBowlId(new Long(bowlId));
		logger.info("BowlController.modifyBowl -> BowlModificationList size: " + bowlModificationList.size());

		// Iterator<BowlModificationDto> bmIterator =
		// bowlModificationList.iterator();
		// BowlModificationDto bmDto = null;
		// while (bmIterator.hasNext()) {
		// bmDto = bmIterator.next();
		// logger.info("BowlController.modifyBowl -> BowlModification: " +
		// bmDto.asString());
		// }

		result.render("bowlModifications", bowlModificationList);

		return result;
	}

	/**
	 * Handles the filtering and sorting of a {@code Bowl} portfolio.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @return {@code Result} instance
	 */
	public Result portfolioBowl(Session session, Context context, @Param("bowlId") String bowlId) {
		if (logger.isInfoEnabled()) {
			logger.info("BowlController.portfolioBowl -> SessionId......: " + session.getId());
			logger.info("BowlController.portfolioBowl -> Session.isEmpty: " + session.isEmpty());
			logger.info("BowlController.portfolioBowl -> BowlId.........: " + bowlId);
		}

		Map<String, Object> map = new HashMap<String, Object>();

		Result result = Results.html();
		result.render(map);

		return result;
	}

	/**
	 * Handles the registration of a new {@code Bowl}.
	 * <p>
	 * Note that by default the selected {@code GeoRegion} will be
	 * <em>Europe</em> with an index value of <em>7</em> and the default value
	 * of <em>8</em> as the {@code GeoRegion} identifier of european timber in
	 * the {@code Timber} table of the data source.
	 * <p>
	 * The {@code selectedStatusId} refers to the default {@code Status} if the
	 * template <em>registerBowl</em> is first called.
	 * <p>
	 * The {@code selectedTimberId} refers to the default {@code Timber} if the
	 * template <em>registerBowl</em> is first called.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code Bowl} table has entries
	 * @param bowlMaxOrdinal
	 *            the current maximum ordinal number of persisted bowl
	 *            incremented by one (1)
	 * @param geoRegionCode
	 *            the query parameter for a {@code GeoRegion}
	 * @param timberCode
	 *            the query parameter for a {@code Timber}
	 * @param statusCode
	 *            the query parameter for a {@code Status}
	 * @param timberOriginId
	 *            the query parameter for a {@code TimberOrigin}
	 * @param manufactureId
	 *            the query parameter for a {@code Manufacture}
	 * @return {@code Result} instance
	 */
	public Result registerBowl(Session session, Context context, @Param("emptyTable") Boolean emptyTable,
			@Param("bowlMaxOrdinal") String bowlMaxOrdinal, @Param("geoRegionCode") String geoRegionCode,
			@Param("timberCode") String timberCode, @Param("statusCode") String statusCode,
			@Param("timberOriginId") String timberOriginId, @Param("manufactureId") String manufactureId) {
		logger.info("BowlController.registerBowl -> Session Id......: " + session.getId());
		logger.info("BowlController.registerBowl -> Empty Table.....: " + emptyTable.toString());
		logger.info("BowlController.registerBowl -> Bowl Max Ordinal: " + bowlMaxOrdinal);
		logger.info("BowlController.registerBowl -> GeoRegion Code..: " + geoRegionCode);
		logger.info("BowlController.registerBowl -> Timber Code.....: " + timberCode);
		logger.info("BowlController.registerBowl -> Status Code.....: " + statusCode);
		logger.info("BowlController.registerBowl -> TimberOrigin Id.: " + timberOriginId);
		logger.info("BowlController.registerBowl -> Manufacture Id..: " + manufactureId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bEmptyTable", emptyTable.toString());

		List<GeoRegionDto> lGeoRegionList = geoRegionService.listGeoRegions();
		List<ManufactureDto> lManufactureList = null;
		List<StatusDto> lStatusList = null;
		List<TimberDto> lTimberList = null;
		List<TimberOriginDto> lTimberOriginList = null;

		GeoRegionDto lGeoRegionDto = null;
		ManufactureDto lManufactureDto = null;
		StatusDto lStatusDto = null;
		TimberDto lTimberDto = null;
		TimberOriginDto lTimberOriginDto = null;

		Long lSelectedGeoRegionId = null;
		String lSelectedGeoRegionCode = null;
		Long lSelectedManufactureId = null;
		String lSelectedManufactureYear = null;
		Long lSelectedStatusId = null;
		String lSelectedStatusCode = null;
		Long lSelectedTimberId = null;
		String lSelectedTimberCode = null;
		Long lSelectedTimberOriginId = null;
		String lSelectedTimberOriginName = null;

		Integer lBowlMaxIndex = null;
		Integer lBowlMaxOrdinal = null;

		Integer lGeoRegionIndex = null;
		Integer lManufactureIndex = null;
		Integer lStatusIndex = null;
		Integer lTimberIndex = null;
		Integer lTimberOriginIndex = null;

		String lTimberCode = null;
		String lTimberName = null;
		String lTimberOriginName = null;

		if (emptyTable) {
			lBowlMaxIndex = ZERO;
			lBowlMaxOrdinal = ONE;
			//
			lManufactureList = manufactureService.listManufacture();
			lStatusList = statusService.listStatus();
			lTimberList = timberService.listTimber();
			lTimberOriginList = timberOriginService.listTimberOrigin();
			//
			if (geoRegionCode != null && !geoRegionCode.isEmpty()) {
				lGeoRegionDto = geoRegionService.getGeoRegionByCode(geoRegionCode);
				lTimberList = timberService.listTimberByGeoRegionCode(geoRegionCode);
			} else {
				lGeoRegionDto = geoRegionService.getGeoRegionByCode(DEFAULT_GEOREGION_CODE);
				lTimberList = timberService.listTimberByGeoRegionCode(DEFAULT_GEOREGION_CODE);
			}
			//
			if (manufactureId != null && !manufactureId.isEmpty()) {
				lManufactureDto = manufactureService.getManufactureById(new Long(manufactureId));
			} else {
				lManufactureDto = manufactureService.getManufactureById(DEFAULT_MANUFACTURE_ID);
			}
			//
			if (statusCode != null && !statusCode.isEmpty()) {
				lStatusDto = statusService.getStatusByCode(statusCode);
			} else {
				lStatusDto = statusService.getStatusByCode(DEFAULT_STATUS_CODE);
			}
			//
			if (timberCode != null && !timberCode.isEmpty()) {
				lTimberDto = timberService.getTimberByCode(timberCode);
			} else {
				lTimberDto = timberService.getTimberByCode(DEFAULT_TIMBER_CODE);
			}
			//
			if (lTimberOriginList != null && lTimberOriginList.size() > ZERO) {
				if (timberOriginId != null && !timberOriginId.isEmpty()) {
					lTimberOriginDto = timberOriginService.getTimberOriginById(new Long(timberOriginId));
				} else {
					lTimberOriginDto = timberOriginService.getTimberOriginById(DEFAULT_TIMBER_ORIGIN_ID);
				}
			}
			//
			lSelectedGeoRegionId = lGeoRegionDto.getId();
			lSelectedGeoRegionCode = lGeoRegionDto.getCode();
			lSelectedManufactureId = lManufactureDto.getId();
			lSelectedManufactureYear = lManufactureDto.getYear();
			lSelectedStatusId = lStatusDto.getId();
			lSelectedStatusCode = lStatusDto.getCode();
			lSelectedTimberId = lTimberDto.getId();
			lSelectedTimberCode = lTimberDto.getCode();
			//
			lGeoRegionIndex = lGeoRegionDto.getIndex();
			lManufactureIndex = lManufactureDto.getIndex();
			lStatusIndex = lStatusDto.getIndex();
			lTimberIndex = lTimberDto.getIndex();
			//
			lTimberCode = lTimberDto.getCode();
			lTimberName = lTimberDto.getName();
			//
			logger.info("BowlController.registerBowl -> lTimberOriginDto: " + String.valueOf(lTimberOriginDto));
			if (lTimberOriginDto != null) {
				lSelectedTimberOriginId = lTimberOriginDto.getId();
				lSelectedTimberOriginName = lTimberOriginDto.getCompositeName();
				lTimberOriginIndex = lTimberOriginDto.getIndex();
				lTimberOriginName = lTimberOriginDto.getCompositeName();
			} else {
				lSelectedTimberOriginId = 0L;
				lSelectedTimberOriginName = "Unknown";
				lTimberOriginIndex = 0;
				lTimberOriginName = "Unknown";
			}
			logger.info("BowlController.registerBowl -> lSelectedTimberOriginId..: "
					+ String.valueOf(lSelectedTimberOriginId));
			logger.info("BowlController.registerBowl -> lSelectedTimberOriginName: "
					+ String.valueOf(lSelectedTimberOriginName));
			logger.info(
					"BowlController.registerBowl -> lTimberOriginIndex.......: " + String.valueOf(lTimberOriginIndex));
			logger.info(
					"BowlController.registerBowl -> lTimberOriginName........: " + String.valueOf(lTimberOriginName));
		} else {
			lBowlMaxIndex = bowlService.getBowlMaxIndexAsInteger();
			logger.info("BowlController.registerBowl lBowlMaxIndex -> {}", lBowlMaxIndex);
			++lBowlMaxIndex;
			logger.info("BowlController.registerBowl lBowlMaxIndex -> {}", lBowlMaxIndex);
			//
			lBowlMaxOrdinal = bowlService.getBowlMaxOrdinalAsInteger();
			logger.info("BowlController.registerBowl lBowlMaxOrdinal -> {}", lBowlMaxOrdinal);
			++lBowlMaxOrdinal;
			logger.info("BowlController.registerBowl lBowlMaxOrdinal -> {}", lBowlMaxOrdinal);
			//
			// lBowlDto = bowlService.getBowlMaxIndex();
			// logger.info("BowlController.registerBowl lBowlDto -> {}",
			// lBowlDto.asString());
			// lBowlMaxIndex = lBowlDto.getIndex() + 1;
			//
			// lBowlDto = bowlService.getBowlMaxOrdinal();
			// logger.info("BowlController.registerBowl lBowlDto -> {}",
			// lBowlDto.asString());
			// lBowlMaxOrdinal = lBowlDto.getOrdinal() + 1;
			//
			lManufactureList = manufactureService.listManufacture();
			lStatusList = statusService.listStatus();
			lTimberOriginList = timberOriginService.listTimberOrigin();
			//
			if (geoRegionCode != null && !geoRegionCode.isEmpty()) {
				lGeoRegionDto = geoRegionService.getGeoRegionByCode(geoRegionCode);
				lTimberList = timberService.listTimberByGeoRegionCode(geoRegionCode);
			} else {
				lGeoRegionDto = geoRegionService.getGeoRegionByCode(DEFAULT_GEOREGION_CODE);
				lTimberList = timberService.listTimberByGeoRegionCode(DEFAULT_GEOREGION_CODE);
			}
			//
			if (timberCode != null && !timberCode.isEmpty()) {
				lTimberDto = timberService.getTimberByCode(timberCode);
			} else {
				lTimberDto = timberService.getTimberByCode(DEFAULT_TIMBER_CODE);
			}
			//
			if (statusCode != null && !statusCode.isEmpty()) {
				lStatusDto = statusService.getStatusByCode(statusCode);
			} else {
				lStatusDto = statusService.getStatusByCode(DEFAULT_STATUS_CODE);
			}
			//
			if (manufactureId != null && !manufactureId.isEmpty()) {
				lManufactureDto = manufactureService.getManufactureById(new Long(manufactureId));
			} else {
				lManufactureDto = manufactureService.getManufactureById(DEFAULT_MANUFACTURE_ID);
			}
			//
			if (lTimberOriginList != null && lTimberOriginList.size() > ZERO) {
				if (timberOriginId != null && !timberOriginId.isEmpty()) {
					lTimberOriginDto = timberOriginService.getTimberOriginById(new Long(timberOriginId));
				} else {
					lTimberOriginDto = timberOriginService.getTimberOriginById(DEFAULT_TIMBER_ORIGIN_ID);
				}
			}
			//
			lSelectedGeoRegionId = lGeoRegionDto.getId();
			lSelectedGeoRegionCode = lGeoRegionDto.getCode();
			lSelectedManufactureId = lManufactureDto.getId();
			lSelectedManufactureYear = lManufactureDto.getYear();
			lSelectedStatusId = lStatusDto.getId();
			lSelectedStatusCode = lStatusDto.getCode();
			lSelectedTimberId = lTimberDto.getId();
			lSelectedTimberCode = lTimberDto.getCode();
			//
			lGeoRegionIndex = lGeoRegionDto.getIndex();
			logger.info("BowlController.registerBowl lGeoRegionIndex -> {}", lGeoRegionIndex);
			lManufactureIndex = lManufactureDto.getIndex();
			logger.info("BowlController.registerBowl lManufactureIndex -> {}", lManufactureIndex);
			lStatusIndex = lStatusDto.getIndex();
			logger.info("BowlController.registerBowl lStatusIndex -> {}", lStatusIndex);
			lTimberIndex = lTimberDto.getIndex();
			logger.info("BowlController.registerBowl lTimberIndex -> {}", lTimberIndex);
			//
			lTimberCode = lTimberDto.getCode();
			lTimberName = lTimberDto.getName();
			//
			logger.info("BowlController.registerBowl lTimberOriginDto -> {}", String.valueOf(lTimberOriginDto));
			if (lTimberOriginDto != null) {
				lSelectedTimberOriginId = lTimberOriginDto.getId();
				lSelectedTimberOriginName = lTimberOriginDto.getCompositeName();
				lTimberOriginIndex = lTimberOriginDto.getIndex();
				lTimberOriginName = lTimberOriginDto.getCompositeName();
			} else {
				lSelectedTimberOriginId = 0L;
				lSelectedTimberOriginName = "Unknown";
				lTimberOriginIndex = 0;
				lTimberOriginName = "Unknown";
			}
			logger.info("BowlController.registerBowl lTimberOriginIndex -> {}", lTimberOriginIndex);
			logger.info("BowlController.registerBowl lTimberOriginName  -> {}", lTimberOriginName);
			logger.info("BowlController.registerBowl lSelectedTimberOriginId -> {}", String.valueOf(lSelectedTimberOriginId));
			logger.info("BowlController.registerBowl lSelectedTimberOriginName -> {}", String.valueOf(lSelectedTimberOriginName));
		}
		//
		map.put("geoRegionList", lGeoRegionList);
		map.put("manufactureList", lManufactureList);
		map.put("statusList", lStatusList);
		map.put("timberList", lTimberList);
		map.put("timberOriginList", lTimberOriginList);
		//
		map.put("bowlMaxIndex", lBowlMaxIndex);
		map.put("bowlMaxOrdinal", lBowlMaxOrdinal);
		//
		map.put("selectedGeoRegionId", lSelectedGeoRegionId);
		map.put("selectedGeoRegionCode", lSelectedGeoRegionCode);
		map.put("selectedManufactureId", lSelectedManufactureId);
		map.put("selectedManufactureYear", lSelectedManufactureYear);
		map.put("selectedStatusId", lSelectedStatusId);
		map.put("selectedStatusCode", lSelectedStatusCode);
		map.put("selectedTimberId", lSelectedTimberId);
		map.put("selectedTimberCode", lSelectedTimberCode);
		map.put("selectedTimberOriginId", lSelectedTimberOriginId);
		map.put("selectedTimberOriginName", SuperbowlHelper.replaceSpaceByMarker(lSelectedTimberOriginName));
		//
		map.put("geoRegionIndex", lGeoRegionIndex);
		map.put("manufactureIndex", lManufactureIndex);
		map.put("statusIndex", lStatusIndex);
		map.put("timberIndex", lTimberIndex);
		map.put("timberOriginIndex", lTimberOriginIndex);
		//
		map.put("timberCode", lTimberCode);
		map.put("timberName", lTimberName);
		map.put("timberOriginName", SuperbowlHelper.replaceSpaceByMarker(lTimberOriginName));

		BowlDto sessionBowlDto = getBowlDtoFromSession(session);

		if (sessionBowlDto != null) {
			logger.info(sessionBowlDto.asString());
			if (sessionBowlDto.getManufacture() != null) {
				map.put("bManufacture", sessionBowlDto.getManufacture());
				map.put("bYear", sessionBowlDto.getManufacture().getYear());
			} else {
				map.put("bManufacture", EMPTY);
				map.put("bYear", EMPTY);
			}
			if (sessionBowlDto.getStatus() != null) {
				map.put("bStatus", sessionBowlDto.getStatus());
			} else {
				map.put("bStatus", EMPTY);
			}
			if (sessionBowlDto.getTimber() != null) {
				map.put("bTimber", sessionBowlDto.getTimber());
			} else {
				map.put("bTimber", EMPTY);
			}
			if (sessionBowlDto.getTimberOrigin() != null) {
				map.put("bTimberOrigin", sessionBowlDto.getTimberOrigin());
			} else {
				map.put("bTimberOrigin", EMPTY);
			}
			if (sessionBowlDto.getId() != null) {
				map.put("bId", sessionBowlDto.getId());
			} else {
				map.put("bId", EMPTY);
			}
			if (sessionBowlDto.getVersion() != null) {
				map.put("bVersion", sessionBowlDto.getVersion());
			} else {
				map.put("bVersion", EMPTY);
			}
			if (sessionBowlDto.getIndex() != null) {
				map.put("bIndex", sessionBowlDto.getIndex());
			} else {
				map.put("bIndex", EMPTY);
			}
			if (sessionBowlDto.getOrdinal() != null) {
				map.put("bOrdinal", sessionBowlDto.getOrdinal());
			} else {
				map.put("bOrdinal", EMPTY);
			}
			if (sessionBowlDto.getImageName() != null) {
				map.put("bImageName", sessionBowlDto.getImageName());
			} else {
				map.put("bImageName", EMPTY);
			}
			if (sessionBowlDto.getPrice() != null) {
				map.put("bPrice", sessionBowlDto.getPrice());
			} else {
				map.put("bPrice", EMPTY);
			}
			if (sessionBowlDto.getCent() != null) {
				map.put("bCent", sessionBowlDto.getCent());
			} else {
				map.put("bCent", EMPTY);
			}
			if (sessionBowlDto.getSalesPrice() != null) {
				map.put("bSalesPrice", sessionBowlDto.getSalesPrice());
			} else {
				map.put("bSalesPrice", EMPTY);
			}
			if (sessionBowlDto.getSalesCent() != null) {
				map.put("bSalesCent", sessionBowlDto.getSalesCent());
			} else {
				map.put("bSalesCent", EMPTY);
			}
			if (sessionBowlDto.getSalesLocation() != null) {
				map.put("bSalesLocation", sessionBowlDto.getSalesLocation());
			} else {
				map.put("bSalesLocation", EMPTY);
			}
			if (sessionBowlDto.getSalesDate() != null) {
				map.put("bSalesDate", sessionBowlDto.getSalesDate());
			} else {
				map.put("bSalesDate", EMPTY);
			}
			if (sessionBowlDto.getComment() != null) {
				map.put("bComment", SuperbowlHelper.replaceSpaceByMarker(sessionBowlDto.getComment()));
			} else {
				map.put("bComment", EMPTY);
			}
		} else {
			map.put("bStatus", EMPTY);
			map.put("bTimber", EMPTY);
			map.put("bTimberOrigin", EMPTY);
			map.put("bId", EMPTY);
			map.put("bVersion", EMPTY);
			map.put("bIndex", lBowlMaxIndex.toString());
			map.put("bOrdinal", lBowlMaxOrdinal.toString());
			map.put("bImageName", EMPTY);
			map.put("bYear", EMPTY);
			map.put("bPrice", ZERO);
			map.put("bCent", ZERO);
			map.put("bSalesPrice", ZERO);
			map.put("bSalesCent", ZERO);
			map.put("bSalesLocation", ZERO);
			map.put("bSalesDate", ZERO);
			map.put("bComment", EMPTY);
		}

		return Results.html().render(map).template("views/BowlController/registerBowl.ftl.html");
	}

	/**
	 * Handles the registration of a new {@code Bowl}.
	 * <p>
	 * Note that by default the selected {@code GeoRegion} will be
	 * <em>Europe</em> with an index value of <em>7</em> and the default value
	 * of <em>8</em> as the {@code GeoRegion} identifier of european timber in
	 * the {@code Timber} table of the data source.
	 * <p>
	 * The {@code selectedStatusId} refers to the default {@code Status} if the
	 * template <em>registerBowl</em> is first called.
	 * <p>
	 * The {@code selectedTimberId} refers to the default {@code Timber} if the
	 * template <em>registerBowl</em> is first called.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param emptyTable
	 *            indicates whether the {@code Bowl} table has entries
	 * @return {@code Result} instance
	 */
	public Result registerBowlOld(Session session, Context context, @Param("emptyTable") Boolean emptyTable) {
		logger.info("BowlController.registerBowl -> Session id.: " + session.getId());
		logger.info("BowlController.registerBowl -> Empty Table: " + emptyTable.toString());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bEmptyTable", emptyTable.toString());

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		List<ManufactureDto> manufactureList = manufactureService.listManufacture();
		List<StatusDto> statusList = statusService.listStatus();
		List<TimberDto> timberList = timberService.listTimberByGeoRegionId(DEFAULT_GEOREGION_ID);

		map.put("geoRegionList", geoRegionList);
		map.put("manufactureList", manufactureList);
		map.put("statusList", statusList);
		map.put("timberList", timberList);

		BowlDto sessionBowlDto = null;
		BowlDto bowlDto = null;
		Integer bowlIndex = null;

		if (emptyTable) {
			bowlIndex = 0;
		} else {
			// Bowl max index ermitteln
			bowlDto = bowlService.getBowlMaxIndex();
			bowlIndex = bowlDto.getIndex() + 1;
		}

		Long selectGeoRegionId = geoRegionList.get(DEFAULT_GEOREGION_INDEX).getId();
		logger.info("BowlController.registerBowl -> GeoRegion id....: " + selectGeoRegionId);
		Long selectManufactureId = manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getId();
		logger.info("BowlController.registerBowl -> Manufacture id..: " + selectManufactureId);
		String selectManufactureYear = manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getYear();
		logger.info("BowlController.registerBowl -> Manufacture year: " + selectManufactureYear);
		Long selectStatusId = statusList.get(DEFAULT_STATUS_INDEX).getId();
		logger.info("BowlController.registerBowl -> Status id.......: " + selectStatusId);
		Long selectTimberId = timberList.get(DEFAULT_TIMBER_INDEX).getId();
		logger.info("BowlController.registerBowl -> Timber id.......: " + selectTimberId);

		bowlDto = bowlService.getBowlMaxOrdinal();
		logger.info("BowlController.registerBowl -> Bowl max ordinal: " + bowlDto.getOrdinal());
		// Add 1 to current max ordinal number
		bowlDto.setOrdinal(bowlDto.getOrdinal() + 1);
		// default geoRegion index ("6") for Europa
		map.put("geoRegionIndex", DEFAULT_GEOREGION_INDEX);
		// default manufacture index ("7") for 2017
		map.put("manufactureIndex", DEFAULT_MANUFACTURE_INDEX);
		// default status index ("1") for 'in Bearbeitung'
		map.put("statusIndex", DEFAULT_STATUS_INDEX);
		// default timber index ("7") for 'Apfelbaum'
		map.put("timberIndex", DEFAULT_TIMBER_INDEX);
		// default timber name ("Apfelbaum")
		map.put("timberName", DEFAULT_TIMBER_NAME);
		map.put("selectedGeoRegionId", selectGeoRegionId);
		map.put("selectedGeoRegionCode", DEFAULT_GEOREGION_CODE);
		map.put("selectedManufactureId", selectManufactureId);
		map.put("selectedManufactureYear", selectManufactureYear);
		map.put("selectedStatusId", selectStatusId);
		map.put("selectedStatusCode", DEFAULT_STATUS_CODE);
		map.put("selectedTimberId", selectTimberId);
		map.put("bowlMaxOrdinal", bowlDto.getOrdinal());

		sessionBowlDto = getBowlDtoFromSession(session);

		if (sessionBowlDto != null) {
			logger.info(sessionBowlDto.asString());
			map.put("bManufacture", sessionBowlDto.getManufacture());
			map.put("bStatus", sessionBowlDto.getStatus());
			map.put("bTimber", sessionBowlDto.getTimber());
			map.put("bId", sessionBowlDto.getId());
			map.put("bVersion", sessionBowlDto.getVersion());
			map.put("bIndex", sessionBowlDto.getIndex());
			map.put("bOrdinal", sessionBowlDto.getOrdinal());
			map.put("bImageName", sessionBowlDto.getImageName());
			map.put("bPrice", sessionBowlDto.getPrice());
			map.put("bCent", sessionBowlDto.getCent());
			map.put("bComment", helper.replaceSpaceByMarker(sessionBowlDto.getComment()));
		} else {
			map.put("bManufacture", EMPTY);
			map.put("bStatus", EMPTY);
			map.put("bTimber", EMPTY);
			map.put("bId", EMPTY);
			map.put("bVersion", EMPTY);
			map.put("bIndex", bowlIndex.toString());
			map.put("bOrdinal", sessionBowlDto.getOrdinal());
			map.put("bImageName", EMPTY);
			map.put("bPrice", ZERO);
			map.put("bCent", ZERO);
			map.put("bYear", EMPTY);
			map.put("bComment", EMPTY);
		}

		return Results.html().render(map).template("views/BowlController/registerBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param geoRegionCode
	 *            the query parameter for a {@code GeoRegion} code
	 * @param bowlMaxOrdinal
	 *            the current maximum ordinal number of persisted bowl
	 *            incremented by one (1)
	 * @return {@code Result} instance
	 */
	public Result registerBowlByGeoRegionCode(Session session, Context context,
			@Param("geoRegionCode") String geoRegionCode, @Param("bowlMaxOrdinal") String bowlMaxOrdinal) {
		logger.info("BowlController.registerBowlByGeoRegionCode -> GeoRegion code...: " + geoRegionCode);

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		List<ManufactureDto> manufactureList = manufactureService.listManufacture();
		List<StatusDto> statusList = statusService.listStatus();
		List<TimberDto> timberList = timberService.listTimberByGeoRegionCode(geoRegionCode);

		GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionByCode(geoRegionCode);
		logger.info("BowlController.registerBowlByGeoRegionCode -> GeoRegion id.....: " + geoRegionDto.getId());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("manufactureList", manufactureList);
		map.put("statusList", statusList);
		map.put("timberList", timberList);
		map.put("geoRegionList", geoRegionList);
		map.put("geoRegionIndex", geoRegionDto.getIndex());
		map.put("manufactureIndex", DEFAULT_MANUFACTURE_INDEX);
		map.put("statusIndex", DEFAULT_STATUS_INDEX);
		map.put("timberIndex", DEFAULT_TIMBER_INDEX);
		map.put("selectedGeoRegionId", geoRegionDto.getId());
		map.put("selectedGeoRegionCode", geoRegionDto.getCode());
		map.put("selectedManufactureId", manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getId());
		map.put("selectedManufactureYear", manufactureList.get(DEFAULT_MANUFACTURE_INDEX).getYear());
		map.put("selectedStatusId", statusList.get(DEFAULT_STATUS_INDEX).getId());
		map.put("selectedStatusCode", statusList.get(DEFAULT_STATUS_INDEX).getCode());
		map.put("selectedTimberId", timberList.get(DEFAULT_TIMBER_INDEX).getId());
		map.put("bowlMaxOrdinal", bowlMaxOrdinal);

		session.clear();

		return Results.html().render(map).template("views/BowlController/registerBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param manufactureId
	 *            the query parameter for a {@code Manufacture} year
	 * @param geoRegionCode
	 *            the selected {@code GeoRegion} identifier
	 * @param statusCode
	 *            the query parameter for a {@code Status} code
	 * @param timberId
	 *            the query parameter for a {@code Timber} id
	 * @param bowlMaxOrdinal
	 *            the current maximum ordinal number of persisted bowl
	 *            incremented by one (1)
	 * @return {@code Result} instance
	 */
	public Result registerBowlByManufactureYear(Session session, Context context,
			@Param("manufactureId") Long manufactureId, @Param("geoRegionCode") String geoRegionCode,
			@Param("statusCode") String statusCode, @Param("timberId") Long timberId,
			@Param("bowlMaxOrdinal") String bowlMaxOrdinal) {
		logger.info("BowlController.registerBowlByManufactureYear -> Manufacture id....: " + manufactureId);
		logger.info("BowlController.registerBowlByManufactureYear -> GeoRegion id......: " + geoRegionCode);
		logger.info("BowlController.registerBowlByManufactureYear -> Status code.......: " + statusCode);
		logger.info("BowlController.registerBowlByManufactureYear -> Timber id.........: " + timberId);

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		List<ManufactureDto> manufactureList = manufactureService.listManufacture();
		List<StatusDto> statusList = statusService.listStatus();
		List<TimberDto> timberList = timberService.listTimberByGeoRegionCode(geoRegionCode);

		GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionByCode(geoRegionCode);
		logger.info("BowlController.registerBowlByManufactureYear -> GeoRegion index...: " + geoRegionDto.getIndex());

		ManufactureDto manufactureDto = manufactureService.getManufactureById(manufactureId);
		logger.info("BowlController.registerBowlByManufactureYear -> Manufacture index.: " + manufactureDto.getIndex());

		StatusDto statusDto = statusService.getStatusByCode(statusCode);
		logger.info("BowlController.registerBowlByManufactureYear -> Status id.........: " + statusDto.getId());
		logger.info("BowlController.registerBowlByManufactureYear -> Status index......: " + statusDto.getIndex());

		TimberDto timberDto = timberService.getTimberById(timberId);
		logger.info("BowlController.registerBowlByManufactureYear -> Timber id.........: " + timberDto.getId());
		logger.info("BowlController.registerBowlByManufactureYear -> Timber code.......: " + timberDto.getCode());
		logger.info("BowlController.registerBowlByManufactureYear -> Timber name.......: " + timberDto.getName());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("geoRegionList", geoRegionList);
		map.put("manufactureList", manufactureList);
		map.put("statusList", statusList);
		map.put("timberList", timberList);
		map.put("geoRegionIndex", geoRegionDto.getIndex());
		map.put("manufactureIndex", manufactureDto.getIndex());
		map.put("statusIndex", statusDto.getIndex());
		map.put("timberIndex", timberDto.getIndex());
		map.put("selectedGeoRegionId", geoRegionDto.getId());
		map.put("selectedGeoRegionCode", geoRegionDto.getCode());
		map.put("selectedManufactureId", manufactureDto.getId());
		map.put("selectedManufactureYear", manufactureDto.getYear());
		map.put("selectedStatusId", statusDto.getId());
		map.put("selectedStatusCode", statusDto.getCode());
		map.put("selectedTimberId", timberDto.getId());
		map.put("selectedTimberCode", timberDto.getCode());
		map.put("bowlMaxOrdinal", bowlMaxOrdinal);

		session.clear();

		return Results.html().render(map).template("views/BowlController/registerBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param statusCode
	 *            the query parameter for a {@code Status} code
	 * @param geoRegionId
	 *            the selected {@code GeoRegion} identifier
	 * @param manufactureId
	 *            the query parameter for a {@code Manufacture} id
	 * @param timberId
	 *            the query parameter for a {@code Timber} id
	 * @param bowlMaxOrdinal
	 *            the current maximum ordinal number of persisted bowl
	 *            incremented by one (1)
	 * @return {@code Result} instance
	 */
	public Result registerBowlByStatusCode(Session session, Context context, @Param("statusCode") String statusCode,
			@Param("geoRegionId") Long geoRegionId, @Param("manufactureId") Long manufactureId,
			@Param("timberId") Long timberId, @Param("bowlMaxOrdinal") String bowlMaxOrdinal) {
		logger.info("BowlController.registerBowlByStatusCode -> Status code......: " + statusCode);
		logger.info("BowlController.registerBowlByStatusCode -> GeoRegion id.....: " + geoRegionId);
		logger.info("BowlController.registerBowlByStatusCode -> Manufacture id...: " + manufactureId);
		logger.info("BowlController.registerBowlByStatusCode -> Timber id........: " + timberId);

		GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionById(geoRegionId);
		logger.info("BowlController.registerBowlByStatusCode -> GeoRegion index..: " + geoRegionDto.getIndex());
		logger.info("BowlController.registerBowlByStatusCode -> GeoRegion code...: " + geoRegionDto.getCode());

		ManufactureDto manufactureDto = manufactureService.getManufactureById(manufactureId);
		logger.info("BowlController.registerBowlByStatusCode -> Manufacture index: " + manufactureDto.getIndex());

		StatusDto statusDto = statusService.getStatusByCode(statusCode);
		logger.info("BowlController.registerBowlByStatusCode -> Status id........: " + statusDto.getId());
		logger.info("BowlController.registerBowlByStatusCode -> Status index.....: " + statusDto.getIndex());

		TimberDto timberDto = timberService.getTimberById(timberId);
		logger.info("BowlController.registerBowlByStatusCode -> Timber id........: " + timberDto.getId());
		logger.info("BowlController.registerBowlByStatusCode -> Timber code......: " + timberDto.getCode());
		logger.info("BowlController.registerBowlByStatusCode -> Timber name......: " + timberDto.getName());

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		List<ManufactureDto> manufactureList = manufactureService.listManufacture();
		List<StatusDto> statusList = statusService.listStatus();
		List<TimberDto> timberList = timberService.listTimberByGeoRegionCode(geoRegionDto.getCode());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("geoRegionList", geoRegionList);
		map.put("manufactureList", manufactureList);
		map.put("statusList", statusList);
		map.put("timberList", timberList);
		map.put("geoRegionIndex", geoRegionDto.getIndex());
		map.put("manufactureIndex", manufactureDto.getIndex());
		map.put("statusIndex", statusDto.getIndex());
		map.put("timberIndex", timberDto.getIndex());
		map.put("selectedGeoRegionId", geoRegionId);
		map.put("selectedGeoRegionCode", geoRegionDto.getCode());
		map.put("selectedManufactureId", manufactureDto.getId());
		map.put("selectedManufactureYear", manufactureDto.getYear());
		map.put("selectedStatusId", statusDto.getId());
		map.put("selectedStatusCode", statusDto.getCode());
		map.put("selectedTimberId", timberDto.getId());
		map.put("bowlMaxOrdinal", bowlMaxOrdinal);

		session.clear();

		return Results.html().render(map).template("views/BowlController/registerBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param timberCode
	 *            the query parameter for a {@code Timber} code
	 * @param geoRegionCode
	 *            the query parameter for a {@code GeoRegion} code
	 * @param manufactureId
	 *            the query parameter for a {@code Manufacture} id
	 * @param statusCode
	 *            the query parameter for a {@code Status} code
	 * @param bowlMaxOrdinal
	 *            the current maximum ordinal number of persisted bowl
	 *            incremented by one (1)
	 * @return {@code Result} instance
	 */
	public Result registerBowlByTimberCode(Session session, Context context, @Param("timberCode") String timberCode,
			@Param("geoRegionCode") String geoRegionCode, @Param("manufactureId") Long manufactureId,
			@Param("statusCode") String statusCode, @Param("bowlMaxOrdinal") String bowlMaxOrdinal) {
		logger.info("BowlController.registerBowlByTimberCode -> GeoRegion code...: " + geoRegionCode);
		logger.info("BowlController.registerBowlByTimberCode -> Manufacture id...: " + manufactureId);
		logger.info("BowlController.registerBowlByTimberCode -> Status code......: " + statusCode);
		logger.info("BowlController.registerBowlByTimberCode -> Timber code......: " + timberCode);

		List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
		List<ManufactureDto> manufactureList = manufactureService.listManufacture();
		List<StatusDto> statusList = statusService.listStatus();
		List<TimberDto> timberList = timberService.listTimberByGeoRegionCode(geoRegionCode);

		GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionByCode(geoRegionCode);
		logger.info("BowlController.registerBowlByTimberCode -> GeoRegion code...: " + geoRegionDto.getCode());

		ManufactureDto manufactureDto = manufactureService.getManufactureById(manufactureId);
		logger.info("BowlController.registerBowlByTimberCode -> Manufacture index: " + manufactureDto.getIndex());

		TimberDto timberDto = timberService.getTimberByCode(timberCode);
		logger.info("BowlController.registerBowlByTimberCode -> Timber id........: " + timberDto.getId());
		logger.info("BowlController.registerBowlByTimberCode -> Timber name......: " + timberDto.getName());

		StatusDto statusDto = statusService.getStatusByCode(statusCode);
		logger.info("BowlController.registerBowlByStatusCode -> Status text......: " + statusDto.getText());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("manufactureList", manufactureList);
		map.put("statusList", statusList);
		map.put("timberList", timberList);
		map.put("geoRegionList", geoRegionList);
		map.put("geoRegionIndex", geoRegionDto.getIndex());
		map.put("manufactureIndex", manufactureDto.getIndex());
		map.put("statusIndex", statusDto.getIndex());
		map.put("timberIndex", timberDto.getIndex());
		map.put("selectedGeoRegionId", geoRegionDto.getId());
		map.put("selectedGeoRegionCode", geoRegionDto.getCode());
		map.put("selectedManufactureId", manufactureDto.getId());
		map.put("selectedManufactureYear", manufactureDto.getYear());
		map.put("selectedStatusId", statusDto.getId());
		map.put("selectedStatusCode", statusDto.getCode());
		map.put("selectedTimberId", timberDto.getId());
		map.put("bowlMaxOrdinal", bowlMaxOrdinal);

		session.clear();

		return Results.html().render(map).template("views/BowlController/registerBowl.ftl.html");
	}

	/**
	 * Insert method description here...
	 *
	 * @param bowlForm
	 *            the {@code BowlForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerBowlConfirmation(@JSR303Validation BowlForm bowlForm, Session session, Validation validation,
			Context context) {

		logger.info("BowlController.registerBowlConfirmation -> Session id...: " + session.getId());
		logger.info("BowlController.registerBowlConfirmation -> Session empty: " + session.isEmpty());
		logger.info("BowlController.registerBowlConfirmation -> Context......: " + context.getRequestPath());
		logger.info("BowlController.registerBowlConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("===== Register Bowl Confirmation =====");
		logger.info("BowlController:BowlForm -> GeoRegionId.....: " + bowlForm.getGeoRegionId());
		logger.info("BowlController:BowlForm -> ManufactureId...: " + bowlForm.getManufactureId());
		logger.info("BowlController:BowlForm -> StatusId........: " + bowlForm.getStatusId());
		logger.info("BowlController:BowlForm -> TimberId........: " + bowlForm.getTimberId());
		logger.info("BowlController:BowlForm -> TimberOriginId..: " + bowlForm.getTimberOriginId());
		logger.info("BowlController:BowlForm -> TimberOriginName: " + bowlForm.getTimberOriginName());
		logger.info("BowlController:BowlForm -> Ordinal.........: " + bowlForm.getOrdinal());
		logger.info("BowlController:BowlForm -> Price...........: " + bowlForm.getPrice());
		logger.info("BowlController:BowlForm -> Cent............: " + bowlForm.getCent());
		logger.info("BowlController:BowlForm -> SalesPrice......: " + bowlForm.getSalesPrice());
		logger.info("BowlController:BowlForm -> SalesLocation...: " + bowlForm.getSalesLocation());
		logger.info("BowlController:BowlForm -> SalesDate.......: " + bowlForm.getSalesDate());
		logger.info("BowlController:BowlForm -> Comment.........: " + bowlForm.getComment());
		logger.info("BowlController:BowlForm -> EmptyTable......: " + bowlForm.getEmptyTable());
		logger.info("=== End Register Bowl Confirmation ===");

		if (validation.hasViolations()) {

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("manufacture".equals(fieldViolation.getFieldKey())
						&& "{bowl.manufacture.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("status".equals(fieldViolation.getFieldKey())
						&& "{bowl.status.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("timber".equals(fieldViolation.getFieldKey())
						&& "{bowl.timber.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("timberOrigin".equals(fieldViolation.getFieldKey())
						&& "{bowl.timber.origin.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("ordinal".equals(fieldViolation.getFieldKey())
						&& "{bowl.ordinal.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("price".equals(fieldViolation.getFieldKey())
						&& "{bowl.price.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			BowlDto bowlDto = bowlService.getBowlMaxOrdinal();
			GeoRegionDto geoRegionDto = geoRegionService.getGeoRegionById(bowlForm.getGeoRegionId());
			ManufactureDto manufactureDto = manufactureService.getManufactureById(bowlForm.getId());
			StatusDto statusDto = statusService.getStatusById(bowlForm.getStatusId());
			TimberDto timberDto = timberService.getTimberById(bowlForm.getTimberId());
			TimberOriginDto timberOriginDto = timberOriginService
					.getTimberOriginById(new Long(bowlForm.getTimberOriginId()));

			List<GeoRegionDto> geoRegionList = geoRegionService.listGeoRegions();
			List<ManufactureDto> manufactureList = manufactureService.listManufacture();
			List<StatusDto> statusList = statusService.listStatus();
			List<TimberDto> timberList = timberService.listTimberByGeoRegionCode(geoRegionDto.getCode());
			List<TimberOriginDto> timberOriginList = timberOriginService.listTimberOrigin();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("manufactureList", manufactureList);
			map.put("statusList", statusList);
			map.put("timberList", timberList);
			map.put("timberOriginList", timberOriginList);
			map.put("geoRegionList", geoRegionList);
			map.put("geoRegionIndex", geoRegionDto.getIndex());
			map.put("manufactureId", manufactureDto.getId());
			map.put("manufactureIndex", manufactureDto.getIndex());
			map.put("manufactureYear", manufactureDto.getYear());
			map.put("statusIndex", statusDto.getIndex());
			map.put("timberIndex", timberDto.getIndex());
			map.put("timberOriginId", timberOriginDto.getId());
			map.put("timberOriginIndex", timberOriginDto.getIndex());
			map.put("timberOriginName", timberOriginDto.getTimberOriginName());
			map.put("selectedGeoRegionId", geoRegionDto.getId());
			map.put("selectedGeoRegionCode", geoRegionDto.getCode());
			map.put("selectedManufactureId", manufactureDto.getId());
			map.put("selectedStatusId", bowlForm.getStatusId());
			map.put("selectedStatusCode", statusDto.getCode());
			map.put("selectedTimberId", bowlForm.getTimberId());
			map.put("selectedTimberOriginId", bowlForm.getTimberOriginId());
			map.put("bowlMaxOrdinal", bowlDto.getOrdinal() + 1);
			map.put("bowlPrice", bowlForm.getPrice());
			map.put("bowlOrdinal", bowlForm.getOrdinal());

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("bowl", bowlForm);
			result.template("views/BowlController/registerBowl.ftl.html");

			session.save(context);

			return result;

		} else {

			ManufactureDto manufactureDto = manufactureService.getManufactureById(bowlForm.getManufactureId());
			StatusDto statusDto = statusService.getStatusById(bowlForm.getStatusId());
			TimberDto timberDto = timberService.getTimberById(bowlForm.getTimberId());
			TimberOriginDto timberOriginDto = timberOriginService.getTimberOriginById(bowlForm.getTimberOriginId());
			bowlForm.setStatusText(statusDto.getText());
			bowlForm.setTimberName(timberDto.getName());

			// Bowl in Session speichern
			putBowlFormIntoSession(bowlForm, session);

			Result result = Results.html();
			result.render("geoRegionId", bowlForm.getGeoRegionId());
			result.render("manufactureId", manufactureDto.getId());
			result.render("manufactureIndex", manufactureDto.getIndex());
			result.render("manufactureYear", manufactureDto.getYear());
			result.render("statusId", bowlForm.getStatusId());
			result.render("statusText", statusDto.getText());
			result.render("timberId", bowlForm.getTimberId());
			result.render("timberIndex", timberDto.getIndex());
			result.render("timberName", timberDto.getName());
			result.render("timberOriginId", bowlForm.getTimberOriginId());
			result.render("timberOriginIndex", timberOriginDto.getIndex());
			result.render("timberOriginName", timberOriginDto.getTimberOriginName());
			result.render("ordinal", bowlForm.getOrdinal());
			result.render("imageName", bowlForm.getImageName());
			result.render("price", bowlForm.getPrice());
			result.render("cent", bowlForm.getCent());
			result.render("salesPrice", bowlForm.getSalesPrice());
			result.render("salesLocation", bowlForm.getSalesLocation());
			result.render("salesDate", bowlForm.getSalesDate());
			result.render("comment", bowlForm.getComment());
			result.render("emptyTable", bowlForm.getEmptyTable());

			result.render("bowl", bowlForm);

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
	public Result registerBowlCompletion(Session session, Context context) {

		logger.info("BowlController.registerBowlCompletion -> Session id: " + session.getId());
		logger.info("BowlController.registerBowlCompletion -> Context: " + context.getRequestPath());

		BowlDto bowlDto = (BowlDto) getBowlDtoFromSession(session);

		bowlService.register(bowlDto);

		session.clear();

		return Results.ok().redirect("/superbowl/bowl");

	}

	/**
	 * Handles the registration of a new {@code BowlMod}.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @param bowlModStepId
	 *            the {@code BowlModStep} technical identifier
	 * @param bowlModStepIndex
	 *            the {@code BowlModStep} index
	 * @param bowlModStepCode
	 *            the {@code BowlModStep} code
	 * @param bowlModStepName
	 *            the {@code BowlModStep} name
	 * @return {@code Result} instance
	 */
	public Result registerBowlMod(Session session, Context context, @Param("bowlId") String bowlId,
			@Param("bowlModStepId") String bowlModStepId, @Param("bowlModStepIndex") String bowlModStepIndex,
			@Param("bowlModStepCode") String bowlModStepCode, @Param("bowlModStepName") String bowlModStepName) {
		logger.info("BowlController.registerBowlMod -> Session        id: " + session.getId());
		logger.info("BowlController.registerBowlMod -> Bowl           id: " + bowlId);
		logger.info("BowlController.registerBowlMod -> BowlModStepId....: " + bowlModStepId);
		logger.info("BowlController.registerBowlMod -> BowlModStepIndex.: " + bowlModStepIndex);
		logger.info("BowlController.registerBowlMod -> BowlModStepCode..: " + bowlModStepCode);
		logger.info("BowlController.registerBowlMod -> BowlModStepName..: " + bowlModStepName);

		List<BowlModStepDto> bowlModStepList = bowlService.listBowlModSteps();
		logger.info("BowlController.registerBowlMod -> BowlModSteps size: " + bowlModStepList.size());

		BowlDto bowlDto = bowlService.getBowlById(bowlId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bowlModStepList", bowlModStepList);
		map.put("bowlId", bowlId);
		map.put("bowlOrdinal", bowlDto.getOrdinal());

		BowlModStepDto bowlModStepDto = null;
		// BowlModDto bowlModDto = bowlService.getBowlModById(id);
		BowlModDto sessionBowlModDto = null;
		// Integer bowlModStepIndex = null;

		Long selectedBowlModStepId = null;
		Integer selectedBowlModStepIndex = null;
		String selectedBowlModStepCode = null;
		String selectedBowlModStepName = null;

		if (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModStepId)
				&& Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModStepIndex)
				&& Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModStepCode)
				&& Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModStepName)) {
			sessionBowlModDto = getBowlModDtoFromSession(session);

			bowlModStepDto = bowlService.getBowlModStepById(bowlModStepId);
			logger.info("BowlController.registerBowlMod -> BowlModStep index: " + bowlModStepDto.getIndex());

			selectedBowlModStepId = bowlModStepDto.getId();
			selectedBowlModStepIndex = bowlModStepDto.getIndex();
			selectedBowlModStepCode = bowlModStepDto.getCode();
			selectedBowlModStepName = bowlModStepDto.getName();

			map.put("bowlModStepId", bowlModStepId);
			map.put("bowlModStepIndex", bowlModStepIndex);
			map.put("bowlModStepName", SuperbowlHelper.replaceSpaceByMarker(bowlModStepName));
			map.put("bowlModStepCode", bowlModStepCode);

			// map.put("bowlModStepId", bowlModStepId);
			// map.put("bowlModStepIndex", bowlModStepDto.getIndex());
			// map.put("bowlModStepName", bowlModStepDto.getName());
			// map.put("bowlModStepCode", bowlModStepDto.getCode());

			map.put("selectedBowlId", bowlId);
			map.put("selectedBowlModStepId", selectedBowlModStepId);
			map.put("selectedBowlModStepIndex", selectedBowlModStepIndex);
			map.put("selectedBowlModStepName", SuperbowlHelper.replaceSpaceByMarker(selectedBowlModStepName));
			map.put("selectedBowlModStepCode", selectedBowlModStepCode);

			// map.put("selectedBowlId", bowlId);
			// map.put("selectedBowlModStepId", bowlModStepId);
			// map.put("selectedBowlModStepIndex", bowlModStepIndex);
			// map.put("selectedBowlModStepName", bowlModStepName);
			// map.put("selectedBowlModStepCode", bowlModStepCode);
		} else {
			selectedBowlModStepId = bowlModStepList.get(DEFAULT_BOWL_MODSTEP_INDEX).getId();
			selectedBowlModStepIndex = bowlModStepList.get(DEFAULT_BOWL_MODSTEP_INDEX).getIndex();
			selectedBowlModStepCode = bowlModStepList.get(DEFAULT_BOWL_MODSTEP_INDEX).getCode();
			selectedBowlModStepName = SuperbowlHelper
					.replaceSpaceByMarker(bowlModStepList.get(DEFAULT_BOWL_MODSTEP_INDEX).getName());

			map.put("bowlModStepId", selectedBowlModStepId);
			map.put("bowlModStepIndex", DEFAULT_BOWL_MODSTEP_INDEX);
			map.put("bowlModStepCode", selectedBowlModStepCode);
			map.put("bowlModStepName", selectedBowlModStepName);

			map.put("selectedBowlId", bowlId);
			map.put("selectedBowlModStepId", selectedBowlModStepId);
			map.put("selectedBowlModStepIndex", selectedBowlModStepIndex);
			map.put("selectedBowlModStepName", selectedBowlModStepName);
			map.put("selectedBowlModStepCode", selectedBowlModStepCode);
		}

		if (sessionBowlModDto != null) {
			logger.info(sessionBowlModDto.asString());
			if (sessionBowlModDto.getDate() != null) {
				map.put("bmDate", SuperbowlHelper.convertDate(sessionBowlModDto.getDate().toString()));
			} else {
				map.put("bmDate", EMPTY);
			}
			if (sessionBowlModDto.getDiameter() != null) {
				map.put("bmDiameter", sessionBowlModDto.getDiameter());
			} else {
				map.put("bmDiameter", EMPTY);
			}
			if (sessionBowlModDto.getHeight() != null) {
				map.put("bmHeight", sessionBowlModDto.getHeight());
			} else {
				map.put("bmHeight", EMPTY);
			}
			if (sessionBowlModDto.getWallthicknessMin() != null) {
				map.put("bmWallthicknessMin", sessionBowlModDto.getWallthicknessMin());
			} else {
				map.put("bmWallthicknessMin", EMPTY);
			}
			if (sessionBowlModDto.getWallthicknessMin() != null) {
				map.put("bmWallthicknessMax", sessionBowlModDto.getWallthicknessMin());
			} else {
				map.put("bmWallthicknessMax", EMPTY);
			}
			if (sessionBowlModDto.getGranulation() != null) {
				map.put("bmGranulation", sessionBowlModDto.getGranulation());
			} else {
				map.put("bmpGranulation", EMPTY);
			}
			if (sessionBowlModDto.getSurface() != null) {
				map.put("bmSurface", SuperbowlHelper.replaceSpaceByMarker(sessionBowlModDto.getSurface()));
			} else {
				map.put("bmSurface", EMPTY);
			}
			if (sessionBowlModDto.getTap() != null) {
				map.put("bmTap", sessionBowlModDto.getTap());
			} else {
				map.put("bmTap", EMPTY);
			}
			if (sessionBowlModDto.getRecess() != null) {
				map.put("bmRecess", sessionBowlModDto.getRecess());
			} else {
				map.put("bmRecess", EMPTY);
			}
			if (sessionBowlModDto.getComment() != null) {
				map.put("bmComment", SuperbowlHelper.replaceSpaceByMarker(sessionBowlModDto.getComment()));
			} else {
				map.put("bmComment", EMPTY);
			}
		} else {
			map.put("bmDate", EMPTY);
			map.put("bmDiameter", EMPTY);
			map.put("bmHeight", EMPTY);
			map.put("bmWallthicknessMin", EMPTY);
			map.put("bmWallthicknessMax", EMPTY);
			map.put("bmGranulation", EMPTY);
			map.put("bmSurface", EMPTY);
			map.put("bmTap", EMPTY);
			map.put("bmRecess", EMPTY);
			map.put("bmComment", EMPTY);
		}

		return Results.html().render(map).template("views/BowlController/registerBowlMod.ftl.html");
	}

	/**
	 * Handles the registration of a new {@code BowlMod}.
	 * 
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @param bowlModId
	 *            the unique technical identifier of a {@code BowlMod}
	 * @return {@code Result} instance
	 */
	public Result registerBowlModItem(Session session, Context context, @Param("bowlId") String bowlId,
			@Param("bowlModId") String bowlModId) {
		logger.info("BowlController.registerBowlModItem -> Session id: " + session.getId());
		logger.info("BowlController.registerBowlModItem -> Bowl    id: " + bowlId);
		logger.info("BowlController.registerBowlModItem -> BowlMod id: " + bowlModId);

		BowlDto bowlDto = bowlService.getBowlById(bowlId);
		logger.info("BowlController.registerBowlModItem -> Bowl          id: " + bowlDto.getId());
		logger.info("BowlController.registerBowlModItem -> Bowl     ordinal: " + bowlDto.getOrdinal());

		BowlModDto bowlModDto = bowlService.getBowlModById(bowlModId);
		logger.info("BowlController.registerBowlModItem -> BowlMod       id: " + bowlModDto.getId());
		logger.info("BowlController.registerBowlModItem -> BowlModStep name: " + bowlModDto.getBowlModStep().getName());

		Result result = Results.html();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("bowlId", bowlId);
		map.put("bowlModId", bowlModId);
		map.put("bowlModStepName", SuperbowlHelper.replaceSpaceByMarker(bowlModDto.getBowlModStep().getName()));
		map.put("bowlOrdinal", bowlDto.getOrdinal());
		map.put("bowlTimberName", bowlDto.getTimber().getName());

		BowlModItemDto sessionBowlModItemDto = getBowlModItemDtoFromSession(session);

		if (sessionBowlModItemDto != null) {
			logger.info(sessionBowlModItemDto.asString());
			if (sessionBowlModItemDto.getDate() != null) {
				map.put("bmiDate", SuperbowlHelper.convertDate(sessionBowlModItemDto.getDate().toString()));
			} else {
				map.put("bmiDate", EMPTY);
			}
			if (sessionBowlModItemDto.getWeight() != null) {
				map.put("bmiWeight", sessionBowlModItemDto.getWeight());
			} else {
				map.put("bmiWeight", EMPTY);
			}
			if (sessionBowlModItemDto.getMoisture() != null) {
				map.put("bmiMoisture", sessionBowlModItemDto.getMoisture());
			} else {
				map.put("bmiMoisture", EMPTY);
			}
			if (sessionBowlModItemDto.getText() != null) {
				map.put("bmiText", SuperbowlHelper.replaceSpaceByMarker(sessionBowlModItemDto.getText()));
			} else {
				map.put("bmiText", EMPTY);
			}
		} else {
			map.put("bmiDate", EMPTY);
			map.put("bmiWeight", EMPTY);
			map.put("bmiMoisture", EMPTY);
			map.put("bmiText", EMPTY);
		}

		return result.render(map);
	}

	/**
	 * Handles the registration of a new {@code BowlMod}.
	 * 
	 * @param bowlModForm
	 *            the {@code BowlModForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerBowlModConfirmation(@JSR303Validation BowlModForm bowlModForm, Session session,
			Validation validation, Context context) {
		logger.info("BowlController.registerBowlModConfirmation -> Session id...: " + session.getId());
		logger.info("BowlController.registerBowlModConfirmation -> Session empty: " + session.isEmpty());
		logger.info("BowlController.registerBowlModConfirmation -> Context......: " + context.getRequestPath());
		logger.info("BowlController.registerBowlModConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> BowlId...........: "
				+ bowlModForm.getBowlId());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> BowlModStepId....: "
				+ bowlModForm.getBowlModStepId());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> BowlModStepIndex.: "
				+ bowlModForm.getBowlModStepIndex());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> BowlModStepCode..: "
				+ bowlModForm.getBowlModStepCode());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> BowlModStepName..: "
				+ bowlModForm.getBowlModStepName());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Date.............: "
				+ bowlModForm.getDate());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Diameter.........: "
				+ bowlModForm.getDiameter());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Height...........: "
				+ bowlModForm.getHeight());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Wallthickness Min: "
				+ bowlModForm.getWallthicknessMin());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Wallthickness Max: "
				+ bowlModForm.getWallthicknessMax());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Granulation......: "
				+ bowlModForm.getGranulation());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Surface..........: "
				+ bowlModForm.getSurface());
		logger.info(
				"BowlController.registerBowlModConfirmation:BowlModForm -> Tap..............: " + bowlModForm.getTap());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Recess...........: "
				+ bowlModForm.getRecess());
		logger.info("BowlController.registerBowlModConfirmation:BowlModForm -> Comment..........: "
				+ bowlModForm.getComment());

		if (validation.hasViolations()) {
			logger.info(
					"BowlController.registerBowlModConfirmation -> Has violations...: " + validation.hasViolations());

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("date".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.date.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("diameter".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.diameter.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("height".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.height.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				/*
				if ("granulation".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.granulation.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				*/

				if ("wallthicknessMin".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.wallthickness.min.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("wallthicknessMax".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.wallthickness.max.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				/*
				if ("surface".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.surface.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("tap".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.tap.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("recess".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.recess.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}
				*/

			}

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();

			BowlDto bowlDto = null;

			String lBowlId = bowlModForm.getBowlId();

			if (lBowlId == null || lBowlId.isEmpty()) {
				map.put("bowlId", "");
				map.put("bowlOrdinal", 0);
			} else {
				bowlDto = bowlService.getBowlById(new Long(lBowlId));
				map.put("bowlId", bowlDto.getId());
				map.put("bowlOrdinal", bowlDto.getOrdinal());
			}

			map.put("bowlModStepId", bowlModForm.getBowlModStepId());
			map.put("bowlModStepIndex", bowlModForm.getBowlModStepIndex());
			map.put("bowlModStepCode", bowlModForm.getBowlModStepCode());
			map.put("bowlModStepName", bowlModForm.getBowlModStepName());

			map.put("selectedBowlModStepId", bowlModForm.getBowlModStepId());
			map.put("selectedBowlModStepIndex", bowlModForm.getBowlModStepIndex());
			map.put("selectedBowlModStepCode", bowlModForm.getBowlModStepCode());
			map.put("selectedBowlModStepName", SuperbowlHelper.replaceSpaceByMarker(bowlModForm.getBowlModStepName()));
			//
			map.put("bmDate", SuperbowlHelper.convertDate(bowlModForm.getDate()));
			map.put("bmDiameter", bowlModForm.getDiameter());
			map.put("bmHeight", bowlModForm.getHeight());
			map.put("bmWallthicknessMin", bowlModForm.getWallthicknessMin());
			map.put("bmWallthicknessMax", bowlModForm.getWallthicknessMax());
			map.put("bmGranulation", bowlModForm.getGranulation());
			map.put("bmSurface", SuperbowlHelper.replaceSpaceByMarker(bowlModForm.getSurface()));
			map.put("bmTap", bowlModForm.getTap());
			map.put("bmRecess", bowlModForm.getRecess());

			result.render(map);

			List<BowlModStepDto> bowlModStepList = bowlService.listBowlModSteps();

			result.render("bowlModStepList", bowlModStepList);

			result.render("violations", validation.getViolations());
			result.render("bowlMod", bowlModForm);
			result.template("views/BowlController/registerBowlMod.ftl.html");

			session.save(context);

			return result;

		} else {

			// BowlModStepDto bowlModStepDto =
			// bowlService.getBowlModStepById(new
			// Long(bowlModForm.getBowlModStepId()));
			// bowlModForm.setBowlModStepId(bowlModStepDto.getId().toString());
			// bowlModForm.setBowlModStepIndex(bowlModStepDto.getIndex().toString());
			// bowlModForm.setBowlModStepCode(bowlModStepDto.getCode());
			// bowlModForm.setBowlModStepName(bowlModStepDto.getName());

			// BowlMod form in Session speichern
			putBowlModFormIntoSession(bowlModForm, session);

			Result result = Results.html();
			result.render("bowlId", bowlModForm.getBowlId());
			result.render("bowlModStepId", bowlModForm.getBowlModStepId());
			result.render("bowlModStepIndex", bowlModForm.getBowlModStepIndex());
			result.render("bowlModStepCode", bowlModForm.getBowlModStepCode());
			result.render("bowlModStepName", bowlModForm.getBowlModStepName());
			result.render("comment", bowlModForm.getComment());
			result.render("date", bowlModForm.getDate());
			result.render("diameter", bowlModForm.getDiameter());
			result.render("granulation", bowlModForm.getGranulation());
			result.render("height", bowlModForm.getHeight());
			result.render("recess", bowlModForm.getRecess());
			result.render("surface", bowlModForm.getSurface());
			result.render("tap", bowlModForm.getTap());
			result.render("wallthicknessMin", bowlModForm.getWallthicknessMin());
			result.render("wallthicknessMax", bowlModForm.getWallthicknessMax());

			result.render("bowlMod", bowlModForm);

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
	public Result registerBowlModCompletion(Session session, Context context) {

		logger.info("BowlController.registerBowlModCompletion -> Session id: " + session.getId());
		logger.info("BowlController.registerBowlModCompletion -> Context   : " + context.getRequestPath());

		BowlModDto bowlModDto = (BowlModDto) getBowlModDtoFromSession(session);

		logger.info("BowlController.registerBowlModCompletion -> Bowl.........Id: " + bowlModDto.getBowl().getId());
		logger.info("BowlController.registerBowlModCompletion -> BowlMod......Id: " + bowlModDto.getId());
		logger.info("BowlController.registerBowlModCompletion -> BowlMod Version: " + bowlModDto.getVersion());
		logger.info(
				"BowlController.registerBowlModCompletion -> BowlModStep..Id: " + bowlModDto.getBowlModStep().getId());
		logger.info("BowlController.registerBowlModCompletion -> Date...........: " + bowlModDto.getDate());
		logger.info("BowlController.registerBowlModCompletion -> Diameter.......: " + bowlModDto.getDiameter());
		logger.info("BowlController.registerBowlModCompletion -> Height.........: " + bowlModDto.getHeight());
		logger.info("BowlController.registerBowlModCompletion -> Wall Min.......: " + bowlModDto.getWallthicknessMin());
		logger.info("BowlController.registerBowlModCompletion -> Wall Max.......: " + bowlModDto.getWallthicknessMax());
		logger.info("BowlController.registerBowlModCompletion -> Granulation....: " + bowlModDto.getGranulation());
		logger.info("BowlController.registerBowlModCompletion -> Surface........: " + bowlModDto.getSurface());
		logger.info("BowlController.registerBowlModCompletion -> Tap............: " + bowlModDto.getTap());
		logger.info("BowlController.registerBowlModCompletion -> Recess.........: " + bowlModDto.getRecess());
		logger.info("BowlController.registerBowlModCompletion -> Comment........: " + bowlModDto.getComment());

		bowlService.register(bowlModDto);

		session.clear();

		Long bowlId = bowlModDto.getBowl().getId();

		return Results.ok().redirect("/superbowl/modifyBowl?bowlId=" + bowlId);
		// return Results.ok().redirect("/superbowl/bowl");

	}

	/**
	 * Handles the registration of a new {@code BowlModItem}.
	 * 
	 * @param bowlModItemForm
	 *            the {@code BowlModItemForm} instance
	 * @param session
	 *            the {@code Ninja Session} instance
	 * @param validation
	 *            the {@code Ninja Validation} instance
	 * @param context
	 *            the {@code Ninja Context} instance
	 * @return {@code Result} instance
	 */
	public Result registerBowlModItemConfirmation(@JSR303Validation BowlModItemForm bowlModItemForm, Session session,
			Validation validation, Context context) {
		logger.info("BowlController.registerBowlModItemConfirmation -> Session id...: " + session.getId());
		logger.info("BowlController.registerBowlModItemConfirmation -> Session empty: " + session.isEmpty());
		logger.info("BowlController.registerBowlModItemConfirmation -> Context......: " + context.getRequestPath());
		logger.info("BowlController.registerBowlModItemConfirmation -> Violations...: " + validation.hasViolations());

		logger.info("BowlController.registerBowlModItemConfirmation:BowlModItemForm -> BowlId...........: "
				+ bowlModItemForm.getBowlId());
		logger.info("BowlController.registerBowlModItemConfirmation:BowlModItemForm -> BowlModId........: "
				+ bowlModItemForm.getBowlModId());
		logger.info("BowlController.registerBowlModItemConfirmation:BowlModItemForm -> Date.............: "
				+ bowlModItemForm.getDate());
		logger.info("BowlController.registerBowlModItemConfirmation:BowlModItemForm -> Weight...........: "
				+ bowlModItemForm.getWeight());
		logger.info("BowlController.registerBowlModItemConfirmation:BowlModItemForm -> Moisture.........: "
				+ bowlModItemForm.getMoisture());
		logger.info("BowlController.registerBowlModItemConfirmation:BowlModItemForm -> Text.............: "
				+ bowlModItemForm.getText());

		if (validation.hasViolations()) {
			logger.info("BowlController.registerBowlModItemConfirmation -> Has violations...: "
					+ validation.hasViolations());

			for (ConstraintViolation fieldViolation : validation.getViolations()) {

				logger.error("fieldViolation.field: {}", fieldViolation.getFieldKey());
				logger.error("fieldViolation.constraintViolation.getMessageKey: {}", fieldViolation.getMessageKey());

				if ("date".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.item.date.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("weight".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.item.weight.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

				if ("moisture".equals(fieldViolation.getFieldKey())
						&& "{bowl.mod.item.moisture.blank}".equals(fieldViolation.getMessageKey())) {
					throw new BadRequestException();
				}

			}

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();

			BowlModDto bowlModDto = null;
			String bowlModId = bowlModItemForm.getBowlModId();

			if (bowlModId == null) {
				map.put("bowlModId", "");
				map.put("bowlOrdinal", 0);
			} else {
				map.put("bowlModId", bowlModId);
				bowlModDto = bowlService.getBowlModById(new Long(bowlModId));
				map.put("bowlOrdinal", bowlModDto.getBowl().getOrdinal());
			}
			map.put("bowlId", bowlModItemForm.getBowlId());
			map.put("bowlModStepName", bowlModItemForm.getBowlModStepName());
			map.put("bowlTimberName", bowlModItemForm.getBowlTimberName());

			map.put("bmiDate", bowlModItemForm.getDate());
			map.put("bmiWeight", bowlModItemForm.getWeight());
			map.put("bmiMoisture", bowlModItemForm.getMoisture());
			map.put("bmiText", SuperbowlHelper.replaceSpaceByMarker(bowlModItemForm.getText()));

			result.render(map);

			result.render("violations", validation.getViolations());
			result.render("bowlModItem", bowlModItemForm);
			result.template("views/BowlController/registerBowlModItem.ftl.html");
			// result.template("views/BowlController/registerBowlModItem.ftl.html?bowlId="
			// + bowlModItemForm.getBowlId()
			// + "&bowlModId=" + bowlModItemForm.getBowlModId());

			session.save(context);

			return result;

		} else {

			putBowlModItemFormIntoSession(bowlModItemForm, session);

			Result result = Results.html();

			Map<String, Object> map = new HashMap<String, Object>();

			BowlModDto bowlModDto = null;
			String bowlModId = bowlModItemForm.getBowlModId();

			if (bowlModId == null) {
				map.put("bowlModId", "");
				map.put("bowlOrdinal", 0);
			} else {
				map.put("bowlModId", bowlModId);
				bowlModDto = bowlService.getBowlModById(new Long(bowlModId));
				map.put("bowlOrdinal", bowlModDto.getBowl().getOrdinal());
			}

			map.put("bmiDate", bowlModItemForm.getDate());
			map.put("bmiWeight", bowlModItemForm.getWeight());
			map.put("bmiMoisture", bowlModItemForm.getMoisture());
			map.put("bmiText", SuperbowlHelper.replaceSpaceByMarker(bowlModItemForm.getText()));
			map.put("bowlId", bowlModItemForm.getBowlId());
			map.put("bowlModStepName", bowlModItemForm.getBowlModStepName());
			map.put("bowlTimberName", bowlModItemForm.getBowlTimberName());

			result.render(map);

			result.render("bowlModItem", bowlModItemForm);

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
	public Result registerBowlModItemCompletion(Session session, Context context) {

		logger.info("BowlController.registerBowlModItemCompletion -> Session Id {} ", session.getId());
		logger.info("BowlController.registerBowlModItemCompletion -> Context {} ", context.getRequestPath());

		BowlModItemDto bowlModItemDto = (BowlModItemDto) getBowlModItemDtoFromSession(session);

		BowlMod bowlMod = bowlModItemDto.getBowlMod();

		if (bowlMod == null) {
			logger.info("BowlController.registerBowlModItemCompletion -> BowlMod Id {} ", "null");
		} else {
			logger.info("BowlController.registerBowlModItemCompletion -> BowlMod Id {} ", bowlMod.getId());
		}
		logger.info("BowlController.registerBowlModItemCompletion -> BowlModItem Id {} ", bowlModItemDto.getId());
		logger.info("BowlController.registerBowlModItemCompletion -> BowlModItem Version {} ",
				bowlModItemDto.getVersion());
		logger.info("BowlController.registerBowlModItemCompletion -> Date {} ", bowlModItemDto.getDate());
		logger.info("BowlController.registerBowlModItemCompletion -> Weight {} ", bowlModItemDto.getWeight());
		logger.info("BowlController.registerBowlModItemCompletion -> Moisture {} ", bowlModItemDto.getMoisture());
		logger.info("BowlController.registerBowlModItemCompletion -> Text {} ", bowlModItemDto.getText());

		bowlService.register(bowlModItemDto);

		session.clear();

		Long bowlId = bowlModItemDto.getBowlMod().getBowl().getId();
		Long bowlModId = bowlModItemDto.getBowlMod().getId();

		return Results.ok().redirect("/superbowl/modifyBowl?bowlId=" + bowlId + "&bowlModId=" + bowlModId);
		// return Results.ok().redirect("/superbowl/bowl");

	}

}
