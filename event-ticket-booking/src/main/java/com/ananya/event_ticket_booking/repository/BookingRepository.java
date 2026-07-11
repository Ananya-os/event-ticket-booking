package com.ananya.event_ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ananya.event_ticket_booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}