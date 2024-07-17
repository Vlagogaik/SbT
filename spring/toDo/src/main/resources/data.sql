use tododata;
INSERT INTO users (id, username, email, password) VALUES (1, 'Oleg1', 'mail1', '123');
INSERT INTO users (id, username, email, password) VALUES (2, 'Oleg2','mail2', '123');
INSERT INTO users (id, username, email, password) VALUES (3, 'Oleg3','mail3', '123');
INSERT INTO tasks (id, title, description, due_date, status, user_id) VALUES (1, 'todo1', '123', '2024-05-08', 'OPEN', 1);
INSERT INTO tasks (id, title, description, due_date, status, user_id) VALUES (2, 'todo12', '1234', '2024-05-08', 'OPEN', 1);
INSERT INTO tasks (id, title, description, due_date, status, user_id) VALUES (3, 'todo13', '1235', '2024-05-08', 'OPEN', 1);
INSERT INTO tasks (id, title, description, due_date, status, user_id) VALUES (4, 'todo2', '12389', '2024-05-08', 'OPEN', 2);
INSERT INTO tasks (id, title, description, due_date, status, user_id) VALUES (5, 'todo3', '123456', '2024-05-08', 'OPEN', 3);