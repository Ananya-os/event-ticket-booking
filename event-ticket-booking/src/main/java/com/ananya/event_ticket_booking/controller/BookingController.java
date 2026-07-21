package com.ananya.event_ticket_booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ananya.event_ticket_booking.dto.BookingRequest;
import com.ananya.event_ticket_booking.entity.Booking;
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
    public Booking bookTicket(
        @Valid @RequestBody BookingRequest request) {
            return bookingService.bookTicket(
                request.getUserId(),
                request.getEventId(),
                request.getSeats());
            }
            
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}