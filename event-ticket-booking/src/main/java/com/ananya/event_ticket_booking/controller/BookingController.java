package com.ananya.event_ticket_booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ananya.event_ticket_booking.entity.Booking;
import com.ananya.event_ticket_booking.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking bookTicket(
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam Integer seats) {

        return bookingService.bookTicket(userId, eventId, seats);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}