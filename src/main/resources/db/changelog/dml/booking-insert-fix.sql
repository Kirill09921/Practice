INSERT INTO booking (id, time, departure_time, arrival_time, departure_location, arrival_location, user_id, ticket_id) VALUES
(gen_random_uuid(), '12:30:00', '2025-06-15 08:00:00', '2025-06-15 12:00:00', 'Minsk', 'Berlin',
(SELECT id FROM user_customer WHERE username = 'userOne'), (SELECT id FROM ticket WHERE flight_detail = 'someFlightDetailOne')),

(gen_random_uuid(), '15:00:00', '2025-06-16 10:00:00', '2025-06-16 14:00:00', 'Moscow', 'Paris',
(SELECT id FROM user_customer WHERE username = 'userTwo'), (SELECT id FROM ticket WHERE flight_detail = 'someFlightDetailTwo')),

(gen_random_uuid(), '18:45:00', '2025-06-17 07:30:00', '2025-06-17 11:15:00', 'Warsaw', 'Rome',
(SELECT id FROM user_customer WHERE username = 'userThree'), (SELECT id FROM ticket WHERE flight_detail = 'someFlightDetailThree'));
