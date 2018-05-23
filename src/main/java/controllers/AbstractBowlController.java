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
package controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;

import dto.BowlDto;
import dto.BowlModDto;
import dto.BowlModItemDto;
import dto.BowlModStepDto;
import entity.Bowl;
import entity.BowlMod;
import entity.BowlModStep;
import entity.Customer;
import entity.Exhibition;
import entity.Manufacture;
import entity.Status;
import entity.Timber;
import entity.TimberOrigin;
import forms.BowlForm;
import forms.BowlModForm;
import forms.BowlModItemForm;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.session.Session;
import services.BowlService;
import services.CustomerService;
import services.ExhibitionService;
import services.GeoRegionService;
import services.ManufactureService;
import services.StatusService;
import services.TimberOriginService;
import services.TimberService;
import types.Checker;
import types.SuperbowlHelper;
import types.Whitespace;

/**
 * Common abstract {@code IController} for {@code Bowl} controllers.
 *
 * @author mbsusr01
 *
 *         ninja-superbowl 19.04.2017 mbsusr01
 */
public abstract class AbstractBowlController extends AbstractController implements IBowlController {

	/**
	 * This is the {@code Bowl} service
	 */
	@Inject
	BowlService bowlService;

	/**
	 * This is the {@code Customer} service
	 */
	@Inject
	CustomerService customerService;

	/**
	 * This is the {@code Exhibition} service
	 */
	@Inject
	ExhibitionService exhibitionService;

	/**
	 * This is the {@code GeoRegion} service
	 */
	@Inject
	GeoRegionService geoRegionService;

	/**
	 * This is the {@code Manufacture} service
	 */
	@Inject
	ManufactureService manufactureService;

	/**
	 * This is the {@code Status} service
	 */
	@Inject
	StatusService statusService;

	/**
	 * This is the {@code Timber} service
	 */
	@Inject
	TimberService timberService;

	/**
	 * This is the {@code TimberOrigin} service
	 */
	@Inject
	TimberOriginService timberOriginService;

	/**
	 * Default not available value -> value is 'N.A.'
	 */
	static final String NOT_AVAILABLE = "N.A.";

	/**
	 * Default empty value -> value is ''
	 */
	static final String EMPTY = "";

	/**
	 * Default numeric value of '0'
	 */
	static final Integer ZERO = 0;

	/**
	 * Default numeric value of '1'
	 */
	static final Integer ONE = 1;

	/**
	 * Default numeric value of '-1'
	 */
	static final Integer MINUS_ONE = -1;

	/**
	 * Default {@code BowlModSTep} index for {@code Vorgedreht} -> value is '0'
	 */
	static final Integer DEFAULT_BOWL_MODSTEP_INDEX = 0;

	/**
	 * Default {@code GeoRegion} identifier for european timber -> value is '10'
	 */
	static final Long DEFAULT_GEOREGION_ID = 10L;

	/**
	 * Default {@code GeoRegion} index for {@code Europe} -> value is '9'
	 */
	static final Integer DEFAULT_GEOREGION_INDEX = 9;

	/**
	 * Default {@code GeoRegion} code for {@code Europe} -> value is 'EU'
	 */
	static final String DEFAULT_GEOREGION_CODE = "EU";

	/**
	 * Default {@code Manufacture} index for {@code Manufacture} year -> value
	 * is '7'
	 */
	static final Integer DEFAULT_MANUFACTURE_INDEX = 7;

	/**
	 * Default {@code Manufacture} identifier for {@code Manufacture} year ->
	 * value is '8'
	 */
	static final Long DEFAULT_MANUFACTURE_ID = 8L;

	/**
	 * Default {@code Status} index for selection of {@code Status} <em>in
	 * Bearbeitung</em> in a selection box -> value is '1'
	 */
	static final Integer DEFAULT_STATUS_INDEX = new Integer(1);

	/**
	 * Default {@code Status} code for selection of {@code Status} <em>in
	 * Bearbeitung</em> in a selection box -> value is 'MODI'
	 */
	static final String DEFAULT_STATUS_CODE = "MODI";

	/**
	 * Default {@code Timber} index for selection of {@code Timber} in a
	 * selection box -> value is '0'
	 */
	static final Integer DEFAULT_TIMBER_INDEX = new Integer(0);

	/**
	 * Default {@code Timber} identifier for selection of {@code Timber} in a
	 * selection box -> value is '1'
	 */
	static final Long DEFAULT_TIMBER_ORIGIN_ID = 1L;

	/**
	 * Default {@code Timber} code -> value is 'MLSY'
	 */
	static final String DEFAULT_TIMBER_CODE = "MLSY";

	/**
	 * Default {@code Timber} name -> value is 'Apfelbaum'
	 */
	static final String DEFAULT_TIMBER_NAME = "Apfelbaum";

	/**
	 * Default {@code TimberOrigin} index for selection of {@code TimberOrigin}
	 * in a selection box -> value is '0'
	 */
	static final Integer DEFAULT_TIMBER_ORIGIN_INDEX = new Integer(0);

	/**
	 * Representative {@code String} value for an unknown {@code Status} code ->
	 * value is '????'
	 */
	static final String UNKNOWN_STATUS_CODE = "????";

	/**
	 * Constructor.
	 *
	 */
	public AbstractBowlController() {
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#listBowl(ninja.Context)
	 */
	@Override
	public Result listBowl(Context context) {
		Session session = context.getSession();
		session.clear();
		List<BowlDto> bowlList = bowlService.listBowls();
		Iterator<BowlDto> iterator = bowlList.iterator();
		BowlDto bowlDto = null;
		Boolean bowlInExhibition = Boolean.FALSE;
		Boolean bowlSold = Boolean.FALSE;
		while (iterator.hasNext()) {
			bowlDto = iterator.next();
			if (bowlDto.getExhibition() != null) {
				bowlInExhibition = Boolean.TRUE;
			} else {
				if (bowlDto.getCustomer() != null) {
					bowlSold = Boolean.TRUE;
				}
			}
			if (bowlInExhibition && bowlSold) {
				break;
			}
		}
		bowlDto = bowlService.getBowlMaxOrdinal();
		Integer bowlOrdinal = bowlDto.getOrdinal();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bowlInExhibition", bowlInExhibition);
		map.put("bowlSold", bowlSold);
		map.put("bowlMaxOrdinal", bowlOrdinal + 1);
		return Results.html().render(map).render("bowls", bowlList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#listBowlMod(Long, ninja.Context)
	 */
	@Override
	public Result listBowlMod(Long bowlId, Context context) {
		List<BowlModDto> bowlModList = bowlService.listBowlModsByBowlId(bowlId);
		return Results.html().render("bowlModificationList", bowlModList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#listBowlModItem(Long, ninja.Context)
	 */
	@Override
	public Result listBowlModItem(Long bowlModId, Context context) {
		List<BowlModItemDto> bowlModItemList = bowlService.listBowlModItemsByBowlModId(bowlModId);
		return Results.html().render("bowlModItemList", bowlModItemList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#listBowlModStep(ninja.Context)
	 */
	@Override
	public Result listBowlModStep(Context context) {
		List<BowlModStepDto> bowlModStepList = bowlService.listBowlModSteps();
		return Results.html().render("bowlModStepList", bowlModStepList);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * controllers.IBowlController#getBowlDtoFromSession(ninja.session.Session)
	 */
	@Override
	public BowlDto getBowlDtoFromSession(Session session) {
		logger.info("AbstractBowlController.getBowlDtoFromSession -> Session    id: " + session.getId());
		logger.info("AbstractBowlController.getBowlDtoFromSession -> Session empty: " + session.isEmpty());

		logBowlCompletion(session);

		Long id = null;
		if (session.get("bowl.id") != null) {
			id = new Long(session.get("bowl.id"));
		}
		Integer version = null;
		if (session.get("bowl.version") != null) {
			version = new Integer(session.get("bowl.version")).intValue();
		}
		Integer index = null;
		if (session.get("bowl.index") != null) {
			index = new Integer(session.get("bowl.index")).intValue();
		}
		String manufactureId = null;
		Manufacture manufacture = null;
		if (session.get("bowl.manufacture.id") != null) {
			manufactureId = session.get("bowl.manufacture.id");
			logger.info("AbstractBowlController.getBowlDtoFromSession ManufactureId is {}", manufactureId);
			manufacture = new Manufacture(manufactureService.getManufactureById(manufactureId));
			logger.info("AbstractBowlController.getBowlDtoFromSession ManufactureId is {}", manufacture.getId());
		}
		String statusId = null;
		Status status = null;
		if (session.get("bowl.status.id") != null) {
			statusId = session.get("bowl.status.id");
			logger.info("AbstractBowlController.getBowlDtoFromSession StatusId is {}", statusId);
			status = new Status(statusService.getStatusById(statusId));
			logger.info("AbstractBowlController.getBowlDtoFromSession StatusId is {}", status.getId());
		}
		String timberId = null;
		Timber timber = null;
		if (session.get("bowl.timber.id") != null) {
			timberId = session.get("bowl.timber.id");
			logger.info("AbstractBowlController.getBowlDtoFromSession TimberId is {}", timberId);
			timber = new Timber(timberService.getTimberById(timberId));
			logger.info("AbstractBowlController.getBowlDtoFromSession TimberId is {}", timber.getId());
		}
		String timberOriginId = null;
		TimberOrigin timberOrigin = null;
		if (session.get("bowl.timber.origin.id") != null) {
			timberOriginId = session.get("bowl.timber.origin.id");
			logger.info("AbstractBowlController.getBowlDtoFromSession TimberOriginId is {}", timberOriginId);
			timberOrigin = new TimberOrigin(timberOriginService.getTimberOriginById(timberOriginId));
			logger.info("AbstractBowlController.getBowlDtoFromSession TimberOriginId is {}", timberOrigin.getId());
		}
		String customerId = null;
		Customer customer = null;
		if (session.get("bowl.customer.id") != null) {
			customerId = session.get("bowl.customer.id");
			logger.info("AbstractBowlController.getBowlDtoFromSession CustomerId is {}", customerId);
			customer = new Customer(customerService.getCustomerById(customerId));
			logger.info("AbstractBowlController.getBowlDtoFromSession CustomerId is {}", customer.getId());
		}
		String exhibitionId = null;
		Exhibition exhibition = null;
		if (session.get("bowl.exhibition.id") != null) {
			exhibitionId = session.get("bowl.exhibition.id");
			logger.info("AbstractBowlController.getBowlDtoFromSession ExhibitionId is {}", exhibitionId);
			exhibition = new Exhibition(exhibitionService.getExhibitionById(exhibitionId));
			logger.info("AbstractBowlController.getBowlDtoFromSession ExhibitionId is {}", exhibition.getId());
		}
		Integer ordinal = null;
		if (session.get("bowl.ordinal") != null) {
			ordinal = new Integer(session.get("bowl.ordinal"));
		} else {
			ordinal = null;
		}
		String year = null;
		if (session.get("bowl.year") != null) {
			year = session.get("bowl.year");
		} else {
			if (manufacture != null) {
				year = manufacture.getYear();
				logger.info("AbstractBowlController.getBowlDtoFromSession Manufacture -> {}", manufacture.asString());
			}
		}
		String imageName = null;
		if (session.get("bowl.image.name") != null) {
			imageName = session.get("bowl.image.name");
		} else {
			imageName = null;
		}
		BigDecimal price = null;
		if (session.get("bowl.price") != null) {
			price = new BigDecimal(session.get("bowl.price"));
		} else {
			price = null;
		}
		BigDecimal cent = null;
		if (session.get("bowl.cent") != null) {
			cent = new BigDecimal(session.get("bowl.cent"));
		} else {
			cent = null;
		}
		BigDecimal salesPrice = null;
		String lSalesPrice = session.get("bowl.sales.price");
		if (lSalesPrice != null && !lSalesPrice.isEmpty()) {
			if (lSalesPrice != null && !lSalesPrice.isEmpty()) {
				salesPrice = new BigDecimal(lSalesPrice);
			}
		} else {
			salesPrice = null;
		}
		BigDecimal salesCent = null;
		String lSalesCent = session.get("bowl.sales.cent");
		if (lSalesCent != null && !lSalesCent.isEmpty()) {
			salesCent = new BigDecimal(lSalesCent);
		} else {
			salesCent = null;
		}
		String salesLocation = null;
		if (session.get("bowl.sales.location") != null) {
			salesLocation = session.get("bowl.sales.location");
		} else {
			salesLocation = null;
		}
		Date salesDate = null;
		String lSalesDate = session.get("bowl.sales.date");
		if (lSalesDate != null && !lSalesDate.isEmpty()) {
			salesDate = SuperbowlHelper.convertToDate(lSalesDate);
		} else {
			salesDate = null;
		}
		String comment = null;
		if (session.get("bowl.comment") != null) {
			comment = session.get("bowl.comment");
		} else {
			comment = null;
		}
		String timberOriginName = null;
		if (session.get("bowl.timber.origin.name") != null) {
			timberOriginName = session.get("bowl.timber.origin.name");
		} else {
			timberOriginName = null;
		}
		logger.info("AbstractBowlController.getBowlDtoFromSession timberOriginName -> {}",
				String.valueOf(timberOriginName));

		BowlDto bowlDto = new BowlDto(id, version, index, manufacture, status, timber, timberOrigin, customer,
				exhibition, ordinal, year, imageName, price, salesPrice, salesLocation, salesDate, comment);

		logger.info("AbstractBowlController.getBowlDtoFromSession BowlDto -> {}", bowlDto.asString());

		return bowlDto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#getBowlModDtoFromSession(ninja.session.
	 * Session)
	 */
	@Override
	public BowlModDto getBowlModDtoFromSession(Session session) {
		logger.info("AbstractBowlController.getBowlModDtoFromSession -> Session    id: " + session.getId());
		logger.info("AbstractBowlController.getBowlModDtoFromSession -> Session empty: " + session.isEmpty());

		// Map<String, String> sessionMap = session.getData();
		// logger.info("AbstractBowlController.getBowlModDtoFromSession ->
		// Session map size: " + sessionMap.size());
		// Set<String> sessionKeySet = sessionMap.keySet();
		// Iterator<String> sessionKeySetIterator = sessionKeySet.iterator();
		// String key = null;
		// Object value = null;
		// while (sessionKeySetIterator.hasNext()) {
		// key = sessionKeySetIterator.next();
		// logger.info("AbstractBowlController.getBowlModDtoFromSession ->
		// Session key: " + key);
		// value = sessionMap.get(key);
		// logger.info("AbstractBowlController.getBowlModDtoFromSession ->
		// Session value: " + value);
		// }

		Long bowlModId = null;
		if (session.get("bowlMod.id") != null) {
			bowlModId = new Long(session.get("bowlMod.id"));
		}
		Integer bowlModVersion = null;
		if (session.get("bowlMod.version") != null) {
			bowlModVersion = new Integer(session.get("bowlMod.version")).intValue();
		}
		String bowlId = null;
		if (session.get("bowlMod.bowlId") != null) {
			bowlId = session.get("bowlMod.bowlId");
		}
		String bowlModStepId = null;
		if (session.get("bowlMod.bowlModStepId") != null) {
			bowlModStepId = session.get("bowlMod.bowlModStepId");
		}
		String date = null;
		if (session.get("bowlMod.date") != null) {
			date = session.get("bowlMod.date");
		}
		String diameter = null;
		if (session.get("bowlMod.diameter") != null) {
			diameter = session.get("bowlMod.diameter");
		}
		String height = null;
		if (session.get("bowlMod.height") != null) {
			height = session.get("bowlMod.height");
		}
		String wallthicknessMin = null;
		if (session.get("bowlMod.wallthicknessMin") != null) {
			wallthicknessMin = session.get("bowlMod.wallthicknessMin");
		}
		String wallthicknessMax = null;
		if (session.get("bowlMod.wallthicknessMax") != null) {
			wallthicknessMax = session.get("bowlMod.wallthicknessMax");
		}
		String granulation = null;
		if (session.get("bowlMod.granulation") != null) {
			granulation = session.get("bowlMod.granulation");
		}
		String tap = null;
		if (session.get("bowlMod.tap") != null) {
			tap = session.get("bowlMod.tap");
		}
		String recess = null;
		if (session.get("bowlMod.recess") != null) {
			recess = session.get("bowlMod.recess");
		}
		String surface = null;
		if (session.get("bowlMod.surface") != null) {
			surface = session.get("bowlMod.surface");
		}
		String comment = null;
		if (session.get("bowlMod.comment") != null) {
			comment = session.get("bowlMod.comment");
		}

		logBowlModCompletion(session);

		BowlModDto bowlModDto = null;

		// bowlModDto = new BowlModDto(bowlModId, bowlModVersion, new
		// Bowl(bowlService.getBowlById(new Long(bowlId))),
		// new BowlModStep(bowlService.getBowlModStepById(new
		// Long(bowlModStepId))),
		// SuperbowlHelper.convertToDate(date), new BigDecimal(diameter), new
		// BigDecimal(height),
		// new BigDecimal(wallthicknessMin), new BigDecimal(wallthicknessMax),
		// new Integer(granulation),
		// new Integer(tap), new Integer(recess), surface, comment);

		if (bowlId != null) {
			bowlModDto = new BowlModDto(bowlModId, bowlModVersion, new Bowl(bowlService.getBowlById(bowlId)),
					new BowlModStep(bowlService.getBowlModStepById(bowlModStepId)), SuperbowlHelper.convertToDate(date),
					new BigDecimal(diameter), new BigDecimal(height), new BigDecimal(wallthicknessMin),
					new BigDecimal(wallthicknessMax), new Integer(granulation), new Integer(tap), new Integer(recess),
					surface, comment);
		}

		return bowlModDto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * controllers.BowlController#getBowlModItemDtoFromSession(ninja.session.
	 * Session)
	 */
	@Override
	public BowlModItemDto getBowlModItemDtoFromSession(Session session) {
		logger.info("AbstractBowlController.getBowlModItemDtoFromSession -> Session id: " + session.getId());

		Long id = null;
		if (session.get("bowlModItem.id") != null) {
			id = new Long(session.get("bowlModItem.id"));
		}
		Integer version = null;
		if (session.get("bowlModItem.version") != null) {
			version = new Integer(session.get("bowlModItem.version")).intValue();
		}
		String bowlModId = null;
		BowlMod bowlMod = null;
		if (session.get("bowlModItem.bowlModId") != null) {
			bowlModId = session.get("bowlModItem.bowlModId");
			bowlMod = new BowlMod(bowlService.getBowlModById(bowlModId));
		}
		String text = null;
		if (session.get("bowlModItem.text") != null) {
			text = session.get("bowlModItem.text");
		}
		Date date = null;
		if (session.get("bowlModItem.date") != null) {
			String sessionDate = session.get("bowlModItem.date");
			date = SuperbowlHelper.convertToDate(sessionDate);
		}
		String weight = session.get("bowlModItem.weight");
		if (session.get("bowlModItem.weight") != null) {
			weight = session.get("bowlModItem.weight");
		}
		String moisture = session.get("bowlModItem.moisture");
		if (session.get("bowlModItem.moisture") != null) {
			moisture = session.get("bowlModItem.moisture");
		}

		logBowlModItemCompletion(session);

		BowlModItemDto bowlModItemDto = null;

		if (bowlMod != null) {
			bowlModItemDto = new BowlModItemDto(id, version, bowlMod, text, date, new BigDecimal(weight),
					new BigDecimal(moisture));
		}

		return bowlModItemDto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * controllers.IBowlController#getBowlModStepDtoFromSession(ninja.session.
	 * Session)
	 */
	@Override
	public BowlModStepDto getBowlModStepDtoFromSession(Session session) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#putBowlDtoIntoSession(dto.BowlDto,
	 * ninja.session.Session)
	 */
	@Override
	public void putBowlDtoIntoSession(BowlDto bowlDto, Session session) {
		logger.info("AbstractBowlController.putBowlDtoIntoSession -> Session id: " + session.getId());

		logBowl(bowlDto);

		String data = null;

		logger.info("=== Put Bowl Data Transfer Object Into Session ===");
		logger.info("id................: {}", bowlDto.getId().toString());
		session.put("bowl.id", bowlDto.getId().toString());

		logger.info("version...........: {}", bowlDto.getVersion().toString());
		session.put("bowl.version", bowlDto.getVersion().toString());

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getIndex().toString())
				? bowlDto.getIndex().toString() : Whitespace.EMPTY.getValue());
		logger.info("index.............: {}", data);
		session.put("bowl.index", data);

		if (bowlDto.getStatus() != null) {
			logger.info("status.id.........: {}", bowlDto.getStatus().getId());
			session.put("bowl.status.id", bowlDto.getStatus().getId().toString());
		} else {
			logger.info("status.id.........: {}", "null");
			session.put("bowl.status.id", null);
		}

		if (bowlDto.getTimber() != null) {
			logger.info("timber.id.........: {}", bowlDto.getTimber().getId());
			session.put("bowl.timber.id", bowlDto.getTimber().getId().toString());
		} else {
			logger.info("timber.id.........: {}", "null");
			session.put("bowl.timber.id", null);
		}

		if (bowlDto.getTimberOrigin() != null) {
			logger.info("timber.origin.id..: {}", bowlDto.getTimberOrigin().getId());
			session.put("bowl.timber.origin.id", bowlDto.getTimberOrigin().getId().toString());
		} else {
			logger.info("timber.origin.id..: {}", "null");
			session.put("bowl.timber.origin.id", null);
		}

		if (bowlDto.getManufacture() != null) {
			logger.info("manufacture.id....: {}", bowlDto.getManufacture().getId());
			session.put("bowl.manufacture.id", bowlDto.getManufacture().getId().toString());
		} else {
			logger.info("manufacture.id....: {}", "null");
			session.put("bowl.manufacture.id", null);
		}

		if (bowlDto.getCustomer() != null) {
			logger.info("customer.id.......: {}", bowlDto.getCustomer().getId());
			session.put("bowl.customer.id", bowlDto.getCustomer().getId().toString());
		} else {
			logger.info("customer.id.......: {}", "null");
			session.put("bowl.customer.id", null);
		}

		if (bowlDto.getExhibition() != null) {
			logger.info("exhibition.id.....: {}", bowlDto.getExhibition().getId());
			session.put("bowl.exhibition.id", bowlDto.getExhibition().getId().toString());
		} else {
			logger.info("exhibition.id.....: {}", "null");
			session.put("bowl.exhibition.id", null);
		}

		logger.info("timber.origin.name: {}", bowlDto.getTimberOrigin().getTimberOriginName());
		session.put("bowl.timber.origin.name", bowlDto.getTimberOrigin().getTimberOriginName());

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getOrdinal().toString())
				? bowlDto.getOrdinal().toString() : Whitespace.EMPTY.getValue());
		logger.info("ordinal...........: {}", data);
		session.put("bowl.ordinal", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getYear()) ? bowlDto.getYear()
				: Whitespace.EMPTY.getValue());
		logger.info("year..............: {}", data);
		session.put("bowl.year", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getImageName()) ? bowlDto.getImageName()
				: Whitespace.EMPTY.getValue());
		logger.info("imageName.........: {}", data);
		session.put("bowl.image.name", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getPrice().toString())
				? bowlDto.getPrice().toString() : Whitespace.EMPTY.getValue());
		logger.info("price.............: {}", data);
		session.put("bowl.price", data);

		if (bowlDto.getSalesPrice() == null) {
			data = null;
		} else {
			data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getSalesPrice().toString())
					? bowlDto.getSalesPrice().toString() : Whitespace.EMPTY.getValue());
		}
		logger.info("salesPrice........: {}", data);
		session.put("bowl.sales.price", data);

		if (bowlDto.getSalesLocation() == null) {
			data = null;
		} else {
			data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getSalesLocation())
					? bowlDto.getSalesLocation() : Whitespace.EMPTY.getValue());
		}
		logger.info("salesLocation.....: {}", data);
		session.put("bowl.sales.location", data);

		if (bowlDto.getSalesDate() == null) {
			data = null;
		} else {
			data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getSalesDate().toString())
					? bowlDto.getSalesDate().toString() : Whitespace.EMPTY.getValue());
		}
		logger.info("salesDate.........: {}", data);
		session.put("bowl.sales.date", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getComment()) ? bowlDto.getComment()
				: Whitespace.EMPTY.getValue());
		logger.info("comment...........: {}", data);
		session.put("bowl.comment", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlDto.getEmptyTable()) ? bowlDto.getEmptyTable()
				: Whitespace.EMPTY.getValue());
		logger.info("emptyTable........: {}", data);
		session.put("bowl.empty.table", data);
		logger.info("=== End Put Bowl Data Transfer Object Into Session ===");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#putBowlModDtoIntoSession(dto.BowlModDto,
	 * ninja.session.Session)
	 */
	@Override
	public void putBowlModDtoIntoSession(BowlModDto bowlModDto, Session session) {
		logger.info("AbstractBowlController.putBowlModDtoIntoSession -> Session id: " + session.getId());

		logBowlMod(bowlModDto);

		String data = null;

		logger.info("=== put bowl mod dto into session ===");
		logger.info("id..............: {}", bowlModDto.getId().toString());
		session.put("bowlMod.id", bowlModDto.getId().toString());
		logger.info("version.........: {}", bowlModDto.getVersion().toString());
		session.put("bowlMod.version", bowlModDto.getVersion().toString());
		logger.info("bowlId..........: {}", bowlModDto.getBowl().getId());
		session.put("bowlMod.bowlId", bowlModDto.getBowl().getId().toString());
		logger.info("bowlModStepId...: {}", bowlModDto.getBowlModStep().getId());
		session.put("bowlMod.bowlModStepId", bowlModDto.getBowlModStep().getId().toString());
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getDate().toString())
				? bowlModDto.getDate().toString() : Whitespace.EMPTY.getValue());
		logger.info("date............: {}", data);
		session.put("bowlMod.date", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getDiameter().toString())
				? bowlModDto.getDiameter().toString() : Whitespace.EMPTY.getValue());
		logger.info("diameter........: {}", data);
		session.put("bowlMod.diameter", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getHeight().toString())
				? bowlModDto.getHeight().toString() : Whitespace.EMPTY.getValue());
		logger.info("height..........: {}", data);
		session.put("bowlMod.height", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getGranulation().toString())
				? bowlModDto.getGranulation().toString() : Whitespace.EMPTY.getValue());
		logger.info("granulation: {}", data);
		session.put("bowlMod.granulation", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getWallthicknessMin().toString())
				? bowlModDto.getWallthicknessMin().toString() : Whitespace.EMPTY.getValue());
		logger.info("wallthicknessMin: {}", data);
		session.put("bowlMod.wallthicknessMin", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getWallthicknessMax().toString())
				? bowlModDto.getWallthicknessMax().toString() : Whitespace.EMPTY.getValue());
		logger.info("wallthicknessMax.....: {}", data);
		session.put("bowlMod.wallthicknessMax", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getSurface()) ? bowlModDto.getSurface()
				: Whitespace.EMPTY.getValue());
		logger.info("surface.........: {}", data);
		session.put("bowlMod.surface", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getTap().toString())
				? bowlModDto.getTap().toString() : Whitespace.EMPTY.getValue());
		logger.info("tap.............: {}", data);
		session.put("bowlMod.tap", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getRecess().toString())
				? bowlModDto.getRecess().toString() : Whitespace.EMPTY.getValue());
		logger.info("recess..........: {}", data);
		session.put("bowlMod.recess", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModDto.getComment()) ? bowlModDto.getComment()
				: Whitespace.EMPTY.getValue());
		logger.info("comment.........: {}", data);
		session.put("bowlMod.comment", data);
		logger.info("=== end put bowl mod dto into session ===");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#putBowlModItemDtoIntoSession(dto.
	 * BowlModItemDto, ninja.session.Session)
	 */
	@Override
	public void putBowlModItemDtoIntoSession(BowlModItemDto bowlModItemDto, Session session) {
		logger.info("AbstractBowlController.putBowlModDtoIntoSession -> Session id: " + session.getId());

		logBowlModItem(bowlModItemDto);

		String data = null;

		logger.info("=== put bowl mod item dto into session ===");
		logger.info("id.........: {}", bowlModItemDto.getId().toString());
		session.put("bowlModItem.id", bowlModItemDto.getId().toString());
		logger.info("version....: {}", bowlModItemDto.getVersion().toString());
		session.put("bowlModItem.version", bowlModItemDto.getVersion().toString());
		logger.info("bowlModId..: {}", bowlModItemDto.getBowlMod().getId());
		session.put("bowlModItem.bowlModId", bowlModItemDto.getBowlMod().getId().toString());
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemDto.getText()) ? bowlModItemDto.getText()
				: Whitespace.EMPTY.getValue());
		logger.info("text.......: {}", data);
		session.put("bowlModItem.text", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemDto.getDate().toString())
				? bowlModItemDto.getDate().toString() : Whitespace.EMPTY.getValue());
		logger.info("date.......: {}", data);
		session.put("bowlModItem.date", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemDto.getWeight().toString())
				? bowlModItemDto.getWeight().toString() : Whitespace.EMPTY.getValue());
		logger.info("weight.....: {}", data);
		session.put("bowlModItem.weight", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemDto.getMoisture().toString())
				? bowlModItemDto.getMoisture().toString() : Whitespace.EMPTY.getValue());
		logger.info("moisture...: {}", data);
		session.put("bowlModItem.moisture", data);
		logger.info("=== end put bowl mod item dto into session ===");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#putBowlModStepDtoIntoSession(dto.
	 * BowlModStepDto, ninja.session.Session)
	 */
	@Override
	public void putBowlModStepDtoIntoSession(BowlModStepDto bowlModStepDto, Session session) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#putBowlFormIntoSession(forms.BowlForm,
	 * ninja.session.Session)
	 */
	@Override
	public void putBowlFormIntoSession(BowlForm bowlForm, Session session) {
		logger.info("AbstractBowlController.putBowlFormIntoSession -> Session    id: " + session.getId());
		logger.info("AbstractBowlController.putBowlFormIntoSession -> Session empty: " + session.isEmpty());

		// logBowlConfirmation(bowlForm);

		String data = null;

		logger.info("=== Put Bowl Form Data Into Session ===");
		logger.info("id................: {}", bowlForm.getId());
		if (bowlForm.getId() == null) {
			session.put("bowl.id", null);
		} else {
			session.put("bowl.id", bowlForm.getId());
		}

		logger.info("version...........: {}", bowlForm.getVersion());
		if (bowlForm.getVersion() == null) {
			session.put("bowl.version", "0");
		} else {
			session.put("bowl.version", bowlForm.getVersion());
		}

		logger.info("index.............: {}", bowlForm.getIndex());
		if (bowlForm.getIndex() == null) {
			session.put("bowl.index", "0");
		} else {
			session.put("bowl.index", bowlForm.getIndex());
		}

		logger.info("geo.region.id.....: {}", bowlForm.getGeoRegionId());
		session.put("bowl.geo.region.id", bowlForm.getGeoRegionId());

		logger.info("geo.region.code...: {}", bowlForm.getGeoRegionCode());
		session.put("bowl.geo.region.code", bowlForm.getGeoRegionCode());

		logger.info("manufacture.id....: {}", bowlForm.getManufactureId());
		session.put("bowl.manufacture.id", bowlForm.getManufactureId());

		logger.info("status.id.........: {}", bowlForm.getStatusId());
		session.put("bowl.status.id", bowlForm.getStatusId());

		logger.info("timber.id.........: {}", bowlForm.getTimberId());
		session.put("bowl.timber.id", bowlForm.getTimberId());

		logger.info("timber.origin.id..: {}", bowlForm.getTimberOriginId());
		session.put("bowl.timber.origin.id", bowlForm.getTimberOriginId());

		logger.info("timber.origin.name: {}", bowlForm.getTimberOriginName());
		session.put("bowl.timber.origin.name", bowlForm.getTimberOriginName());

		logger.info("customer.id.......: {}", bowlForm.getCustomerId());
		session.put("bowl.customer.id", bowlForm.getCustomerId());

		logger.info("exhibition.id.....: {}", bowlForm.getExhibitionId());
		session.put("bowl.exhibition.id", bowlForm.getExhibitionId());

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getOrdinal().toString())
				? bowlForm.getOrdinal().toString() : Whitespace.EMPTY.getValue());
		logger.info("ordinal...........: {}", data);
		session.put("bowl.ordinal", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getImageName()) ? bowlForm.getImageName()
				: Whitespace.EMPTY.getValue());
		logger.info("imageName.........: {}", data);
		session.put("bowl.image.name", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getYear()) ? bowlForm.getYear()
				: Whitespace.EMPTY.getValue());
		logger.info("year..............: {}", data);
		session.put("bowl.year", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getPrice().toString())
				? bowlForm.getPrice().toString() : Whitespace.EMPTY.getValue());
		logger.info("price.............: {}", data);
		session.put("bowl.price", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getCent().toString())
				? bowlForm.getCent().toString() : Whitespace.EMPTY.getValue());
		logger.info("cent..............: {}", data);
		session.put("bowl.cent", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getSalesPrice()) ? bowlForm.getSalesPrice()
				: null);
		logger.info("salesPrice........: {}", data);
		session.put("bowl.sales.price", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getSalesCent()) ? bowlForm.getSalesCent()
				: null);
		logger.info("salesCent.........: {}", data);
		session.put("bowl.sales.cent", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getSalesLocation())
				? bowlForm.getSalesLocation() : null);
		logger.info("salesLocation.....: {}", data);
		session.put("bowl.sales.location", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getSalesDate()) ? bowlForm.getSalesDate()
				: null);
		logger.info("salesDate.........: {}", data);
		session.put("bowl.sales.date", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getComment()) ? bowlForm.getComment()
				: Whitespace.EMPTY.getValue());
		logger.info("comment...........: {}", data);
		session.put("bowl.comment", data);

		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlForm.getEmptyTable()) ? bowlForm.getEmptyTable()
				: Whitespace.EMPTY.getValue());
		logger.info("emptyTable........: {}", data);
		session.put("bowl.empty.table", data);
		logger.info("=== End Put Bowl Form Data Into Session ===");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * controllers.IBowlController#putBowlModFormIntoSession(forms.BowlModForm,
	 * ninja.session.Session)
	 */
	@Override
	public void putBowlModFormIntoSession(BowlModForm bowlModForm, Session session) {
		logger.info("AbstractBowlController.putBowlModFormIntoSession -> Session    id: " + session.getId());
		logger.info("AbstractBowlController.putBowlModFormIntoSession -> Session empty: " + session.isEmpty());

		// logBowlModConfirmation(bowlModForm);

		String data = null;

		logger.info("=== put bowl mod form into session ===");
		logger.info("id..............: {}", bowlModForm.getId());
		if (bowlModForm.getId() == null) {
			session.put("bowlMod.id", null);
		} else {
			session.put("bowlMod.id", bowlModForm.getId());
		}
		logger.info("version.........: {}", bowlModForm.getVersion());
		if (bowlModForm.getVersion() == null) {
			session.put("bowlMod.version", "0");
		} else {
			session.put("bowlMod.version", bowlModForm.getVersion());
		}
		logger.info("bowlId..........: {}", bowlModForm.getBowlId());
		session.put("bowlMod.bowlId", bowlModForm.getBowlId());
		logger.info("bowlModStepId...: {}", bowlModForm.getBowlModStepId());
		session.put("bowlMod.bowlModStepId", bowlModForm.getBowlModStepId());
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getDate().toString())
				? bowlModForm.getDate().toString() : Whitespace.EMPTY.getValue());
		logger.info("date............: {}", data);
		session.put("bowlMod.date", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getDiameter().toString())
				? bowlModForm.getDiameter().toString() : Whitespace.EMPTY.getValue());
		logger.info("diameter........: {}", data);
		session.put("bowlMod.diameter", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getHeight().toString())
				? bowlModForm.getHeight().toString() : Whitespace.EMPTY.getValue());
		logger.info("height..........: {}", data);
		session.put("bowlMod.height", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getWallthicknessMin())
				? bowlModForm.getWallthicknessMin() : Whitespace.EMPTY.getValue());
		logger.info("wallthicknessMin: {}", data);
		session.put("bowlMod.wallthicknessMin", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getWallthicknessMax())
				? bowlModForm.getWallthicknessMax() : Whitespace.EMPTY.getValue());
		logger.info("wallthicknessMax: {}", data);
		session.put("bowlMod.wallthicknessMax", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getGranulation().toString())
				? bowlModForm.getGranulation().toString() : Whitespace.EMPTY.getValue());
		logger.info("granulation.....: {}", data);
		session.put("bowlMod.granulation", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getSurface().toString())
				? bowlModForm.getSurface().toString() : Whitespace.EMPTY.getValue());
		logger.info("surface.........: {}", data);
		session.put("bowlMod.surface", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getTap()) ? bowlModForm.getTap()
				: Whitespace.EMPTY.getValue());
		logger.info("tap.............: {}", data);
		session.put("bowlMod.tap", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getRecess()) ? bowlModForm.getRecess()
				: Whitespace.EMPTY.getValue());
		logger.info("recess..........: {}", data);
		session.put("bowlMod.recess", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModForm.getComment()) ? bowlModForm.getComment()
				: Whitespace.EMPTY.getValue());
		logger.info("comment.........: {}", data);
		session.put("bowlMod.comment", data);
		logger.info("=== end put bowl mod form into session ===");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see controllers.IBowlController#putBowlModItemFormIntoSession(forms.
	 * BowlModItemForm, ninja.session.Session)
	 */
	@Override
	public void putBowlModItemFormIntoSession(BowlModItemForm bowlModItemForm, Session session) {
		logger.info("AbstractBowlController.putBowlModItemFormIntoSession -> Session id: " + session.getId());

		// logBowlModItemConfirmation(bowlModItemForm);

		String data = null;

		logger.info("=== put bowl mod item form into session ===");
		logger.info("id.........: {}", bowlModItemForm.getId());
		if (bowlModItemForm.getId() == null) {
			session.put("bowlModItem.id", null);
		} else {
			session.put("bowlModItem.id", bowlModItemForm.getId());
		}
		logger.info("version....: {}", bowlModItemForm.getVersion());
		if (bowlModItemForm.getVersion() == null) {
			session.put("bowlModItem.version", "0");
		} else {
			session.put("bowlModItem.version", bowlModItemForm.getVersion());
		}
		logger.info("bowlModId..: {}", bowlModItemForm.getBowlModId());
		session.put("bowlModItem.bowlModId", bowlModItemForm.getBowlModId().toString());
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemForm.getText())
				? bowlModItemForm.getText() : Whitespace.EMPTY.getValue());
		logger.info("text.......: {}", data);
		session.put("bowlModItem.text", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemForm.getDate().toString())
				? bowlModItemForm.getDate().toString() : Whitespace.EMPTY.getValue());
		logger.info("date.......: {}", data);
		session.put("bowlModItem.date", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemForm.getWeight().toString())
				? bowlModItemForm.getWeight().toString() : Whitespace.EMPTY.getValue());
		logger.info("weight.....: {}", data);
		session.put("bowlModItem.weight", data);
		data = (Checker.isNotNullNotEmptyNotWhiteSpaceOnlyByCommons(bowlModItemForm.getMoisture().toString())
				? bowlModItemForm.getMoisture().toString() : Whitespace.EMPTY.getValue());
		logger.info("moisture...: {}", data);
		session.put("bowlModItem.moisture", data);
		logger.info("=== end put bowl mod item form into session ===");
	}

	/**
	 * Logging of {@code Bowl} data.
	 *
	 * @param bowlDto
	 *            the {@code BowlDto} instance
	 */
	final void logBowl(BowlDto bowlDto) {
		logger.info(bowlDto.asString());
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl"));
		if (isLog) {
			logger.info("=== Bowl DTO Data ===");
			logger.info("id................: {}", bowlDto.getId());
			logger.info("version...........: {}", bowlDto.getVersion());
			logger.info("index.............: {}", bowlDto.getIndex());
			logger.info("manufacture.id....: {}", (bowlDto.getManufacture().getId() == null)
					? String.valueOf(bowlDto.getManufacture().getId()) : bowlDto.getManufacture().getId());
			logger.info("status.id.........: {}", (bowlDto.getStatus().getId() == null)
					? String.valueOf(bowlDto.getStatus().getId()) : bowlDto.getStatus().getId());
			logger.info("timber.id.........: {}", (bowlDto.getTimber().getId() == null)
					? String.valueOf(bowlDto.getTimber().getId()) : bowlDto.getTimber().getId());
			logger.info("timber.origin.id..: {}", (bowlDto.getTimberOrigin().getId() == null)
					? String.valueOf(bowlDto.getTimberOrigin().getId()) : bowlDto.getTimberOrigin().getId());
			if (bowlDto.getCustomer() != null) {
				logger.info("customer.id.......: {}", (bowlDto.getCustomer().getId() == null)
						? String.valueOf(bowlDto.getCustomer().getId()) : bowlDto.getCustomer().getId());
			}
			if (bowlDto.getExhibition() != null) {
				logger.info("exhibition.id.....: {}", (bowlDto.getExhibition().getId() == null)
						? String.valueOf(bowlDto.getExhibition().getId()) : bowlDto.getExhibition().getId());
			}
			logger.info("timber.origin.name: {}",
					(bowlDto.getTimberOrigin().getTimberOriginName() == null)
							? String.valueOf(bowlDto.getTimberOrigin().getTimberOriginName())
							: bowlDto.getTimberOrigin().getTimberOriginName());
			logger.info("ordinal...........: {}", bowlDto.getOrdinal());
			logger.info("imageName.........: {}", bowlDto.getImageName());
			logger.info("price.............: {}", bowlDto.getPrice());
			logger.info("cent..............: {}", bowlDto.getCent());
			logger.info("salesPrice........: {}", bowlDto.getSalesPrice());
			logger.info("salesCent.........: {}", bowlDto.getSalesCent());
			logger.info("salesLocation.....: {}", bowlDto.getSalesLocation());
			logger.info("salesDate.........: {}", bowlDto.getSalesDate());
			logger.info("comment...........: {}", bowlDto.getComment());
			logger.info("emptyTable........: {}", bowlDto.getEmptyTable());
			logger.info("=== End Bowl DTO Data ===");
		}
	}

	/**
	 * Logging of {@code BowlMod} data.
	 *
	 * @param bowlModDto
	 *            the {@code BowlModDto} instance
	 */
	final void logBowlMod(BowlModDto bowlModDto) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.mod"));
		if (isLog) {
			logger.info("=== BowlMod DTO Data ===");
			logger.info("id..............: {}", bowlModDto.getId());
			logger.info("bowlId..........: {}", (bowlModDto.getBowl().getId() == null)
					? String.valueOf(bowlModDto.getBowl().getId()) : bowlModDto.getBowl().getId());
			logger.info("bowlModStepId...: {}", (bowlModDto.getBowlModStep().getId() == null)
					? String.valueOf(bowlModDto.getBowlModStep().getId()) : bowlModDto.getBowlModStep().getId());
			logger.info("date............: {}", bowlModDto.getDate());
			logger.info("diameter........: {}", bowlModDto.getDiameter());
			logger.info("height..........: {}", bowlModDto.getHeight());
			logger.info("wallthicknessMin: {}", bowlModDto.getWallthicknessMin());
			logger.info("wallthicknessMax: {}", bowlModDto.getWallthicknessMax());
			logger.info("granulation.....: {}", bowlModDto.getGranulation());
			logger.info("surface.........: {}", bowlModDto.getSurface());
			logger.info("tap.............: {}", bowlModDto.getTap());
			logger.info("recess..........: {}", bowlModDto.getRecess());
			logger.info("=== End BowlMod DTO Data ===");
		}
	}

	/**
	 * Logging of {@code BowlModItem} data.
	 *
	 * @param bowlModItemDto
	 *            the {@code BowlModItemDto} instance
	 */
	final void logBowlModItem(BowlModItemDto bowlModItemDto) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.mod.item"));
		if (isLog) {
			logger.info("=== BowlModItem DTO Data ===");
			logger.info("id.........: {}", bowlModItemDto.getId());
			logger.info("version....: {}", bowlModItemDto.getVersion());
			logger.info("bowlModId..: {}", (bowlModItemDto.getBowlMod().getId() == null)
					? String.valueOf(bowlModItemDto.getBowlMod().getId()) : bowlModItemDto.getBowlMod().getId());
			logger.info("date....: {}", bowlModItemDto.getDate());
			logger.info("weight.....: {}", bowlModItemDto.getWeight());
			logger.info("moisture...: {}", bowlModItemDto.getMoisture());
			logger.info("=== End BowlModItem DTO Data ===");
		}
	}

	/**
	 * Logging of confirmation of {@code BowlMod} data.
	 *
	 * @param bowlForm
	 *            the {@code BowlForm} instance
	 */
	final void logBowlConfirmation(BowlForm bowlForm) {
		logger.info(bowlForm.asString());
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.form"));
		if (isLog) {
			logger.info("=== Confirmation of BowlForm Data ===");
			logger.info("manufacture.id......{}", (bowlForm.getManufactureId() == null)
					? String.valueOf(bowlForm.getManufactureId()) : bowlForm.getManufactureId());
			logger.info("status.id.........: {}",
					(bowlForm.getStatusId() == null) ? String.valueOf(bowlForm.getStatusId()) : bowlForm.getStatusId());
			logger.info("timber.id.........: {}",
					(bowlForm.getTimberId() == null) ? String.valueOf(bowlForm.getTimberId()) : bowlForm.getTimberId());
			logger.info("timber.origin.id..: {}", (bowlForm.getTimberOriginId() == null)
					? String.valueOf(bowlForm.getTimberOriginId()) : bowlForm.getTimberOriginId());
			logger.info("customer.id.......: {}", (bowlForm.getCustomerId() == null)
					? String.valueOf(bowlForm.getCustomerId()) : bowlForm.getCustomerId());
			logger.info("exhibition.id.....: {}", (bowlForm.getExhibitionId() == null)
					? String.valueOf(bowlForm.getExhibitionId()) : bowlForm.getExhibitionId());
			logger.info("timber.origin.name: {}", (bowlForm.getTimberOriginName() == null)
					? String.valueOf(bowlForm.getTimberOriginName()) : bowlForm.getTimberOriginName());
			logger.info("ordinal...........: {}", bowlForm.getOrdinal());
			logger.info("imageName.........: {}", bowlForm.getImageName());
			logger.info("price.............: {}", bowlForm.getPrice());
			logger.info("cent..............: {}", bowlForm.getCent());
			logger.info("salesPrice........: {}", bowlForm.getSalesPrice());
			logger.info("salesCent.........: {}", bowlForm.getSalesCent());
			logger.info("salesLocation.....: {}", bowlForm.getSalesLocation());
			logger.info("salesDate.........: {}", bowlForm.getSalesDate());
			logger.info("comment...........: {}", bowlForm.getComment());
			logger.info("emptyTable........: {}", bowlForm.getEmptyTable());
			logger.info("=== End Confirmation of BowlForm Data ===");
		}
	}

	/**
	 * Logging of confirmation of {@code BowlMod} data.
	 *
	 * @param bowlModForm
	 *            the {@code BowlModForm} instance
	 */
	final void logBowlModConfirmation(BowlModForm bowlModForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.mod.form"));
		if (isLog) {
			logger.info("=== Confirmation of BowlMod Form Data ===");
			logger.info("id..............: {}", bowlModForm.getId());
			logger.info("version.........: {}", bowlModForm.getVersion());
			logger.info("bowl.id.........: {}", (bowlModForm.getBowlId() == null)
					? String.valueOf(bowlModForm.getBowlId()) : bowlModForm.getBowlId());
			logger.info("bowlModStep.id..: {}", (bowlModForm.getBowlModStepId() == null)
					? String.valueOf(bowlModForm.getBowlModStepId()) : bowlModForm.getBowlModStepId());
			logger.info("date............: {}", bowlModForm.getDate());
			logger.info("diameter........: {}", bowlModForm.getDiameter());
			logger.info("height..........: {}", bowlModForm.getHeight());
			logger.info("wallthicknessMin: {}", bowlModForm.getWallthicknessMin());
			logger.info("wallthicknessMax: {}", bowlModForm.getWallthicknessMax());
			logger.info("granulation.....: {}", bowlModForm.getGranulation());
			logger.info("tap.............: {}", bowlModForm.getTap());
			logger.info("recess..........: {}", bowlModForm.getRecess());
			logger.info("surface.........: {}", bowlModForm.getSurface());
			logger.info("comment.........: {}", bowlModForm.getComment());
			logger.info("=== End Confirmation of BowlMod Form Data ===");
		}
	}

	/**
	 * Logging of confirmation of {@code BowlModItem} data.
	 *
	 * @param bowlModItemForm
	 *            the {@code BowlModItemForm} instance
	 */
	final void logBowlModItemConfirmation(BowlModItemForm bowlModItemForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.mod.item.form"));
		if (isLog) {
			logger.info("=== Confirmation of BowlModItem Form Data ===");
			logger.info("id.........: {}", bowlModItemForm.getId());
			logger.info("version....: {}", bowlModItemForm.getVersion());
			logger.info("bowlMod.id.: {}", (bowlModItemForm.getBowlModId() == null)
					? String.valueOf(bowlModItemForm.getBowlModId()) : bowlModItemForm.getBowlModId());
			logger.info("text.......: {}", bowlModItemForm.getText());
			logger.info("date.......: {}", bowlModItemForm.getDate());
			logger.info("weight.....: {}", bowlModItemForm.getWeight());
			logger.info("moisture...: {}", bowlModItemForm.getMoisture());
			logger.info("=== End Confirmation of BowlModItem Form Data ===");
		}
	}

	/**
	 * Logging of completion of {@code Bowl} data.
	 *
	 * @param bowlForm
	 *            the {@code BowlForm} instance
	 */
	final void logBowlCompletion(BowlForm bowlForm) {
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.completion"));
		if (isLog) {
			logger.info(bowlForm.asString());
			logger.info("=== Completion of Bowl Form Data ===");
			logger.info("id................: {}", bowlForm.getId());
			logger.info("version...........: {}", bowlForm.getVersion());
			logger.info("index.............: {}", bowlForm.getIndex());
			logger.info("manufacture.id....: {}",
					(bowlForm.getStatusId() == null) ? String.valueOf(bowlForm.getStatusId()) : bowlForm.getStatusId());
			logger.info("status.id.........: {}",
					(bowlForm.getStatusId() == null) ? String.valueOf(bowlForm.getStatusId()) : bowlForm.getStatusId());
			logger.info("timber.id.........: {}",
					(bowlForm.getTimberId() == null) ? String.valueOf(bowlForm.getTimberId()) : bowlForm.getTimberId());
			logger.info("timber.origin.id..: {}", (bowlForm.getTimberOriginId() == null)
					? String.valueOf(bowlForm.getTimberOriginId()) : bowlForm.getTimberOriginId());
			logger.info("customer.id.......: {}", (bowlForm.getCustomerId() == null)
					? String.valueOf(bowlForm.getCustomerId()) : bowlForm.getCustomerId());
			logger.info("exhibition.id: {}", (bowlForm.getExhibitionId() == null)
					? String.valueOf(bowlForm.getExhibitionId()) : bowlForm.getExhibitionId());
			logger.info("timber.origin.name: {}", (bowlForm.getTimberOriginName() == null)
					? String.valueOf(bowlForm.getTimberOriginName()) : bowlForm.getTimberOriginName());
			logger.info("ordinal...........: {}", bowlForm.getOrdinal());
			logger.info("imageName.........: {}", bowlForm.getImageName());
			logger.info("price.............: {}", bowlForm.getPrice());
			logger.info("cent..............: {}", bowlForm.getCent());
			logger.info("salesPrice........: {}", bowlForm.getSalesPrice());
			logger.info("salesCent.........: {}", bowlForm.getSalesCent());
			logger.info("salesLocation.....: {}", bowlForm.getSalesLocation());
			logger.info("salesDate.........: {}", bowlForm.getSalesDate());
			logger.info("comment...........: {}", bowlForm.getComment());
			logger.info("emptyTable........: {}", bowlForm.getEmptyTable());
			logger.info("=== End Completion of Bowl Form Data ===");
		}
	}

	/**
	 * Logging of completion of {@code Bowl} data.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	final void logBowlCompletion(Session session) {
		Long id = null;
		if (session.get("bowl.id") != null) {
			id = new Long(session.get("bowl.id"));
		}
		Integer version = null;
		if (session.get("bowl.version") != null) {
			version = new Integer(session.get("bowl.version")).intValue();
		}
		Integer index = null;
		if (session.get("bowl.index") != null) {
			index = new Integer(session.get("bowl.index")).intValue();
		}
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.completion"));
		if (isLog) {
			logger.info("AbstractBowlController.logBowlCompletion -> Session    id: " + session.getId());
			logger.info("AbstractBowlController.logBowlCompletion -> Session empty: " + session.isEmpty());
			logger.info("=== Completion of Bowl Session Data ===");
			logger.info("id.................: {}", id);
			logger.info("version...........: {}", version);
			logger.info("manufacture.id....: {}", session.get("bowl.manufacture.id"));
			logger.info("status.id.........: {}", session.get("bowl.status.id"));
			logger.info("timber.id.........: {}", session.get("bowl.timber.id"));
			logger.info("timber.origin.id..: {}", session.get("bowl.timber.origin.id"));
			logger.info("customer.id.......: {}", session.get("bowl.customer.id"));
			logger.info("exhibition.id.....: {}", session.get("bowl.exhibition.id"));
			logger.info("timber.origin.name: {}", session.get("bowl.timber.origin.name"));
			logger.info("ordinal...........: {}", session.get("bowl.ordinal"));
			logger.info("imageName.........: {}", session.get("bowl.imageName"));
			logger.info("year..............: {}", session.get("bowl.year"));
			logger.info("price.............: {}", session.get("bowl.price"));
			logger.info("cent..............: {}", session.get("bowl.cent"));
			logger.info("salesPrice........: {}", session.get("bowl.sales.price"));
			logger.info("salesCent.........: {}", session.get("bowl.sales.cent"));
			logger.info("salesLocation.....: {}", session.get("bowl.sales.location"));
			logger.info("salesDate.........: {}", session.get("bowl.sales.date"));
			logger.info("comment...........: {}", session.get("bowl.comment"));
			logger.info("emptyTable........: {}", session.get("bowl.empty.table"));
			logger.info("=== End Completion of Bowl Session Data ===");
		}
	}

	/**
	 * Logging of completion of modified {@code BowlMod} data.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	final void logBowlModCompletion(Session session) {
		Long id = null;
		if (session.get("bowlMod.id") != null) {
			id = new Long(session.get("bowlMod.id"));
		}
		Integer version = null;
		if (session.get("bowlMod.version") != null) {
			version = new Integer(session.get("bowlMod.version")).intValue();
		}
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.mod.completion"));
		if (isLog) {
			logger.info("AbstractBowlController.logBowlModCompletion -> Session    id: " + session.getId());
			logger.info("AbstractBowlController.logBowlModCompletion -> Session empty: " + session.isEmpty());
			logger.info("=== Completion of BowlMod Session Data ===");
			logger.info("id..............: {}", id);
			logger.info("version.........: {}", version);
			logger.info("bowl.id.........: {}", session.get("bowlMod.bowlId"));
			logger.info("bowlModStep.id..: {}", session.get("bowlMod.bowlModStepId"));
			logger.info("date............: {}", session.get("bowlMod.date"));
			logger.info("diameter........: {}", session.get("bowlMod.diameter"));
			logger.info("height..........: {}", session.get("bowlMod.height"));
			logger.info("wallthicknessMin: {}", session.get("bowlMod.wallthicknessMin"));
			logger.info("wallthicknessMax: {}", session.get("bowlMod.wallthicknessMax"));
			logger.info("granulation.....: {}", session.get("bowlMod.granulation"));
			logger.info("surface.........: {}", session.get("bowlMod.surface"));
			logger.info("tap.............: {}", session.get("bowlMod.tap"));
			logger.info("recess..........: {}", session.get("bowlMod.recess"));
			logger.info("comment.........: {}", session.get("bowlMod.comment"));
			logger.info("=== End Completion of BowlMod Session Data ===");
		}
	}

	/**
	 * Logging of completion of modified {@code BowlModItem} data.
	 *
	 * @param session
	 *            the {@code Ninja Session} instance
	 */
	final void logBowlModItemCompletion(Session session) {
		Long id = null;
		if (session.get("bowlModItem.id") != null) {
			id = new Long(session.get("bowlModItem.id"));
		}
		Integer version = null;
		if (session.get("bowlModItem.version") != null) {
			version = new Integer(session.get("bowlModItem.version")).intValue();
		}
		Boolean isLog = new Boolean(ninjaProperties.get("superbowl.log.bowl.mod.item.completion"));
		if (isLog) {
			logger.info("BowlController.logBowlModItemCompletion -> Session    id: " + session.getId());
			logger.info("BowlController.logBowlModItemCompletion -> Session empty: " + session.isEmpty());
			logger.info("=== Completion of BowlModItem Session Data ===");
			logger.info("id........: {}", id);
			logger.info("version...: {}", version);
			logger.info("bowlMod.id: {}", session.get("bowlModItem.bowlModId"));
			logger.info("date......: {}", session.get("bowlModItem.date"));
			logger.info("weight....: {}", session.get("bowlModItem.weight"));
			logger.info("moisture..: {}", session.get("bowlModItem.moisture"));
			logger.info("text......: {}", session.get("bowlModItem.text"));
			logger.info("=== End Completion of BowlModItem Session Data ===");
		}
	}

}
