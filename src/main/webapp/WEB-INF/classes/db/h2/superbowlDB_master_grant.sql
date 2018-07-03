/*==============================================================
DBMS    :  H2
Version :  1.4.193
Database:  superbowlDB
Created :  23.03.2017 00:00:00
Updated :  27.05.2017 00:00:00
==============================================================*/

/*==============================================================
Granting privileges
==============================================================*/
--grant select on Audit to public;
grant select on Roadmap to public;
grant select on Software to public;
grant select on Manufacture to public;
grant select on Bowl to public;
grant select on BowlMod to public;
grant select on BowlModItem to public;
grant select on BowlModStep to public;
grant select on BotanicSystem to public;
grant select on Customer to public;
grant select on Exhibition to public;
grant select on GeoRegion to public;
grant select on Status to public;
grant select on Timber to public;
grant select on TimberOrigin to public;

-- all on Audit to sa;
grant all on Roadmap to sa;
grant all on Software to sa;
grant all on Manufacture to sa;
grant all on Bowl to sa;
grant all on BowlMod to sa;
grant all on BowlModItem to sa;
grant all on BowlModStep to sa;
grant all on BotanicSystem to sa;
grant all on Customer to sa;
grant all on Exhibition to sa;
grant all on GeoRegion to sa;
grant all on Status to sa;
grant all on Timber to sa;
grant all on TimberOrigin to sa;

commit;