package com.ananya.event_ticket_booking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequest {

    @NotBlank(message = "Event name cannot be blank")
    private String name;

    @NotBlank(message = "Venue cannot be blank")
    private String venue;

    private LocalDateTime dateTime;

    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;
}