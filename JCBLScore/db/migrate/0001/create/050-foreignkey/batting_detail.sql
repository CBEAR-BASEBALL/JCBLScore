alter table BATTING_DETAIL add constraint BATTING_DETAIL_FK1 foreign key (PLAYER_ID) references PLAYER (ID);
alter table BATTING_DETAIL add constraint BATTING_DETAIL_FK2 foreign key (GAME_ID) references GAME (GAME_ID);
alter table BATTING_DETAIL add constraint BATTING_DETAIL_FK3 foreign key (MYTEAM_ID) references TEAM (TEAM_ID);
alter table BATTING_DETAIL add constraint BATTING_DETAIL_FK4 foreign key (PITCHER_ID) references PLAYER (ID);
alter table BATTING_DETAIL add constraint BATTING_DETAIL_FK5 foreign key (TEAM_ID) references TEAM (TEAM_ID);
