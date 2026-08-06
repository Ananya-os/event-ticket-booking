package com.ananya.event_ticket_booking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequest {

    @NotBlank(message = "Event name cannot be blank")
    private String name;

    @NotBlank(message = "Venue cannot be blank")
    private String venue;

    @NotNull(message = "Event date and time are required")
    @Future(message = "Event date and time must be in the future")
    private LocalDateTime dateTime;

    @NotNull(message = "Total seats are required")
    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;
}
