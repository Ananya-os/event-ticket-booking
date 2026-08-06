package com.ananya.event_ticket_booking.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {

    @NotNull
    @Positive
    private Long eventId;

    @NotNull
    @Positive
    private Integer seats;
}
