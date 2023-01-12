insert into users
(user_id, username, first_name, last_name, email, phone_nr, role, active)
values
('123', 'SaneManiacMG', 'Mogomotsi', 'Moroane', 'mmoroane@hotmail.com', '0813916607', 'admin', true),
('456', 'dummy1admin', 'dummy1', 'admin', 'dummy1@email.com', '0123456789', 'admin', true),
('789', 'dummy2user', 'dummy2', 'user', 'dummy2@email.com', '1234567890', 'user', true),
('101', 'dummy3deactivatedAcc', 'dummy3', 'deactivatedAcc', 'dummy3@email.com', '1234567899', 'user', false);

insert into logins
(user_id, password, active)
values
('123', 'p@ssw0rd1', true);