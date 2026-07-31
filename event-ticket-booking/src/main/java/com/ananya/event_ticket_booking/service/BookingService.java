package com.ananya.event_ticket_booking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ananya.event_ticket_booking.dto.BookingResponse;
import com.ananya.event_ticket_booking.entity.Booking;
import com.ananya.event_ticket_booking.entity.Event;
import com.ananya.event_ticket_booking.entity.User;
import com.ananya.event_ticket_booking.enums.BookingStatus;
import com.ananya.event_ticket_booking.exception.ResourceNotFoundException;
import com.ananya.event_ticket_booking.exception.SeatUnavailableException;
import com.ananya.event_ticket_booking.repository.BookingRepository;
import com.ananya.event_ticket_booking.repository.EventRepository;
import com.ananya.event_ticket_booking.repository.UserRepository;


@Service
@Transactional
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

    public BookingResponse bookTicket(Long eventId, Integer seats) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Event not found"));

        if (event.getAvailableSeats() < seats) {
            throw new SeatUnavailableException("Not enough seats available");
        }

        event.setAvailableSeats(event.getAvailableSeats() - seats);

        eventRepository.save(event);

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setEvent(event);
        booking.setNumberOfSeats(seats);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingTime(LocalDateTime.now());

        return toResponse(bookingRepository.save(booking));
    }

    @Transactional(readOnly = true)
    public List<BookingResponse> getBookingsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Booking> bookings = isAdmin(authentication)
                ? bookingRepository.findAll()
                : bookingRepository.findByUserEmail(authentication.getName());

        return bookings.stream()
                .map(this::toResponse)
                .toList();
    }

    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    private BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getUser().getId(),
                booking.getEvent().getId(),
                booking.getNumberOfSeats(),
                booking.getStatus(),
                booking.getBookingTime());
    }
}
