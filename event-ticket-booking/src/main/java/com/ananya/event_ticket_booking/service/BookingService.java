package com.ananya.event_ticket_booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ananya.event_ticket_booking.entity.Booking;
import com.ananya.event_ticket_booking.entity.Event;
import com.ananya.event_ticket_booking.entity.User;
import com.ananya.event_ticket_booking.enums.BookingStatus;
import com.ananya.event_ticket_booking.repository.BookingRepository;
import com.ananya.event_ticket_booking.repository.EventRepository;
import com.ananya.event_ticket_booking.repository.UserRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            EventRepository eventRepository) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public Booking bookTicket(Long userId, Long eventId, Integer seats) {

        User user = userRepository.findById(userId).orElseThrow();

        Event event = eventRepository.findById(eventId).orElseThrow();

        if (event.getAvailableSeats() < seats) {
            throw new RuntimeException("Not enough seats available");
        }

        event.setAvailableSeats(event.getAvailableSeats() - seats);

        eventRepository.save(event);

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setEvent(event);
        booking.setNumberOfSeats(seats);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingTime(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}