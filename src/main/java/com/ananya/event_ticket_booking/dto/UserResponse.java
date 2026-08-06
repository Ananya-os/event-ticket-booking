package com.ananya.event_ticket_booking.dto;

import com.ananya.event_ticket_booking.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role) {
}
