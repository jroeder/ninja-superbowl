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

import dto.BowlDto;
import dto.BowlModDto;
import dto.BowlModItemDto;
import dto.BowlModStepDto;
import dto.BowlModificationDto;

/**
 * Schnittstellenbeschreibung zur Selektion von {@code Bowl} Instanzen aus der
 * Datenbank.
 *
 * @author mbsusr01
 */
public interface BowlService {

	/**
	 * Liefert eine Instanz des Typs {@code Bowl}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code Bowl}
	 * @return the persisted {@code Bowl} instance
	 */
	BowlDto getBowlById(Long id);

	/**
	 * Liefert eine Instanz des Typs {@code Bowl}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code Bowl}
	 * @return the persisted {@code BowlDto} instance
	 */
	BowlDto getBowlById(String id);

	/**
	 * Liefert eine {@code BowlDto} instanz mit dem höchsten Indexwert aus der
	 * {@code Bowl} Tabelle.
	 * 
	 * @return the {@code BowlDto} dto instance
	 */
	BowlDto getBowlMaxIndex();

	/**
	 * Liefert die größte gespeicherte Ordinalnummer des Typs {@code Bowl}.
	 * 
	 * @return the maximum ordinal number of a {@code Bowl} instance
	 */
	BowlDto getBowlMaxOrdinal();

	/**
	 * Liefert eine Instanz des Typs {@code BowlMod}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code BowlMod}
	 * @return the persisted {@code BowlMod} instance
	 */
	BowlModDto getBowlModById(Long id);

	/**
	 * Liefert eine Instanz des Typs {@code BowlMod}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code BowlMod}
	 * @return the persisted {@code BowlMod} instance
	 */
	BowlModDto getBowlModById(String id);

	/**
	 * Liefert eine Instanz des Typs {@code BowlModItem}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code BowlModItem}
	 * @return the persisted {@code BowlModItem} instance
	 */
	BowlModItemDto getBowlModItemById(Long id);

	/**
	 * Liefert eine Instanz des Typs {@code BowlModItem}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code BowlModItem}
	 * @return the persisted {@code BowlModItem} instance
	 */
	BowlModItemDto getBowlModItemById(String id);

	/**
	 * Liefert eine Instanz des Typs {@code BowlModStep}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code BowlModStep}
	 * @return the persisted {@code BowlModStep} instance
	 */
	BowlModStepDto getBowlModStepById(Long id);

	/**
	 * Liefert eine Instanz des Typs {@code BowlModStep}.
	 * 
	 * @param id
	 *            the unique technical identifier of a {@code BowlModStep}
	 * @return the persisted {@code BowlModStep} instance
	 */
	BowlModStepDto getBowlModStepById(String id);

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code Bowl} welche einen bestimmten {@code Status} besitzen.
	 * 
	 * @param statusId
	 *            the unique technical identifier of a {@code Status}
	 * @return the list of all persisted {@code Bowl} instances with a discrete
	 *         {@code Status}
	 */
	List<BowlDto> getBowlsByStatus(Long statusId);

	/**
	 * Liefert eine Liste mit allen persistierten Instanz des Typs {@code Bowl}
	 * welche einen bestimmten {@code Timber} besitzen.
	 * 
	 * @param timberId
	 *            the unique technical identifier of a {@code Timber}
	 * @return the list of all persisted {@code Bowl} instances with a discrete
	 *         {@code Timber}
	 */
	List<BowlDto> getBowlsByTimber(Long timberId);

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code Bowl} welche einen bestimmte Ordinalnummer besitzen.
	 * 
	 * @param ordinal
	 *            the ordinal number of a {@code Bowl}
	 * @return the list of all persisted {@code Bowl} instances with a discrete
	 *         ordinal number
	 */
	List<BowlDto> getBowlsByOrdinal(Integer ordinal);

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code Bowl} welche in einen bestimmten Jahr gefertigt wurden.
	 * 
	 * @param year
	 *            the manufacturing year of a {@code BowlModStep}
	 * @return the list of all persisted {@code Bowl} instances with a discrete
	 *         year
	 */
	List<BowlDto> getBowlsByYear(String year);

	/**
	 * Liefert die größte gespeicherte Ordinalnummer des Typs {@code Bowl}.
	 * 
	 * @return the maximum ordinal number of a {@code Bowl} instance
	 */
	Integer getBowlMaxOrdinalAsInteger();

	/**
	 * Liefert eine {@code BowlDto} instanz mit dem höchsten Indexwert aus der
	 * {@code Bowl} Tabelle.
	 * 
	 * @return the maximum index of a {@code Bowl} instance
	 */
	Integer getBowlMaxIndexAsInteger();

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code Bowl}.
	 * 
	 * @return the list of all persisted {@code Bowl} instances
	 */
	List<BowlDto> listBowls();

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code BowlMod}.
	 * 
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * 
	 * @return the list of all persisted {@code BowlMod} instances
	 */
	List<BowlModDto> listBowlModsByBowlId(Long bowlId);

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code BowlModItem}.
	 * 
	 * @param bowlModId
	 *            the unique technical identifier of a {@code BowlMod}
	 * 
	 * @return the list of all persisted {@code BowlModItem} instances
	 */
	List<BowlModItemDto> listBowlModItemsByBowlModId(Long bowlModId);

	/**
	 * Liefert eine Liste mit allen persistierten Instanzen des Typs
	 * {@code BowlModStep}.
	 * 
	 * @return the list of all persisted {@code BowlModStep} instances
	 */
	List<BowlModStepDto> listBowlModSteps();

	/**
	 * Liefert eine Liste mit allen Modifikationen aus {@code BowlMod} und
	 * {@code BowlModItem} zu einer persistierten Instanz des Typs {@code Bowl}.
	 * <p>
	 * Es erfolgt ein Left Outer Join in der Query um auch alle {@code BowlMod}
	 * Reecors in der Datenbank zu erhalten, welchen noch keine
	 * {@code BowlModItem} Records zu geordnet sind.
	 * 
	 * @param bowlId
	 *            the unique technical identifier of a {@code Bowl}
	 * @return the list of all persisted {@code BowlModStep} instances
	 */
	List<BowlModificationDto> listJoinedBowlModificationsByBowlId(Long bowlId);

	/**
	 * Persistiert eine modifizierte Instanz des Typs {@code Bowl}.
	 * 
	 * @param bowlDto
	 *            the {@code Bowl} Data Transfer Object instance
	 */
	void merge(BowlDto bowlDto);

	/**
	 * Persistiert eine neue Instanz des Typs {@code Bowl}.
	 * 
	 * @param bowlDto
	 *            the {@code Bowl} Data Transfer Object instance
	 */
	void register(BowlDto bowlDto);

	/**
	 * Persistiert eine modifizierte Instanz des Typs {@code BowlMod}.
	 * 
	 * @param bowlModDto
	 *            the {@code BowlMod} Data Transfer Object instance
	 */
	void merge(BowlModDto bowlModDto);

	/**
	 * Persistiert eine neue Instanz des Typs {@code BowlMod}.
	 * 
	 * @param bowlModDto
	 *            the {@code BowlMod} Data Transfer Object instance
	 */
	void register(BowlModDto bowlModDto);

	/**
	 * Persistiert eine neue Instanz des Typs {@code BowlModItem}.
	 * 
	 * @param bowlModItemDto
	 *            the {@code BowlModItem} Data Transfer Object instance
	 */
	void merge(BowlModItemDto bowlModItemDto);

	/**
	 * Persistiert eine modifizierte Instanz des Typs {@code BowlModItem}.
	 * 
	 * @param bowlModItemDto
	 *            the {@code BowlModItem} Data Transfer Object instance
	 */
	void register(BowlModItemDto bowlModItemDto);

}
