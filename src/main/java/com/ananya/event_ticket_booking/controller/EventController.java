package com.ananya.event_ticket_booking.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ananya.event_ticket_booking.dto.EventRequest;
import com.ananya.event_ticket_booking.dto.EventResponse;
import com.ananya.event_ticket_booking.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/events")
@Tag(name = "Events", description = "Event management")
@SecurityRequirement(name = "bearerAuth")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @Operation(summary = "Create an event")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Event created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "403", description = "Administrator role required")
    })
    public ResponseEntity<EventResponse> saveEvent(@Valid @RequestBody EventRequest request) {
        EventResponse event = eventService.saveEvent(request);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(event.id())
                        .toUri())
                .body(event);
    }

    @GetMapping
    @Operation(summary = "List events", description = "Supports page, size, and sort query parameters.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Events returned"),
            @ApiResponse(responseCode = "401", description = "Authentication required")
    })
    public Page<EventResponse> getAllEvents(
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return eventService.getAllEvents(pageable);
    }
}
