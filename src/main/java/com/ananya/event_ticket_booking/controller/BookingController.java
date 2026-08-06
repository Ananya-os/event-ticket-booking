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

import com.ananya.event_ticket_booking.dto.BookingRequest;
import com.ananya.event_ticket_booking.dto.BookingResponse;
import com.ananya.event_ticket_booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Ticket booking")
@SecurityRequirement(name = "bearerAuth")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @Operation(summary = "Book tickets for an event")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Booking created"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Authentication required"),
            @ApiResponse(responseCode = "409", description = "Seats unavailable")
    })
    public ResponseEntity<BookingResponse> bookTicket(@Valid @RequestBody BookingRequest request) {

        BookingResponse booking = bookingService.bookTicket(
                request.getEventId(),
                request.getSeats());

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(booking.id())
                        .toUri())
                .body(booking);
    }

    @GetMapping
    @Operation(summary = "List accessible bookings", description = "Supports page, size, and sort query parameters.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bookings returned"),
            @ApiResponse(responseCode = "401", description = "Authentication required")
    })
    public Page<BookingResponse> getAllBookings(
            @ParameterObject @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return bookingService.getBookingsForCurrentUser(pageable);
    }
}
