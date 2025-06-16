INSERT INTO booking (id, time, departure_time, arrival_time, departure_location, arrival_location, user_id, ticket_id) VALUES
(gen_random_uuid(), '12:30:00', '2025-06-15 08:00:00', '2025-06-15 12:00:00', 'Minsk', 'Berlin', '7379dfee-08df-4607-8caf-f01db24fec58', 'a548be9f-cf52-4d34-b2e6-d65a32cdad63'),
(gen_random_uuid(), '15:00:00', '2025-06-16 10:00:00', '2025-06-16 14:00:00', 'Moscow', 'Paris', '091c527c-b10b-472f-972d-dd67208644a5', '1c83546f-15ae-4bfd-8d97-ab71fd8d3dfb'),
(gen_random_uuid(), '18:45:00', '2025-06-17 07:30:00', '2025-06-17 11:15:00', 'Warsaw', 'Rome', 'dadc6258-2f26-4f7d-afef-83c067d3aba8', 'fbe64c26-c5f4-4c0f-b917-da70f00a65dc');
