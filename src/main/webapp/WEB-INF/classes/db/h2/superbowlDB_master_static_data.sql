/*==============================================================
DBMS     :  H2
Version  :  1.4.193
Database :  superbowlDB
Created  :  07.03.2017 00:00:00
Modified :  27.05.2017 00:00:00
==============================================================*/

-- ==================================================
-- Table: ROADMAP
-- ==================================================
DELETE FROM ROADMAP;
INSERT INTO ROADMAP (ROADMAP_VERSION, ROADMAP_FEATURE, ROADMAP_STATUS, ROADMAP_COMMENT, ROADMAP_VERSIONNUMBER)
VALUES
	  (0,  'Table TimberOrigin implementieren', 'finished', 'Implementierung einer Tabelle zur Erfassung des Rohmaterials', '1.0.0-Alpha-1')
	 ,(0,  'TimberOrigin bearbeiten', 'finished', 'Dialog zur Erfassung des Rohmaterials implementieren', '1.0.0-Alpha-2')
	 ,(0,  'BowlMod anlegen', 'finished', 'Dialog zur Erfassung einer Modifikation einer Schale implementieren', '1.0.0-Alpha-3')
	 ,(0,  'BowlModItem anlegen', 'finished', 'Dialog zur Erfassung einer Modifikation eines Items einer Schale implementieren', '1.0.0-Alpha-4')
	 ,(0,  'Exhibition anlegen', 'finished', 'Dialog zur Erfassung einer Ausstellung implementieren', '1.0.0-Alpha-5')
	 ,(0,  'Tabelle Bowl modifizieren', 'finished', 'Tabelle BOWL um Fremdschlüssel zu Customer und Exhibition erweitern', '1.0.0-Alpha-6')
	 ,(0,  'Tabelle Customer modifizieren', 'finished', 'Tabelle CUSTOMER um Feld Index erweitern', '1.0.0-Alpha-7')
	 ,(0,  'Modifikation Datenstruktur GeoRegion', 'in Progress', 'Afrika in vier Teile aufteilen', '1.0.0-Alpha-8')
	 ,(0,  'Modifikation Datenstruktur BotanicSystem', 'in Progress', 'Indices für Order, Family und Subfamily', '1.0.0-Alpha-9')
	 ,(0,  'Ersterfassung Holzart', 'in Progress', 'Daten zu neuer Holzart erfassen', '1.0.0-Alpha-10')
	 ,(0,  'Ersterfassung Botanisches System', 'in Progress', 'Daten zu neuem Botanischem System erfassen', '1.0.0-Alpha-11')
	 ,(0,  'Selektion Rohmaterial', 'in Progress', 'Selektion des Herkunftsortes des RohmaterialsDaten erfassen', '1.0.0-Alpha-12')
	 ,(0,  'Alle zu einem Kunden korrespondierenden Codes modifizieren', 'in Progress', 'Kundenspezifische Codes erweitern', '1.0.0-Alpha-13')
	 ,(0,  'Verknüpfung Schale und Ausstellung', 'in Progress', 'Ver- und Entknüpfung Schale zu Ausstellung implementieren', '1.0.0-Alpha-14')
	 ,(0,  'Schalen in Ausstellung anzeigen', 'work planned', 'Schalen in Ausstellung(en) anzeigen (pro Ausstellung)', '1.0.0-Alpha-15')
	 ,(0,  'Verknüpfung Schale und Käufer', 'work planned', 'Verknüpfung Schale zu Käufer implementieren', '1.0.0-Alpha-16')
	 ,(0,  'Verkäufe anzeigen', 'work planned', 'Verkaufte Schalen anzeigen (total oder pro Käufer)', '1.0.0-Alpha-17')
	 ,(0,  'Minor Bugfix', 'work planned', 'Bugfixes', '1.0.0-Beta-1')
	 ,(0,  'Testing', 'work planned', 'Test of all functions', '1.0.0-Beta-2')
	 ,(0,  'Release', 'work planned', 'Release of Version 1.0.0', '1.0.0')
	 ,(0,  'Image Gallery', 'work planned', 'Implementierung einer Bootstrap Image Gallery', '1.1.0')
	 ,(0,  'Bootstrap Slider', 'work planned', 'Implementierung eines Bootstrap Sliders', '1.2.0')
	 ,(0,  'PDF Druck Daten Schale', 'work planned', 'PDF Druck der Daten zu einer Schale', '1.3.0')
	 ,(0,  'Bootstrap Landing Page', 'work planned', 'Implementierung einer Bootstrap Landing Page', '2.0.0')
	 ,(0,  'User Administration', 'work planned', 'Benutzerverwaltung mit Register/Login/Logout', '3.0.0')
	 ;

COMMIT;

-- ==================================================
-- Table: SOFTWARE
-- ==================================================
DELETE FROM SOFTWARE;
INSERT INTO SOFTWARE (SOFTWARE_VERSION, SOFTWARE_VENDOR, SOFTWARE_NAME, SOFTWARE_URL, SOFTWARE_VERSIONNUMBER, SOFTWARE_TYPE, SOFTWARE_DESCRIPTION)
VALUES
	  (0,  'Almasaeed Studio', 'AdminLTE Control Panel Template', 'https://almsaeedstudio.com/', '2.3.11', 'F', 'Freeware')
	 ,(0,  'Twitter Bootstrap', 'Bootstrap CSS Framework', 'https://getbootstrap.com/', '3.3.7', 'O', 'Open Source')
	 ,(0,  'Ninja Framework', 'Java Microservice Web Framework', 'http://www.ninjaframework.org/', '6.0.0', 'O', 'Open Source')
	 ,(0,  'Apache Freemarker', 'Freemarker Template Engine', 'https://freemarker.apache.org/', '2.3.25', 'O', 'Open Source')
	 ,(0,  'Apache Maven', 'Software Project Management Tool', 'https://maven.apache.org/', '3.5.0', 'O', 'Open Source')
	 ,(0,  'Apache Tomcat', 'Java Web Server', 'https://tomcat.apache.org/', '8.0.42', 'O', 'Open Source')
	 ,(0,  'Oracle Corp.', 'Java Software Development Kit', 'http://www.oracle.com/technetwork/index.html', '8.0.0', 'O', 'Open Source')
	 ,(0,  'jQuery Foundation', 'jQuery JavaScript library', 'http://jquery.com/', '3.2.1', 'O', 'Open Source')
	 ,(0,  'Eclipse Foundation', 'Eclipse Neon IDE', 'http://www.eclipse.org', '4.6.3', 'O', 'Open Source')
	 ,(0,  'H2 Database', 'H2 Database', 'http://www.h2database.com/html/main.html', '1.4.194', 'O', 'Open Source')
	 ,(0,  'JBoss', 'Hibernate ORM', 'http://hibernate.org/', '5.2.10', 'O', 'Open Source')
	 ,(0,  'Logback Project', 'Lockback Logging Framework', 'https://logback.qos.ch/', '2.0.0', 'O', 'Open Source')
	 ,(0,  'Font Awesome', 'Iconic Font and CSS Toolkit', 'http://fontawesome.io/', '4.7.0', 'F', 'Freeware')
	 ,(0,  'DataTables', 'DataTable Table plug-in for jQuery', 'https://datatables.net/manual/styling/bootstrap', '1.0.15', 'F', 'Freeware')
	 ,(0,  'EHCache', 'Java Chache Library', 'http://www.ehcache.org/', '3.3.0', 'F', 'Freeware')
	 ,(0,  'Memcached', 'Java Chache Library', 'https://memcached.org/', '1.4.36', 'F', 'Freeware')
	 ,(0,  'Accordion Wizard', 'Accordion Wizard for Bootstrap 3', 'http://sathomas.me/acc-wizard/#prerequisites', '1.0.0', 'O', 'Open Source')
	 ;

COMMIT;

-- ==================================================
-- Table: SUBUSER
-- ==================================================
DELETE FROM SUBUSER;
INSERT INTO SUBUSER (SUBUSER_VERSION, SUBUSER_USERID, SUBUSER_PASSWORD, SUBUSER_USERNAME, SUBUSER_EMAIL, SUBUSER_LASTLOGIN, SUBUSER_LASTLOGOUT, SUBUSER_LOGINCOUNT, SUBUSER_LOCKED)
VALUES
	 (0,  'tpildner', 'keule', 'Thomas Pildner', 'tp@thomas-pildner.de', CURRENT_TIMESTAMP, NULL, 0, FALSE)
	 ;

COMMIT;

-- ==================================================
-- Table: MANUFACTURE
-- ==================================================
DELETE FROM MANUFACTURE;
INSERT INTO MANUFACTURE (MANU_VERSION, MANU_INDEX, MANU_YEAR)
VALUES
	 (0,  0, '2010')
	,(0,  1, '2011')
	,(0,  2, '2012')
	,(0,  3, '2013')
	,(0,  4, '2014')
	,(0,  5, '2015')
	,(0,  6, '2016')
	,(0,  7, '2017')
	,(0,  8, '2018')
	,(0,  9, '2019')
	,(0, 10, '2020')
	,(0, 11, '2021')
	,(0, 12, '2022')
	,(0, 13, '2023')
	,(0, 14, '2024')
	,(0, 15, '2025')
	,(0, 16, '2026')
	,(0, 17, '2026')
	,(0, 18, '2028')
	,(0, 19, '2029')
	,(0, 20, '2030')
	;

COMMIT;

-- ==================================================
-- Table: GEOREGION
-- ==================================================
DELETE FROM GEOREGION;
INSERT INTO GEOREGION (GR_VERSION, GR_ORDINAL, GR_INDEX, GR_CODE, GR_NAME, GR_REGION)
VALUES
	 (0,  1,  0, 'AF(N)', 'Afrika (Nord)', 'Nordafrika')
	,(0,  2,  1, 'AF(S)', 'Afrika (Süd)', 'Südafrika')
	,(0,  3,  2, 'AF(W)', 'Afrika (West)', 'Westafrika')
	,(0,  4,  3, 'AF(Z)', 'Afrika (Zentral)', 'Zentralafrika')
	,(0,  5,  4, 'AM', 'Amerika', 'Amerika')
	,(0,  6,  5, 'AS', 'Asien', 'Asien')
	,(0,  7,  6, 'AS(AP)', 'Austalasien', 'Asien & Australien/Pazifik')
	,(0,  8,  7, 'AS(EU)', 'Eurasien', 'Asien & Europa')
	,(0,  9,  8, 'AP', 'Ozeaninen', 'Australien/Pazifik')
	,(0, 10,  9, 'EU', 'Europa', 'Europa')
	,(0, 11, 10, 'AM(N)', 'Amerika (Nord)', 'Nordamerika')
	,(0, 12, 11, 'AM(S)', 'Amerika (Süd)', 'Südamerika')
	,(0, 13, 12, 'AM(CS)', 'Amerika (Latein)', 'Zentral & Südamerika')
	,(0, 14, 13, 'AM(C)', 'Amerika (Zentral)', 'Zentralamerika')
	;

COMMIT;

-- ==================================================
-- Table: BOTANICSYSTEM
-- ==================================================
INSERT INTO BOTANICSYSTEM (BS_VERSION, BS_ORDINAL, BS_ORDER_INDEX, BS_FAMILY_INDEX, BS_SUBFAMILY_INDEX, BS_ORDER, BS_FAMILY, BS_SUBFAMILY)
VALUES
	 (0,  1,  0,  0,  0, 'BORAGINALES', 'Boraginaceae', 'Cordioideae')
	,(0,  2,  1,  0,  0, 'CELASTRALES', 'Aquifoliaceae', '')
	,(0,  3,  1,  1,  0, 'CELASTRALES', 'Celastraceae', '')
	,(0,  4,  2,  0,  0, 'CONIFERALES', 'Cupressaceae', '')
	,(0,  5,  2,  1,  0, 'CONIFERALES', 'Taxaceae', '')
	,(0,  6,  3,  0,  0, 'CORNALES', 'Cornaceae', '')
	,(0,  7,  4,  0,  0, 'CUCURBITALES', 'Tetramelaceae', '')
	,(0,  8,  5,  0,  0, 'ERICALES', 'Ebenaceae', '')
	,(0,  9,  5,  1,  0, 'ERICALES', 'Lecythidaceae', '')
	,(0, 10,  5,  2,  3, 'ERICALES', 'Sapotaceae', '')
	,(0, 11,  5,  2,  0, 'ERICALES', 'Sapotaceae', 'Chrysophylloideae')
	,(0, 12,  5,  2,  1, 'ERICALES', 'Sapotaceae', 'Sapotaceae')
	,(0, 13,  5,  2,  2, 'ERICALES', 'Sapotaceae', 'Sapotoideae')
	,(0, 14,  6,  0,  3, 'FABALES', 'Fabaceae', '')
	,(0, 15,  6,  0,  0, 'FABALES', 'Fabaceae', 'Caesalpinioideae')
	,(0, 16,  6,  0,  1, 'FABALES', 'Fabaceae', 'Faboideae')
	,(0, 17,  6,  0,  2, 'FABALES', 'Fabaceae', 'Mimosoideae')
	,(0, 18,  7,  0,  2, 'FAGALES', 'Betulaceae', '')
	,(0, 19,  7,  0,  0, 'FAGALES', 'Betulaceae', 'Betuloideae')
	,(0, 20,  7,  0,  1, 'FAGALES', 'Betulaceae', 'Coryloideae')
	,(0, 21,  7,  1,  0, 'FAGALES', 'Fagaceae', 'Castaneoideae')
	,(0, 22,  7,  1,  1, 'FAGALES', 'Fagaceae', 'Fagoideae')
	,(0, 23,  7,  2,  0, 'FAGALES', 'Juglandaceae', '')
	,(0, 24,  7,  3,  0, 'FAGALES', 'Nothofagaceae', '')
	,(0, 25,  8,  0,  0, 'GENTIANALES', 'Apocynaceae', 'Rauvolfioideae')
	,(0, 26,  8,  1,  0, 'GENTIANALES', 'Rubiaceae', 'Cinchonoideae')
	,(0, 27,  8,  1,  1, 'GENTIANALES', 'Rubiaceae', '')
	,(0, 28,  9,  0,  0, 'LAMIALES', 'Bignoniaceae', '')
	,(0, 29,  9,  1,  0, 'LAMIALES', 'Lamiaceae', '')
	,(0, 30,  9,  2,  0, 'LAMIALES', 'Oleaceae', '')
	,(0, 31, 10,  0,  0, 'LAURALES', 'Lauraceae', '')
	,(0, 32, 11,  0,  0, 'MAGNOLIALES', 'Magnoliaceae', '')
	,(0, 33, 11,  1,  0, 'MAGNOLIALES', 'Myristicaceae', '')
	,(0, 34, 12,  0,  0, 'MAGNOLIOPSIDA', 'Fabaceae', '')
	,(0, 35, 12,  1,  0, 'MAGNOLIOPSIDA', 'Myristicaceae', '')
	,(0, 36, 13,  0,  0, 'MALPIGHIALES', 'Achariaceae', '')
	,(0, 37, 13,  1,  0, 'MALPIGHIALES', 'Calophyllaceae', '')
	,(0, 38, 13,  2,  0, 'MALPIGHIALES', 'Caryocaraceae', '')
	,(0, 39, 13,  3,  0, 'MALPIGHIALES', 'Euphorbiaceae', 'Crotonoideae')
	,(0, 40, 13,  4,  0, 'MALPIGHIALES', 'Ochnaceae', 'Ochnoideae')
	,(0, 41, 13,  5,  0, 'MALPIGHIALES', 'Salicaceae', '')
	,(0, 42, 14,  0,  0, 'MALVALES', 'Dipterocarpaceae', '')
	,(0, 43, 14,  1,  0, 'MALVALES', 'Malvaceae', 'Bombacoideae')
	,(0, 44, 14,  1,  1, 'MALVALES', 'Malvaceae', 'Dombeyoideae')
	,(0, 45, 14,  1,  2, 'MALVALES', 'Malvaceae', 'Helicteroideae')
	,(0, 46, 14,  1,  3, 'MALVALES', 'Malvaceae', 'Sterculioideae')
	,(0, 47, 14,  1,  4, 'MALVALES', 'Malvaceae', 'Tilioideae')
	,(0, 48, 14,  1,  5, 'MALVALES', 'Malvaceae', 'Thymelaeceae')
	,(0, 49, 15,  0,  0, 'MYRTALES', 'Combretaceae', 'Combretoideae')
	,(0, 50, 15,  1,  1, 'MYRTALES', 'Myrtaceae', '')
	,(0, 51, 15,  1,  0, 'MYRTALES', 'Myrtaceae', 'Myrtoideae')
	,(0, 52, 15,  2,  0, 'MYRTALES', 'Vochysiaceae', '')
	,(0, 53, 16,  0,  0, 'PROTEALES', 'Platenaceae', '')
	,(0, 54, 17,  0,  0, 'ROSALES', 'Cannabaceae', '')
	,(0, 55, 17,  1,  0, 'ROSALES', 'Fabaceae', 'Faboideae')
	,(0, 56, 17,  2,  1, 'ROSALES', 'Moraceae', '')
	,(0, 57, 17,  2,  0, 'ROSALES', 'Moraceae', 'Moreae')
	,(0, 58, 17,  3,  0, 'ROSALES', 'Rosaceae', 'Amygdaloideae')
	,(0, 59, 17,  4,  0, 'ROSALES', 'Ulmaceae', '')
	,(0, 60, 18,  0,  0, 'RUBIALES', 'Anacardiaceae', 'Spondiadoideae')
	,(0, 61, 19,  0,  0, 'SANTALALES', 'Santalaceae', '')
	,(0, 62, 20,  0,  0, 'SAPINDALES', 'Aceraceae', 'Hippocastanoideae')
	,(0, 63, 20,  1,  0, 'SAPINDALES', 'Anacardiaceae', 'Anacardioideae')
	,(0, 64, 20,  2,  0, 'SAPINDALES', 'Burseraceae', '')
	,(0, 65, 20,  3,  0, 'SAPINDALES', 'Meliaceae', '')
	,(0, 66, 20,  4,  0, 'SAPINDALES', 'Rutaceae', 'Toddalioideae')
	,(0, 67, 20,  5,  0, 'SAPINDALES', 'Sapindaceae', 'Hippocastanoideae')
	,(0, 68, 20,  5,  1, 'SAPINDALES', 'Sapindaceae', 'Sapindoideae')
	,(0, 69, 20,  6,  0, 'SAPINDALES', 'Simaroubaceae', '')
	,(0, 70, 21,  0,  0, 'SAXIFRAGALES', 'Altingiaceae', '')
	,(0, 71, 22,  0,  0, 'ZYGOPHYLLALES', 'Zygophyllaceae', 'Larreoideae')
	;
	
COMMIT;

-- ==================================================
-- Table: TIMBER
-- ==================================================
DELETE FROM TIMBER;

INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0,  1, 45, 'L', 'TRSC', 'Abachi', 'abachi', 'Triplochiton scleroxylon', '0,25 - 0,5', '45 - 52', '26 - 42', '51 - 72', '4 - 5', '37 - 41', '17 - 21', '5,1 - 6,5', '2,1 - 3,0')
	,(0,  1,  1, 40, 'L', 'LOAL', 'Azobé', 'bongossi', 'Lophira alata', '1,10 - 1,20', '150 - 215', '87 - 108', '165 - 240', '11 - 20', '130 - 160', '55 - 88', '12,4 - 14,0', '6,7 - 9,2')
	,(0,  2,  1, 43, 'L', 'CBPN', 'Ceiba', 'ceiba', 'Ceiba pentandra', '0,21 - 0,40', '', '20 - 25', '31 - 40', '3', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  3,  1, 43, 'L', 'CBFU', 'Ceiba (Fuma)', 'ceibafuma', 'Ceiba pentandra', '0,27', '', '22', '41', '', '', '', '', '')
	,(0,  4,  1, 46, 'L', 'EROB', 'Eyong', 'eyong', 'Sterculia oblonga', '0,72 - 0,8', '', '64 - 71', '141 - 160', '5,1 - 6,3', '', '', '11,1 - 12,5', '4,1 - 5,0')
	,(0,  5,  1, 49, 'L', 'TMIV', 'Framiré', 'framire', 'Terminalia ivorensis', '0,45 - 0,50', '100 - 110', '41 - 53', '76 - 95', '5,1 - 6,3', '38', '16', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  6,  1, 40, 'L', 'TZGB', 'Izombé', '', 'Testulea gabonensis', '0,64 - 0,7', '', '70', '120', '', '', '', '7,5', '4,0')
	,(0,  7,  1, 43, 'L', 'BMBN', 'Kappokier', '', 'Bombax buonopozense', '0,3 - 0,4', '', '', '', '', '', '', '', '')
	,(0,  8,  1, 43, 'L', 'RHBR', 'Kondroti', '', 'Rhodognaphalon brevicuspe', '', '0,36 - 0,60', '', '43', '63', '', '', '7,5', '3,9')
	,(0,  9,  1, 44, 'L', 'NEPP', 'Kotibé', 'kotibe', 'Nesogordonia papaverifera', '0,72 - 0,8', '', '57 - 65', '140 - 180', '8,1 - 9,0', '', '', '8,1 - 9,5', '5,1 - 6,0')
	,(0, 10,  1, 46, 'L', 'PQXX', 'Koto', 'koto', 'Pterygota macrocarpa', '0,51 - 0,56', '80 - 89', '43 - 58', '80 - 90', '6,1 - 7,0', '45', '19 - 22', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 11,  1, 46, 'L', 'STRH', 'Lotofa', 'lotofa', 'Sterculia rhinopetala', '0,73 - 0,8', '', '64 - 71', '140 - 160', '', '', '', '9,8', '5,3')
	,(0, 12,  1, 45, 'L', 'MAAL', 'Mansonia', 'mansonia', 'Mansonia altissima', '0,6', '110 - 120', '53 - 66', '120 - 130', '', '83', '32', '', '')
	,(0, 13,  1, 46, 'L', 'HEXN', 'Niangon', 'niangon', 'Heritiera utilis','0,59 - 0,61', '', '54 - 56', '104 - 106', '5,1 - 6,3', '', '', '6,6 - 8,0', '2,1 - 3,0')

	,(0,  0,  2, 64, 'L', 'CNSC', 'Aielé', 'canarium', 'Canarium schweinfurthii', '0,36 - 0,4', '', '40', '80', '6,3 - 5,1', '', '', '11,1 - 12,5', '7,1 - 8,0')
	,(0,  1,  2, 56, 'L', 'ATTX', 'Ako', 'ako', 'Antiaris toxicaria', '0,41 - 0,45', '', '35 - 40', '80 - 90', '4 - 5', '', '', '6,6 - 8', '4,1 - 5,0')
	,(0,  2,  2, 65, 'L', 'TUAF', 'Avodirè', 'avodire', 'Turraeanthus africanus', '0,64 - 0,71', '91 - 100', '57 - 63', '141 - 160', '10 - 12', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0,  3,  2, 65, 'L', 'GRXX', 'Bossé', 'bosse', 'Guarea cedrata', '0,54 - 0,60', '', '66', '135', '', '', '', '', '')
	,(0,  4,  2, 65, 'L', 'LVTR', 'Dibetou', 'dibetou', 'Lovoa trichilioides', '0,41 - 0,45', '100 - 112', '51 - 56', '91 - 100', '8 - 9', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  5,  2, 65, 'L', 'GRTH', 'Diambi', 'diambi', 'Guarea thompsonii', '0,64 - 0,71', '', '64 - 71', '160 - 180', '12', '', '', '6,6 - 8,0', '5,1 - 6,0')
	,(0,  6,  2, 56, 'L', 'MIXX', 'Iroko', 'iroko', 'Milicia excelsa', '0,60 - 0,65', '', '53 - 55', '99 - 101', '7 - 8', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  7,  2, 65, 'L', 'KHXX', 'Khaya Mahagoni', 'mahagonikha', 'Khaya Ivorensis', '0,45 - 0,55', '', '35 - 40', '80 - 90', '5 - 6', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  8,  2, 65, 'L', 'ENCN', 'Kosipo', 'kosipo', 'Entandrophragma candollei', '0,65', '', '59', '96', '', '', '', '', '')
	,(0,  9,  2, 54, 'L', 'CJXX', 'Ohia', 'ohia', 'Celtis spp.', '0,7 - 0,8', '140 - 160', '52', '110', '14 - 16', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 10,  2, 64, 'L', 'CKGB', 'Okan', 'denya', 'Cylicodiscus gabunensis', '1,04 - 1,25', '', '65 - 110', '103 - 187', '', '', '', '', '')
	,(0, 11,  2, 64, 'L', 'AUKL', 'Okoume', 'okume', 'Aucoumea klaineana', '0,4', '57 - 65', '30 - 41', '50 - 80', '4 - 5', '25 - 35', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 12,  2, 66, 'L', 'ZAHT', 'Olon', 'olon', 'Zanthoxylum heitzii', '0,48 - 0,52', '', '44', '72', '', '', '', '5,7', '3,8')
	,(0, 13,  2, 65, 'L', 'ENCY', 'Sapelli', 'mahagonisapelli', 'Entandrophragma cylindricum', '0,63', '', '60', '140', '7,0 - 8,0', '', '', '6,6 - 8,0', '5,1 - 6,0')
	,(0, 14,  2, 65, 'L', 'ENUT', 'Sipo', 'mahagonisipo', 'Entandrophragma utile', '0,57 - 0,63', '', '55 - 60', '103 - 107', '8,1 - 9,0', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 15,  2, 65, 'L', 'ENAN', 'Tiama', 'tiama', 'Entandrophragma angolense', '0,50 - 0,55', '', '46 - 49', '77 - 81', '', '', '', '', '')
	
	,(0,  0,  3,  8, 'L', 'DSXX', 'Afrikanisches Ebenholz', 'schwarzes-ebenholz', 'Diospyros crassiflora', '0,81 - 0,9', '', '90', '181 - 200', '16 - 18', '', '', '', '')
	,(0,  1,  3, 15, 'L', 'AFXX', 'Afzelia', 'afzelia', 'Afzelia spp.', '0,70 - 0,76', '120', '68 - 85', '110 - 150', '6,4 - 7,1', '33 - 45', '', '3,6 - 5', '3,1 - 4,0')
	,(0,  2,  3, 15, 'L', 'MOXX', 'Andoung', 'andoung', 'Monopetalanthus spp.', '0,52 - 0,66', '', '', '', '', '', '', '', '')
	,(0,  3,  3, 15, 'L', 'PBBI', 'Beli', 'awoura', 'Paraberlinia bifoliolata', '0,76', '', '68', '128', '', '', '', '', '')
	,(0,  4,  3, 14, 'L', 'BEXX', 'Berlinia', 'berlinia', 'Berlinia spp.', '0,58 - 0,67', '', '72 - 80', '180', '8 - 9', '', '', '8,1 - 9,5', '5,1 - 6,0')
	,(0,  5,  3, 15, 'L', 'BRXB', 'Bomanga', 'bomanga', 'Brachystegia laurentii B. - zenkeri', '0,85 - 0,95', '', '49', '95', '6,0 - 7,0', '', '', '4,1 - 6,5', '4,1 - 5,0')
	,(0,  6,  3, 15, 'L', 'GUXX', 'Bubinga', 'bubinga', 'Guibourtia tessmannii', '0,80 - 0,90', '', '66 - 73', '139 - 141', '80', '31 - 45', '', '7,8 - 9,6', '5,0 - 7,5')
	,(0,  7,  3, 13, 'L', 'TGAF', 'Douka', 'douka', 'Tieghemella africana', '0,66', '', '53', '103', '', '', '', '', '')
	,(0,  8,  3,  9, 'L', 'PUMC', 'Essia', 'essia', 'Petersianthus macrocarpus', '0,8 - 0,9', '', '57 - 63', '141 - 160', '7 - 8', '', '', '11,1 - 12,7', '6,1 - 7,0')
	,(0,  9,  3, 15, 'L', 'CFSL', 'Etimoé', 'etimoe', 'Copaifera salikounda', '0,7', '', '', '', '', '', '', '', '')
	,(0, 10,  3, 15, 'L', 'DNXX', 'Faro', 'faro', 'Daniellia ogea', '0,42 - 0,45', '', '34 - 36', '63 - 65', '', '', '', '', '')
	,(0, 11,  3, 15, 'L', 'SPLT', 'Ghéombi', '', 'Sindoropsis le-testui', '0,52 - 0,80', '', '64 - 71', '161 - 180', '8,1 - 9,0', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 12,  3, 15, 'L', 'DDXX', 'Gombé', '', 'Didelotia idae', '0,58', '', '56', '109', '', '', '', '', '')
	,(0, 13,  3, 15, 'L', 'GBDW', 'Limbali', '', 'Gilbertiodendron dewevrei', '0,73 - 0,81', '', '72', '150', '', '', '', '9,8', '4,8')
	,(0, 14,  3, 11, 'L', 'GAXX', 'Longhi', 'longhi', 'Chrysophyllum africanum', '0,57', '', '', '', '', '', '', '', '')
	,(0, 15,  3, 13, 'L', 'TGHC', 'Makoré', 'makore', 'Tieghemella heckelii', '0,62 - 0,8', '', '57 - 63', '160 - 180', '8,1 - 9,0', '', '', '8,1 - 9,5', '6,1 - 7,0')
	,(0, 16,  3, 12, 'L', 'BLTX', 'Moabi', 'moabi', 'Baillonella toxisperma', '0,91 - 1,0', '', '72', '154 - 157', '10 - 12', '', '', '8,1 - 9,5', '6,1 - 7,0')
	,(0, 17,  3, 15, 'L', 'DTBN', 'Movingui', 'movingui', 'Distemonanthus benthamianus', '0,68', '', '60 - 62', '108 - 111', '', '', '', '', '')
	,(0, 18,  3, 15, 'L', 'GUAR', 'Mutényé', 'mutenye', 'Guibourtia arnoldiana', '0,72 - 0,8', '', '74 - 76', '180', '6,0 - 7,0', '', '', '9,6 - 11,0', '4,1 - 5,0')
	,(0, 19,  3, 10, 'L', 'AWCO', 'Mukulungu', 'mukulungu', 'Autranella congolensis', '0,75 - 1,0', '126 - 140', '83', '150', '10 - 12', '', '', '5,1 - 6,5', '4,1 - 5,0')
	,(0, 20,  3,  1, 'L', 'COXB', 'Mukumari (Afrikanische Cordia)', 'mukumari', 'Cordia millenii', '0,3 - 0,5', '64 - 70', '41 - 45', '31 - 40', '6,4 - 7,0', '', '', '3,6 - 5,0', '2,1 - 3,0')
	,(0, 21,  3, 15, 'L', 'BRXN', 'Naga', 'naga', 'Brachystegia cynometroides', '0,64 - 0,71', '', '52 - 56', '98 - 103', '7,2 - 8,0', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 22,  3, 15, 'L', 'GUEH', 'Ovéngkol', 'ovenkol', 'Guibourtia ehie', '0,75 - 0,85', '126 - 140', '81 - 90', '126 - 140', '10 - 12', '71', '', '8,1 - 9,5', '3,1 - 4,0')
	,(0, 23,  3, 15, 'L', 'EYXX', 'Tali', 'tali', 'Erythrophleum suaveolens', '0,85 - 1,07', '', '80 - 85', '140 - 152', '', '', '', '', '')
	,(0, 24,  3, 15, 'L', 'OXOX', 'Tchitola', 'tchitola', 'Oxystigma oxyphyllum', '0,53 - 0,64', '', '58', '88', '', '', '', '7,5 - 10,7', '3,9 - 5,1')
	,(0, 25,  3, 15, 'L', 'GOXX', 'Tola', 'agba', 'Gossweilerodendron balsamiferum', '0,48 - 0,56', '50 - 58', '41 - 45', '70 - 80', '5,1 - 8,0', '15 - 17', '28 - 32', '', '')
	,(0, 26,  3, 16, 'L', 'MTLR', 'Wengé', 'wenge', 'Millettia laurentii', '0,79', '42 - 98', '74 - 79', '125 - 165', '', '', '39 - 50', '', '')
	,(0, 27,  3, 15, 'L', 'MBXX', 'Zebrano', 'zingana', 'Microberlinia brazzavillensis', '0,73', '', '35 - 66', '84 - 102', '', '', '', '10,8 - 11,5', '6,8 - 7,6')
	
	,(0,  0,  4, 27, 'L', 'HLCL', 'Abura', 'abura', 'Fleroya ledermannii', '0,52', '', '43', '78', '8 - 9', '', '', '9,6 - 11,0', '4,0 - 5,0')
	,(0,  1,  4, 16, 'L', 'PTXX', 'Afrikanisches Padouk', 'padouk', 'Pterocarpus soyauxii', '0,70 - 0,80', '137', '55 - 75', '111 - 166', '6 - 7', '', '', '3,6 - 5,0', '2,1 - 3,0')
	,(0,  2,  4, 16, 'L', 'PKEL', 'Afrormosia', 'afrormos', 'Pericopsis elata', '0,65', '130', '60 - 71', '118 - 140', '11 - 16', '31', '31', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  3,  4, 36, 'L', 'SLCO', 'Akossika', 'akossika', 'Scottellia klaineana', '0,9 - 1,1', '', '', '', '', '', '', '', '')
	,(0,  4,  4, 16, 'L', 'AQXX', 'Aningré', 'aningre', 'Aningeria robusta', '0,8', '65 - 70', '55 - 60', '93 - 100', '8 - 7,2', '23 - 27', '49 - 59', '6,6 - 8,0', '4,1 - 5,0')
	,(0,  5,  4, 26, 'L', 'NADD', 'Bilinga', 'bilinga', 'Nauclea diderrichii', '0,71', '2,3 - 2,5', '64', '105', '8 - 9', '', '31', '8,1 - 9,5', '6,1 - 7,0')
	,(0,  6,  4, 17, 'L', 'PIAF', 'Dabema', '', 'Piptadeniastrum africanum', '0,64 - 0,7', '', '57 - 63', '140 - 160', '8 - 9', '', '', '8,1 - 9,5', '3,1 - 4,0')
	,(0,  7,  4, 33, 'L', 'CVPR', 'Ekoune', '', 'Coelocaryon preussii', '0,52 - 0,65', '', '31 - 55', '91 - 100', '5 - 4,1', '', '', '6,6 - 8,0', '2,1 - 3,0')
	,(0,  8,  4, 25, 'L', 'AOCO', 'Emien', '', 'Alstonia congensis', '0,36 - 0,4', '', '31,4 - 35,5 ', '81 - 90', '', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  9,  4, 39, 'L', 'RIXX', 'Erimado', '', 'Ricinodendron heudelotii', '0,26 - 0,33', '', '16 - 20', '40 - 50', '3 - 4', '', '', '5,1 - 6,5', '2,1 - 3,0')
	,(0, 10,  4, 16, 'L', 'DLML', 'Grenadill', 'grenadill-splint', 'Dalbergia melanoxylon', '1,15 - 1,25', '', '85 - 92', '189', '', '133 - 145', '96', '', '')
	,(0, 11,  4, 33, 'L', 'PXAN', 'Ilomba', 'ilomba', 'Pycnanthus angolensis', '0,42 - 0,60', '', '35 - 40', '71 - 80', '5 - 6', '', '', '8,1 - 9,5', '4,1 - 5,0')
	,(0, 12,  4, 31, 'L', 'BIXX', 'Kanda brun', '', 'Beilschmiedia spp.', '0,73 - 0,72', '', '0,85 - 46', '140 - 160', '9 - 10', '', '', '8,1 - 9,5', '3,1 - 4,0')
	,(0, 13,  4, 16, 'L', 'APPT', 'Lati', 'lati', 'Amphimas pterocarpoides', '0,72 - 0,81', '', '52', '126', '', '', '', '12,1', '6,8')
	,(0, 14,  4, 16, 'L', 'PTAN', 'Muninga', 'muninga', 'Pterocarpus angolensis', '0,57 - 0,64', '', '57 - 63', '91 - 100', '14 - 16', '', '', '3,6 - 5,0', '1,1 - 2,0')
	,(0, 15,  4, 33, 'L', 'SSST', 'Niové', 'niove', 'Staudtia stipitata', '0,83 - 0,87', '', '', '', '', '', '', '', '')
	,(0, 16,  4, 16, 'L', 'DABT', 'Ozigo', 'ozigo', 'Dacryodes buettneri', '0,55 - 0,8', '', '58', '130', '6,4 - 7,1', '', '', '6,6 - 8,0', '5,1 - 6,0')
	,(0, 17,  4, 16, 'L', 'MTST', 'Panga panga', 'panga-panga', 'Millettia stuhlmannii', '0,95', '', '', '', '', '', '', '', '')
	,(0, 18,  4, 16, 'L', 'SZXX', 'Pau rosa', 'paurosa', 'Bobgunnia fistuloides', '0,85 - 1,0', '', '', '', '', '', '', '', '')
	;
	
COMMIT;INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0,  5, 23, 'L', 'JGNG', 'Amerikanischer Nussbaum', 'nussbaumus', 'Juglans nigra', '0,58', '90 - 106', '45 - 55', '119 - 147', '', '50', '26', '', '')
	,(0,  1,  5, 39, 'L', 'HVBR', 'Hevea', 'hevea', 'Hevea brasiliensis', '0,62', '', '50 - 53', '103 - 106', '', '', '', '', '')
	,(0,  2,  5, 70, 'L', 'LQST', 'Sweetgum', 'sweetgum', 'Liquidambar styraciflua', '0,52 - 0,56', '', '41 - 45', '80 - 90', '8 - 9', '', '', '9,6 - 11,0', '5,0 - 6,0')
	;
	
COMMIT;
	
INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0,  6, 42, 'L', 'SHBL', 'Balau', 'bangkirai', 'Shorea spp.', '0,76 - 1,1', '', '76', '142', '10 - 12', '', '', '8,1 - 9,5', '4,1 - 5,0')
	,(0,  1,  6, 37, 'L', 'CLXX', 'Bintangor', 'bitangor', 'Calophyllum kajewskii', '0,40 - 0,65', '', '60', '105', '10 - 12', '', '', '', '')
	,(0,  2,  6,  7, 'L', 'OTSU', 'Binuang', '', 'Octomeles sumatrana', '0,26 - 0,4', '', '20 - 25', '40 - 50', '3 - 4', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  3,  6, 42, 'L', 'SHDR', 'Dark red meranti', 'meranti', 'Shorea spp.', '0,4', '120 - 165', '51 - 65', '90 - 126', '12,3 - 12,5', '44 - 50', '21 - 26', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  4,  6, 33, 'L', 'PHMG', 'Gerutu', '', 'Parashorea lucida', '0,69', '', '', '', '', '', '', '', '')
	,(0,  5,  6, 26, 'L', 'ADCR', 'Haldu', '', 'Haldina cordifolia', '0,64 - 0,71', '', '51 - 56', '9,1 - 10,0', '9,0 - 10,0', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  6,  6, 49, 'L', 'TMXX', 'India Laurel', 'laurelindian', 'Terminalia alata', '0,70 - 0,90', '', '57 - 63', '91 - 100', '', '', '', '', '')
	,(0,  7,  6, 25, 'L', 'DYCS', 'Jelutong', 'jelutong', 'Dyera costulata', '0,39 - 0,70', '', '35', '53', '', '', '', '', '')
	,(0,  8,  6, 42, 'L', 'DRXX', 'Kapur', 'keladan', 'Dryobalanops spp.', '0,76', '', '59 - 61', '109 - 112', '', '', '', '', '')
	,(0,  9,  6, 64, 'L', 'CNXX', 'Kedondong', '', 'Canarium spp.', '0,46 - 0,50', '141 - 160', '41 - 45', '71 - 80', '9,1 - 10', '', '', '8,1 - 9,5', '5,1 - 6,0')
	,(0, 10,  6, 42, 'L', 'DPXX', 'Keruing', '', 'Dipterocarpus spp.', '0,72 - 0,80', '201 - 224', '72 - 80', '126 - 140', '14 - 16', '', '', '12,6 - 14,0', '6,1 - 7,0')
	,(0, 11,  6,  8, 'L', 'DSCL', 'Makassar Ebenholz', 'ebenholzmacassar', 'Diospyros celebica', '1,03', '', '78 - 80', '189', '18 - 16 ', '133 - 143', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 12,  6, 46, 'L', 'HEXM', 'Mengkulang', 'mengkulang', 'Heritiera spp.', '0,56 - 0,71', '', '35 - 59', '101', '6,4 - 7,1', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 13,  6, 42, 'L', 'HPXM', 'Merawan', 'merawan', 'Hopea odorata', '0,72 - 0,8', '', '57 - 63', '140 - 160', '12 - 14', '', '', '', '')
	,(0, 14,  6, 15, 'L', 'INXX', 'Merbau', 'merbau', 'Intsia bijuga', '0,72 - 0,8', '', '64 - 71', '126 - 140', '16 - 18', '', '', '8,1 - 9,5', '4,1 - 5,0')
	,(0, 15,  6, 42, 'L', 'ANXX', 'Mersawa','mersawa',  'Anisoptera marginata', '0,6', '', '35 - 40', '63 - 71', '6,4 - 7,1', '', '', '6,6 - 8', '3,1 - 4,0')
	,(0, 16,  6, 23, 'L', 'Y005', 'Nussbaum (Japan)', 'nussbaum-japan', 'Juglans ailantifolia', '0,42 - 0,53', '', '51', '78', '', '', '', '', '')
	,(0, 17,  6, 13, 'L', 'PPXX', 'Nyatoh', '', 'Palaquium spp.', '0,6 - 0,75', '', '', '', '', '', '', '', '')
	,(0, 18,  6, 22, 'L', 'FAOR', 'Orient-Buche', '', 'Fagus orientalis', '0,67', '', '', '', '', '', '', '', '')
	,(0, 19,  6, 16, 'L', 'DLLT', 'Ostindischer Palisander', 'palisanderind', 'Dalbergia latifolia', '0,83', '', '55 - 65', '130 - 138', '', '65 - 90', '34', '', '')
	,(0, 20,  6, 60, 'L', 'DMDA', 'Paldao', 'paldao', 'Dracontomelon dao', '0,60 - 0,65', '', '51 - 56', '100 - 110', '10 - 12', '', '', '8,1 - 9,5', '5,1 - 6,0')
	,(0, 21,  6, 25, 'L', 'AOXX', 'Pulai', '', 'Alstonia scholaris', '0,36 - 0,4', '', '28,1 - 31,5 ', '91 - 100', '', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0, 22,  6, 17, 'L', 'XYXX', 'Pyinkado', 'pyinkado', 'Xylia xylocarpa', '0,87 - 1,0', '81 - 90', '141 - 16', '', '', '', '', '', '')
	,(0, 23,  6, 48, 'L', 'GYBN', 'Ramin', 'ramin', 'Gonystylus bancanus', '0,58', '90 - 110', '60 - 71', '110 - 134', '12,3 - 12,5 ', '', '25', '9,6 - 11,0', '5,1 - 6,0')
	,(0, 24,  6, 42, 'L', 'VAXX', 'Resak', '', 'Vatica spp.', '0,64 - 0,71', '181 - 200', '72 - 80', '126 - 140', '16 - 18', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 25,  6, 41, 'L', 'SNAL', 'Sandelholz', 'sandelholz', 'Santalum album', '0,88 - 0,94', '', '', '', '', '', '', '', '')
	,(0, 26,  6, 14, 'L', 'SDXX', 'Sepetir', '', 'Sindora spp.', '0,57 - 0,66', '', '64 - 71', '101 - 112', '14 - 16', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 27,  6, 29, 'L', 'TEGR', 'Teak', 'teak', 'Tectona grandis', '0,63', '95 - 155', '42 - 59', '58 - 109', '10 - 12', '63 - 71', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0, 28,  6, 42, 'L', 'PHWS', 'White seraya', '', 'Parashorea malaanonan', '0,51 - 0,56', '', '46 - 50', '91 - 100', '9 - 10', '', '', '8,1 - 9,5', '4,1 - 5,0')
	;
	
COMMIT;


INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0,  7, 68, 'L', 'PMPN', 'Kasai', '', 'Pometia pinnata', '0,65 - 0,80', '', '57 - 63', '101 - 112', '12 - 14', '', '', '6,6 - 8,0', '5,1 - 6,0')
	,(0,  1,  7, 50, 'L', 'EUDG', 'Kamarere', '', 'Eucalyptus deglupta', '0,57 - 0,63', '141 - 160', '7 - 63', '101 - 112', '9,1 - 10', '', '', '6,6 - 8,0', '3,1 - 4,0')
	;
	
COMMIT;


INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0,  8, 23, 'L', 'Y004', 'Flügelnuss', '', 'Pterocarya fraxinifolia', '', '', '0,58', '', '', '', '', '', '')
	,(0,  1,  8, 69, 'L', 'AIAL', 'Götterbaum', '', 'Ailanthus altissima', '0,72 - 0,8', '', '46 - 50', '113 - 125', '', '', '', '9,6 - 11', '5,1 - 6,0')
	,(0,  2,  8, 31, 'L', 'Y006', 'Nussbaum (Australien)', 'nussbaum-australien', 'Endiandra palmerstonii', '0,9', '', '', '', '', '', '', '', '')
	;
	
COMMIT;


INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0,  9, 51, 'L', 'EUGL', 'Blue gum', 'bluegum', 'Eucalyptus globulus', '0,91 - 1,0', '', '81 - 90', '140 - 160', '16 - 18', '', '', '9,6 - 11,0', '6,1 - 7,0')
	,(0,  1,  9, 51, 'L', 'EUMR', 'Jarrah', 'jarrah', 'Eucalyptus marginata', '0,76 - 0,9', '', '57 - 63', '113 - 125', '14 - 16', '', '', '6,6 - 8,0', '5,1 - 6,0')
	,(0,  2,  9, 51, 'L', 'EUDV', 'Karri', 'karri', 'Eucalyptus diversicolor', '0,72 - 0,8', '', '72 - 80', '126 - 140', '12 - 14', '', '', '9,6 - 11,0', '6,1 - 7,0')
	,(0,  3,  9, 51, 'L', 'EUCM', 'Red River gum', '', 'Eucalyptus camaldulensis', '0,79 - 0,90', '', '51 - 56', '101 - 112', '14 - 16', '', '', '6,6 - 8,0', '5,1 - 6,0')
	,(0,  4,  9, 23, 'L', 'NOMN', 'Silver beech', '', 'Nothofagus menziesii', '0,46 - 0,50', '', '20 - 25', '50 - 56', '9,1 - 10,0', '', '', '', '')
	;
	
COMMIT;

INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0, 10, 58, 'L', 'MLSY', 'Apfelbaum', 'wildapfel', 'Malus sylvestris', '0,61 - 0,67', '', '', '', '', '', '', '10,1', '5,6')
	,(0,  1, 10, 41, 'L', 'POTL', 'Aspe', 'pappel', 'Populus tremula', '0,40 - 0,45', '69 - 76', '29 - 37', '54 - 76', '', '25 - 33', '10 - 15', '8,1 - 9,5', '3,1 - 4,0')
	,(0,  2, 10, 62, 'L', 'ACPS', 'Bergahorn', 'bergahorn', 'Acer pseudoplatanus', '0,59', '80 - 140', '46 - 62', '85 - 135', '9 - 10', '48 - 61', '27 - 34', '6,6 - 8,0', '2,1 - 3,0')
	,(0,  3, 10, 19, 'L', 'BTPU', 'Birke', 'birke', 'Betula pubescens', '0,57 - 0,63', '130 - 140', '42 - 62', '120 - 144', '16,1 - 18,0', '48', '22 - 35', '12,6 - 14,0', '8,1 - 9,0')
	,(0,  4, 10, 58, 'L', 'PYCM', 'Birnbaum', 'birnbaum', 'Pyrus communis', '0,65 - 0,75', '101', '55 - 60', '74 - 96', '', '59', '32', '6,1 - 9,1', '3,1 - 4,6')
	,(0,  5, 10, 22, 'L', 'FASY', 'Buche', 'bucheungedae', 'Fagus sylvatica', '0,64 - 0,71', '100 - 135', '52 - 64', '90 - 125', '16 - 18', '71', '28 - 41', '7,1 - 8,0', '4,3 - 4,7')
	,(0,  6, 10, 58, 'L', 'SOAU', 'Eberesche', 'eberesche', 'Sorbus aucuparia', '0,44 - 0,74', '81 - 120', '55 - 68', '86 - 126', '10 - 14', '55 - 69', '26 - 42', '14,0 - 9,0', '5,0 - 8,0')
	,(0,  7, 10, 21, 'L', 'CTST', 'Edelkastanie', 'edelkastanie_0', 'Castanea sativa', '0,60 - 0,70', '115 - 142', '40 - 52', '63 - 79', '8,1 - 9,0', '32 - 37', '15 - 23', '5,1 - 6,5', '4,1 - 5,0')
	,(0,  8, 10,  6, 'L', 'TXBC', 'Eibe', 'eibe', 'Taxus baccata', '0,59 - 0,63', '108', '56 - 58', '85', '', '70', '30', '5,7', '3,7')
	,(0,  9, 10, 22, 'L', 'QCXF', 'Eiche', 'eichestiel', 'Quercus robur', '0,64 - 0,71', '90 - 110', '52 - 64', '90 - 110', '12', '50 - 65', '25 - 42', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 10, 10, 58, 'L', 'SOTR', 'Elsbeere', 'elsbeere', 'Sorbus torminalis', '0,63 - 0,87', '130 - 140', '45 - 55', '93 - 108', '', '41 - 57', '21 - 27', '9,2 - 11,6', '6,7 - 7,6')
	,(0, 11, 10, 30, 'L', 'FXEX', 'Esche', 'esche', 'Fraxinus excelsior', '0,41 - 0,82', '130 - 160', '43 - 59', '100 - 127 ', '14 - 16', '64', '30 - 46', '8,1 - 9,5', '5,1 - 6,0 ')
	,(0, 12, 10, 63, 'L', 'Y000', 'Essigbaum', 'essigbaum', 'Rhus typhina', '', '', '', '', '', '', '', '', '')
	,(0, 13, 10, 62, 'L', 'ACCM', 'Feldahorn', '', 'Acer campestre', '0,65 - 0,74', '', '49', '', '', '60', '30', '9', '5')
	,(0, 14, 10, 14, 'L', 'LBAN', 'Goldregen', 'goldregen', 'Laburnum spp.', '0,86', '', '', '', '', '', '', '', '')
	,(0, 15, 10, 18, 'L', 'ALIN', 'Grauerle', 'grauerle', 'Alnus incana', '0,51 - 0,56', '', '', '', '', '', '', '', '')
	,(0, 16, 10, 19, 'L', 'BTPE', 'Hängebirke', 'haengebirke', 'Betula pendula', '0,56 - 0,83', '7,0', '38 - 100', '76 - 155', '12 - 14,5', '48', '22 - 49', '7,8', '5,3')
	,(0, 17, 10, 58, 'L', 'PRAV', 'Kirschbaum', 'kirschbaum', 'Prunus avium', '0,54', '98', '44 - 55', '83 - 110', '', '51 - 58', '28 - 31', '11,0 - 9,6', '5,1 - 6,0')
	,(0, 18, 10, 47, 'L', 'TIXX', 'Linde', 'linde', 'Tilia spp', '0,49', '83', '43 - 53', '88 - 105', '', '37 - 41', '13 - 20', '8,1 - 9,5', '5,1 - 6,0')
	,(0, 19, 10, 58, 'L', 'SOAR', 'Mehlbeere', 'mehlbeere', 'Sorbus aria', '', '', '', '', '', '', '', '', '')
	,(0, 20, 10, 23, 'L', 'JGRG', 'Nussbaum', 'nussbaum', 'Juglans regia', '0,61', '100', '58 - 72', '119 - 147', '7 - 8', '50 - 69', '25 - 28', '6,6 - 8,0', '5,1 - 6,0')
	,(0, 21, 10, 30, 'L', 'OLER', 'Olive', 'olive', 'Olea europaea', '0,81 - 0,9', '', '57 - 63', '', '', '', '', '3,6 - 5,0', '2,1 - 3,0')
	,(0, 22, 10, 58, 'L', 'Y001', 'Pflaumenbaum', 'zweschge', 'Prunus domestica', '0,79', '', '55', '80', '', '', '', '', '')
	,(0, 23, 10, 53, 'L', 'PLXH', 'Platane', 'platane', 'Platanus ×hispanica', '0,58 - 0,8', '98', '56', '104', '9,8 - 12', '40 - 45', '16 - 25', '8,2', '4,5')
	,(0, 24, 10, 16, 'L', 'ROPS', 'Robinie', 'robinie', 'Robinia pseudoacacia', '0,69', '120 - 148', '58 - 72', '118 - 14', '16 - 20', '', '25', '6,5 - 5,1', '3,1 - 4,0')
	,(0, 25, 10, 67, 'L', 'AEHP', 'Rosskastanie', 'rosskastanie', 'Aesculus hippocastanum', '0.5 - 0.6', '79', '30 - 40', '62 - 71', '', '32 - 37', '15 - 23', '6,6 - 8', '4,1 - 5,0')
	,(0, 26, 10, 19, 'L', 'ALGL', 'Schwarzerle', 'erle', 'Alnus glutinosa', '0,42 - 0,64', '71 - 92', '41 - 54', '80 - 95', '4 - 5', '32 - 37', '7 - 17', '8,1 - 9,5', '4,1 - 5,0')
	,(0, 27, 10, 41, 'L', 'PONG', 'Schwarzpappel', 'schwarzpappel', 'Populus nigra', '0,38 - 0,5', '43 - 110', '26 - 55', '47 - 94', '5,1 - 6,3', '', '', '8,1 - 9,5', '5,1 - 6,0')
	,(0, 28, 10, 62, 'L', 'ACPL', 'Spitzahorn', 'spitzahorn', 'Acer platanoides', '0,65 - 0,71', '101 - 112', '64 - 71', '126 - 140', '9 - 10', '', '', '9,5 - 8,1', '4,1 - 5,0')
	,(0, 29, 10,  2, 'L', 'ILAQ', 'Stechpalme', 'stechpalme', 'Ilex aquifolium', '0,64 - 0,83', '', '', '', '', '', '', '', '')
	,(0, 30, 10, 22, 'L', 'QCXE', 'Traubeneiche', 'eichetrauben', 'Quercus petraea', '0,63 - 0,71', '91 - 100', '64 - 71', '100 - 112', '12 - 12,5', '', '', '8,0 - 6,6', '3,1 - 4,0')
	,(0, 31, 10, 58, 'L', 'Y002', 'Traubenkirsche', 'traubenkirsche', 'Prunus padus', '0,51 - 0,56', '', '51 - 56', '80 - 90', '12 - 12,5', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 32, 10, 59, 'L', 'ULGL', 'Ulme', 'ruester', 'Ulmus glabra', '0,48 - 0,86', '65 - 80', '34 - 64', '65 - 110', '7 - 11', '58 - 63', '28 - 37', '6,9 - 8,3', '4,6 - 4,8')
	,(0, 33, 10, 58, 'L', 'Y003', 'Vogelkirsche', 'vogelkirsche', 'Prunus avium', '0,57 - 0,63', '', '51 - 56', '112 - 125', '16 - 18', '', '', '9,6 - 11,0', '5,1 - 6,0')
	,(0, 34, 10, 41, 'L', 'SAXX', 'Weide', 'weide', 'Salix alba', '0,40 - 0,55', '64 - 80', '31 - 35', '31 - 40', '7 - 8', '10 - 15', '25 - 33', '6,6 - 8,0', '2,1 - 3,0')
	,(0, 35, 10, 41, 'L', 'POAL', 'Weißpappel', '', 'Populus alba', '0,46', '', '', '', '', '', '', '', '')
	,(0, 36, 10, 62, 'L', 'ACSC', 'Zuckerahorn', 'zuckerahorn', 'Acer saccharum', '0.57 - 0.7', '', '51 - 56', '100 - 110', '14 - 16', '', '', '8,1 - 9,5', '4,1 - 5')
	;
	
COMMIT;

INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0, 11, 41, 'L', 'POTD', 'Amerikanische Aspe', 'aspen', 'Populus tremuloides', '0,4 - 0,6', '75', '25 - 40', '52 - 80', '6,4 - 7,1', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  1, 11, 22, 'L', 'FAGR', 'Amerikanische Buche', 'beech', 'Fagus grandifolia', '0,64 - 0,71', '', '51 - 56', '100 - 112', '14 - 16', '', '', '9,6 - 11,0', '5,1 - 6,0')
	,(0,  2, 11, 30, 'L', 'FXXX', 'Amerikanische Esche', 'escheus', 'Fraxinus americana', '0,65', '', '50 - 56', '105 - 110', '', '', '', '', '')
	,(0,  3, 11, 47, 'L', 'TIAM', 'Amerikanische Linde', 'basswood', 'Tilia americana', '0,36 - 0,44', '', '26 - 33', '53 - 60', '6,4 - 7,1', '', '', '8,1 - 9,5', '6,1 - 7,0')
	,(0,  4, 11, 59, 'L', 'ULAM', 'Amerikanische Ulme', 'ruesterus', 'Ulmus americana', '0,46 - 0,5', '', '35 - 40', '80 - 90', '10 - 12', '19', '38', '8,1 - 9,5', '4,1 - 5,0')
	,(0,  5, 11, 58, 'L', 'PRSR', 'Amerikanischer Kirschbaum', 'kirschbaumus', 'Prunus serotina', '0,58', '98', '44 - 55', '83 - 100', '12,3 - 12,5', '51 - 58', '28 - 31', '6,6 - 8,0', '3,1 - 4,0')
	,(0,  6, 11, 41, 'L', 'POBL', 'Balsampappel', '', 'Populus balsamifera', '0,38', '', '', '', '', '', '', '', '')
	,(0,  7, 11, 19, 'L', 'BTAL', 'Birke US', 'birkeus', 'Betula alleghaniensis', '0,57 - 0,65', '', '57 - 63', '113 - 125', '12,6 - 14,0', '', '', '8,1 - 9,5', '7,1 - 8,0')
	,(0,  8, 11, 23, 'L', 'CAXY', 'Bitternuss (Bitternut Hickory)', 'bitternuss', 'Carya cordiformis', '0,64 - 0,71', '', '57 - 63', '141 - 160', '18,1 - 20,0', '', '', '9,6 - 11,0', '3,1 - 4,0')
	,(0,  9, 11, 41, 'L', 'PODL', 'Kanadische Schwarzpappel', 'cottonwood', 'Populus deltoides', '', '', '', '', '', '', '', '', '')
	,(0, 10, 11,  4, 'L', 'Y008', 'Mammutbaum', 'mammutbaum', 'Sequoiadendron giganteum', '0,35 - 0,45', '', '35 - 40', '63 - 71', '6,4 - 7,1', '', '', '', '')
	,(0, 11, 11, 56, 'L', 'MAPO', 'Osagedorn', '', 'Maclura pomifera', '', '', '', '', '', '', '', '', '')
	,(0, 12, 11,  8, 'L', 'DSVG', 'Persimmon', 'persimmon', 'Diospyros virginiana', '0,81 - 0,85', '', '', '', '', '', '', '', '')
	,(0, 13, 11,  4, 'L', 'Y009', 'Redwood', 'redwoodcalifornia', 'Sequoia sempervirens', '0,37', '76', '29 - 42', '50 - 69', '7 - 8', '27', '12', '3,6 - 5,0', '2,1 - 3,0')
	,(0, 14, 11, 22, 'L', 'QCXR', 'Roteiche', 'eicherot', 'Quercus rubra', '0,51 - 0,56', '', '46 - 50', '100 - 112', '12', '', '', '8,1 - 9,5', '4,1 - 5,0')
	,(0, 15, 11,  6, 'L', 'NYAQ', 'Tupelo', '', 'Nyssa aquatica', '0,51 - 0,56', '41 - 45', '63 - 71', '10 - 12', '', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 16, 11, 59, 'L', 'ULRB', 'Ulme (Rot)', 'ruesterrot', 'Ulmus rubra', '0,44 - 0,82', '', '', '', '', '', '', '', '')
	,(0, 17, 11,  4, 'L', 'Y010', 'Urweltmammutbaum ', '', 'Metasequoia glyptostroboides', '0,29 - 0,38', '45', '25', '48', '', '', '', '', '')
	,(0, 18, 11, 41, 'L', 'POTR', 'Westliche Balsampappel', '', 'Populus balsamifera subsp. trichocarpa', '0,4 - 0,47', '', '', '', '', '', '', '', '')
	;
	
COMMIT;

INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0, 12, 15, 'L', 'PGXX', 'Amarant', 'amaranth', 'Peltogyne venosa', '0,8 - 1,0', '', '90', '180', '9,1 - 10,0', '', '', '8,1 - 9,5', '6,1 - 7,0')
	,(0,  1, 12, 65, 'L', 'CRGN', 'Andiroba', 'andiroba', 'Carapa guianensis', '0,58', '80 - 155', '37 - 64', '53 - 125', '10,1 - 12,2 ', '', '34 - 48', '8,1 - 9,5', '3,1 - 4,0')
	,(0,  2, 12, 17, 'L', 'DEEX', 'Angelim vermehlo', 'angelin', 'Dinizia excelsa', '1,09 - 1,20', '', '', '118', '', '', '', '', '')
	,(0,  3, 12, 16, 'L', 'CMXX', 'Araribá', 'arariba', 'Centrolobium spp.', '0,87', '', '', '', '', '', '', '', '')
	,(0,  4, 12, 54, 'L', 'BGGN', 'Bagassa', 'bagassa', 'Bagassa guianensis', '0,75 - 0,8', '', '80', '127', '', '', '', '', '')
	,(0,  5, 12, 32, 'L', 'TLOV', 'Baguacu', '', 'Magnolia ovata', '0,57', '', '', '', '', '', '', '', '')
	,(0,  6, 12, 16, 'L', 'DLXX', 'Bahia Rosenholz', 'rosenholzbahia', 'Dalbergia decipularis', '0,95', '93 - 183', '77', '140 - 217', '', '110', '58', '', '')
	,(0,  7, 12, 33, 'L', 'DZXX', 'Virola', '', 'Dialyanthera otoba ', '0,35 - 0,55', '', '', '', '', '', '', '', '')
	,(0,  8, 12, 15, 'L', 'DIXX', 'Basralocus', 'angelique', 'Dicorynia paraensis', '0,7', '', '70', '120', '', '', '', '8,1 - 9,5', '6,1 - 7,0')
	,(0,  9, 12, 23, 'L', 'CAXX', 'Bitternuss', 'hickory', 'Carya tomentosa', '0,74 - 0,78', '150', '52 - 65', '115 - 139', '12,3 - 12,5', '76 - 90', '38 - 43', '9,6 - 11,0', '6,1 - 7,0')
	,(0, 10, 12, 64, 'L', 'PJXX', 'Breu', '', 'Protium spp.', '0,65 - 0,87', '', '', '', '', '', '', '', '')
	,(0, 11, 12, 16, 'L', 'AMCR', 'Cerejeia', 'cerejeira', 'Amburana cearensis', '0,57', '', '49 - 51', '84 - 86', '', '', '', '', '')
	,(0, 12, 12, 19, 'L', 'BTPP', 'Papierbirke', '', 'Betula papyrifera', '0,51 - 0,56', '', '46 - 50', '91 - 100', '9,1 - 10,0', '', '', '8,1 - 9,5', '6,1 - 7,0')
	,(0, 12, 12, 15, 'L', 'CFXX', 'Copaiba', 'copaiba', 'Copaifera spp.', '0,82 - 0,84', '', '81 - 90', '101 - 112', '', '', '', '9,6 - 11,0', '4,1 - 5,0')
	,(0, 13, 12, 16, 'L', 'DXOD', 'Cumaru', 'cumaro', 'Dipteryx odorata', '0.96 - 1,2', '', '85 - 110', '151 - 190', '', '', '', '4,5 - 5,0', '3,2 - 3,8')
	,(0, 14, 12,  3, 'L', 'GPGL', 'Cupiuba', 'goupie', 'Goupia glabra', '0,81', '', '62', '122', '', '', '', '', '')
	,(0, 15, 12,  1, 'L', 'COGL', 'Freijó', 'freijo', 'Cordia goeldiana', '0,4 - 0,7', '', '27 - 29', '85 - 87', '10,1 - 12,2 ', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 16, 12, 57, 'L', 'MCTT', 'Fustic', '', 'Maclura tinctoria', '0,64 - 0,71', '', '46 - 50', '80 - 90', '10,1 - 12,2', '', '', '5,1 - 6,5', '2,1 - 3,0')
	,(0, 17, 12, 31, 'L', 'CHRD', 'Greenheart', 'greenheart', 'Chlorocardium rodiei', '0,9 - 1,1', '', '95 - 132', '180 - 240', '', '', '32 - 57', '', '')
	,(0, 18, 12, 52, 'L', 'QUXX', 'Gronfolo', '', 'Qualea spp.', '0,60 - 0,82', '', '81 - 90', '161 - 180', '9 - 10', '', '', '8,1 - 9,5', '3,1 - 4,0')
	,(0, 19, 12, 66, 'L', 'BFRD', 'Guatambu', 'guatambu', 'Balfourodendron riedelianum', '0,85', '', '81 - 90', '160 - 180', '', '', '', '9,6 - 11,0', '5,1 - 6,0')
	,(0, 20, 12, 31, 'L', 'OCPR', 'Imbuia', 'imbuia', 'Ocotea porosa', '0,64 - 0,71', '', '46 - 50', '80 - 90', '10 - 12', '', '', '5,1 - 6,5', '2,1 - 3,0')
	,(0, 21, 12, 28, 'L', 'TBXX', 'Ipé', 'ipe', 'Handroanthus serratifolius', '0,95 - 1,5', '', '90', '180', '14 - 16', '', '', '6,6 - 8,0', '6,1 - 7,0')
	,(0, 22, 12, 31, 'L', 'MZXX', 'Itaúba', 'itauba', 'Mezilaurus spp.', '0,7 - 0,95', '', '60 - 75', '80 - 120', '12 - 15', '', '', '6,6 - 8,3', '2,4 - 4,3')
	,(0, 23, 12, 52, 'L', 'EIUN', 'Jabuti', '', 'Erisma uncinatum', '0,57 - 0,63', '', '57 - 63', '141 - 160', '4 - 5', '', '', '9,6 - 11,0', '4,1 - 5,0')
	,(0, 24, 12, 37, 'L', 'CLBR', 'Jacareúba', '', 'Calophyllum brasiliense', '0,57 - 0,63', '', '41 - 45', '80 - 90', '10 - 12', '', '', '8,1 - 9,5', '5,1 - 6,0')
	,(0, 25, 12,  9, 'L', 'CZXX', 'Jequitibá', 'jequitiba', 'Cariniana brasiliensis', '0,51 - 0,56', '', '46 - 50', '91 - 100', '', '', '', '5,1 - 6,5', '2,1 - 3,0')
	,(0, 26, 12, 16, 'L', 'DLCR', 'Königsholz', 'kingwood', 'Dalbergia cearensis', '0,9 - 1,0', '', '60 - 80', '100 - 150', '', '', '', '', '')
	,(0, 27, 12, 23, 'L', 'NOPM', 'Lenga', 'lenga', 'Nothofagus pumilio', '0,56 - 0,60', '', '42 - 44', '87 - 89', '', '', '', '', '')
	,(0, 28, 12, 31, 'L', 'OCRB', 'Louro vermelho', 'wane', 'Sextonia rubra', '0,55 - 0,70', '', '35 - 54', '63 - 95', '6 - 9', '', '', '7,5 - 10,0', '3,2 - 4,0')
	,(0, 29, 12, 16, 'L', 'PZXX', 'Macacaúba', 'macacauba', 'Platymiscium trinitatis', '0,85 - 1,10', '', '', '', '', '', '', '', '')
	,(0, 30, 12, 69, 'L', 'SMAM', 'Marupá', 'marupa', 'Simaruba amara', '0,32 - 0,45', '', '35 - 40', '91 - 100', '3,1 - 4,0', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 31, 12, 13, 'L', 'MNXX', 'Massaranduba', 'massaranduba', 'Manilkara bidentata', '0,85 - 1,30', '', '88 - 91', '180 - 190', '16 - 18', '', '', '9,6 - 11,0', '6,1 - 7,0')
	,(0, 32, 12, 56, 'L', 'OMSC', 'Muiratinga', '', 'Maquira sclerophylla', '0,6 - 0,7', '', '', '', '', '', '', '', '')
	,(0, 33, 12, 23, 'L', 'Y007', 'Nogal', 'nogal', 'Juglans neotropica', '0,4 - 0,7', '', '', '', '', '', '', '', '')
	,(0, 34, 12,  1, 'L', 'COXA', 'Pardillo', '', 'Cordia alliodora', '0,51 - 0,56', '', '51 - 56', '101 - 112', '9,1 - 10', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 35, 12, 34, 'L', 'CSGR', 'Partridge', '', 'Caesalpinia granadillo', '1,2', '', '', '', '', '', '', '', '')
	,(0, 36, 12, 66, 'L', 'EXPA', 'Pau amarelo', 'amarelo', 'Euxylophora paraensis', '0,80 - 1,10', '', '64 - 71', '113 - 125', '14 - 16', '', '', '6,5', '5,7')
	,(0, 37, 12, 15, 'L', 'CSEC', 'Pernambuk', 'pernambuk', 'Caesalpinia echinata', '0,75 - 1,0', '', '', '', '', '', '', '', '')
	,(0, 38, 12, 28, 'L', 'PAPR', 'Peroba de campos', '', 'Paratecoma peroba', '0,7 - 0,8', '', '57 - 63', '113 - 126', '16 - 18', '', '', '6,6 - 8,0', '3,1  4,0')
	,(0, 39, 12, 25, 'L', 'ASXX', 'Peroba rosa', 'perobarosa', 'Aspidosperma polyneuron', '0,70 - 0,80', '', '64 - 71', '110 - 125', '4 - 5', '', '', '6,6 - 8,0', '4,1 - 5,0')
	,(0, 40, 12, 38, 'L', 'COXX', 'Piquiá', '', 'Caryocar villosum', '0,85 - 0,95', '', '58 - 71', '94 - 124', '', '', '', '10,2 - 9,4', '4,2 - 6,2')
	,(0, 41, 12, 16, 'L', 'DLNG', 'Rio Palisander', 'palisanderriohell', 'Dalbergia nigra', '0,81 - 0,9', '', '60 - 70', '120 - 135', '12 - 14', '90', '34 - 50', '9,6 - 11,0', '3,1 - 4,0')
	,(0, 42, 12, 16, 'L', 'MHSC', 'Santos Palisander', 'santos-palisander', 'Machaerium scleroxylon', '0,85', '', '64 - 71', '140 - 160', '', '', '', '6,6 - 8,0', '2,1 - 3,0')
	,(0, 43, 12, 56, 'L', 'BSRB', 'Satiné', 'satine', 'Brosimum rubescens', '1,10 - 1,20', '', '64 - 71', '161 - 180', '', '', '', '', '')
	,(0, 44, 12, 56, 'L', 'BSGN', 'Schlangenholz', 'schlangenholz', 'Brosimum guianense', '1,20 - 1,35', '', '', '', '', '', '', '', '')
	,(0, 45, 12, 55, 'L', 'BOXX', 'Sucupira', 'sucupira', 'Bowdichia nitida', '0,85 - 0,95', '', '88', '140', '', '', '', '7,8', '5')
	,(0, 46, 12, 17, 'L', 'CIXX', 'Tauari', 'couratari', 'Couratari fagifolia', '0,55 - 0,75', '', '', '', '', '', '', '7,0', '4,5')
	,(0, 47, 12, 63, 'L', 'AVXX', 'Tigerwood', 'goncaloalves', 'Astronium fraxinifolium', '0,81 - 0,9', '', '72 - 80', '113 - 125', '12 - 14', '', '', '6,6 - 8,0', '3,1 - 4,0')
	,(0, 48, 12, 17, 'L', 'CGCT', 'Tornillo', 'tornillo', 'Cedrelinga cateniformis', '0,7 - 0,8', '', '25 - 40', '50 - 80', '', '', '', '', '')
	,(0, 49, 12, 33, 'L', 'VIXX', 'Virola', 'baboen', 'Virola surinamensis', '0,46 - 0,5', '81 - 90', '35 - 40', '91 - 100', '6,4 - 7,1', '', '', '9,6 - 11,0', '7,1 - 8,0')
	,(0, 50, 12, 15, 'L', 'EPXX', 'Walaba', '', 'Eperua spp.', '0,9', '', '', '', '', '', '', '', '')
	,(0, 51, 12, 20, 'L', 'CPBT', 'Weißbuche', 'buchehein', 'Carpinus betulus', '0,72', '100 - 135', '52 - 64', '90 - 125', '12 - 14', '71', '28 - 41', '11,1 - 12,5', '7,1 - 8,0')
	;
	
COMMIT;

INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0, 13, 65, 'L', 'SWMC', 'Amerikanisches Mahagoni', 'mahagonius', 'Swietenia macrophylla', '0,48 - 0,54', '100 - 110', '51 - 60', '90 - 104', '7 - 8', '45', '14 - 17', '3,6 - 5,0', '3,1 - 4,0')
	,(0,  1, 13, 16, 'L', 'AAXY', 'Andira', '', 'Andira parviflora', '0,79', '', '', '', '', '', '', '', '')
	,(0,  2, 13, 16, 'L', 'MYXX', 'Balsamo', 'balsamo', 'Myroxylon balsamum', '0,9 - 1,15', '', '72 - 80', '160 - 180', '', '', '43', '6,6 - 8,0', '4,1 - 5,0')
	,(0,  3, 13, 65, 'L', 'CEXX', 'Cedro', 'cedro', 'Cedrela odorata', '0,41 - 0,45', '', '25 - 28', '63 - 71', '7,1 - 6,4', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  4, 13, 15, 'L', 'HYCB', 'Courbaril', 'courbaril', 'Hymenaea courbaril', '0,81 - 0,9', '', '72 - 80', '180', '16 - 18', '', '', '5,1 - 6,5', '3,1 - 4,0')
	,(0,  5, 13, 52, 'L', 'VOXX', 'Quaruba', 'quaruba', 'Vochysia tomentosa', '0,40 - 0,60', '64 - 80', '8 - 31', '71 - 80', '7 - 8', '', '', '12,6 - 14,0', '3,1 - 4,0')
	,(0,  6, 13, 23, 'L', 'NOPR', 'Rauli', 'rauli', 'Nothofagus alpina', '0,46 - 0,51', '', '41 - 45', '80 - 90', '12 - 14', '', '', '6,6 - 8,0', '4,1 - 5,0')
	;
	
COMMIT;
	
INSERT INTO TIMBER (TIMBER_VERSION, TIMBER_INDEX, TIMBER_GEOREGION_ID, TIMBER_BOTANICSYSTEM_ID, TIMBER_TYPE, TIMBER_CODE, 
TIMBER_NAME, TIMBER_IMAGENAME, TIMBER_ACADEMICNAME, TIMBER_GROSS_DENSITY, TIMBER_TENSILE_STRENGTH, TIMBER_BURST_STRENGTH,
TIMBER_BENDING_STRENGTH, TIMBER_SHEAR_STRENGTH, TIMBER_BRINELL_HARDNESS1, TIMBER_BRINELL_HARDNESS2, TIMBER_TANGENT_SHRINKAGE,
TIMBER_RADIAL_SHRINKAGE)
VALUES
	 (0,  0, 14, 16, 'L', 'AAXX', 'Andira', 'angelin-0', 'Andira inermis', '0,57 - 0,63', '', '46 - 50', '113 - 125', '13', '', '', '10', '5')
	,(0,  1, 14, 16, 'L', 'DLRT', 'Cocobolo', 'cocobolo', 'Dalbergia retusa', '1,03', '', '71', '', '', '', '', '', '')
	,(0,  2, 14, 16, 'L', 'DLST', 'Honduras Palisander', 'palisandersandomin', 'Dalbergia stevensonii', '1 - 1,15', '', '60 - 70', '120 - 135', '', '', '', '', '')
	,(0,  3, 14, 71, 'L', 'GCXX', 'Pockholz', 'pockholz', 'Guaiacum guatemalense', '1,20 - 1,40', '', '80 - 126', '141 - 177', '', '155 - 165', '90', '', '')
	;
	
COMMIT;

-- ==================================================
-- Table: STATUS
-- ==================================================
DELETE FROM STATUS;
INSERT INTO STATUS (STATUS_VERSION, STATUS_INDEX, STATUS_CODE, STATUS_TEXT, STATUS_COMMENT)
VALUES
	 (0, 0, 'EXHI', 'in Ausstellung', 'Objekt befindet sich in einer Ausstellung')
	,(0, 1, 'MODI', 'in Bearbeitung', 'Objekt befindet sich im manuellen Bearbeitungsprozess')
	,(0, 2, 'STCK', 'in Bestand', 'Objekt befindet sich im Bestand oder in Verkaufsraum')
	,(0, 3, 'REST', 'in Restauration', 'Objekt befindet sich in der Restauration')
	,(0, 4, 'FNSD', 'fertiggestellt', 'Objekt wurde vollendet')
	,(0, 5, 'RSVD', 'reserviert', 'Objekt ist für einen potentiellen Käufer reserviert')
	,(0, 6, 'RSRT', 'restauriert', 'Verkauftes Objekt erfolgreich restauriert')
	,(0, 7, 'PRIV', 'unverkäuflich', 'Objekt steht nicht zum Verkauf')
	,(0, 8, 'SOLD', 'verkauft', 'Objekt ist bereits verkauft')
	,(0, 9, 'GIFT', 'verschenkt', 'Objekt wurde verschenkt')
	;

COMMIT;

-- ==================================================
-- Table: BOWLMODSTEP
-- ==================================================
DELETE FROM BOWLMODSTEP;
INSERT INTO BOWLMODSTEP (BMS_VERSION, BMS_INDEX, BMS_CODE, BMS_NAME, BMS_COMMENT)
VALUES
	 (0, 0, 'VG', 'Vorgedreht', 'Rohmaterial bearbeitet bis zur Rohform')
	,(0, 1, 'FG', 'Final gedreht', 'Rohform bearbeitet bis zur finalen Form')
	,(0, 2, 'FB', 'Final bearbeitet', 'Oberfläche der Finalform bearbeitet')
	;

COMMIT;


