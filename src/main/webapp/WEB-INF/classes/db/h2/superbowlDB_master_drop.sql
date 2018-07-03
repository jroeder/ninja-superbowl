/*==============================================================
DBMS    :  H2
Version :  1.4.193
Database:  superbowlDB
Created :  08.03.2017 00:00:00
Updated :  27.05.2017 00:00:00
==============================================================*/

/*==============================================================
Drop/Create Schema
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
The CREATE SCHEMA statement creates a schema, which is a way to logically
group objects in a single collection and to provide a unique namespace for objects.
The CREATE SCHEMA statement is subject to access control when the derby.database.sqlAuthorization
property is set to true for the database or system. Only the database owner can create a schema
with a name different from the current user name, and only the the database owner can specify
AUTHORIZATION userName
with a user name other than the current user name.
==============================================================*/
--drop schema sub;
--create schema sub;

/*==============================================================
Drop all indices
==============================================================*/
drop index software_vendor_idx if exists;
drop index timber_georegion_idx if exists;
drop index timber_botanicsystem_idx if exists;
drop index timber_name_idx if exists;
drop index bs_ordinal_idx if exists;
drop index bowl_customer_idx if exists;
drop index bowl_exhibition_idx if exists;
drop index bowl_manufacture_idx if exists;
drop index bowl_status_idx if exists;
drop index bowl_timber_idx if exists;
drop index bowl_timberorigin_idx if exists;
drop index customer_familyname_idx if exists;
drop index customer_email_idx if exists;
drop index georegion_region_idx if exists;
drop index bm_bms_idx if exists;
drop index bmi_bm_idx if exists;
drop index to_timber_idx if exists;
drop index to_city_idx if exists;
drop index to_location_idx if exists;

commit;

/*==============================================================
Drop all tables
==============================================================*/
drop table Audit if exists;
drop table Roadmap if exists;
drop table Software if exists;
drop table Manufacture if exists;
drop table Subuser if exists;
drop table BowlModJoined if exists;
drop table BowlModItem if exists;
drop table BowlMod if exists;
drop table BowlModStep if exists;
drop table Sales if exists;
drop table Customer if exists;
drop table Bowl if exists;
drop table Exhibition if exists;
drop table Status if exists;
drop table TimberOrigin if exists;
drop table Timber if exists;
drop table BotanicSystem if exists;
drop table GeoRegion if exists;

commit;
