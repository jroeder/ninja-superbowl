/*==============================================================
DBMS     :  H2
Version  :  1.4.193
Database :  superbowlDB
Created  :  07.03.2017 00:00:00
Modified :  03.05.2017 00:00:00
==============================================================*/
select * from BowlModItem;

/*
 * Left Outer Join
 */
select * from BowlMod bm left outer join BowlModItem bmi ON bm.bowlmod_id = bmi.bowlmoditem_bowlmod_id where bm.bowlmod_bowl_id = 1;

/*
 * Right Outer Join
 */
select * from BowlModItem bmi left outer join BowlMod bm ON bmi.bowlmoditem_bowlmod_id = bm.bowlmod_id where bm.bowlmod_bowl_id = 1;

/*
 * Full Outer Join
 * select * from dbo.Students S FULL OUTER JOIN dbo.Advisors A ON S.Advisor_ID=A.Advisor_ID
 */
select * from BowlModItem bmi JOIN BowlMod bm ON bmi.bowlmoditem_bowlmod_id = bm.bowlmod_id;

select * from BowlMod bm INNER JOIN BowlModItem bmi ON bmi.bowlmoditem_bowlmod_id = bm.bowlmod_id;