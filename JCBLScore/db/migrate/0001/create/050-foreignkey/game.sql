alter table GAME add constraint GAME_FK1 foreign key (FIRST_TEAM) references TEAM (TEAM_ID);
alter table GAME add constraint GAME_FK2 foreign key (LAST_TEAM) references TEAM (TEAM_ID);
