alter table PLAYER add constraint PLAYER_FK1 foreign key (TEAM_ID) references TEAM (TEAM_ID);
