INSERT INTO flight (id, departure, destination, plane_id) VALUES
(gen_random_uuid(), 'Minsk', 'Berlin', (SELECT id FROM plane WHERE model = 'Boeing 737')),
(gen_random_uuid(), 'Moscow', 'Paris', (SELECT id FROM plane WHERE model = 'Boeing 737')),
(gen_random_uuid(), 'Warsaw', 'Rome', (SELECT id FROM plane WHERE model = 'Airbus A320'));
