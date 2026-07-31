package com.ananya.event_ticket_booking.controller;

import java.util.List;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
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
    public List<BookingResponse> getAllBookings() {
        return bookingService.getBookingsForCurrentUser();
    }
}
