package com.ananya.event_ticket_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ananya.event_ticket_booking.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}