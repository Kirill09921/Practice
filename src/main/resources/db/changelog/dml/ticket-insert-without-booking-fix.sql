INSERT INTO ticket (id, price, flight_detail, user_id, flight_id) VALUES
(gen_random_uuid(), 150, 'someFlightDetailOne', (SELECT id FROM user_customer WHERE username = 'userOne'), (SELECT id FROM flight WHERE departure = 'Minsk')),
(gen_random_uuid(), 250, 'someFlightDetailTwo', (SELECT id FROM user_customer WHERE username = 'userTwo'), (SELECT id FROM flight WHERE departure = 'Moscow')),
(gen_random_uuid(), 300, 'someFlightDetailThree', (SELECT id FROM user_customer WHERE username = 'userThree'), (SELECT id FROM flight WHERE departure = 'Warsaw'));
