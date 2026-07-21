package com.ananya.event_ticket_booking.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {

    private Long userId;

    private Long eventId;

    @Min(1)
    private Integer seats;

}