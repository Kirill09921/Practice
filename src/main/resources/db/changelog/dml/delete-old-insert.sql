UPDATE ticket SET booking_id = NULL;
UPDATE booking SET ticket_id = NULL;
DELETE FROM booking;
DELETE FROM ticket;
DELETE FROM flight;