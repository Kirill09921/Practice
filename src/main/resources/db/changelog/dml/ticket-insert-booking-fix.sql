UPDATE ticket SET booking_id = (SELECT id FROM booking WHERE ticket_id = ticket.id);
