package com.ananya.event_ticket_booking.dto;

import java.time.LocalDateTime;

import com.ananya.event_ticket_booking.enums.BookingStatus;

public record BookingResponse(
        Long id,
        Long userId,
        Long eventId,
        Integer numberOfSeats,
        BookingStatus status,
        LocalDateTime bookingTime) {
}
