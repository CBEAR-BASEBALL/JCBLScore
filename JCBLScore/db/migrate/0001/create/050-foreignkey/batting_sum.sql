alter table BATTING_SUM add constraint BATTING_SUM_FK1 foreign key (PLAYER_ID) references PLAYER (ID);
alter table BATTING_SUM add constraint BATTING_SUM_FK2 foreign key (GAME_ID) references GAME (GAME_ID);
alter table BATTING_SUM add constraint BATTING_SUM_FK3 foreign key (MYTEAM_ID) references TEAM (TEAM_ID);
alter table BATTING_SUM add constraint BATTING_SUM_FK4 foreign key (TEAM_ID) references TEAM (TEAM_ID);
