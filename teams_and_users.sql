INSERT INTO app_user (username, password, first_name, last_name, bio) VALUES ('NJCrain', '123456', 'Nick', 'Crain', 'test');
INSERT INTO app_user (username, password, first_name, last_name, bio) VALUES ('DarrinHowell', '123456', 'Darrin', 'Howell', 'test');
INSERT INTO app_user (username, password, first_name, last_name, bio) VALUES ('jasonb315', '123456', 'Jason', 'Burns', 'test');

INSERT INTO team (name, difficulty, capacity, date_created, date_going, resort_id) VALUES ('Team 1-1', 1, 3, '12/31/18 12:53PM', '1/4/19 9:00AM', 1);
INSERT INTO team (name, difficulty, capacity, date_created, date_going, resort_id) VALUES ('team 2-1', 2, 2, '9/32/18 2:53PM', '1/14/19 10:00AM', 2);
INSERT INTO team (name, difficulty, capacity, date_created, date_going, resort_id) VALUES ('Team 2-2', 3, 3, '1/1/70 12:53PM', '11/21/19 11:00AM', 2);
INSERT INTO team (name, difficulty, capacity, date_created, date_going, resort_id) VALUES ('Team 3-1', 4, 4, '1/13/15 1:53PM', '11/8/19 8:00AM', 3);
INSERT INTO team (name, difficulty, capacity, date_created, date_going, resort_id) VALUES ('Team 3-2', 1, 4, '12/31/18 12:53AM', '12/31/19 12:00AM', 3);
INSERT INTO team (name, difficulty, capacity, date_created, date_going, resort_id) VALUES ('Team 3-3', 3, 5, '12/1/18 12:53PM', '1/15/19 9:00AM', 3);

INSERT INTO team_members (team_id, user_id) VALUES (1, 1);
INSERT INTO team_members (team_id, user_id) VALUES (1, 2);
INSERT INTO team_members (team_id, user_id) VALUES (1, 3);
INSERT INTO team_members (team_id, user_id) VALUES (2, 1);
INSERT INTO team_members (team_id, user_id) VALUES (2, 3);
INSERT INTO team_members (team_id, user_id) VALUES (3, 2);
INSERT INTO team_members (team_id, user_id) VALUES (3, 3);
INSERT INTO team_members (team_id, user_id) VALUES (4, 1);
INSERT INTO team_members (team_id, user_id) VALUES (4, 3);
INSERT INTO team_members (team_id, user_id) VALUES (5, 1);
INSERT INTO team_members (team_id, user_id) VALUES (5, 2);
INSERT INTO team_members (team_id, user_id) VALUES (5, 3);
INSERT INTO team_members (team_id, user_id) VALUES (6, 2);