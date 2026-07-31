package com.ananya.event_ticket_booking.dto;

import java.time.LocalDateTime;

public record EventResponse(
        Long id,
        String name,
        String venue,
        LocalDateTime dateTime,
        Integer totalSeats,
        Integer availableSeats) {
}
